plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("com.vanniktech.maven.publish") version "0.35.0"
}

group = "io.github.frigoturcomoreno"
version = "1.0"
val artifact = "ktor_pagination"

repositories {
    mavenCentral()
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates("$group.$artifact", "ktor_pagination", version.toString())

    pom {
        name.set("Ktor Pagination")
        description.set("Kotlin pagination extension library for Exposed ORM with built-in support for all entity types")
        inceptionYear.set("2025")
        url.set("https://github.com/FrigoTurcoMoreno/KtorPagination")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("FrigoTurcoMoreno")
                name.set("Moreno Frigo Turco")
                url.set("https://github.com/FrigoTurcoMoreno")
            }
        }

        scm {
            url.set("https://github.com/FrigoTurcoMoreno/KtorPagination")
            connection.set("scm:git:git://github.com/FrigoTurcoMoreno/KtorPagination.git")
            developerConnection.set("scm:git:ssh://git@github.com/FrigoTurcoMoreno/KtorPagination.git")
        }
    }
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