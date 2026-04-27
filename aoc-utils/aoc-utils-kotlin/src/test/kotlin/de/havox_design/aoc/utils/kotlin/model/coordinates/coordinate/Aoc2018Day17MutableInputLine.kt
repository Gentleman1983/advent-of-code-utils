package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

data class Aoc2018Day17MutableInputLine<T : Aoc2018Day17InputToken, A : Aoc2018Day17InputToken>(
    val inputLine: Aoc2018Day17InputLine<T>,
    val additions: MutableSet<A> = mutableSetOf()) : Aoc2018Day17LineWithToken<T> {

    constructor(tokens: Set<T>, base: String) : this(Aoc2018Day17InputLine(tokens, base))

    override fun tokens() =
        inputLine.tokens

    override fun base() =
        inputLine.base

    override fun at(index: Int) =
        additions
            .firstOrNull { it.atColumn(index) }
        ?.let { it.toString()[0] } ?: inputLine.at(index)

    override fun render(offset: Coordinate) =
        buildString {
        append(inputLine.render(offset))
        setLength(1 + additions.map { it.x() }.max())
        additions
            .forEach { setCharAt(it.x() - offset.x, it.toString()[0]) }
    }
}
