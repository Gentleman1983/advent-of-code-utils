package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

data class Aoc2018Day17Clay(override var point: Coordinate) : Aoc2018Day17InputToken(point) {
    override fun isActive() = true
    override fun toString() = "#"
}
