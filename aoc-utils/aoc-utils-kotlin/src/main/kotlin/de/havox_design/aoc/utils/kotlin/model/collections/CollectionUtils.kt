package de.havox_design.aoc.utils.kotlin.model.collections

fun Collection<String>.filterNotEmpty(): Collection<String> =
    filter(String::isNotEmpty)