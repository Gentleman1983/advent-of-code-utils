package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

interface AoC2015Day18Grid {
    fun turnOn(position: Position2d<Int>)
    fun turnOff(position: Position2d<Int>)
    fun toggle(position: Position2d<Int>)
    fun lights(): Int
}