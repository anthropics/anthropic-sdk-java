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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * Bridges from the Wire LLM types (LlmRequest/LlmResponse) to external
 * backends. **Pure glue** — no chat/streaming/tokenization logic is
 * reimplemented; everything delegates to the upstream library.
 *
 * HTTP-based providers (Anthropic, Google Gemini, Google Nano Banana,
 * Google Veo, Google NotebookLM, OpenAI, LM Studio, Suno, PixVerse, Azure,
 * Adobe Firefly) all flow through the **single** [HttpLlmClient] class via
 * a [ProviderSpec] data descriptor — 11 vendor-specific classes collapsed
 * into 1 data-driven client. Adding a new HTTP vendor means adding one
 * entry to [ProviderSpecs], not writing a new class.
 *
 * Non-HTTP providers with fundamentally different transports keep their
 * own clients:
 * - **ComfyUi** — node-graph workflow with WS progress events
 * - **LangChain4j / DJL / Jlama** — in-process JVM runtimes
 * - **MCP** — stdio or SSE JSON-RPC
 */
object LlmProviderClients {

    /**
     * Return the right [LlmProviderClient] for a given [LlmProvider].
     * Data-driven: HTTP providers all share [HttpLlmClient]; only
     * non-HTTP ones get bespoke classes.
     */
    @kotlin.jvm.JvmStatic
    fun forProvider(
        provider: LlmProvider,
        apiKey: String = "",
        httpClient: HttpClient? = null,
    ): LlmProviderClient {
        // HTTP providers — single data-driven client
        ProviderSpecs.forProvider(provider)?.let { spec ->
            return HttpLlmClient(provider, apiKey, spec, httpClient)
        }

        // Non-HTTP / in-process providers — platform-specific dispatch
        return createPlatformClient(provider, apiKey, httpClient)
    }
}

// === Shared multipart helper (used by HttpLlmClient.uploadFile + ComfyUI) ===

/**
 * Shared ktor multipart/form-data uploader. Used by [HttpLlmClient.uploadFile]
 * for Suno/PixVerse/Adobe Firefly/NotebookLM uploads, and by [ComfyUiClient]
 * for reference-image + workflow uploads.
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

/** A file part for multipart/form-data uploads. */
data class FilePart(
    val name: String,                       // form field name (e.g. "audio", "image", "source", "file")
    val filename: String,                   // original filename
    val contentType: String,                // e.g. "audio/mpeg", "image/png"
    val bytes: ByteArray,
) {
    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = name.hashCode() * 31 + filename.hashCode()
}

/**
 * Platform-specific client creation for non-HTTP providers.
 * JVM: LangChain4j, DJL, Jlama, MCP bridges via reflection-probed compileOnly deps.
 * JS/Native: only ComfyUi (HTTP-based) and MCP (via SDK) are available.
 */
expect fun createPlatformClient(
    provider: LlmProvider,
    apiKey: String,
    httpClient: io.ktor.client.HttpClient?,
): LlmProviderClient
