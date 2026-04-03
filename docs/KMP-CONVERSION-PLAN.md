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
| Jackson | 2.18.2 | removed from common (kept in jvmMain for structured outputs) |
| OkHttp | 4.12.0 | unchanged (JVM-only module) |

---

## Phase 1: Build System & Toolchain Upgrade

### 1.1 Install toolchain via SDKMAN!
```bash
# Install/upgrade all tools via sdkman
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

## Implementation Order

1. **Phase 1**: Build system changes (Kotlin upgrade, KMP plugin, source set restructure)
2. **Phase 2**: Core infrastructure first (Values.kt, JsonConfiguration, Utils.kt, Check.kt)
3. **Phase 3**: HTTP abstractions (HttpClient, HttpRequest, HttpResponse, HttpRequestBody)
4. **Phase 4**: Ktor CIO client implementation
5. **Phase 2 cont.**: Handlers (JsonHandler, SseHandler, StreamHandler)
6. **Phase 3 cont.**: Replace async (CompletableFuture → coroutines) in services
7. **Phase 2 cont.**: Model files (batch transform ~485 files)
8. **Phase 3 cont.**: Client/Backend classes
9. **Phase 5**: Test infrastructure (Ktor CIO server) then port tests
10. **Phase 6**: Verify other modules still compile against JVM target

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

### 8.4 Update settings.gradle.kts
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

### A. Complete Annotation Inventory & Migration

#### Jackson Annotations (remove from common, all in model/core files)
| Annotation | Count | Files | KMP Replacement |
|---|---|---|---|
| `@JsonProperty("name")` | ~3,217 | 485 models | `@SerialName("name")` |
| `@JsonCreator` | ~924 | 485 models | Custom `KSerializer` companion `deserialize()` |
| `@JsonAnyGetter` | ~412 | 485 models | Custom serializer logic for additionalProperties |
| `@JsonAnySetter` | ~413 | 485 models | Custom deserializer logic for additionalProperties |
| `@JsonValue` | ~100 | Enum-like classes (Model, StopReason, etc.) | `@Serializable` with `KSerializer` |
| `@JsonDeserialize(using=)` | ~93 | Union types (ContentBlock, ToolChoice, etc.) | `@Serializable(with=)` using `KSerializer` |
| `@JsonSerialize(using=)` | ~93 | Union types | `@Serializable(with=)` using `KSerializer` |
| `@ExcludeMissing` | ~5 | Models with Optional fields | Custom serializer skips `JsonMissing` |
| `@JsonTypeName` | ~few | Tool use annotations (user-facing) | Keep as-is in jvmMain for structured outputs only |
| `@JsonClassDescription` | ~few | Tool use (user examples only) | Keep in jvmMain; not needed in common models |
| `@JsonPropertyDescription` | ~few | Tool use (user examples only) | Keep in jvmMain |
| `@JsonIgnore` | ~few | Tool use (user examples) | Keep in jvmMain |

#### JVM Annotations (remove from commonMain)
| Annotation | Count | Files | KMP Action |
|---|---|---|---|
| `@JvmStatic` | ~1,090 | companion objects, `of()`, `builder()` | Remove; add back in jvmMain via `@JvmStatic` on actual |
| `@JvmSynthetic` | ~1,184 | internal functions | Remove entirely (common Kotlin has `internal` visibility) |
| `@JvmName` | ~25 | `@file:JvmName(...)` on utility files | Remove from common; not needed |
| `@JvmOverloads` | ~12 | Error constructors | Remove; use default parameters |
| `@JvmField` | ~367 | Enum-like companion vals (Model.CLAUDE_OPUS_4_6) | Remove; Kotlin `val` is fine in common |
| `@file:JvmName(...)` | ~10 | Utility files (Utils, Check, Properties, etc.) | Remove |

#### Google ErrorProne Annotations
| Annotation | Usage | KMP Action |
|---|---|---|
| `@MustBeClosed` | ~40 uses in service interfaces (streaming methods) | Remove; add custom `@MustBeClosed` annotation in common or use `@OptIn` |

#### Swagger Annotations
| Annotation | Usage | KMP Action |
|---|---|---|
| `@Schema` | Only in example code + StructuredOutputs test | Keep in jvmMain only (part of structured outputs JVM impl) |

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

### E. HTTP Body Abstraction Change

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

**After (common):**
```kotlin
interface HttpRequestBody {
    fun toByteArray(): ByteArray    // for simple bodies
    fun contentType(): String?
    fun contentLength(): Long
    fun repeatable(): Boolean
    fun close()
}
interface HttpResponse {
    fun statusCode(): Int
    fun headers(): Headers
    fun body(): ByteArray            // for simple responses
    fun bodyAsText(): String         // convenience
    fun close()
}
```

For streaming, use Ktor's `ByteReadChannel` or return `Sequence<String>` for line-by-line SSE:
```kotlin
interface HttpResponse {
    // ... above methods
    suspend fun bodyLines(): Sequence<String>  // for SSE streaming
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

## Critical Files to Modify

| File | Change |
|---|---|
| `buildSrc/build.gradle.kts` | Kotlin 2.1.x, add serialization plugin |
| `buildSrc/src/main/kotlin/anthropic.kotlin.gradle.kts` | KMP support |
| `anthropic-java-core/build.gradle.kts` | KMP plugin + new deps |
| `core/Values.kt` | Jackson → kotlinx.serialization |
| `core/ObjectMappers.kt` | Delete, replace with JsonConfiguration.kt |
| `core/BaseSerializer.kt`, `core/BaseDeserializer.kt` | Delete, replace |
| `core/Utils.kt` | Remove java.util imports |
| `core/Check.kt` | Remove Jackson version check |
| `core/Properties.kt` | expect/actual |
| `core/PhantomReachable.kt` | expect/actual |
| `core/Timeout.kt` | java.time → kotlin.time |
| `core/Sleeper.kt` | CompletableFuture → suspend |
| `core/DefaultSleeper.kt` | Timer → coroutines |
| `core/ClientOptions.kt` | Remove JVM deps |
| `core/RequestOptions.kt` | java.time → kotlin.time |
| `core/http/HttpClient.kt` | Remove CompletableFuture |
| `core/http/HttpRequestBody.kt` | OutputStream → ByteArray |
| `core/http/HttpResponse.kt` | InputStream → ByteArray/Channel |
| `core/http/RetryingHttpClient.kt` | CompletableFuture → coroutines |
| `core/http/StreamResponse.kt` | Stream → Sequence/Flow |
| `core/http/AsyncStreamResponse.kt` | CompletableFuture → Flow |
| `core/handlers/*.kt` | Jackson → kotlinx.serialization |
| `models/**/*.kt` (~485 files) | Jackson annotations → @Serializable |
| `services/**/*.kt` (~44 files) | CompletableFuture → suspend, Optional → nullable |
| `client/*.kt` (4 files) | Remove JVM-specific code |
| `backends/*.kt` (2 files) | System.getProperty → expect/actual |
| `errors/*.kt` (14 files) | Remove @JvmOverloads |
