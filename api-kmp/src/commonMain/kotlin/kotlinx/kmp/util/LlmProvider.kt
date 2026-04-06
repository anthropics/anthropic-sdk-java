package kotlinx.kmp.util

import kotlin.jvm.JvmStatic

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

    /** Google Gemini GenerateContent API (text + vision). `x-goog-api-key` header auth. */
    object Google : LlmProvider {
        override val id = "google"
        override val baseUrl = "https://generativelanguage.googleapis.com/v1beta"
        override val defaultModel = "gemini-2.0-flash"
        override val requestFormat = RequestFormat.GEMINI_GENERATE
        override val auth = AuthScheme.API_KEY_HEADER
        const val HEADER_API_KEY = "x-goog-api-key"
    }

    /**
     * Google **Nano Banana** (`gemini-2.5-flash-image`) — image generation +
     * editing via the same Generative Language API. Returns image bytes
     * inline in ContentBlock.image.data. Accepts text prompts plus optional
     * reference images (inline base64 or multipart upload on the public API).
     *
     * Same `x-goog-api-key` auth as [Google]. Served via the standard
     * `models/{model}:generateContent` endpoint — only the model name and
     * response handling differ (images not text).
     */
    object GoogleNanoBanana : LlmProvider {
        override val id = "google-nano-banana"
        override val baseUrl = "https://generativelanguage.googleapis.com/v1beta"
        override val defaultModel = "gemini-2.5-flash-image"
        override val requestFormat = RequestFormat.GEMINI_GENERATE
        override val auth = AuthScheme.API_KEY_HEADER
    }

    /**
     * Google **Veo** (text-to-video / image-to-video). Uses the long-running
     * operation pattern: POST `models/{model}:predictLongRunning` returns an
     * operation name; client polls `operations/{id}` until `done=true`.
     *
     * Image-to-video can accept reference images either inline (base64 in
     * JSON) or via Files API upload (multipart) — both paths supported.
     */
    data class GoogleVeo(
        override val defaultModel: String = "veo-3.0-generate-preview",
    ) : LlmProvider {
        override val id = "google-veo"
        override val baseUrl = "https://generativelanguage.googleapis.com/v1beta"
        override val requestFormat = RequestFormat.GEMINI_VEO
        override val auth = AuthScheme.API_KEY_HEADER
    }

    /**
     * Google **NotebookLM** — source-grounded research assistant. Access
     * tiers:
     * - **NotebookLM Enterprise** via Vertex AI — official REST API at
     *   `https://{region}-notebooklm.googleapis.com` (requires GCP project
     *   + OAuth2 / service-account auth, not simple API key).
     * - **Public consumer** (notebooklm.google.com) — no official public
     *   REST API as of late 2025; community gateways exist but are unstable.
     *
     * Capabilities: upload source documents (PDF, URL, YouTube, text →
     * Files API-style multipart upload), ask grounded questions,
     * generate audio overviews (podcast-style TTS).
     *
     * Default base URL points to the Vertex AI Enterprise endpoint. Override
     * via constructor with your actual region + project.
     */
    data class GoogleNotebookLm(
        val region: String = "us-central1",
        val projectId: String = "",
        override val defaultModel: String = "notebooklm-default",
    ) : LlmProvider {
        override val id = "google-notebooklm"
        override val baseUrl = "https://$region-notebooklm.googleapis.com/v1beta"
        override val requestFormat = RequestFormat.NOTEBOOKLM_GROUNDED
        override val auth = AuthScheme.BEARER  // OAuth2 access token from gcloud
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
     * LM Studio — OpenAI-compatible local server (default `http://localhost:1234/v1`).
     * Also covers **llama.cpp server, Ollama, vLLM, text-generation-webui** — any
     * local backend exposing the OpenAI-compatible endpoint set.
     *
     * **Supported beyond `/v1/chat/completions`:**
     * - `/v1/models`            — list loaded models
     * - `/v1/completions`       — legacy text completion
     * - `/v1/embeddings`        — embedding models (e.g. Google EmbeddingGemma)
     * - `/v1/responses`         — stateful multi-turn via `previous_response_id`
     * - **Vision** — image content blocks in chat messages (requires a vision-
     *                capable model loaded, e.g. LLaVA, Qwen2-VL, Llama 3.2 Vision)
     * - **Tool / function calling** — full OpenAI-compatible `tools` + `tool_choice`
     *                                  (requires a tool-trained model, e.g. Llama 3.1,
     *                                  Qwen 2.5, Mistral Nemo)
     * - **Structured outputs** — `response_format = { type: "json_schema", ... }`
     * - **Reasoning blocks** — `openai/gpt-oss-20b` + similar models emit thinking
     *                           content that maps to Wire [ThinkingBlock]
     *
     * Each capability depends on the specific model loaded in LM Studio — the
     * server relays whatever the model supports. api-kmp's Wire [LlmRequest]
     * envelope carries all four feature dimensions (images via `ImageBlock`,
     * tools via `Tool`, structured output via `response_format` metadata,
     * reasoning via `ThinkingBlock`) so the same client works against any
     * LM Studio model without code changes.
     */
    data class LmStudio(
        override val baseUrl: String = "http://localhost:1234/v1",
        override val defaultModel: String = "local-model",
    ) : LlmProvider {
        override val id = "lmstudio"
        override val requestFormat = RequestFormat.OPENAI_CHAT
        override val auth = AuthScheme.NONE
        companion object {
            /** Supported endpoints beyond chat/completions. */
            val ENDPOINTS = listOf(
                "/v1/models",
                "/v1/chat/completions",
                "/v1/completions",
                "/v1/embeddings",
                "/v1/responses",
            )
        }
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

    // === Media generation providers (exercise file upload / multipart path) ===

    /**
     * Suno AI — music generation. Accepts text prompts + reference audio
     * file uploads via multipart/form-data and returns generated tracks.
     *
     * **Note on base URL:** Suno does not publish a single official public
     * REST API. Users supply the base URL of their chosen gateway
     * (e.g. `sunoapi.org`, `apibox.erweima.ai`, `gcop.ai`, `acedata.cloud`)
     * since each has slightly different paths and auth conventions. The
     * default below is a placeholder — override via constructor.
     *
     * Exercises the **multipart/form-data upload** code path of the
     * generator: reference-audio endpoint accepts an `audio` field with
     * content-type audio/mpeg or audio/wav.
     */
    data class Suno(
        override val baseUrl: String = "https://api.sunoapi.org/v1",
        override val defaultModel: String = "chirp-v4",
    ) : LlmProvider {
        override val id = "suno"
        override val requestFormat = RequestFormat.SUNO_MUSIC
        override val auth = AuthScheme.BEARER
    }

    /**
     * PixVerse AI — video generation (text-to-video + image-to-video).
     * Image-to-video uses a two-step flow: upload image via
     * multipart/form-data then POST JSON referencing the returned img_id.
     *
     * **Note on base URL:** PixVerse platform base URL varies by tenant.
     * Common default: `https://app-api.pixverse.ai/openapi/v2`. Override
     * via constructor for your deployment.
     *
     * Auth: custom `API-KEY` header (not Bearer). Also sends `Ai-trace-id`
     * per request for support tracing.
     */
    data class PixVerse(
        override val baseUrl: String = "https://app-api.pixverse.ai/openapi/v2",
        override val defaultModel: String = "v3.5",
    ) : LlmProvider {
        override val id = "pixverse"
        override val requestFormat = RequestFormat.PIXVERSE_VIDEO
        override val auth = AuthScheme.API_KEY_HEADER
        companion object {
            const val HEADER_API_KEY = "API-KEY"
            const val HEADER_TRACE_ID = "Ai-trace-id"
        }
    }

    /**
     * **Azure AI / Azure OpenAI** — Microsoft's hosted OpenAI models plus
     * Azure-exclusive models (Phi, Mistral-via-Azure, etc.). Also the backend
     * for **Microsoft Copilot Designer** (image generation uses DALL-E 3 /
     * GPT-Image-1 deployed via Azure OpenAI).
     *
     * Base URL pattern (tenant-specific):
     *   `https://<resource>.openai.azure.com/openai/deployments/<deployment>`
     *
     * Auth: custom `api-key` header (not Bearer). An `api-version` query
     * param is **required** on every request (e.g. `2024-10-21`,
     * `2024-02-15-preview`).
     *
     * Supports both text chat completions (OpenAI-compatible) and image
     * generation via `:images/generations` — pass a DALL-E 3 deployment
     * name as [defaultModel] to use it as a Copilot Designer backend.
     */
    data class AzureAi(
        val resourceName: String = "my-resource",
        val deployment: String = "gpt-4o",
        val apiVersion: String = "2024-10-21",
    ) : LlmProvider {
        override val id = "azure"
        override val baseUrl: String =
            "https://$resourceName.openai.azure.com/openai/deployments/$deployment"
        override val defaultModel: String = deployment
        override val requestFormat = RequestFormat.AZURE_OPENAI
        override val auth = AuthScheme.API_KEY_HEADER
        companion object {
            const val HEADER_API_KEY = "api-key"
            const val QUERY_API_VERSION = "api-version"

            /**
             * Convenience factory for **Microsoft Copilot Designer** style
             * image generation via Azure-hosted DALL-E 3 / GPT-Image.
             * Usage: `LlmProvider.AzureAi.copilotDesigner("my-resource", "dalle-3")`.
             */
            @JvmStatic
            fun copilotDesigner(
                resourceName: String,
                dalleDeployment: String = "dall-e-3",
            ): AzureAi = AzureAi(
                resourceName = resourceName,
                deployment = dalleDeployment,
                apiVersion = "2024-02-15-preview",
            )
        }
    }

    /**
     * **Adobe Firefly Services** — Adobe's generative AI API (text-to-image,
     * generative fill, generative expand, generative match, text-to-vector,
     * text-to-video, object composite). Also powers Adobe Express AI.
     *
     * Base URL: `https://firefly-api.adobe.io/` (v3).
     *
     * Auth requires **two headers simultaneously**:
     *   - `Authorization: Bearer <IMS_access_token>`  (from Adobe IMS OAuth2)
     *   - `x-api-key: <client_id>`                    (your Adobe dev API key)
     *
     * Uses async job pattern: POST returns a job URL; client polls it until
     * the generation completes and returns signed URLs to the output assets.
     *
     * File uploads (reference images, style references, masks) go via a
     * separate Storage API at `https://firefly-api.adobe.io/v2/storage/image`
     * using multipart/form-data.
     */
    data class AdobeFirefly(
        val clientId: String = "",
        override val baseUrl: String = "https://firefly-api.adobe.io",
        override val defaultModel: String = "firefly-v3",
    ) : LlmProvider {
        override val id = "adobe-firefly"
        override val requestFormat = RequestFormat.ADOBE_FIREFLY
        override val auth = AuthScheme.BEARER  // PLUS the x-api-key header — bridge handles both
        companion object {
            const val HEADER_API_KEY = "x-api-key"
            const val STORAGE_UPLOAD_PATH = "/v2/storage/image"
            const val GENERATE_IMAGE_PATH = "/v3/images/generate-async"
            const val GENERATE_VIDEO_PATH = "/v3/videos/generate-async"
        }
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
    SUNO_MUSIC,             // Suno gateway — music gen + reference audio upload
    PIXVERSE_VIDEO,         // PixVerse — text-to-video + image-to-video (multipart upload)
    GEMINI_VEO,             // Google Veo — long-running video ops (poll operations/{id})
    NOTEBOOKLM_GROUNDED,    // NotebookLM (Vertex Enterprise) — source-grounded QA + audio overview
    AZURE_OPENAI,           // Azure OpenAI — OpenAI-compatible + Copilot Designer (DALL-E 3)
    ADOBE_FIREFLY,          // Adobe Firefly Services — async image/video/vector generation
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
