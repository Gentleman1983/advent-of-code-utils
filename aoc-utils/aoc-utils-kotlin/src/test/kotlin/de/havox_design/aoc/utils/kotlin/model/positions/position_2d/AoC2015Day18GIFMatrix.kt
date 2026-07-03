package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

class AoC2015Day18GIFMatrix(private var filename: String) {
    private val data = readData()

    fun processPart1(steps: Int=100): Int =
        (1..steps)
            .fold(data) { current, _ -> current.next() }
            .lights()

    fun processPart2(steps: Int=100): Int =
        (1..steps)
            .fold(data) { current, _ ->
                current.turnCornersOn().next().turnCornersOn()
            }
            .lights()

    private fun readData(): AoC2015Day18BooleanGrid =
        AoC2015Day18BooleanGrid.from(getResourceAsText(filename))

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}