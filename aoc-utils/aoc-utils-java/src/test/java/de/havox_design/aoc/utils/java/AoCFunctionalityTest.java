package de.havox_design.aoc.utils.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Test class for {@link AoCFunctionality}.
 */
class AoCFunctionalityTest {
    @Test
    void testBasic() {
        Assertions.assertTrue(true);
    }

    /**
     * <b>Given</b>: A file name and the expected entries.
     * <br/>
     * <b>When</b>: Calling {@link AoCFunctionality#readData(String)}.
     * <br/>
     * <b>Then</b>: The correct data should be read from these files.
     *
     * @param fileName the file name
     * @param expectedEntries the expected content
     */
    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    void testReadFile(String fileName, List<String> expectedEntries) {
        AoCFunctionality instance = getAoCFunctionality();
        List<String> testData = instance.readData(fileName);

        assertAll(
                () -> assertThat(testData.size(), is(expectedEntries.size())),
                () -> assertThat(testData, contains(expectedEntries.toArray())),
                () -> assertThat(testData, equalTo(expectedEntries))
        );
    }

    /**
     * Data for {@link #testReadFile(String, List)}.
     *
     * @return the test data
     */
    private static Stream<Arguments> getDataForReadFile() {
        return Stream.of(
                Arguments.of(
                        "DataReader-sample1.txt",
                        List.of(
                                "This is a single row text!"
                        )
                ),
                Arguments.of(
                        "DataReader-sample2.txt",
                        List.of(
                                "This is",
                                "a",
                                "multi row",
                                "text!"
                        )
                )
        );
    }

    /**
     * Produces instant instance of {@link AoCFunctionality}.
     *
     * @return a dummy instance.
     */
    private AoCFunctionality getAoCFunctionality() {
        return new AoCFunctionality() {
            @Override
            public List<String> readData(String fileName) {
                return AoCFunctionality.super.readData(fileName);
            }
        };
    }
}
