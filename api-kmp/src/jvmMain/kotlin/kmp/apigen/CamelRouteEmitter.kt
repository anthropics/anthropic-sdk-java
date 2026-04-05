package kmp.apigen

import java.io.File

/**
 * Generates an Apache Camel `RouteBuilder` from OpenAPI paths.
 *
 * Each path becomes a REST route that dispatches through the vendor-agnostic
 * [kotlinx.kmp.util.LlmComponent] (the `llm:` scheme). Provider selection is
 * URI-driven, so the same generated routes work against any backend:
 *
 * ```kotlin
 * class GeneratedMessagesRouteBuilder : RouteBuilder() {
 *     override fun configure() {
 *         restConfiguration().component("platform-http").bindingMode(RestBindingMode.json)
 *
 *         rest("/v1")
 *             .post("/messages")
 *                 .to("llm:messages?provider=anthropic")
 *             .post("/messages:stream")
 *                 .to("llm:stream?provider=anthropic")
 *     }
 * }
 * ```
 *
 * A companion route file adds direct endpoints for non-HTTP use:
 * ```kotlin
 * from("direct:chat").to("llm:messages?provider={{llm.provider}}")
 * ```
 *
 * Where `{{llm.provider}}` is a property so a single deployment can switch
 * between Anthropic / Google / LM Studio / local backends via config.
 *
 * This mirrors the existing LLDs at KMP-CONVERSION-PLAN.md §8.2 + §8.5 but
 * rewrites them from Anthropic-specific to provider-generic.
 */
class CamelRouteEmitter : ProtocolEmitter {
    override val protocol = "camel"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val camelPackage = "$pkg.camel"
        val dir = File(outputDir, camelPackage.replace(".", "/"))
        dir.mkdirs()

        val byTag = spec.paths.values.groupBy { it.tags.firstOrNull() ?: "Default" }
        byTag.forEach { (tag, ops) ->
            val className = "${tag.replaceFirstChar { it.uppercase() }}RouteBuilder"
            File(dir, "$className.kt").writeText(
                buildRouteBuilder(camelPackage, className, tag, ops, spec)
            )
        }

        // Emit a properties file with one entry per provider so the routes are
        // runtime-reconfigurable without code changes.
        File(dir, "llm-providers.properties").writeText(providersProperties(spec))
    }

    private fun buildRouteBuilder(
        pkg: String,
        className: String,
        tag: String,
        ops: List<ParsedPath>,
        spec: ParsedSpec,
    ): String = buildString {
        val baseUrl = spec.servers.firstOrNull()?.url ?: ""
        val pathPrefix = if (baseUrl.isNotBlank()) {
            // Extract the path component from the server URL (e.g. /v1, /v1beta)
            baseUrl.substringAfter("://").substringAfter("/", "").let { "/$it".trimEnd('/') }
        } else ""

        appendLine("package $pkg")
        appendLine()
        appendLine("import org.apache.camel.builder.RouteBuilder")
        appendLine("import org.apache.camel.model.rest.RestBindingMode")
        appendLine()
        appendLine("/**")
        appendLine(" * Generated Camel routes for the '$tag' tag of ${spec.info?.title ?: "API"}.")
        appendLine(" *")
        appendLine(" * All routes dispatch through the llm: Camel component (vendor-agnostic).")
        appendLine(" * Set `llm.provider` in your Camel properties to switch backends:")
        appendLine(" *   llm.provider=anthropic  → ${spec.servers.firstOrNull { it.url.contains("anthropic") }?.url ?: "default"}")
        appendLine(" *   llm.provider=google     → Gemini")
        appendLine(" *   llm.provider=lmstudio   → local OpenAI-compat server")
        appendLine(" *   llm.provider=comfyui    → ComfyUI workflow")
        appendLine(" *   llm.provider=langchain4j:openai → any LangChain4j vendor")
        appendLine(" *   llm.provider=djl | jlama → in-process inference")
        appendLine(" */")
        appendLine("class $className : RouteBuilder() {")
        appendLine("    override fun configure() {")
        appendLine("        restConfiguration()")
        appendLine("            .component(\"platform-http\")")
        appendLine("            .bindingMode(RestBindingMode.json)")
        appendLine()
        if (pathPrefix.isNotBlank() && pathPrefix != "/") {
            appendLine("        rest(\"$pathPrefix\")")
        } else {
            appendLine("        rest(\"/\")")
        }
        ops.forEach { op ->
            val method = op.method.lowercase()
            val relPath = if (pathPrefix.isNotBlank() && op.path.startsWith(pathPrefix))
                op.path.removePrefix(pathPrefix)
            else op.path
            appendLine("            .$method(\"$relPath\")")
            appendLine("                .id(\"${op.operationId}\")")
            if (op.streaming) {
                appendLine("                .to(\"llm:stream?provider={{llm.provider}}&model={{llm.model}}&stream=true\")")
            } else {
                appendLine("                .to(\"llm:${op.operationId}?provider={{llm.provider}}&model={{llm.model}}\")")
            }
        }
        appendLine()
        appendLine("        // Direct endpoints for non-HTTP use (Camel → Camel integration)")
        ops.filter { !it.streaming }.forEach { op ->
            appendLine("        from(\"direct:${op.operationId}\")")
            appendLine("            .to(\"llm:${op.operationId}?provider={{llm.provider}}&model={{llm.model}}\")")
        }
        appendLine("    }")
        appendLine("}")
    }

    /**
     * Emit a properties file with one profile per provider. Users drop this
     * into their Camel application and select at runtime:
     *
     *   camel.main.properties-location=classpath:llm-providers.properties
     *   llm.provider=anthropic   # or google|lmstudio|comfyui|djl|jlama|langchain4j:openai
     */
    private fun providersProperties(spec: ParsedSpec): String = buildString {
        appendLine("# Generated Camel properties for vendor-agnostic LLM dispatch.")
        appendLine("# Switch providers by changing llm.provider without modifying code.")
        appendLine()
        appendLine("# --- default from OpenAPI servers[0] ---")
        spec.servers.firstOrNull()?.let { appendLine("llm.base-url=${it.url}") }
        appendLine("llm.provider=anthropic")
        appendLine("llm.model=")
        appendLine()
        appendLine("# --- Anthropic profile ---")
        appendLine("# llm.provider=anthropic")
        appendLine("# llm.model=claude-sonnet-4-6")
        appendLine()
        appendLine("# --- Google Gemini profile ---")
        appendLine("# llm.provider=google")
        appendLine("# llm.model=gemini-2.0-flash")
        appendLine()
        appendLine("# --- OpenAI-compatible local server (LM Studio, llama.cpp, vLLM, Ollama) ---")
        appendLine("# llm.provider=lmstudio")
        appendLine("# llm.model=local-model")
        appendLine()
        appendLine("# --- ComfyUI workflow ---")
        appendLine("# llm.provider=comfyui")
        appendLine("# llm.workflow=/path/to/workflow.json")
        appendLine()
        appendLine("# --- LangChain4j (any supported vendor) ---")
        appendLine("# llm.provider=langchain4j:openai      # or anthropic|gemini|ollama|bedrock|azure")
        appendLine("# llm.model=gpt-4o")
        appendLine()
        appendLine("# --- In-process JVM runtimes ---")
        appendLine("# llm.provider=jlama")
        appendLine("# llm.modelPath=/models/llama3-8b-q4.gguf")
        appendLine("# llm.provider=djl")
        appendLine("# llm.modelPath=djl://ai.djl.huggingface.pytorch/bert-base-uncased")
    }
}
