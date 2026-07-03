package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

class AoC2015Day18BooleanGrid(val size: Int) : AoC2015Day18Grid {
    private val lights = mutableSetOf<Position2d<Int>>()

    override fun turnOn(position: Position2d<Int>) {
        lights += Position2d(position.x, position.y)
    }

    override fun turnOff(position: Position2d<Int>) {
        lights -= Position2d(position.x, position.y)
    }

    override fun toggle(position: Position2d<Int>) =
        position
            .let {
                if (it in lights) {
                    lights -= it
                } else {
                    lights += it
                }
            }

    override fun lights() =
        lights.size

    private fun on(x: Int, y: Int) =
        Position2d(x, y) in lights

    fun next(): AoC2015Day18BooleanGrid {
        val new = AoC2015Day18BooleanGrid(size)

        for (x in 0 until size) {
            for (y in 0 until size) {
                val currentPosition = Position2d(x, y)

                if (on(x, y))
                    when (neighbors(currentPosition)) {
                        2, 3 -> new.turnOn(currentPosition)
                    }
                else
                    when (neighbors(currentPosition)) {
                        3 -> new.turnOn(currentPosition)
                    }
            }
        }

        return new
    }

    private fun neighbors(position: Position2d<Int>): Int {
        var on = if (on(position.x, position.y)) -1 else 0

        for (dy in -1..1) for (dx in -1..1) {
            if (on(position.x + dx, position.y + dy)) {
                on++
            }
        }

        return on
    }

    fun turnCornersOn(): AoC2015Day18BooleanGrid {
        turnOn(Position2d(0, 0))
        turnOn(Position2d(size - 1, 0))
        turnOn(Position2d(0, size - 1))
        turnOn(Position2d(size - 1, size - 1))
        return this
    }

    companion object {
        fun from(lines: Collection<String>): AoC2015Day18BooleanGrid {
            val grid = AoC2015Day18BooleanGrid(lines.size)

            lines
                .forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        if (c == '#') {
                            grid.turnOn(Position2d(x, y))
                        }
                    }
                }

            return grid
        }
    }
}