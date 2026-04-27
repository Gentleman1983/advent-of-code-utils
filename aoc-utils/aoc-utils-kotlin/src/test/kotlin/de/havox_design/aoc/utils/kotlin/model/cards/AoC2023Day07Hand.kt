package de.havox_design.aoc.utils.kotlin.model.cards

class AoC2023Day07Hand(val cards: List<Card>, private val isPartOne: Boolean) : Comparable<AoC2023Day07Hand> {
    override fun compareTo(other: AoC2023Day07Hand): Int =
        compare(this, other, isPartOne)

    companion object {
        fun from(input: String, isPartOne: Boolean): AoC2023Day07Hand {
            val hand = ArrayList<Card>()

            for (c in input.toCharArray()) {
                hand.add(Card.from(c))
            }

            return AoC2023Day07Hand(hand, isPartOne)
        }

        fun compare(a: AoC2023Day07Hand, b: AoC2023Day07Hand, isPartOne: Boolean): Int {
            if (a == b) {
                return 0
            }

            val aGameType = AoC2023Day07GameType.from(a, isPartOne)
            val bGameType = AoC2023Day07GameType.from(b, isPartOne)

            if (aGameType == bGameType) {
                for (index in a.cards.indices) {
                    if (a.cards[index] == b.cards[index]) {
                        continue
                    }

                    return Card.compare(a.cards[index], b.cards[index], isPartOne)
                }
            }

            return AoC2023Day07GameType.compare(aGameType, bGameType)
        }
    }
}