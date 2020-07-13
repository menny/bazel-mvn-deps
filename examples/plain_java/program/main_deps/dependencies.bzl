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

    # from com.google.guava:guava:20.0
    http_file(
        name = "com_google_guava__guava__20_0",
        urls = ["https://repo1.maven.org/maven2/com/google/guava/guava/20.0/guava-20.0.jar"],
        downloaded_file_path = "guava-20.0.jar",
        sha256 = "36a666e3b71ae7f0f0dca23654b67e086e6c93d192f60ba5dfd5519db6c288c8",
    )

def _no_op_missing_aar_impl(name, **kwargs):
    """
    This is a help macro for missing concrete rule implementation.

    This will be used in cases when some dependencies require aar_import rule implementation.

    Args:
        name: A unique name for this target.
        **kwargs: Anything else. Not used.
    """

    fail(
        "Unable to create target {} since it is a aar_import which was not provide. Add argument aar_import when calling generate_transitive_dependency_targets."
            .format(name),
    )

def _no_op_missing_kt_jvm_impl(name, **kwargs):
    """
    This is a help macro for missing concrete rule implementation.

    This will be used in cases when some dependencies require Kotlin rule implementation.

    Args:
        name: A unique name for this target.
        **kwargs: Anything else. Not used.
    """

    fail(
        "Unable to create target {} since it is a kt_jvm_import which was not provide. Add argument kt_jvm_import when calling generate_transitive_dependency_targets."
            .format(name),
    )

def _no_op_missing_kt_android_impl(name, **kwargs):
    """
    This is a help macro for missing concrete rule implementation.

    This will be used in cases when some dependencies require Kotlin rule implementation.

    Args:
        name: A unique name for this target.
        **kwargs: Anything else. Not used.
    """

    fail(
        "Unable to create target {} since it is a kt_android_library which was not provide. Add argument kt_android_library when calling generate_transitive_dependency_targets."
            .format(name),
    )

def generate_transitive_dependency_targets(
        name = "generate_transitive_dependency_targets",
        java_library = native.java_library,
        java_plugin = native.java_plugin,
        java_import = native.java_import,
        aar_import = _no_op_missing_aar_impl,
        kt_jvm_import = _no_op_missing_kt_jvm_impl,
        kt_android_library = _no_op_missing_kt_android_impl):
    """
    Macro to set up the transitive rules.

    You can provide your own implementation of java_import, aar_import, etc. This can be used
    in cases where you need to shade (or jar_jar or jetify) your jars.

    Args:
        name: a unique name for this macro. Not needed to specify.
        java_library: rule implementation for java_library. Defaults to native.java_library.
        java_plugin: rule implementation for java_plugin. Defaults to native.java_plugin.
        java_import: rule implementation for java_import. Defaults to native.java_import.
        aar_import: rule implementation for aar_import. Required only if you have Android dependencies.
        kt_jvm_import: rule implementation for kt_jvm_import. Required only if you have Kotlin dependencies.
        kt_android_library: rule implementation for kt_android_library. Required only if you have Android-Kotlin dependencies.
    """

    # from com.google.guava:guava:20.0
    native.alias(
        name = "com_google_guava__guava",
        actual = ":com_google_guava__guava__20_0",
        visibility = ["//visibility:public"],
    )

    # from com.google.guava:guava:20.0
    java_import(
        name = "com_google_guava__guava__20_0",
        jars = ["@com_google_guava__guava__20_0//file"],
        tags = ["maven_coordinates=com.google.guava:guava:20.0"],
        licenses = ["notice"],
        deps = [],
        exports = [],
        runtime_deps = [],
    )
