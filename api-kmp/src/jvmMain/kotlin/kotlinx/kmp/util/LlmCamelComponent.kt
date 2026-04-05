package kotlinx.kmp.util

import kotlinx.coroutines.runBlocking
import org.apache.camel.Consumer
import org.apache.camel.Endpoint
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.Producer
import org.apache.camel.support.DefaultComponent
import org.apache.camel.support.DefaultEndpoint
import org.apache.camel.support.DefaultProducer

/**
 * **Vendor-agnostic** Apache Camel LLM component. Replaces the Anthropic-
 * specific `AnthropicComponent` design from the old LLD (§8.2/§8.5 in
 * `docs/KMP-CONVERSION-PLAN.md`) with a provider-driven implementation.
 *
 * URI scheme:
 * ```
 * llm:<endpoint>?provider=<id>&model=<model>&maxTokens=<n>&stream=<bool>
 * ```
 *
 * `endpoint` is a logical operation name (messages|embeddings|chat|completions).
 * `provider` maps to any [LlmProvider] — Anthropic, Google, LmStudio, ComfyUi,
 * LangChain4j, Djl, Jlama, OpenAi, Mcp. Adding a new vendor requires **zero
 * changes** to this component — you just add a new `LlmProvider` case in
 * `LlmProvider.kt` and its bridge in `LlmBridges.kt`.
 *
 * Example Camel routes:
 * ```kotlin
 * from("direct:chat-any")
 *     .to("llm:messages?provider=anthropic")
 * from("direct:chat-local")
 *     .to("llm:chat?provider=lmstudio&model=llama-3.1-8b")
 * from("direct:chat-gemini")
 *     .to("llm:generateContent?provider=google&model=gemini-2.0-flash")
 * from("direct:inference")
 *     .to("llm:complete?provider=jlama&modelPath=/models/llama3-8b-q4.gguf")
 * ```
 *
 * The component is registered under the `llm:` scheme. In a Camel app, add:
 * ```kotlin
 * camelContext.addComponent("llm", LlmComponent())
 * ```
 *
 * A generated [kmp.apigen.CamelRouteEmitter] can emit these routes directly
 * from an OpenAPI spec — one route per path, with the provider selected at
 * route-install time.
 */
class LlmComponent : DefaultComponent() {
    /** Default provider id when the URI omits `?provider=...`. */
    var defaultProvider: String = LlmProvider.Anthropic.id

    /** API key — falls back to ANTHROPIC_API_KEY / GOOGLE_API_KEY / OPENAI_API_KEY env vars. */
    var apiKey: String? = null

    override fun createEndpoint(uri: String, remaining: String, parameters: Map<String, Any>): Endpoint {
        val endpoint = LlmEndpoint(uri, this, remaining)
        setProperties(endpoint, parameters)
        return endpoint
    }
}

/**
 * A single Camel endpoint. `operation` is the part after `llm:` — e.g.
 * `messages`, `embeddings`, `chat`. `provider` picks the [LlmProvider]
 * implementation at route-configure time.
 */
class LlmEndpoint(
    uri: String,
    component: LlmComponent,
    val operation: String,
) : DefaultEndpoint(uri, component) {
    var provider: String = (component as LlmComponent).defaultProvider
    var model: String = ""
    var maxTokens: Int = 4096
    var temperature: Double = 0.0
    var stream: Boolean = false
    var systemPrompt: String = ""
    /** For ComfyUi: workflow JSON. For Djl/Jlama: local model path. */
    var workflow: String = ""
    var modelPath: String = ""

    override fun createProducer(): Producer = LlmProducer(this)

    override fun createConsumer(processor: Processor): Consumer =
        throw UnsupportedOperationException("llm: endpoint does not support consumers")
}

/**
 * Producer that dispatches an [Exchange] body (plain text or a Wire
 * [LlmRequest]) to the configured provider via [LlmProviderClients.forProvider].
 * The response body becomes the Wire [LlmResponse]; common metadata
 * (model, stop_reason, input/output tokens) is also set as exchange headers
 * for convenience.
 */
class LlmProducer(private val endpoint: LlmEndpoint) : DefaultProducer(endpoint) {
    private lateinit var client: LlmProviderClient

    override fun doStart() {
        super.doStart()
        val provider = resolveProvider(endpoint)
        val component = endpoint.component as LlmComponent
        val apiKey = component.apiKey
            ?: System.getenv("ANTHROPIC_API_KEY")
            ?: System.getenv("GOOGLE_API_KEY")
            ?: System.getenv("OPENAI_API_KEY")
            ?: ""
        client = LlmProviderClients.forProvider(provider, apiKey)
    }

    override fun process(exchange: Exchange) {
        val body = exchange.getIn().body
        val request = when (body) {
            is LlmRequest -> body
            is String -> LlmRequest(
                model = endpoint.model,
                messages = listOf(
                    ChatMessage(
                        role = "user",
                        content = listOf(ContentBlock(text = TextBlock(text = body))),
                    )
                ),
                system = endpoint.systemPrompt,
                max_tokens = endpoint.maxTokens,
                temperature = endpoint.temperature,
                stream = endpoint.stream,
            )
            else -> error("Unsupported exchange body for llm: endpoint: ${body?.javaClass}")
        }

        val response = runBlocking { client.complete(request) }

        exchange.getIn().body = response
        exchange.getIn().setHeader("LlmModel", response.model)
        exchange.getIn().setHeader("LlmStopReason", response.stop_reason)
        response.usage?.let { usage ->
            exchange.getIn().setHeader("LlmInputTokens", usage.input_tokens)
            exchange.getIn().setHeader("LlmOutputTokens", usage.output_tokens)
        }
    }

    /** Pick the right [LlmProvider] object from endpoint config. */
    private fun resolveProvider(ep: LlmEndpoint): LlmProvider = when (ep.provider.lowercase()) {
        "anthropic" -> LlmProvider.Anthropic
        "google", "gemini" -> LlmProvider.Google
        "openai" -> LlmProvider.OpenAi(defaultModel = ep.model.ifBlank { "gpt-4o" })
        "lmstudio", "lm-studio", "llamacpp", "vllm", "ollama" ->
            LlmProvider.LmStudio(defaultModel = ep.model.ifBlank { "local-model" })
        "comfyui", "comfy" ->
            LlmProvider.ComfyUi(workflow = ep.workflow, defaultModel = ep.model)
        "djl" -> LlmProvider.Djl(modelPath = ep.modelPath)
        "jlama" -> LlmProvider.Jlama(modelPath = ep.modelPath)
        "mcp" -> LlmProvider.Mcp(baseUrl = ep.modelPath)
        "suno" -> LlmProvider.Suno(defaultModel = ep.model.ifBlank { "chirp-v4" })
        "pixverse" -> LlmProvider.PixVerse(defaultModel = ep.model.ifBlank { "v3.5" })
        "google-nano-banana", "nano-banana" -> LlmProvider.GoogleNanoBanana
        "google-veo", "veo" -> LlmProvider.GoogleVeo(defaultModel = ep.model.ifBlank { "veo-3.0-generate-preview" })
        "google-notebooklm", "notebooklm" -> LlmProvider.GoogleNotebookLm(
            projectId = ep.modelPath,  // project id carried in modelPath param
            defaultModel = ep.model,
        )
        "azure", "copilot-designer" -> LlmProvider.AzureAi(
            resourceName = ep.modelPath.ifBlank { "my-resource" },
            deployment = ep.model.ifBlank { "gpt-4o" },
        )
        "adobe-firefly", "firefly" -> LlmProvider.AdobeFirefly(
            clientId = ep.modelPath,  // Adobe dev client_id carried in modelPath
            defaultModel = ep.model.ifBlank { "firefly-v3" },
        )
        else -> {
            // Assume langchain4j:<vendor>
            if (ep.provider.startsWith("langchain4j:")) {
                LlmProvider.LangChain4j(
                    vendor = ep.provider.substringAfter(":"),
                    defaultModel = ep.model,
                )
            } else {
                error("Unknown provider '${ep.provider}' — valid: anthropic|google|openai|lmstudio|comfyui|djl|jlama|mcp|langchain4j:<vendor>")
            }
        }
    }
}
