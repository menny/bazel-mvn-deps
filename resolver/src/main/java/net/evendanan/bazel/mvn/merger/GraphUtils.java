package net.evendanan.bazel.mvn.merger;

import com.google.common.base.Preconditions;
import net.evendanan.bazel.mvn.api.DependencyTools;
import net.evendanan.bazel.mvn.api.model.Dependency;
import net.evendanan.bazel.mvn.api.model.MavenCoordinate;
import net.evendanan.bazel.mvn.api.model.Resolution;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class GraphUtils {

    static String printGraph(Resolution resolution) {
        return printGraph(Collections.singleton(resolution));
    }

    static String printGraph(Collection<Resolution> resolutions) {
        final DependencyTools dependencyTools = new DependencyTools();
        final StringBuilder builder = new StringBuilder();

        DfsTraveller(
                resolutions,
                (dependency, level) -> {
                    for (int i = 0; i < level; i++) {
                        builder.append("  ");
                    }

                    builder.append(dependencyTools.mavenCoordinates(dependency))
                            .append(System.lineSeparator());
                });

        return builder.toString();
    }

    public static void DfsTraveller(
            Collection<Resolution> resolutions, BiConsumer<Dependency, Integer> visitor) {
        Map<MavenCoordinate, Dependency> mapper = new HashMap<>();
        resolutions.forEach(
                resolution ->
                        resolution
                                .allResolvedDependencies()
                                .forEach(dep -> mapper.put(dep.mavenCoordinate(), dep)));

        resolutions.forEach(
                resolution -> DfsTraveller(resolution.rootDependency(), mapper::get, 1, visitor, new HashSet<MavenCoordinate>()));
    }

    private static void DfsTraveller(
            MavenCoordinate mavenCoordinate,
            Function<MavenCoordinate, Dependency> dependencyMap,
            int level,
            BiConsumer<Dependency, Integer> visitor,
            Set<MavenCoordinate> seenDependencies) {
        Dependency dependency =
                Preconditions.checkNotNull(
                        dependencyMap.apply(mavenCoordinate),
                        "Can not find mapping for " + mavenCoordinate);
        if (seenDependencies.contains(mavenCoordinate)) return;
        seenDependencies.add(mavenCoordinate);

        visitor.accept(dependency, level);

        Stream.concat(
                        Stream.concat(
                                dependency.dependencies().stream(), dependency.exports().stream()),
                        dependency.runtimeDependencies().stream())
                .distinct()
                .forEach(child -> DfsTraveller(child, dependencyMap, level + 1, visitor, seenDependencies));

        seenDependencies.remove(mavenCoordinate);
    }

    static void BfsTraveller(
            Collection<Resolution> resolutions, BiConsumer<Dependency, Integer> visitor) {
        Map<MavenCoordinate, Dependency> mapper = new HashMap<>();
        resolutions.forEach(
                resolution ->
                        resolution
                                .allResolvedDependencies()
                                .forEach(dep -> mapper.put(dep.mavenCoordinate(), dep)));

        Queue<MavenCoordinate> queue = new ArrayDeque<>();
        resolutions.forEach(resolution -> queue.add(resolution.rootDependency()));

        while (!queue.isEmpty()) {
            final Dependency dependency =
                    Preconditions.checkNotNull(
                            mapper.get(queue.remove()), "Can not find mapping for " + queue.peek());
            visitor.accept(dependency, queue.size());

            Stream.concat(
                            Stream.concat(
                                    dependency.dependencies().stream(),
                                    dependency.exports().stream()),
                            dependency.runtimeDependencies().stream())
                    .distinct()
                    .forEach(queue::add);
        }
    }
}
