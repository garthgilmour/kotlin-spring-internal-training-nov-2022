import org.jetbrains.compose.compose

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.compose") version "0.1.0-m1-build62"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "demos.compose.desktop.calculator.v1.CalculatorKt"
    }
}