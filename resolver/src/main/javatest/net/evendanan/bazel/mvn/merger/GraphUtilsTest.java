package net.evendanan.bazel.mvn.merger;

import net.evendanan.bazel.mvn.api.model.Dependency;
import net.evendanan.bazel.mvn.api.model.MavenCoordinate;
import net.evendanan.bazel.mvn.api.model.Resolution;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class GraphUtilsTest {

    @Test
    public void testPrintGraphHappyPath() {
        final String simpleGraphPrintOut = "" +
                "  net.evendanan:dep1:0.1\n" +
                "    net.evendanan:inner1:0.1\n" +
                "      net.evendanan:inner-inner1:0.1\n" +
                "      net.evendanan:inner-inner2:0.1\n";

        Resolution resolution = Resolution.create(
                MavenCoordinate.create("net.evendanan", "dep1", "0.1", ""),
                Arrays.asList(
                        Dependency.builder()
                                .mavenCoordinate(MavenCoordinate.create("net.evendanan", "dep1", "0.1", ""))
                                .dependencies(Collections.singleton(MavenCoordinate.create("net.evendanan", "inner1", "0.1", "")))
                                .build(),
                        Dependency.builder()
                                .mavenCoordinate(MavenCoordinate.create("net.evendanan", "inner1", "0.1", ""))
                                .dependencies(Arrays.asList(
                                        MavenCoordinate.create("net.evendanan", "inner-inner1", "0.1", ""),
                                        MavenCoordinate.create("net.evendanan", "inner-inner2", "0.1", "")))
                                .build(),
                        Dependency.builder()
                                .mavenCoordinate(MavenCoordinate.create("net.evendanan", "inner-inner1", "0.1", ""))
                                .build(),
                        Dependency.builder()
                                .mavenCoordinate(MavenCoordinate.create("net.evendanan", "inner-inner2", "0.1", ""))
                                .build()));

        final String actual = GraphUtils.printGraph(resolution);

        Assert.assertEquals(simpleGraphPrintOut, actual);
    }
}