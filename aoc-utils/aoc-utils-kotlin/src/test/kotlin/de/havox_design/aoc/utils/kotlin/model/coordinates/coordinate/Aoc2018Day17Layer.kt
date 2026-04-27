package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

typealias Layer = Aoc2018Day17MutableInputLine<Aoc2018Day17Clay, Water>

fun Layer.nothingAt(point: Coordinate) =
    tokens().none { it.point == point } && additions.none { it.point == point }
