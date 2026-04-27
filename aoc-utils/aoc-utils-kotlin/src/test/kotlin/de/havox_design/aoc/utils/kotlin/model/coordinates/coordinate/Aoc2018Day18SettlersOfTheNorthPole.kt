package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

class Aoc2018Day18SettlersOfTheNorthPole(private var filename: String) {

    @SuppressWarnings("kotlin:S6611")
    fun processTask1(iterations: Int): Any {
        val state: AoC2018Day18Area = getResourceAsText(filename)
            .mapIndexed { y, line ->
                Aoc2018Day17InputLine(
                    line
                        .mapIndexed { x, value ->
                            Aoc2018Day18Acre(Coordinate(x, y), Aoc2018Day18Content.parse(value))
                        }
                        .toSet(),
                    ""
                )
            }
        val seen = mutableMapOf<Int, Int>()
        var matched: Int? = null

        while (matched == null && seen.size < iterations) {
            state.step()
            matched = seen.put(state.render().hashCode(), seen.size)
        }

        if (seen.size < iterations) {
            val current = seen[state.render().hashCode()]!!

            repeat(((iterations - matched!!) % (current - matched)) - 1) {
                state.step()
            }
        }

        return state
            .getTokens()
            .groupBy { it.content }
            .let { it[Aoc2018Day18Content.TREES]!!.size * it[Aoc2018Day18Content.LUMBERYARD]!!.size }
    }

    fun processTask2(): Any =
        processTask1(1000000000)

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}