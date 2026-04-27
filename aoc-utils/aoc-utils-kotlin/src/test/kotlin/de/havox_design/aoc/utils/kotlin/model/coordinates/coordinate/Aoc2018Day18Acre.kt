package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

data class Aoc2018Day18Acre(
    override var point: Coordinate,
    var content: Aoc2018Day18Content
) : Aoc2018Day17InputToken(point) {

    override fun isActive() =
        true

    override fun toString() =
        content
            .output
            .toString()

    fun next(state: AoC2018Day18Area) =
        when (content) {
            Aoc2018Day18Content.OPEN ->
                if (state.near(this, 3, Aoc2018Day18Content.TREES)) {
                    Aoc2018Day18Content.TREES
                } else {
                    content
                }

            Aoc2018Day18Content.TREES ->
                if (state.near(this, 3, Aoc2018Day18Content.LUMBERYARD)) {
                    Aoc2018Day18Content.LUMBERYARD
                } else {
                    content
                }

            Aoc2018Day18Content.LUMBERYARD ->
                if (
                    state.near(this, 1, Aoc2018Day18Content.LUMBERYARD) &&
                    state.near(this, 1, Aoc2018Day18Content.TREES)
                ) {
                    Aoc2018Day18Content.LUMBERYARD
                } else {
                    Aoc2018Day18Content.OPEN
                }
        }
}
