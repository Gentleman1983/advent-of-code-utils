package de.havox_design.aoc.utils.kotlin.model.coordinates.left_right_direction

data class AoC2023Day08Node(val name: String, var left: AoC2023Day08Node?, var right: AoC2023Day08Node?) : Comparable<AoC2023Day08Node> {
    fun setNode(direction: LeftRightDirection, node: AoC2023Day08Node) {
        when (direction) {
            LeftRightDirection.LEFT -> left = node
            LeftRightDirection.RIGHT -> right = node
        }
    }

    fun setNodes(left: AoC2023Day08Node, right: AoC2023Day08Node) {
        setNode(LeftRightDirection.LEFT, left)
        setNode(LeftRightDirection.RIGHT, right)
    }

    fun getNode(direction: LeftRightDirection): AoC2023Day08Node =
        when (direction) {
            LeftRightDirection.LEFT -> left!!
            LeftRightDirection.RIGHT -> right!!
        }

    override fun compareTo(other: AoC2023Day08Node): Int =
        name
            .compareTo(other.name)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (javaClass != other?.javaClass) {
            return false
        }

        other as AoC2023Day08Node

        return name == other.name
    }

    override fun hashCode(): Int =
        name
            .hashCode()
}
