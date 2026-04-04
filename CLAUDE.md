# CLAUDE.md — KMP Conversion Project Guide

## Project Overview

Converting `anthropic-sdk-java` from JVM-only to Kotlin Multiplatform (KMP).
The core principle: **use stable KMP libs directly, don't duplicate them**.

## Key Documents

- **Migration Plan + Low-Level Design**: [`docs/KMP-CONVERSION-PLAN.md`](docs/KMP-CONVERSION-PLAN.md)
- **Branch**: `claude/convert-to-kmp-I9zBV`
- **58 commits** on branch, all pushed

## Current Status

| Metric | Value |
|---|---|
| Files in commonMain | 614 (608 com.anthropic + 6 kotlinx.kmp.util) |
| Files in jvmMain | 7 (PlatformJvm, PlatformTimeJvm, PropertiesJvm, PhantomReachableJvm, ServiceExceptionExtensions, OptionalJvm, FunctionalJvm) |
| java.* imports remaining in core | 42 (all CompletableFuture/Executor/Clock — blocked on suspend conversion) |
| JVM tests | 2,682 pass |
| commonTest tests | 66 (KmpOptional) |

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

### 🔲 Remaining Work
| Section | Line | What |
|---|---|---|
| GAPS — 22 core files | L1267 | Phase 0-4 tables with ✅/🔲 per file |
| GAPS — 730 lines custom code | L1567 | RetryingHttpClient, MultipartBody, SseHandler to delete |
| GAPS — MCP SDK Integration | L1333 | Not yet added |

### Low-Level Designs
| Section | Line | What |
|---|---|---|
| **Stable Libs Strategy** | L691 | Core principle: libs replace all boilerplate |
| **Serialization Formats** | L710 | JSON + MsgPack + Protobuf + CBOR via ktor ContentNegotiation |
| **Custom Code → Lib Map** | L736 | 730 lines to delete, mapped to stable lib replacements |
| **JsonField/Value = Wire** | L752 | Field presence semantics: KnownValue/Missing/Null = Wire @WireField |
| **Deep Classification** | L780 | Nothing in com.anthropic.core is Claude-specific |
| **ClientOptions → ktor** | L833 | Field-by-field mapping to ktor CIO plugins + OkHttp |
| **OpenAPI Security Schemes** | L948 | OIDC/OAuth2/Bearer/Basic/MutualTLS via ktor Auth |
| **Tool Roles + Security** | L1076 | OpenAPI/AsyncAPI/CLI/POSIX unified role model |
| **Concurrency Control** | L1076 | Mutex + Semaphore (kotlinx.coroutines.sync) |
| **LangChain4j Integration** | L1953 | JVM-only, ChatLanguageModel + Tokenizer |
| **Apache Camel Integration** | L2031 | JVM-only, Component + Endpoint + Producer |
| **MCP SDK Integration** | L2113 | KMP, McpClient + Transport + Tool bridge |
| **Protobuf/gRPC (Wire)** | L2227 | KMP, Wire plugin + proto codegen + GrpcClient |
| **MessagePack** | L2352 | ktor ContentNegotiation + kotlinx-serialization-msgpack |
| **Annotations Inventory** | L2510 | All annotations: Jackson, Kotlin, Wire, custom — migration status (section A) |
| **Folder Structure** | L2581 | Actual current state of commonMain/jvmMain/commonTest |
| **kotlinx.serialization Patterns** | L2735 | Model class patterns for @Serializable migration |

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

1. **Don't duplicate stable libs** — use ktor/Wire/okio/kotlinx directly
2. **Only stable libs are secured** — ktor Auth (not custom SecurityScheme), ktor RateLimit (not custom wrappers), kotlinx Mutex/Semaphore (not ToolExecutor), okio FileSystem (not custom POSIX), Wire GrpcClient (not custom gRPC auth)
3. **`kotlinx.kmp.util.*`** — generic KMP utilities (Optional, functional interfaces)
4. **`com.anthropic.core`** — config constants only (~50 lines truly Anthropic-specific)
5. **JsonField/JsonValue = Wire field semantics** — format-agnostic, not JSON-specific
6. **expect/actual typealias** works for Optional, Function, Supplier — NOT for CompletableFuture (Java SAM mismatch)
7. **suspend replaces CompletableFuture** — JVM backward compat via `runBlocking` wrappers in jvmMain
8. **Flow replaces Stream** — `stream()` returns `Flow<T>` on all platforms
9. **Security via stable libs only** — ktor Auth for all OpenAPI schemes, ktor RateLimit for throttling, kotlinx.coroutines.sync for Mutex/Semaphore, okio for file permissions, Wire for gRPC TLS channels

## Source Reference — Key Classes & Methods

All paths relative to `anthropic-java-core/src/`.

### Core Types (Wire-style field containers)
| Class | Location |
|---|---|
| `sealed class JsonField<T>` | `commonMain/.../core/Values.kt:5` |
| `sealed class JsonValue` | `commonMain/.../core/Values.kt:86` |
| `class KnownValue<T>` | `commonMain/.../core/Values.kt:119` |
| `class JsonMissing` | `commonMain/.../core/Values.kt:127` |
| `class JsonNull` | `commonMain/.../core/Values.kt:132` |

### HTTP Interfaces (okio-based)
| Interface | Location | Key Method |
|---|---|---|
| `interface HttpClient` | `commonMain/.../core/http/HttpClient.kt:6` | `execute()`, `executeAsync()` |
| `interface HttpResponse` | `commonMain/.../core/http/HttpResponse.kt:5` | `body(): BufferedSource` |
| `interface HttpRequestBody` | `commonMain/.../core/http/HttpRequestBody.kt:5` | `writeTo(sink: BufferedSink)` |
| `interface StreamResponse<T>` | `commonMain/.../core/http/StreamResponse.kt:6` | `stream(): Flow<T>` |
| `interface HttpResponseFor<T>` | `commonMain/.../core/http/HttpResponseFor.kt:5` | `parse(): T` |

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

1. **Suspend conversion** — CompletableFuture→suspend in 10 core files (removes 30 java.* imports)
2. **Delete RetryingHttpClient** — use ktor HttpRequestRetry (120 lines)
3. **Delete MultipartBody** — use ktor MultiPartFormDataContent (130 lines)
4. **Delete SseHandler** — use ktor SSE plugin (70 lines)
5. **MCP SDK integration** — add dependency, create tool bridge
6. **MsgPack + Protobuf** — add ktor ContentNegotiation formats
