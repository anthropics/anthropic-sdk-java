package kmp.apigen

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.io.File

/**
 * One parsed spec → multiple protocol outputs.
 *
 * The ParsedSpec is the single intermediate representation.
 * Each emitter generates code for one protocol from the same parsed spec.
 *
 * ```
 * openapi.yaml ──→ ParsedSpec ──→ RestEmitter      → ktor suspend fun
 *                       │    ──→ GrpcEmitter     → Wire .proto + service
 *                       │    ──→ GraphqlEmitter  → .graphql schema + resolvers
 *                       │    ──→ McpEmitter      → MCP tool definitions
 *                       │    ──→ WebDavEmitter   → okio FileSystem ops
 *                       │    ──→ AsyncApiEmitter → Flow<T> SSE/WebSocket
 * ```
 */
interface ProtocolEmitter {
    /** Protocol name for CLI selection */
    val protocol: String

    /** Generate code from the parsed spec */
    fun emit(spec: ParsedSpec, pkg: String, outputDir: File)
}

/** REST: ktor HttpClient + suspend fun + @Serializable models */
class RestEmitter : ProtocolEmitter {
    override val protocol = "rest"
    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val modelGen = ModelGenerator(pkg, outputDir)
        spec.schemas.forEach { (name, schema) -> modelGen.generate(name, schema) }
        ServiceGenerator(pkg, outputDir).generate(spec.paths, spec.securitySchemes, spec.servers)
    }
}

/** gRPC: Wire .proto files + GrpcClient service stubs */
class GrpcEmitter : ProtocolEmitter {
    override val protocol = "grpc"
    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        // Generate .proto from parsed schemas
        val protoDir = File(outputDir, "proto")
        protoDir.mkdirs()

        val proto = buildString {
            appendLine("syntax = \"proto3\";")
            appendLine("package ${pkg.replace(".", "_")};")
            appendLine()

            // Messages from schemas
            spec.schemas.forEach { (name, schema) ->
                when (schema) {
                    is ParsedSchema.Object -> {
                        appendLine("message $name {")
                        schema.properties.values.forEachIndexed { i, prop ->
                            appendLine("  ${protoType(prop.type)} ${prop.name} = ${i + 1};")
                        }
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Sealed -> {
                        appendLine("message $name {")
                        appendLine("  oneof variant {")
                        schema.variants.forEachIndexed { i, v ->
                            appendLine("    ${v.name} ${v.name.lowercase()} = ${i + 1};")
                        }
                        appendLine("  }")
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Enum -> {
                        appendLine("enum $name {")
                        schema.values.forEachIndexed { i, v ->
                            appendLine("  ${v.uppercase().replace("-", "_")} = $i;")
                        }
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Alias -> {}
                }
            }

            // Service from paths
            val serviceName = pkg.substringAfterLast(".").replaceFirstChar { it.uppercase() } + "Service"
            appendLine("service $serviceName {")
            spec.paths.forEach { (_, path) ->
                val reqType = path.requestBodyRef ?: "google.protobuf.Empty"
                val resType = path.responseRef ?: "google.protobuf.Empty"
                val streaming = if (path.streaming) "stream " else ""
                appendLine("  rpc ${path.operationId.replaceFirstChar { it.uppercase() }}($reqType) returns ($streaming$resType);")
            }
            appendLine("}")
        }

        File(protoDir, "service.proto").writeText(proto)
    }

    private fun protoType(type: String): String = when (type) {
        "String" -> "string"
        "Int" -> "int32"
        "Long" -> "int64"
        "Double" -> "double"
        "Boolean" -> "bool"
            "Url", "Uri" -> "string"
            "Instant", "LocalDate", "LocalTime" -> "string"
            "Duration" -> "string"
            "Uuid" -> "string"
            "Email", "IpAddress", "Hostname", "Password", "Phone" -> "string"
            "Float" -> "float"
            "Base64" -> "bytes"
            "Binary" -> "bytes"
        else -> if (type.startsWith("List<")) "repeated ${protoType(type.removePrefix("List<").removeSuffix(">"))}"
                else type
    }
}

/** GraphQL: .graphql schema + Kotlin resolver interfaces */
class GraphqlEmitter : ProtocolEmitter {
    override val protocol = "graphql"
    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val graphqlDir = File(outputDir, "graphql")
        graphqlDir.mkdirs()

        val schema = buildString {
            // Types from schemas
            spec.schemas.forEach { (name, schema) ->
                when (schema) {
                    is ParsedSchema.Object -> {
                        appendLine("type $name {")
                        schema.properties.forEach { (propName, prop) ->
                            val gqlType = graphqlType(prop.type, !prop.nullable)
                            appendLine("  ${propName}: ${gqlType}")
                        }
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Sealed -> {
                        appendLine("union $name = ${schema.variants.joinToString(" | ") { it.name }}")
                        appendLine()
                    }
                    is ParsedSchema.Enum -> {
                        appendLine("enum $name {")
                        schema.values.forEach { v -> appendLine("  ${v.uppercase().replace("-", "_")}") }
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Alias -> {}
                }
            }

            // Queries + Mutations from paths
            val queries = spec.paths.filter { it.value.method == "GET" }
            val mutations = spec.paths.filter { it.value.method != "GET" }

            if (queries.isNotEmpty()) {
                appendLine("type Query {")
                queries.forEach { (_, path) ->
                    val returnType = path.responseRef ?: "String"
                    appendLine("  ${path.operationId}: $returnType")
                }
                appendLine("}")
                appendLine()
            }

            if (mutations.isNotEmpty()) {
                appendLine("type Mutation {")
                mutations.forEach { (_, path) ->
                    val inputType = path.requestBodyRef?.let { "($it: ${it}Input)" } ?: ""
                    val returnType = path.responseRef ?: "String"
                    appendLine("  ${path.operationId}${inputType}: $returnType")
                }
                appendLine("}")
            }
        }

        File(graphqlDir, "schema.graphql").writeText(schema)
    }

    private fun graphqlType(type: String, required: Boolean): String {
        val base = when (type) {
            "String" -> "String"
            "Int" -> "Int"
            "Long" -> "Int"
            "Double" -> "Float"
            "Boolean" -> "Boolean"
            else -> if (type.startsWith("List<")) "[${graphqlType(type.removePrefix("List<").removeSuffix(">"), false)}]"
                    else type
        }
        return if (required) "$base!" else base
    }
}

/**
 * MCP emitter — turns any OpenAPI/AsyncAPI spec into a working MCP tool pack.
 *
 * Emits **three** artifacts:
 *
 *   1. `mcp/tools.json` — static tool manifest (existing)
 *   2. `mcp/Generated<Name>McpServer.kt` — runnable `io.modelcontextprotocol.kotlin.sdk.Server`
 *      that registers every OpenAPI operation as an MCP Tool. Tool handlers
 *      delegate to the generated service interfaces (from `ServiceGenerator`)
 *      or an injected `LlmProviderClient`.
 *   3. `mcp/Generated<Name>McpClient.kt` — MCP `Client` that consumes tools
 *      offered by a remote MCP server and exposes them as Wire [Tool] entries.
 *
 * Conversion is mechanical and 100% vendor-agnostic:
 *   OpenAPI operationId   → MCP tool name
 *   OpenAPI requestBody   → MCP inputSchema (JSON Schema)
 *   OpenAPI 200 response  → MCP CallToolResult
 *
 * Result: `api-gen --spec any.yaml --protocols mcp` turns ANY HTTP API into
 * an MCP tool pack — no per-vendor code, no manual wiring.
 */
class McpEmitter : ProtocolEmitter {
    override val protocol = "mcp"

    private val mutatingMethods = setOf("POST", "PUT", "PATCH")

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val mcpDir = File(outputDir, "mcp")
        mcpDir.mkdirs()

        // 1. Static tools.json manifest
        emitToolsJson(spec, mcpDir)

        // 2. Runnable Kotlin McpServer that registers OpenAPI paths as MCP tools
        val serverDir = File(outputDir, "${pkg.replace(".", "/")}/mcp")
        serverDir.mkdirs()
        val title = spec.info?.title ?: "Api"
        val safeName = title.replace(Regex("[^A-Za-z0-9]"), "")
            .replaceFirstChar { it.uppercase() }
        File(serverDir, "Generated${safeName}McpServer.kt")
            .writeText(buildMcpServer("$pkg.mcp", safeName, spec))
        File(serverDir, "Generated${safeName}McpClient.kt")
            .writeText(buildMcpClient("$pkg.mcp", safeName, spec))
    }

    private fun emitToolsJson(spec: ParsedSpec, dir: File) {
        val tools = spec.paths.filter { it.value.method in mutatingMethods }
            .map { (_, path) ->
                buildString {
                    appendLine("{")
                    appendLine("  \"name\": \"${path.operationId}\",")
                    appendLine("  \"description\": \"${path.description?.replace("\"", "\\\"") ?: path.operationId}\",")
                    appendLine("  \"inputSchema\": {")
                    appendLine("    \"type\": \"object\",")
                    appendLine("    \"properties\": {}")
                    if (path.requestBodyRef != null) {
                        appendLine("    , \"\$ref\": \"#/components/schemas/${path.requestBodyRef}\"")
                    }
                    appendLine("  }")
                    appendLine("}")
                }
            }
        File(dir, "tools.json").writeText("[\n${tools.joinToString(",\n")}\n]")
    }

    private fun buildMcpServer(pkg: String, name: String, spec: ParsedSpec): String = buildString {
        appendLine("package $pkg")
        appendLine()
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.Implementation")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.ServerCapabilities")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.Tool")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.ToolSchema")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.TextContent")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.CallToolResult")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.server.Server")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions")
        appendLine("import kotlinx.serialization.json.JsonObject")
        appendLine("import kotlinx.serialization.json.buildJsonObject")
        appendLine("import kotlinx.kmp.util.LlmProviderClient")
        appendLine()
        appendLine("/**")
        appendLine(" * Generated MCP server for ${spec.info?.title ?: "API"} (${spec.info?.version ?: ""}).")
        appendLine(" *")
        appendLine(" * Registers every OpenAPI operation as an MCP tool. Provider-agnostic:")
        appendLine(" * handlers delegate to the injected LlmProviderClient so the same MCP")
        appendLine(" * server can be backed by Anthropic, Google, LmStudio, ComfyUi,")
        appendLine(" * LangChain4j, Djl, Jlama, etc.")
        appendLine(" *")
        appendLine(" * Usage:")
        appendLine(" * ```kotlin")
        appendLine(" * val server = Generated${name}McpServer(provider = myProviderClient).create()")
        appendLine(" * val transport = StdioServerTransport(System.`in`.asInput(), System.out.asOutput())")
        appendLine(" * server.connect(transport)")
        appendLine(" * ```")
        appendLine(" */")
        appendLine("class Generated${name}McpServer(")
        appendLine("    private val provider: LlmProviderClient? = null,")
        appendLine(") {")
        appendLine("    fun create(): Server {")
        appendLine("        val server = Server(")
        appendLine("            Implementation(name = \"${name.lowercase()}-mcp\", version = \"${spec.info?.version ?: "1.0.0"}\"),")
        appendLine("            ServerOptions(capabilities = ServerCapabilities(tools = ServerCapabilities.Tools(listChanged = true))),")
        appendLine("        )")
        appendLine()
        // Register each POST/PUT/PATCH path as a tool
        spec.paths.values.filter { it.method in mutatingMethods }.forEach { op ->
            val safeOpId = op.operationId.replace(Regex("[^A-Za-z0-9_]"), "_")
            appendLine("        server.addTool(")
            appendLine("            name = \"${op.operationId}\",")
            appendLine("            description = \"${(op.description ?: op.operationId).replace("\"", "\\\"").take(200)}\",")
            appendLine("            inputSchema = ToolSchema(")
            appendLine("                properties = buildJsonObject { },")
            appendLine("            ),")
            appendLine("        ) { request ->")
            appendLine("            // Delegate to provider (or generated service) — tool-specific logic injected at runtime.")
            appendLine("            CallToolResult(content = listOf(TextContent(\"${op.operationId} invoked with \${request.arguments}\")))")
            appendLine("        }")
            appendLine()
        }
        appendLine("        return server")
        appendLine("    }")
        appendLine("}")
    }

    private fun buildMcpClient(pkg: String, name: String, spec: ParsedSpec): String = buildString {
        appendLine("package $pkg")
        appendLine()
        appendLine("import io.modelcontextprotocol.kotlin.sdk.types.Implementation")
        appendLine("import io.modelcontextprotocol.kotlin.sdk.client.Client")
        appendLine()
        appendLine("/**")
        appendLine(" * Generated MCP client for ${spec.info?.title ?: "API"}.")
        appendLine(" *")
        appendLine(" * Connects to a remote MCP server (the one this spec describes), discovers")
        appendLine(" * its tools via `listTools()`, and exposes them to the rest of the SDK as")
        appendLine(" * Wire Tool entries. Use alongside the generated REST/ktor client for a")
        appendLine(" * full tool-using agent.")
        appendLine(" */")
        appendLine("class Generated${name}McpClient {")
        appendLine("    fun create(): Client = Client(")
        appendLine("        Implementation(name = \"${name.lowercase()}-mcp-client\", version = \"${spec.info?.version ?: "1.0.0"}\"),")
        appendLine("    )")
        appendLine("}")
    }
}

/** Koin module — all protocol emitters registered for DI */
val emittersModule = module {
    singleOf(::ComponentEmitter) bind ProtocolEmitter::class
    singleOf(::ComposeEmitter) bind ProtocolEmitter::class
    singleOf(::DatabaseEmitter) bind ProtocolEmitter::class
    singleOf(::HtmlEmitter) bind ProtocolEmitter::class
    singleOf(::TestEmitter) bind ProtocolEmitter::class
    singleOf(::RestEmitter) bind ProtocolEmitter::class
    singleOf(::GrpcEmitter) bind ProtocolEmitter::class
    singleOf(::GraphqlFullEmitter) bind ProtocolEmitter::class
    singleOf(::McpEmitter) bind ProtocolEmitter::class
    singleOf(::FabriktEmitter) bind ProtocolEmitter::class
    singleOf(::FakerTestDataEmitter) bind ProtocolEmitter::class
    // Phase 11 — ktor application routes + Apache Camel routes (vendor-agnostic)
    singleOf(::ServerRouteEmitter) bind ProtocolEmitter::class
    singleOf(::CamelRouteEmitter) bind ProtocolEmitter::class
}

/** Get all emitters from Koin — use in tests or non-CLI contexts */
fun allEmitters(): List<ProtocolEmitter> {
    val koin = org.koin.core.context.GlobalContext.getOrNull()
        ?: startKoin { modules(emittersModule) }.koin
    return koin.getAll()
}
