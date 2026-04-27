package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

data class Aoc2018Day17StillWater(override var point: Coordinate) : Water(point) {
    override fun isActive() =
        true

    override fun toString() =
        "~"
}
