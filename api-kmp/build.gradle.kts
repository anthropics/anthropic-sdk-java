plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.squareup.wire") version "5.3.1"
}

repositories { mavenCentral() }

wire {
    kotlin {
        // Wire-generated models go to commonMain (pure data classes).
        // gRPC services (GrpcCall, GrpcClient) go to jvmMain since
        // wire-grpc-client is JVM-only.
        out = "src/commonMain/kotlin"
        grpcServerCompatible = false
    }
    sourcePath {
        srcDir("src/main/proto")
        srcDir("src/test/proto")
    }
    // Exclude gRPC service generation from proto — gRPC client is JVM-only.
    // Generated models (message types) still land in commonMain.
    prune("io_petstore.PetstoreService")
}

kotlin {
    jvm()
    js(IR) {
        browser { testTask { useMocha() } }
        nodejs()
    }
    // Native targets
    linuxX64()
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
                api("org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.10.0")
                api("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.10.0")
                // MsgPack via third-party KMP lib (ktor ContentNegotiation format)
                implementation("com.ensarsarajcic.kotlinx:serialization-msgpack:0.5.6")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
                api("com.squareup.okio:okio:3.17.0")
                api("com.squareup.wire:wire-runtime:5.3.1")
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
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:3.4.2")
            }
        }
        val nativeMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-cio:3.4.2")
            }
        }
        val linuxX64Main by getting { dependsOn(nativeMain) }
        val macosX64Main by getting { dependsOn(nativeMain) }
        val macosArm64Main by getting { dependsOn(nativeMain) }
        val jvmMain by getting {
            dependencies {
                api("com.fasterxml.jackson.core:jackson-annotations:2.18.2")
                api("com.fasterxml.jackson.core:jackson-core:2.18.2")
                api("com.fasterxml.jackson.core:jackson-databind:2.18.2")
                api("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
                api("com.squareup.wire:wire-grpc-client:5.3.1")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
                implementation("com.github.victools:jsonschema-generator:4.38.0")
                implementation("com.github.victools:jsonschema-module-jackson:4.38.0")
                implementation("com.github.victools:jsonschema-module-swagger-2:4.38.0")
                api("io.swagger.core.v3:swagger-annotations:2.2.31")
                api("com.google.errorprone:error_prone_annotations:2.33.0")
                implementation("io.ktor:ktor-client-cio:3.4.2")
                implementation("io.ktor:ktor-client-auth:3.4.2")  // Basic/Digest/Bearer — critical for CalDAV
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
                compileOnly("org.igniterealtime.smack:smack-tcp:4.4.8")         // XMPP TCP client
                compileOnly("org.igniterealtime.smack:smack-java8:4.4.8")       // Java 8 stream features
                compileOnly("org.bedework:bw-ical4j-vcard:1.0.11")              // CardDAV vCard
                // LLM provider bridges — all compileOnly so api-kmp has zero forced transitive deps.
                // Consumers pull in only the backends they actually use.
                compileOnly("dev.langchain4j:langchain4j-core:0.36.2")           // wraps ~20 vendors
                compileOnly("dev.langchain4j:langchain4j:0.36.2")
                compileOnly("io.modelcontextprotocol:kotlin-sdk:0.5.0")          // MCP SDK (Tool/Server/Client)
                compileOnly("ai.djl:api:0.30.0")                                 // Deep Java Library core
                compileOnly("ai.djl.huggingface:tokenizers:0.30.0")              // HF tokenizers via DJL
                compileOnly("com.github.tjake:jlama-core:0.8.4")                 // Pure-Java GGUF/llama.cpp
                // Apache Camel — route generation + generic LlmComponent
                compileOnly("org.apache.camel:camel-core:4.12.0")
                compileOnly("org.apache.camel:camel-support:4.12.0")
                compileOnly("org.apache.camel:camel-rest:4.12.0")
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
                // Round-trip bridge deps runtime — need for test execution
                implementation("org.igniterealtime.smack:smack-extensions:4.4.8")
                implementation("org.igniterealtime.smack:smack-tcp:4.4.8")
                implementation("org.igniterealtime.smack:smack-java8:4.4.8")
                implementation("org.bedework:bw-ical4j-vcard:1.0.11")
                implementation("io.github.serpro69:kotlin-faker:1.16.0")  // multi-locale test fixtures
                implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
                implementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
                implementation("org.assertj:assertj-core:3.26.3")
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
