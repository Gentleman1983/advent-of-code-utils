package de.havox_design.aoc.utils.java.model.programs.duet.token;

import java.math.BigInteger;

/**
 * A provider for the {@link Token}s.
 */
public class TokenProvider {
    /**
     * The internal constructor.
     */
    private TokenProvider() {}

    /**
     * Creates a {@link Token} with a given value.
     *
     * @param value the value
     * @return the {@link Token}
     */
    public static Token createToken(final String value) {
        try {
            return new Constant(new BigInteger(value));
        } catch (final NumberFormatException nfe) {
            return new Variable(value);
        }
    }
}
