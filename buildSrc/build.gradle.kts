plugins {
    `kotlin-dsl`
    kotlin("jvm") version "2.3.0"
    id("com.vanniktech.maven.publish") version "0.28.0"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.20")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.3.20")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.28.0")
}
