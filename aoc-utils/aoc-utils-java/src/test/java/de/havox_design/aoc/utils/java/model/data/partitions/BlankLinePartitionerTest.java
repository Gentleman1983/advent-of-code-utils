package de.havox_design.aoc.utils.java.model.data.partitions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

/**
 * Test class for {@link BlankLinePartitioner}.
 */
class BlankLinePartitionerTest {
    @ParameterizedTest
    @MethodSource("getDataForPartitioningTest")
    void testPartitioning(List<String> input, List<List<String>> expectedResult) {
        Partitioner<String> partitioner = new BlankLinePartitioner();

        List<List<String>> result = partitioner.partition(input);

        Assertions.assertIterableEquals(expectedResult, result);
    }

    private static Stream<Arguments> getDataForPartitioningTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                "This",
                                "list",
                                "should",
                                "not",
                                "be",
                                "partitioned."
                        ),
                        List.of(
                                List.of(
                                        "This",
                                        "list",
                                        "should",
                                        "not",
                                        "be",
                                        "partitioned."
                                )
                        )
                ),
                Arguments.of(
                        List.of(
                                "This",
                                "is",
                                "the",
                                "first",
                                "partition.",
                                "",
                                "This",
                                "is",
                                "the",
                                "second",
                                "partition."
                        ),
                        List.of(
                                List.of(
                                        "This",
                                        "is",
                                        "the",
                                        "first",
                                        "partition."
                                ),
                                List.of(
                                        "This",
                                        "is",
                                        "the",
                                        "second",
                                        "partition."
                                )
                        )
                ),
                Arguments.of(
                        List.of(
                                "This",
                                "is",
                                "the",
                                "first",
                                "partition.",
                                "",
                                "This",
                                "is",
                                "the",
                                "second",
                                "partition.",
                                "",
                                "This",
                                "is",
                                "the",
                                "final",
                                "partition."
                        ),
                        List.of(
                                List.of(
                                        "This",
                                        "is",
                                        "the",
                                        "first",
                                        "partition."
                                ),
                                List.of(
                                        "This",
                                        "is",
                                        "the",
                                        "second",
                                        "partition."
                                ),
                                List.of(
                                        "This",
                                        "is",
                                        "the",
                                        "final",
                                        "partition."
                                )
                        )
                )
        );
    }
}
