package de.havox_design.aoc.utils.kotlin.model.coordinates.up_down_left_right_direction

import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d

enum class UDLRDirection(val direction: Position2d<Int>, val symbol: Char) {
    DOWN(Position2d(0, 1), 'D'),
    LEFT(Position2d(-1, 0), 'L'),
    RIGHT(Position2d(1, 0), 'R'),
    UP(Position2d(0, -1), 'U')
}