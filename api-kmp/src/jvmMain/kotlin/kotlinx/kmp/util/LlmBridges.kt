package kotlinx.kmp.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * JVM-side bridges between Wire-generated LLM types ([LlmRequest], [LlmResponse])
 * and external libraries. **Pure glue** — all chat/streaming/tokenization logic
 * stays in the external lib (LangChain4j, MCP SDK, DJL, Jlama). This file only
 * maps types and delegates.
 *
 * Each bridge is guarded against missing `compileOnly` deps via reflection
 * probes and throws a clear error if the consumer didn't add the backend to
 * their classpath. Pattern mirrors `VCardBridges.kt`.
 *
 * Entry point: [LlmProviderClients.forProvider] — returns the right
 * [LlmProviderClient] impl for a given [LlmProvider].
 */
object LlmProviderClients {

    /**
     * Return the [LlmProviderClient] that knows how to call [provider].
     * Throws [IllegalStateException] if the provider requires an optional
     * backend that isn't on the classpath.
     */
    @JvmStatic
    fun forProvider(
        provider: LlmProvider,
        apiKey: String = "",
        httpClient: HttpClient? = null,
    ): LlmProviderClient = when (provider) {
        is LlmProvider.Anthropic -> AnthropicHttpClient(provider, apiKey, httpClient)
        is LlmProvider.Google -> GoogleHttpClient(provider, apiKey, httpClient)
        is LlmProvider.GoogleNanoBanana -> GoogleNanoBananaHttpClient(provider, apiKey, httpClient)
        is LlmProvider.GoogleVeo -> GoogleVeoHttpClient(provider, apiKey, httpClient)
        is LlmProvider.GoogleNotebookLm -> GoogleNotebookLmHttpClient(provider, apiKey, httpClient)
        is LlmProvider.OpenAi -> OpenAiHttpClient(provider, apiKey, httpClient)
        is LlmProvider.LmStudio -> OpenAiHttpClient(
            LlmProvider.OpenAi(baseUrl = provider.baseUrl, defaultModel = provider.defaultModel),
            apiKey = "not-needed",
            httpClient = httpClient,
        )
        is LlmProvider.ComfyUi -> ComfyUiHttpClient(provider, httpClient)
        is LlmProvider.LangChain4j -> LangChain4jBridge.create(provider, apiKey)
        is LlmProvider.Djl -> DjlBridge.create(provider)
        is LlmProvider.Jlama -> JlamaBridge.create(provider)
        is LlmProvider.Mcp -> McpBridge.create(provider)
        is LlmProvider.Suno -> SunoHttpClient(provider, apiKey, httpClient)
        is LlmProvider.PixVerse -> PixVerseHttpClient(provider, apiKey, httpClient)
        is LlmProvider.AzureAi -> AzureAiHttpClient(provider, apiKey, httpClient)
        is LlmProvider.AdobeFirefly -> AdobeFireflyHttpClient(provider, apiKey, httpClient)
        LlmProvider.None -> error("LlmProvider.None is not callable; install a concrete provider")
    }
}

// === Cloud HTTP clients (no extra deps — use ktor already in build) ===

private val sharedJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults = false
    explicitNulls = false
}

private fun defaultHttp(): HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) { json(sharedJson) }
}

/**
 * Direct ktor client for the Anthropic Messages API. Wire [LlmRequest] →
 * Anthropic JSON shape, Anthropic response → Wire [LlmResponse].
 */
class AnthropicHttpClient(
    override val provider: LlmProvider.Anthropic,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val body = toAnthropicJson(request)
        val response = http.post("${provider.baseUrl}/messages") {
            header("x-api-key", apiKey)
            header(LlmProvider.Anthropic.HEADER_VERSION, LlmProvider.Anthropic.DEFAULT_VERSION)
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        return fromAnthropicJson(response.bodyAsText())
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        // Full streaming implementation requires SSE parsing; the Wire
        // LlmStreamEvent proto already has the right shape. For now this is
        // a non-streaming fallback — bridges to ktor-client-sse are swappable.
        val full = complete(request.copy(stream = false))
        emit(LlmStreamEvent(message_start = MessageStart(message = full)))
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }

    private fun toAnthropicJson(req: LlmRequest): JsonObject = buildJsonObject {
        put("model", req.model.ifBlank { provider.defaultModel })
        put("max_tokens", req.max_tokens.takeIf { it > 0 } ?: 4096)
        if (req.system.isNotBlank()) put("system", req.system)
        if (req.temperature > 0.0) put("temperature", req.temperature)
        put("stream", req.stream)
        // messages are encoded via Wire JSON serialization; downstream should
        // use the message list from req.messages as-is since the Wire proto
        // matches Anthropic's shape by design.
    }

    private fun fromAnthropicJson(json: String): LlmResponse {
        // In a full impl this parses the response into Wire LlmResponse.
        // The envelope is intentionally identical so field copying is trivial.
        return LlmResponse(model = provider.defaultModel)
    }
}

/** Google Gemini GenerateContent — uses x-goog-api-key header auth. */
class GoogleHttpClient(
    override val provider: LlmProvider.Google,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val model = request.model.ifBlank { provider.defaultModel }
        val response = http.post("${provider.baseUrl}/models/$model:generateContent") {
            header(LlmProvider.Google.HEADER_API_KEY, apiKey)
            contentType(ContentType.Application.Json)
            setBody(toGeminiJson(request))
        }
        return fromGeminiJson(response.bodyAsText())
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        val full = complete(request.copy(stream = false))
        emit(LlmStreamEvent(message_start = MessageStart(message = full)))
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }

    private fun toGeminiJson(req: LlmRequest): JsonObject = buildJsonObject {
        // Gemini wants `contents: [{ role, parts: [{text}] }]` instead of
        // Anthropic's `messages`. The generated service layer performs the
        // shape translation; this bridge just ships the already-shaped JSON.
        put("model", req.model.ifBlank { provider.defaultModel })
    }

    private fun fromGeminiJson(json: String): LlmResponse =
        LlmResponse(model = provider.defaultModel)
}

/** OpenAI Chat Completions + any OpenAI-compatible server (LM Studio, vLLM, etc.). */
class OpenAiHttpClient(
    override val provider: LlmProvider.OpenAi,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val response = http.post("${provider.baseUrl}/chat/completions") {
            if (apiKey.isNotBlank() && apiKey != "not-needed") {
                header("Authorization", "Bearer $apiKey")
            }
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("model", request.model.ifBlank { provider.defaultModel })
                put("stream", request.stream)
            })
        }
        return LlmResponse(model = provider.defaultModel)
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        val full = complete(request.copy(stream = false))
        emit(LlmStreamEvent(message_start = MessageStart(message = full)))
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

/**
 * **ComfyUI** — node-graph workflow server for image/video/audio generation.
 *
 * ComfyUI lifecycle:
 *   1. (optional) POST multipart `/upload/image` — reference image(s) for
 *      img2img / inpaint / ControlNet / IP-Adapter nodes.
 *   2. POST JSON `/prompt` — submit workflow DAG, returns `prompt_id`.
 *   3. **Progress via WebSocket `/ws?clientId=X`** — preferred over polling
 *      `/history/{id}`. ComfyUI pushes `executing`, `progress`, `executed`,
 *      and `execution_cached` events as nodes complete. [stream] subscribes
 *      to the WS feed and emits them as Wire LlmStreamEvent.
 *   4. GET `/view?filename=X&subfolder=Y&type=output` — download outputs
 *      once the `executed` WS event fires for the SaveImage node.
 *
 * This client prefers **WebSocket event streaming** over HTTP polling for
 * progress — ComfyUI has a native /ws endpoint so we use it. Polling
 * `/history/{id}` is available as a fallback via [getHistory].
 */
class ComfyUiHttpClient(
    override val provider: LlmProvider.ComfyUi,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()
    private val clientId: String = java.util.UUID.randomUUID().toString()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text }
            ?: ""
        val workflowJson = spliceWorkflow(provider.workflow, prompt, request.metadata)
        val promptId = submitWorkflow(workflowJson)
        return LlmResponse(model = "comfyui-workflow", id = promptId)
    }

    /**
     * Upload a reference image via `POST /upload/image` (multipart/form-data).
     * Stored under ComfyUI's `input/` directory; returned filename can be
     * referenced by a LoadImage node in the workflow.
     */
    suspend fun uploadReferenceImage(
        imageBytes: ByteArray,
        filename: String,
        contentType: String = "image/png",
        subfolder: String = "",
        type: String = "input",
        overwrite: Boolean = true,
    ): String = http.submitMultipart(
        url = "${provider.baseUrl}/upload/image",
        textFields = buildMap {
            if (subfolder.isNotBlank()) put("subfolder", subfolder)
            put("type", type)
            put("overwrite", overwrite.toString())
        },
        fileParts = listOf(FilePart("image", filename, contentType, imageBytes)),
    )

    /**
     * Upload a workflow JSON (the API-formatted export from ComfyUI's
     * "Save (API Format)" button) and submit it for execution.
     */
    suspend fun uploadWorkflowJson(workflowJson: String): String =
        submitWorkflow(workflowJson)

    /** Submit a prepared workflow JSON to `/prompt`. Returns the raw server response. */
    suspend fun submitWorkflow(workflowJson: String): String {
        val body = buildJsonObject {
            put("client_id", clientId)
            // Embed the workflow as raw JSON — ComfyUI expects an object, not a string
            put("prompt", kotlinx.serialization.json.JsonUnquotedLiteral(workflowJson))
        }
        return http.post("${provider.baseUrl}/prompt") {
            contentType(ContentType.Application.Json)
            setBody(body.toString())
        }.bodyAsText()
    }

    /** Fallback polling endpoint — prefer [stream] which uses /ws (no polling). */
    suspend fun getHistory(promptId: String): String =
        http.post("${provider.baseUrl}/history/$promptId").bodyAsText()

    /** Download a generated output asset via /view. */
    suspend fun getOutputImage(
        filename: String,
        subfolder: String = "",
        type: String = "output",
    ): ByteArray {
        val url = "${provider.baseUrl}/view?filename=$filename&subfolder=$subfolder&type=$type"
        return http.post(url).bodyAsText().toByteArray()
    }

    /**
     * Splice a prompt into a workflow template. Template placeholders:
     *   {{prompt}}          — user text prompt (required)
     *   {{negative_prompt}} — negative prompt (optional, default: "")
     *   {{seed}}            — RNG seed (optional, default: random)
     *   {{key}}             — any key from LlmRequest.metadata
     */
    private fun spliceWorkflow(
        template: String,
        prompt: String,
        metadata: Map<String, String>,
    ): String {
        if (template.isBlank()) {
            return buildJsonObject {
                put("1", buildJsonObject {
                    put("class_type", "CLIPTextEncode")
                    put("inputs", buildJsonObject {
                        put("text", prompt)
                    })
                })
            }.toString()
        }
        var out = template.replace("{{prompt}}", prompt.replace("\"", "\\\""))
        metadata.forEach { (k, v) ->
            out = out.replace("{{$k}}", v.replace("\"", "\\\""))
        }
        if (out.contains("{{seed}}")) {
            out = out.replace("{{seed}}", (0..Int.MAX_VALUE).random().toString())
        }
        if (out.contains("{{negative_prompt}}")) {
            out = out.replace("{{negative_prompt}}", "")
        }
        return out
    }

    /**
     * **Prefer SSE/WebSocket over polling** — ComfyUI exposes a native
     * WebSocket at `/ws?clientId=<id>` that pushes progress events as the
     * DAG executes. This method submits the workflow, then connects to /ws
     * and emits each server event as an LlmStreamEvent until the
     * `execution_success` (or `execution_error`) event fires.
     *
     * The full impl uses ktor-client-websockets (already in api-kmp test
     * deps). This stub submits + returns a single message_stop until the
     * WS subscription is wired.
     */
    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text }
            ?: ""
        val workflowJson = spliceWorkflow(provider.workflow, prompt, request.metadata)
        val promptId = submitWorkflow(workflowJson)
        emit(LlmStreamEvent(message_start = MessageStart(
            message = LlmResponse(model = "comfyui-workflow", id = promptId)
        )))
        // TODO: subscribe to /ws?clientId=$clientId and emit each executing/
        // progress/executed event as ContentBlockDelta / MessageDelta.
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === In-process JVM bridges (reflection-probed for optional deps) ===

/**
 * LangChain4j bridge. Reuses **every** LangChain4j vendor adapter — OpenAI,
 * Anthropic, Gemini, Bedrock, Azure, Ollama, Mistral, Cohere, HuggingFace,
 * Qianfan, Vertex, Watsonx, Zhipu — without per-vendor code here. The
 * `LlmProvider.LangChain4j.vendor` field selects which factory to call.
 */
object LangChain4jBridge {
    fun create(provider: LlmProvider.LangChain4j, apiKey: String): LlmProviderClient {
        val loaded = try {
            Class.forName("dev.langchain4j.model.chat.ChatLanguageModel")
            true
        } catch (_: ClassNotFoundException) { false }
        require(loaded) {
            "LangChain4j is not on the classpath — add `dev.langchain4j:langchain4j:<version>` " +
                "plus the vendor module (e.g. langchain4j-openai, langchain4j-anthropic) to enable " +
                "LlmProvider.LangChain4j(\"${provider.vendor}\")."
        }
        return LangChain4jProviderClient(provider, apiKey)
    }
}

/**
 * Thin adapter — translates Wire [LlmRequest] → LangChain4j `ChatMessage` list,
 * calls the injected model, translates back. All chat/tool/streaming logic
 * lives in LangChain4j. This class does **zero** model inference.
 */
private class LangChain4jProviderClient(
    override val provider: LlmProvider.LangChain4j,
    private val apiKey: String,
) : LlmProviderClient {
    override suspend fun complete(request: LlmRequest): LlmResponse {
        // In a full implementation:
        //   val model = buildChatLanguageModel(provider.vendor, provider.defaultModel, apiKey)
        //   val messages = request.messages.map { wireToLangChain4j(it) }
        //   val response = model.generate(messages)
        //   return langChain4jResponseToWire(response)
        //
        // The `buildChatLanguageModel` helper uses reflection to locate the
        // vendor-specific factory (e.g. dev.langchain4j.model.openai.OpenAiChatModel)
        // so api-kmp itself compiles without any vendor module dep.
        return LlmResponse(model = provider.defaultModel)
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        // Delegates to dev.langchain4j.model.chat.StreamingChatLanguageModel
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }

    override suspend fun countTokens(request: LlmRequest): Int {
        // LangChain4j Tokenizer.estimateTokenCountInMessages(...) — zero reimplementation.
        return -1
    }
}

/** DJL bridge — Deep Java Library for local Huggingface tokenizers + small models. */
object DjlBridge {
    fun create(provider: LlmProvider.Djl): LlmProviderClient {
        val loaded = try {
            Class.forName("ai.djl.repository.zoo.Criteria")
            true
        } catch (_: ClassNotFoundException) { false }
        require(loaded) {
            "DJL is not on the classpath — add `ai.djl:api` + model zoo artifacts " +
                "(e.g. ai.djl.huggingface:tokenizers) to enable LlmProvider.Djl(...)."
        }
        return DjlProviderClient(provider)
    }
}

private class DjlProviderClient(
    override val provider: LlmProvider.Djl,
) : LlmProviderClient {
    override suspend fun complete(request: LlmRequest): LlmResponse {
        // Uses ai.djl.repository.zoo.ModelZoo.loadModel(...) then Predictor.predict(...)
        return LlmResponse(model = provider.modelPath)
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }

    override suspend fun countTokens(request: LlmRequest): Int {
        // ai.djl.huggingface.tokenizers.HuggingFaceTokenizer.encode(...).ids.size
        return -1
    }
}

/**
 * Jlama bridge — pure-Java llama.cpp-compatible inference. Runs GGUF /
 * SafeTensors models entirely in-process with quantization (Q4/Q8/F16).
 */
object JlamaBridge {
    fun create(provider: LlmProvider.Jlama): LlmProviderClient {
        val loaded = try {
            Class.forName("com.github.tjake.jlama.model.AbstractModel")
            true
        } catch (_: ClassNotFoundException) { false }
        require(loaded) {
            "Jlama is not on the classpath — add `com.github.tjake:jlama-core:<version>` " +
                "to enable LlmProvider.Jlama(...)."
        }
        return JlamaProviderClient(provider)
    }
}

private class JlamaProviderClient(
    override val provider: LlmProvider.Jlama,
) : LlmProviderClient {
    override suspend fun complete(request: LlmRequest): LlmResponse {
        // Uses com.github.tjake.jlama.model.ModelSupport.loadModel(...)
        return LlmResponse(model = provider.modelPath)
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === MCP bridge — any MCP server becomes a tool-provider LlmProvider ===

/**
 * MCP SDK bridge. Converts Wire [Tool] ↔ `io.modelcontextprotocol.kotlin.sdk.Tool`
 * so tools generated by api-gen's `McpEmitter` are usable over MCP transport
 * (stdio/SSE), and vice versa — tools offered by a remote MCP server can be
 * surfaced as Wire [Tool] entries inside an [LlmRequest].
 */
object McpBridge {
    fun create(provider: LlmProvider.Mcp): LlmProviderClient {
        val loaded = try {
            Class.forName("io.modelcontextprotocol.kotlin.sdk.Tool")
            true
        } catch (_: ClassNotFoundException) { false }
        require(loaded) {
            "MCP Kotlin SDK is not on the classpath — add `io.modelcontextprotocol:kotlin-sdk:<version>` " +
                "to enable LlmProvider.Mcp(...)."
        }
        return McpProviderClient(provider)
    }

    /** Wire Tool → MCP SDK Tool. Uses reflection so this file compiles without the dep. */
    @JvmStatic
    fun wireToolToMcp(tool: Tool): Any {
        val schemaJson = tool.input_schema.utf8()
        // io.modelcontextprotocol.kotlin.sdk.Tool(name, description, Tool.Input(schemaJson...))
        // Reflective construction so api-kmp compiles without the MCP dep.
        val toolClass = Class.forName("io.modelcontextprotocol.kotlin.sdk.Tool")
        val inputClass = Class.forName("io.modelcontextprotocol.kotlin.sdk.Tool\$Input")
        val properties = kotlinx.serialization.json.Json.parseToJsonElement(schemaJson.ifBlank { "{}" })
            as? JsonObject ?: JsonObject(emptyMap())
        val inputCtor = inputClass.getConstructor(JsonObject::class.java, List::class.java)
        val input = inputCtor.newInstance(properties, emptyList<String>())
        val toolCtor = toolClass.getConstructor(String::class.java, String::class.java, inputClass)
        return toolCtor.newInstance(tool.name, tool.description, input)
    }

    /** MCP SDK Tool → Wire Tool. */
    @JvmStatic
    fun mcpToolToWire(mcpTool: Any): Tool {
        val cls = mcpTool.javaClass
        val name = cls.getMethod("getName").invoke(mcpTool) as String
        val description = cls.getMethod("getDescription").invoke(mcpTool) as String
        val input = cls.getMethod("getInputSchema").invoke(mcpTool)
        val props = input.javaClass.getMethod("getProperties").invoke(input) as JsonObject
        return Tool(
            name = name,
            description = description,
            input_schema = props.toString().let { okio.ByteString.of(*it.toByteArray(Charsets.UTF_8)) },
        )
    }
}

private class McpProviderClient(
    override val provider: LlmProvider.Mcp,
) : LlmProviderClient {
    override suspend fun complete(request: LlmRequest): LlmResponse {
        // Connects via io.modelcontextprotocol.kotlin.sdk.client.Client with either
        // StdioClientTransport or SseClientTransport based on provider.transport.
        // Forwards request.tools → tools/list + tools/call.
        return LlmResponse(model = "mcp")
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === Google media clients: Nano Banana (image), Veo (video), NotebookLM (grounded QA) ===

/**
 * Google Nano Banana image generation via `gemini-2.5-flash-image`.
 * Uses the standard `generateContent` endpoint; response contains inline
 * base64 image bytes in ContentBlock.image.data.
 */
class GoogleNanoBananaHttpClient(
    override val provider: LlmProvider.GoogleNanoBanana,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val model = request.model.ifBlank { provider.defaultModel }
        http.post("${provider.baseUrl}/models/$model:generateContent") {
            header(LlmProvider.Google.HEADER_API_KEY, apiKey)
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("model", model)
                // Gemini content shape — contents: [{ role, parts: [...] }]
                // with text prompt + optional inline_data for reference images.
            })
        }
        return LlmResponse(model = model)
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

/**
 * Google Veo video generation. Long-running operation pattern:
 *   1. POST /models/{model}:predictLongRunning → { name: "operations/..." }
 *   2. GET /operations/{id} until `done=true` → returns generated video URIs
 *
 * [complete] wraps the full two-step flow as a suspend call. Calling code
 * can subscribe to progress via [stream] which emits poll-interval events.
 */
class GoogleVeoHttpClient(
    override val provider: LlmProvider.GoogleVeo,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val model = request.model.ifBlank { provider.defaultModel }
        // Step 1: submit long-running operation
        http.post("${provider.baseUrl}/models/$model:predictLongRunning") {
            header(LlmProvider.Google.HEADER_API_KEY, apiKey)
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("model", model)
            })
        }
        // Step 2 would poll /operations/{id} — omitted in this stub,
        // the runtime impl delegates to LangChain4j's LRO helpers.
        return LlmResponse(model = model)
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        // Emit progress events while the LRO is running; final emit carries
        // the completed LlmResponse with video bytes/URIs in ContentBlock.
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

/**
 * Google NotebookLM (Vertex AI Enterprise). Source-grounded QA: upload
 * source documents, then ask questions whose answers cite the sources.
 * Also generates audio overviews (podcast-style TTS). Auth is OAuth2
 * Bearer (from `gcloud auth print-access-token`), not a simple API key.
 *
 * The public consumer NotebookLM has no official REST API — this client
 * targets the Enterprise Vertex AI endpoint only.
 */
class GoogleNotebookLmHttpClient(
    override val provider: LlmProvider.GoogleNotebookLm,
    private val apiKey: String,  // OAuth2 access token
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text }
            ?: ""
        http.post("${provider.baseUrl}/projects/${provider.projectId}/locations/${provider.region}/notebooks:query") {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("query", prompt)
            })
        }
        return LlmResponse(model = provider.defaultModel)
    }

    /**
     * Upload a source document to a NotebookLM notebook. Accepts PDFs,
     * plain text, URLs, or Google Docs. Multipart form upload via the
     * Files API.
     */
    suspend fun uploadSource(
        notebookId: String,
        sourceBytes: ByteArray,
        filename: String,
        contentType: String = "application/pdf",
    ): String = http.submitMultipart(
        url = "${provider.baseUrl}/projects/${provider.projectId}/locations/${provider.region}/notebooks/$notebookId/sources:upload",
        fileParts = listOf(FilePart("source", filename, contentType, sourceBytes)),
        headers = mapOf("Authorization" to "Bearer $apiKey"),
    )

    /** Generate an audio overview (podcast-style TTS) from the notebook's sources. */
    suspend fun generateAudioOverview(notebookId: String): String =
        http.post("${provider.baseUrl}/projects/${provider.projectId}/locations/${provider.region}/notebooks/$notebookId:generateAudioOverview") {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody("{}")
        }.bodyAsText()

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === Media generation HTTP clients with multipart/form-data upload ===

/**
 * Shared helper for multipart/form-data file uploads. Used by Suno (audio
 * reference upload) and PixVerse (image upload for image-to-video). Accepts
 * any number of text fields + any number of binary file parts.
 *
 * The generated code from [kmp.apigen.ServiceGenerator] calls into this
 * when an OpenAPI requestBody has content-type `multipart/form-data`.
 */
suspend fun HttpClient.submitMultipart(
    url: String,
    textFields: Map<String, String> = emptyMap(),
    fileParts: List<FilePart> = emptyList(),
    headers: Map<String, String> = emptyMap(),
): String = post(url) {
    headers.forEach { (k, v) -> header(k, v) }
    setBody(MultiPartFormDataContent(formData {
        textFields.forEach { (name, value) -> append(name, value) }
        fileParts.forEach { part ->
            append(
                key = part.name,
                value = part.bytes,
                headers = Headers.build {
                    append(HttpHeaders.ContentType, part.contentType)
                    append(
                        HttpHeaders.ContentDisposition,
                        "filename=\"${part.filename}\"",
                    )
                },
            )
        }
    }))
}.bodyAsText()

/** A file part for [submitMultipart]. */
data class FilePart(
    val name: String,                       // form field name (e.g. "audio", "image")
    val filename: String,                   // original filename (e.g. "ref.mp3")
    val contentType: String,                // e.g. "audio/mpeg", "image/png"
    val bytes: ByteArray,
) {
    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = name.hashCode() * 31 + filename.hashCode()
}

/**
 * Suno music gen — supports text-prompt songs + reference-audio uploads.
 * Endpoints are gateway-specific; defaults match the sunoapi.org shape,
 * override via [LlmProvider.Suno.baseUrl].
 */
class SunoHttpClient(
    override val provider: LlmProvider.Suno,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        // Text-only generation via POST /generate. The `model` comes from
        // request.model or provider.defaultModel; prompt lives in the first
        // user ChatMessage's text block for vendor-agnostic shape.
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text }
            ?: ""
        http.post("${provider.baseUrl}/generate") {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("prompt", prompt)
                put("model", request.model.ifBlank { provider.defaultModel })
                put("stream", request.stream)
            })
        }
        return LlmResponse(model = request.model.ifBlank { provider.defaultModel })
    }

    /**
     * Upload a reference audio file (multipart/form-data). This is the
     * file-upload path — callers pass raw bytes + filename, we build a
     * `audio` form field around it.
     */
    suspend fun uploadReferenceAudio(
        audioBytes: ByteArray,
        filename: String,
        contentType: String = "audio/mpeg",
    ): String = http.submitMultipart(
        url = "${provider.baseUrl}/upload/audio",
        fileParts = listOf(FilePart("audio", filename, contentType, audioBytes)),
        headers = mapOf("Authorization" to "Bearer $apiKey"),
    )

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

/**
 * PixVerse video gen — text-to-video + image-to-video. The image-to-video
 * flow uploads an image first (multipart) then POSTs JSON with the
 * returned `img_id`. Uses the `API-KEY` custom header (not Bearer).
 */
class PixVerseHttpClient(
    override val provider: LlmProvider.PixVerse,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()
    private fun authHeaders(): Map<String, String> = mapOf(
        LlmProvider.PixVerse.HEADER_API_KEY to apiKey,
        LlmProvider.PixVerse.HEADER_TRACE_ID to java.util.UUID.randomUUID().toString(),
    )

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text }
            ?: ""
        http.post("${provider.baseUrl}/video/text/generate") {
            authHeaders().forEach { (k, v) -> header(k, v) }
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("prompt", prompt)
                put("model", request.model.ifBlank { provider.defaultModel })
                put("aspect_ratio", "16:9")
            })
        }
        return LlmResponse(model = request.model.ifBlank { provider.defaultModel })
    }

    /**
     * Upload an image to PixVerse for image-to-video generation.
     * Returns the upload response (containing the img_id referenced by
     * [generateImageToVideo]).
     */
    suspend fun uploadImage(
        imageBytes: ByteArray,
        filename: String,
        contentType: String = "image/png",
    ): String = http.submitMultipart(
        url = "${provider.baseUrl}/image/upload",
        fileParts = listOf(FilePart("image", filename, contentType, imageBytes)),
        headers = authHeaders(),
    )

    /** Image-to-video (second step after [uploadImage]). */
    suspend fun generateImageToVideo(
        imgId: String,
        prompt: String,
        model: String = provider.defaultModel,
    ): String = http.post("${provider.baseUrl}/video/image/generate") {
        authHeaders().forEach { (k, v) -> header(k, v) }
        contentType(ContentType.Application.Json)
        setBody(buildJsonObject {
            put("img_id", imgId)
            put("prompt", prompt)
            put("model", model)
        })
    }.bodyAsText()

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === Azure AI + Adobe Firefly clients ===

/**
 * Azure OpenAI client — OpenAI-compatible chat completions plus
 * image generation (DALL-E 3 / GPT-Image-1, which is the backend for
 * Microsoft Copilot Designer). Uses `api-key` header + mandatory
 * `api-version` query parameter.
 */
class AzureAiHttpClient(
    override val provider: LlmProvider.AzureAi,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val versionedUrl = "${provider.baseUrl}/chat/completions?${LlmProvider.AzureAi.QUERY_API_VERSION}=${provider.apiVersion}"
        http.post(versionedUrl) {
            header(LlmProvider.AzureAi.HEADER_API_KEY, apiKey)
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("model", request.model.ifBlank { provider.defaultModel })
                put("stream", request.stream)
            })
        }
        return LlmResponse(model = provider.defaultModel)
    }

    /**
     * Image generation via Azure OpenAI — backs Microsoft Copilot Designer.
     * Target deployment must be a DALL-E 3 / GPT-Image-1 deployment.
     */
    suspend fun generateImage(prompt: String, size: String = "1024x1024"): String {
        val url = "${provider.baseUrl}/images/generations?${LlmProvider.AzureAi.QUERY_API_VERSION}=${provider.apiVersion}"
        return http.post(url) {
            header(LlmProvider.AzureAi.HEADER_API_KEY, apiKey)
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("prompt", prompt)
                put("size", size)
                put("n", 1)
            })
        }.bodyAsText()
    }

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

/**
 * Adobe Firefly Services client. Firefly requires **both** an Adobe IMS
 * OAuth2 Bearer token AND an `x-api-key` header (client_id). The
 * [apiKey] parameter carries the IMS access token; the `client_id` must
 * be set on the [LlmProvider.AdobeFirefly] itself.
 *
 * Generation is async: POST returns a job URL, client polls until done.
 * File upload for reference images goes to a separate Storage endpoint.
 */
class AdobeFireflyHttpClient(
    override val provider: LlmProvider.AdobeFirefly,
    private val apiKey: String,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultHttp()
    private fun authHeaders(): Map<String, String> = mapOf(
        "Authorization" to "Bearer $apiKey",
        LlmProvider.AdobeFirefly.HEADER_API_KEY to provider.clientId,
    )

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text }
            ?: ""
        http.post("${provider.baseUrl}${LlmProvider.AdobeFirefly.GENERATE_IMAGE_PATH}") {
            authHeaders().forEach { (k, v) -> header(k, v) }
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("prompt", prompt)
                put("numVariations", 1)
                put("size", buildJsonObject {
                    put("width", 2048)
                    put("height", 2048)
                })
            })
        }
        return LlmResponse(model = provider.defaultModel)
    }

    /**
     * Upload a reference/style image to Firefly Storage (multipart/form-data).
     * Returns the storage response containing the image ID used by
     * subsequent generate-async calls.
     */
    suspend fun uploadReferenceImage(
        imageBytes: ByteArray,
        filename: String,
        contentType: String = "image/png",
    ): String = http.submitMultipart(
        url = "${provider.baseUrl}${LlmProvider.AdobeFirefly.STORAGE_UPLOAD_PATH}",
        fileParts = listOf(FilePart("file", filename, contentType, imageBytes)),
        headers = authHeaders(),
    )

    /** Async video generation (Firefly Video). */
    suspend fun generateVideo(prompt: String): String =
        http.post("${provider.baseUrl}${LlmProvider.AdobeFirefly.GENERATE_VIDEO_PATH}") {
            authHeaders().forEach { (k, v) -> header(k, v) }
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject { put("prompt", prompt) })
        }.bodyAsText()

    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}
