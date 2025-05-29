rootProject.name = "advent-of-code-utils"

include("aoc-utils")
include("aoc-utils:aoc-utils-java")
include("aoc-utils:aoc-utils-kotlin")

dependencyResolutionManagement {
    versionCatalogs {
        create("utilityLibs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}