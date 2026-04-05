package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates suspend service interfaces from parsed OpenAPI paths.
 *
 * POST /v1/messages → suspend fun create(params): Message
 * POST /v1/messages (SSE) → suspend fun createStreaming(params): Flow<Event>
 * GET /v1/models → suspend fun list(params): ModelListPage
 */
class ServiceGenerator(
    private val basePackage: String,
    private val outputDir: File,
) {
    private val servicesPackage = "$basePackage.services"
    private val modelsPackage = "$basePackage.models"
    private val flow = ClassName("kotlinx.coroutines.flow", "Flow")

    fun generate(paths: Map<String, ParsedPath>, security: Map<String, ParsedSecurity>) {
        generate(paths, security, emptyList())
    }

    fun generate(
        paths: Map<String, ParsedPath>,
        security: Map<String, ParsedSecurity>,
        servers: List<ParsedServer>,
    ) {
        // Group paths by tag → one service interface per tag
        val byTag = paths.values.groupBy { it.tags.firstOrNull() ?: "Default" }

        byTag.forEach { (tag, ops) ->
            val serviceName = "${tag.replaceFirstChar { it.uppercase() }}Service"
            generateServiceInterface(serviceName, ops)
        }

        // Generate client factory from security schemes + OpenAPI servers list
        generateClientFactory(security, servers)
    }

    private fun generateServiceInterface(name: String, operations: List<ParsedPath>) {
        val serviceInterface = TypeSpec.classBuilder(name).addModifiers(KModifier.OPEN)

        operations.forEach { op ->
            val funName = op.operationId.replaceFirstChar { it.lowercase() }
            val funBuilder = FunSpec.builder(funName)
                .addModifiers(KModifier.OPEN, KModifier.SUSPEND)

            // Request parameter
            op.requestBodyRef?.let { ref ->
                funBuilder.addParameter("params", ClassName(modelsPackage, ref))
            }

            // Return type
            if (op.streaming) {
                val eventType = op.responseRef?.let { ClassName(modelsPackage, it) } ?: ANY
                funBuilder.returns(flow.parameterizedBy(eventType))
            } else {
                val returnType = op.responseRef?.let { ClassName(modelsPackage, it) } ?: UNIT
                funBuilder.returns(returnType)
            }

            op.description?.let { funBuilder.addKdoc("%L", it) }

            serviceInterface.addFunction(funBuilder.build())
        }

        FileSpec.builder(servicesPackage, name)
            .addType(serviceInterface.build())
            .build()
            .writeTo(outputDir)
    }

    private fun generateClientFactory(
        security: Map<String, ParsedSecurity>,
        servers: List<ParsedServer> = emptyList(),
    ) {
        val clientFactory = FunSpec.builder("createClient")
            .returns(ClassName("io.ktor.client", "HttpClient"))

        // Add parameters from security schemes
        security.forEach { (name, scheme) ->
            when (scheme.type) {
                "apiKey" -> clientFactory.addParameter(
                    ParameterSpec.builder("apiKey", String::class).build()
                )
                "http" -> when (scheme.scheme) {
                    "bearer" -> clientFactory.addParameter(
                        ParameterSpec.builder("token", String::class).build()
                    )
                    "basic" -> {
                        clientFactory.addParameter("username", String::class)
                        clientFactory.addParameter("password", String::class)
                    }
                }
            }
        }

        // Base URL comes from OpenAPI `servers:` list (first entry), not a hardcoded value.
        // Falls back to an empty string only if the spec provides no servers section at all.
        val defaultBaseUrl = servers.firstOrNull()?.url ?: ""
        clientFactory.addParameter(
            ParameterSpec.builder("baseUrl", String::class)
                .defaultValue("%S", defaultBaseUrl)
                .build()
        )

        clientFactory.addCode("""
            |return io.ktor.client.HttpClient(io.ktor.client.engine.cio.CIO) {
            |    install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
            |        io.ktor.serialization.kotlinx.json.json()
            |    }
            |    install(io.ktor.client.plugins.HttpRequestRetry) {
            |        retryOnServerErrors(maxRetries = 2)
            |        exponentialDelay()
            |    }
            |    defaultRequest {
            |        url(baseUrl)
            |    }
    """.trimIndent()
        )

        // Add auth from security schemes
        security.forEach { (_, scheme) ->
            when (scheme.type) {
                "apiKey" -> clientFactory.addCode(
                    """
                    |    defaultRequest {
                    |        header(%S, apiKey)
                    |    }
    """.trimIndent(), scheme.name ?: "x-api-key"
                )
            }
        }

        clientFactory.addCode("}\n")

        FileSpec.builder("$basePackage.client", "ClientFactory")
            .addFunction(clientFactory.build())
            .build()
            .writeTo(outputDir)
    }
}

private fun String.toCamelCase(): String {
    return split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
}
