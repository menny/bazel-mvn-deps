package net.evendanan.bazel.mvn.impl;

import net.evendanan.bazel.mvn.api.DependencyTools;
import net.evendanan.bazel.mvn.api.Target;
import net.evendanan.bazel.mvn.api.model.Dependency;
import net.evendanan.bazel.mvn.api.model.License;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.evendanan.bazel.mvn.TestUtils.createDependency;

public class FormattersTests {

    private static final String HTTP_FILE_TEXT =
            " http_file(\n"
                    + "     name = \"aar__lib__\",\n"
                    + "     urls = [\"https://some_url/ss.aar\"],\n"
                    + "     downloaded_file_path = \"ss.aar\",\n"
                    + " )\n";
    private static final String JAVA_IMPORT_TEXT =
            " java_import(\n"
                    + "     name = \"java__lib__\",\n"
                    + "     jars = [\"@java__lib__//file\"],\n"
                    + "     tags = [\"maven_coordinates=java:lib:\"],\n"
                    + "     licenses = [],\n"
                    + "     deps = [\n"
                    + "         \":safe_mvn__dep1\",\n"
                    + "         \":safe_mvn__dep2\",\n"
                    + "     ],\n"
                    + "     exports = [\n"
                    + "         \":safe_mvn__export1\",\n"
                    + "         \":safe_mvn__export2\",\n"
                    + "     ],\n"
                    + "     runtime_deps = [\n"
                    + "         \":safe_mvn__runtime1\",\n"
                    + "         \":safe_mvn__runtime2\",\n"
                    + "     ],\n"
                    + " )\n"
                    + " native.alias(\n"
                    + "     name = \"java__lib\",\n"
                    + "     actual = \":java__lib__\",\n"
                    + "     visibility = [\"//visibility:public\"],\n"
                    + " )\n";
    private static final String JAVA_IMPORT_TEXT_WITH_SOURCES =
            " java_import(\n"
                    + "     name = \"java__lib__\",\n"
                    + "     jars = [\"@java__lib__//file\"],\n"
                    + "     tags = [\"maven_coordinates=java:lib:\"],\n"
                    + "     licenses = [],\n"
                    + "     deps = [\n"
                    + "         \":safe_mvn__dep1\",\n"
                    + "         \":safe_mvn__dep2\",\n"
                    + "     ],\n"
                    + "     exports = [\n"
                    + "         \":safe_mvn__export1\",\n"
                    + "         \":safe_mvn__export2\",\n"
                    + "     ],\n"
                    + "     runtime_deps = [\n"
                    + "         \":safe_mvn__runtime1\",\n"
                    + "         \":safe_mvn__runtime2\",\n"
                    + "     ],\n"
                    + "     srcjar = \"@java__lib____sources//file\",\n"
                    + " )\n"
                    + " native.alias(\n"
                    + "     name = \"java__lib\",\n"
                    + "     actual = \":java__lib__\",\n"
                    + "     visibility = [\"//visibility:public\"],\n"
                    + " )\n";
    private static final String POM_ONLY_NATIVE_IMPORT_TEXT =
            " java_import(\n"
                    + "     name = \"parent__lib__\",\n"
                    + "     jars = [],\n"
                    + "     tags = [\"maven_coordinates=parent:lib:\"],\n"
                    + "     licenses = [],\n"
                    + "     deps = [\n"
                    + "         \":safe_mvn__dep1\",\n"
                    + "         \":safe_mvn__dep2\",\n"
                    + "     ],\n"
                    + "     exports = [\n"
                    + "         \":safe_mvn__export1\",\n"
                    + "         \":safe_mvn__export2\",\n"
                    + "     ],\n"
                    + "     runtime_deps = [\n"
                    + "         \":safe_mvn__runtime1\",\n"
                    + "         \":safe_mvn__runtime2\",\n"
                    + "     ],\n"
                    + " )\n"
                    + " native.alias(\n"
                    + "     name = \"parent__lib\",\n"
                    + "     actual = \":parent__lib__\",\n"
                    + "     visibility = [\"//visibility:public\"],\n"
                    + " )\n";
    private static final String NATIVE_JAVA_IMPORT_TEXT =
            "    java_import(\n"
                    + "        name = \"java__lib__\",\n"
                    + "        jars = [\"@java__lib__//file\"],\n"
                    + "        tags = [\"maven_coordinates=java:lib:\"],\n"
                    + "        licenses = [],\n"
                    + "        deps = [\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "        ],\n"
                    + "        exports = [\n"
                    + "            \":safe_mvn__export1\",\n"
                    + "            \":safe_mvn__export2\",\n"
                    + "        ],\n"
                    + "        runtime_deps = [\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"java__lib\",\n"
                    + "        actual = \":java__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT =
            "    java_import(\n"
                    + "        name = \"java__lib__\",\n"
                    + "        jars = [\"@java__lib__//file\"],\n"
                    + "        tags = [\n"
                    + "            \"mabel_license_detected_type=Apache\",\n"
                    + "            \"mabel_license_name=Apache-2\",\n"
                    + "            \"mabel_license_url=http://some.com/url/to/license\",\n"
                    + "            \"maven_coordinates=java:lib:\",\n"
                    + "        ],\n"
                    + "        licenses = [\"notice\"],\n"
                    + "        deps = [\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "        ],\n"
                    + "        exports = [\n"
                    + "            \":safe_mvn__export1\",\n"
                    + "            \":safe_mvn__export2\",\n"
                    + "        ],\n"
                    + "        runtime_deps = [\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"java__lib\",\n"
                    + "        actual = \":java__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_NO_URL =
            "    java_import(\n"
                    + "        name = \"java__lib__\",\n"
                    + "        jars = [\"@java__lib__//file\"],\n"
                    + "        tags = [\n"
                    + "            \"mabel_license_detected_type=Apache\",\n"
                    + "            \"mabel_license_name=Apache-2\",\n"
                    + "            \"maven_coordinates=java:lib:\",\n"
                    + "        ],\n"
                    + "        licenses = [\"notice\"],\n"
                    + "        deps = [],\n"
                    + "        exports = [],\n"
                    + "        runtime_deps = [],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"java__lib\",\n"
                    + "        actual = \":java__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_WITH_QUOTES =
            "    java_import(\n"
                    + "        name = \"java__lib__\",\n"
                    + "        jars = [\"@java__lib__//file\"],\n"
                    + "        tags = [\n"
                    + "            \"mabel_license_detected_type=Apache\",\n"
                    + "            \"mabel_license_name=Apache-2 \\\"NEW\\\" for example\",\n"
                    + "            \"maven_coordinates=java:lib:\",\n"
                    + "        ],\n"
                    + "        licenses = [\"notice\"],\n"
                    + "        deps = [],\n"
                    + "        exports = [],\n"
                    + "        runtime_deps = [],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"java__lib\",\n"
                    + "        actual = \":java__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_WITH_NEW_LINE =
            "    java_import(\n"
                    + "        name = \"java__lib__\",\n"
                    + "        jars = [\"@java__lib__//file\"],\n"
                    + "        tags = [\n"
                    + "            \"mabel_license_detected_type=Apache\",\n"
                    + "            \"mabel_license_name=Apache-2 New line\",\n"
                    + "            \"maven_coordinates=java:lib:\",\n"
                    + "        ],\n"
                    + "        licenses = [\"notice\"],\n"
                    + "        deps = [],\n"
                    + "        exports = [],\n"
                    + "        runtime_deps = [],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"java__lib\",\n"
                    + "        actual = \":java__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_UNKNOWN_LICENSE =
            "    java_import(\n"
                    + "        name = \"java__lib__\",\n"
                    + "        jars = [\"@java__lib__//file\"],\n"
                    + "        tags = [\n"
                    + "            \"mabel_license_detected_type=UNKNOWN\",\n"
                    + "            \"mabel_license_name=SomeReallyWeirdOne\",\n"
                    + "            \"mabel_license_url=http://example.com/license\",\n"
                    + "            \"maven_coordinates=java:lib:\",\n"
                    + "        ],\n"
                    + "        licenses = [],\n"
                    + "        deps = [],\n"
                    + "        exports = [],\n"
                    + "        runtime_deps = [],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"java__lib\",\n"
                    + "        actual = \":java__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String AAR_IMPORT_TEXT =
            " aar_import(\n"
                    + "     name = \"aar__lib__\",\n"
                    + "     aar = \"@aar__lib__//file\",\n"
                    + "     tags = [\"maven_coordinates=aar:lib:\"],\n"
                    + "     deps = [\n"
                    + "         \":safe_mvn__dep1\",\n"
                    + "         \":safe_mvn__dep2\",\n"
                    + "         \":safe_mvn__runtime1\",\n"
                    + "         \":safe_mvn__runtime2\",\n"
                    + "     ],\n"
                    + "     exports = [\n"
                    + "         \":safe_mvn__export1\",\n"
                    + "         \":safe_mvn__export2\",\n"
                    + "     ],\n"
                    + " )\n"
                    + " native.alias(\n"
                    + "     name = \"aar__lib\",\n"
                    + "     actual = \":aar__lib__\",\n"
                    + "     visibility = [\"//visibility:public\"],\n"
                    + " )\n";
    private static final String NATIVE_AAR_IMPORT_TEXT =
            "    aar_import(\n"
                    + "        name = \"aar__lib__\",\n"
                    + "        aar = \"@aar__lib__//file\",\n"
                    + "        tags = [\"maven_coordinates=aar:lib:\"],\n"
                    + "        deps = [\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "        exports = [\n"
                    + "            \":safe_mvn__export1\",\n"
                    + "            \":safe_mvn__export2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib\",\n"
                    + "        actual = \":aar__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_JAVA_PLUGIN_TEXT =
            "    java_import(\n"
                    + "        name = \"aar__lib__\",\n"
                    + "        jars = [\"@aar__lib__//file\"],\n"
                    + "        tags = [\"maven_coordinates=aar:lib:\"],\n"
                    + "        licenses = [],\n"
                    + "        deps = [\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "        ],\n"
                    + "        exports = [\n"
                    + "            \":safe_mvn__export1\",\n"
                    + "            \":safe_mvn__export2\",\n"
                    + "        ],\n"
                    + "        runtime_deps = [\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib\",\n"
                    + "        actual = \":aar__lib__\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n"
                    + "    java_plugin(\n"
                    + "        name = \"aar__lib_____processor_class_0\",\n"
                    + "        processor_class = \"com.example.Processor\",\n"
                    + "        generates_api = 0,\n"
                    + "        deps = [\n"
                    + "            \":aar__lib__\",\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib___processor_class_0\",\n"
                    + "        actual = \":aar__lib_____processor_class_0\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n"
                    + "    java_plugin(\n"
                    + "        name = \"aar__lib_____generates_api___processor_class_0\",\n"
                    + "        processor_class = \"com.example.Processor\",\n"
                    + "        generates_api = 1,\n"
                    + "        deps = [\n"
                    + "            \":aar__lib__\",\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib___generates_api___processor_class_0\",\n"
                    + "        actual = \":aar__lib_____generates_api___processor_class_0\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n"
                    + "    java_plugin(\n"
                    + "        name = \"aar__lib_____processor_class_1\",\n"
                    + "        processor_class = \"com.example.Processor2\",\n"
                    + "        generates_api = 0,\n"
                    + "        deps = [\n"
                    + "            \":aar__lib__\",\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib___processor_class_1\",\n"
                    + "        actual = \":aar__lib_____processor_class_1\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n"
                    + "    java_plugin(\n"
                    + "        name = \"aar__lib_____generates_api___processor_class_1\",\n"
                    + "        processor_class = \"com.example.Processor2\",\n"
                    + "        generates_api = 1,\n"
                    + "        deps = [\n"
                    + "            \":aar__lib__\",\n"
                    + "            \":safe_mvn__dep1\",\n"
                    + "            \":safe_mvn__dep2\",\n"
                    + "            \":safe_mvn__runtime1\",\n"
                    + "            \":safe_mvn__runtime2\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib___generates_api___processor_class_1\",\n"
                    + "        actual = \":aar__lib_____generates_api___processor_class_1\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n"
                    + "    java_library(\n"
                    + "        name = \"aar__lib_____processor_class_all\",\n"
                    + "        exported_plugins = [\n"
                    + "            \":aar__lib_____processor_class_0\",\n"
                    + "            \":aar__lib_____processor_class_1\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib___processor_class_all\",\n"
                    + "        actual = \":aar__lib_____processor_class_all\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n"
                    + "    java_library(\n"
                    + "        name = \"aar__lib_____generates_api___processor_class_all\",\n"
                    + "        exported_plugins = [\n"
                    + "            \":aar__lib_____generates_api___processor_class_0\",\n"
                    + "            \":aar__lib_____generates_api___processor_class_1\",\n"
                    + "        ],\n"
                    + "    )\n"
                    + "    native.alias(\n"
                    + "        name = \"aar__lib___generates_api___processor_class_all\",\n"
                    + "        actual = \":aar__lib_____generates_api___processor_class_all\",\n"
                    + "        visibility = [\"//visibility:public\"],\n"
                    + "    )\n";
    private static final String NATIVE_KOTLIN_IMPORT_TEXT =
            "    kt_jvm_import(\n" +
            "        name = \"kotlin__lib___kt_jvm_import\",\n" +
            "        jar = \"@kotlin__lib__//file\",\n" +
            "        visibility = [\"//visibility:private\"],\n" +
            "    )\n" +
            "    kt_jvm_library(\n" +
            "        name = \"kotlin__lib__\",\n" +
            "        tags = [\"maven_coordinates=kotlin:lib:\"],\n" +
            "        exports = [\n" +
            "            \":kotlin__lib___kt_jvm_import\",\n" +
            "            \":safe_mvn__export1\",\n" +
            "            \":safe_mvn__export2\",\n" +
            "        ],\n" +
            "        runtime_deps = [\n" +
            "            \":safe_mvn__runtime1\",\n" +
            "            \":safe_mvn__runtime2\",\n" +
            "        ],\n" +
            "    )\n" +
            "    native.alias(\n" +
            "        name = \"kotlin__lib\",\n" +
            "        actual = \":kotlin__lib__\",\n" +
            "        visibility = [\"//visibility:public\"],\n" +
            "    )\n";
    private static final String NATIVE_KOTLIN_ANDROID_IMPORT_TEXT =
            "    kt_jvm_import(\n" +
            "        name = \"kotlin__lib_____kt_library\",\n" +
            "        tags = [\"maven_coordinates=kotlin:lib:\"],\n" +
            "        exports = [\n" +
            "            \":safe_mvn__export1\",\n" +
            "            \":safe_mvn__export2\",\n" +
            "        ],\n" +
            "        runtime_deps = [\n" +
            "            \":safe_mvn__runtime1\",\n" +
            "            \":safe_mvn__runtime2\",\n" +
            "        ],\n" +
            "        jar = \"@kotlin__lib__//file\",\n" +
            "    )\n" +
            "    native.alias(\n" +
            "        name = \"kotlin__lib\",\n" +
            "        actual = \":kotlin__lib__\",\n" +
            "        visibility = [\"//visibility:public\"],\n" +
            "    )\n" +
            "    kt_android_library(\n" +
            "        name = \"kotlin__lib__\",\n" +
            "        deps = [\n" +
            "            \":safe_mvn__dep1\",\n" +
            "            \":safe_mvn__dep2\",\n" +
            "        ],\n" +
            "        exports = [\n" +
            "            \":kotlin__lib_____kt_library\",\n" +
            "            \":safe_mvn__export1\",\n" +
            "            \":safe_mvn__export2\",\n" +
            "        ],\n" +
            "        runtime_deps = [\n" +
            "            \":safe_mvn__runtime1\",\n" +
            "            \":safe_mvn__runtime2\",\n" +
            "        ],\n" +
            "        tags = [\"maven_coordinates=kotlin:lib:\"],\n" +
            "    )\n";

    private static String targetsToString(String indent, List<Target> targets) {
        StringBuilder builder = new StringBuilder();
        targets.forEach(
                target ->
                        builder.append(target.outputString(indent)).append(System.lineSeparator()));

        return builder.toString();
    }

    @Test
    public void testPomOnlyArtifact() {
        final String ruleText =
                targetsToString(
                        " ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                createDependency(
                                        "parent:lib",
                                        "some_url.pom",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(POM_ONLY_NATIVE_IMPORT_TEXT, ruleText);
    }

    @Test
    public void testPomOnlyArtifactHttp() {
        Assert.assertTrue(
                new TargetsBuilders.HttpTargetsBuilder(false, dep -> URI.create(""))
                        .buildTargets(
                                createDependency(
                                        "parent:lib",
                                        "some_url.pom",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT)
                        .isEmpty());
    }

    @Test
    public void testAarFormatter() {
        final String ruleText =
                targetsToString(
                        " ",
                        TargetsBuilders.AAR_IMPORT.buildTargets(
                                createDependency(
                                        "aar:lib",
                                        "some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(AAR_IMPORT_TEXT, ruleText);
    }

    @Test
    public void testHttpFormatter() {
        final String ruleText =
                targetsToString(
                        " ",
                        new TargetsBuilders.HttpTargetsBuilder(false, dep -> URI.create(""))
                                .buildTargets(
                                        createDependency(
                                                "aar:lib",
                                                "https://some_url/ss.aar",
                                                Arrays.asList("dep1", "dep2"),
                                                Arrays.asList("export1", "export2"),
                                                Arrays.asList("runtime1", "runtime2")),
                                        DependencyTools.DEFAULT));

        Assert.assertEquals(HTTP_FILE_TEXT, ruleText);
    }

    @Test
    public void testJavaImport() {
        final String ruleText =
                targetsToString(
                        " ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                createDependency(
                                        "java:lib",
                                        "https://some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(JAVA_IMPORT_TEXT, ruleText);
    }

    @Test
    public void testJavaImportWithSources() {
        final String ruleText =
                targetsToString(
                        " ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                createDependency(
                                        "java:lib",
                                        "https://some_url",
                                        "https://some_url-sources",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(JAVA_IMPORT_TEXT_WITH_SOURCES, ruleText);
    }

    @Test
    public void testNativeAarFormatter() {
        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.AAR_IMPORT.buildTargets(
                                createDependency(
                                        "aar:lib",
                                        "some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_AAR_IMPORT_TEXT, ruleText);
    }

    @Test
    public void testNativeJavaImport() {
        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                createDependency(
                                        "java:lib",
                                        "https://some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_IMPORT_TEXT, ruleText);
    }

    @Test
    public void testNativeJavaImportWithLicenses() {
        Dependency dependency =
                Dependency.builder(
                                createDependency(
                                        "java:lib",
                                        "https://some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")))
                        .licenses(Collections.singleton(License.create("Apache-2", "http://some.com/url/to/license")))
                        .build();

        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                dependency, DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT, ruleText);
    }

    @Test
    public void testNativeJavaImportWithLicensesNoUrl() {
        Dependency dependency =
                Dependency.builder(
                        createDependency(
                                "java:lib",
                                "https://some_url",
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList()))
                        .licenses(Collections.singleton(License.create("Apache-2", "")))
                        .build();

        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                dependency, DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_NO_URL, ruleText);
    }

    @Test
    public void testNativeJavaImportWithLicensesWithQuotes() {
        Dependency dependency =
                Dependency.builder(
                        createDependency(
                                "java:lib",
                                "https://some_url",
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList()))
                        .licenses(Collections.singleton(License.create("Apache-2 \"NEW\" for example", "")))
                        .build();

        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                dependency, DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_WITH_QUOTES, ruleText);
    }

    @Test
    public void testNativeJavaImportWithLicensesWithNewLine() {
        Dependency dependency =
                Dependency.builder(
                        createDependency(
                                "java:lib",
                                "https://some_url",
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList()))
                        .licenses(Collections.singleton(License.create("Apache-2\nNew line", "")))
                        .build();

        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                dependency, DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_WITH_NEW_LINE, ruleText);
    }

    @Test
    public void testNativeJavaImportWithUnknownLicenses() {
        Dependency dependency =
                Dependency.builder(
                        createDependency(
                                "java:lib",
                                "https://some_url",
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList()))
                        .licenses(Collections.singleton(License.create("SomeReallyWeirdOne", "http://example.com/license")))
                        .build();

        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.JAVA_IMPORT.buildTargets(
                                dependency, DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_IMPORT_WITH_LICENSE_TEXT_UNKNOWN_LICENSE, ruleText);
    }

    @Test
    public void testNativeJavaPlugin() {
        final String ruleText =
                targetsToString(
                        "    ",
                        new TargetsBuilders.JavaPluginFormatter(
                                        Arrays.asList(
                                                "com.example.Processor", "com.example.Processor2"))
                                .buildTargets(
                                        createDependency(
                                                "aar:lib",
                                                "https://some_url",
                                                Arrays.asList("dep1", "dep2"),
                                                Arrays.asList("export1", "export2"),
                                                Arrays.asList("runtime1", "runtime2")),
                                        DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_JAVA_PLUGIN_TEXT, ruleText);
    }

    @Test
    public void testNativeKotlinImport() {
        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.KOTLIN_IMPORT.buildTargets(
                                createDependency(
                                        "kotlin:lib",
                                        "https://some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_KOTLIN_IMPORT_TEXT, ruleText);
    }

    @Test
    @Ignore("until we figure out kotlin-android import")
    public void testNativeKotlinAndroidImport() {
        final String ruleText =
                targetsToString(
                        "    ",
                        TargetsBuilders.KOTLIN_ANDROID_IMPORT.buildTargets(
                                createDependency(
                                        "kotlin:lib",
                                        "https://some_url",
                                        Arrays.asList("dep1", "dep2"),
                                        Arrays.asList("export1", "export2"),
                                        Arrays.asList("runtime1", "runtime2")),
                                DependencyTools.DEFAULT));

        Assert.assertEquals(NATIVE_KOTLIN_ANDROID_IMPORT_TEXT, ruleText);
    }
}
