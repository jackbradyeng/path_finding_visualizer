import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") version "2.4.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.0"
    id("org.jetbrains.compose") version "1.9.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        getByName("desktopMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        getByName("desktopTest") {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter:5.10.0")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "path_finding_visualizer"
            packageVersion = "1.0.0"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}