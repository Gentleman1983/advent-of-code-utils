package de.havox_design.aoc.utils.java.exceptions;

/**
 * Basic {@link RuntimeException} for Advent of Code exceptions.
 */
public class AdventOfCodeException extends RuntimeException {
    /**
     * Creates the exception with a source exception and message.
     *
     * @param message the message
     * @param e the source exception
     */
    public AdventOfCodeException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Creates the exception with a source exception and uses the source exceptions message.
     * @param e the source exception
     */
    public AdventOfCodeException(Exception e) {
        this(e.getMessage(), e);
    }
}
