package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day22Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        AoC2022Day22MonkeyMap(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(expectedResult: Int) =
        AoC2022Day22MonkeyMap("de/havox_design/aoc2022/day22/day22.txt").processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadOrders")
    fun testReadOrders(filename: String, expectedResult: List<String>) =
        AoC2022Day22MonkeyMap(filename).orders.shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadMapData")
    fun testReadMapData(filename: String, expectedResult: List<String>) =
        AoC2022Day22MonkeyMap(filename).data.shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStartMap")
    fun testStartMap(filename: String, expectedResult: Array<Array<AoC2022Day22Field>>) =
        AoC2022Day22Map(AoC2022Day22MonkeyMap(filename).data).map.shouldExactlyBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStartingField")
    fun testStartingField(filename: String, expectedField: Position2d<Int>, expectedDirection: AoC2022Day22Field) {
        val map = AoC2022Day22Map(AoC2022Day22MonkeyMap(filename).data)

        assertAll(
            { map.currentPosition.shouldBe(expectedField) },
            { map.map[expectedField.y][expectedField.x].shouldBe(expectedDirection) }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day22/day22Sample.txt", 6032)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of(55267)
            )

        @JvmStatic
        private fun getDataForTestReadMapData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day22/day22Sample.txt",
                    listOf(
                        "        ...#",
                        "        .#..",
                        "        #...",
                        "        ....",
                        "...#.......#",
                        "........#...",
                        "..#....#....",
                        "..........#.",
                        "        ...#....",
                        "        .....#..",
                        "        .#......",
                        "        ......#."
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestReadOrders(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day22/day22Sample.txt",
                    listOf(
                        "10",
                        "R",
                        "5",
                        "L",
                        "5",
                        "R",
                        "10",
                        "L",
                        "4",
                        "R",
                        "5",
                        "L",
                        "5"
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestStartMap(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day22/day22Sample.txt",
                    arrayOf(
                        "                  ".toMapRow(),
                        "         >..#     ".toMapRow(),
                        "         .#..     ".toMapRow(),
                        "         #...     ".toMapRow(),
                        "         ....     ".toMapRow(),
                        " ...#.......#     ".toMapRow(),
                        " ........#...     ".toMapRow(),
                        " ..#....#....     ".toMapRow(),
                        " ..........#.     ".toMapRow(),
                        "         ...#.... ".toMapRow(),
                        "         .....#.. ".toMapRow(),
                        "         .#...... ".toMapRow(),
                        "         ......#. ".toMapRow(),
                        "                  ".toMapRow()
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestStartingField(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day22/day22Sample.txt",
                    Position2d(9, 1),
                    AoC2022Day22Field.RIGHT
                )
            )
    }
}

private fun Array<Array<AoC2022Day22Field>>.shouldExactlyBe(expectation: Array<Array<AoC2022Day22Field>>) =
    assertAll(
        { this.size.shouldBe(expectation.size) },
        {
            for (rowIndex in this.indices) {
                this[rowIndex].size.shouldBe(expectation[rowIndex].size)
            }
        },
        {
            for (rowIndex in this.indices) {
                for (colIndex in this[rowIndex].indices) {
                    this[rowIndex][colIndex].shouldBe(expectation[rowIndex][colIndex])
                }
            }
        }
    )
private fun String.toMapRow(): Array<AoC2022Day22Field> {
    val data = emptyList<AoC2022Day22Field>().toMutableList()

    for (index in this.indices) {
        when (this[index]) {
            '.' -> data += AoC2022Day22Field.FREE
            '#' -> data += AoC2022Day22Field.BLOCKED
            '^' -> data += AoC2022Day22Field.UP
            'v' -> data += AoC2022Day22Field.DOWN
            '<' -> data += AoC2022Day22Field.LEFT
            '>' -> data += AoC2022Day22Field.RIGHT
            else -> data += AoC2022Day22Field.UNAVAILABLE
        }
    }

    return data.toTypedArray()
}
