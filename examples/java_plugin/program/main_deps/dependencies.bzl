"""
Required macros for creating repository-rules and third-party Maven dependencies.

Auto-generated by https://github.com/menny/mabel
"""

# Loading a drop-in replacement for native.http_file
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_file")

def generate_workspace_rules(name = "generate_workspace_rules"):
    """
    Repository rules macro to be run in the WORKSPACE file.

    Args:
        name: A unique name for this target. No need to specify.
    """

    # from com.google.auto.value:auto-value-annotations:1.6.3
    http_file(
        name = "apt___com_google_auto_value__auto_value_annotations__1_6_3",
        urls = ["https://repo1.maven.org/maven2/com/google/auto/value/auto-value-annotations/1.6.3/auto-value-annotations-1.6.3.jar"],
        downloaded_file_path = "auto-value-annotations-1.6.3.jar",
        sha256 = "0e951fee8c31f60270bc46553a8586001b7b93dbb12aec06373aa99a150392c0",
    )

    # from com.google.auto.value:auto-value:1.6.3
    http_file(
        name = "apt___com_google_auto_value__auto_value__1_6_3",
        urls = ["https://repo1.maven.org/maven2/com/google/auto/value/auto-value/1.6.3/auto-value-1.6.3.jar"],
        downloaded_file_path = "auto-value-1.6.3.jar",
        sha256 = "0aa5463f85a210aacecbebaa45e61b79ec17e903218d79f8db9e1efde06b3c7f",
    )

    # from com.google.code.findbugs:jsr305:1.3.9
    http_file(
        name = "apt___com_google_code_findbugs__jsr305__1_3_9",
        urls = ["https://repo1.maven.org/maven2/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar"],
        downloaded_file_path = "jsr305-1.3.9.jar",
        sha256 = "905721a0eea90a81534abb7ee6ef4ea2e5e645fa1def0a5cd88402df1b46c9ed",
    )

    # from com.google.dagger:dagger-compiler:2.19
    http_file(
        name = "apt___com_google_dagger__dagger_compiler__2_19",
        urls = ["https://repo1.maven.org/maven2/com/google/dagger/dagger-compiler/2.19/dagger-compiler-2.19.jar"],
        downloaded_file_path = "dagger-compiler-2.19.jar",
        sha256 = "27a4b202a2de908182edb261f8c0a264e08e5e4733d7514bc7fbf0d31da5c0fc",
    )

    # from com.google.dagger:dagger-producers:2.19
    http_file(
        name = "apt___com_google_dagger__dagger_producers__2_19",
        urls = ["https://repo1.maven.org/maven2/com/google/dagger/dagger-producers/2.19/dagger-producers-2.19.jar"],
        downloaded_file_path = "dagger-producers-2.19.jar",
        sha256 = "a17663abe0fc38b676026950907d4c5f5e2bf338375415861eaff6e3bdb0b768",
    )

    # from com.google.dagger:dagger-spi:2.19
    http_file(
        name = "apt___com_google_dagger__dagger_spi__2_19",
        urls = ["https://repo1.maven.org/maven2/com/google/dagger/dagger-spi/2.19/dagger-spi-2.19.jar"],
        downloaded_file_path = "dagger-spi-2.19.jar",
        sha256 = "e7a6379d82c841f6aac2866948ad1eed716528707814602842a8d844ce04e2e1",
    )

    # from com.google.dagger:dagger:2.19
    http_file(
        name = "apt___com_google_dagger__dagger__2_19",
        urls = ["https://repo1.maven.org/maven2/com/google/dagger/dagger/2.19/dagger-2.19.jar"],
        downloaded_file_path = "dagger-2.19.jar",
        sha256 = "514b6f1e0727c6572e1d65cb27e4ae668b7aeaeb93a29515182965265b609939",
    )

    # from com.google.errorprone:error_prone_annotations:2.1.3
    http_file(
        name = "apt___com_google_errorprone__error_prone_annotations__2_1_3",
        urls = ["https://repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.1.3/error_prone_annotations-2.1.3.jar"],
        downloaded_file_path = "error_prone_annotations-2.1.3.jar",
        sha256 = "03d0329547c13da9e17c634d1049ea2ead093925e290567e1a364fd6b1fc7ff8",
    )

    # from com.google.errorprone:javac-shaded:9-dev-r4023-3
    http_file(
        name = "apt___com_google_errorprone__javac_shaded__9_dev_r4023_3",
        urls = ["https://repo1.maven.org/maven2/com/google/errorprone/javac-shaded/9-dev-r4023-3/javac-shaded-9-dev-r4023-3.jar"],
        downloaded_file_path = "javac-shaded-9-dev-r4023-3.jar",
        sha256 = "65bfccf60986c47fbc17c9ebab0be626afc41741e0a6ec7109e0768817a36f30",
    )

    # from com.google.googlejavaformat:google-java-format:1.5
    http_file(
        name = "apt___com_google_googlejavaformat__google_java_format__1_5",
        urls = ["https://repo1.maven.org/maven2/com/google/googlejavaformat/google-java-format/1.5/google-java-format-1.5.jar"],
        downloaded_file_path = "google-java-format-1.5.jar",
        sha256 = "aa19ad7850fb85178aa22f2fddb163b84d6ce4d0035872f30d4408195ca1144e",
    )

    # from com.google.guava:guava:25.0-jre
    http_file(
        name = "apt___com_google_guava__guava__25_0_jre",
        urls = ["https://repo1.maven.org/maven2/com/google/guava/guava/25.0-jre/guava-25.0-jre.jar"],
        downloaded_file_path = "guava-25.0-jre.jar",
        sha256 = "3fd4341776428c7e0e5c18a7c10de129475b69ab9d30aeafbb5c277bb6074fa9",
    )

    # from com.google.j2objc:j2objc-annotations:1.1
    http_file(
        name = "apt___com_google_j2objc__j2objc_annotations__1_1",
        urls = ["https://repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar"],
        downloaded_file_path = "j2objc-annotations-1.1.jar",
        sha256 = "2994a7eb78f2710bd3d3bfb639b2c94e219cedac0d4d084d516e78c16dddecf6",
    )

    # from com.squareup:javapoet:1.11.1
    http_file(
        name = "apt___com_squareup__javapoet__1_11_1",
        urls = ["https://repo1.maven.org/maven2/com/squareup/javapoet/1.11.1/javapoet-1.11.1.jar"],
        downloaded_file_path = "javapoet-1.11.1.jar",
        sha256 = "9cbf2107be499ec6e95afd36b58e3ca122a24166cdd375732e51267d64058e90",
    )

    # from javax.annotation:jsr250-api:1.0
    http_file(
        name = "apt___javax_annotation__jsr250_api__1_0",
        urls = ["https://repo1.maven.org/maven2/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.jar"],
        downloaded_file_path = "jsr250-api-1.0.jar",
        sha256 = "a1a922d0d9b6d183ed3800dfac01d1e1eb159f0e8c6f94736931c1def54a941f",
    )

    # from javax.inject:javax.inject:1
    http_file(
        name = "apt___javax_inject__javax_inject__1",
        urls = ["https://repo1.maven.org/maven2/javax/inject/javax.inject/1/javax.inject-1.jar"],
        downloaded_file_path = "javax.inject-1.jar",
        sha256 = "91c77044a50c481636c32d916fd89c9118a72195390452c81065080f957de7ff",
    )

    # from org.checkerframework:checker-compat-qual:2.5.3
    http_file(
        name = "apt___org_checkerframework__checker_compat_qual__2_5_3",
        urls = ["https://repo1.maven.org/maven2/org/checkerframework/checker-compat-qual/2.5.3/checker-compat-qual-2.5.3.jar"],
        downloaded_file_path = "checker-compat-qual-2.5.3.jar",
        sha256 = "d76b9afea61c7c082908023f0cbc1427fab9abd2df915c8b8a3e7a509bccbc6d",
    )

    # from org.codehaus.mojo:animal-sniffer-annotations:1.14
    http_file(
        name = "apt___org_codehaus_mojo__animal_sniffer_annotations__1_14",
        urls = ["https://repo1.maven.org/maven2/org/codehaus/mojo/animal-sniffer-annotations/1.14/animal-sniffer-annotations-1.14.jar"],
        downloaded_file_path = "animal-sniffer-annotations-1.14.jar",
        sha256 = "2068320bd6bad744c3673ab048f67e30bef8f518996fa380033556600669905d",
    )

def kotlin_jar_support(name, deps, exports, runtime_deps, jar, tags, java_import_impl, kt_jvm_import = None, visibility = ["//visibility:public"]):
    """
    This is a help macro to handle Kotlin rules.

    Transitive rules macro to be run in the BUILD.bazel file.
    If you use kt_* rules, you MUST provide the correct rule implementation when call this macro, if you decide
    not to provide those implementations we'll try to use java_* rules.

    Args:
        name: A unique name for this target.
        deps: The list of other libraries to be linked in to the target.
        exports: Targets to make available to users of this rule.
        runtime_deps: Libraries to make available to the final binary or test at runtime only.
        jar: The JAR file provided to Java targets that depend on this target.
        java_import_impl: rule implementation for java_import.
        kt_jvm_import: rule implementation for kt_jvm_import. Can be None.
        visibility: Target visibility to pass to actual targets.
        tags: List of arbitrary text tags. Tags may be any valid string.
    """

    #In case the developer did not provide a kt_* impl, we'll try to use java_*, should work
    if kt_jvm_import == None:
        java_import_impl(
            name = name,
            jars = [jar],
            deps = deps,
            exports = exports,
            runtime_deps = runtime_deps,
            visibility = visibility,
            tags = tags,
        )
    else:
        kt_jvm_import(
            name = name,
            jar = jar,
            exports = exports,
            runtime_deps = runtime_deps,
            visibility = visibility,
            tags = tags,
        )

def generate_transitive_dependency_targets(name = "generate_transitive_dependency_targets", java_library_impl = native.java_library, java_plugin_impl = native.java_plugin, java_import_impl = native.java_import, aar_import_impl = native.aar_import, kt_jvm_import = None):
    """
    Macro to set up the transitive rules.

    You can provide your own implementation of java_import and aar_import. This can be used
    in cases where you need to shade (or jar_jar or jetify) your jars.

    Args:
        name: a unique name for this macro. Not needed to specify.
        java_library_impl: rule implementation for java_library.
        java_plugin_impl: rule implementation for java_plugin.
        java_import_impl: rule implementation for java_import.
        aar_import_impl: rule implementation for aar_import.
        kt_jvm_import: rule implementation for kt_jvm_import.
    """

    # from com.google.auto.value:auto-value-annotations:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value_annotations",
        actual = ":apt___com_google_auto_value__auto_value_annotations__1_6_3",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value-annotations:1.6.3
    java_import_impl(
        name = "apt___com_google_auto_value__auto_value_annotations__1_6_3",
        jars = ["@apt___com_google_auto_value__auto_value_annotations__1_6_3//file"],
        tags = ["maven_coordinates=com.google.auto.value:auto-value-annotations:1.6.3"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_import_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3",
        jars = ["@apt___com_google_auto_value__auto_value__1_6_3//file"],
        tags = ["maven_coordinates=com.google.auto.value:auto-value:1.6.3"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_0",
        processor_class = "com.google.auto.value.extension.memoized.processor.MemoizedValidator",
        generates_api = 1,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_1",
        processor_class = "com.google.auto.value.processor.AutoAnnotationProcessor",
        generates_api = 1,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_2",
        processor_class = "com.google.auto.value.processor.AutoOneOfProcessor",
        generates_api = 1,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_3",
        processor_class = "com.google.auto.value.processor.AutoValueBuilderProcessor",
        generates_api = 1,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_4",
        processor_class = "com.google.auto.value.processor.AutoValueProcessor",
        generates_api = 1,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_library_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_all",
        exported_plugins = [
            ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_0",
            ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_1",
            ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_2",
            ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_3",
            ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_4",
        ],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___processor_class_0",
        processor_class = "com.google.auto.value.extension.memoized.processor.MemoizedValidator",
        generates_api = 0,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___processor_class_1",
        processor_class = "com.google.auto.value.processor.AutoAnnotationProcessor",
        generates_api = 0,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___processor_class_2",
        processor_class = "com.google.auto.value.processor.AutoOneOfProcessor",
        generates_api = 0,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___processor_class_3",
        processor_class = "com.google.auto.value.processor.AutoValueBuilderProcessor",
        generates_api = 0,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_plugin_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___processor_class_4",
        processor_class = "com.google.auto.value.processor.AutoValueProcessor",
        generates_api = 0,
        deps = [":apt___com_google_auto_value__auto_value__1_6_3"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    java_library_impl(
        name = "apt___com_google_auto_value__auto_value__1_6_3___processor_class_all",
        exported_plugins = [
            ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_0",
            ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_1",
            ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_2",
            ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_3",
            ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_4",
        ],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___generates_api___processor_class_0",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_0",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___generates_api___processor_class_1",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_1",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___generates_api___processor_class_2",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_2",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___generates_api___processor_class_3",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_3",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___generates_api___processor_class_4",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_4",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___generates_api___processor_class_all",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___generates_api___processor_class_all",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___processor_class_0",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_0",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___processor_class_1",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_1",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___processor_class_2",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_2",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___processor_class_3",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_3",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___processor_class_4",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_4",
        visibility = ["//visibility:public"],
    )

    # from com.google.auto.value:auto-value:1.6.3
    native.alias(
        name = "apt___com_google_auto_value__auto_value___processor_class_all",
        actual = ":apt___com_google_auto_value__auto_value__1_6_3___processor_class_all",
        visibility = ["//visibility:public"],
    )

    # from com.google.code.findbugs:jsr305:1.3.9
    native.alias(
        name = "apt___com_google_code_findbugs__jsr305",
        actual = ":apt___com_google_code_findbugs__jsr305__1_3_9",
        visibility = ["//visibility:public"],
    )

    # from com.google.code.findbugs:jsr305:1.3.9
    java_import_impl(
        name = "apt___com_google_code_findbugs__jsr305__1_3_9",
        jars = ["@apt___com_google_code_findbugs__jsr305__1_3_9//file"],
        tags = ["maven_coordinates=com.google.code.findbugs:jsr305:1.3.9"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from com.google.dagger:dagger-compiler:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_compiler",
        actual = ":apt___com_google_dagger__dagger_compiler__2_19",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-compiler:2.19
    java_import_impl(
        name = "apt___com_google_dagger__dagger_compiler__2_19",
        jars = ["@apt___com_google_dagger__dagger_compiler__2_19//file"],
        tags = ["maven_coordinates=com.google.dagger:dagger-compiler:2.19"],
        licenses = ["notice"],
        deps = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_dagger__dagger_producers",
            ":apt___com_google_dagger__dagger_spi",
            ":apt___com_google_googlejavaformat__google_java_format",
            ":apt___com_google_guava__guava",
            ":apt___com_squareup__javapoet",
            ":apt___javax_annotation__jsr250_api",
            ":apt___javax_inject__javax_inject",
        ],
        exports = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_dagger__dagger_producers",
            ":apt___com_google_dagger__dagger_spi",
            ":apt___com_google_googlejavaformat__google_java_format",
            ":apt___com_google_guava__guava",
            ":apt___com_squareup__javapoet",
            ":apt___javax_annotation__jsr250_api",
            ":apt___javax_inject__javax_inject",
        ],
        runtime_deps = [],
    )

    # from com.google.dagger:dagger-compiler:2.19
    java_plugin_impl(
        name = "apt___com_google_dagger__dagger_compiler__2_19___generates_api___processor_class_0",
        processor_class = "dagger.internal.codegen.ComponentProcessor",
        generates_api = 1,
        deps = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_dagger__dagger_compiler__2_19",
            ":apt___com_google_dagger__dagger_producers",
            ":apt___com_google_dagger__dagger_spi",
            ":apt___com_google_googlejavaformat__google_java_format",
            ":apt___com_google_guava__guava",
            ":apt___com_squareup__javapoet",
            ":apt___javax_annotation__jsr250_api",
            ":apt___javax_inject__javax_inject",
        ],
    )

    # from com.google.dagger:dagger-compiler:2.19
    java_library_impl(
        name = "apt___com_google_dagger__dagger_compiler__2_19___generates_api___processor_class_all",
        exported_plugins = [":apt___com_google_dagger__dagger_compiler__2_19___generates_api___processor_class_0"],
    )

    # from com.google.dagger:dagger-compiler:2.19
    java_plugin_impl(
        name = "apt___com_google_dagger__dagger_compiler__2_19___processor_class_0",
        processor_class = "dagger.internal.codegen.ComponentProcessor",
        generates_api = 0,
        deps = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_dagger__dagger_compiler__2_19",
            ":apt___com_google_dagger__dagger_producers",
            ":apt___com_google_dagger__dagger_spi",
            ":apt___com_google_googlejavaformat__google_java_format",
            ":apt___com_google_guava__guava",
            ":apt___com_squareup__javapoet",
            ":apt___javax_annotation__jsr250_api",
            ":apt___javax_inject__javax_inject",
        ],
    )

    # from com.google.dagger:dagger-compiler:2.19
    java_library_impl(
        name = "apt___com_google_dagger__dagger_compiler__2_19___processor_class_all",
        exported_plugins = [":apt___com_google_dagger__dagger_compiler__2_19___processor_class_0"],
    )

    # from com.google.dagger:dagger-compiler:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_compiler___generates_api___processor_class_0",
        actual = ":apt___com_google_dagger__dagger_compiler__2_19___generates_api___processor_class_0",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-compiler:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_compiler___generates_api___processor_class_all",
        actual = ":apt___com_google_dagger__dagger_compiler__2_19___generates_api___processor_class_all",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-compiler:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_compiler___processor_class_0",
        actual = ":apt___com_google_dagger__dagger_compiler__2_19___processor_class_0",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-compiler:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_compiler___processor_class_all",
        actual = ":apt___com_google_dagger__dagger_compiler__2_19___processor_class_all",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-producers:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_producers",
        actual = ":apt___com_google_dagger__dagger_producers__2_19",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-producers:2.19
    java_import_impl(
        name = "apt___com_google_dagger__dagger_producers__2_19",
        jars = ["@apt___com_google_dagger__dagger_producers__2_19//file"],
        tags = ["maven_coordinates=com.google.dagger:dagger-producers:2.19"],
        licenses = ["notice"],
        deps = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_guava__guava",
            ":apt___javax_inject__javax_inject",
            ":apt___org_checkerframework__checker_compat_qual",
        ],
        exports = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_guava__guava",
            ":apt___javax_inject__javax_inject",
            ":apt___org_checkerframework__checker_compat_qual",
        ],
        runtime_deps = [],
    )

    # from com.google.dagger:dagger-spi:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger_spi",
        actual = ":apt___com_google_dagger__dagger_spi__2_19",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger-spi:2.19
    java_import_impl(
        name = "apt___com_google_dagger__dagger_spi__2_19",
        jars = ["@apt___com_google_dagger__dagger_spi__2_19//file"],
        tags = ["maven_coordinates=com.google.dagger:dagger-spi:2.19"],
        licenses = ["notice"],
        deps = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_dagger__dagger_producers",
            ":apt___com_google_guava__guava",
            ":apt___javax_inject__javax_inject",
        ],
        exports = [
            ":apt___com_google_dagger__dagger",
            ":apt___com_google_dagger__dagger_producers",
            ":apt___com_google_guava__guava",
            ":apt___javax_inject__javax_inject",
        ],
        runtime_deps = [],
    )

    # from com.google.dagger:dagger:2.19
    native.alias(
        name = "apt___com_google_dagger__dagger",
        actual = ":apt___com_google_dagger__dagger__2_19",
        visibility = ["//visibility:public"],
    )

    # from com.google.dagger:dagger:2.19
    java_import_impl(
        name = "apt___com_google_dagger__dagger__2_19",
        jars = ["@apt___com_google_dagger__dagger__2_19//file"],
        tags = ["maven_coordinates=com.google.dagger:dagger:2.19"],
        licenses = ["notice"],
        deps = [":apt___javax_inject__javax_inject"],
        exports = [":apt___javax_inject__javax_inject"],
        runtime_deps = [],
    )

    # from com.google.errorprone:error_prone_annotations:2.1.3
    native.alias(
        name = "apt___com_google_errorprone__error_prone_annotations",
        actual = ":apt___com_google_errorprone__error_prone_annotations__2_1_3",
        visibility = ["//visibility:public"],
    )

    # from com.google.errorprone:error_prone_annotations:2.1.3
    java_import_impl(
        name = "apt___com_google_errorprone__error_prone_annotations__2_1_3",
        jars = ["@apt___com_google_errorprone__error_prone_annotations__2_1_3//file"],
        tags = ["maven_coordinates=com.google.errorprone:error_prone_annotations:2.1.3"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from com.google.errorprone:javac-shaded:9-dev-r4023-3
    native.alias(
        name = "apt___com_google_errorprone__javac_shaded",
        actual = ":apt___com_google_errorprone__javac_shaded__9_dev_r4023_3",
        visibility = ["//visibility:public"],
    )

    # from com.google.errorprone:javac-shaded:9-dev-r4023-3
    java_import_impl(
        name = "apt___com_google_errorprone__javac_shaded__9_dev_r4023_3",
        jars = ["@apt___com_google_errorprone__javac_shaded__9_dev_r4023_3//file"],
        tags = ["maven_coordinates=com.google.errorprone:javac-shaded:9-dev-r4023-3"],
        licenses = ["restricted"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from com.google.googlejavaformat:google-java-format:1.5
    native.alias(
        name = "apt___com_google_googlejavaformat__google_java_format",
        actual = ":apt___com_google_googlejavaformat__google_java_format__1_5",
        visibility = ["//visibility:public"],
    )

    # from com.google.googlejavaformat:google-java-format:1.5
    java_import_impl(
        name = "apt___com_google_googlejavaformat__google_java_format__1_5",
        jars = ["@apt___com_google_googlejavaformat__google_java_format__1_5//file"],
        tags = ["maven_coordinates=com.google.googlejavaformat:google-java-format:1.5"],
        licenses = ["notice"],
        deps = [
            ":apt___com_google_errorprone__javac_shaded",
            ":apt___com_google_guava__guava",
        ],
        exports = [
            ":apt___com_google_errorprone__javac_shaded",
            ":apt___com_google_guava__guava",
        ],
        runtime_deps = [],
    )

    # from com.google.guava:guava:25.0-jre
    native.alias(
        name = "apt___com_google_guava__guava",
        actual = ":apt___com_google_guava__guava__25_0_jre",
        visibility = ["//visibility:public"],
    )

    # from com.google.guava:guava:25.0-jre
    java_import_impl(
        name = "apt___com_google_guava__guava__25_0_jre",
        jars = ["@apt___com_google_guava__guava__25_0_jre//file"],
        tags = ["maven_coordinates=com.google.guava:guava:25.0-jre"],
        licenses = ["notice"],
        deps = [
            ":apt___com_google_code_findbugs__jsr305",
            ":apt___com_google_errorprone__error_prone_annotations",
            ":apt___com_google_j2objc__j2objc_annotations",
            ":apt___org_checkerframework__checker_compat_qual",
            ":apt___org_codehaus_mojo__animal_sniffer_annotations",
        ],
        exports = [
            ":apt___com_google_code_findbugs__jsr305",
            ":apt___com_google_errorprone__error_prone_annotations",
            ":apt___com_google_j2objc__j2objc_annotations",
            ":apt___org_checkerframework__checker_compat_qual",
            ":apt___org_codehaus_mojo__animal_sniffer_annotations",
        ],
        runtime_deps = [],
    )

    # from com.google.j2objc:j2objc-annotations:1.1
    native.alias(
        name = "apt___com_google_j2objc__j2objc_annotations",
        actual = ":apt___com_google_j2objc__j2objc_annotations__1_1",
        visibility = ["//visibility:public"],
    )

    # from com.google.j2objc:j2objc-annotations:1.1
    java_import_impl(
        name = "apt___com_google_j2objc__j2objc_annotations__1_1",
        jars = ["@apt___com_google_j2objc__j2objc_annotations__1_1//file"],
        tags = ["maven_coordinates=com.google.j2objc:j2objc-annotations:1.1"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from com.squareup:javapoet:1.11.1
    native.alias(
        name = "apt___com_squareup__javapoet",
        actual = ":apt___com_squareup__javapoet__1_11_1",
        visibility = ["//visibility:public"],
    )

    # from com.squareup:javapoet:1.11.1
    java_import_impl(
        name = "apt___com_squareup__javapoet__1_11_1",
        jars = ["@apt___com_squareup__javapoet__1_11_1//file"],
        tags = ["maven_coordinates=com.squareup:javapoet:1.11.1"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from javax.annotation:jsr250-api:1.0
    native.alias(
        name = "apt___javax_annotation__jsr250_api",
        actual = ":apt___javax_annotation__jsr250_api__1_0",
        visibility = ["//visibility:public"],
    )

    # from javax.annotation:jsr250-api:1.0
    java_import_impl(
        name = "apt___javax_annotation__jsr250_api__1_0",
        jars = ["@apt___javax_annotation__jsr250_api__1_0//file"],
        tags = ["maven_coordinates=javax.annotation:jsr250-api:1.0"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from javax.inject:javax.inject:1
    native.alias(
        name = "apt___javax_inject__javax_inject",
        actual = ":apt___javax_inject__javax_inject__1",
        visibility = ["//visibility:public"],
    )

    # from javax.inject:javax.inject:1
    java_import_impl(
        name = "apt___javax_inject__javax_inject__1",
        jars = ["@apt___javax_inject__javax_inject__1//file"],
        tags = ["maven_coordinates=javax.inject:javax.inject:1"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from org.checkerframework:checker-compat-qual:2.5.3
    native.alias(
        name = "apt___org_checkerframework__checker_compat_qual",
        actual = ":apt___org_checkerframework__checker_compat_qual__2_5_3",
        visibility = ["//visibility:public"],
    )

    # from org.checkerframework:checker-compat-qual:2.5.3
    java_import_impl(
        name = "apt___org_checkerframework__checker_compat_qual__2_5_3",
        jars = ["@apt___org_checkerframework__checker_compat_qual__2_5_3//file"],
        tags = ["maven_coordinates=org.checkerframework:checker-compat-qual:2.5.3"],
        licenses = [
            "notice",
            "restricted",
        ],
        deps = [],
        exports = [],
        runtime_deps = [],
    )

    # from org.codehaus.mojo:animal-sniffer-annotations:1.14
    native.alias(
        name = "apt___org_codehaus_mojo__animal_sniffer_annotations",
        actual = ":apt___org_codehaus_mojo__animal_sniffer_annotations__1_14",
        visibility = ["//visibility:public"],
    )

    # from org.codehaus.mojo:animal-sniffer-annotations:1.14
    java_import_impl(
        name = "apt___org_codehaus_mojo__animal_sniffer_annotations__1_14",
        jars = ["@apt___org_codehaus_mojo__animal_sniffer_annotations__1_14//file"],
        tags = ["maven_coordinates=org.codehaus.mojo:animal-sniffer-annotations:1.14"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )
