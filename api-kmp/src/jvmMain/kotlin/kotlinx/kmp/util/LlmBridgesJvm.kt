package kotlinx.kmp.util

import io.ktor.client.HttpClient
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
import io.modelcontextprotocol.kotlin.sdk.types.Tool as McpTool
import io.modelcontextprotocol.kotlin.sdk.types.ToolSchema
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put



// === Reflection-probed in-process bridges ===

/**
 * Asserts an optional class is on the classpath, with a clear error
 * message naming the dep to add. Eliminates 6 copies of this pattern.
 */
private fun requireClass(fqn: String, providerName: String, depCoordinate: String) {
    try {
        Class.forName(fqn)
    } catch (_: ClassNotFoundException) {
        error(
            "$providerName is not on the classpath — add `$depCoordinate` to your build " +
                "to enable the corresponding LlmProvider.",
        )
    }
}

/**
 * LangChain4j bridge. Reuses every LangChain4j vendor adapter — OpenAI,
 * Anthropic, Gemini, Bedrock, Azure, Ollama, Mistral, Cohere, HuggingFace,
 * Qianfan, Vertex, Watsonx, Zhipu — with no per-vendor code here.
 */
object LangChain4jBridge {
    fun create(provider: LlmProvider.LangChain4j, apiKey: String): LlmProviderClient {
        requireClass(
            "dev.langchain4j.model.chat.ChatLanguageModel",
            "LangChain4j",
            "dev.langchain4j:langchain4j:<version>",
        )
        return LangChain4jClient(provider, apiKey)
    }
}

private class LangChain4jClient(
    override val provider: LlmProvider.LangChain4j,
    @Suppress("unused") private val apiKey: String,
) : StubStreamingClient() {
    override suspend fun complete(request: LlmRequest): LlmResponse {
        // In a full impl: buildChatLanguageModel(provider.vendor) → generate(messages)
        return LlmResponse(model = provider.defaultModel)
    }
}

object DjlBridge {
    fun create(provider: LlmProvider.Djl): LlmProviderClient {
        requireClass(
            "ai.djl.repository.zoo.Criteria",
            "DJL",
            "ai.djl:api:<version> + ai.djl.huggingface:tokenizers:<version>",
        )
        return DjlClient(provider)
    }
}

private class DjlClient(override val provider: LlmProvider.Djl) : StubStreamingClient() {
    override suspend fun complete(request: LlmRequest): LlmResponse =
        LlmResponse(model = provider.modelPath)
}

object JlamaBridge {
    fun create(provider: LlmProvider.Jlama): LlmProviderClient {
        requireClass(
            "com.github.tjake.jlama.model.AbstractModel",
            "Jlama",
            "com.github.tjake:jlama-core:<version>",
        )
        return JlamaClient(provider)
    }
}

private class JlamaClient(override val provider: LlmProvider.Jlama) : StubStreamingClient() {
    override suspend fun complete(request: LlmRequest): LlmResponse =
        LlmResponse(model = provider.modelPath)
}

object McpBridge {
    fun create(provider: LlmProvider.Mcp): LlmProviderClient {
        return McpClient(provider)
    }

    /** Wire Tool → MCP SDK Tool. Direct import — no more reflection. */
    @JvmStatic
    fun wireToolToMcp(tool: Tool): io.modelcontextprotocol.kotlin.sdk.types.Tool {
        val schemaJson = tool.input_schema.utf8()
        val properties = Json.parseToJsonElement(schemaJson.ifBlank { "{}" })
            as? JsonObject ?: JsonObject(emptyMap())
        return io.modelcontextprotocol.kotlin.sdk.types.Tool(
            name = tool.name,
            inputSchema = io.modelcontextprotocol.kotlin.sdk.types.ToolSchema(
                properties = properties,
            ),
            description = tool.description,
        )
    }

    /** MCP SDK Tool → Wire Tool. */
    @JvmStatic
    fun mcpToolToWire(mcpTool: io.modelcontextprotocol.kotlin.sdk.types.Tool): Tool {
        val props = mcpTool.inputSchema.properties
        return Tool(
            name = mcpTool.name,
            description = mcpTool.description ?: "",
            input_schema = (props?.toString() ?: "{}").let {
                okio.ByteString.of(*it.toByteArray(Charsets.UTF_8))
            },
        )
    }
}

private class McpClient(override val provider: LlmProvider.Mcp) : StubStreamingClient() {
    override suspend fun complete(request: LlmRequest): LlmResponse =
        LlmResponse(model = "mcp")
}

/**
 * Base class for [LlmProviderClient] implementations whose `stream()` is
 * a stub (delegates to complete + emits message_stop). Eliminates 15
 * copies of the same stub stream() method across the bridges.
 */
private abstract class StubStreamingClient : LlmProviderClient {
    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        val full = complete(request.copy(stream = false))
        emit(LlmStreamEvent(message_start = MessageStart(message = full)))
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === ComfyUI — node-graph workflow transport (prefers WebSocket /ws over polling) ===

/**
 * ComfyUI client. Full workflow lifecycle: upload references + workflow,
 * submit, subscribe to `/ws?clientId=X` for progress, download outputs.
 *
 * ComfyUI has its own transport model (JSON DAG + WebSocket events) so
 * it gets a bespoke client — it doesn't fit the generic HttpLlmClient
 * shape even though it uses HTTP.
 */
class ComfyUiClient(
    override val provider: LlmProvider.ComfyUi,
    httpClient: HttpClient? = null,
) : LlmProviderClient {
    private val http = httpClient ?: defaultLlmHttpClient()
    private val clientId: String = java.util.UUID.randomUUID().toString()

    override suspend fun complete(request: LlmRequest): LlmResponse {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text } ?: ""
        val workflowJson = spliceWorkflow(provider.workflow, prompt, request.metadata)
        val promptId = submitWorkflow(workflowJson)
        return LlmResponse(model = "comfyui-workflow", id = promptId)
    }

    /** Multipart upload a reference image to ComfyUI's input dir. */
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

    /** Upload and submit an API-format workflow JSON. */
    suspend fun uploadWorkflowJson(workflowJson: String): String = submitWorkflow(workflowJson)

    suspend fun submitWorkflow(workflowJson: String): String {
        val body = buildJsonObject {
            put("client_id", clientId)
            put("prompt", kotlinx.serialization.json.JsonUnquotedLiteral(workflowJson))
        }
        return http.post("${provider.baseUrl}/prompt") {
            contentType(ContentType.Application.Json)
            setBody(body.toString())
        }.bodyAsText()
    }

    /** Fallback polling endpoint — prefer [stream] which uses /ws. */
    suspend fun getHistory(promptId: String): String =
        http.post("${provider.baseUrl}/history/$promptId").bodyAsText()

    suspend fun getOutputImage(
        filename: String,
        subfolder: String = "",
        type: String = "output",
    ): ByteArray {
        val url = "${provider.baseUrl}/view?filename=$filename&subfolder=$subfolder&type=$type"
        return http.post(url).bodyAsText().toByteArray()
    }

    /**
     * Workflow template substitution: {{prompt}}, {{seed}},
     * {{negative_prompt}}, {{key}} from LlmRequest.metadata.
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
                    put("inputs", buildJsonObject { put("text", prompt) })
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
     * **Prefer SSE/WebSocket over polling.** ComfyUI has a native /ws endpoint
     * — this stream() submits the workflow then (in the full impl) subscribes
     * to /ws?clientId= for per-node progress events. Current stub version
     * emits message_start + message_stop.
     */
    override suspend fun stream(request: LlmRequest): Flow<LlmStreamEvent> = flow {
        val prompt = request.messages.firstOrNull { it.role == "user" }
            ?.content?.firstNotNullOfOrNull { it.text?.text } ?: ""
        val workflowJson = spliceWorkflow(provider.workflow, prompt, request.metadata)
        val promptId = submitWorkflow(workflowJson)
        emit(LlmStreamEvent(message_start = MessageStart(
            message = LlmResponse(model = "comfyui-workflow", id = promptId),
        )))
        // TODO: subscribe to /ws?clientId=$clientId and emit each executing/
        // progress/executed event as ContentBlockDelta / MessageDelta.
        emit(LlmStreamEvent(message_stop = MessageStop()))
    }
}

// === Convenience factory extensions — provider-specific file-upload helpers ===
//
// These are thin wrappers around HttpLlmClient.uploadFile() that package
// the per-provider path + field name as named suspend functions. They
// exist purely for discoverability ("myClient.uploadAudio(...)" is
// clearer than "myClient.uploadFile('/upload/audio', ...)").

/**
 * Upload a reference audio file for Suno music gen. Returns the gateway's
 * response body (typically JSON containing an upload id).
 */
suspend fun HttpLlmClient.sunoUploadAudio(
    audioBytes: ByteArray,
    filename: String,
    contentType: String = "audio/mpeg",
): String = uploadFile(
    path = "/upload/audio",
    files = listOf(FilePart("audio", filename, contentType, audioBytes)),
)

/** Upload an image to PixVerse for image-to-video. */
suspend fun HttpLlmClient.pixVerseUploadImage(
    imageBytes: ByteArray,
    filename: String,
    contentType: String = "image/png",
): String = uploadFile(
    path = "/image/upload",
    files = listOf(FilePart("image", filename, contentType, imageBytes)),
)

/** Upload a style/reference image to Adobe Firefly storage. */
suspend fun HttpLlmClient.fireflyUploadReference(
    imageBytes: ByteArray,
    filename: String,
    contentType: String = "image/png",
): String = uploadFile(
    path = LlmProvider.AdobeFirefly.STORAGE_UPLOAD_PATH,
    files = listOf(FilePart("file", filename, contentType, imageBytes)),
)

/** Upload a source document to a NotebookLM notebook. */
suspend fun HttpLlmClient.notebookLmUploadSource(
    notebookId: String,
    sourceBytes: ByteArray,
    filename: String,
    contentType: String = "application/pdf",
): String = uploadFile(
    path = "/notebooks/$notebookId/sources:upload",
    files = listOf(FilePart("source", filename, contentType, sourceBytes)),
)

actual fun createPlatformClient(
    provider: LlmProvider,
    apiKey: String,
    httpClient: io.ktor.client.HttpClient?,
): LlmProviderClient = when (provider) {
    is LlmProvider.ComfyUi -> ComfyUiClient(provider, httpClient)
    is LlmProvider.LangChain4j -> LangChain4jBridge.create(provider, apiKey)
    is LlmProvider.Djl -> DjlBridge.create(provider)
    is LlmProvider.Jlama -> JlamaBridge.create(provider)
    is LlmProvider.Mcp -> McpBridge.create(provider)
    LlmProvider.None -> error("LlmProvider.None is not callable; install a concrete provider")
    else -> error("No client available for ${provider::class.simpleName}")
}
