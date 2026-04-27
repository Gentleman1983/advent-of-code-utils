package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

typealias InputMap<T> =
        List<Aoc2018Day17LineWithToken<T>>

fun <T : Aoc2018Day17InputToken> InputMap<T>.getTokens(): InputTokens<T> =
    flatMap { it.tokens() }
        .toSet()

fun <T : Aoc2018Day17InputToken> InputMap<T>.render(offset: Coordinate = Coordinate(0, 0)): List<String> =
    map { it.render(offset) }