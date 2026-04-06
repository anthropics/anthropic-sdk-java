# CLAUDE.md — KMP Conversion Project Guide

## Project Overview

Converting `anthropic-sdk-java` from JVM-only to Kotlin Multiplatform (KMP).
The core principle: **use stable KMP libs directly, don't duplicate them**.

## Key Documents

- **Migration Plan + Low-Level Design**: [`docs/KMP-CONVERSION-PLAN.md`](docs/KMP-CONVERSION-PLAN.md)
- **Branch**: `claude/convert-to-kmp-I9zBV`
- **194 commits** on branch, all pushed

## Current Status

| Metric | Value |
|---|---|
| Files in anthropic-java-core commonMain | 540 (models, services, helpers, client) |
| api-kmp commonMain | 121 files (kotlinx.kmp.util.core — HTTP, JSON, errors, paging, platform) |
| api-kmp jvmMain | 54 files (Jackson adapters, JVM handlers, async extensions) |
| api-kmp jsMain | 6 files (JS platform actuals, Jackson annotation stubs) |
| api-kmp nativeMain | 6 files (Native platform actuals, Jackson annotation stubs) |
| api-kmp commonTest | 5 test files (99 tests — KmpOptional 66, KotlinxApiJsonBackend 8, JsonValueSerializer 8, McpTypes 5, ContentFormats 12) |
| api-kmp jvmTest | 2 test files (26 tests — HttpRetryTest 20 WireMock + KtorServerProtocolTest 6 JSON/SSE/WS) |
| api-kmp test (gen) | 3 test files (20 tests — MultiProviderGenTest 14, ComposeEmitterTest 3, DatabaseEmitterTest 3) |
| KMP targets | JVM ✅, JS (IR) ✅ — both compile with zero errors |
| Native targets | linuxX64, macosX64, macosArm64 — actuals written, pending toolchain download |
| GraalVM | Oracle 25.0.2 + native-image — .sdkmanrc java=25.0.2-graal |
| Content formats | JSON + CBOR + Protobuf + MsgPack via kotlinx.serialization (commonMain, all targets) |
| java.* imports in api-kmp commonMain | **0** (zero — fully purified) |
| Jackson imports in api-kmp commonMain | 2 (JsonSchemaValidator + ErrorType — by directive) |
| Jackson annotations | Reused directly — JVM: real JAR; JS: stub classes in jsMain |
| JSON backends | JacksonApiJsonBackend (JVM) + KotlinxApiJsonBackend (commonMain) |
| Error classes | `Api*Exception` (renamed from `Anthropic*Exception`) in `kotlinx.kmp.util.core.errors` |
| Retry | `HttpClient.withRetry` extension + `Retryable` marker (deleted `RetryingHttpClient`) |
| HttpClient interface | commonMain: `execute()` + `executeSuspend()` only; JVM extension: `executeAsync()` |
| Sleeper interface | commonMain: `sleep()` + `sleepSuspend()` only; JVM extension: `sleepAsync()` |
| api-gen | ✅ Generates KMP Kotlin from OpenAPI — tested with Petstore + Amazon SP-API |
| Petstore tests | 5 WireMock tests, 0 failures |

## Plan Structure — Where to Find What

### ✅ Completed Work
| Section | Line | What |
|---|---|---|
| Current Progress | L629 | Summary of all completed phases with commit hashes |
| Kotlin-Native Replacements | L640 | Duration, Stream→Flow, Timer→delay, UUID, AutoCloseable |
| Stable Library Integration | L655 | okio, Wire, ktor added |
| KMP Utility Packages | L662 | `kotlinx.kmp.util.optional` (66 tests) |
| HttpMethod Multi-Protocol | L670 | HTTP/WebDAV/gRPC/RSocket/MCP enum |
| KMP-Native Files | L674 | KtorHttpClient, KotlinxJsonHandler, JsonConfiguration, KmpOptional |
| api-kmp extraction | L700 | Runtime + codegen in separate module |
| api-gen end-to-end | — | OpenAPI → @Serializable + suspend + ktor (Petstore, Amazon) |
| Petstore WireMock tests | — | 5 tests pass: GET/POST Pet, List, Inventory, Category |
| Ktor extensions | — | HeadersBuilder/ParametersBuilder + KmpValue support |

### ✅ Phase 12 — commonMain purification + multi-backend JSON
| Section | What |
|---|---|
| Core/errors/ErrorType to api-kmp | `66d982f` `95b96e4` — entire `com.anthropic.core.*` + `com.anthropic.errors.*` → `kotlinx.kmp.util.core` (65 file renames) |
| RetryingHttpClient deleted | `d0bf076` — replaced with `HttpClient.withRetry` extension + `Retryable` marker |
| Api*Exception rename | `54a3f9f` — `Anthropic*Exception` → `Api*Exception` (518 files) |
| Zero java.\* in commonMain | `b12470c` — CompletableFuture/Executor/Clock all moved to jvmMain extensions |
| Jackson → jvmMain + typealiases | `94b2536` `500fea9` — adapters in jvmMain; `kotlinx.kmp.util.core.json.*` typealiases for annotations + runtime types |
| Model import rewrite | `342c78b` `c422ed2` — ~600 model files → `kotlinx.kmp.util.core.json.*` imports |
| KotlinxApiJsonBackend | `94076fb` — kotlinx.serialization backend + JsonValue↔JsonElement serializers (16 tests) |
| HttpRetryTest restored | `c2d1ba5` `5536687` — 20 WireMock tests via ClientOptions config |
| JS (IR) target compiles | `5dd4364` — js(IR) { browser; nodejs }, JS platform actuals, CaseInsensitiveMap, runBlockingCompat |
| Reuse Jackson annotations | `f425ede` — delete typealias layer; JS stubs in jsMain; model files import Jackson directly; all tests pass |
| JS (IR) target compiles | `5dd4364` — js(IR) { browser; nodejs }, JS platform actuals, CaseInsensitiveMap, runBlockingCompat |
| Native target | `215c819` — linuxX64/macosX64/macosArm64 actuals + Jackson stubs (pending toolchain) |
| @Serializable on api-gen models | `fe7d511` — api-gen emits @JsonProperty + @Serializable + @SerialName |
| MCP tool types | `ab9604d` — commonMain McpTypes (ToolDefinition, ToolCallRequest/Result, ToolContent) + 5 tests |
| ktor CIO server tests | `828b03b` — 6 tests: JSON GET/POST, SSE stream, WebSocket echo + JSON streaming |
| Compose + DB emitter tests | `2da1388` — 6 tests: ComposeEmitter (Form/List/Detail/MasterDetail + skip rules) + DatabaseEmitter (Exposed + SQLDelight + jsonb RFC types) |
| CBOR + Protobuf + MsgPack formats | `4c3f3c1` `90bbfc9` — ContentFormat enum (JSON/CBOR/PROTOBUF/MSGPACK) + .sdkmanrc java=25.0.2-graal + 12 tests |

### 🔲 Remaining Work
| Section | What |
|---|---|
| ~~JS/Native targets~~ | **Not needed** — api-gen generates KMP-native models from OpenAPI spec via @Serializable + Wire proto. The 540 hand-written model files in anthropic-java-core are the JVM-only legacy path; the KMP path is api-gen output. |

### What stays, what gets typealiased, what's NOT migrated

| Category | Approach | Code Migration? |
|---|---|---|
| Jackson annotations (`@JsonProperty` etc.) | **Keep** — standard, JVM runtime, ignored on non-JVM | None |
| kotlin.jvm annotations (`@JvmStatic` etc.) | **Keep** — standard, JVM bytecode, ignored on non-JVM | None |
| `java.util.Optional<T>` | **typealias** → `expect class Optional` in commonMain | None — same API |
| `java.util.function.*` | **typealias** → `expect fun interface` in commonMain | None — same API |
| Jackson annotations (`@JsonProperty` etc.) | **typealias** → `kotlinx.kmp.util.core.json.JsonProperty` (resolves to Jackson on JVM) | ✅ Done — ~600 model files rewritten |
| Jackson runtime (`JsonMapper`, serializers) | **jvmMain only** — expect/actual `jsonMapper()` + `ApiJsonBackend` | ✅ Done — `JacksonApiJsonBackend` in jvmMain |
| `java.util.concurrent.CompletableFuture` | **jvmMain extension** — `HttpClient.executeAsync()` as JVM ext, not interface | ✅ Done — commonMain suspend-only |
| `java.time.Clock` | **Replaced** — `nowMillisProvider: () -> Long` in ClientOptions | ✅ Done |
| `java.io.InputStream/OutputStream` | **okio** `BufferedSource/BufferedSink` | ✅ Done (core interfaces) |
| `java.util.concurrent.Executor` | **jvmMain** — `createDefaultStreamExecutor()` expect/actual | ✅ Done |
| Wire `@WireRpc`, `@WireField` | **Use directly** — standard proto lib | None |

### Low-Level Designs
| Section | Line | Commit | What |
|---|---|---|---|
| **Stable Libs Strategy** | L1396 | `50cc231` | Core principle: libs replace all boilerplate |
| **Serialization Formats** | L1415 | `50cc231` | JSON + MsgPack + Protobuf + CBOR via ktor ContentNegotiation |
| **Custom Code → Lib Map** | L1441 | `50cc231` | 730 lines to delete, mapped to stable lib replacements |
| **JsonField/Value = Wire** | L1457 | `6720d54` | Field presence semantics: KnownValue/Missing/Null = Wire @WireField |
| **Deep Classification** | L1485 | `5b3038d` | Nothing in com.anthropic.core is Claude-specific |
| **ClientOptions → ktor** | L1538 | `b72f11e` | Field-by-field mapping to ktor CIO plugins + OkHttp |
| **OpenAPI Security Schemes** | L1653 | `b72f11e` | OIDC/OAuth2/Bearer/Basic/MutualTLS via ktor Auth |
| **Tool Roles + Security** | L1784 | `4ed5d43` | OpenAPI/AsyncAPI/gRPC/WASM — specs define security |
| **LangChain4j Integration** | L2640 | prior | JVM-only, ChatLanguageModel + Tokenizer |
| **Apache Camel Integration** | L2718 | prior | JVM-only, Component + Endpoint + Producer |
| **MCP SDK Integration** | L2800 | prior | KMP, McpClient + Transport + Tool bridge |
| **Protobuf/gRPC (Wire)** | L2914 | prior | KMP, Wire plugin + proto codegen + GrpcClient |
| **MessagePack** | L3039 | prior | ktor ContentNegotiation + kotlinx-serialization-msgpack |
| **Annotations Inventory** | L3192 | `aaa23ea` | All annotations: Jackson, Kotlin, Wire — migration status |
| **Folder Structure** | L3263 | `aaa23ea` | Actual current state of commonMain/jvmMain/commonTest |
| **kotlinx.serialization Patterns** | L3417 | prior | Model class patterns for @Serializable migration |
| **Component Architecture** | L3963 | `f81381f` | Schema = Component = All Protocols |
| **PatchEvent SSE Sync** | L4114 | `f4072b2` | PatchEvent = SSE message for all Flow subscribers |
| **HTTP Methods = Patch Ops** | L4203 | `0d93d41` | POST=add, PUT=replace, DELETE=remove, PATCH=ops |
| **WebSocket = Same PatchEvent** | L4249 | `0d93d41` | SSE + WS receive same event, different transport |
| **WebDAV Locking + Schema** | L4312 | `d686664` | LOCK/UNLOCK, PROPFIND schema, PROPPATCH validated |
| **MCP Exposes Components** | L4439 | `83493eb` | Component operations = MCP tools for AI agents |
| **Schema Registry** | L4525 | `041e3b1` | External $ref + cross-API JSON Patch |

### Implementation Todo + Test Plan
| Section | Line | What |
|---|---|---|
| Detailed 18-Step Todo | L2937 | Step-by-step implementation checklist |
| 11-Category Test Plan | L3140 | 80+ test cases across all categories |

## Stable Lib Stack

| Lib | Version | What It Replaces |
|---|---|---|
| **okio** | 3.17.0 | java.io.InputStream/OutputStream ✅ done |
| **Wire** | 5.3.1 | Custom ProtoAnnotations ✅ done, @WireRpc for gRPC |
| **ktor** | 3.4.2 | HttpClient, retry, SSE, multipart, content negotiation |
| **kotlinx.serialization** | 1.10.0 | Jackson (JSON + MsgPack + Protobuf + CBOR) |
| **kotlinx.coroutines** | 1.10.2 | CompletableFuture, Executor, Timer |
| **MCP SDK** | 0.11.0 | Custom MCP integration |

## Architecture Decisions

### Core Principle: Standard Specs + Stable Libs = Anything Is a Secure Tool

We use **standard cross-platform API specs** for configuration:
- **OpenAPI** — REST HTTP services (JSON/MsgPack/CBOR)
- **AsyncAPI** — Event-driven services (WebSocket, SSE, MQTT, Kafka)
- **gRPC/Proto** — RPC services (Protobuf, streaming)
- **WASM** — Sandboxed compute (portable, memory-safe)

Any service described by these specs can be called as a tool securely.
The spec's `securitySchemes` definition IS the security boundary — enforced
by the stable lib that implements the spec (ktor, Wire, wasmtime).

### Decisions

1. **Reuse Jackson annotations directly** — Model files import `com.fasterxml.jackson.annotation.JsonProperty` etc. On JVM, the real Jackson JAR provides them. On JS, stub annotation classes in `jsMain/com/fasterxml/jackson/annotation/` provide compile-time compatibility. **No custom annotation layer needed.**
2. **Native typealiases, not code migration** — `expect/actual typealias` for types that need KMP compat (Optional → java.util.Optional on JVM, Function → java.util.function.Function on JVM, JsonMapperType → Jackson JsonMapper on JVM). **Zero model file changes.**
3. **Standard specs for config** — OpenAPI, AsyncAPI, gRPC, WASM define tools + security. No proprietary config format, no custom annotations.
4. **Only stable libs are secured** — ktor, Wire, okio, kotlinx.coroutines. Custom wrappers are security liabilities.
5. **Don't duplicate stable libs** — use ktor/Wire/okio/kotlinx directly
6. **`kotlinx.kmp.util.*`** — generic KMP utilities (Optional, functional interfaces)
7. **JsonField/JsonValue = Wire field semantics** — format-agnostic, not JSON-specific
8. **commonMain is pure Kotlin** — zero `java.*` imports in api-kmp commonMain. JVM-only types (`CompletableFuture`, `Executor`, `Clock`, `Jackson`) live in jvmMain only. Common code uses `suspend fun`, `kotlinx.datetime`, `ApiJsonBackend`.
9. **Dual JSON backend** — `JacksonApiJsonBackend` (jvmMain) + `KotlinxApiJsonBackend` (commonMain). Both implement `ApiJsonBackend` open class. Jackson for backward compat on JVM; kotlinx.serialization for cross-platform.
10. **executeAsync is JVM extension, not interface** — `HttpClient` interface has only `execute()` + `executeSuspend()`. JVM gets `executeAsync()` as an extension function returning `CompletableFuture`.
11. **Flow replaces Stream** — `stream()` returns `Flow<T>` on all platforms.
12. **Retryable marker, not IOException check** — `HttpClient.withRetry` retries on any `Throwable` implementing `Retryable` interface. `ApiIoException` + `ApiRetryableException` implement it. Provider-neutral.
13. **api-gen IS the KMP path** — The 540 hand-written model files in `anthropic-java-core` are the JVM-only legacy path (Jackson + Java types). For JS/Native, use api-gen to generate KMP-native models from the Anthropic OpenAPI spec: `@Serializable` + `@SerialName` + Wire proto types. No need to convert the hand-written files to KMP — they're replaced by generated output.
14. **4 wire formats** — JSON + CBOR + Protobuf + MsgPack via `ContentFormat` enum in commonMain. All backed by kotlinx.serialization (+ third-party msgpack). Usable with ktor `ContentNegotiation` on all targets.

### What stays, what gets typealiased, what's NOT migrated

| Category | Approach | Code Migration? |
|---|---|---|
| Jackson annotations (`@JsonProperty` etc.) | **Keep** — standard, JVM runtime, ignored on non-JVM | None |
| kotlin.jvm annotations (`@JvmStatic` etc.) | **Keep** — standard, JVM bytecode, ignored on non-JVM | None |
| `java.util.Optional<T>` | **typealias** → `expect class Optional` in commonMain | None — same API |
| `java.util.function.*` | **typealias** → `expect fun interface` in commonMain | None — same API |
| Jackson annotations (`@JsonProperty` etc.) | **typealias** → `kotlinx.kmp.util.core.json.JsonProperty` (resolves to Jackson on JVM) | ✅ Done — ~600 model files rewritten |
| Jackson runtime (`JsonMapper`, serializers) | **jvmMain only** — expect/actual `jsonMapper()` + `ApiJsonBackend` | ✅ Done — `JacksonApiJsonBackend` in jvmMain |
| `java.util.concurrent.CompletableFuture` | **jvmMain extension** — `HttpClient.executeAsync()` as JVM ext, not interface | ✅ Done — commonMain suspend-only |
| `java.time.Clock` | **Replaced** — `nowMillisProvider: () -> Long` in ClientOptions | ✅ Done |
| `java.io.InputStream/OutputStream` | **okio** `BufferedSource/BufferedSink` | ✅ Done (core interfaces) |
| `java.util.concurrent.Executor` | **jvmMain** — `createDefaultStreamExecutor()` expect/actual | ✅ Done |
| Wire `@WireRpc`, `@WireField` | **Use directly** — standard proto lib | None |

### How any service becomes a secure tool

```
OpenAPI spec (YAML/JSON)          → ktor HttpClient + Auth plugin    → tool call
AsyncAPI spec (YAML/JSON)         → ktor WebSocket/SSE + Auth       → tool call
gRPC .proto service definition    → Wire GrpcClient + TLS channel   → tool call
WASM module (.wasm)               → wasmtime sandbox (memory-safe)  → tool call
MCP server (JSON-RPC)             → MCP SDK Client + Transport      → tool call
```

Security comes from the spec + the stable lib enforcing it:
- OpenAPI `securitySchemes.bearerAuth` → ktor `install(Auth) { bearer {} }`
- AsyncAPI `security.oauth2` → ktor `install(Auth) { bearer { refreshTokens {} } }`
- gRPC TLS → Wire `GrpcClient.Builder().client(tlsClient)`
- WASM sandbox → wasmtime linear memory isolation (no raw pointers)
- MCP auth → MCP SDK Transport-level authentication

## Source Reference — Key Classes & Methods

All paths relative to `anthropic-java-core/src/`.

### Core Types (Wire-style field containers) — all in api-kmp
| Class | Location |
|---|---|
| `sealed class JsonField<T>` | `api-kmp/commonMain/.../core/Values.kt` |
| `sealed class JsonValue` | `api-kmp/commonMain/.../core/Values.kt` |
| `class KnownValue<T>` | `api-kmp/commonMain/.../core/Values.kt` |
| `class JsonMissing` | `api-kmp/commonMain/.../core/Values.kt` |
| `class JsonNull` | `api-kmp/commonMain/.../core/Values.kt` |

### HTTP Interfaces (okio-based) — all in api-kmp
| Interface | Location | Key Method |
|---|---|---|
| `interface HttpClient` | `api-kmp/commonMain/.../core/http/HttpClient.kt` | `execute()`, `executeSuspend()` |
| `fun HttpClient.executeAsync()` | `api-kmp/jvmMain/.../core/http/HttpClientJvm.kt` | JVM extension → `CompletableFuture` |
| `fun HttpClient.withRetry()` | `api-kmp/commonMain/.../core/http/HttpRetry.kt` | Retry wrapper with `Retryable` marker |
| `interface HttpResponse` | `api-kmp/commonMain/.../core/http/HttpResponse.kt` | `body(): BufferedSource` |
| `interface HttpRequestBody` | `api-kmp/commonMain/.../core/http/HttpRequestBody.kt` | `writeTo(sink: BufferedSink)` |
| `interface StreamResponse<T>` | `api-kmp/commonMain/.../core/http/StreamResponse.kt` | `stream(): Flow<T>` |
| `interface HttpResponseFor<T>` | `api-kmp/commonMain/.../core/http/HttpResponseFor.kt` | `parse(): T` |

### JSON Backend (multi-platform)
| Class | Location | What |
|---|---|---|
| `open class ApiJsonBackend` | `api-kmp/commonMain/.../core/ObjectMappers.kt` | Abstract backend: `encodeToString()`, `decodeFromString()`, `encodePretty()` |
| `expect fun jsonMapper()` | `api-kmp/commonMain/.../core/ObjectMappers.kt` | Platform mapper factory |
| `class JacksonApiJsonBackend` | `api-kmp/jvmMain/.../core/ObjectMappersJvm.kt` | JVM: delegates to Jackson `JsonMapper` |
| `class KotlinxApiJsonBackend` | `api-kmp/commonMain/.../core/json/KotlinxApiJsonBackend.kt` | Multi-platform: kotlinx.serialization `Json` |
| `object JsonValueSerializer` | `api-kmp/commonMain/.../core/json/BaseKSerializer.kt` | `KSerializer<JsonValue>` ↔ `JsonElement` bridge |
| `class JsonFieldSerializer<T>` | `api-kmp/commonMain/.../core/json/BaseKSerializer.kt` | `KSerializer<JsonField<T>>` |
| `fun toJsonElement(JsonValue)` | `api-kmp/commonMain/.../core/json/BaseKSerializer.kt` | SDK JsonValue → kotlinx JsonElement |
| `fun fromJsonElement(JsonElement)` | `api-kmp/commonMain/.../core/json/BaseKSerializer.kt` | kotlinx JsonElement → SDK JsonValue |

### Jackson Annotation Typealiases
| Typealias | Resolves To (JVM) | Location |
|---|---|---|
| `JsonProperty` | `com.fasterxml.jackson.annotation.JsonProperty` | `api-kmp/commonMain/.../core/json/Annotations.kt` |
| `JsonCreator` / `JsonCreatorMode` | `com.fasterxml.jackson.annotation.JsonCreator` / `.Mode` | same |
| `JsonAnyGetter` / `JsonAnySetter` | `com.fasterxml.jackson.annotation.*` | same |
| `JsonDeserialize` / `JsonSerialize` | `com.fasterxml.jackson.databind.annotation.*` | same |
| `ObjectCodec` / `JsonNode` / `JsonGenerator` / `SerializerProvider` | `com.fasterxml.jackson.core/databind.*` | same |

### Error Classes — api-kmp kotlinx.kmp.util.core.errors
| Class | What |
|---|---|
| `ApiException` | Base (was `AnthropicException`) |
| `ApiInvalidDataException` | Data validation (was `AnthropicInvalidDataException`) |
| `ApiIoException` | I/O + implements `Retryable` |
| `ApiRetryableException` | Generic retryable + implements `Retryable` |
| `ApiServiceException` | HTTP error with status code + body |
| `BadRequestException` / `UnauthorizedException` / etc. | Specific HTTP status |
| `ErrorType` | Enum of API error types |

### Optional (expect/actual — kotlinx.kmp.util.optional)
| Declaration | Location |
|---|---|
| `expect class Optional<T>` | `commonMain/.../kotlinx/kmp/util/optional/KmpOptional.kt:24` |
| `actual typealias Optional = java.util.Optional` | `jvmMain/.../kotlinx/kmp/util/optional/OptionalJvm.kt` |
| `expect fun optionalOf(value)` | `commonMain/.../kotlinx/kmp/util/optional/KmpOptional.kt:68` |
| `fun Optional<T>.orNull(): T?` | `commonMain/.../kotlinx/kmp/util/optional/KmpOptional.kt:81` |
| `fun Optional<T>.getOrNull(): T?` | `commonMain/.../kotlinx/kmp/util/optional/KmpOptional.kt:90` |

### Functional Interfaces (expect/actual — kotlinx.kmp.util.optional)
| Declaration | Location |
|---|---|
| `expect fun interface Function<T,R>` | `commonMain/.../kotlinx/kmp/util/optional/KmpFunctional.kt:23` |
| `expect fun interface Supplier<T>` | `commonMain/.../kotlinx/kmp/util/optional/KmpFunctional.kt:35` |
| `expect fun interface Consumer<T>` | `commonMain/.../kotlinx/kmp/util/optional/KmpFunctional.kt:47` |
| `expect fun interface Predicate<T>` | `commonMain/.../kotlinx/kmp/util/optional/KmpFunctional.kt:59` |
| `expect fun interface BiFunction<T,U,R>` | `commonMain/.../kotlinx/kmp/util/optional/KmpFunctional.kt:71` |

### HttpMethod (multi-protocol enum)
| Declaration | Location |
|---|---|
| `enum class HttpMethod(value, protocol, streamMode)` | `commonMain/.../core/http/HttpMethod.kt:15` |
| `enum class Protocol { HTTP, WEBDAV, GRPC, RSOCKET, MCP }` | `commonMain/.../core/http/HttpMethod.kt:60` |
| `enum class StreamMode { UNARY, SERVER, CLIENT, BIDI }` | `commonMain/.../core/http/HttpMethod.kt:65` |

### ClientOptions & Config
| Declaration | Location |
|---|---|
| `class ClientOptions` | `commonMain/.../core/ClientOptions.kt:22` |
| `class ClientOptions.Builder` | `commonMain/.../core/ClientOptions.kt:140` |
| `fun Builder.build(): ClientOptions` | `commonMain/.../core/ClientOptions.kt:381` |
| `class Timeout` | `commonMain/.../core/Timeout.kt` |
| `class RequestOptions` | `commonMain/.../core/RequestOptions.kt` |

### Ktor Bridge
| Function | Location |
|---|---|
| `fun HttpMethod.toKtor()` | `commonMain/.../core/KtorBridge.kt:14` |
| `fun Headers.toKtor()` | `commonMain/.../core/KtorBridge.kt:17` |
| `fun io.ktor.http.Headers.toSdk()` | `commonMain/.../core/KtorBridge.kt:25` |
| `fun QueryParams.toKtor()` | `commonMain/.../core/KtorBridge.kt:32` |
| `class KtorHttpClient` | `commonMain/.../core/http/KtorHttpClient.kt:18` |

### Platform expect/actual
| Declaration | Location | JVM Actual |
|---|---|---|
| `expect fun urlEncode(value)` | `commonMain/.../core/Platform.kt:4` | `PlatformJvm.kt` → URLEncoder |
| `expect fun currentTimeNanos()` | `commonMain/.../core/PlatformTime.kt:9` | `PlatformTimeJvm.kt` → System.nanoTime |
| `expect fun parseRetryAfterToDelayNanos()` | `commonMain/.../core/PlatformTime.kt:16` | `PlatformTimeJvm.kt` → OffsetDateTime |
| `expect fun getOsArch/Name/Version()` | `commonMain/.../core/Properties.kt:3-11` | `PropertiesJvm.kt` → System.getProperty |
| `expect fun closeWhenPhantomReachable()` | `commonMain/.../core/PhantomReachable.kt:22` | `PhantomReachableJvm.kt` → java.lang.ref.Cleaner |

### Test
| Test | Location | Count |
|---|---|---|
| `OptionalTest` | `commonTest/.../kotlinx/kmp/util/optional/OptionalTest.kt` | 66 tests |

## Build & Test

```bash
# Compile
./gradlew :anthropic-java-core:compileKotlinJvm --no-configuration-cache

# Test (skip WireMock-dependent tests in CI)
SKIP_MOCK_TESTS=true ./gradlew :anthropic-java-core:jvmTest --no-configuration-cache

# Full chain
./gradlew :anthropic-java-core:compileKotlinJvm :anthropic-java-client-okhttp:compileKotlin --no-configuration-cache
```

## Next Steps (Priority Order)

1. ~~Suspend conversion~~ **DONE** — `executeSuspend()` + `prepareSuspend()` added with default impls. Zero breaking changes. Services/models stay as-is.
2. ~~`api-gen` common lib~~ **DONE** — generates KMP Kotlin from OpenAPI. Tested: Petstore (5 WireMock tests pass), Amazon SP-API (69 models).
3. ~~ktor CIO server tests~~ **DONE** — `828b03b` 6 tests: JSON GET/POST, SSE stream, WebSocket echo + JSON streaming
4. ~~Compose KMP UI generation~~ **DONE** — `2da1388` ComposeEmitter generates Form/List/Detail/MasterDetail @Composable (3 tests)
5. ~~Database generation~~ **DONE** — `2da1388` DatabaseEmitter generates Exposed tables + SQLDelight .sq (3 tests)
6. ~~JS/Native targets~~ **DONE** — `5dd4364` JS (IR) compiles zero errors; `215c819` native actuals written
7. ~~MCP SDK integration~~ **DONE** — `ab9604d` commonMain McpTypes + 5 tests; McpEmitter generates Server+Client
8. ~~MsgPack + Protobuf~~ **DONE** — `4c3f3c1` ContentFormat enum: JSON + CBOR + Protobuf (10 tests); `90bbfc9` + MsgPack (12 tests)
