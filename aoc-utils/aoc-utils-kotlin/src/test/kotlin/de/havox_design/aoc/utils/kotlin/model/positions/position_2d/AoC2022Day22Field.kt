package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

enum class AoC2022Day22Field(var symbol:String, var value: Int) {
    UNAVAILABLE("", 0),
    FREE(".", 0),
    BLOCKED("#", 0),
    UP("^", 3),
    RIGHT(">", 0),
    DOWN("v", 1),
    LEFT("<", 2);
}