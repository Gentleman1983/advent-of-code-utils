package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

class PyroclasticFlow(private var filename: String) {
    val jetPattern = readFile()
    private var numberOfJetsPerformed: Long = 0
    private val rockSequence = arrayOf(AoC2022Day17Rock.HORIZONTAL_LINE, AoC2022Day17Rock.PLUS, AoC2022Day17Rock.ARROW, AoC2022Day17Rock.VERTICAL_LINE, AoC2022Day17Rock.BOX)
    val chamber = AoC2022Day17Chamber()

    fun processPart1(numberOfStones: Long = 2022): Long {
        var counter = 0

        while (counter < numberOfStones) {
            val nextRock = rockSequence[counter % rockSequence.size]

            performRock(nextRock)
            counter++
        }

        return chamber.getMaxHeight()
    }

    fun processPart2(): Long =
        processPart1(1000000000000)

    private fun performRock(rock: AoC2022Day17Rock) {
        var currentPosition = chamber.getStartPositionForRock()
        var currentJet = jetPattern[(numberOfJetsPerformed % jetPattern.size).toInt()]

        while (!chamber.addRockToObstaclesIfItCollides(rock, currentPosition, currentJet)) {
            numberOfJetsPerformed++
            currentPosition += AoC2022Day17Jet.getPositionForJet(chamber.getRealDirection(rock, currentPosition, currentJet))
            currentJet = jetPattern[(numberOfJetsPerformed % jetPattern.size).toInt()]

            // Drop a row
            if (chamber.addRockToObstaclesIfItCollides(rock, currentPosition, AoC2022Day17Jet.DOWN)) {
                numberOfJetsPerformed--
                break
            }
            currentPosition += AoC2022Day17Jet.getPositionForJet(AoC2022Day17Jet.DOWN)
        }
        numberOfJetsPerformed++
    }

    private fun readFile(): List<AoC2022Day17Jet> {
        val fileData = getResourceAsText(filename)
        val jetSequence = emptyList<AoC2022Day17Jet>().toMutableList()

        for (index in fileData[0].indices) {
            val code: String = fileData[0][index].toString()

            jetSequence.add(AoC2022Day17Jet.getJetForCode(code))
        }

        return jetSequence
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

operator fun Position2d<Long>.plus(other: Position2d<Long>): Position2d<Long> =
    Position2d(x + other.x, y + other.y)
