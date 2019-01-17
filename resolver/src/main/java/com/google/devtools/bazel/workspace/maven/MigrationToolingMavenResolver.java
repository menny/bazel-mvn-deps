// Copyright 2015 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.bazel.workspace.maven;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.apache.maven.model.Repository;
import org.apache.maven.model.resolution.UnresolvableModelException;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.util.artifact.JavaScopes;

import static com.google.devtools.bazel.workspace.maven.ArtifactBuilder.InvalidArtifactCoordinateException;
import static com.google.devtools.bazel.workspace.maven.VersionResolver.defaultResolver;

/**
 * Resolves Maven dependencies.
 */
public class MigrationToolingMavenResolver {

    private static final String TOP_LEVEL_ARTIFACT = "pom.xml";

    /**
     * The set of scopes whose artifacts are pulled into the transitive dependency tree.
     * See https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html for
     * more details on how maven handles this.
     */
    private static final Set<String> INHERITED_SCOPES =
            Sets.newHashSet(JavaScopes.COMPILE, JavaScopes.RUNTIME);

    private static final Set<String> NON_INHERITED_SCOPES =
            Sets.newHashSet(JavaScopes.PROVIDED);
    private final DefaultModelResolver modelResolver;
    private final Map<String, Rule> deps;
    private final Map<String, String> restriction;
    private final VersionResolver versionResolver;
    private final Collection<String> blacklist;
    private MigrationToolingMavenResolver(
            DefaultModelResolver modelResolver, VersionResolver versionResolver, Collection<String> blacklist) {
        this.versionResolver = versionResolver;
        this.deps = Maps.newHashMap();
        this.restriction = Maps.newHashMap();
        this.modelResolver = modelResolver;
        this.blacklist = blacklist;
    }

    public MigrationToolingMavenResolver(DefaultModelResolver resolver, Collection<String> blacklist) {
        this(resolver, defaultResolver(), blacklist);
    }

    private static String unversionedCoordinate(Dependency dependency) {
        return dependency.getGroupId() + ":" + dependency.getArtifactId();
    }

    private static String unversionedCoordinateGroup(Dependency dependency) {
        return dependency.getGroupId();
    }

    private static String unversionedCoordinate(Exclusion exclusion) {
        return exclusion.getGroupId() + ":" + exclusion.getArtifactId();
    }

    static boolean isEmpty(CharSequence text) {
        return text==null || text.length()==0;
    }

    /**
     * Returns all maven_jars.
     */
    public Collection<Rule> getRules() {
        return deps.values();
    }

    /**
     * Resolves an artifact as a root of a dependency graph.
     */
    public void resolveRuleArtifacts(Rule rule) {
        DefaultModelResolver.RepoModelSource modelSource;
        try {
            modelSource = modelResolver.resolveModel(rule.getArtifact());
        } catch (UnresolvableModelException e) {
            throw new RuntimeException(e);
        }

        Model model = modelResolver.getEffectiveModel(modelSource.getModelSource());
        if (model!=null) {
            rule.setPackaging(model.getPackaging());
            rule.setLicenses(model.getLicenses());
            rule.setRepository(modelSource.getRepository().getUrl());
            traverseDeps(model, Sets.newHashSet(), Sets.newHashSet(), rule);
        }
    }

    public Optional<Rule> createRule(String artifactCoord) {
        Artifact artifact;
        try {
            artifact = ArtifactBuilder.fromCoords(artifactCoord);
        } catch (InvalidArtifactCoordinateException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }

        Rule rule = new Rule(artifact);

        deps.put(rule.friendlyName(), rule);

        return Optional.of(rule);
    }

    /**
     * Resolves all dependencies from a given "model source," which could be either a URL or a local
     * file.
     */
    private void traverseDeps(Model model, Set<String> scopes, Set<String> exclusions, Rule parent) {
        for (Repository repo : model.getRepositories()) {
            modelResolver.addRepository(repo);
        }

        if (model.getDependencyManagement()!=null) {
            // Dependencies described in the DependencyManagement section of the pom override all others,
            // so resolve them first.
            for (Dependency dependency : model.getDependencyManagement().getDependencies()) {
                restriction.put(
                        Rule.generateFriendlyName(dependency.getGroupId(), dependency.getArtifactId()),
                        dependency.getVersion());
            }
        }
        for (Dependency dependency : model.getDependencies()) {
            addDependency(dependency, model, scopes, exclusions, parent);
        }
    }

    private void addDependency(
            Dependency dependency,
            Model model,
            Set<String> topLevelScopes,
            Set<String> exclusions,
            @Nullable Rule parent) {
        String scope = isEmpty(dependency.getScope()) ? JavaScopes.COMPILE:dependency.getScope();
        if (parent==null) {
            // Top-level scopes get pulled in based on the user-provided scopes.
            if (!topLevelScopes.contains(scope)) {
                return;
            }
        } else {
            // TODO (bazel-devel): Relabel the scope of transitive dependencies so that they match how
            // maven relabels them as described here:
            // https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html
            if (!INHERITED_SCOPES.contains(scope) && !NON_INHERITED_SCOPES.contains(scope)) {
                return;
            }
        }

        if (dependency.isOptional()) {
            return;
        }
        if (exclusions.contains(unversionedCoordinate(dependency)) || exclusions.contains(unversionedCoordinateGroup(dependency))) {
            return;
        }

        if (blacklist.contains(unversionedCoordinate(dependency)) || blacklist.contains(unversionedCoordinateGroup(dependency))) {
            return;
        }

        try {
            Rule artifactRule = new Rule(ArtifactBuilder.fromMavenDependency(dependency, versionResolver));
            artifactRule.setScope(scope);

            HashSet<String> localDepExclusions = Sets.newHashSet(exclusions);
            dependency.getExclusions().forEach(
                    exclusion -> localDepExclusions.add(unversionedCoordinate(exclusion)));

            boolean isNewDependency = addArtifact(artifactRule, model.toString());
            if (isNewDependency) {
                DefaultModelResolver.RepoModelSource depModelSource = modelResolver.resolveModel(
                        dependency.getGroupId(), dependency.getArtifactId(), dependency.getClassifier()==null ? "":dependency.getClassifier(), dependency.getVersion());
                if (depModelSource!=null) {
                    //artifactRule.setRepository(depModelSource.getLocation());
                    Model depModel = modelResolver.getEffectiveModel(depModelSource.getModelSource());
                    if (depModel!=null) {
                        artifactRule.setPackaging(depModel.getPackaging());
                        artifactRule.setRepository(depModelSource.getRepository().getUrl());
                        traverseDeps(depModel, topLevelScopes, localDepExclusions, artifactRule);
                    }
                } else {
                    System.out.println("Could not get a model for " + dependency);
                }
            }

            if (parent==null) {
                addArtifact(artifactRule, TOP_LEVEL_ARTIFACT);
            } else {
                parent.addDependency(artifactRule.scope(), artifactRule);
            }
        } catch (UnresolvableModelException | InvalidArtifactCoordinateException e) {
            System.out.println("Could not resolve dependency " + dependency.getGroupId()
                    + ":" + dependency.getArtifactId() + ":" + dependency.getVersion() + ": "
                    + e.getMessage() + ": " + e.getClass().getName());
        }
    }

    /**
     * Adds the artifact to the map of deps, if it is not already there. Returns if the artifact
     * was newly added. If the artifact was in the list at a different version, adds an comment
     * about the desired version.
     */
    private boolean addArtifact(Rule dependency, String parent) {
        String artifactName = dependency.friendlyName();
        if (deps.containsKey(artifactName)) {
            Rule existingDependency = deps.get(artifactName);
            // Check that the versions are the same.
            if (!existingDependency.version().equals(dependency.version())) {
                existingDependency.addParent(parent + " wanted version " + dependency.version());
            } else {
                existingDependency.addParent(parent + " got requested version");
            }

            dependency.setPackaging(existingDependency.packaging());
            dependency.setRepository(existingDependency.getRepository());
            return false;
        }

        updateVersion(artifactName, dependency);
        deps.put(artifactName, dependency);
        dependency.addParent(parent);

        return true;
    }

    /**
     * TODO: this should be removed once this uses Maven's own version resolution.
     */
    private void updateVersion(String artifactName, Rule dependency) {
        VersionRange versionRange;
        if (!restriction.containsKey(artifactName)) {
            return;
        }
        String versionRestriction = restriction.get(artifactName);
        try {
            versionRange = VersionRange.createFromVersionSpec(versionRestriction);
        } catch (InvalidVersionSpecificationException e) {
            System.out.println("Error parsing version " + versionRestriction + ": " + e.getLocalizedMessage());
            // So that this isn't logged over and over.
            restriction.remove(artifactName);
            return;
        }
        if (!versionRange.containsVersion(new DefaultArtifactVersion(dependency.version()))) {
            try {
                dependency.setVersion(
                        versionResolver.resolveVersion(dependency.groupId(), dependency.artifactId(), dependency.classifier(), versionRestriction));
            } catch (InvalidArtifactCoordinateException e) {
                System.out.println("Error setting version: " + e.getLocalizedMessage());
            }
        }
    }
}
