package de.havox_design.aoc.utils.java.search.breadth_first_search;

import de.havox_design.aoc.utils.kotlin.model.coordinates.up_down_left_right_direction.UDLRDirection;
import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d;

public record AoC2016Day17Tile(Position2d<Integer> position) {
    public boolean isValid(int rowCount, int colCount) {
        return position.getY() >= 0
                && position.getY() < rowCount
                && position.getX() >= 0
                && position.getX() < colCount;
    }

    public AoC2016Day17Tile neighbor(UDLRDirection direction) {
        return new AoC2016Day17Tile(
                new Position2d<>(
                        direction.getDirection().getX() + position.getX(),
                        direction.getDirection().getY() + position.getY()
                )
        );
    }
}
