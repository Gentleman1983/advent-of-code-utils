package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate

import de.havox_design.aoc.utils.kotlin.model.coordinates.four_directions.FourDirectionsFlipped

data class AoC2023Day17History(
    val lastDirection: FourDirectionsFlipped, val location: Coordinate, val count: Int
) {
    @SuppressWarnings("kotlin:S6510")
    fun moveDirections(maxConsecutive: Int, minConsecutive: Int): List<AoC2023Day17History> {
        when (count) {
            0 -> {
                return listOf(FourDirectionsFlipped.RIGHT, FourDirectionsFlipped.DOWN)
                    .map { AoC2023Day17History(it, it + location, 1) }
            }
            else -> {
                val leftRight =
                    listOf(lastDirection.turnLeft(), lastDirection.turnRight())
                        .map { AoC2023Day17History(it, it + location, 1) }
                val forward = AoC2023Day17History(lastDirection, lastDirection + location, count + 1)

                return when {
                    count < minConsecutive -> {
                        listOf(forward)
                    }
                    count == maxConsecutive -> {
                        leftRight
                    }
                    else -> leftRight + forward
                }
            }
        }
    }
}
