import org.gradle.kotlin.dsl.sonarResolver

dependencies {
    implementation(utilityLibs.jakarta.annotation.api)
    implementation(utilityLibs.struts2.core)

    testImplementation(project(":aoc-utils:aoc-utils-kotlin"))
    testImplementation(libs.commons.collections4)
    testImplementation(libs.commons.codec)
}

val sonarResolverTask = tasks.sonarResolver.get()
val kotlinProject = project.parent?.subprojects?.filter { proj -> proj.name.equals("aoc-utils-kotlin") }[0]
gradle.taskGraph.whenReady {
    sonarResolverTask.inputs.files(kotlinProject?.tasks?.getByName("compileKotlin"))
    sonarResolverTask.inputs.files(kotlinProject?.tasks?.getByName("compileJava"))
}
