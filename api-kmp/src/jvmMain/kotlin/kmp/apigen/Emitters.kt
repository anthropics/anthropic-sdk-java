package kmp.apigen

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
        ServiceGenerator(pkg, outputDir).generate(spec.paths, spec.securitySchemes)
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

/** MCP: tool definitions for MCP SDK */
class McpEmitter : ProtocolEmitter {
    override val protocol = "mcp"
    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val mcpDir = File(outputDir, "mcp")
        mcpDir.mkdirs()

        // Each mutation path → MCP tool
        val tools = spec.paths.filter { it.value.method == "POST" }.map { (_, path) ->
            buildString {
                appendLine("{")
                appendLine("  \"name\": \"${path.operationId}\",")
                appendLine("  \"description\": \"${path.description ?: path.operationId}\",")
                appendLine("  \"inputSchema\": {")
                appendLine("    \"type\": \"object\",")
                appendLine("    \"properties\": {}")
                appendLine("  }")
                appendLine("}")
            }
        }

        File(mcpDir, "tools.json").writeText("[\n${tools.joinToString(",\n")}\n]")
    }
}

/** Registry of all protocol emitters */
val ALL_EMITTERS = listOf(
    ComponentEmitter(),
    ComposeEmitter(),
    DatabaseEmitter(),
    RestEmitter(),
    GrpcEmitter(),
    GraphqlEmitter(),
    McpEmitter(),
)
