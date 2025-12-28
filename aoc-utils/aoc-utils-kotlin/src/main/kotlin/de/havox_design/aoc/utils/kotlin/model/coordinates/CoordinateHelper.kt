package de.havox_design.aoc.utils.kotlin.model.coordinates

import kotlin.math.abs

val origin =
    Coordinate(0, 0)

fun adjacentCoordinates(origin: Coordinate) =
    sequenceOf(
        Coordinate(origin.x + 1, origin.y),
        Coordinate(origin.x - 1, origin.y),
        Coordinate(origin.x, origin.y + 1),
        Coordinate(origin.x, origin.y - 1)
    )

fun adjacentCoordinates8Directions(origin: Coordinate) =
    sequenceOf(
        Coordinate(origin.x + 1, origin.y + 1),
        Coordinate(origin.x + 1, origin.y),
        Coordinate(origin.x + 1, origin.y - 1),
        Coordinate(origin.x, origin.y + 1),
        Coordinate(origin.x, origin.y - 1),
        Coordinate(origin.x - 1, origin.y + 1),
        Coordinate(origin.x - 1, origin.y),
        Coordinate(origin.x - 1, origin.y - 1)
    )

fun manhattanDistance(a: Coordinate, b: Coordinate) =
    abs(a.x - b.x) + abs(a.y - b.y)

/**********************************************************************************************************************
 * List                                                                                                               *
 **********************************************************************************************************************/
fun <V> Map<Coordinate, V>.yRange() =
    keys.minByOrNull { it.y }!!.y to keys.maxByOrNull { it.y }!!.y

fun <V> Map<Coordinate, V>.xRange() =
    keys.minByOrNull { it.x }!!.x to keys.maxByOrNull { it.x }!!.x
