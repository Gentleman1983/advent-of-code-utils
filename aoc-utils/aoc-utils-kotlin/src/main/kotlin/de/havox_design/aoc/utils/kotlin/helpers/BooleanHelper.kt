package de.havox_design.aoc.utils.kotlin.helpers

fun Boolean.toInt() = when {
    this -> 1
    else -> 0
}
