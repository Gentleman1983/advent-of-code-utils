package de.havox_design.aoc.utils.kotlin.model.directions

enum class LeftRightDirection(private val symbol: Char, val direction: Int) {
    LEFT('L', -1),
    RIGHT('R', 1);

    companion object {
        fun from(string: String): LeftRightDirection =
            from(string[0])

        fun from(char: Char): LeftRightDirection {
            if (entries.map { dir -> dir.symbol }.contains(char)) {
                return entries.first { dir -> dir.symbol == char }
            }

            throw IllegalArgumentException("Unknown symbol '${char}'.")
        }
    }
}
