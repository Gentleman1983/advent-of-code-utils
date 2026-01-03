package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public record AoC2016Day13Point(long x, long y) {
    public boolean isValid(long width, long height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Collection<AoC2016Day13Point> neighbors() {
        return List.of(
                new AoC2016Day13Point(x - 1, y),
                new AoC2016Day13Point(x + 1, y),
                new AoC2016Day13Point(x, y - 1),
                new AoC2016Day13Point(x, y + 1));
    }

    public Collection<AoC2016Day13Point> neighbors(Predicate<AoC2016Day13Point> predicate) {
        return neighbors()
                .stream()
                .filter(predicate)
                .toList();
    }

    public Collection<AoC2016Day13Point> validNeighbors(long width, long height) {
        return neighbors(p -> p.isValid(width, height));
    }

    public Collection<AoC2016Day13Point> validNeighbors(long width, long height, Predicate<AoC2016Day13Point> predicate) {
        return neighbors(p -> p.isValid(width, height) && predicate.test(p));
    }

    public long dist(AoC2016Day13Point p) {
        return dist(this, p);
    }

    public static long dist(AoC2016Day13Point p1, AoC2016Day13Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
