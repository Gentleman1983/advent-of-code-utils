package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

enum class Aoc2018Day18Content(val output: Char) {
    OPEN('.'),
    TREES('|'),
    LUMBERYARD('#');

    companion object {
        fun parse(value: Char) =
            entries
                .find { it.output == value }
            ?: throw IllegalArgumentException("Unknown content.")
    }
}
