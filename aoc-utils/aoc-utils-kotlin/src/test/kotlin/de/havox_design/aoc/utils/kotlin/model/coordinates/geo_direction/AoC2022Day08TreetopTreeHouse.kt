package de.havox_design.aoc.utils.kotlin.model.coordinates.geo_direction

class AoC2022Day08TreetopTreeHouse(private var filename: String) {
    private var wood = AoC2022Day08Wood()

    fun processPart1(): Int {
        readFile()

        var visibleTrees = 0

        for (rowIndex in 0 until wood.getRows()) {
            for (colIndex in 0 until wood.getCols()) {
                if (wood.isTreeVisible(rowIndex, colIndex)) {
                    visibleTrees++
                }
            }
        }

        return visibleTrees
    }

    fun processPart2(): Int {
        readFile()
        wood.calculateScenicScores()

        var topScore = 0

        for (rowIndex in 0 until wood.getRows()) {
            for (colIndex in 0 until wood.getCols()) {
                topScore = wood.getTree(rowIndex, colIndex).scenicScore.coerceAtLeast(topScore)
            }
        }

        return topScore
    }

    fun readFile() {
        val data = getResourceAsText(filename)

        for (row in data) {
            wood.addRow(row)
        }
    }

    fun getWood(): AoC2022Day08Wood =
        wood

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
