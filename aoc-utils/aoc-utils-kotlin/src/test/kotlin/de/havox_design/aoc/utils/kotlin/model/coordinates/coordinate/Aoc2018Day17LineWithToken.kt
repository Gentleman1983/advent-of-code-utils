package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

interface Aoc2018Day17LineWithToken<T : Aoc2018Day17InputToken> {

    fun tokens(): Set<T>

    fun base(): String

    fun at(index: Int): Char

    fun render(offset: Coordinate = Coordinate(0, 0)): String
}
