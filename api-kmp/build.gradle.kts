plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

repositories { mavenCentral() }

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                api("com.squareup.okio:okio:3.17.0")
                api("com.squareup.wire:wire-runtime:5.3.1")
                api("com.fasterxml.jackson.core:jackson-annotations:2.18.2")
                api("com.fasterxml.jackson.core:jackson-core:2.18.2")
                api("com.fasterxml.jackson.core:jackson-databind:2.18.2")
                api("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
                implementation("io.ktor:ktor-client-core:3.4.2")
                implementation("io.ktor:ktor-client-content-negotiation:3.4.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
                implementation("com.github.victools:jsonschema-generator:4.38.0")
                implementation("com.github.victools:jsonschema-module-jackson:4.38.0")
                implementation("com.github.victools:jsonschema-module-swagger-2:4.38.0")
                api("io.swagger.core.v3:swagger-annotations:2.2.31")
                api("com.google.errorprone:error_prone_annotations:2.33.0")
                implementation("io.ktor:ktor-client-cio:3.4.2")
                implementation("io.swagger.parser.v3:swagger-parser:2.1.39")
                implementation("com.squareup:kotlinpoet:2.2.0")
            }
        }
    }
}

configurations.all {
    resolutionStrategy { force("com.fasterxml.jackson.core:jackson-databind:2.14.0") }
}

// Task to run the code generator
tasks.register<JavaExec>("generate") {
    mainClass.set("com.anthropic.apikmp.MainKt")
    classpath = kotlin.jvm().compilations["main"].runtimeDependencyFiles +
        kotlin.jvm().compilations["main"].output.allOutputs
    workingDir = rootDir
}
