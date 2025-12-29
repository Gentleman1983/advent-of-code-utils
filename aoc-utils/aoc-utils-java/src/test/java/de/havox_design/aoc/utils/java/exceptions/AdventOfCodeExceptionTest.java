package de.havox_design.aoc.utils.java.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Test class for {@link AdventOfCodeException}.
 */
class AdventOfCodeExceptionTest {
    /**
     * <b>Given</b>: A message and a source exception.
     * <br/>
     * <b>When</b>: Creating this exception.
     * <br/>
     * <b>Then</b>: The exceptions message shall be the given message, the source exception shall be the given exception
     * and the exception shall be extending {@link RuntimeException}.
     *
     * @param message the given message
     * @param exception the given exception
     */
    @ParameterizedTest
    @MethodSource("getDataForTestConstructorMessageSource")
    void testConstructorMessageSource(String message, Exception exception) {
        AdventOfCodeException adventOfCodeException = new AdventOfCodeException(message, exception);

        Assertions.assertAll(
                () -> Assertions.assertEquals(message, adventOfCodeException.getMessage()),
                () -> Assertions.assertEquals(exception, adventOfCodeException.getCause()),
                () -> Assertions.assertInstanceOf(RuntimeException.class, adventOfCodeException)
        );
    }

    /**
     * Generated data for {@link #testConstructorMessageSource(String, Exception)}.
     *
     * @return the test data
     */
    private static Stream<Arguments> getDataForTestConstructorMessageSource() {
        return Stream.of(
                Arguments.of(
                        "Message 1",
                        new IllegalArgumentException("Source Message 1")
                ),
                Arguments.of(
                        "Message 2",
                        new NumberFormatException("Source Message 2")
                ),
                Arguments.of(
                        "Message 3",
                        new IOException("Source Message 3")
                )
        );
    }

    /**
     * <b>Given</b>: A message and a source exception.
     * <br/>
     * <b>When</b>: Creating this exception.
     * <br/>
     * <b>Then</b>: The exceptions message shall be the given message, the source exception shall be the given exception
     * and the exception shall be extending {@link RuntimeException}.
     *
     * @param exception the given exception
     */
    @ParameterizedTest
    @MethodSource("getDataForTestConstructorSource")
    void testConstructorSource(Exception exception) {
        AdventOfCodeException adventOfCodeException = new AdventOfCodeException(exception);

        Assertions.assertAll(
                () -> Assertions.assertEquals(exception.getMessage(), adventOfCodeException.getMessage()),
                () -> Assertions.assertEquals(exception, adventOfCodeException.getCause()),
                () -> Assertions.assertInstanceOf(RuntimeException.class, adventOfCodeException)
        );
    }

    /**
     * Generated data for {@link #testConstructorMessageSource(String, Exception)}.
     *
     * @return the test data
     */
    private static Stream<Arguments> getDataForTestConstructorSource() {
        return Stream.of(
                Arguments.of(
                        new IllegalArgumentException("Source Message 1")
                ),
                Arguments.of(
                        new NumberFormatException("Source Message 2")
                ),
                Arguments.of(
                        new IOException("Source Message 3")
                )
        );
    }
}
