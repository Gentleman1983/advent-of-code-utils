plugins {
    id("com.gradle.develocity") version("4.0.1")
}

rootProject.name = "advent-of-code-utils"

include("aoc-utils")
include("aoc-utils:aoc-utils-java")
include("aoc-utils:aoc-utils-kotlin")
