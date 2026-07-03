package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

data class Aoc2016Day24Position(val position: Position2d<Int>, val value: String) {
    val isValid = position.x >= 0
            && position.y >= 0
    val isWall = value == "#"
    val n = try {
        value.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
