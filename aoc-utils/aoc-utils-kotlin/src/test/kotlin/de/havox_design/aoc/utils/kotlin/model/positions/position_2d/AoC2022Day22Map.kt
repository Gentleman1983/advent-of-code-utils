package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

import java.lang.IllegalArgumentException

class AoC2022Day22Map(private var import: List<String>) {
    var map = buildMap()
    var currentPosition = setStartPosition()

    fun turnRight() {
        when (map[currentPosition.y][currentPosition.x]) {
            AoC2022Day22Field.UP -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.RIGHT
            AoC2022Day22Field.RIGHT -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.DOWN
            AoC2022Day22Field.DOWN -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.LEFT
            AoC2022Day22Field.LEFT -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.UP
            else -> return // ignore
        }
    }

    fun turnLeft() {
        when (map[currentPosition.y][currentPosition.x]) {
            AoC2022Day22Field.UP -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.LEFT
            AoC2022Day22Field.RIGHT -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.UP
            AoC2022Day22Field.DOWN -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.RIGHT
            AoC2022Day22Field.LEFT -> map[currentPosition.y][currentPosition.x] = AoC2022Day22Field.DOWN
            else -> return // ignore
        }
    }

    fun move(numberOfFields: Int) {
        when (map[currentPosition.y][currentPosition.x]) {
            AoC2022Day22Field.UP -> for (i in 0 until numberOfFields) tryMoveUp()
            AoC2022Day22Field.RIGHT -> for (i in 0 until numberOfFields) tryMoveRight()
            AoC2022Day22Field.DOWN -> for (i in 0 until numberOfFields) tryMoveDown()
            AoC2022Day22Field.LEFT -> for (i in 0 until numberOfFields) tryMoveLeft()
            else -> return // ignore
        }
    }

    fun evaluatePosition(): Int {
        val rowNo = currentPosition.y
        val colNo = currentPosition.x

        return rowNo * 1000 + colNo * 4 + map[rowNo][colNo].value
    }

    private fun tryMoveUp() =
        tryMove(targetX = currentPosition.x, targetY = currentPosition.y - 1, targetDirection = AoC2022Day22Field.UP)

    private fun tryMoveRight() =
        tryMove(targetX = currentPosition.x + 1, targetY = currentPosition.y, targetDirection = AoC2022Day22Field.RIGHT)

    private fun tryMoveDown() =
        tryMove(targetX = currentPosition.x, targetY = currentPosition.y + 1, targetDirection = AoC2022Day22Field.DOWN)

    private fun tryMoveLeft() =
        tryMove(targetX = currentPosition.x - 1, targetY = currentPosition.y, targetDirection = AoC2022Day22Field.LEFT)

    private fun tryMove(targetX: Int, targetY: Int, targetDirection: AoC2022Day22Field) {
        // Handle map borders
        if(targetY >= map.size) {
            tryMove(targetX = targetX, targetY = 1, targetDirection = targetDirection)
            return
        }
        if(targetY < 0) {
            tryMove(targetX = targetX, targetY = map.size - 1, targetDirection = targetDirection)
            return
        }
        if(targetX >= map[0].size) {
            tryMove(targetX = 1, targetY = targetY, targetDirection = targetDirection)
            return
        }
        if(targetX < 0) {
            tryMove(targetX = map[0].size - 1, targetY = targetY, targetDirection = targetDirection)
            return
        }

        val targetField = map[targetY][targetX]

        if (targetField != AoC2022Day22Field.BLOCKED && targetField != AoC2022Day22Field.UNAVAILABLE) {
            map[targetY][targetX] = targetDirection
            currentPosition = Position2d(targetX, targetY)
        }
        if(targetField == AoC2022Day22Field.UNAVAILABLE) {
            when(targetDirection) {
                AoC2022Day22Field.UP -> tryMove(targetX = targetX, targetY = targetY - 1, targetDirection = targetDirection)
                AoC2022Day22Field.RIGHT -> tryMove(targetX = targetX + 1, targetY = targetY, targetDirection = targetDirection)
                AoC2022Day22Field.DOWN -> tryMove(targetX = targetX, targetY = targetY + 1, targetDirection = targetDirection)
                AoC2022Day22Field.LEFT -> tryMove(targetX = targetX - 1, targetY = targetY, targetDirection = targetDirection)
                else -> throw IllegalArgumentException("This should never happen")
            }
        }
    }

    private fun setStartPosition(): Position2d<Int> {
        for(fieldIndex in map[1].indices) {
            val field = map[1][fieldIndex]
            if(field != AoC2022Day22Field.BLOCKED
                && field != AoC2022Day22Field.UNAVAILABLE
            ) {
                map[1][fieldIndex] = AoC2022Day22Field.RIGHT
                return Position2d(y = 1, x = fieldIndex)
            }
        }

        throw IllegalArgumentException("Expected to have a valid start field in row 1.")
    }

    private fun buildMap(): Array<Array<AoC2022Day22Field>> {
        val columns = import.maxOf { row -> row.length }

        val rows = emptyList<Array<AoC2022Day22Field>>().toMutableList()
        var currentRow = emptyList<AoC2022Day22Field>().toMutableList()
        for (col in 0..columns + 1) {
            currentRow += AoC2022Day22Field.UNAVAILABLE
        }
        rows += currentRow.toTypedArray()

        for (row in 1..import.size) {
            currentRow = emptyList<AoC2022Day22Field>().toMutableList()
            currentRow += AoC2022Day22Field.UNAVAILABLE

            for (col in 1..columns) {
                if(row <= import.size &&
                    col <= import[row - 1].length) {
                    currentRow += when (import[row - 1][col - 1].toString()) {
                        AoC2022Day22Field.FREE.symbol -> AoC2022Day22Field.FREE
                        AoC2022Day22Field.BLOCKED.symbol -> AoC2022Day22Field.BLOCKED
                        else -> AoC2022Day22Field.UNAVAILABLE
                    }
                }
                else {
                    currentRow += AoC2022Day22Field.UNAVAILABLE
                }
            }
            currentRow += AoC2022Day22Field.UNAVAILABLE
            rows += currentRow.toTypedArray()
        }

        currentRow = emptyList<AoC2022Day22Field>().toMutableList()
        for (col in 0..columns + 1) {
            currentRow += AoC2022Day22Field.UNAVAILABLE
        }
        rows += currentRow.toTypedArray()

        return rows.toTypedArray()
    }
}
