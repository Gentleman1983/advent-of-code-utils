package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

abstract class Aoc2018Day17InputToken(open var point: Coordinate) {

    abstract fun isActive(): Boolean

    fun x() =
        point.x

    fun y() =
        point.y

    fun atColumn(x: Int) =
        point.x == x

    fun atRow(y: Int) =
        point.y == y
}
