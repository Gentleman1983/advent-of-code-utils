dependencies {
    implementation(utilityLibs.struts2.core)

    testImplementation(project(":aoc-utils:aoc-utils-kotlin"))
    testImplementation(libs.commons.collections4)
    testImplementation(libs.commons.codec)
}
