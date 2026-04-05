package kmp.apigen

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
    val sourceFile: java.io.File? = null,
    /** OpenAPI `servers:` array — base URLs for the API. First entry is the default. */
    val servers: List<ParsedServer> = emptyList(),
    /** OpenAPI `info:` block — title, version, description. Used for naming the generated SDK. */
    val info: ParsedInfo? = null,
)

/**
 * OpenAPI `servers:` entry (§4.8.5). Holds a base URL and optional variable substitutions.
 * First server becomes the generated client's default baseUrl instead of a hardcoded URL.
 */
data class ParsedServer(
    val url: String,
    val description: String = "",
    val variables: Map<String, String> = emptyMap(),  // server variable name → default value
)

/** OpenAPI `info:` block (§4.8.2). Drives SDK name + version in generated code. */
data class ParsedInfo(
    val title: String,
    val version: String = "",
    val description: String = "",
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
    val customSql: String? = null,
    /**
     * True when the operation accepts `multipart/form-data` — i.e. the
     * generated client must submit file uploads via
     * `io.ktor.client.request.forms.MultiPartFormDataContent` instead of
     * JSON. Media-gen providers (Suno audio upload, PixVerse image upload,
     * Adobe Firefly reference-image, ComfyUI /upload/image) exercise this.
     */
    val multipart: Boolean = false,
    /**
     * File part schema when [multipart] is true. Maps form-field name →
     * accepted content-types (e.g. `"audio" -> ["audio/mpeg", "audio/wav"]`).
     * Empty when the operation has text fields only.
     */
    val multipartFileFields: Map<String, List<String>> = emptyMap(),
    /** Text (non-file) form fields when multipart. */
    val multipartTextFields: List<String> = emptyList(),
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
            isResolveFully = false  // Keep $ref intact for oneOf variant resolution
        }
        val openApi: OpenAPI = OpenAPIV3Parser().read(file.absolutePath, null, options)
            ?: error("Failed to parse OpenAPI spec: ${file.name}")

        return ParsedSpec(
            schemas = parseSchemas(openApi),
            paths = parsePaths(openApi),
            securitySchemes = parseSecurity(openApi),
            sourceFile = file,
            servers = parseServers(openApi),
            info = parseInfo(openApi),
        )
    }

    private fun parseServers(openApi: OpenAPI): List<ParsedServer> {
        val servers = openApi.servers ?: return emptyList()
        return servers.map { s ->
            val vars = s.variables?.mapValues { (_, v) -> v.default ?: "" } ?: emptyMap()
            // Substitute {var} placeholders with defaults so the URL is usable as-is
            val resolvedUrl = vars.entries.fold(s.url ?: "") { acc, (k, v) ->
                acc.replace("{$k}", v)
            }
            ParsedServer(
                url = resolvedUrl,
                description = s.description ?: "",
                variables = vars,
            )
        }
    }

    private fun parseInfo(openApi: OpenAPI): ParsedInfo? {
        val info = openApi.info ?: return null
        return ParsedInfo(
            title = info.title ?: "Api",
            version = info.version ?: "",
            description = info.description ?: "",
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
            // After resolveFully, $ref is null — use discriminator mapping or schema title
            val variants = schema.oneOf.mapIndexed { i, variant ->
                val variantName = variant.`$ref`?.substringAfterLast("/")
                    ?: mapping.values.elementAtOrNull(i)?.substringAfterLast("/")
                    ?: variant.title
                    ?: variant.properties?.keys?.let { props ->
                        // Infer name from unique properties
                        mapping.entries.find { (_, ref) ->
                            val refName = ref.substringAfterLast("/")
                            schema.oneOf.any { v -> v.title == refName || v.properties?.containsKey(discriminator) == true }
                        }?.value?.substringAfterLast("/")
                    }
                    ?: "Variant$i"
                VariantInfo(variantName, variant.`$ref` ?: variantName)
            }
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
            "string" -> when (schema.format) {
                "date-time" -> "Instant"
                "date" -> "LocalDate"
                "time" -> "LocalTime"
                "duration" -> "Duration"
                "uri", "url" -> "Uri"
                "email" -> "Email"
                "uuid" -> "Uuid"
                "ipv4", "ipv6" -> "IpAddress"
                "hostname" -> "Hostname"
                "byte" -> "Base64"
                "binary" -> "Binary"
                "password" -> "Password"

                "phone" -> "Phone"
                "address" -> "PostalAddress"
                "geo", "geojson", "geopoint" -> "GeoPoint"
                "locale", "bcp47" -> "Locale"
                "currency", "iso4217" -> "Currency"
                "timezone", "tz", "iana-tz" -> "Timezone"
                "country", "iso3166" -> "Country"
                "language", "iso639" -> "Language"
                "measure", "unit" -> "Measure"
                "person-name", "name" -> "PersonName"

                // RFC 6350 vCard (.vcf) + RFC 7095 jCard JSON
                // String field with format: vcard|vcf|jcard → proto VCardContact
                "vcard", "vcf", "jcard" -> "VCardContact"

                // RFC 5545 iCalendar (.ics) + RFC 7265 jCal JSON
                // String field with format: icalendar|ical|ics|jcal → proto ICalEvent
                "icalendar", "ical", "ics", "jcal" -> "ICalEvent"

                // RFC 4791 CalDAV calendar collection
                "caldav", "calendar-data" -> "ICalEvent"

                // RFC 6352 CardDAV address book entry
                "carddav", "address-data" -> "VCardContact"

                else -> "String"
            }
            "integer" -> if (schema.format == "int64") "Long" else "Int"
            "number" -> if (schema.format == "float") "Float" else "Double"
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
                val requestBodyContent = op.requestBody?.content
                val requestRef = requestBodyContent?.values?.firstOrNull()
                    ?.schema?.`$ref`?.substringAfterLast("/")
                val responseRef = op.responses?.get("200")?.content?.values?.firstOrNull()
                    ?.schema?.`$ref`?.substringAfterLast("/")
                val streaming = op.responses?.get("200")?.content?.containsKey("text/event-stream") == true

                // Detect multipart/form-data — drives file-upload code gen
                val multipartSchema = requestBodyContent?.get("multipart/form-data")?.schema
                val isMultipart = multipartSchema != null
                val multipartFileFields = mutableMapOf<String, List<String>>()
                val multipartTextFields = mutableListOf<String>()
                if (isMultipart && multipartSchema?.properties != null) {
                    multipartSchema.properties.forEach { (propName, propSchema) ->
                        // Binary file field: type=string, format=binary (OpenAPI 3.x convention)
                        if (propSchema.type == "string" && propSchema.format == "binary") {
                            // Accepted content-types come from encoding if specified,
                            // else a sensible default based on field name heuristic.
                            val encoding = requestBodyContent.get("multipart/form-data")
                                ?.encoding?.get(propName)
                            val contentTypes = encoding?.contentType?.split(",")?.map { it.trim() }
                                ?: defaultMultipartContentTypes(propName)
                            multipartFileFields[propName] = contentTypes
                        } else {
                            multipartTextFields.add(propName)
                        }
                    }
                }

                operationId to ParsedPath(
                    method = method.name,
                    path = path,
                    operationId = operationId,
                    requestBodyRef = requestRef,
                    responseRef = responseRef,
                    streaming = streaming,
                    description = op.description ?: op.summary,
                    tags = op.tags ?: emptyList(),
                    customSql = op.extensions?.get("x-sql") as? String,
                    multipart = isMultipart,
                    multipartFileFields = multipartFileFields,
                    multipartTextFields = multipartTextFields,
                )
            }
        }.toMap()
    }

    /**
     * Sensible default content-types for a multipart file field when the
     * spec doesn't declare them via `encoding.<field>.contentType`. Picks
     * based on field-name heuristics — covers Suno audio, PixVerse image,
     * Adobe Firefly reference image, ComfyUI upload/image, NotebookLM source.
     */
    private fun defaultMultipartContentTypes(fieldName: String): List<String> {
        val lower = fieldName.lowercase()
        return when {
            "audio" in lower || "music" in lower || "song" in lower ->
                listOf("audio/mpeg", "audio/wav", "audio/ogg", "audio/flac")
            "image" in lower || "img" in lower || "photo" in lower || "picture" in lower ->
                listOf("image/png", "image/jpeg", "image/webp", "image/gif")
            "video" in lower || "movie" in lower || "clip" in lower ->
                listOf("video/mp4", "video/webm", "video/quicktime")
            "pdf" in lower || "document" in lower || "source" in lower || "doc" in lower ->
                listOf("application/pdf", "text/plain", "text/markdown")
            else -> listOf("application/octet-stream")
        }
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
