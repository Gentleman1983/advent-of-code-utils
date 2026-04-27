package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

typealias AoC2018Day18Area = InputMap<Aoc2018Day18Acre>

fun AoC2018Day18Area.step() =
    getTokens()
    .associateBy { it.point }
    .mapValues { (_, acre) -> acre.next(this) }
    .forEach { (point, content) -> get(point)?.content = content }

fun AoC2018Day18Area.near(acre: Aoc2018Day18Acre, min: Int, target: Aoc2018Day18Content) =
    adjacentCoordinates8Directions(acre.point)
        .count { get(it)?.content == target } >= min

fun AoC2018Day18Area.get(point: Coordinate) =
    if (point.y in indices) {
        this[point.y]
            .tokens()
            .firstOrNull { it.atColumn(point.x) }
    } else {
        null
    }
