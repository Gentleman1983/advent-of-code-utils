dependencies {
    implementation(utilityLibs.jakarta.annotation.api)
    implementation(utilityLibs.struts2.core)

    testImplementation(project(":aoc-utils:aoc-utils-kotlin"))
    testImplementation(utilityLibs.commons.collections4)
    testImplementation(utilityLibs.commons.codec)
}
