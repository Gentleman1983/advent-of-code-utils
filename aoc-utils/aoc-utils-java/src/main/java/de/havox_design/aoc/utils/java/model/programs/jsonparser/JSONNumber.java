package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.io.Serial;
import java.util.List;
import java.util.Set;

/**
 *
 */
@SuppressWarnings("javaarchitecture:S7027")
public final class JSONNumber extends Number implements JSONEntity {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
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
     *
     */
    private final Number number;

    /**
     *
     * @param number
     */
    public JSONNumber(Number number) {
        this.number = number;
    }

    /**
     *
     * @param c
     * @return
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
     *
     * @param c
     * @return
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
