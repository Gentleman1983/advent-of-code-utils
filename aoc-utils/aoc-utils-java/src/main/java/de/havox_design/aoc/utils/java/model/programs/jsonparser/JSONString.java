package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * This represents a JSON string.
 */
@SuppressWarnings("javaarchitecture:S7027")
public final class JSONString implements CharSequence, JSONEntity {
    /**
     * The string.
     */
    private String string;

    /**
     * The constructor.
     *
     * @param string the string
     */
    public JSONString(String string) {
        this.string = string;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char charAt(int index) {
        return string.charAt(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JSONString jss) {
            return string.equals(jss.string);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getIntegersWithoutRed() {
        return List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @Override
    public int length() {
        return string.length();
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public CharSequence subSequence(int start, int end) {
        return string.subSequence(start, end);
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String toString() {
        return "'" + string + "'";
    }
}
