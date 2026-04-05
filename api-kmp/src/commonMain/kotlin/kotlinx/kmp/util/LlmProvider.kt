package kotlinx.kmp.util

/**
 * Vendor-agnostic LLM provider contract. Concrete providers supply a base URL,
 * auth scheme, default model, and request format mapping. The Wire-generated
 * [LlmRequest]/[LlmResponse] types are the common envelope across all of them.
 *
 * Providers come in three flavors:
 *
 * 1. **Cloud APIs** — Anthropic Messages, Google Gemini GenerateContent, OpenAI
 *    Chat Completions (and any OpenAI-compatible server). These carry a URL +
 *    API key.
 *
 * 2. **Local HTTP servers** — LM Studio, llama.cpp server, Ollama, vLLM, text-
 *    generation-webui, ComfyUI. These run on localhost and usually speak the
 *    OpenAI Chat Completions protocol (except ComfyUI which has its own graph
 *    workflow API).
 *
 * 3. **In-process runtimes** — LangChain4j (wraps ~20 vendors), DJL (Deep Java
 *    Library with Huggingface tokenizers), Jlama (pure-Java llama.cpp port).
 *    These don't need a URL — the JVM bridge calls into the library directly.
 *
 * api-kmp itself owns this interface in commonMain (KMP-compatible). JVM-side
 * bridges live in `LlmBridges.kt`. Each provider is a pure data container —
 * the actual request dispatching is done by an `LlmProviderClient`
 * implementation which the bridges supply.
 *
 * **The `ktor` and `Camel` emitters in api-gen both dispatch through this
 * abstraction**, so a single spec-driven generated service can target any
 * provider by swapping the injected `LlmProvider`.
 */
sealed interface LlmProvider {
    /** Stable identifier used in URIs like `llm:messages?provider=<id>`. */
    val id: String

    /** Base URL for HTTP-based providers. Empty for in-process runtimes. */
    val baseUrl: String

    /** Default model name for this provider (overridable per request). */
    val defaultModel: String

    /** Wire format the provider expects for its chat endpoint. */
    val requestFormat: RequestFormat

    /** Authentication scheme the provider's HTTP endpoint requires. */
    val auth: AuthScheme

    // === Cloud providers ===

    /** Anthropic Messages API. `x-api-key` header auth. */
    object Anthropic : LlmProvider {
        override val id = "anthropic"
        override val baseUrl = "https://api.anthropic.com/v1"
        override val defaultModel = "claude-sonnet-4-6"
        override val requestFormat = RequestFormat.ANTHROPIC_MESSAGES
        override val auth = AuthScheme.X_API_KEY
        const val HEADER_VERSION = "anthropic-version"
        const val DEFAULT_VERSION = "2023-06-01"
    }

    /** Google Gemini GenerateContent API. `x-goog-api-key` header auth. */
    object Google : LlmProvider {
        override val id = "google"
        override val baseUrl = "https://generativelanguage.googleapis.com/v1beta"
        override val defaultModel = "gemini-2.0-flash"
        override val requestFormat = RequestFormat.GEMINI_GENERATE
        override val auth = AuthScheme.API_KEY_HEADER
        const val HEADER_API_KEY = "x-goog-api-key"
    }

    /** OpenAI Chat Completions (or any OpenAI-compatible cloud endpoint). */
    data class OpenAi(
        override val baseUrl: String = "https://api.openai.com/v1",
        override val defaultModel: String = "gpt-4o",
    ) : LlmProvider {
        override val id = "openai"
        override val requestFormat = RequestFormat.OPENAI_CHAT
        override val auth = AuthScheme.BEARER
    }

    // === Local HTTP servers (OpenAI-compatible by default) ===

    /**
     * LM Studio — OpenAI-compatible server (default `http://localhost:1234/v1`).
     * Works with any model loaded in LM Studio; the model name is set in the UI
     * and passed through [defaultModel]. Same protocol shape as llama.cpp server
     * (`--api-server`), Ollama (`/v1/...` endpoints), vLLM, and text-generation-
     * webui — so this one class covers all local OpenAI-compat backends.
     */
    data class LmStudio(
        override val baseUrl: String = "http://localhost:1234/v1",
        override val defaultModel: String = "local-model",
    ) : LlmProvider {
        override val id = "lmstudio"
        override val requestFormat = RequestFormat.OPENAI_CHAT
        override val auth = AuthScheme.NONE
    }

    /**
     * ComfyUI — node-based workflow server (default `http://localhost:8188`).
     * Unlike chat providers, ComfyUI takes a JSON workflow graph at `/prompt`
     * and returns output images via `/history/{prompt_id}`. The [workflow]
     * string is either a raw workflow JSON or a reference to a saved workflow
     * name. Intended for image/video generation + custom node pipelines.
     */
    data class ComfyUi(
        override val baseUrl: String = "http://localhost:8188",
        val workflow: String = "",
        override val defaultModel: String = "",
    ) : LlmProvider {
        override val id = "comfyui"
        override val requestFormat = RequestFormat.COMFY_WORKFLOW
        override val auth = AuthScheme.NONE
    }

    // === In-process JVM runtimes (baseUrl is empty — delegated to bridge) ===

    /**
     * LangChain4j — JVM library that wraps ~20 vendors (OpenAI, Anthropic,
     * Gemini, Bedrock, Azure, Ollama, Mistral, Cohere, HuggingFace, Qianfan,
     * Vertex AI, Watsonx, Zhipu, and more). The [vendor] field selects which
     * underlying LangChain4j `ChatLanguageModel` factory is used. This makes
     * every LangChain4j-supported vendor available as a provider for free —
     * no per-vendor code in api-kmp.
     *
     * JVM-only. Activated by the `LangChain4jBridge` in `LlmBridges.kt`.
     */
    data class LangChain4j(
        val vendor: String = "openai",                    // openai|anthropic|gemini|ollama|...
        override val defaultModel: String = "",
        val apiKey: String = "",
    ) : LlmProvider {
        override val id = "langchain4j:$vendor"
        override val baseUrl = ""
        override val requestFormat = RequestFormat.LANGCHAIN4J_NATIVE
        override val auth = AuthScheme.NONE                // managed inside langchain4j
    }

    /**
     * Deep Java Library (DJL) — model zoo + local inference with Huggingface
     * tokenizers. Use for tokenization and small on-device models. JVM-only.
     */
    data class Djl(
        val modelPath: String,
        override val defaultModel: String = modelPath,
    ) : LlmProvider {
        override val id = "djl"
        override val baseUrl = ""
        override val requestFormat = RequestFormat.DJL_NATIVE
        override val auth = AuthScheme.NONE
    }

    /**
     * Jlama — pure-Java inference engine supporting GGUF / SafeTensors /
     * HuggingFace models with quantization. Runs Llama, Mistral, Gemma,
     * Qwen, etc. entirely in-process on JVM. No network calls.
     */
    data class Jlama(
        val modelPath: String,
        override val defaultModel: String = modelPath,
    ) : LlmProvider {
        override val id = "jlama"
        override val baseUrl = ""
        override val requestFormat = RequestFormat.JLAMA_NATIVE
        override val auth = AuthScheme.NONE
    }

    /** MCP server — treats any MCP server as an LLM/tool endpoint via the MCP SDK. */
    data class Mcp(
        override val baseUrl: String,                      // stdio:///path or http+sse://host
        override val defaultModel: String = "",
        val transport: String = "sse",                     // stdio|sse
    ) : LlmProvider {
        override val id = "mcp"
        override val requestFormat = RequestFormat.MCP_TOOL
        override val auth = AuthScheme.NONE
    }

    /** No provider — placeholder for generated code that selects at install time. */
    object None : LlmProvider {
        override val id = "none"
        override val baseUrl = ""
        override val defaultModel = ""
        override val requestFormat = RequestFormat.ANTHROPIC_MESSAGES
        override val auth = AuthScheme.NONE
    }
}

/**
 * Wire format of the request body a provider expects. The `LlmProviderClient`
 * implementation picks the right translator based on this enum.
 */
enum class RequestFormat {
    ANTHROPIC_MESSAGES,     // POST /v1/messages — Claude native
    OPENAI_CHAT,            // POST /v1/chat/completions — OpenAI + compatible
    GEMINI_GENERATE,        // POST /v1beta/models/{model}:generateContent — Google
    COMFY_WORKFLOW,         // POST /prompt — ComfyUI graph workflow
    LANGCHAIN4J_NATIVE,     // no wire format — in-process JVM bridge
    DJL_NATIVE,             // no wire format — in-process JVM bridge
    JLAMA_NATIVE,           // no wire format — in-process JVM bridge
    MCP_TOOL,               // MCP JSON-RPC tools/call
}

/** HTTP authentication scheme. Maps 1:1 to OpenAPI `securitySchemes` types. */
enum class AuthScheme {
    BEARER,           // Authorization: Bearer <token>
    X_API_KEY,        // x-api-key: <key>            (Anthropic)
    API_KEY_HEADER,   // custom header: <key>        (Google, etc.)
    API_KEY_QUERY,    // ?key=<value>                (Google legacy)
    BASIC,            // Authorization: Basic <base64>
    NONE,             // local/in-process
}

/**
 * Runtime abstraction over a provider. Implementations (in jvmMain `LlmBridges.kt`)
 * take a [LlmProvider] and translate Wire [LlmRequest]/[LlmResponse] to/from
 * the provider's native wire or API.
 *
 * This interface is declared in commonMain so generated services can depend on
 * it without a JVM-only import; the actual implementations are JVM bridges.
 */
interface LlmProviderClient {
    val provider: LlmProvider

    /** Synchronous (suspending) single-shot completion. */
    suspend fun complete(request: LlmRequest): LlmResponse

    /** Streaming completion — one [LlmStreamEvent] per server-sent delta. */
    suspend fun stream(request: LlmRequest): kotlinx.coroutines.flow.Flow<LlmStreamEvent>

    /** Count tokens without invoking the model. Optional; may throw if unsupported. */
    suspend fun countTokens(request: LlmRequest): Int = -1
}
