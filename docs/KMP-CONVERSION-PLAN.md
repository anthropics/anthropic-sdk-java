# KMP Conversion Plan: anthropic-sdk-java → anthropic-sdk-kotlin-multiplatform

## Context

The Anthropic Java SDK is a JVM-only Kotlin project using Jackson for JSON, OkHttp for HTTP, and CompletableFuture for async. The goal is to convert `anthropic-java-core` to Kotlin Multiplatform (KMP), moving all code to `commonMain`/`commonTest` with pure Kotlin (no JVM APIs), while keeping Java examples and JVM tests unchanged. Ktor CIO client replaces OkHttp in common, and Ktor CIO server replaces WireMock for testing.

## Key Design Decisions (Confirmed)

1. **Sync API**: Suspend-only in commonMain. JVM blocking wrappers via `runBlocking` in jvmMain for backward compat.
2. **Serializers**: Plugin-generated `@Serializable` on each model class. Standard KMP approach.
3. **Implementation order**: Build system first, then code changes.

## Summary of Changes

- **Build system**: Upgrade Kotlin 2.3.20, Gradle 9.4.1, GraalVM JDK 25, add KMP + serialization plugins, restructure source sets
- **JSON**: Replace Jackson with kotlinx.serialization plugin-generated `@Serializable` (~485 model files, core infrastructure)
- **HTTP**: Replace OkHttp with Ktor CIO client in common; keep OkHttp module for JVM
- **Async**: Suspend-only API. Replace CompletableFuture with coroutines (suspend functions, Flow). JVM blocking bridge in jvmMain.
- **Tests**: Ktor CIO test server with API key auth replaces WireMock; port tests to commonTest; keep JVM tests as-is in jvmTest
- **JVM APIs**: Replace Optional, Duration, Stream, InputStream, Collections, System.getProperty with Kotlin equivalents

---

## Dependency Versions (2026-latest)

| Dependency | Current | Target |
|---|---|---|
| Kotlin | 1.9.20 | **2.3.20** |
| Gradle | 8.12 | **9.4.1** |
| GraalVM JDK | 21 | **25** (GraalVM CE 25.0.2) |
| kotlinx.serialization | N/A | **1.10.0** |
| kotlinx.coroutines | N/A | **1.10.2** |
| Ktor | N/A | **3.4.2** |
| Okio | N/A | **3.17.0** (common filesystem + I/O) |
| Jackson | 2.18.2 | removed from common (kept in jvmMain for structured outputs) |
| OkHttp | 4.12.0 | unchanged (JVM-only module) |
| LangChain4j | N/A | **1.12.2** (JVM-only integration) |
| Apache Camel | N/A | **4.12.0** (JVM-only integration) |
| Kotlin MCP SDK | N/A | **0.11.0** (KMP integration) |
| Wire (protobuf/gRPC) | N/A | **6.2.0** (KMP proto + gRPC) |
| kotlinx-io | N/A | **0.9.0** (KMP I/O supplement) |

---

## Phase 1: Build System & Toolchain Upgrade

### 1.0 Create .sdkmanrc for reproducible toolchain
Create `.sdkmanrc` at project root so `sdk env` auto-selects correct versions.

### 1.1 Install toolchain via SDKMAN!
```bash
# Install/upgrade all tools via sdkman (reads .sdkmanrc)
sdk env install
# Or manually:
sdk install java 25-graal       # GraalVM JDK 25
sdk install kotlin 2.3.20       # Kotlin 2.3.20
sdk install gradle 9.4.1        # Gradle 9.4.1
sdk install jbang               # JBang (latest)

# Set as defaults
sdk default java 25-graal
sdk default kotlin 2.3.20
sdk default gradle 9.4.1
```

### 1.2 Upgrade project build files
- `buildSrc/build.gradle.kts`: Change `kotlin("jvm") version "1.9.20"` → `"2.3.20"`
- Update `org.jetbrains.kotlin:kotlin-gradle-plugin` to `2.3.20`
- `gradle/wrapper/gradle-wrapper.properties`: Change `gradle-8.12-bin.zip` → `gradle-9.4.1-bin.zip`
- Run `./gradlew wrapper --gradle-version=9.4.1` to update wrapper
- `gradle.properties`: Add `kotlin.mpp.stability.nowarn=true`
- `anthropic.kotlin.gradle.kts`: Update `jvmToolchain` from `JavaLanguageVersion.of(21)` → `JavaLanguageVersion.of(25)`
- `anthropic.java.gradle.kts`: Update `languageVersion.set(JavaLanguageVersion.of(21))` → `JavaLanguageVersion.of(25)`
- Update Kotlin compiler options: remove `languageVersion.set(KotlinVersion.KOTLIN_1_8)` / `apiVersion` / `coreLibrariesVersion` (use 2.3 defaults)

### 1.2 Convert anthropic-java-core to KMP
**File: `anthropic-java-core/build.gradle.kts`**
```kotlin
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("anthropic.publish")
}

kotlin {
    // JVM
    jvm()

    // JS
    js(IR) {
        browser()
        nodejs()
    }

    // Wasm
    wasmJs { browser(); nodejs() }
    wasmWasi { nodejs() }

    // Apple Native
    macosX64()
    macosArm64()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    watchosArm64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosSimulatorArm64()

    // Linux Native
    linuxX64()
    linuxArm64()

    // Windows Native
    mingwX64()

    // Native build configuration
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries {
            // Shared framework for Apple targets
            if (konanTarget.family == org.jetbrains.kotlin.konan.target.Family.IOS ||
                konanTarget.family == org.jetbrains.kotlin.konan.target.Family.OSX) {
                framework {
                    baseName = "AnthropicSDK"
                    isStatic = true
                }
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                api("com.squareup.okio:okio:3.17.0")
                implementation("io.ktor:ktor-client-core:3.4.2")
                implementation("io.ktor:ktor-client-content-negotiation:3.4.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.2")
            }
        }
        commonTest {
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
        // Platform-specific Ktor engines
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:3.4.2")  // CIO for JVM
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:3.4.2")  // CIO for Native
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:3.4.2")   // JS engine for browser/node
                implementation("com.squareup.okio:okio-nodefilesystem:3.17.0") // Node.js filesystem
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:3.4.2")   // JS engine for Wasm
            }
        }
        jvmTest {
            dependencies {
                // Keep existing JVM test deps for backward compat
                implementation(project(":anthropic-java-client-okhttp"))
                implementation("com.github.tomakehurst:wiremock-jre8:2.35.2")
                implementation("org.assertj:assertj-core:3.27.7")
                implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
                implementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
                implementation("org.mockito:mockito-core:5.14.2")
                implementation("org.mockito:mockito-junit-jupiter:5.14.2")
                implementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
            }
        }
    }
}
```

### 1.3 Update buildSrc convention plugins
- `anthropic.kotlin.gradle.kts`: Update to support KMP (remove `kotlin("jvm")` for KMP modules, keep for JVM-only modules)
- Remove JVM-specific compiler options from common config; apply them only for JVM targets
- Keep `anthropic.java.gradle.kts` unchanged (for example module)

### 1.4 Source set restructuring
Move files from `src/main/kotlin/` → `src/commonMain/kotlin/` and `src/test/kotlin/` → `src/commonTest/kotlin/` + keep copies in `src/jvmTest/kotlin/`.

**Directory layout after conversion:**
```
anthropic-java-core/
  src/
    commonMain/kotlin/com/anthropic/   ← all code, pure Kotlin
    commonTest/kotlin/com/anthropic/   ← ported tests (Ktor CIO server)
    jvmMain/kotlin/com/anthropic/     ← expect/actual JVM impls (minimal)
    jvmTest/kotlin/com/anthropic/     ← existing JVM tests (WireMock, AssertJ, kept as-is)
```

### 1.5 Update settings.gradle.kts
Keep auto-discovery; no changes needed.

---

## Phase 2: Replace Jackson with kotlinx.serialization

### 2.1 Core JSON value system (`Values.kt`)
**File: `src/commonMain/kotlin/com/anthropic/core/Values.kt`**

Replace Jackson-based sealed class hierarchy with kotlinx.serialization:

- `JsonField<T>` sealed class: Remove all Jackson annotations (`@JsonCreator`, `@JsonValue`, etc.)
- `JsonValue` sealed class: Replace `JsonNode` backing with native Kotlin representation
- Use `@Serializable` with custom `KSerializer` implementations
- `JsonMissing`, `JsonNull`, `JsonBoolean`, `JsonNumber`, `JsonString`, `JsonArray`, `JsonObject`: Keep same API, remove Jackson deps
- `@ExcludeMissing` annotation: Replace with custom kotlinx.serialization logic
- `MultipartField<T>`: Keep API, remove Jackson annotations

### 2.2 Replace ObjectMappers.kt
**Delete: `ObjectMappers.kt`**
**New: `src/commonMain/kotlin/com/anthropic/core/JsonConfiguration.kt`**

Replace Jackson `JsonMapper` with kotlinx.serialization `Json` configuration:
```kotlin
val anthropicJson = Json {
    ignoreUnknownKeys = true
    isLenient = false
    encodeDefaults = false
    explicitNulls = false
    classDiscriminator = "type"
}
```

### 2.3 Replace BaseSerializer/BaseDeserializer
**Delete: `BaseSerializer.kt`, `BaseDeserializer.kt`**

Replace with kotlinx.serialization custom `KSerializer<T>` base classes for union types.

### 2.4 Convert all model files (~485 files)
Each model file follows a consistent pattern. The transformation is mechanical:

**Before (Jackson):**
```kotlin
class RateLimitError
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    @JsonProperty("message") private val message: JsonField<String>,
    @JsonProperty("type") private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
)
```

**After (kotlinx.serialization):**
```kotlin
@Serializable(with = RateLimitErrorSerializer::class)
class RateLimitError
private constructor(
    private val message: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
)
```

For each model:
- Remove `@JsonCreator`, `@JsonProperty`, `@JsonAnyGetter`, `@JsonAnySetter`, `@ExcludeMissing`
- Add `@Serializable` with custom serializer (or generate them)
- Remove `@JvmStatic` from companion `builder()` methods
- Remove `@JvmSynthetic` annotations (not needed in common)
- Remove `@JvmName` annotations
- Replace `java.util.Optional` returns with nullable `T?`
- Replace `java.util.Collections.unmodifiableMap` with `toMap()`

### 2.5 Handler layer
**Files: `core/handlers/JsonHandler.kt`, `SseHandler.kt`, `StreamHandler.kt`, `StringHandler.kt`**

- `JsonHandler.kt`: Replace `jsonMapper.readValue()` with `anthropicJson.decodeFromString()`
- `SseHandler.kt`: Replace Jackson JSON parsing with kotlinx.serialization
- `SseMessage.kt`: Replace `jacksonTypeRef<T>()` with `serializer<T>()`
- `StreamHandler.kt`: Replace `java.util.stream.Stream` with `Sequence<T>` or `Flow<T>`
- `StringHandler.kt`: Replace `response.body().readBytes()` with platform-agnostic read

### 2.6 HttpRequestBodies.kt
- Replace `jsonMapper.writeValueAsBytes()` with `anthropicJson.encodeToString()`
- Replace `JsonNode`/`ObjectNode` with kotlinx `JsonElement`/`JsonObject`
- Replace `InputStream` in multipart with `ByteArray` or `Source` (kotlinx-io)
- Replace `java.util.UUID` with `kotlin.uuid.Uuid` (Kotlin 2.0+) or simple random string

### 2.7 StructuredOutputs.kt & JsonSchemaValidator.kt
- `StructuredOutputs.kt`: Replace `victools/jsonschema-generator` (JVM-only) with expect/actual
  - commonMain: Define `expect fun extractSchema(type: KClass<*>): JsonObject`
  - jvmMain: actual implementation using victools (keep existing logic)
- `JsonSchemaValidator.kt`: Already pure logic on `JsonNode` → convert to use `JsonElement`

### 2.8 Check.kt
- Remove Jackson version compatibility check (not needed for kotlinx.serialization)
- Keep `checkRequired`, `checkKnown`, `checkLength`, etc. (pure Kotlin already)

---

## Phase 3: Replace JVM APIs with Pure Kotlin

### 3.1 java.util.Optional → Kotlin nullables
**All model files + service interfaces:**
- `fun foo(): Optional<T>` → `fun foo(): T?`
- `import kotlin.jvm.optionals.getOrNull` → remove
- Builder methods: `fun foo(foo: Optional<T>) = foo(foo.orElse(null))` → remove overload

### 3.2 java.time.Duration → kotlin.time.Duration
**Files: `Timeout.kt`, `Sleeper.kt`, `DefaultSleeper.kt`, `RequestOptions.kt`, `ClientOptions.kt`**
- `import java.time.Duration` → `import kotlin.time.Duration`
- `Duration.ofMinutes(1)` → `1.minutes`
- `Duration.ofSeconds(30)` → `30.seconds`
- `Duration.ZERO` → `Duration.ZERO`
- `duration.toMillis()` → `duration.inWholeMilliseconds`

### 3.3 java.util.stream.Stream → Sequence/Flow
**Files: `StreamResponse.kt`, `AsyncStreamResponse.kt`, `AutoPager.kt`, `AutoPagerAsync.kt`**
- `StreamResponse.stream(): Stream<T>` → `fun asSequence(): Sequence<T>` or `fun asFlow(): Flow<T>`
- Keep `stream()` as JVM-only extension for backward compatibility in jvmMain

### 3.4 InputStream/OutputStream → ByteArray / kotlinx-io
**Files: `HttpRequestBody.kt`, `HttpResponse.kt`, `HttpRequestBodies.kt`, `StreamHandler.kt`**
- `HttpRequestBody.writeTo(outputStream: OutputStream)` → `fun writeTo(sink: ByteArrayOutput)` or use `ByteArray`
- `HttpResponse.body(): InputStream` → `fun body(): ByteArray` or `fun bodyAsChannel(): ByteReadChannel`
- Consider using Ktor's `ByteReadChannel` for streaming

### 3.5 CompletableFuture → Coroutines (Suspend-Only)
**Files: All services, HttpClient, RetryingHttpClient, Sleeper, AsyncStreamResponse**

Since we chose suspend-only, the blocking/async split collapses:
- `HttpClient.execute()` and `executeAsync()` → single `suspend fun execute()`
- `MessageService.create()` (blocking) and `MessageServiceAsync.create()` (CF) → single `suspend fun create()`
- `AsyncStreamResponse<T>` → `Flow<T>`
- `StreamResponse<T>` → `Flow<T>` (same as async; callers use `flow.toList()` or `flow.collect {}`)
- `Sleeper.sleep()` and `sleepAsync()` → single `suspend fun sleep()`
- `RetryingHttpClient`: simple suspend loop with `delay()` for backoff
- `AutoPager` → `Flow`-based auto-pagination
- `PageAsync.nextPage(): CompletableFuture` → `suspend fun nextPage(): Page`

**JVM backward compatibility (in jvmMain):**
```kotlin
// Extension functions for Java callers
fun MessageService.createBlocking(params: MessageCreateParams): Message =
    runBlocking { create(params) }
fun MessageService.createAsync(params: MessageCreateParams): CompletableFuture<Message> =
    CoroutineScope(Dispatchers.IO).future { create(params) }
```

### 3.6 Collections utilities (Utils.kt)
- `Collections.unmodifiableList(toList())` → `toList()` (Kotlin lists are already read-only views)
- `Collections.unmodifiableMap(toMap())` → `toMap()`
- `Collections.emptySortedMap()` → `sortedMapOf()` or custom
- `SortedMap` → keep using Kotlin stdlib's `toSortedMap()`
- Remove `java.util.concurrent.locks.Lock` usage → use `kotlinx.coroutines.sync.Mutex`
- `contentEquals`/`contentHash`/`contentToString` → pure Kotlin already (uses `arrayOf().contentDeepEquals()`)

### 3.7 System properties / Environment (Properties.kt, AnthropicBackend.kt)
**expect/actual pattern:**
```kotlin
// commonMain
expect fun getSystemProperty(key: String): String?
expect fun getEnvironmentVariable(key: String): String?

// jvmMain
actual fun getSystemProperty(key: String): String? = System.getProperty(key)
actual fun getEnvironmentVariable(key: String): String? = System.getenv(key)
```

### 3.8 PhantomReachable.kt
- Move to jvmMain (Java Cleaner is JVM-only)
- commonMain: `expect fun closeWhenPhantomReachable(observed: Any, close: () -> Unit)`
- jvmMain: actual using `java.lang.ref.Cleaner` (existing code)
- Other targets: no-op actual

### 3.9 Thread/Timer (DefaultSleeper.kt)
- Replace `Thread.sleep()` → `delay()` (coroutine)
- Replace `Timer`/`TimerTask` → coroutine-based scheduling
- `DefaultSleeper` becomes coroutine-based

### 3.10 AutoCloseable
- Kotlin common has no `AutoCloseable` in older versions, but Kotlin 2.0+ has `kotlin.AutoCloseable`
- Use `kotlin.AutoCloseable` or define `Closeable` interface in common

### 3.11 Remove JVM annotations from common code
- Remove all `@JvmStatic`, `@JvmSynthetic`, `@JvmName`, `@JvmOverloads`, `@file:JvmName`
- For JVM backward compatibility, add `@JvmStatic` etc. only in jvmMain extensions

---

## Phase 4: HTTP Client - Ktor CIO in Common

### 4.1 Implement HttpClient using Ktor CIO
**File: `src/commonMain/kotlin/com/anthropic/core/http/KtorHttpClient.kt`**

```kotlin
class KtorHttpClient(
    private val engine: HttpClientEngine = CIO.create(),
    private val config: ClientOptions,
) : HttpClient {
    private val client = io.ktor.client.HttpClient(engine) {
        install(HttpTimeout) { ... }
        // Don't install ContentNegotiation - we handle JSON ourselves
    }

    override suspend fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        val response = client.request {
            method = request.method.toKtorMethod()
            url(request.url())
            request.headers.names().forEach { name ->
                request.headers.values(name).forEach { value ->
                    headers.append(name, value)
                }
            }
            request.body?.let { body ->
                setBody(ByteArrayContent(body.toByteArray(), ContentType.parse(body.contentType() ?: "application/json")))
            }
        }
        return KtorHttpResponse(response)
    }
}
```

### 4.2 Keep OkHttp module as JVM-only
**`anthropic-java-client-okhttp/`**: Stays as-is, depends on `anthropic-java-core` JVM target.

### 4.3 Update ClientOptions
- Default HTTP client in common: `KtorHttpClient`
- Remove Jackson `JsonMapper` field → replace with `Json` (kotlinx.serialization)
- Remove `streamHandlerExecutor: Executor` → coroutines handle this
- Replace `Clock` with `kotlinx.datetime.Clock` or simple expect/actual

---

## Phase 5: Tests - Ktor CIO Server + Port to Common

### 5.1 Create Ktor test server infrastructure
**File: `src/commonTest/kotlin/com/anthropic/TestServer.kt`**

Replace WireMock with embedded Ktor CIO server:
```kotlin
class TestServer(private val apiKey: String = "test-api-key") {
    private val server = embeddedServer(CIO, port = 0) {
        install(Authentication) {
            bearer("api-key") {
                authenticate { credential ->
                    if (credential.token == apiKey) UserIdPrincipal("test") else null
                }
            }
        }
        install(ContentNegotiation) { json(anthropicJson) }

        routing {
            authenticate("api-key") {
                post("/v1/messages") { /* mock response */ }
                post("/v1/messages/count_tokens") { /* mock response */ }
                get("/v1/models") { /* mock response */ }
                // ... other endpoints
            }
        }
    }

    fun start(): Int { server.start(); return server.resolvedConnectors().first().port }
    fun stop() { server.stop() }

    // Stub configuration methods similar to WireMock
    fun stubResponse(path: String, status: Int, body: String) { ... }
}
```

### 5.2 Port service tests to commonTest
**From: `src/test/kotlin/com/anthropic/services/` → `src/commonTest/kotlin/com/anthropic/services/`**

- Replace `@ExtendWith(TestServerExtension::class)` with common test setup
- Replace WireMock stubs with Ktor test server stubs
- Replace AssertJ assertions with kotlin.test assertions
- Replace JUnit5 annotations with kotlin.test annotations
- Use `runTest` from kotlinx-coroutines-test for async tests

### 5.3 Port model tests to commonTest
**From: `src/test/kotlin/com/anthropic/models/` → `src/commonTest/kotlin/com/anthropic/models/`**

- Replace AssertJ `assertThat(...).isEqualTo(...)` with `assertEquals(...)`
- Replace JUnit5 `@Test` with `kotlin.test.Test`
- Remove JUnit5-specific features (parameterized tests → manual loops)

### 5.4 Port core tests to commonTest
**From: `src/test/kotlin/com/anthropic/core/` → `src/commonTest/kotlin/com/anthropic/core/`**

Same transformation as model tests.

### 5.5 Convert Java Examples to Common Tests
**From: `anthropic-java-example/src/main/java/` → `anthropic-java-core/src/commonTest/kotlin/com/anthropic/example/`**

There are 39 Java example files. Each is a standalone `main()` that calls the real API. Convert each to a Kotlin test that uses the Ktor test server with mock responses instead.

**Conversion pattern (e.g., MessagesExample.java → MessagesExampleTest.kt):**
```kotlin
class MessagesExampleTest {
    @Test
    fun testMessagesCreate() = runTest {
        val server = TestServer()
        val port = server.start()
        server.stubPost("/v1/messages", 200, """
            {"id":"msg_1","type":"message","role":"assistant","content":[{"type":"text","text":"Hello!"}],"model":"claude-sonnet-4-20250514","stop_reason":"end_turn","usage":{"input_tokens":10,"output_tokens":5}}
        """)
        try {
            val client = AnthropicClient.builder()
                .apiKey("test-api-key")
                .baseUrl("http://localhost:$port")
                .build()
            
            val params = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build()
            
            val message = client.messages().create(params)
            assertEquals("assistant", message.role().toString())
            assertTrue(message.content().isNotEmpty())
        } finally {
            server.stop()
        }
    }
}
```

**Example categories and test conversion:**
| Category | Examples | Test Focus |
|---|---|---|
| Messages (sync) | MessagesExample, MessagesConversation, MessagesImage | Basic create, multi-turn, image params |
| Messages (async) | MessagesAsyncExample, MessagesConversationAsync | Suspend function calls |
| Streaming (sync) | MessagesStreamingExample, MessagesStreamingCancellation | Flow collection, cancellation |
| Streaming (async) | MessagesStreamingAsyncExample, ThinkingStreamingAsync | Flow + coroutine patterns |
| Count tokens | CountTokensExample, CountTokensAsync | Token counting endpoint |
| Models | ModelListExample, ModelListAsync | List + pagination |
| Batch | BatchExample, BatchAsync | Batch create/list |
| Structured outputs | StructuredOutputsExample, BetaStructuredOutputs* | JSON output format |
| Tool use | BetaMessagesToolsExample, BetaToolRunner* | Tool definition, invocation |
| Thinking | ThinkingExample, ThinkingAsync, ThinkingStreaming* | Extended thinking params |
| Platform-specific | BedrockMessages*, VertexMessages*, Foundry* | **Skip** (stay JVM-only, not ported) |
| MCP | MessagesMcpExample | **Skip** (MCP is external) |
| Memory | BetaMemoryToolExample | Memory tool params |

**Platform-specific examples (Bedrock, Vertex, Foundry) stay in jvmTest only** since they depend on JVM-only backend modules. Non-platform examples (27 of 39) get converted to common Kotlin tests.

### 5.6 Keep JVM tests as-is
**Keep: `src/jvmTest/kotlin/com/anthropic/`** ← copy of all existing tests, unchanged.
These continue to use WireMock, AssertJ, JUnit5, Mockito.

---

## Phase 6: Other Modules

### 6.1 anthropic-java-client-okhttp
- Stays JVM-only, no changes
- Depends on `anthropic-java-core` JVM target (`:anthropic-java-core` → automatically resolves JVM variant)

### 6.2 anthropic-java (umbrella module)
- Stays JVM-only, depends on `anthropic-java-core` + `anthropic-java-client-okhttp`
- No changes

### 6.3 anthropic-java-example
- Stays JVM-only Java, no changes
- Uses JVM target

### 6.4 anthropic-java-bedrock, -vertex, -foundry, -aws
- Stay JVM-only, depend on `anthropic-java-core` JVM target
- No changes to source code

### 6.5 anthropic-java-proguard-test
- Stays JVM-only, no changes

---

## Phase 7: expect/actual Declarations Summary

| Common (expect) | JVM (actual) | Purpose |
|---|---|---|
| `getSystemProperty(key)` | `System.getProperty(key)` | Config/env |
| `getEnvironmentVariable(key)` | `System.getenv(key)` | Config/env |
| `closeWhenPhantomReachable()` | Java Cleaner reflection | Resource cleanup |
| `getPackageVersion()` | JAR manifest lookup | User-Agent header |
| `getOsArch()`, `getOsName()`, `getOsVersion()` | System.getProperty | Platform info |
| `extractSchema(KClass<*>)` | victools jsonschema-generator | Structured outputs |
| `randomUuid()` | `UUID.randomUUID()` or `kotlin.uuid.Uuid` | Multipart boundary |

---

## Current Progress (synced with branch claude/convert-to-kmp-I9zBV)

### ✅ DONE — Build System (Phase 1)
- Gradle 8.12 → 9.4.1, Kotlin 1.9.20 → 2.3.20
- KMP multiplatform plugin on anthropic-java-core
- .sdkmanrc, convention plugins updated

### ✅ DONE — All Code in commonMain (Phases 2-8)
- **607 files in commonMain**, 3 in jvmMain, 0 in src/main
- All 2682 JVM tests pass, 0 failures

### ✅ DONE — Kotlin-Native Replacements
| JVM Type | Kotlin Replacement | Files |
|---|---|---|
| `java.time.Duration` | `kotlin.time.Duration` (.minutes, .seconds) | 13 |
| `java.util.stream.Stream` | `kotlinx.coroutines.flow.Flow` | 8 |
| `Timer`/`TimerTask` | `kotlinx.coroutines.delay()` | DefaultSleeper |
| `java.util.UUID` | `kotlin.uuid.Uuid` | 2 |
| `java.lang.AutoCloseable` | `kotlin.AutoCloseable` | 5 interfaces |
| `Objects.hash()` | `contentHash()` | all models |
| `Collections.unmodifiableMap()` | `.toMap()` | all models |
| `@JvmStatic/@JvmSynthetic/etc` | Removed | 503 files |

### ✅ DONE — New KMP-Native Files
| File | Uses |
|---|---|
| `KtorHttpClient.kt` | io.ktor.client.HttpClient — alternative to OkHttp |
| `KotlinxJsonHandler.kt` | kotlinx.serialization.Json — alternative to Jackson handler |
| `JsonConfiguration.kt` | `anthropicJson` config + JsonValue↔JsonElement bridge |
| `KtorBridge.kt` | HttpMethod/Headers/QueryParams ↔ Ktor types |
| `KmpOptional.kt` | typealias to java.util.Optional + Kotlin extensions |

### 🔲 GAPS — Remaining java.* Usage in commonMain
| Import | Count | Needed For | Gap |
|---|---|---|---|
| `java.util.Optional` | 309 | Model public API | Need KMP Optional or nullable migration |
| `java.util.function.Consumer` | 48 | Builder fluent API for Java | Need Kotlin lambda replacement |
| `CompletableFuture` | 39 | Async API surface | Need suspend/Flow migration |
| `Executor`/`ExecutorService` | 13 | Async threading | Need coroutine dispatcher |
| `java.time.OffsetDateTime` | 9 | Model date fields | Need kotlinx.datetime.Instant |
| `java.io.InputStream` | 7 | File upload models | Need okio.Source |
| `java.nio.file.Path` | 3 | File path models | Need okio.Path |
| `java.io.OutputStream` | 2 | HTTP body writing | Need okio.Sink |
| `java.io.IOException` | 2 | Stream error handling | Need AnthropicIoException |
| `java.time.Clock` | 2 | Retry timing | Need kotlinx.datetime.Clock |
| `Jackson annotations` | ~2200 | Model serialization | Need kotlinx.serialization migration |
| `Jackson core/databind` | ~15 files | JSON parsing, ObjectMappers | Need kotlinx.serialization |

### 🔲 GAPS — Custom Code Duplicating Stable KT Libs (~730 lines)
| Custom Code | Lines | Stable Lib Replacement |
|---|---|---|
| `RetryingHttpClient.kt` | 120 | Ktor HttpRequestRetry plugin |
| `HttpRequestBodies.kt` MultipartBody | 130 | Ktor MultiPartFormDataContent |
| `SseHandler.kt` SSE parser | 70 | Ktor SSE plugin |
| `StreamHandler.kt` wrappers | 45 | kotlinx.coroutines.flow.Flow |
| `AsyncStreamResponse.kt` | 70 | kotlinx.coroutines.flow.Flow |
| `AutoPagerAsync.kt` | 60 | kotlinx.coroutines.flow.Flow |
| `ObjectMappers.kt` Jackson config | 162 | kotlinx.serialization.Json |
| `BaseSerializer/Deserializer` | 40 | kotlinx.serialization.KSerializer |

### 🔲 GAPS — MCP SDK Integration
- MCP SDK dependency not yet added (`io.modelcontextprotocol:kotlin-sdk:0.11.0`)
- `AnthropicMcpToolProvider` not yet created
- `AnthropicMcpClient` not yet created
- Bridge functions exist (`JsonValue.toJsonElement()`) but MCP integration code doesn't use them yet

---

## Phase 9: MCP SDK Integration — Using Ktor/kotlinx/MCP Directly

### Research: Kotlin MCP SDK (io.modelcontextprotocol:kotlin-sdk:0.11.0)

The MCP SDK uses the same stack we want:
- `kotlinx.serialization.json.JsonElement/JsonObject` for JSON (via McpJson wrapper)
- `io.ktor.client.HttpClient` for HTTP (no engine bundled — user provides CIO/etc.)
- `kotlinx.coroutines` for async (suspend, Flow)
- KMP: JVM, Native, JS, Wasm

#### Why Direct JsonField<T> Replacement Won't Work
`JsonField<T>` has `JsonMissing` (absent vs null) + `KnownValue<T>` (typed wrapper) — 
`JsonElement` has no equivalent. 6,643 references would break.

#### Strategy: Coexist via Bridge
```
Existing code (Jackson+JsonField) ←→ Bridge functions ←→ New code (MCP SDK/kotlinx types)
```

Bridges already exist:
- `JsonValue.toJsonElement()` ↔ `JsonValue.fromJsonElement()`
- `JsonField<T>.toJsonElement()` ↔ `JsonField.fromJsonElement()`

### Context
The SDK still uses Jackson for JSON, CompletableFuture for async, and custom implementations
for HTTP retry, multipart, SSE parsing. These duplicate stable KT library functionality.
We add a Kotlin-native layer using Ktor, kotlinx.serialization, and kotlinx.coroutines directly,
while keeping the Jackson layer for backward compatibility.

### 9.1 Create KtorHttpClient Implementation

**File: `src/commonMain/kotlin/com/anthropic/core/http/KtorHttpClient.kt`**

Implement the existing `HttpClient` interface using Ktor client directly:

```kotlin
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.jvm.javaio.*

class KtorHttpClient(
    private val engine: io.ktor.client.HttpClient,
) : HttpClient {
    
    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        return runBlocking { executeAsync(request, requestOptions).get() }
    }
    
    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val future = CompletableFuture<HttpResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = engine.request {
                    method = request.method.toKtorMethod()
                    url(request.url())
                    request.headers.names().forEach { name ->
                        request.headers.values(name).forEach { value ->
                            headers.append(name, value)
                        }
                    }
                    request.body?.let { body ->
                        val bytes = ByteArrayOutputStream().also { body.writeTo(it) }.toByteArray()
                        setBody(bytes)
                        body.contentType()?.let { contentType(ContentType.parse(it)) }
                    }
                }
                future.complete(KtorHttpResponse(response))
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }
    
    override fun close() { engine.close() }
}
```

This uses Ktor's built-in:
- Content negotiation (replaces Jackson for request bodies)
- Retry plugin (replaces RetryingHttpClient)
- SSE support (replaces SseHandler)
- Multipart support (replaces custom MultipartBody)

### 9.2 Add Ktor SSE Support

**File: `src/commonMain/kotlin/com/anthropic/core/handlers/KtorSseHandler.kt`**

Use Ktor's built-in SSE support instead of custom SseState parser:

```kotlin
import io.ktor.client.plugins.sse.*

internal fun ktorSseHandler(): Handler<StreamResponse<SseMessage>> =
    object : Handler<StreamResponse<SseMessage>> {
        override fun handle(response: HttpResponse): StreamResponse<SseMessage> {
            // Use Ktor's SSE plugin for spec-compliant parsing
        }
    }
```

### 9.3 Add kotlinx.serialization JSON Handler

**File: `src/commonMain/kotlin/com/anthropic/core/handlers/KotlinxJsonHandler.kt`**

Use kotlinx.serialization instead of Jackson for JSON deserialization:

```kotlin
import kotlinx.serialization.json.Json
import com.anthropic.core.anthropicJson

internal inline fun <reified T> kotlinxJsonHandler(): Handler<T> =
    object : Handler<T> {
        override fun handle(response: HttpResponse): T {
            val body = response.body().readBytes().toString(Charsets.UTF_8)
            return anthropicJson.decodeFromString<T>(body)
        }
    }
```

### 9.4 Replace Custom MultipartBody with Ktor

Use Ktor's `MultiPartFormDataContent` instead of custom boundary/CRLF handling:

```kotlin
import io.ktor.client.request.forms.*
import io.ktor.http.*

fun buildMultipartBody(fields: Map<String, MultipartField<*>>): MultiPartFormDataContent {
    return MultiPartFormDataContent(formData {
        fields.forEach { (name, field) ->
            val value = field.value.asKnown() ?: return@forEach
            append(name, value.toString(), Headers.build {
                field.contentType.let { append(HttpHeaders.ContentType, it) }
            })
        }
    })
}
```

### 9.5 Verification

```bash
./gradlew :anthropic-java-core:compileKotlinJvm :anthropic-java-client-okhttp:compileKotlin --no-configuration-cache
SKIP_MOCK_TESTS=true ./gradlew :anthropic-java-core:jvmTest --no-configuration-cache
```

Critical files created:
- DONE: `core/http/KtorHttpClient.kt` — Ktor-based HttpClient impl
- DONE: `core/handlers/KotlinxJsonHandler.kt` — kotlinx.serialization JSON handler
- DONE: `core/JsonConfiguration.kt` — anthropicJson + bridge functions
- DONE: `core/KtorBridge.kt` — HttpMethod/Headers/QueryParams ↔ Ktor types

### 9.6 Add MCP SDK Dependency + MCP Tool Provider

**Add to build.gradle.kts commonMain:**
```kotlin
implementation("io.modelcontextprotocol:kotlin-sdk:0.11.0")
```

**New file: `src/commonMain/kotlin/com/anthropic/mcp/AnthropicMcpToolProvider.kt`**

Uses MCP SDK's `Client` to discover and invoke tools, bridging via `JsonValue.toJsonElement()`:

```kotlin
class AnthropicMcpToolProvider(private val mcpClient: io.modelcontextprotocol.sdk.Client) {
    suspend fun listTools(): List<BetaTool> {
        return mcpClient.listTools().tools.map { mcpTool ->
            BetaTool.builder()
                .name(mcpTool.name)
                .description(mcpTool.description)
                .inputSchema(JsonValue.fromJsonElement(mcpTool.inputSchema))
                .build()
        }
    }
}
```

### 9.7 What We Replace vs Keep

| Custom Code | Action | Replacement |
|---|---|---|
| KtorHttpClient.kt | DONE | io.ktor.client.HttpClient |
| KotlinxJsonHandler.kt | DONE | kotlinx.serialization.Json |
| JsonConfiguration.kt | DONE | anthropicJson + bridges |
| KtorBridge.kt | DONE | HttpMethod/Headers ↔ Ktor |
| KmpOptional.kt | DONE | typealias + extensions |
| DefaultSleeper.kt | DONE | kotlinx.coroutines.delay() |
| Jackson (ObjectMappers) | KEEP | Models depend on it (Stainless-generated) |
| JsonField<T>/JsonValue | KEEP | JsonMissing semantics, 14K references |
| BaseSerializer/Deserializer | KEEP | Model inner classes need them |

---

## Next Phase: Typealias-Based Migration Strategy

### Core Idea: Use `typealias` in commonMain to Bridge JVM Types

Instead of creating complex expect/actual or rewriting interfaces, use Kotlin `typealias` 
in commonMain to reference common abstractions, with platform-specific typealiases pointing
to the actual platform types. This keeps existing code working with minimal changes.

### Step A: Create commonMain type bridges (Compat.kt)

**File: `src/commonMain/kotlin/com/anthropic/core/Compat.kt`**
```kotlin
package com.anthropic.core

// Time
expect class PlatformDuration
expect fun durationOfMillis(millis: Long): PlatformDuration
expect fun PlatformDuration.toMillis(): Long
expect val zeroDuration: PlatformDuration

// Async  
expect class PlatformFuture<T>
expect fun <T> PlatformFuture<T>.await(): T

// I/O
expect abstract class PlatformInputStream
expect abstract class PlatformOutputStream
expect interface PlatformCloseable
```

**File: `src/jvmMain/kotlin/com/anthropic/core/CompatJvm.kt`**
```kotlin
package com.anthropic.core

actual typealias PlatformDuration = java.time.Duration
actual fun durationOfMillis(millis: Long): PlatformDuration = java.time.Duration.ofMillis(millis)
actual fun PlatformDuration.toMillis(): Long = this.toMillis()
actual val zeroDuration: PlatformDuration = java.time.Duration.ZERO

actual typealias PlatformFuture<T> = java.util.concurrent.CompletableFuture<T>
actual fun <T> PlatformFuture<T>.await(): T = this.get()

actual typealias PlatformInputStream = java.io.InputStream
actual typealias PlatformOutputStream = java.io.OutputStream
actual typealias PlatformCloseable = java.lang.AutoCloseable
```

### Step B: Migrate files using typealias bridges

| File | Change | Typealias Used |
|---|---|---|
| Timeout.kt → commonMain | `java.time.Duration` → `kotlin.time.Duration` | Direct stdlib (no typealias needed) |
| RequestOptions.kt → commonMain | `java.time.Duration` → `kotlin.time.Duration` | Direct stdlib |
| HttpRequestBody.kt → commonMain | `OutputStream` → `PlatformOutputStream` | PlatformOutputStream |
| HttpResponse.kt → commonMain | `InputStream` → `PlatformInputStream`, `Optional` → nullable | PlatformInputStream |
| HttpResponseFor.kt → commonMain | Remove `@JvmSynthetic`, `InputStream` → `PlatformInputStream` | PlatformInputStream |
| HttpClient.kt → commonMain | `CompletableFuture` → `PlatformFuture`, `AutoCloseable` → `PlatformCloseable` | PlatformFuture, PlatformCloseable |
| StreamResponse.kt → commonMain | `Stream<T>` → `Sequence<T>` | Direct stdlib |
| Sleeper.kt → commonMain | `Duration` → `kotlin.time.Duration`, `CompletableFuture` → `PlatformFuture` | PlatformFuture |
| PageAsync.kt → commonMain | `CompletableFuture` → `PlatformFuture` | PlatformFuture |
| AutoPager.kt → commonMain | `Stream` → `Sequence` | Direct stdlib |
| Properties.kt → commonMain | `System.getProperty` → expect fun | Platform.kt |

### Step C: Migrate remaining error classes

The HTTP error classes (BadRequestException, etc.) depend on Headers + JsonValue + Optional.
Headers and JsonValue are now in commonMain. Replace `Optional<T>` returns with `T?`.

### Step D: Fix 4 BetaToolRunner test failures

Root cause: Jackson deserializer not properly populating KnownValue in test mocks.
The JsonFieldDeserializer registered in ObjectMappers module may not be getting contextual
type info correctly for parameterized JsonField<T> types.

### Step E: Commit and push

---

## Implementation Order (Updated)

1. ✅ **Phase 1**: Build system (Gradle 9.4.1, Kotlin 2.3.20, KMP plugin)
2. ✅ **Phase 2a**: Values.kt, Utils.kt → commonMain (nullable API)
3. ✅ **Phase 2b**: Headers.kt, QueryParams.kt, Params.kt, HttpRequest.kt → commonMain
4. **Phase 3a**: Create Compat.kt typealias bridges + Platform.kt expect/actual
5. **Phase 3b**: Timeout.kt, RequestOptions.kt → commonMain (kotlin.time.Duration)
6. **Phase 3c**: HttpRequestBody, HttpResponse, HttpResponseFor → commonMain (PlatformInputStream/OutputStream)
7. **Phase 3d**: HttpClient, Sleeper, PageAsync → commonMain (PlatformFuture, PlatformCloseable)
8. **Phase 3e**: StreamResponse, AutoPager → commonMain (Sequence replaces Stream)
9. **Phase 3f**: Properties.kt → commonMain (expect getSystemProperty)
10. **Phase 3g**: Error classes → commonMain (Optional→nullable)
11. **Phase 4**: Fix BetaToolRunner test failures
12. **Phase 5**: Verify full build + tests, commit and push

---

## Verification

### Build
```bash
./gradlew :anthropic-java-core:build
./gradlew :anthropic-java-client-okhttp:build
./gradlew :anthropic-java:build
./gradlew :anthropic-java-example:compileJava
```

### Tests
```bash
# Common tests (Ktor CIO server)
./gradlew :anthropic-java-core:allTests

# JVM tests only (WireMock, existing)
./gradlew :anthropic-java-core:jvmTest

# Other modules
./gradlew :anthropic-java-client-okhttp:test
./gradlew :anthropic-java-bedrock:test
```

### Key Validation Points
- All 485 model classes compile in commonMain with kotlinx.serialization
- JSON round-trip: serialize → deserialize produces identical objects
- SSE streaming works with Ktor client
- Retry logic works with coroutine-based delays
- All existing JVM tests pass unchanged in jvmTest
- Common tests pass using Ktor CIO test server with API key auth
- Java example code compiles and runs against JVM target

---

## Phase 8: Integration Modules

### 8.1 LangChain4j Integration (JVM-only, new module)
**New module: `anthropic-java-langchain4j/`**

Provides a LangChain4j `ChatLanguageModel` + `StreamingChatLanguageModel` backed by the Anthropic SDK.

```kotlin
// build.gradle.kts
plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}
dependencies {
    api(project(":anthropic-java-core"))
    api("dev.langchain4j:langchain4j-core:1.12.2")
    testImplementation(kotlin("test"))
}
```

Key classes:
- `AnthropicChatModel : ChatLanguageModel` — wraps `MessageService.create()`
- `AnthropicStreamingChatModel : StreamingChatLanguageModel` — wraps `createStreaming()`
- `AnthropicTokenizer : Tokenizer` — wraps `countTokens()`
- Maps LangChain4j `ChatMessage`, `ToolSpecification`, `AiMessage` ↔ Anthropic SDK types

### 8.2 Apache Camel Integration (JVM-only, new module)
**New module: `anthropic-java-camel/`**

Provides a Camel component (`anthropic:`) for using Claude in Camel routes.

```kotlin
// build.gradle.kts
plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}
dependencies {
    api(project(":anthropic-java-core"))
    api("org.apache.camel:camel-core:4.12.0")
    testImplementation(kotlin("test"))
    testImplementation("org.apache.camel:camel-test-junit5:4.12.0")
}
```

Key classes:
- `AnthropicComponent : DefaultComponent` — Camel component entry point
- `AnthropicEndpoint : DefaultEndpoint` — configures model, maxTokens, etc.
- `AnthropicProducer : DefaultProducer` — sends messages, handles streaming
- URI pattern: `anthropic:messages?model=claude-sonnet-4-6&maxTokens=2048`

### 8.3 Kotlin MCP SDK Integration (KMP, new module)
**New module: `anthropic-kotlin-mcp/`**

Provides MCP client/server integration with the Anthropic SDK, using the official Kotlin MCP SDK (which is already KMP).

```kotlin
// build.gradle.kts
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("anthropic.publish")
}
kotlin {
    jvm()
    sourceSets {
        commonMain {
            dependencies {
                api(project(":anthropic-java-core"))
                api("io.modelcontextprotocol:kotlin-sdk:0.11.0")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
            }
        }
    }
}
```

Key classes:
- `AnthropicMcpToolProvider` — converts MCP `Tool` definitions to Anthropic `BetaTool` params
- `AnthropicMcpClient` — MCP client that uses Anthropic's tool use to invoke MCP tools
- `McpToolResultAdapter` — maps MCP tool results ↔ Anthropic `BetaToolResultBlockParam`
- Enables using MCP servers as tool providers for Claude conversations

### 8.4 Low-Level Design: LangChain4j Integration

**Interface mapping (LangChain4j → Anthropic SDK):**

```kotlin
// AnthropicChatModel.kt
class AnthropicChatModel(
    private val client: AnthropicClient,
    private val defaultModel: Model = Model.CLAUDE_SONNET_4_6,
    private val defaultMaxTokens: Long = 4096,
) : ChatLanguageModel {

    override fun chat(request: ChatRequest): ChatResponse {
        val params = MessageCreateParams.builder()
            .model(request.model()?.let { Model.of(it) } ?: defaultModel)
            .maxTokens(request.maxTokens()?.toLong() ?: defaultMaxTokens)
            .apply {
                request.messages().forEach { msg ->
                    when (msg) {
                        is UserMessage -> addUserMessage(msg.singleText())
                        is AiMessage -> addAssistantMessage(msg.text())
                        is SystemMessage -> system(msg.text())
                    }
                }
                // Map LangChain4j ToolSpecification → Anthropic Tool
                request.toolSpecifications()?.forEach { spec ->
                    addTool(Tool.builder()
                        .name(spec.name())
                        .description(spec.description())
                        .inputSchema(mapJsonSchema(spec.parameters()))
                        .build())
                }
            }
            .build()

        val message = runBlocking { client.messages().create(params) }
        return mapToLangChainResponse(message)
    }
}

// AnthropicStreamingChatModel.kt
class AnthropicStreamingChatModel(
    private val client: AnthropicClient,
    private val defaultModel: Model = Model.CLAUDE_SONNET_4_6,
) : StreamingChatLanguageModel {

    override fun chat(request: ChatRequest, handler: StreamingChatResponseHandler) {
        // Uses client.messages().createStreaming() → collect Flow → call handler.onPartialResponse/onComplete
    }
}

// AnthropicTokenizer.kt
class AnthropicTokenizer(private val client: AnthropicClient) : Tokenizer {
    override fun estimateTokenCountInText(text: String): Int {
        val params = MessageCountTokensParams.builder()
            .model(Model.CLAUDE_SONNET_4_6)
            .addUserMessage(text)
            .build()
        return runBlocking { client.messages().countTokens(params) }.inputTokens().toInt()
    }
}
```

**Type mappings:**
| LangChain4j | Anthropic SDK |
|---|---|
| `UserMessage` | `MessageParam.ofUserMessage()` |
| `AiMessage` | `MessageParam.ofAssistantMessage()` |
| `SystemMessage` | `MessageCreateParams.system()` |
| `ToolSpecification` | `Tool` |
| `ToolExecutionRequest` | `ToolUseBlock` |
| `ToolExecutionResult` | `ToolResultBlockParam` |
| `AiMessage.text()` | `ContentBlock.text().text()` |
| `TokenUsage` | `Usage` (inputTokens, outputTokens) |
| `FinishReason.STOP` | `StopReason.END_TURN` |
| `FinishReason.TOOL_EXECUTION` | `StopReason.TOOL_USE` |
| `FinishReason.LENGTH` | `StopReason.MAX_TOKENS` |

### 8.5 Low-Level Design: Apache Camel Integration

**Component hierarchy:**

```kotlin
// AnthropicComponent.kt
class AnthropicComponent : DefaultComponent() {
    var apiKey: String? = null
    var defaultModel: String = "claude-sonnet-4-6"

    override fun createEndpoint(uri: String, remaining: String, parameters: Map<String, Any>): Endpoint {
        val endpoint = AnthropicEndpoint(uri, this)
        setProperties(endpoint, parameters)
        return endpoint
    }
}

// AnthropicEndpoint.kt  
// URI: anthropic:messages?model=claude-sonnet-4-6&maxTokens=2048&streaming=true
class AnthropicEndpoint(
    uri: String,
    component: AnthropicComponent,
) : DefaultEndpoint(uri, component) {
    var model: String = component.defaultModel
    var maxTokens: Long = 4096
    var streaming: Boolean = false
    var systemPrompt: String? = null

    override fun createProducer(): Producer = AnthropicProducer(this)
    override fun createConsumer(processor: Processor): Consumer =
        throw UnsupportedOperationException("anthropic: endpoint does not support consumers")
}

// AnthropicProducer.kt
class AnthropicProducer(private val endpoint: AnthropicEndpoint) : DefaultProducer(endpoint) {
    private lateinit var client: AnthropicClient

    override fun doStart() {
        client = AnthropicClient.builder()
            .apiKey(endpoint.component().apiKey ?: getEnvironmentVariable("ANTHROPIC_API_KEY"))
            .build()
    }

    override fun process(exchange: Exchange) {
        val userMessage = exchange.getIn().getBody(String::class.java)
        val params = MessageCreateParams.builder()
            .model(Model.of(endpoint.model))
            .maxTokens(endpoint.maxTokens)
            .addUserMessage(userMessage)
            .apply { endpoint.systemPrompt?.let { system(it) } }
            .build()

        if (endpoint.streaming) {
            // Collect Flow<RawMessageStreamEvent>, set streaming body
            val events = runBlocking { client.messages().createStreaming(params).toList() }
            exchange.getIn().body = events
        } else {
            val message = runBlocking { client.messages().create(params) }
            exchange.getIn().body = message.content()
                .mapNotNull { it.text()?.text() }
                .joinToString("")
            exchange.getIn().setHeader("AnthropicModel", message.model())
            exchange.getIn().setHeader("AnthropicStopReason", message.stopReason().toString())
            exchange.getIn().setHeader("AnthropicInputTokens", message.usage().inputTokens())
            exchange.getIn().setHeader("AnthropicOutputTokens", message.usage().outputTokens())
        }
    }
}
```

**Camel route example:**
```kotlin
from("direct:chat")
    .to("anthropic:messages?model=claude-sonnet-4-6&maxTokens=2048")
    .log("Claude response: \${body}")

from("direct:stream")
    .to("anthropic:messages?model=claude-sonnet-4-6&streaming=true")
    .split(body())
    .log("Chunk: \${body}")
```

### 8.6 Low-Level Design: Kotlin MCP SDK Integration

**Interface mapping (MCP SDK → Anthropic SDK):**

```kotlin
// AnthropicMcpToolProvider.kt - converts MCP tools to Anthropic tool definitions
class AnthropicMcpToolProvider(private val mcpClient: Client) {

    /**
     * Lists all tools from the MCP server and converts them to Anthropic BetaTool definitions.
     */
    suspend fun listTools(): List<BetaTool> {
        val mcpTools = mcpClient.listTools()
        return mcpTools.tools.map { mcpTool ->
            BetaTool.builder()
                .name(mcpTool.name)
                .apply { mcpTool.description?.let { description(it) } }
                .inputSchema(JsonValue.from(mcpTool.inputSchema))  // MCP JSON Schema → Anthropic JsonValue
                .build()
        }
    }

    /**
     * Invokes an MCP tool with arguments from an Anthropic tool use block.
     */
    suspend fun invokeTool(toolUseBlock: BetaToolUseBlock): BetaToolResultBlockParam {
        val result = mcpClient.callTool(
            name = toolUseBlock.name(),
            arguments = toolUseBlock._input().asObject()?.mapValues { (_, v) ->
                // Convert Anthropic JsonValue → kotlinx.serialization JsonElement for MCP
                v.toJsonElement()
            } ?: emptyMap()
        )

        return BetaToolResultBlockParam.builder()
            .toolUseId(toolUseBlock.id())
            .apply {
                if (result.isError == true) {
                    isError(true)
                }
                // Map MCP content → Anthropic content
                val textContent = result.content
                    .filterIsInstance<TextContent>()
                    .joinToString("\n") { it.text }
                content(textContent)
            }
            .build()
    }
}

// AnthropicMcpClient.kt - orchestrates Claude + MCP tools in a conversation loop
class AnthropicMcpClient(
    private val anthropicClient: AnthropicClient,
    private val mcpToolProvider: AnthropicMcpToolProvider,
    private val model: Model = Model.CLAUDE_SONNET_4_6,
    private val maxTokens: Long = 4096,
) {
    /**
     * Runs a conversation loop: send message → if tool_use → invoke MCP tool → send result → repeat
     */
    suspend fun chat(userMessage: String, systemPrompt: String? = null): String {
        val tools = mcpToolProvider.listTools()
        val messages = mutableListOf<MessageParam>()
        messages.add(MessageParam.ofUserMessage(userMessage))

        while (true) {
            val params = MessageCreateParams.builder()
                .model(model)
                .maxTokens(maxTokens)
                .apply {
                    systemPrompt?.let { system(it) }
                    tools.forEach { addTool(it) }
                    messages.forEach { addMessage(it) }
                }
                .build()

            val response = anthropicClient.beta().messages().create(params)

            // Check if Claude wants to use tools
            val toolUseBlocks = response.content().mapNotNull { it.toolUse() }
            if (toolUseBlocks.isEmpty() || response.stopReason() != BetaStopReason.TOOL_USE) {
                // Final text response
                return response.content()
                    .mapNotNull { it.text()?.text() }
                    .joinToString("")
            }

            // Add assistant response with tool use
            messages.add(MessageParam.ofAssistantMessage(
                response.content().map { /* map to content block params */ }
            ))

            // Invoke each tool and add results
            val toolResults = toolUseBlocks.map { mcpToolProvider.invokeTool(it) }
            messages.add(MessageParam.ofUserMessage(
                toolResults.map { BetaContentBlockParam.ofToolResult(it) }
            ))
        }
    }
}
```

**Type mappings:**
| MCP SDK (io.modelcontextprotocol) | Anthropic SDK |
|---|---|
| `Tool` (name, description, inputSchema) | `BetaTool` |
| `Tool.inputSchema` (JsonObject) | `BetaTool.inputSchema` (JsonValue) |
| `CallToolResult` | `BetaToolResultBlockParam` |
| `TextContent` | `BetaToolResultBlockParam.content(String)` |
| `ImageContent` | `BetaToolResultBlockParam` with base64 image |
| `ResourceContent` | text extraction → `BetaToolResultBlockParam` |
| `Client.listTools()` | used to populate `MessageCreateParams.addTool()` |
| `Client.callTool()` | invoked when Claude returns `StopReason.TOOL_USE` |

### 8.7 Low-Level Design: Protobuf/gRPC Integration (KMP via Wire)

Uses Square Wire for KMP-compatible protobuf/gRPC (same ecosystem as Okio/Ktor).

**New module: `anthropic-kotlin-proto/`**

```kotlin
// build.gradle.kts
plugins {
    kotlin("multiplatform")
    id("com.squareup.wire") version "6.2.0"
    id("anthropic.publish")
}

wire {
    kotlin { out = "src/commonMain/kotlin" }
    sourcePath { srcDir("src/commonMain/proto") }
}

kotlin {
    jvm()
    macosArm64(); macosX64(); linuxX64(); mingwX64()
    js(IR) { browser(); nodejs() }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":anthropic-java-core"))
                api("com.squareup.wire:wire-runtime:6.2.0")
                api("com.squareup.wire:wire-grpc-client:6.2.0")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.squareup.wire:wire-grpc-server-generator:6.2.0")
            }
        }
    }
}
```

**Proto definitions:**
```protobuf
// src/commonMain/proto/anthropic/v1/messages.proto
syntax = "proto3";
package anthropic.v1;

option java_package = "com.anthropic.proto.v1";

service AnthropicService {
  rpc CreateMessage (CreateMessageRequest) returns (CreateMessageResponse);
  rpc CreateMessageStream (CreateMessageRequest) returns (stream MessageStreamEvent);
  rpc CountTokens (CountTokensRequest) returns (CountTokensResponse);
  rpc ListModels (ListModelsRequest) returns (ListModelsResponse);
}

message CreateMessageRequest {
  string model = 1;
  int64 max_tokens = 2;
  repeated MessageParam messages = 3;
  string system = 4;
  repeated Tool tools = 5;
}

message CreateMessageResponse {
  string id = 1;
  string type = 2;
  string role = 3;
  repeated ContentBlock content = 4;
  string model = 5;
  string stop_reason = 6;
  Usage usage = 7;
}

message MessageStreamEvent {
  string type = 1;
  oneof event {
    MessageStart message_start = 2;
    ContentBlockStart content_block_start = 3;
    ContentBlockDelta content_block_delta = 4;
    ContentBlockStop content_block_stop = 5;
    MessageDelta message_delta = 6;
  }
}
// ... additional messages for Tool, Usage, ContentBlock, etc.
```

**gRPC client adapter:**
```kotlin
// AnthropicGrpcClient.kt
class AnthropicGrpcClient(
    private val grpcClient: GrpcClient,  // Wire GrpcClient
) {
    private val service = grpcClient.create(AnthropicServiceClient::class)

    suspend fun createMessage(params: MessageCreateParams): Message {
        val request = params.toProto()  // SDK model → proto
        val response = service.CreateMessage().execute(request)
        return response.toSdkModel()     // proto → SDK model
    }

    fun createMessageStream(params: MessageCreateParams): Flow<RawMessageStreamEvent> {
        val request = params.toProto()
        return service.CreateMessageStream().execute(request)
            .map { it.toSdkModel() }
    }
}

// Extension functions for bidirectional mapping
internal fun MessageCreateParams.toProto(): CreateMessageRequest = CreateMessageRequest(
    model = model().toString(),
    max_tokens = maxTokens(),
    messages = messages().map { it.toProto() },
    // ...
)

internal fun CreateMessageResponse.toSdkModel(): Message = Message.builder()
    .id(id)
    .model(Model.of(model))
    .content(content.map { it.toSdkModel() })
    .usage(usage.toSdkModel())
    .build()
```

### 8.8 Low-Level Design: MessagePack Content Negotiation

Adds MessagePack as an alternative serialization format alongside JSON, using Ktor content negotiation.

**New file: `src/commonMain/kotlin/com/anthropic/core/http/MessagePackSupport.kt`**

```kotlin
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.msgpack.MsgPack

/**
 * Content type for MessagePack: application/msgpack or application/x-msgpack
 */
val ContentType.Application.MsgPack: ContentType
    get() = ContentType("application", "msgpack")

/**
 * Ktor ContentNegotiation converter for MessagePack.
 */
@OptIn(ExperimentalSerializationApi::class)
class MessagePackConverter(
    private val msgPack: MsgPack = MsgPack { }
) : ContentConverter {

    override suspend fun serialize(
        contentType: ContentType,
        charset: Charset,
        typeInfo: TypeInfo,
        value: Any?
    ): OutgoingContent {
        val serializer = msgPack.serializersModule.serializer(typeInfo.type)
        val bytes = msgPack.encodeToByteArray(serializer, value)
        return ByteArrayContent(bytes, contentType)
    }

    override suspend fun deserialize(
        charset: Charset,
        typeInfo: TypeInfo,
        content: ByteReadChannel
    ): Any? {
        val bytes = content.toByteArray()
        val serializer = msgPack.serializersModule.serializer(typeInfo.type)
        return msgPack.decodeFromByteArray(serializer, bytes)
    }
}

/**
 * Install MessagePack content negotiation on the Ktor client.
 */
fun ContentNegotiation.Config.msgpack(
    contentType: ContentType = ContentType.Application.MsgPack,
    block: MsgPack.() -> Unit = {}
) {
    register(contentType, MessagePackConverter(MsgPack(builderAction = block)))
}
```

**Usage in KtorHttpClient:**
```kotlin
class KtorHttpClient(...) : HttpClient {
    private val client = io.ktor.client.HttpClient(engine) {
        install(ContentNegotiation) {
            json(anthropicJson)        // JSON (default)
            msgpack()                  // MessagePack (opt-in via Accept header)
        }
    }
}
```

**Content negotiation on request:**
```kotlin
// Client can request MessagePack responses via Accept header
val params = MessageCreateParams.builder()
    .model(Model.CLAUDE_SONNET_4_6)
    .maxTokens(2048)
    .addUserMessage("Hello")
    .putAdditionalHeader("Accept", "application/msgpack")  // opt-in
    .build()
```

**Dependencies (add to commonMain):**
```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-serialization-msgpack:0.5.6")
```

### 8.9 Common Annotations Module

Shared annotations used across the SDK for API exposure, validation, and documentation.

**New file: `src/commonMain/kotlin/com/anthropic/core/annotations/Annotations.kt`**

```kotlin
package com.anthropic.core.annotations

/** Marks an API as part of the public SDK surface. */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class AnthropicApi

/** Marks an API as internal SDK implementation detail. */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class InternalAnthropicApi

/** Marks an API as beta/experimental. */
@RequiresOptIn(
    message = "This API is experimental and may change without notice.",
    level = RequiresOptIn.Level.WARNING
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class ExperimentalAnthropicApi

/** Replaces @MustBeClosed from error_prone (JVM-only). */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class MustBeClosed

/** Replaces @ExcludeMissing — signals serializer to skip JsonMissing values. */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class ExcludeMissing

/** Marks a proto/gRPC service definition. */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class ProtoService(val service: String)

/** Marks a field for MessagePack serialization with a specific key. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class MsgPackKey(val key: Int)
```

### 8.10 Update settings.gradle.kts
Add new modules to auto-discovery (they already match `anthropic-java*` or add `anthropic-kotlin*` pattern):
```kotlin
val projectNames = rootDir.listFiles()
    ?.asSequence()
    .orEmpty()
    .filter { file ->
        file.isDirectory &&
        (file.name.startsWith("anthropic-java") || file.name.startsWith("anthropic-kotlin")) &&
        file.listFiles()?.asSequence().orEmpty().any { it.name == "build.gradle.kts" }
    }
    .map { it.name }
    .toList()
```

---

## Low-Level Design Review: Annotations, Folders & Serialization

### A. Annotation Inventory & Migration Status

#### Jackson Annotations — ✅ KEPT in commonMain (jackson-annotations in commonMain deps)
| Annotation | Count | Status |
|---|---|---|
| `@JsonProperty("name")` | ~3,217 | ✅ KEPT — needed by Jackson at runtime on JVM |
| `@JsonCreator` | ~924 | ✅ KEPT — needed for model deserialization |
| `@JsonAnyGetter/@JsonAnySetter` | ~825 | ✅ KEPT — additionalProperties support |
| `@JsonValue` | ~100 | ✅ RESTORED — on KnownValue, JsonBoolean, etc. for correct serialization |
| `@JsonDeserialize/@JsonSerialize` | ~186 | ✅ KEPT — union type serialization |
| `@ExcludeMissing` | ~5 | ✅ KEPT — common annotation, Jackson filter via ObjectMappers |
| `@JsonTypeName/@JsonClassDescription` | ~few | ✅ KEPT in jvmMain for structured outputs |

**Note:** jackson-annotations + jackson-core + jackson-databind + jackson-module-kotlin are all
commonMain dependencies. This allows model files to compile in commonMain while using Jackson 
annotations. On JVM these annotations are processed by Jackson at runtime. On non-JVM platforms 
they're compile-time only (no Jackson runtime).

#### JVM Annotations — ✅ ALL REMOVED (503 files modified)
| Annotation | Original Count | Status |
|---|---|---|
| `@JvmStatic` | ~1,090 | ✅ REMOVED — `fun builder()` works without it |
| `@JvmSynthetic` | ~1,184 | ✅ REMOVED — `internal` visibility covers this |
| `@JvmName` | ~25 | ✅ REMOVED |
| `@JvmOverloads` | ~12 | ✅ REMOVED — default params |
| `@JvmField` | ~367 | ✅ REMOVED — Kotlin `val` works |
| `@file:JvmName(...)` | ~10 | ✅ REMOVED |

#### JVM Stdlib Replacements — Status
| Original | Replacement | Status |
|---|---|---|
| `java.time.Duration` | `kotlin.time.Duration` | ✅ DONE (13 files) |
| `java.util.stream.Stream` | `kotlinx.coroutines.flow.Flow` | ✅ DONE (8 files) |
| `java.util.UUID` | `kotlin.uuid.Uuid` | ✅ DONE (2 files) |
| `java.lang.AutoCloseable` | `kotlin.AutoCloseable` | ✅ DONE (5 interfaces) |
| `Objects.hash()` | `contentHash()` | ✅ DONE (all models) |
| `Collections.unmodifiableMap()` | `.toMap()` | ✅ DONE (all models) |
| `Timer`/`TimerTask` | `kotlinx.coroutines.delay()` | ✅ DONE (DefaultSleeper) |
| `java.util.Optional` | KmpOptional typealias + extensions | ✅ DONE (typealias + 309 files still using java.util.Optional) |
| `java.time.OffsetDateTime` | kotlinx.datetime.Instant | 🔲 GAP (9 files) |
| `java.io.InputStream` | okio.Source | 🔲 GAP (7 files) |
| `java.io.OutputStream` | okio.Sink | 🔲 GAP (2 files) |
| `java.nio.file.Path` | okio.Path | 🔲 GAP (3 files) |
| `CompletableFuture` | suspend/Flow | 🔲 GAP (39 files) |
| `Executor`/`ExecutorService` | CoroutineDispatcher | 🔲 GAP (13 files) |
| `java.util.function.Consumer` | Kotlin `(T) -> Unit` | 🔲 GAP (48 files) |

#### Custom Code Duplicating Stable KT Libs — Status
| Custom Code | Lines | Replacement | Status |
|---|---|---|---|
| `KtorHttpClient.kt` | NEW | io.ktor.client.HttpClient | ✅ CREATED |
| `KotlinxJsonHandler.kt` | NEW | kotlinx.serialization.Json | ✅ CREATED |
| `JsonConfiguration.kt` | NEW | anthropicJson + bridges | ✅ CREATED |
| `KtorBridge.kt` | NEW | SDK↔Ktor type conversions | ✅ CREATED |
| `RetryingHttpClient.kt` | 120 | Ktor HttpRequestRetry | 🔲 GAP |
| `HttpRequestBodies.kt` MultipartBody | 130 | Ktor MultiPartFormDataContent | 🔲 GAP |
| `SseHandler.kt` SSE parser | 70 | Ktor SSE plugin | 🔲 GAP |
| `StreamHandler.kt` wrappers | 45 | Flow catch/transform | 🔲 GAP |
| `AsyncStreamResponse.kt` | 70 | Flow | 🔲 GAP |
| `AutoPagerAsync.kt` | 60 | Flow | 🔲 GAP |
| `ObjectMappers.kt` Jackson | 162 | kotlinx.serialization.Json | 🔲 GAP (blocked by model Jackson deps) |
| `BaseSerializer/Deserializer` | 40 | KSerializer | 🔲 GAP (blocked by model inner classes) |

#### MCP SDK Integration — Status
| Item | Status |
|---|---|
| MCP SDK dependency | 🔲 NOT ADDED (`io.modelcontextprotocol:kotlin-sdk:0.11.0`) |
| AnthropicMcpToolProvider | 🔲 NOT CREATED |
| AnthropicMcpClient (tool loop) | 🔲 NOT CREATED |
| Bridge: MCP Tool → Anthropic BetaTool | 🔲 NOT CREATED (bridge functions exist) |

### B. KMP Target Folder Structure (Final)

```
anthropic-java-core/
  src/
    commonMain/
      kotlin/
        com/anthropic/
          client/                    ← AnthropicClient, AnthropicClientAsync interfaces + impls
          backends/                  ← Backend interface, AnthropicBackend
          core/
            Values.kt               ← JsonField, JsonValue hierarchy (pure Kotlin)
            JsonConfiguration.kt    ← kotlinx.serialization Json config (replaces ObjectMappers)
            Utils.kt                ← Collection helpers (pure Kotlin)
            Check.kt                ← Validation helpers (remove Jackson check)
            Timeout.kt              ← kotlin.time.Duration based
            Sleeper.kt              ← suspend-based interface
            DefaultSleeper.kt       ← coroutine delay-based
            ClientOptions.kt        ← No Jackson, no Executor, no Clock JVM types
            RequestOptions.kt       ← kotlin.time.Duration based
            Params.kt               ← Pure interface (already common-ready)
            PrepareRequest.kt       ← Suspend version (no CompletableFuture)
            Page.kt                 ← Already pure (no changes)
            PageAsync.kt            ← suspend fun nextPage() (was CompletableFuture)
            AutoPager.kt            ← Sequence-based (remove java.util.stream)
            AutoPagerAsync.kt       ← Flow-based (replace CompletableFuture/Executor)
            Platform.kt             ← expect declarations (see below)
            handlers/
              JsonHandler.kt        ← kotlinx.serialization decodeFromString
              SseHandler.kt         ← kotlinx.serialization
              StreamHandler.kt      ← Sequence-based (remove java.util.stream)
              StringHandler.kt      ← ByteArray-based (remove InputStream)
              ErrorHandler.kt       ← No changes needed
            http/
              HttpClient.kt         ← suspend fun execute() (no CompletableFuture)
              HttpRequest.kt        ← Pure Kotlin (already mostly common-ready)
              HttpResponse.kt       ← body(): ByteArray (was InputStream)
              HttpResponseFor.kt    ← No changes
              HttpMethod.kt         ← Pure enum (already common-ready)
              HttpRequestBody.kt    ← toByteArray() (was OutputStream)
              HttpRequestBodies.kt  ← kotlinx.serialization + ByteArray
              Headers.kt            ← TreeMap → sortedMapOf (pure Kotlin)
              QueryParams.kt        ← Already mostly pure
              StreamResponse.kt     ← asSequence() (was stream(): Stream<T>)
              AsyncStreamResponse.kt ← Flow-based (replace CompletableFuture)
              SseMessage.kt         ← kotlinx.serialization (was Jackson)
              RetryingHttpClient.kt ← coroutine-based retry (was CompletableFuture)
              KtorHttpClient.kt     ← NEW: Ktor CIO client impl
              KtorHttpResponse.kt   ← NEW: Ktor response wrapper
          errors/                   ← All error classes (remove @JvmOverloads)
          helpers/                  ← MessageAccumulator, etc.
          models/                   ← ALL 485 model files (@Serializable, no Jackson)
            messages/
            beta/
            completions/
            models/
          services/                 ← ALL service interfaces + impls (suspend, no CompletableFuture)
            blocking/
            async/

    commonTest/
      kotlin/
        com/anthropic/
          TestServer.kt             ← NEW: Ktor CIO server with API key auth
          core/                     ← Ported core tests (kotlin.test)
          models/                   ← Ported model tests (kotlin.test)
          services/                 ← Ported service tests (Ktor server stubs)
          helpers/                  ← Ported helper tests
          backends/                 ← Ported backend tests

    jvmMain/
      kotlin/
        com/anthropic/
          core/
            Platform.jvm.kt        ← actual fun getSystemProperty, getEnvironmentVariable, etc.
            PhantomReachable.kt     ← actual closeWhenPhantomReachable (Java Cleaner)
            StructuredOutputs.kt    ← actual extractSchema (victools jsonschema-generator)
            PhantomReachableExecutorService.kt ← JVM-only (keep as-is)
            PhantomReachableSleeper.kt ← JVM-only (keep as-is)
            JsonSchemaLocalValidation.kt ← JVM-only
            http/
              PhantomReachableClosingStreamResponse.kt  ← JVM-only wrapper
              PhantomReachableClosingHttpClient.kt       ← JVM-only wrapper
              PhantomReachableClosingAsyncStreamResponse.kt ← JVM-only wrapper

    jsMain/
      kotlin/
        com/anthropic/core/
          Platform.js.kt           ← actual using js("process.env"), navigator.platform, etc.

    wasmJsMain/                    ← delegates to jsMain via dependsOn or duplicates
      kotlin/
        com/anthropic/core/
          Platform.wasmJs.kt

    nativeMain/                    ← shared by all native targets (linux, macos, ios, mingw, etc.)
      kotlin/
        com/anthropic/core/
          Platform.native.kt      ← actual using platform.posix (getenv, uname, etc.)

    appleMain/                     ← shared by macOS, iOS, watchOS, tvOS
      kotlin/
        com/anthropic/core/
          Platform.apple.kt       ← actual using Foundation (NSProcessInfo, etc.)

    linuxMain/                     ← shared by linuxX64, linuxArm64
    mingwMain/                     ← Windows native

    jvmTest/
      kotlin/
        com/anthropic/              ← ALL existing tests UNCHANGED
          TestServerExtension.kt    ← WireMock-based (kept as-is)
          core/
          models/
          services/
          helpers/
          backends/
```

### C. expect/actual Declarations (Platform.kt)

**File: `src/commonMain/kotlin/com/anthropic/core/Platform.kt`**
```kotlin
package com.anthropic.core

// Environment access
internal expect fun getSystemProperty(key: String): String?
internal expect fun getEnvironmentVariable(key: String): String?

// Platform info for User-Agent headers
internal expect fun platformOsArch(): String
internal expect fun platformOsName(): String
internal expect fun platformOsVersion(): String
internal expect fun platformRuntimeVersion(): String
internal expect fun packageVersion(): String

// Resource cleanup (no-op on non-JVM)
internal expect fun closeWhenPhantomReachable(observed: Any, close: () -> Unit)

// Monotonic time source (for retry jitter)
internal expect fun currentTimeMillis(): Long

// Random (for retry jitter, multipart boundary)
internal expect fun randomDouble(): Double
internal expect fun randomUuidString(): String
```

**File: `src/jvmMain/kotlin/com/anthropic/core/Platform.jvm.kt`**
```kotlin
package com.anthropic.core

internal actual fun getSystemProperty(key: String): String? = System.getProperty(key)
internal actual fun getEnvironmentVariable(key: String): String? = System.getenv(key)
internal actual fun platformOsArch(): String = // existing getOsArch() logic
internal actual fun platformOsName(): String = // existing getOsName() logic
internal actual fun platformOsVersion(): String = System.getProperty("os.version", "unknown")
internal actual fun platformRuntimeVersion(): String = System.getProperty("java.version", "unknown")
internal actual fun packageVersion(): String =
    com.anthropic.client.AnthropicClient::class.java.`package`?.implementationVersion ?: "unknown"
internal actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) = // existing Cleaner logic
internal actual fun currentTimeMillis(): Long = System.currentTimeMillis()
internal actual fun randomDouble(): Double = java.util.concurrent.ThreadLocalRandom.current().nextDouble()
internal actual fun randomUuidString(): String = java.util.UUID.randomUUID().toString()
```

**File: `src/nativeMain/kotlin/com/anthropic/core/Platform.native.kt`**
```kotlin
package com.anthropic.core

import kotlinx.cinterop.*
import platform.posix.*

internal actual fun getSystemProperty(key: String): String? = null // no system properties on native
internal actual fun getEnvironmentVariable(key: String): String? = getenv(key)?.toKString()
internal actual fun platformOsArch(): String = // uname().machine
internal actual fun platformOsName(): String = // uname().sysname
internal actual fun platformOsVersion(): String = // uname().release
internal actual fun platformRuntimeVersion(): String = "native"
internal actual fun packageVersion(): String = "unknown" // no JAR manifest
internal actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) {} // no-op
internal actual fun currentTimeMillis(): Long = // clock_gettime or kotlin.system.getTimeMillis()
internal actual fun randomDouble(): Double = kotlin.random.Random.nextDouble()
internal actual fun randomUuidString(): String = // Kotlin 2.0+ kotlin.uuid.Uuid.random().toString()
```

**File: `src/jsMain/kotlin/com/anthropic/core/Platform.js.kt`**
```kotlin
package com.anthropic.core

internal actual fun getSystemProperty(key: String): String? = null
internal actual fun getEnvironmentVariable(key: String): String? =
    js("typeof process !== 'undefined' && process.env ? process.env[key] : undefined") as? String
internal actual fun platformOsArch(): String = js("typeof process !== 'undefined' ? process.arch : 'browser'") as String
internal actual fun platformOsName(): String = js("typeof process !== 'undefined' ? process.platform : 'browser'") as String
internal actual fun platformOsVersion(): String = "unknown"
internal actual fun platformRuntimeVersion(): String = "js"
internal actual fun packageVersion(): String = "unknown"
internal actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) {} // no-op (or FinalizationRegistry)
internal actual fun currentTimeMillis(): Long = kotlin.js.Date.now().toLong()
internal actual fun randomDouble(): Double = kotlin.random.Random.nextDouble()
internal actual fun randomUuidString(): String = kotlin.uuid.Uuid.random().toString()
```

### D. kotlinx.serialization: Model Patterns

#### D.1 Regular Model (e.g., RateLimitError) - Plugin-generated @Serializable
The models need constructor restructuring to work with the kotlinx.serialization compiler plugin:
```kotlin
@Serializable
class RateLimitError private constructor(
    @SerialName("message") private val message: JsonField<String> = JsonMissing.of(),
    @SerialName("type") private val type: JsonValue = JsonMissing.of(),
    // additionalProperties handled via @JsonNames or custom strategy
) {
    // Builder, validate(), etc. - unchanged
    @Transient
    private val additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

    companion object {
        fun builder() = Builder()  // no @JvmStatic in common
    }
}
```
**Key change**: The compiler plugin generates the serializer automatically. We use `@SerialName` instead of `@JsonProperty`, and `@Transient` for non-serialized fields. For `additionalProperties` (the `@JsonAnyGetter/@JsonAnySetter` pattern), we implement a custom `JsonTransformingSerializer` or use `JsonObject` decoding to capture unknown keys.

#### D.2 Enum-like Class (e.g., Model) - Custom KSerializer (can't use plugin for this pattern)
Enum-like classes wrapping `JsonField<String>` need a simple manual serializer since they aren't data-class-shaped:
```kotlin
@Serializable(with = ModelSerializer::class)
class Model private constructor(private val value: JsonField<String>) : Enum {
    fun _value(): JsonField<String> = value
    companion object {
        val CLAUDE_OPUS_4_6 = of("claude-opus-4-6")  // no @JvmField, no @JvmStatic
        fun of(value: String) = Model(JsonField.of(value))
    }
}

internal object ModelSerializer : KSerializer<Model> {
    override val descriptor = PrimitiveSerialDescriptor("Model", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Model) {
        encoder.encodeString(value._value().asStringOrThrow())
    }
    override fun deserialize(decoder: Decoder): Model = Model.of(decoder.decodeString())
}
```
There are ~20-30 such enum-like classes. These need manual serializers (small, trivial).

#### D.3 Union/Discriminated Type (e.g., ContentBlockSourceContent)
```kotlin
@Serializable(with = ContentBlockSourceContentSerializer::class)
class ContentBlockSourceContent private constructor(
    private val text: TextBlockParam? = null,
    private val image: ImageBlockParam? = null,
    private val _json: JsonValue? = null,
) {
    // Visitor pattern, factory methods - unchanged
}

internal object ContentBlockSourceContentSerializer : KSerializer<ContentBlockSourceContent> {
    override val descriptor = buildClassSerialDescriptor("ContentBlockSourceContent")
    override fun deserialize(decoder: Decoder): ContentBlockSourceContent {
        val jsonDecoder = decoder as JsonDecoder
        val element = jsonDecoder.decodeJsonElement().jsonObject
        val type = element["type"]?.jsonPrimitive?.content
        return when (type) {
            "text" -> ContentBlockSourceContent(text = jsonDecoder.json.decodeFromJsonElement(element))
            "image" -> ContentBlockSourceContent(image = jsonDecoder.json.decodeFromJsonElement(element))
            else -> ContentBlockSourceContent(_json = JsonValue.fromJsonElement(element))
        }
    }
    override fun serialize(encoder: Encoder, value: ContentBlockSourceContent) {
        val jsonEncoder = encoder as JsonEncoder
        when {
            value.text != null -> jsonEncoder.encodeSerializableValue(TextBlockParam.serializer(), value.text)
            value.image != null -> jsonEncoder.encodeSerializableValue(ImageBlockParam.serializer(), value.image)
            value._json != null -> jsonEncoder.encodeSerializableValue(JsonValue.serializer(), value._json)
        }
    }
}
```

#### D.4 Tool Use Annotations (JVM-only, via StructuredOutputs)
The `@JsonTypeName`, `@JsonClassDescription`, `@JsonPropertyDescription` annotations used in user-defined tool classes stay JVM-only. The `extractSchema()` function is `expect/actual`:
- **commonMain**: `expect internal fun extractSchema(type: KClass<*>): kotlinx.serialization.json.JsonObject`
- **jvmMain**: `actual internal fun extractSchema(type: KClass<*>)` uses victools jsonschema-generator + Jackson annotations (existing code)
- Tool use via `addTool(Class<*>)` stays JVM-only in the API. For common, add `addTool(KClass<*>)` variant that uses kotlinx.serialization schema generation.

#### D.5 JsonValue ↔ kotlinx.serialization.json.JsonElement Bridge
```kotlin
// In Values.kt (commonMain)
fun JsonValue.toJsonElement(): kotlinx.serialization.json.JsonElement = when (this) {
    is JsonNull -> kotlinx.serialization.json.JsonNull
    is JsonBoolean -> JsonPrimitive(value)
    is JsonNumber -> JsonPrimitive(value)
    is JsonString -> JsonPrimitive(value)
    is JsonArray -> kotlinx.serialization.json.JsonArray(values.map { it.toJsonElement() })
    is JsonObject -> kotlinx.serialization.json.JsonObject(values.mapValues { it.value.toJsonElement() })
    is JsonMissing -> kotlinx.serialization.json.JsonNull // or handle separately
}

fun JsonValue.Companion.fromJsonElement(element: kotlinx.serialization.json.JsonElement): JsonValue = ...
```

### E. HTTP Body Abstraction Change (using Okio)

**Before (JVM):**
```kotlin
interface HttpRequestBody : AutoCloseable {
    fun writeTo(outputStream: OutputStream)
    fun contentType(): String?
    fun contentLength(): Long
    fun repeatable(): Boolean
}
interface HttpResponse : AutoCloseable {
    fun body(): InputStream
}
```

**After (common with Okio):**
```kotlin
import okio.BufferedSink
import okio.BufferedSource
import okio.FileSystem
import okio.Path

interface HttpRequestBody {
    fun writeTo(sink: BufferedSink)  // Okio sink replaces OutputStream
    fun contentType(): String?
    fun contentLength(): Long
    fun repeatable(): Boolean
    fun close()
}

interface HttpResponse {
    fun statusCode(): Int
    fun headers(): Headers
    fun body(): BufferedSource        // Okio source replaces InputStream
    fun bodyAsText(): String          // convenience: body().readUtf8()
    fun bodyAsBytes(): ByteArray      // convenience: body().readByteArray()
    fun close()
}
```

**Okio usage throughout the codebase:**
- `HttpRequestBodies.json()`: write JSON to `Buffer()` then wrap as body
- `StreamHandler.kt`: `response.body().buffered()` → Okio `BufferedSource.readUtf8Line()`
- `StringHandler.kt`: `response.body().readUtf8()`
- `MultipartBody`: write parts to `BufferedSink` instead of `OutputStream`
- File uploads (`MultipartField<InputStream>` → `MultipartField<Source>`): use `FileSystem.SYSTEM.source(path)` for file reads
- File downloads: `response.body()` is already a `BufferedSource`, write to `FileSystem.SYSTEM.sink(path)`
- Tests: `FakeFileSystem` for isolated file operations

**Okio FileSystem for platform file operations:**
```kotlin
// Common code - works on all platforms
val fs = FileSystem.SYSTEM
val source = fs.source(path)
val sink = fs.sink(path)

// Tests - isolated fake filesystem
val fakeFs = FakeFileSystem()
fakeFs.write("test.json".toPath()) { writeUtf8(jsonContent) }
```

For SSE streaming, use Okio line-by-line reading:
```kotlin
fun BufferedSource.lines(): Sequence<String> = sequence {
    while (!exhausted()) {
        readUtf8Line()?.let { yield(it) }
    }
}
```

### F. Async Model Change Summary

**Before (JVM):**
```kotlin
interface HttpClient : AutoCloseable {
    fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse
    fun executeAsync(request: HttpRequest, requestOptions: RequestOptions): CompletableFuture<HttpResponse>
}
interface MessageServiceAsync {
    fun create(params: MessageCreateParams): CompletableFuture<Message>
}
interface AsyncStreamResponse<T> {
    fun subscribe(handler: Handler<T>): AsyncStreamResponse<T>
    fun onCompleteFuture(): CompletableFuture<Void?>
}
```

**After (common):**
```kotlin
interface HttpClient {
    suspend fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse
    fun close()
}
interface MessageServiceAsync {
    suspend fun create(params: MessageCreateParams): Message
    fun createStreaming(params: MessageCreateParams): Flow<RawMessageStreamEvent>
}
// AsyncStreamResponse replaced by Flow<T>
```

---

## Critical Files — Status

### ✅ DONE
| File | Change | Commit |
|---|---|---|
| `buildSrc/build.gradle.kts` | Kotlin 2.3.20, serialization plugin | 5212898 |
| `buildSrc/anthropic.kotlin.gradle.kts` | KMP support, remove Kotlin 1.8 constraints | 5212898 |
| `anthropic-java-core/build.gradle.kts` | KMP plugin + Jackson/Ktor/Okio/kotlinx deps | 5212898+ |
| `core/Values.kt` | → commonMain (nullable API, Jackson @JsonValue restored) | 5119bc6, a2cc478 |
| `core/Utils.kt` | → commonMain (contentHash, checkRequired, getOptional) | 5119bc6 |
| `core/Timeout.kt` | → commonMain, kotlin.time.Duration | 0e0f999 |
| `core/RequestOptions.kt` | → commonMain, kotlin.time.Duration | 0e0f999 |
| `core/DefaultSleeper.kt` | kotlinx.coroutines.delay() replacing Timer | 23ec675 |
| `core/Sleeper.kt` | → commonMain, kotlin.time.Duration | 0e0f999 |
| `core/http/StreamResponse.kt` | → commonMain, Flow<T> replacing Stream<T> | 12d21ea |
| `core/http/Headers.kt` | → commonMain, sortedMapOf replacing TreeMap | 8c570c5 |
| `core/http/QueryParams.kt` | → commonMain, removed @JvmName/@JvmStatic | 8c570c5 |
| `core/http/HttpRequest.kt` | → commonMain, expect/actual urlEncode() | 8c570c5 |
| `core/http/HttpRequestBody.kt` | → commonMain | 8c570c5 |
| `core/http/HttpResponse.kt` | → commonMain | cc23387 |
| `core/http/HttpClient.kt` | → commonMain | cc23387 |
| `core/Properties.kt` | → commonMain, expect/actual | c48e657 |
| `errors/*.kt` (14 files) | → commonMain, Optional→nullable | fb6c8b1 |
| `models/**/*.kt` (485 files) | → commonMain, stripped JVM annotations | ac4e751 |
| `services/**/*.kt` (44 files) | → commonMain | ac4e751 |
| `client/*.kt` (4 files) | → commonMain | ac4e751 |
| `backends/*.kt` (2 files) | → commonMain | ac4e751 |
| NEW: `core/JsonConfiguration.kt` | anthropicJson + JsonValue↔JsonElement bridge | 1f2cbef |
| NEW: `core/KtorBridge.kt` | HttpMethod/Headers↔Ktor types | 167afd5 |
| NEW: `core/KmpOptional.kt` | typealias + Kotlin extensions | b417b24 |
| NEW: `core/http/KtorHttpClient.kt` | Ktor-based HttpClient impl | 43770ad |
| NEW: `core/handlers/KotlinxJsonHandler.kt` | kotlinx.serialization JSON handler | 43770ad |

### 🔲 REMAINING GAPS
| File | Change Needed |
|---|---|
| `core/ObjectMappers.kt` | Replace with kotlinx.serialization (blocked by 485 model Jackson deps) |
| `core/BaseSerializer.kt` | Replace with KSerializer (blocked by model inner classes) |
| `core/BaseDeserializer.kt` | Replace with DeserializationStrategy (blocked) |
| `core/http/RetryingHttpClient.kt` | Replace with Ktor HttpRequestRetry plugin |
| `core/http/HttpRequestBodies.kt` | Replace MultipartBody with Ktor MultiPartFormDataContent |
| `core/http/AsyncStreamResponse.kt` | Replace CompletableFuture with Flow |
| `core/AutoPagerAsync.kt` | Replace CompletableFuture with Flow |
| `core/handlers/SseHandler.kt` | Replace custom SSE parser with Ktor SSE plugin |
| `core/ClientOptions.kt` | Remove Executor/Clock JVM deps |
| `core/PrepareRequest.kt` | Remove CompletableFuture |
| `core/PhantomReachable.kt` | expect/actual (JVM Cleaner) |
| Model files (485) | Jackson annotations → @Serializable (massive, needs code gen) |
| MCP SDK integration | Add dependency, create AnthropicMcpToolProvider |

---

## Detailed Implementation Todo

### Step 1: Toolchain & Build System
- [ ] 1.0 Create `.sdkmanrc` at project root (java=25-graal, kotlin=2.3.20, gradle=9.4.1, jbang=latest)
- [ ] 1.1 Install SDKMAN! tools: `sdk env install` (reads .sdkmanrc)
- [ ] 1.2 Update `gradle/wrapper/gradle-wrapper.properties` → gradle 9.4.1
- [ ] 1.3 Run `./gradlew wrapper --gradle-version=9.4.1`
- [ ] 1.4 Update `buildSrc/build.gradle.kts`: Kotlin `"2.3.20"`, add `kotlin("plugin.serialization")`
- [ ] 1.5 Update `buildSrc/src/main/kotlin/anthropic.kotlin.gradle.kts`: JDK 25, remove Kotlin 1.8 constraints
- [ ] 1.6 Update `buildSrc/src/main/kotlin/anthropic.java.gradle.kts`: JDK 25
- [ ] 1.7 Rewrite `anthropic-java-core/build.gradle.kts` with `kotlin("multiplatform")` + all targets
- [ ] 1.8 Update `settings.gradle.kts` to include `anthropic-kotlin-*` modules
- [ ] 1.9 Add `gradle.properties` entries for KMP
- [ ] 1.10 Verify: `./gradlew :anthropic-java-core:compileKotlinJvm` succeeds (empty common source)

### Step 2: Create Directory Structure
- [ ] 2.1 Create `src/commonMain/kotlin/com/anthropic/` tree
- [ ] 2.2 Create `src/commonTest/kotlin/com/anthropic/` tree
- [ ] 2.3 Create `src/jvmMain/kotlin/com/anthropic/core/` tree
- [ ] 2.4 Create `src/jsMain/kotlin/com/anthropic/core/` tree
- [ ] 2.5 Create `src/nativeMain/kotlin/com/anthropic/core/` tree
- [ ] 2.6 Copy existing `src/test/kotlin/` → `src/jvmTest/kotlin/` (keep as-is)
- [ ] 2.7 Verify: `./gradlew :anthropic-java-core:compileKotlinJvm` still works

### Step 3: Core Infrastructure → commonMain
- [ ] 3.1 Create `src/commonMain/.../core/annotations/Annotations.kt` (MustBeClosed, ExcludeMissing, etc.)
- [ ] 3.2 Create `src/commonMain/.../core/Platform.kt` (expect declarations)
- [ ] 3.3 Create `src/jvmMain/.../core/Platform.jvm.kt` (actual JVM)
- [ ] 3.4 Create `src/nativeMain/.../core/Platform.native.kt` (actual native)
- [ ] 3.5 Create `src/jsMain/.../core/Platform.js.kt` (actual JS)
- [ ] 3.6 Move+convert `core/Utils.kt` → commonMain (remove `java.util.Collections`, `java.util.concurrent`)
- [ ] 3.7 Move+convert `core/Check.kt` → commonMain (remove Jackson version check)
- [ ] 3.8 Move+convert `core/Timeout.kt` → commonMain (`java.time.Duration` → `kotlin.time.Duration`)
- [ ] 3.9 Move+convert `core/Sleeper.kt` → commonMain (suspend-based, remove CompletableFuture)
- [ ] 3.10 Move+convert `core/DefaultSleeper.kt` → commonMain (coroutine `delay()`)
- [ ] 3.11 Move+convert `core/RequestOptions.kt` → commonMain (kotlin.time)
- [ ] 3.12 Move+convert `core/Params.kt` → commonMain (already pure)
- [ ] 3.13 Move+convert `core/Page.kt` → commonMain (already pure)
- [ ] 3.14 Move+convert `core/PageAsync.kt` → commonMain (suspend fun nextPage)
- [ ] 3.15 Move `core/PhantomReachable.kt` → jvmMain (expect/actual)
- [ ] 3.16 Verify: `./gradlew :anthropic-java-core:compileKotlinMetadata` succeeds

### Step 4: JSON Value System → commonMain
- [ ] 4.1 Create `src/commonMain/.../core/JsonConfiguration.kt` (kotlinx.serialization Json config)
- [ ] 4.2 Move+convert `core/Values.kt` → commonMain (remove all Jackson, add @Serializable + KSerializer for JsonValue/JsonField)
- [ ] 4.3 Delete `core/ObjectMappers.kt` from main source (keep in jvmMain for structured outputs)
- [ ] 4.4 Delete `core/BaseSerializer.kt` and `core/BaseDeserializer.kt`
- [ ] 4.5 Verify: JsonField, JsonValue, JsonMissing, JsonNull compile in common

### Step 5: HTTP Abstractions → commonMain (Okio-based)
- [ ] 5.1 Move+convert `core/http/HttpMethod.kt` → commonMain (already pure enum)
- [ ] 5.2 Move+convert `core/http/Headers.kt` → commonMain (TreeMap → sortedMapOf)
- [ ] 5.3 Move+convert `core/http/QueryParams.kt` → commonMain (pure)
- [ ] 5.4 Move+convert `core/http/HttpRequest.kt` → commonMain (pure)
- [ ] 5.5 Move+convert `core/http/HttpRequestBody.kt` → commonMain (OutputStream → Okio BufferedSink)
- [ ] 5.6 Move+convert `core/http/HttpResponse.kt` → commonMain (InputStream → Okio BufferedSource)
- [ ] 5.7 Move+convert `core/http/HttpResponseFor.kt` → commonMain
- [ ] 5.8 Move+convert `core/http/HttpClient.kt` → commonMain (suspend only, remove CF)
- [ ] 5.9 Move+convert `core/http/StreamResponse.kt` → commonMain (Flow<T>)
- [ ] 5.10 Move+convert `core/http/AsyncStreamResponse.kt` → commonMain (Flow<T>)
- [ ] 5.11 Move+convert `core/http/SseMessage.kt` → commonMain (kotlinx.serialization)
- [ ] 5.12 Move+convert `core/http/HttpRequestBodies.kt` → commonMain (kotlinx.serialization + Okio)
- [ ] 5.13 Move+convert `core/http/RetryingHttpClient.kt` → commonMain (suspend + delay)
- [ ] 5.14 Move PhantomReachableClosing* → jvmMain
- [ ] 5.15 Verify: `./gradlew :anthropic-java-core:compileKotlinMetadata` succeeds

### Step 6: Ktor CIO Client Implementation
- [ ] 6.1 Create `src/commonMain/.../core/http/KtorHttpClient.kt`
- [ ] 6.2 Create `src/commonMain/.../core/http/KtorHttpResponse.kt`
- [ ] 6.3 Create MessagePack content negotiation support
- [ ] 6.4 Update ClientOptions to use Ktor client by default
- [ ] 6.5 Verify: HTTP client compiles on all targets

### Step 7: Handlers → commonMain
- [ ] 7.1 Move+convert `core/handlers/StringHandler.kt` → commonMain (Okio readUtf8)
- [ ] 7.2 Move+convert `core/handlers/JsonHandler.kt` → commonMain (kotlinx.serialization)
- [ ] 7.3 Move+convert `core/handlers/ErrorHandler.kt` → commonMain
- [ ] 7.4 Move+convert `core/handlers/SseHandler.kt` → commonMain (kotlinx.serialization)
- [ ] 7.5 Move+convert `core/handlers/StreamHandler.kt` → commonMain (Okio lines, Sequence)

### Step 8: Error Classes → commonMain
- [ ] 8.1 Move all 14 error classes → commonMain (remove @JvmOverloads)

### Step 9: Model Files → commonMain (batch, ~485 files)
- [ ] 9.1 Write batch transform script (sed/perl) for regular models: remove Jackson annotations, add @Serializable/@SerialName
- [ ] 9.2 Write batch transform for enum-like classes: add custom KSerializer
- [ ] 9.3 Write batch transform for union types: convert Deserializer/Serializer inner classes → KSerializer
- [ ] 9.4 Remove @JvmStatic, @JvmSynthetic, @JvmField, @JvmName from all models
- [ ] 9.5 Replace `java.util.Optional` → nullable returns in all model accessors
- [ ] 9.6 Replace `java.util.Collections.unmodifiableMap` → `.toMap()` in all models
- [ ] 9.7 Replace `java.util.Objects.hash` → manual hashCode or `hashOf()` helper
- [ ] 9.8 Move all converted models → `src/commonMain/kotlin/com/anthropic/models/`
- [ ] 9.9 Verify: `./gradlew :anthropic-java-core:compileKotlinMetadata` succeeds

### Step 10: Services → commonMain
- [ ] 10.1 Convert blocking service interfaces (suspend-only, remove Optional)
- [ ] 10.2 Convert async service interfaces (suspend-only, Flow for streaming)
- [ ] 10.3 Convert service implementations (suspend, kotlinx.serialization handlers)
- [ ] 10.4 Move all converted services → commonMain
- [ ] 10.5 Verify: services compile in common

### Step 11: Client & Backend → commonMain
- [ ] 11.1 Move+convert client interfaces and impls → commonMain
- [ ] 11.2 Move+convert Backend interface and AnthropicBackend → commonMain (expect/actual for env)
- [ ] 11.3 Move+convert helpers (MessageAccumulator, etc.) → commonMain
- [ ] 11.4 Move+convert AutoPager (Sequence), AutoPagerAsync (Flow) → commonMain
- [ ] 11.5 Move PrepareRequest → commonMain (suspend version)

### Step 12: StructuredOutputs (jvmMain only)
- [ ] 12.1 Create expect `extractSchema` in commonMain
- [ ] 12.2 Keep victools-based actual in jvmMain
- [ ] 12.3 Move JsonSchemaValidator → commonMain (convert JsonNode → JsonElement)

### Step 13: Test Infrastructure
- [ ] 13.1 Create `src/commonTest/.../TestServer.kt` (Ktor CIO + API key auth)
- [ ] 13.2 Create test stub helpers (mock responses for each endpoint)
- [ ] 13.3 Create `src/commonTest/.../TestHelpers.kt` (assertion helpers)

### Step 14: Port Tests → commonTest
- [ ] 14.1 Port core tests (Values, Utils, Check, Headers, QueryParams, Timeout, HttpRequest)
- [ ] 14.2 Port handler tests (JsonHandler, SseHandler, StreamHandler)
- [ ] 14.3 Port model tests (~400 files, batch transform: JUnit→kotlin.test, AssertJ→assertEquals)
- [ ] 14.4 Port service tests (WireMock stubs → Ktor server stubs)
- [ ] 14.5 Port helper tests (MessageAccumulator, BetaToolRunner)
- [ ] 14.6 Port backend tests

### Step 15: Convert Examples → commonTest
- [ ] 15.1 Convert Messages examples (sync, async, conversation, image) → test
- [ ] 15.2 Convert Streaming examples (sync, async, cancellation) → test
- [ ] 15.3 Convert CountTokens examples → test
- [ ] 15.4 Convert ModelList examples → test
- [ ] 15.5 Convert Batch examples → test
- [ ] 15.6 Convert StructuredOutputs examples → test
- [ ] 15.7 Convert Tool use examples → test
- [ ] 15.8 Convert Thinking examples → test
- [ ] 15.9 Skip platform-specific examples (Bedrock, Vertex, Foundry)

### Step 16: Integration Modules
- [ ] 16.1 Create `anthropic-java-langchain4j/` module + build.gradle.kts
- [ ] 16.2 Implement AnthropicChatModel, AnthropicStreamingChatModel, AnthropicTokenizer
- [ ] 16.3 Create LangChain4j integration tests
- [ ] 16.4 Create `anthropic-java-camel/` module + build.gradle.kts
- [ ] 16.5 Implement AnthropicComponent, AnthropicEndpoint, AnthropicProducer
- [ ] 16.6 Create Camel integration tests
- [ ] 16.7 Create `anthropic-kotlin-mcp/` module + build.gradle.kts
- [ ] 16.8 Implement AnthropicMcpToolProvider, AnthropicMcpClient
- [ ] 16.9 Create MCP integration tests
- [ ] 16.10 Create `anthropic-kotlin-proto/` module + build.gradle.kts + proto definitions
- [ ] 16.11 Implement AnthropicGrpcClient + proto ↔ SDK model mappers
- [ ] 16.12 Create gRPC integration tests

### Step 17: Verify Other Modules
- [ ] 17.1 `./gradlew :anthropic-java-client-okhttp:build`
- [ ] 17.2 `./gradlew :anthropic-java:build`
- [ ] 17.3 `./gradlew :anthropic-java-example:compileJava`
- [ ] 17.4 `./gradlew :anthropic-java-bedrock:build`
- [ ] 17.5 `./gradlew :anthropic-java-vertex:build`
- [ ] 17.6 `./gradlew :anthropic-java-foundry:build`
- [ ] 17.7 `./gradlew :anthropic-java-aws:build`

### Step 18: Final Verification
- [ ] 18.1 `./gradlew :anthropic-java-core:allTests`
- [ ] 18.2 `./gradlew :anthropic-java-core:jvmTest`
- [ ] 18.3 `./gradlew :anthropic-java-core:jsTest`
- [ ] 18.4 `./gradlew :anthropic-java-core:linkDebugTestLinuxX64` (native tests)
- [ ] 18.5 `./gradlew build` (full project)
- [ ] 18.6 Commit and push

---

## Test Plan

### T1: Build System Tests
| # | Test | Command | Pass Criteria |
|---|---|---|---|
| T1.1 | Gradle wrapper upgrade | `./gradlew --version` | Shows 9.4.1 |
| T1.2 | Kotlin version | `./gradlew :anthropic-java-core:dependencies` | Shows 2.3.20 |
| T1.3 | JDK toolchain | `./gradlew :anthropic-java-core:jvmToolchainInfo` | Shows GraalVM 25 |
| T1.4 | KMP metadata compile | `./gradlew :anthropic-java-core:compileKotlinMetadata` | Success |
| T1.5 | JVM compile | `./gradlew :anthropic-java-core:compileKotlinJvm` | Success |
| T1.6 | JS compile | `./gradlew :anthropic-java-core:compileKotlinJs` | Success |
| T1.7 | Native compile | `./gradlew :anthropic-java-core:compileKotlinLinuxX64` | Success |

### T2: Core Infrastructure Tests (commonTest)
| # | Test | Validates |
|---|---|---|
| T2.1 | `PlatformTest` | expect/actual getSystemProperty, getEnvironmentVariable work on each target |
| T2.2 | `TimeoutTest` | kotlin.time.Duration-based Timeout construction, defaults, assign() |
| T2.3 | `SleeperTest` | suspend sleep() completes after specified delay |
| T2.4 | `UtilsTest` | toImmutable(), allMaxBy(), contentEquals(), contentHash() |
| T2.5 | `CheckTest` | checkRequired(), checkKnown(), checkLength() throw correctly |
| T2.6 | `RequestOptionsTest` | applyDefaults(), timeout calculation from maxTokens |

### T3: JSON Value System Tests (commonTest)
| # | Test | Validates |
|---|---|---|
| T3.1 | `JsonValueSerializationTest` | JsonNull, JsonBoolean, JsonNumber, JsonString, JsonArray, JsonObject round-trip |
| T3.2 | `JsonFieldTest` | JsonField.of(), KnownValue, JsonMissing serialization exclusion |
| T3.3 | `JsonValueFromTest` | JsonValue.from(null/true/42/"s"/List/Map) produces correct types |
| T3.4 | `JsonValueVisitorTest` | Visitor pattern visits correct type |
| T3.5 | `JsonMissingFilterTest` | IsMissing filter excludes missing fields during serialization |
| T3.6 | `JsonValueToJsonElementTest` | Bridge: JsonValue ↔ kotlinx.serialization.json.JsonElement |

### T4: HTTP Abstraction Tests (commonTest)
| # | Test | Validates |
|---|---|---|
| T4.1 | `HeadersTest` | Case-insensitive, multi-value, builder, toImmutable |
| T4.2 | `QueryParamsTest` | Key-value, JSON value flattening, bracket notation |
| T4.3 | `HttpRequestTest` | URL construction, path segments encoding, builder |
| T4.4 | `HttpMethodTest` | All enum values |
| T4.5 | `HttpRequestBodyTest` | JSON body via Okio BufferedSink, contentType, contentLength |
| T4.6 | `MultipartBodyTest` | Multipart form-data construction, boundary, parts |
| T4.7 | `SseMessageTest` | SSE line parsing, event/data/id/retry fields |
| T4.8 | `RetryingHttpClientTest` | Retry on 429/5xx, exponential backoff, max retries, jitter |

### T5: Ktor Client Tests (commonTest)
| # | Test | Validates |
|---|---|---|
| T5.1 | `KtorHttpClientTest` | Execute request against Ktor test server, verify response |
| T5.2 | `KtorStreamingTest` | SSE streaming via Ktor client, collect Flow events |
| T5.3 | `KtorRetryTest` | Retry logic with Ktor test server returning 429 then 200 |
| T5.4 | `KtorTimeoutTest` | Request timeout triggers correctly |
| T5.5 | `KtorAuthTest` | API key sent in X-Api-Key header, 401 on missing key |

### T6: Model Tests (commonTest, ~400 tests)
| # | Test | Validates |
|---|---|---|
| T6.1 | `RateLimitErrorTest` | Serialize/deserialize, builder, validate(), additionalProperties |
| T6.2 | `ModelEnumTest` | Model.of(), known/unknown values, serialization |
| T6.3 | `ContentBlockSourceContentTest` | Union deserialization by "type" discriminator |
| T6.4 | `MessageCreateParamsTest` | Builder pattern, all fields, body serialization |
| T6.5 | `MessageTest` | Full response deserialization, content blocks, usage |
| T6.6 | ... (~395 more) | Each model file has corresponding test |

### T7: Service Tests (commonTest, Ktor server stubs)
| # | Test | Validates |
|---|---|---|
| T7.1 | `MessageServiceTest` | create(), createStreaming(), countTokens() against mock server |
| T7.2 | `ModelServiceTest` | list() with pagination against mock server |
| T7.3 | `CompletionServiceTest` | Legacy completion endpoint |
| T7.4 | `BatchServiceTest` | Batch create, list, get against mock server |
| T7.5 | `BetaMessageServiceTest` | Beta endpoints with beta headers |
| T7.6 | `BetaFileServiceTest` | File upload/download with multipart |
| T7.7 | `ErrorHandlingTest` | 400/401/403/404/422/429/5xx → correct exception types |
| T7.8 | `ServiceParamsTest` | Headers, query params, body params pass through correctly |

### T8: Example Conversion Tests (commonTest)
| # | Test | Validates |
|---|---|---|
| T8.1 | `MessagesExampleTest` | Basic message create, response parsing |
| T8.2 | `MessagesStreamingExampleTest` | Streaming Flow collection |
| T8.3 | `MessagesConversationExampleTest` | Multi-turn conversation |
| T8.4 | `CountTokensExampleTest` | Token counting endpoint |
| T8.5 | `ModelListExampleTest` | Model listing with autopager |
| T8.6 | `BatchExampleTest` | Batch operations |
| T8.7 | `StructuredOutputsExampleTest` | JSON output format |
| T8.8 | `ToolUseExampleTest` | Tool definition, invocation, result |
| T8.9 | `ThinkingExampleTest` | Extended thinking params |

### T9: Integration Module Tests
| # | Test | Validates |
|---|---|---|
| T9.1 | `AnthropicChatModelTest` | LangChain4j chat() maps correctly to Anthropic create() |
| T9.2 | `AnthropicStreamingChatModelTest` | LangChain4j streaming maps to createStreaming() |
| T9.3 | `AnthropicTokenizerTest` | LangChain4j tokenizer maps to countTokens() |
| T9.4 | `AnthropicComponentTest` | Camel component creates endpoint correctly |
| T9.5 | `AnthropicProducerTest` | Camel producer sends message, parses response |
| T9.6 | `AnthropicMcpToolProviderTest` | MCP tools convert to Anthropic BetaTool |
| T9.7 | `AnthropicMcpClientTest` | Full MCP tool use loop: create → tool_use → callTool → result |
| T9.8 | `AnthropicGrpcClientTest` | Proto request/response mapping, gRPC streaming |
| T9.9 | `MessagePackNegotiationTest` | MessagePack serialization/deserialization round-trip |

### T10: JVM Backward Compatibility Tests (jvmTest, existing)
| # | Test | Validates |
|---|---|---|
| T10.1 | All existing tests in `src/jvmTest/` | Pass unchanged with WireMock, AssertJ, JUnit5 |
| T10.2 | `OkHttpClientTest` | OkHttp module still works against JVM target |
| T10.3 | `ProGuardCompatibilityTest` | ProGuard/R8 rules still work |
| T10.4 | Java example compilation | `./gradlew :anthropic-java-example:compileJava` succeeds |

### T11: Cross-Platform Tests
| # | Test | Command | Validates |
|---|---|---|---|
| T11.1 | JVM tests | `./gradlew :anthropic-java-core:jvmTest` | All common+jvm tests pass |
| T11.2 | JS tests | `./gradlew :anthropic-java-core:jsTest` | All common tests pass on Node.js |
| T11.3 | Linux native tests | `./gradlew :anthropic-java-core:linuxX64Test` | All common tests pass on Linux native |
| T11.4 | macOS native tests | `./gradlew :anthropic-java-core:macosArm64Test` | All common tests pass on macOS native |
| T11.5 | Full build | `./gradlew build` | Entire project builds successfully |
| `errors/*.kt` (14 files) | Remove @JvmOverloads |
