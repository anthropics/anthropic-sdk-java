plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("anthropic.publish")
}

repositories {
    mavenCentral()
}

kotlin {
    // JVM target
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                api("com.squareup.okio:okio:3.17.0")
                // Jackson in commonMain — enables model files to use Jackson annotations
                // and serializers in commonMain. On JVM these work with Jackson runtime.
                // On non-JVM platforms these are compile-time only (no Jackson runtime).
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
                implementation("io.ktor:ktor-server-core:3.4.2")
                implementation("io.ktor:ktor-server-cio:3.4.2")
                implementation("io.ktor:ktor-server-content-negotiation:3.4.2")
                implementation("io.ktor:ktor-server-auth:3.4.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.2")
                implementation("io.ktor:ktor-server-test-host:3.4.2")
                implementation("com.squareup.okio:okio-fakefilesystem:3.17.0")
            }
        }
        val jvmMain by getting {
            dependencies {
                // Jackson kept in jvmMain for structured outputs / backward compat
                api("com.fasterxml.jackson.core:jackson-core:2.18.2")
                api("com.fasterxml.jackson.core:jackson-databind:2.18.2")
                api("com.fasterxml.jackson.core:jackson-annotations:2.18.2")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
                implementation("com.github.victools:jsonschema-generator:4.38.0")
                implementation("com.github.victools:jsonschema-module-jackson:4.38.0")
                implementation("com.github.victools:jsonschema-module-swagger-2:4.38.0")
                api("io.swagger.core.v3:swagger-annotations:2.2.31")
                api("com.google.errorprone:error_prone_annotations:2.33.0")
                implementation("org.apache.httpcomponents.core5:httpcore5:5.2.4")
                implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
                implementation("io.ktor:ktor-client-cio:3.4.2")
            }
        }
        val jvmTest by getting {
            // Include existing src/test/kotlin as part of jvmTest until tests are migrated
            kotlin.srcDir("src/test/kotlin")
            resources.srcDir("src/test/resources")
            dependencies {
                implementation(project(":anthropic-java-client-okhttp"))
                implementation("com.github.tomakehurst:wiremock-jre8:2.35.2")
                implementation("org.assertj:assertj-core:3.27.7")
                implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
                implementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
                implementation("org.junit-pioneer:junit-pioneer:1.9.1")
                implementation("org.mockito:mockito-core:5.14.2")
                implementation("org.mockito:mockito-junit-jupiter:5.14.2")
                implementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
            }
        }
    }
}

configurations.all {
    resolutionStrategy {
        // Compile and test against a lower Jackson version to ensure we're compatible with it.
        force("com.fasterxml.jackson.core:jackson-core:2.14.0")
        force("com.fasterxml.jackson.core:jackson-databind:2.14.0")
        force("com.fasterxml.jackson.core:jackson-annotations:2.14.0")
        force("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.0")
        force("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0")
        force("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    }
}
