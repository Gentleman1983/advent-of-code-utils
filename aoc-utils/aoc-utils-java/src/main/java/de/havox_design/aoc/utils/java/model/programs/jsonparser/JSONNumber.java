package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.io.Serial;
import java.util.List;
import java.util.Set;

/**
 * The JSON number class.
 */
@SuppressWarnings("javaarchitecture:S7027")
public final class JSONNumber extends Number implements JSONEntity {
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The potential number characters.
     */
    private static final Set<Character> NUMBER_STARTERS = Set.of(
            '-',
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9'
    );

    /**
     * The number.
     */
    private final Number number;

    /**
     * The constructor.
     *
     * @param number the number
     */
    public JSONNumber(Number number) {
        this.number = number;
    }

    /**
     * Checks if the character can be inner part of the number.
     *
     * @param c the character
     * @return true, if it can be the inner part
     */
    public static boolean canBeInnerPartOfANumber(char c) {
        if (c == '-') {
            return false;
        }
        if (c == '.') {
            return true;
        }
        return NUMBER_STARTERS.contains(c);
    }

    /**
     * Checks if the character can be start of the number.
     *
     * @param c the character
     * @return true, if it can be the start
     */
    public static boolean canBeStartOfANumber(char c) {
        return NUMBER_STARTERS.contains(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double doubleValue() {
        return number.doubleValue();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public float floatValue() {
        return number.floatValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getIntegersWithoutRed() {
        return List.of(number.intValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int intValue() {
        return number.intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long longValue() {
        return number.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return number.toString();
    }
}
