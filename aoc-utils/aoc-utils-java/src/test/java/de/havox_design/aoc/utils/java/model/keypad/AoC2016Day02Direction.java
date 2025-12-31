package de.havox_design.aoc.utils.java.model.keypad;

public enum AoC2016Day02Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT;

    public Key next(Key point) {
        return switch (this) {
            case UP -> new Key(point.x(), point.y() - 1);
            case DOWN -> new Key(point.x(), point.y() + 1);
            case RIGHT -> new Key(point.x() + 1, point.y());
            case LEFT -> new Key(point.x() - 1, point.y());
        };
    }
}
