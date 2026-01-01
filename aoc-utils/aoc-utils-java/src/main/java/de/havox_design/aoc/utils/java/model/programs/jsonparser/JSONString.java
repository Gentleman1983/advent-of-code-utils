package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.util.List;

/**
 *
 */
@SuppressWarnings("javaarchitecture:S7027")
public final class JSONString implements CharSequence, JSONEntity {
    /**
     *
     */
    private String string;

    /**
     *
     * @param string
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
    @Override
    public CharSequence subSequence(int start, int end) {
        return string.subSequence(start, end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "'" + string + "'";
    }
}
