# CLAUDE.md — KMP Conversion Project Guide

## Project Overview

Converting `anthropic-sdk-java` from JVM-only to Kotlin Multiplatform (KMP).
The core principle: **use stable KMP libs directly, don't duplicate them**.

## Key Documents

- **Migration Plan + Low-Level Design**: [`docs/KMP-CONVERSION-PLAN.md`](docs/KMP-CONVERSION-PLAN.md)
- **Branch**: `claude/convert-to-kmp-I9zBV`
- **107 commits** on branch, all pushed

## Current Status

| Metric | Value |
|---|---|
| Files in commonMain | 602 |
| Files in jvmMain | 3 (PlatformJvm, PropertiesJvm, ServiceExceptionExtensions) |
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
| GAPS — 730 lines custom code | L1248 | RetryingHttpClient, MultipartBody, SseHandler to delete |
| GAPS — MCP SDK Integration | L1262 | Not yet added |

### Low-Level Designs
| Section | Line | What |
|---|---|---|
| **Stable Libs Strategy** | L691 | Core principle: libs replace all boilerplate |
| **Serialization Formats** | L710 | JSON + MsgPack + Protobuf + CBOR via ktor ContentNegotiation |
| **Custom Code → Lib Map** | L724 | 730 lines to delete, mapped to stable lib replacements |
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
| **Annotations Inventory** | L2510 | All annotations: Jackson, Kotlin, Wire, custom — migration status |
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
2. **`kotlinx.kmp.util.*`** — generic KMP utilities (Optional, functional interfaces)
3. **`com.anthropic.core`** — config constants only (~50 lines truly Anthropic-specific)
4. **JsonField/JsonValue = Wire field semantics** — format-agnostic, not JSON-specific
5. **expect/actual typealias** works for Optional, Function, Supplier — NOT for CompletableFuture (Java SAM mismatch)
6. **suspend replaces CompletableFuture** — JVM backward compat via `runBlocking` wrappers in jvmMain
7. **Flow replaces Stream** — `stream()` returns `Flow<T>` on all platforms
8. **All OpenAPI security schemes** via ktor Auth plugin (apiKey, bearer, OAuth2, OIDC, basic, mTLS)
9. **Tool security** — Mutex/Semaphore for concurrency, @ToolSecurity for role-based access

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
