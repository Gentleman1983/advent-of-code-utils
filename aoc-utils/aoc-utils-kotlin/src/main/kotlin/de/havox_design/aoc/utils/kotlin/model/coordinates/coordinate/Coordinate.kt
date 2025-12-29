package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {
    /******************************************************************************************************************
     * Operators                                                                                                      *
     ******************************************************************************************************************/
    operator fun div(other: Coordinate): Coordinate =
        Coordinate(x / other.x, y / other.y)

    operator fun minus(other: Coordinate): Coordinate =
        Coordinate(x - other.x, y - other.y)

    operator fun plus(other: Coordinate): Coordinate =
        Coordinate(x + other.x, y + other.y)

    operator fun plus(i: Number): Coordinate =
        plus(Coordinate(i.toInt(), i.toInt()))

    operator fun times(i: Number): Coordinate =
        this * Coordinate(i.toInt(), i.toInt())

    operator fun times(other: Coordinate): Coordinate =
        Coordinate(x * other.x, y * other.y)

    /******************************************************************************************************************
     * Functions                                                                                                      *
     ******************************************************************************************************************/
    override fun compareTo(other: Coordinate): Int =
        when (val result = y.compareTo(other.y)) {
            0 -> x.compareTo(other.x)
            else -> result
        }
}
