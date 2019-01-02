package net.evendanan.bazel.mvn.impl;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import net.evendanan.bazel.mvn.api.Target;
import org.junit.Assert;
import org.junit.Test;

import static net.evendanan.bazel.mvn.TestUtils.createDependency;

public class WritersTests {

    private static final String REPO_RULES_MACRO_OUTPUT = "# Loading a drop-in replacement for native.http_file\n" +
            "load('@bazel_tools//tools/build_defs/repo:http.bzl', 'http_file')\n" +
            "\n" +
            "# Repository rules macro to be run in the WORKSPACE file.\n" +
            "def macro_name():\n" +
            "    http_file(name = 'net_evendanan_dep1__artifact__1_2_3',\n" +
            "        urls = ['https://example.com/net/evendanan/dep.jar'],\n" +
            "        downloaded_file_path = 'dep.jar',\n" +
            "    )\n" +
            "\n" +
            "    http_file(name = 'net_evendanan_dep2__artifact__2_0',\n" +
            "        urls = ['https://example.com/com/example/dep2.jar'],\n" +
            "        downloaded_file_path = 'dep2.jar',\n" +
            "    )\n" +
            "\n" +
            "\n";

    private static final String TRANSITIVE_TARGETS_MACRO_OUTPUT = "# Transitive rules macro to be run in the BUILD.bazel file.\n" +
            "# If you use kt_* rules, you MUST provide the correct rule implementation when call this macro, if you decide\n" +
            "# not to provide those implementations we'll try to use java_* rules.\n" +
            "\n" +
            "# This is a help macro to handle Kotlin rules.\n" +
            "def kotlin_jar_support(name, deps, exports, runtime_deps, jar, kt_jvm_import=None, kt_jvm_library=None):\n" +
            "    #In case the developer did not provide a kt_* impl, we'll try to use java_*, should work\n" +
            "    if kt_jvm_import == None:\n" +
            "        native.java_import(name = name,\n" +
            "            jars = [jar],\n" +
            "            deps = deps,\n" +
            "            exports = exports,\n" +
            "            runtime_deps = runtime_deps,\n" +
            "        )\n" +
            "    else:\n" +
            "        kt_jvm_import(name = '{}_kotlin_jar'.format(name),\n" +
            "            jars = [jar],\n" +
            "        )\n" +
            "        kt_jvm_library(name = name,\n" +
            "            deps = deps + [':{}_kotlin_jar'.format(name)],\n" +
            "            exports = exports + [':{}_kotlin_jar'.format(name)],\n" +
            "            runtime_deps = runtime_deps,\n" +
            "        )\n" +
            "\n" +
            "def macro_name(kt_jvm_import=None, kt_jvm_library=None):\n" +
            "    rule(name = 'name_name_1',\n" +
            "    )\n" +
            "\n" +
            "    rule(name = 'name_name_2',\n" +
            "    )\n" +
            "\n" +
            "\n";
    private static final String ALIAS_DEP_1 = "#Auto-generated by https://github.com/menny/bazel-mvn-deps\n"
            + "# for artifact net.evendanan.dep1:artifact\n\n"
            + "alias(name = 'artifact',\n"
            + "    actual = '//path/to/bzl:name_name_1',\n"
            + "    visibility = ['//visibility:public'],\n"
            + ")\n\n";
    private static final String ALIAS_DEP_2 = "#Auto-generated by https://github.com/menny/bazel-mvn-deps\n"
            + "# for artifact net.evendanan.dep2:artifact\n\n"
            + "alias(name = 'artifact',\n"
            + "    actual = '//path/to/bzl:name_name_2',\n"
            + "    visibility = ['//visibility:public'],\n"
            + ")\n\n";
    private static final String ALIAS_DEP_2_MULTIPLE = "#Auto-generated by https://github.com/menny/bazel-mvn-deps\n" +
            "# for artifact net.evendanan.dep2:artifact\n\n" +
            "alias(name = 'artifact',\n" +
            "    actual = '//path/to/bzl:name_name_2',\n" +
            "    visibility = ['//visibility:public'],\n" +
            ")\n\n" +
            "alias(name = 'name_name_2_somethingelse',\n" +
            "    actual = '//path/to/bzl:name_name_2_somethingelse',\n" +
            "    visibility = ['//visibility:public'],\n" +
            ")\n" +
            "\n";
    private static final String ALIAS_DEP_IGNORES_VERSIONS = "#Auto-generated by https://github.com/menny/bazel-mvn-deps\n" +
            "# for artifact net.evendanan.dep1:artifact\n\n" +
            "alias(name = 'artifact',\n" +
            "    actual = '//path/to/bzl:name_name_1',\n" +
            "    visibility = ['//visibility:public'],\n" +
            ")\n\n" +
            "alias(name = 'name_name_2',\n" +
            "    actual = '//path/to/bzl:name_name_2',\n" +
            "    visibility = ['//visibility:public'],\n" +
            ")\n" +
            "\n";
    ;

    private static String readFileContents(final File file) throws Exception {
        final byte[] buffer = new byte[1024];
        StringBuilder builder = new StringBuilder();

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int read = 0;
            while ((read = inputStream.read(buffer)) >= 0) {
                builder.append(new String(buffer, 0, read, Charsets.UTF_8));
            }
        }

        return builder.toString();
    }

    @Test
    public void testRepositoryRulesMacroWriter() throws Exception {
        File outputFile = File.createTempFile("testRepositoryRulesMacroWriter", ".bzl");
        RuleWriters.HttpRepoRulesMacroWriter writer = new RuleWriters.HttpRepoRulesMacroWriter(outputFile, "macro_name");
        writer.write(Arrays.asList(
                TargetsBuilders.HTTP_FILE.buildTargets(
                        createDependency(
                                "net.evendanan.dep1:artifact:1.2.3",
                                "https://example.com/net/evendanan/dep.jar",
                                Arrays.asList("dep_1_1", "dep_1_2"),
                                Collections.emptyList(),
                                Collections.emptyList())).get(0),
                TargetsBuilders.HTTP_FILE.buildTargets(
                        createDependency(
                                "net.evendanan.dep2:artifact:2.0",
                                "https://example.com/com/example/dep2.jar",
                                Arrays.asList("dep_2_1", "dep_2_2"),
                                Arrays.asList("ex_dep_2_1", "ex_dep_2_2"),
                                Collections.emptyList())).get(0)));

        String contents = readFileContents(outputFile);

        Assert.assertEquals(REPO_RULES_MACRO_OUTPUT, contents);
    }

    @Test
    public void testTransitiveRulesAliasWriter() throws Exception {
        File baseFolder = Files.createTempDirectory("testTransitiveRulesAliasWriter").toFile().getAbsoluteFile();
        RuleWriters.TransitiveRulesAliasWriter writer = new RuleWriters.TransitiveRulesAliasWriter(baseFolder, "path/to/bzl");
        writer.write(Arrays.asList(
                new Target("net.evendanan.dep1:artifact:1.2.3", "rule", "name_name_1").setPublicVisibility(),
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2").setPublicVisibility()));

        Assert.assertEquals(ALIAS_DEP_1, readFileContents(new File(baseFolder, "net/evendanan/dep1/artifact/BUILD.bazel")));
        Assert.assertEquals(ALIAS_DEP_2, readFileContents(new File(baseFolder, "net/evendanan/dep2/artifact/BUILD.bazel")));
    }

    @Test
    public void testTransitiveRulesAliasWriterIgnoresVersions() throws Exception {
        File baseFolder = Files.createTempDirectory("testTransitiveRulesAliasWriter").toFile().getAbsoluteFile();
        RuleWriters.TransitiveRulesAliasWriter writer = new RuleWriters.TransitiveRulesAliasWriter(baseFolder, "path/to/bzl");
        writer.write(Arrays.asList(
                new Target("net.evendanan.dep1:artifact:1.2.3", "rule", "name_name_1").setPublicVisibility(),
                new Target("net.evendanan.dep1:artifact:2.0", "rule", "name_name_2").setPublicVisibility()));

        Assert.assertEquals(ALIAS_DEP_IGNORES_VERSIONS, readFileContents(new File(baseFolder, "net/evendanan/dep1/artifact/BUILD.bazel")));
    }

    @Test
    public void testTransitiveRulesAliasWriterOnlyPublicTargets() throws Exception {
        File baseFolder = Files.createTempDirectory("testTransitiveRulesAliasWriter").toFile().getAbsoluteFile();
        RuleWriters.TransitiveRulesAliasWriter writer = new RuleWriters.TransitiveRulesAliasWriter(baseFolder, "path/to/bzl");
        writer.write(Arrays.asList(
                new Target("net.evendanan.dep1:artifact:1.2.3", "rule", "name_name_1").setPublicVisibility(),
                new Target("net.evendanan.dep3:artifact:1.2.3", "rule", "name_name_3"),//not public - only target
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2").setPublicVisibility(),
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2_somethingelse")));//same folder, but not public

        Assert.assertTrue(new File(baseFolder, "net/evendanan/dep1/artifact/BUILD.bazel").isFile());
        Assert.assertTrue(new File(baseFolder, "net/evendanan/dep2/artifact/BUILD.bazel").isFile());
        //if does not have any public targets, will not create build file
        Assert.assertFalse(new File(baseFolder, "net/evendanan/dep3/artifact/BUILD.bazel").isFile());

        Assert.assertEquals(ALIAS_DEP_1, readFileContents(new File(baseFolder, "net/evendanan/dep1/artifact/BUILD.bazel")));
        Assert.assertEquals(ALIAS_DEP_2, readFileContents(new File(baseFolder, "net/evendanan/dep2/artifact/BUILD.bazel")));
    }

    @Test
    public void testTransitiveRulesAliasWriterMultiplePublicTargets() throws Exception {
        File baseFolder = Files.createTempDirectory("testTransitiveRulesAliasWriter").toFile().getAbsoluteFile();
        RuleWriters.TransitiveRulesAliasWriter writer = new RuleWriters.TransitiveRulesAliasWriter(baseFolder, "path/to/bzl");
        writer.write(Arrays.asList(
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2").setPublicVisibility(),
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2_somethingelse").setPublicVisibility()));

        Assert.assertEquals(ALIAS_DEP_2_MULTIPLE, readFileContents(new File(baseFolder, "net/evendanan/dep2/artifact/BUILD.bazel")));
    }

    @Test
    public void testTransitiveRulesAliasWriterWhenTargetFolderDoesNotExist() throws Exception {
        File baseFolder = new File(
                Files.createTempDirectory("testTransitiveRulesAliasWriter").toFile().getAbsoluteFile(),
                "main_deps");

        RuleWriters.TransitiveRulesAliasWriter writer = new RuleWriters.TransitiveRulesAliasWriter(baseFolder, "path/to/bzl");
        writer.write(Arrays.asList(
                new Target("net.evendanan.dep1:artifact:1.2.3", "rule", "name_name_1").setPublicVisibility(),
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2").setPublicVisibility()));

        Assert.assertEquals(ALIAS_DEP_1, readFileContents(new File(baseFolder, "net/evendanan/dep1/artifact/BUILD.bazel")));
        Assert.assertEquals(ALIAS_DEP_2, readFileContents(new File(baseFolder, "net/evendanan/dep2/artifact/BUILD.bazel")));
    }

    @Test
    public void testGetFilePathFromUrl() throws Exception {
        Assert.assertEquals("net/evendanan/lib1/", RuleWriters.getFilePathFromMavenName("net.evendanan", "lib1"));
        Assert.assertEquals("net/even-danan/lib1/", RuleWriters.getFilePathFromMavenName("net.even-danan", "lib1"));
        Assert.assertEquals("net/evendanan/lib-dep1/", RuleWriters.getFilePathFromMavenName("net.evendanan", "lib-dep1"));
    }

    @Test
    public void testTransitiveRulesMacroWriter() throws Exception {
        File outputFile = File.createTempFile("testRepositoryRulesMacroWriter", ".bzl");
        RuleWriters.TransitiveRulesMacroWriter writer = new RuleWriters.TransitiveRulesMacroWriter(outputFile, "macro_name");
        writer.write(Arrays.asList(
                new Target("net.evendanan.dep1:artifact:1.2.3", "rule", "name_name_1"),
                new Target("net.evendanan.dep2:artifact:2.0", "rule", "name_name_2")));

        String contents = readFileContents(outputFile);

        Assert.assertEquals(TRANSITIVE_TARGETS_MACRO_OUTPUT, contents);
    }

}