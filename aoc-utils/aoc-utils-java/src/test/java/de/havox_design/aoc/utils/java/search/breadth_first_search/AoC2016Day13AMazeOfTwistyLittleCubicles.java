package de.havox_design.aoc.utils.java.search.breadth_first_search;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Map;

public class AoC2016Day13AMazeOfTwistyLittleCubicles implements AoCFunctionality {
    private final Long input;

    public AoC2016Day13AMazeOfTwistyLittleCubicles(String fileName) {
        input = Long.parseLong(readData(fileName).getFirst());
    }

    public static long solvePart1(String fileName) {
        AoC2016Day13AMazeOfTwistyLittleCubicles instance = new AoC2016Day13AMazeOfTwistyLittleCubicles(fileName);

        return instance.solvePart1(1, 1, 31, 39);
    }

    public static long solvePart2(String fileName) {
        AoC2016Day13AMazeOfTwistyLittleCubicles instance = new AoC2016Day13AMazeOfTwistyLittleCubicles(fileName);

        return instance.solvePart2(1, 1, 50);
    }

    public long solvePart1(int startColumn, int startRow, int endColumn, int endRow) {
        AoC2016Day13Point target = new AoC2016Day13Point(endColumn, endRow);

        return process(startColumn, startRow)
                .get(target)
                .getDistance();
    }

    public long solvePart2(int startColumn, int startRow, int maxDistance) {
        return process(startColumn, startRow)
                .values()
                .stream()
                .filter(res -> res.getDistance() <= maxDistance)
                .count();
    }

    private Map<AoC2016Day13Point, PathResult<AoC2016Day13Point>> process(int startColumn, int startRow) {
        AoC2016Day13Point start = new AoC2016Day13Point(startColumn, startRow);
        int mapSize = 52;

        return BreadthFirstSearch.run(
                start,
                point -> point.validNeighbors(mapSize, mapSize, p -> isNotWall(p, input))
        );
    }

    private boolean isNotWall(AoC2016Day13Point point, long shift) {
        return (Long.bitCount(getMagicNumber(point.x(), point.y(), shift)) & 1) == 0;
    }

    private long getMagicNumber(long x, long y, long shift) {
        return x * (x + 3) + y * (x + x + y + 1) + shift;
    }
}
