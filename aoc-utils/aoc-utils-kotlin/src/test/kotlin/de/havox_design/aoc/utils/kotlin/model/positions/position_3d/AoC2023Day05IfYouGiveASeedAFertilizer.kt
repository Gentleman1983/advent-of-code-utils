package de.havox_design.aoc.utils.kotlin.model.positions.position_3d

class AoC2023Day05IfYouGiveASeedAFertilizer(private var filename: String) {
    fun solvePart1(): Long =
        getResourceAsText(filename)
            .let { row -> AoC2023Day05Almanac.from(row.filter(String::isNotBlank)) }
            .seedsToLocation()
            .min()

    fun solvePart2(): Long =
        getResourceAsText(filename)
            .let { row -> AoC2023Day05Almanac.from(row.filter(String::isNotBlank)) }
            .seedRangesToLowestLocation()

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}