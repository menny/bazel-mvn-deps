
script_template = """
OUTPUT_FILENAME=${{BUILD_WORKING_DIRECTORY}}/{output_deps_file_path}
echo "BUILD_WORKING_DIRECTORY: ${{BUILD_WORKING_DIRECTORY}}"
echo "output_deps_file_path: {output_deps_file_path}"
echo "output_target_build_files_base_path: {output_target_build_files_base_path}"
echo "package_path: {package_path}"
java -jar {resolver} {repositories_list} {artifacts_list} {exclude_artifacts_list} \
    --rule_prefix={rule_prefix} \
    --macro_prefix={macro_prefix} \
    --output_macro_file_path=${{OUTPUT_FILENAME}} \
    --output_target_build_files_base_path=${{BUILD_WORKING_DIRECTORY}}/{output_target_build_files_base_path} \
    --package_path={package_path}
echo "Stored resolved dependencies graph (rules) at ${{OUTPUT_FILENAME}}"
"""

def _impl(ctx):
    output_filename = '{}/{}_dependencies.bzl'.format(ctx.label.package, ctx.attr.macro_prefix)
    output_target_build_files_base_path = '{}/{}/'.format(ctx.label.package, ctx.attr.rule_prefix)
    package_path = ctx.label.package
    script = ctx.actions.declare_file('%s-generate-deps.sh' % ctx.label.name)
    script_content = script_template.format(
        resolver = ctx.executable._resolver.short_path,
        repositories_list = " ".join(['--repository={}'.format(repository) for repository in ctx.attr.repositories]),
        artifacts_list = " ".join(['--artifact={}'.format(artifact) for artifact in ctx.attr.maven_deps]),
        exclude_artifacts_list = " ".join(['--blacklist={}'.format(exclude_artifact_list) for exclude_artifact_list in ctx.attr.maven_exclude_deps]),
        output_deps_file_path = output_filename,
        rule_prefix = ctx.attr.rule_prefix,
        macro_prefix = ctx.attr.macro_prefix,
        output_target_build_files_base_path = output_target_build_files_base_path,
        package_path = package_path,
        )

    ctx.actions.write(script, script_content, is_executable=True)

    return [DefaultInfo(executable=script, runfiles=ctx.runfiles(files = [ctx.executable._resolver]))]

deps_workspace_generator_rule = rule(implementation=_impl,
    doc = """Generates a bzl file with repository-rules and targets which describes a Maven dependecy graph based on
    the provided `maven_deps` values. The result will be stored in a file (as defined in `output_deps_file_path`).
    The generated file will contain two macros:
    * `generate_XXX_workspace_rules` - should be load and run in the `WORKSPACE` file. This will create repository-rules for all the remote Maven artifacts (jars).
    * `generate_XXX_transitive_dependency_rules` = should be loaded in the relavent `BUILD.bazel` file. This will create targets for each of the requested `maven_deps`.
    """,
    executable=True,
    attrs = {
        "maven_deps": attr.string_list(mandatory=True, allow_empty=False, doc = "List of Maven dependencies in the format of `group-id:artifact-id:version`."),
        "maven_exclude_deps": attr.string_list(allow_empty=True, default = [], doc = "List of Maven dependencies which should not be resolved. You can omit the `version` or both `artifact-id:version`."),
        "repositories": attr.string_list(allow_empty=False, default = [
            'https://maven.google.com/',
            'https://repo1.maven.org/maven2/',
            'https://jcenter.bintray.com/',
        ], doc = "List of URLs that point to Maven servers. Defaut is Google, Maven-Central and jcenter."),
        "rule_prefix": attr.string(mandatory=True, doc = "prefix to be added to all the generated rules, can be used in cases where you need several separated dependency graphs (say, for app X and app Z, or for annotation-processor phase."),
        "macro_prefix": attr.string(mandatory=True, doc = "prefix to be added to the names of macros that generates the repository-rules and target"),
        "_resolver": attr.label(executable=True, allow_files=True, single_file=True, cfg="host", default=Label("//resolver:resolver_deploy.jar"))
    },
    outputs={"out": "%{name}-generate-deps.sh"})
