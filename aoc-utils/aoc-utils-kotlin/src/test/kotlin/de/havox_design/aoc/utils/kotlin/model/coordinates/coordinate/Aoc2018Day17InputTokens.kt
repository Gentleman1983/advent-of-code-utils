package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

typealias InputTokens<T> = Set<T>

fun <T : Aoc2018Day17InputToken> InputTokens<T>.active() =
    filter { it.isActive() }

fun <T : Aoc2018Day17InputToken> InputTokens<T>.activeAndSorted() =
    active()
        .sortedBy { it.point }
