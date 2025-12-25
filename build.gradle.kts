plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "dev.mft"
version = "1.0"
val artifact = "ktor_pagination"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.ktor.serialization.kotlinx.json)
    compileOnly(libs.ktor.server.content.negotiation)
    compileOnly(libs.exposed.dao)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}