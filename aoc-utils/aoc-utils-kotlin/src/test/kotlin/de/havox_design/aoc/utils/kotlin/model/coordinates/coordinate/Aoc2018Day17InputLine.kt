package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

data class Aoc2018Day17InputLine<T : Aoc2018Day17InputToken>(val tokens: Set<T>, val base: String) : Aoc2018Day17LineWithToken<T> {

    override fun at(index: Int) =
        base[index]

    override fun tokens() =
        tokens

    override fun base() =
        base

    override fun render(offset: Coordinate) =
        buildString {
            append(base)
            append(
                tokens
                    .sortedBy { it.point }
                    .joinToString("") { it.toString() }
            )
        }
}
