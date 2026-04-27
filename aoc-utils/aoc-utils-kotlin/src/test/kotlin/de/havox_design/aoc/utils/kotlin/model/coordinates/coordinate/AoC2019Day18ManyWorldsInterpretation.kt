package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

class AoC2019Day18ManyWorldsInterpretation(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart2(): Any =
        AoC2019Day18Maze
            .from(data)
            .minimumSteps()

    private fun getResourceAsText(path: String) =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
