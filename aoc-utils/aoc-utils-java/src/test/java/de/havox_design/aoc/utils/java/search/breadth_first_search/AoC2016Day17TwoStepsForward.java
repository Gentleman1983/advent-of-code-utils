package de.havox_design.aoc.utils.java.search.breadth_first_search;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.coordinates.up_down_left_right_direction.UDLRDirection;
import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AoC2016Day17TwoStepsForward implements AoCFunctionality {
    private static final int SIZE = 4;
    private static final AoC2016Day17Tile START_TILE = new AoC2016Day17Tile(new Position2d<>(0, 0));
    private static final AoC2016Day17Tile TARGET_TILE = new AoC2016Day17Tile(new Position2d<>(3, 3));
    private static final List<UDLRDirection> ORDERED_DIRECTIONS = List.of(
            UDLRDirection.UP,
            UDLRDirection.DOWN,
            UDLRDirection.LEFT,
            UDLRDirection.RIGHT
    );

    private final String input;

    public AoC2016Day17TwoStepsForward(String fileName) {
        input = readData(fileName).getFirst();
    }

    public static String solvePart1(String fileName) {
        AoC2016Day17TwoStepsForward instance = new AoC2016Day17TwoStepsForward(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        AoC2016Day17TwoStepsForward instance = new AoC2016Day17TwoStepsForward(fileName);

        return instance.solvePart2();
    }

    public String solvePart1() {
        return getResultMap()
                .values()
                .stream()
                .filter(res -> res.getNode().tile().equals(TARGET_TILE))
                .min(Comparator.comparing(PathResult::getDistance))
                .orElseThrow()
                .getNode()
                .path();
    }

    public long solvePart2() {
        return getResultMap()
                .values()
                .stream()
                .filter(res -> res.getNode().tile().equals(TARGET_TILE))
                .mapToLong(PathResult::getDistance)
                .max()
                .orElseThrow();
    }

    private Map<AoC2016Day17State, PathResult<AoC2016Day17State>> getResultMap() {
        return BreadthFirstSearch.run(new AoC2016Day17State(START_TILE, ""), state -> getNextFeasibleStates(state, input));
    }

    private List<AoC2016Day17State> getNextFeasibleStates(AoC2016Day17State state, String passCode) {
        if (state.tile().equals(TARGET_TILE)) {
            return List.of();
        }

        String hash = getMd5Hash(passCode + state.path());

        List<AoC2016Day17State> result = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            AoC2016Day17Tile neighbor = state.tile().neighbor(ORDERED_DIRECTIONS.get(i));
            boolean doorIsOpen = neighbor.isValid(SIZE, SIZE)
                    && hash.charAt(i) >= 'b'
                    && hash.charAt(i) <= 'f';

            if (doorIsOpen) {
                result.add(new AoC2016Day17State(neighbor, state.path() + ORDERED_DIRECTIONS.get(i).getSymbol()));
            }
        }

        return result;
    }

    @SuppressWarnings({"squid:S1874", "squid:S4790"})
    private static String getMd5Hash(String s) {
        return DigestUtils
                .md5Hex(s);
    }
}
