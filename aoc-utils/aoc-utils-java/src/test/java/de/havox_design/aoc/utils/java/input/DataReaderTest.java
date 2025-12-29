package de.havox_design.aoc.utils.java.input;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import de.havox_design.aoc.utils.java.exceptions.ReadDataException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

/**
 * Test class for {@link DataReader}.
 */
class DataReaderTest {
    /**
     * <b>Given</b>: A file name and the expected entries.
     * <br/>
     * <b>When</b>: Reading the files.
     * <br/>
     * <b>Then</b>: The correct data should be read from these files.
     *
     * @param fileName the file name
     * @param expectedEntries the expected content
     */
    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    void testReadFile(String fileName, List<String> expectedEntries) {
        String expectedString = String.join("\n", expectedEntries);

        List<String> testData = DataReader.readData(fileName, DataReaderTest.class);
        String testString = DataReader.readString(fileName, DataReaderTest.class);

        assertAll(
                () -> assertThat(testData.size(), is(expectedEntries.size())),
                () -> assertThat(testData, contains(expectedEntries.toArray())),
                () -> assertThat(testData, equalTo(expectedEntries)),
                () -> assertThat(testString, equalTo(expectedString))
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
     * <b>Given</b>: An invalid file name.
     * <br/>
     * <b>When</b>: Reading the file content.
     * <br/>
     * <b>Then</b>: It should trigger a {@link ReadDataException}.
     *
     * @param fileName the invalid file names
     */
    @ParameterizedTest
    @MethodSource("getDataForReadFileException")
    void testReadFileException(String fileName) {
        assertAll(
                () -> assertThrows(ReadDataException.class, () -> DataReader.readData(fileName, DataReaderTest.class)),
                () -> assertThrows(ReadDataException.class, () -> DataReader.readString(fileName, DataReaderTest.class))
        );
    }

    /**
     * The data for {@link #testReadFileException(String)}.
     *
     * @return the test data
     */
    private static Stream<Arguments> getDataForReadFileException() {
        return Stream.of(
                Arguments.of("http://foo.bar/test.txt"),
                Arguments.of("amnb;//;_:DSAWR-t"),
                Arguments.of("non-existing-file.txt"),
                Arguments.of("A:\\this\\is\\a\\non\\existing\\file.txt"),
                Arguments.of("/this/is/a/non/existing/file.txt")
        );
    }
}
