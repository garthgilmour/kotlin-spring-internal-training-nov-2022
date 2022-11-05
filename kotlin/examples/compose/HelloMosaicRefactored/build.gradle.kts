import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.jakewharton.mosaic:mosaic-gradle-plugin:0.2.0")
        classpath("androidx.compose.compiler:compiler:1.3.0")
    }
}

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "com.jakewharton.mosaic")
apply(plugin = "com.github.johnrengelman.shadow")

group = "org.gdg.belfast"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("androidx.compose.compiler:compiler:1.3.0")
    implementation("org.jline:jline:3.21.0")
}

application {
    mainClass.set("org.gdg.belfast.mosaic.ProgramKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

