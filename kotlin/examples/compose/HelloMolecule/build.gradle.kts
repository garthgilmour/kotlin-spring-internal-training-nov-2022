import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("app.cash.molecule:molecule-gradle-plugin:0.4.0")
    }
}

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

apply(plugin = "app.cash.molecule")

group = "org.gdg.belfast"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("androidx.compose.compiler:compiler:1.3.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}
