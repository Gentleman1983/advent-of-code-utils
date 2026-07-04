package de.havox_design.aoc.utils.kotlin.model.collections.chargrid

import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d

data class AoC2024Day16State(
    val position: Position2d<Int>,
    val direction: MazeDirection,
    val score: Int,
    val path: Set<Position2d<Int>>
)
