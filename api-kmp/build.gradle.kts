plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.squareup.wire") version "5.3.1"
}

repositories { mavenCentral() }

wire {
    kotlin {
        out = "src/commonMain/kotlin"
    }
    sourcePath {
        srcDir("src/main/proto")
        srcDir("src/test/proto")
    }
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
                api("com.squareup.okio:okio:3.17.0")
                api("com.squareup.wire:wire-runtime:5.3.1")
                api("com.squareup.wire:wire-grpc-client:5.3.1")
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
                // Validation libs (JVM)
                implementation("commons-validator:commons-validator:1.9.0")
                implementation("com.googlecode.libphonenumber:libphonenumber:8.13.50")
                implementation("com.googlecode.libphonenumber:geocoder:2.231")
                implementation("com.googlecode.libphonenumber:prefixmapper:2.231")
                implementation("com.googlecode.libphonenumber:carrier:1.221")
                implementation("com.googlecode.ez-vcard:ez-vcard:0.12.1")  // vCard (.vcf)
                implementation("com.ibm.icu:icu4j:76.1")  // ICU CLDR
                implementation("org.mnode.ical4j:ical4j:4.0.7")  // iCalendar (.ics)
                // vCard bridges (optional — compileOnly so users opt-in)
                compileOnly("org.igniterealtime.smack:smack-extensions:4.4.8")  // XEP-0054 VCard
                compileOnly("org.bedework:bw-ical4j-vcard:1.0.11")              // CardDAV vCard
                implementation("io.swagger.parser.v3:swagger-parser:2.1.39")
                implementation("com.squareup:kotlinpoet:2.2.0")
                implementation("com.cjbooms:fabrikt:26.1.0")
                implementation("io.github.serpro69:kotlin-faker:1.16.0")
                implementation("io.insert-koin:koin-core:4.0.4")
            }
        }
        val jvmTest by getting {
            kotlin.srcDir("src/test/kotlin")
            resources.srcDir("src/test/resources")
            dependencies {
                implementation("io.ktor:ktor-client-cio:3.4.2")
                // Validation libs (JVM)
                implementation("commons-validator:commons-validator:1.9.0")
                implementation("com.googlecode.libphonenumber:libphonenumber:8.13.50")
                implementation("com.googlecode.libphonenumber:geocoder:2.231")
                implementation("com.googlecode.libphonenumber:prefixmapper:2.231")
                implementation("com.googlecode.libphonenumber:carrier:1.221")
                implementation("com.googlecode.ez-vcard:ez-vcard:0.12.1")  // vCard (.vcf)
                implementation("com.ibm.icu:icu4j:76.1")  // ICU CLDR
                implementation("org.mnode.ical4j:ical4j:4.0.7")  // iCalendar (.ics)
                implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
                implementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
                implementation("com.github.tomakehurst:wiremock-jre8:2.35.2")
                implementation("io.ktor:ktor-server-core:3.4.2")
                implementation("io.ktor:ktor-server-cio:3.4.2")
                implementation("io.ktor:ktor-server-content-negotiation:3.4.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.2")
                implementation("io.ktor:ktor-server-sse:3.4.2")
                implementation("io.ktor:ktor-server-websockets:3.4.2")
                implementation("io.ktor:ktor-client-websockets:3.4.2")
                implementation("io.ktor:ktor-server-test-host:3.4.2")
            }
        }
    }

    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
}

configurations.all {
    resolutionStrategy { force("com.fasterxml.jackson.core:jackson-databind:2.14.0") }
}

// Task to run the code generator
tasks.register<JavaExec>("generate") {
    mainClass.set("kmp.apigen.MainKt")
    classpath = kotlin.jvm().compilations["main"].runtimeDependencyFiles +
        kotlin.jvm().compilations["main"].output.allOutputs
    workingDir = rootDir
}
