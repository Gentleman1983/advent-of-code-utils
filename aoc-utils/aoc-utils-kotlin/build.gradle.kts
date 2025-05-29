plugins {
    alias(utilityLibs.plugins.kotlin.jvm)
}

dependencies {
    compileOnly(utilityLibs.junit.jupiter)

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
