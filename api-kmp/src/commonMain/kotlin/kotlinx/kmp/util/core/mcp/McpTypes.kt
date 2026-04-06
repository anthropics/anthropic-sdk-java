package kotlinx.kmp.util.core.mcp

import kotlinx.kmp.util.core.JsonValue
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.*

/**
 * Provider-agnostic MCP tool definitions.
 *
 * These types mirror the MCP SDK's Tool/CallToolRequest/CallToolResult
 * but live in commonMain so they're usable on all KMP targets without
 * pulling in the JVM-only MCP SDK dependency.
 *
 * On JVM, [McpToolBridge] converts between these types and the real
 * MCP SDK types for interop.
 */

@Serializable
data class ToolDefinition(
    val name: String,
    val description: String = "",
    @SerialName("inputSchema")
    val inputSchema: JsonElement = buildJsonObject {
        put("type", JsonPrimitive("object"))
        put("properties", buildJsonObject { })
    },
)

@Serializable
data class ToolCallRequest(
    val name: String,
    val arguments: JsonElement = JsonObject(emptyMap()),
)

@Serializable
data class ToolCallResult(
    val content: List<ToolContent> = emptyList(),
    @SerialName("isError")
    val isError: Boolean = false,
)

@Serializable
sealed class ToolContent {
    @Serializable
    @SerialName("text")
    data class Text(val text: String) : ToolContent()

    @Serializable
    @SerialName("image")
    data class Image(val data: String, val mimeType: String) : ToolContent()

    @Serializable
    @SerialName("resource")
    data class Resource(val uri: String, val text: String? = null, val mimeType: String? = null) : ToolContent()
}

/**
 * Convert SDK [JsonValue] to kotlinx [JsonElement] for MCP schema interop.
 */
fun JsonValue.toJsonElement(): JsonElement = kotlinx.kmp.util.core.json.toJsonElement(this)

/**
 * Convert kotlinx [JsonElement] to SDK [JsonValue].
 */
fun JsonElement.toJsonValue(): JsonValue = kotlinx.kmp.util.core.json.fromJsonElement(this)

/**
 * Build a [ToolDefinition] from a name, description, and a map of
 * property name -> type string (for simple tool schemas).
 */
fun toolDefinition(
    name: String,
    description: String = "",
    properties: Map<String, String> = emptyMap(),
    required: List<String> = emptyList(),
): ToolDefinition = ToolDefinition(
    name = name,
    description = description,
    inputSchema = buildJsonObject {
        put("type", JsonPrimitive("object"))
        put("properties", buildJsonObject {
            properties.forEach { (propName, propType) ->
                put(propName, buildJsonObject { put("type", JsonPrimitive(propType)) })
            }
        })
        if (required.isNotEmpty()) {
            put("required", JsonArray(required.map { JsonPrimitive(it) }))
        }
    },
)

/**
 * Create a successful text result.
 */
fun textResult(text: String): ToolCallResult =
    ToolCallResult(content = listOf(ToolContent.Text(text)))

/**
 * Create an error result.
 */
fun errorResult(message: String): ToolCallResult =
    ToolCallResult(content = listOf(ToolContent.Text(message)), isError = true)
