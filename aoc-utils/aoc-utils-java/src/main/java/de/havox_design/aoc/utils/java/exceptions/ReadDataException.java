package de.havox_design.aoc.utils.java.exceptions;

/**
 * The {@link  AdventOfCodeException} regarding the data readers.
 */
public class ReadDataException extends AdventOfCodeException {
    /**
     * Creates the exception with a source exception and message.
     *
     * @param message the message
     * @param e the source exception
     */
    public ReadDataException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Creates the exception with a source exception and uses the source exceptions message.
     *
     * @param e the source exception
     */
    public ReadDataException(Exception e) {
        this(e.getMessage(), e);
    }
}
