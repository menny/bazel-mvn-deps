package net.evendanan.bazel.mvn;

import com.google.devtools.bazel.workspace.maven.Rule;

public interface RuleFormatter {

    String formatRule(String baseIndent, Rule rule);
}
