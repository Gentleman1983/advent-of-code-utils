package de.havox_design.aoc.utils.kotlin.model.chargrid

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class CharGrid(val data: Array<CharArray>) : Iterable<PointVal> {
    val rowIndices: IntRange
        get() = IntRange(0, data.lastIndex)
    val colIndices: IntRange
        get() = IntRange(0, data[0].lastIndex)

    /******************************************************************************************************************
     * Constructors                                                                                                   *
     ******************************************************************************************************************/
    constructor(data: List<CharArray>) : this(data.toTypedArray())

    /******************************************************************************************************************
     * Operators                                                                                                      *
     ******************************************************************************************************************/
    operator fun get(rowindex: Int): CharArray {
        return data[rowindex]
    }

    operator fun get(rowindex: Int, colindex: Int): Char {
        return data[rowindex][colindex]
    }

    operator fun get(point: Position2d<Int>): Char {
        return data[rowIndices.last - point.y][point.x]
    }

    operator fun set(rowindex: Int, colindex: Int, value: Char) {
        data[rowindex][colindex] = value
    }

    operator fun set(point: Position2d<Int>, value: Char) {
        data[rowIndices.last - point.y][point.x] = value
    }

    /******************************************************************************************************************
     * Functions                                                                                                      *
     ******************************************************************************************************************/
    fun copy(): CharGrid {
        return CharGrid(data.map { it.copyOf() }.toTypedArray())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is CharGrid) {
            return false
        }

        if (this.rowIndices != other.rowIndices || this.colIndices != other.colIndices) {
            return false
        }
        this
            .rowIndices
            .forEach { row ->
                if (!this.data[row].contentEquals(other.data[row])) {
                    return false
                }
            }

        return true
    }

    override fun hashCode(): Int {
        var hash = 7

        this
            .data
            .forEach { row ->
                hash = ((hash shl 5) - hash) + row.contentHashCode()
            }

        return hash
    }

    fun isValidPosition(row: Int, col: Int): Boolean {
        return row in rowIndices && col in colIndices
    }

    fun isValidPosition(pos: Pair<Int, Int>): Boolean {
        return pos.first in rowIndices && pos.second in colIndices
    }

    fun isValidPosition(point: Position2d<Int>): Boolean {
        return (rowIndices.last - point.y) in rowIndices && point.x in colIndices
    }

    override fun iterator(): Iterator<PointVal> {
        class PointValIterator(val grid: CharGrid) : Iterator<PointVal> {

            private var row = 0
            private var col = 0

            override fun hasNext(): Boolean {
                return col <= grid.colIndices.last && row <= grid.rowIndices.last
            }

            override fun next(): PointVal {
                val res = try {
                    val point = Position2d(col, row)
                    PointVal(point, grid[point])
                } catch (_: IndexOutOfBoundsException) {
                    throw NoSuchElementException()
                }
                ++col

                if (col > grid.colIndices.last) {
                    col = 0
                    ++row
                }

                return res
            }
        }

        return PointValIterator(this)
    }

    /******************************************************************************************************************
     * Initialization                                                                                                 *
     ******************************************************************************************************************/
    init {
        require(data.isNotEmpty()) { "Grid must have at least one row" }
        require(data.all { it.size == data[0].size }) { "All rows must have the same length" }
    }

    companion object {
        fun fromString(data: String): CharGrid =
            CharGrid(data.trim().lines().map { it.trim().toCharArray() })
    }

}
