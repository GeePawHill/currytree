plugins {
    kotlin("jvm") version "1.9.21" apply false
    kotlin("plugin.serialization") version "1.9.21" apply false
    java
}

tasks.withType(Test::class) {
    useJUnitPlatform()
}