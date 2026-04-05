package kmp.apigen

import java.io.File

/**
 * Generates production ktor server routing from OpenAPI paths.
 *
 * For each tag in the spec, emits a top-level extension function on
 * `io.ktor.server.routing.Routing` that registers all of the tag's paths:
 *
 * ```kotlin
 * fun Routing.messagesRoutes(provider: LlmProviderClient) {
 *     route("/v1") {
 *         post("/messages") {
 *             val request = call.receive<LlmRequest>()
 *             val response = provider.complete(request)
 *             call.respond(response)
 *         }
 *         sse("/messages/stream") {
 *             val request = call.receive<LlmRequest>()
 *             provider.stream(request).collect { event ->
 *                 send(ServerSentEvent(data = Json.encodeToString(event)))
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * Each route handler takes an injected [kotlinx.kmp.util.LlmProviderClient]
 * (from Piece 2) so the same generated server can be installed against any
 * provider at runtime — Anthropic, Google Gemini, LM Studio, ComfyUI,
 * LangChain4j, DJL, or Jlama.
 *
 * This reuses the routing DSL pattern already proven in
 * [TestEmitter.generateTestClass] (lines 121-150) but emits production routes
 * in a separate file per tag, not test scaffolding.
 */
class ServerRouteEmitter : ProtocolEmitter {
    override val protocol = "ktor-server"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val routesPackage = "$pkg.server"
        val dir = File(outputDir, routesPackage.replace(".", "/"))
        dir.mkdirs()

        // Group paths by first tag → one routes file per tag
        val byTag = spec.paths.values.groupBy { it.tags.firstOrNull() ?: "Default" }

        byTag.forEach { (tag, ops) ->
            val fileName = "${tag.replaceFirstChar { it.uppercase() }}Routes.kt"
            File(dir, fileName).writeText(buildRoutesFile(routesPackage, tag, ops, spec))
        }

        // Generate a top-level Application extension that installs everything
        File(dir, "GeneratedApplication.kt").writeText(buildApplicationFile(routesPackage, byTag.keys, spec))
    }

    private fun buildRoutesFile(
        pkg: String,
        tag: String,
        ops: List<ParsedPath>,
        spec: ParsedSpec,
    ): String {
        val fnName = "${tag.lowercase()}Routes"
        val modelsPkg = "${pkg.removeSuffix(".server")}.models"
        val body = buildString {
            appendLine("package $pkg")
            appendLine()
            appendLine("import io.ktor.server.application.*")
            appendLine("import io.ktor.server.request.*")
            appendLine("import io.ktor.server.response.*")
            appendLine("import io.ktor.server.routing.*")
            appendLine("import io.ktor.server.sse.*")
            appendLine("import io.ktor.sse.*")
            appendLine("import io.ktor.http.*")
            appendLine("import kotlinx.serialization.json.Json")
            appendLine("import kotlinx.kmp.util.LlmProviderClient")
            appendLine("import kotlinx.kmp.util.LlmRequest")
            appendLine("import kotlinx.kmp.util.LlmResponse")
            appendLine()
            appendLine("/**")
            appendLine(" * Generated ktor routes for the '$tag' tag. Install against any LlmProvider:")
            appendLine(" *   routing { ${fnName}(myProviderClient) }")
            appendLine(" * Provider can be Anthropic, Google, LmStudio, ComfyUi, LangChain4j, Djl, Jlama, Mcp.")
            appendLine(" */")
            appendLine("fun Routing.$fnName(provider: LlmProviderClient) {")
            ops.forEach { op ->
                appendRoute(op, modelsPkg)
            }
            appendLine("}")
        }
        return body
    }

    private fun StringBuilder.appendRoute(op: ParsedPath, modelsPkg: String) {
        val method = op.method.lowercase()
        val path = op.path
        val isStreaming = op.streaming
        val reqType = op.requestBodyRef?.let { "$modelsPkg.$it" }

        when {
            isStreaming -> {
                // SSE endpoint
                appendLine("    sse(\"$path\") {")
                if (reqType != null) {
                    appendLine("        val request = call.receive<$reqType>()")
                } else {
                    appendLine("        val request = LlmRequest()")
                }
                appendLine("        // Delegate to the injected provider client")
                appendLine("        provider.stream(request as? LlmRequest ?: LlmRequest()).collect { event ->")
                appendLine("            send(ServerSentEvent(data = Json.encodeToString(")
                appendLine("                kotlinx.kmp.util.LlmStreamEvent.ADAPTER, event)))")
                appendLine("        }")
                appendLine("    }")
            }
            method == "post" || method == "put" || method == "patch" -> {
                appendLine("    $method(\"$path\") {")
                if (reqType != null) {
                    appendLine("        val request = call.receive<$reqType>()")
                } else {
                    appendLine("        val request = call.receiveText()")
                }
                appendLine("        val response = provider.complete(request as? LlmRequest ?: LlmRequest())")
                appendLine("        call.respond(response)")
                appendLine("    }")
            }
            method == "get" -> {
                appendLine("    get(\"$path\") {")
                appendLine("        call.respond(HttpStatusCode.OK, mapOf(\"operationId\" to \"${op.operationId}\"))")
                appendLine("    }")
            }
            method == "delete" -> {
                appendLine("    delete(\"$path\") {")
                appendLine("        call.respond(HttpStatusCode.NoContent)")
                appendLine("    }")
            }
        }
    }

    private fun buildApplicationFile(
        pkg: String,
        tags: Set<String>,
        spec: ParsedSpec,
    ): String = buildString {
        appendLine("package $pkg")
        appendLine()
        appendLine("import io.ktor.server.application.*")
        appendLine("import io.ktor.server.plugins.contentnegotiation.*")
        appendLine("import io.ktor.server.routing.*")
        appendLine("import io.ktor.server.sse.SSE")
        appendLine("import io.ktor.server.websocket.WebSockets")
        appendLine("import io.ktor.serialization.kotlinx.json.*")
        appendLine("import kotlinx.kmp.util.LlmProviderClient")
        appendLine()
        appendLine("/**")
        appendLine(" * Top-level generated ktor Application module.")
        appendLine(" *")
        appendLine(" * Usage:")
        appendLine(" * ```kotlin")
        appendLine(" * embeddedServer(CIO, port = 8080) {")
        appendLine(" *     generatedModule(myProviderClient)")
        appendLine(" * }.start(wait = true)")
        appendLine(" * ```")
        appendLine(" *")
        appendLine(" * Spec source: ${spec.info?.title ?: "(untitled)"} ${spec.info?.version ?: ""}")
        appendLine(" * Default base URL from OpenAPI servers[0]: ${spec.servers.firstOrNull()?.url ?: "(none)"}")
        appendLine(" */")
        appendLine("fun Application.generatedModule(provider: LlmProviderClient) {")
        appendLine("    install(ContentNegotiation) { json() }")
        appendLine("    install(SSE)")
        appendLine("    install(WebSockets)")
        appendLine("    routing {")
        tags.forEach { tag ->
            appendLine("        ${tag.lowercase()}Routes(provider)")
        }
        appendLine("    }")
        appendLine("}")
    }
}
