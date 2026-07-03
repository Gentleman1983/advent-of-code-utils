package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

class AoC2022Day17Chamber(var width: Long = 7L) {
    var obstacles: Set<Position2d<Long>> = emptySet<Position2d<Long>>().toMutableSet()

    fun getMaxHeight(): Long =
        obstacles
            .maxOfOrNull { position -> position.y + 1 } ?: 0

    fun getStartPositionForRock(
        distanceToLeftWall: Long = 2,
        distanceToHeighestObstacle: Long = 3
    ): Position2d<Long> {
        val maxHeight = getMaxHeight()

        return Position2d(distanceToLeftWall, maxHeight + distanceToHeighestObstacle)
    }

    fun addRockToObstaclesIfItCollides(rock: AoC2022Day17Rock, position: Position2d<Long>, direction: AoC2022Day17Jet): Boolean {
        val rockBlockers = rock
            .getBlockedPositions()
            .map { pos -> pos + position }
            .toList()
        val realDirection = getRealDirection(rock, position, direction)

        // Do we collide with floor or another stone?
        if (
            rockBlockers
                .map { pos -> pos + AoC2022Day17Jet.getPositionForJet(realDirection) }
                .any { pos -> pos.y < 0 || obstacles.contains(pos) }
        ) {
            obstacles += rockBlockers
            return true
        }

        return false
    }

    fun getRealDirection(rock: AoC2022Day17Rock, position: Position2d<Long>, direction: AoC2022Day17Jet): AoC2022Day17Jet {
        var realDirection = direction
        val rockBlockers = rock
            .getBlockedPositions()
            .map { pos -> pos + position }
            .toList()

        // We cannot be blown out of the tunnel
        if (
            (
                    direction == AoC2022Day17Jet.LEFT
                            && (
                            rockBlockers
                                .any { pos -> pos.x == 0L }
                                    || rockBlockers
                                .map { pos -> pos + AoC2022Day17Jet.getPositionForJet(AoC2022Day17Jet.LEFT) }
                                .any { pos -> obstacles.contains(pos) }
                            )
                    )
            || (
                    direction == AoC2022Day17Jet.RIGHT
                            && (
                            rockBlockers
                                .any { pos -> pos.x == width - 1 }
                                    || rockBlockers
                                .map { pos -> pos + AoC2022Day17Jet.getPositionForJet(AoC2022Day17Jet.RIGHT) }
                                .any { pos -> obstacles.contains(pos) }
                            )
                    )
        ) {
            realDirection = AoC2022Day17Jet.UNKNOWN
        }

        return realDirection
    }
}
