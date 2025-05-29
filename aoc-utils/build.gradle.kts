dependencies {
    api(project(":aoc-utils:aoc-utils-java"))
    api(project(":aoc-utils:aoc-utils-kotlin"))
}

subprojects {
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    dependencies {
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.named<Jar>("jar") {
        manifest {
            attributes["Implementation-Title"] = project.name
            attributes["Implementation-Version"] = project.version
        }
    }
}
