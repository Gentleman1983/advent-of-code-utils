package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AoC2015Day12CharacterIterator implements Iterator<Character> {
    private final String data;
    private int current = 0;

    public AoC2015Day12CharacterIterator(String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return current < data.length();
    }

    @Override
    public Character next() {
        if(!hasNext()) {
            throw new NoSuchElementException("There are no more elements...");
        }

        return data.charAt(current++);
    }

    public char current() {
        return data.charAt(current);
    }

    public char back() {
        return data.charAt(--current);
    }
}
