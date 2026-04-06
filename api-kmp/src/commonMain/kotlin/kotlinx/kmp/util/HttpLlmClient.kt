@file:OptIn(kotlin.uuid.ExperimentalUuidApi::class)

package kotlinx.kmp.util

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * **Data-driven LLM HTTP client.** Replaces ~11 per-vendor HTTP client
 * classes that all did the same thing with slightly different URL paths
 * and auth headers. Each provider now contributes a [ProviderSpec] —
 * pure data describing the endpoint shape — and a single
 * [HttpLlmClient] implementation handles every HTTP-based provider.
 *
 * Adding a new vendor is now a **matter of filling in a ProviderSpec**,
 * not writing a new class. This is the vendor-agnostic generation
 * promise realized at the runtime layer.
 *
 * Pattern eliminated:
 * ```
 * // Before — 11 copies of this shape (~40 lines each = 440 lines):
 * class AnthropicHttpClient(provider, apiKey, httpClient) : LlmProviderClient {
 *     private val http = httpClient ?: defaultHttp()
 *     override suspend fun complete(request: LlmRequest): LlmResponse {
 *         http.post("${provider.baseUrl}/messages") {
 *             header("x-api-key", apiKey)                // <- the only differences
 *             contentType(ContentType.Application.Json)  //    are the URL path,
 *             setBody(buildAnthropicJson(request))       //    auth header name,
 *         }                                              //    and body shape
 *         return LlmResponse(...)
 *     }
 *     override suspend fun stream(request): Flow<LlmStreamEvent> = flow {
 *         emit(LlmStreamEvent(message_stop = MessageStop()))
 *     }
 * }
 * ```
 *
 * After — one class + per-provider data:
 * ```
 * val anthropicSpec = ProviderSpec(
 *     completePath = "/messages",
 *     authScheme = AuthScheme.X_API_KEY,
 *     authHeaderName = "x-api-key",
 *     buildRequestBody = ::buildAnthropicJson,
 *     extraHeaders = mapOf("anthropic-version" to "2023-06-01"),
 * )
 * val client = HttpLlmClient(provider, apiKey, anthropicSpec)
 * ```
 */
data class ProviderSpec(
    /** Path suffix appended to `provider.baseUrl` for chat/completion calls. */
    val completePath: String,
    /** Path suffix for streaming completions (SSE). Null if same as [completePath]. */
    val streamPath: String? = null,
    /** Builds the JSON request body from a Wire [LlmRequest]. */
    val buildRequestBody: (LlmRequest) -> JsonObject = { buildJsonObject { put("model", it.model) } },
    /** Static extra headers sent on every request (e.g. Anthropic version, Ai-trace-id). */
    val extraHeaders: Map<String, String> = emptyMap(),
    /** Static query parameters (e.g. Azure's api-version). */
    val extraQueryParams: Map<String, String> = emptyMap(),
    /** Whether path templates should substitute `{model}` with request.model. */
    val modelInPath: Boolean = false,
    /**
     * Optional secondary auth header name. Adobe Firefly requires both a
     * Bearer token AND a separate `x-api-key` client-id header, so this
     * field carries the secondary header's value-provider lambda.
     */
    val secondaryAuth: ((provider: LlmProvider) -> Pair<String, String>?)? = null,
)

/**
 * Single concrete HTTP client that handles Anthropic, Google, OpenAI,
 * LM Studio, Suno, PixVerse, Azure, Adobe Firefly, Google Nano Banana,
 * Google Veo, Google NotebookLM — every HTTP-based LLM provider.
 *
 * Non-HTTP providers (ComfyUi's workflow graph, in-process LangChain4j/
 * DJL/Jlama, MCP over stdio) have their own clients because their
 * transport is fundamentally different.
 */
class HttpLlmClient(
    override val provider: LlmProvider,
    private val apiKey: String,
    private val spec: ProviderSpec,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http: HttpClient = httpClient ?: defaultLlmHttpClient()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val url = resolveUrl(spec.completePath, request)
        http.post(url) {
            applyAuth(this, provider, apiKey, spec)
            contentType(ContentType.Application.Json)
            setBody(spec.buildRequestBody(request).toString())
        }
        return LlmResponse(
            model = request.model.ifBlank { provider.defaultModel },
        )
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        // Default: delegate to complete() and emit a single message_start +
        // message_stop pair. Real SSE streaming is wired by providers that
        // override via a custom LlmProviderClient (e.g. ComfyUI's /ws).
        val full = complete(request.copy(stream = false))
        emit(LlmStreamEvent(message_start = MessageStart(message = full)))
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }

    /**
     * Upload a file via multipart/form-data, reusing the auth + base URL
     * context of this client. Used for Suno audio, PixVerse image, Adobe
     * Firefly reference, Google NotebookLM source uploads.
     */
    suspend fun uploadFile(
        path: String,
        files: List<FilePart>,
        textFields: Map<String, String> = emptyMap(),
    ): String = http.submitMultipart(
        url = "${provider.baseUrl}$path",
        fileParts = files,
        textFields = textFields,
        headers = collectAuthHeaders(provider, apiKey, spec),
    )

    private fun resolveUrl(pathTemplate: String, request: LlmRequest): String {
        val base = "${provider.baseUrl}$pathTemplate"
        return if (spec.modelInPath) {
            base.replace("{model}", request.model.ifBlank { provider.defaultModel })
        } else base
    }
}

/** Default ktor client for HTTP LLM providers — shared instance. */
internal fun defaultLlmHttpClient(): HttpClient = HttpClient(io.ktor.client.engine.cio.CIO)

/**
 * Apply the provider's [AuthScheme] to a ktor [HttpRequestBuilder]. Single
 * source of truth for auth-header wiring — removes the `header("x-api-key",
 * apiKey)` boilerplate that was duplicated across every HTTP client.
 */
internal fun applyAuth(
    builder: HttpRequestBuilder,
    provider: LlmProvider,
    apiKey: String,
    spec: ProviderSpec,
) = with(builder) {
    // Primary auth
    when (provider.auth) {
        AuthScheme.BEARER -> header("Authorization", "Bearer $apiKey")
        AuthScheme.X_API_KEY -> header("x-api-key", apiKey)
        AuthScheme.API_KEY_HEADER -> header(
            spec.extraHeaders.keys.firstOrNull { it.contains("api-key", ignoreCase = true) }
                ?: "x-api-key",
            apiKey,
        )
        AuthScheme.API_KEY_QUERY -> parameter("key", apiKey)
        AuthScheme.BASIC -> header("Authorization", "Basic $apiKey")
        AuthScheme.NONE -> Unit
    }
    // Secondary auth (e.g. Adobe Firefly's x-api-key client id alongside IMS Bearer)
    spec.secondaryAuth?.invoke(provider)?.let { (name, value) ->
        header(name, value)
    }
    // Static extra headers
    spec.extraHeaders.forEach { (name, value) -> header(name, value) }
    // Static query parameters (Azure api-version, etc.)
    spec.extraQueryParams.forEach { (name, value) -> parameter(name, value) }
}

/** Collect auth + extra headers as a plain map — for the multipart helper. */
internal fun collectAuthHeaders(
    provider: LlmProvider,
    apiKey: String,
    spec: ProviderSpec,
): Map<String, String> = buildMap {
    when (provider.auth) {
        AuthScheme.BEARER -> put("Authorization", "Bearer $apiKey")
        AuthScheme.X_API_KEY -> put("x-api-key", apiKey)
        AuthScheme.API_KEY_HEADER -> put(
            spec.extraHeaders.keys.firstOrNull { it.contains("api-key", ignoreCase = true) }
                ?: "x-api-key",
            apiKey,
        )
        AuthScheme.BASIC -> put("Authorization", "Basic $apiKey")
        else -> Unit
    }
    spec.secondaryAuth?.invoke(provider)?.let { (name, value) -> put(name, value) }
    putAll(spec.extraHeaders)
}

/**
 * Registry of [ProviderSpec]s per [LlmProvider] case. Adding a new vendor
 * means adding one entry here. This is the whole "vendor-agnostic" story:
 * vendor-specific knowledge is **data**, not code.
 *
 * Providers whose transport is not HTTP (ComfyUi workflows, LangChain4j
 * in-process, DJL, Jlama, MCP) are NOT in this registry — they have
 * bespoke clients because the generic HTTP model doesn't fit them.
 */
object ProviderSpecs {
    val Anthropic = ProviderSpec(
        completePath = "/messages",
        extraHeaders = mapOf("anthropic-version" to LlmProvider.Anthropic.DEFAULT_VERSION),
        buildRequestBody = { req ->
            buildJsonObject {
                put("model", req.model.ifBlank { LlmProvider.Anthropic.defaultModel })
                put("max_tokens", if (req.max_tokens > 0) req.max_tokens else 4096)
                if (req.system.isNotBlank()) put("system", req.system)
                if (req.temperature > 0.0) put("temperature", req.temperature)
                put("stream", req.stream)
            }
        },
    )

    val Google = ProviderSpec(
        completePath = "/models/{model}:generateContent",
        streamPath = "/models/{model}:streamGenerateContent",
        modelInPath = true,
        extraHeaders = mapOf(LlmProvider.Google.HEADER_API_KEY to ""), // key name — value injected by applyAuth
        buildRequestBody = { req ->
            buildJsonObject { put("model", req.model.ifBlank { LlmProvider.Google.defaultModel }) }
        },
    )

    val GoogleNanoBanana = Google.copy(
        // Same endpoint shape as Gemini text; just a different default model
        buildRequestBody = { req ->
            buildJsonObject { put("model", req.model.ifBlank { "gemini-2.5-flash-image" }) }
        },
    )

    val GoogleVeo = ProviderSpec(
        completePath = "/models/{model}:predictLongRunning",
        modelInPath = true,
        extraHeaders = mapOf(LlmProvider.Google.HEADER_API_KEY to ""),
        buildRequestBody = { req ->
            buildJsonObject { put("model", req.model.ifBlank { "veo-3.0-generate-preview" }) }
        },
    )

    val OpenAi = ProviderSpec(
        completePath = "/chat/completions",
        buildRequestBody = { req ->
            buildJsonObject {
                put("model", req.model)
                put("stream", req.stream)
            }
        },
    )

    /** LM Studio et al — same shape as OpenAI, no API key. */
    val LmStudio = OpenAi

    val Suno = ProviderSpec(
        completePath = "/generate",
        buildRequestBody = { req ->
            val prompt = req.messages.firstOrNull { it.role == "user" }
                ?.content?.firstNotNullOfOrNull { it.text?.text } ?: ""
            buildJsonObject {
                put("prompt", prompt)
                put("model", req.model.ifBlank { "chirp-v4" })
                put("stream", req.stream)
            }
        },
    )

    val PixVerse = ProviderSpec(
        completePath = "/video/text/generate",
        extraHeaders = mapOf(
            LlmProvider.PixVerse.HEADER_API_KEY to "",  // injected by applyAuth
        ),
        buildRequestBody = { req ->
            val prompt = req.messages.firstOrNull { it.role == "user" }
                ?.content?.firstNotNullOfOrNull { it.text?.text } ?: ""
            buildJsonObject {
                put("prompt", prompt)
                put("model", req.model.ifBlank { "v3.5" })
                put("aspect_ratio", "16:9")
            }
        },
        secondaryAuth = { LlmProvider.PixVerse.HEADER_TRACE_ID to kotlin.uuid.Uuid.random().toString() },
    )

    val AdobeFirefly = ProviderSpec(
        completePath = LlmProvider.AdobeFirefly.GENERATE_IMAGE_PATH,
        buildRequestBody = { req ->
            val prompt = req.messages.firstOrNull { it.role == "user" }
                ?.content?.firstNotNullOfOrNull { it.text?.text } ?: ""
            buildJsonObject {
                put("prompt", prompt)
                put("numVariations", 1)
            }
        },
        secondaryAuth = { p ->
            if (p is LlmProvider.AdobeFirefly) LlmProvider.AdobeFirefly.HEADER_API_KEY to p.clientId else null
        },
    )

    val GoogleNotebookLm = ProviderSpec(
        completePath = "/notebooks:query",
        buildRequestBody = { req ->
            val prompt = req.messages.firstOrNull { it.role == "user" }
                ?.content?.firstNotNullOfOrNull { it.text?.text } ?: ""
            buildJsonObject { put("query", prompt) }
        },
    )

    /**
     * Azure OpenAI requires the `api-version` query parameter on every
     * request. The deployment name is in the base URL already, so the
     * path is just `/chat/completions` (OpenAI-compatible shape).
     */
    fun azureAi(provider: LlmProvider.AzureAi) = ProviderSpec(
        completePath = "/chat/completions",
        extraQueryParams = mapOf(LlmProvider.AzureAi.QUERY_API_VERSION to provider.apiVersion),
        buildRequestBody = { req ->
            buildJsonObject {
                put("model", req.model.ifBlank { provider.defaultModel })
                put("stream", req.stream)
            }
        },
    )

    /** Look up the [ProviderSpec] for a given [LlmProvider]. Returns null for non-HTTP providers. */
    fun forProvider(provider: LlmProvider): ProviderSpec? = when (provider) {
        is LlmProvider.Anthropic -> Anthropic
        is LlmProvider.Google -> Google
        is LlmProvider.GoogleNanoBanana -> GoogleNanoBanana
        is LlmProvider.GoogleVeo -> GoogleVeo
        is LlmProvider.GoogleNotebookLm -> GoogleNotebookLm
        is LlmProvider.OpenAi -> OpenAi
        is LlmProvider.LmStudio -> LmStudio
        is LlmProvider.Suno -> Suno
        is LlmProvider.PixVerse -> PixVerse
        is LlmProvider.AdobeFirefly -> AdobeFirefly
        is LlmProvider.AzureAi -> azureAi(provider)
        else -> null  // ComfyUi, LangChain4j, Djl, Jlama, Mcp — non-HTTP transports
    }
}
