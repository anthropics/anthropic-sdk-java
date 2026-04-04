package com.anthropic.apigen

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.parser.OpenAPIV3Parser
import io.swagger.v3.parser.core.models.ParseOptions
import java.io.File

/** Parsed OpenAPI spec — normalized for code generation. */
data class ParsedSpec(
    val schemas: Map<String, ParsedSchema>,
    val paths: Map<String, ParsedPath>,
    val securitySchemes: Map<String, ParsedSecurity>,
)

sealed class ParsedSchema {
    data class Object(
        val properties: Map<String, PropertyInfo>,
        val required: Set<String>,
        val description: String?,
    ) : ParsedSchema()

    data class Enum(
        val values: List<String>,
        val description: String?,
    ) : ParsedSchema()

    data class Sealed(
        val variants: List<VariantInfo>,
        val discriminator: String?,
        val discriminatorMapping: Map<String, String>,
        val description: String?,
    ) : ParsedSchema()

    data class Alias(val target: String) : ParsedSchema()
}

data class PropertyInfo(
    val name: String,
    val type: String,
    val format: String?,
    val nullable: Boolean,
    val description: String?,
    val ref: String?,
)

data class VariantInfo(
    val name: String,
    val ref: String,
)

data class ParsedPath(
    val method: String,
    val path: String,
    val operationId: String,
    val requestBodyRef: String?,
    val responseRef: String?,
    val streaming: Boolean,
    val description: String?,
    val tags: List<String>,
)

data class ParsedSecurity(
    val type: String,       // apiKey, http, oauth2, openIdConnect
    val scheme: String?,    // bearer, basic
    val name: String?,      // header name for apiKey
    val location: String?,  // header, query, cookie
)

object OpenApiParser {

    fun parse(file: File): ParsedSpec {
        val options = ParseOptions().apply {
            isResolve = true
            isResolveFully = true
        }
        val openApi: OpenAPI = OpenAPIV3Parser().read(file.absolutePath, null, options)
            ?: error("Failed to parse OpenAPI spec: ${file.name}")

        return ParsedSpec(
            schemas = parseSchemas(openApi),
            paths = parsePaths(openApi),
            securitySchemes = parseSecurity(openApi),
        )
    }

    private fun parseSchemas(openApi: OpenAPI): Map<String, ParsedSchema> {
        val schemas = openApi.components?.schemas ?: return emptyMap()
        return schemas.mapNotNull { (name, schema) ->
            val parsed = parseSchema(name, schema) ?: return@mapNotNull null
            name to parsed
        }.toMap()
    }

    private fun parseSchema(name: String, schema: Schema<*>): ParsedSchema? {
        // oneOf → sealed class
        if (!schema.oneOf.isNullOrEmpty()) {
            val discriminator = schema.discriminator?.propertyName
            val mapping = schema.discriminator?.mapping ?: emptyMap()
            val variants = schema.oneOf.map { variant ->
                val ref = variant.`$ref` ?: return@map null
                val variantName = ref.substringAfterLast("/")
                VariantInfo(variantName, ref)
            }.filterNotNull()
            return ParsedSchema.Sealed(variants, discriminator, mapping, schema.description)
        }

        // enum
        if (!schema.enum.isNullOrEmpty()) {
            return ParsedSchema.Enum(schema.enum.map { it.toString() }, schema.description)
        }

        // $ref alias
        if (schema.`$ref` != null) {
            return ParsedSchema.Alias(schema.`$ref`.substringAfterLast("/"))
        }

        // object with properties
        if (!schema.properties.isNullOrEmpty()) {
            val required = schema.required?.toSet() ?: emptySet()
            val props = schema.properties.map { (propName, propSchema) ->
                propName to PropertyInfo(
                    name = propName,
                    type = resolveType(propSchema),
                    format = propSchema.format,
                    nullable = propSchema.nullable == true || propName !in required,
                    description = propSchema.description,
                    ref = propSchema.`$ref`,
                )
            }.toMap()
            return ParsedSchema.Object(props, required, schema.description)
        }

        return null
    }

    private fun resolveType(schema: Schema<*>): String {
        schema.`$ref`?.let { return it.substringAfterLast("/") }
        return when (schema.type) {
            "string" -> if (schema.format == "date-time") "OffsetDateTime" else "String"
            "integer" -> if (schema.format == "int64") "Long" else "Int"
            "number" -> "Double"
            "boolean" -> "Boolean"
            "array" -> "List<${resolveType(schema.items ?: return "Any")}>"
            "object" -> if (schema.additionalProperties is Schema<*>) {
                "Map<String, ${resolveType(schema.additionalProperties as Schema<*>)}>"
            } else "JsonObject"
            else -> "Any"
        }
    }

    private fun parsePaths(openApi: OpenAPI): Map<String, ParsedPath> {
        val paths = openApi.paths ?: return emptyMap()
        return paths.flatMap { (path, pathItem) ->
            pathItem.readOperationsMap().map { (method, op) ->
                val operationId = op.operationId ?: "${method.name.lowercase()}${path.replace("/", "_")}"
                val requestRef = op.requestBody?.content?.values?.firstOrNull()
                    ?.schema?.`$ref`?.substringAfterLast("/")
                val responseRef = op.responses?.get("200")?.content?.values?.firstOrNull()
                    ?.schema?.`$ref`?.substringAfterLast("/")
                val streaming = op.responses?.get("200")?.content?.containsKey("text/event-stream") == true

                operationId to ParsedPath(
                    method = method.name,
                    path = path,
                    operationId = operationId,
                    requestBodyRef = requestRef,
                    responseRef = responseRef,
                    streaming = streaming,
                    description = op.description ?: op.summary,
                    tags = op.tags ?: emptyList(),
                )
            }
        }.toMap()
    }

    private fun parseSecurity(openApi: OpenAPI): Map<String, ParsedSecurity> {
        val schemes = openApi.components?.securitySchemes ?: return emptyMap()
        return schemes.map { (name, scheme) ->
            name to ParsedSecurity(
                type = scheme.type?.toString() ?: "apiKey",
                scheme = scheme.scheme,
                name = scheme.name,
                location = scheme.`in`?.toString(),
            )
        }.toMap()
    }
}
