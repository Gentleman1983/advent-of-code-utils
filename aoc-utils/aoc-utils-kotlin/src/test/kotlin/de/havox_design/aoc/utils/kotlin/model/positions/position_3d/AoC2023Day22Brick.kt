package de.havox_design.aoc.utils.kotlin.model.positions.position_3d

data class AoC2023Day22Brick(val startPosition: Position3d<Int>, val endPosition: Position3d<Int>) {
    val fallen: AoC2023Day22Brick
        get() =
            AoC2023Day22Brick(
                Position3d(startPosition.x, startPosition.y, startPosition.z - 1),
                Position3d(endPosition.x, endPosition.y, endPosition.z - 1)
            )

    val canFall: Boolean
        get() =
            canFall(AoC2023Day22SandSlabs.getBricks())

    fun contains(position: Position3d<Int>): Boolean =
        position.x >= startPosition.x && position.x <= endPosition.x &&
                position.y >= startPosition.y && position.y <= endPosition.y &&
                position.z >= startPosition.z && position.z <= endPosition.z

    @SuppressWarnings("kotlin:S3776")
    fun intersect(other: AoC2023Day22Brick): Boolean {
        return when {
            other.startPosition.z > endPosition.z || other.endPosition.z < startPosition.z -> false
            else -> {
                for (x in other.startPosition.x..other.endPosition.x) {
                    for (y in other.startPosition.y..other.endPosition.y) {
                        for (z in other.startPosition.z..other.endPosition.z) {
                            when {
                                contains(Position3d(x, y, z)) -> return true
                            }
                        }
                    }
                }
                false
            }
        }
    }

    fun canFall(bricks: ArrayList<AoC2023Day22Brick>): Boolean {
        val f = fallen

        for (brick in bricks) {
            when {
                brick != this && brick.intersect(f) -> return false
            }
        }

        return startPosition.z > 1
    }
}
