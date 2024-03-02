import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.5.11"
    kotlin("plugin.serialization")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.0")


    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.0")
    implementation(project(":common"))
}

kotlin {
    jvmToolchain(17)
}

group = "org.currytree"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}


compose.desktop {
    application {
        mainClass = "org.currytree.maker.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "curry-experiment"
            packageVersion = "1.0.0"
        }
    }
}