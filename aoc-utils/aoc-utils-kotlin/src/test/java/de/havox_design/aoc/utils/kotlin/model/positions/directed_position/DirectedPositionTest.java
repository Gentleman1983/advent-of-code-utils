package de.havox_design.aoc.utils.kotlin.model.positions.directed_position;

import static org.junit.jupiter.api.Assertions.*;

import de.havox_design.aoc.utils.kotlin.model.coordinates.four_directions.FourDirections;
import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DirectedPositionTest {
    @Test
    void testConstructorAndGetters() {
        Position2d<Integer> position = new Position2d<>(3, 4);
        DirectedPosition dp = new DirectedPosition(position, FourDirections.UP);

        assertEquals(position.getX(), dp.getX());
        assertEquals(position.getY(), dp.getY());
    }

    @ParameterizedTest
    @MethodSource("getDataForTestForward")
    void testForward(FourDirections startDirection, Position2d<Integer> expectedPosition) {
        Position2d<Integer> position = new Position2d<>(5, 5);
        DirectedPosition dp = new DirectedPosition(position, startDirection);

        DirectedPosition result = dp.forward();

        assertEquals(expectedPosition.getX(), result.getX());
        assertEquals(expectedPosition.getY(), result.getY());
    }

    @ParameterizedTest
    @MethodSource("getDataForTestTurnLeft")
    void testTurnLeft(FourDirections startDirection, FourDirections expectedDirection) {
        Position2d<Integer> position = new Position2d<>(1, 1);
        DirectedPosition dp = new DirectedPosition(position, startDirection);

        DirectedPosition result = dp.left();

        assertEquals(position.getX(), result.getX());
        assertEquals(position.getY(), result.getY());
        assertEquals(expectedDirection, resultDirection(result));
    }

    @ParameterizedTest
    @MethodSource("getDataForTestTurnRight")
    void testTurnRight(FourDirections startDirection, FourDirections expectedDirection) {
        Position2d<Integer> position = new Position2d<>(1, 1);
        DirectedPosition dp = new DirectedPosition(position, startDirection);

        DirectedPosition result = dp.right();

        assertEquals(position.getX(), result.getX());
        assertEquals(position.getY(), result.getY());
        assertEquals(expectedDirection, resultDirection(result));
    }

    @Test
    void testToString() {
        DirectedPosition dp = new DirectedPosition(new Position2d<>(2, 3), FourDirections.DOWN);

        String result = dp.toString();

        assertEquals("[x=2, y=3, direction=DOWN]", result);
    }

    private FourDirections resultDirection(DirectedPosition dp) {

        String str = dp.toString();
        if (str.contains("UP")) return FourDirections.UP;
        if (str.contains("DOWN")) return FourDirections.DOWN;
        if (str.contains("LEFT")) return FourDirections.LEFT;
        if (str.contains("RIGHT")) return FourDirections.RIGHT;

        fail("Direction not found in toString()");
        return null;
    }

    private static Stream<Arguments> getDataForTestForward() {
        return Stream.of(
                Arguments.of(FourDirections.UP, new Position2d<>(5,4)),
                Arguments.of(FourDirections.DOWN, new Position2d<>(5,6)),
                Arguments.of(FourDirections.LEFT, new Position2d<>(4,5)),
                Arguments.of(FourDirections.RIGHT, new Position2d<>(6,5))
        );
    }

    private static Stream<Arguments> getDataForTestTurnLeft() {
        return Stream.of(
                Arguments.of(FourDirections.UP, FourDirections.LEFT),
                Arguments.of(FourDirections.DOWN, FourDirections.RIGHT),
                Arguments.of(FourDirections.LEFT, FourDirections.DOWN),
                Arguments.of(FourDirections.RIGHT, FourDirections.UP)
        );
    }

    private static Stream<Arguments> getDataForTestTurnRight() {
        return Stream.of(
                Arguments.of(FourDirections.UP, FourDirections.RIGHT),
                Arguments.of(FourDirections.DOWN, FourDirections.LEFT),
                Arguments.of(FourDirections.LEFT, FourDirections.UP),
                Arguments.of(FourDirections.RIGHT, FourDirections.DOWN)
        );
    }
}
