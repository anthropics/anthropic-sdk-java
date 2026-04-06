package kotlinx.kmp.util.core.mcp

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class McpTypesTest {

    private val json = Json { prettyPrint = false }

    @Test
    fun toolDefinition_serializes() {
        val tool = toolDefinition(
            name = "get_weather",
            description = "Get weather for a location",
            properties = mapOf("location" to "string", "unit" to "string"),
            required = listOf("location"),
        )
        val encoded = json.encodeToString(tool)
        assertTrue(encoded.contains("\"name\":\"get_weather\""))
        assertTrue(encoded.contains("\"location\""))
        assertTrue(encoded.contains("\"required\":[\"location\"]"))
    }

    @Test
    fun toolDefinition_roundTrip() {
        val tool = toolDefinition(
            name = "search",
            description = "Search the web",
            properties = mapOf("query" to "string"),
        )
        val encoded = json.encodeToString(tool)
        val decoded = json.decodeFromString<ToolDefinition>(encoded)
        assertEquals("search", decoded.name)
        assertEquals("Search the web", decoded.description)
    }

    @Test
    fun toolCallResult_text() {
        val result = textResult("Hello, world!")
        assertEquals(1, result.content.size)
        assertTrue(result.content[0] is ToolContent.Text)
        assertEquals("Hello, world!", (result.content[0] as ToolContent.Text).text)
        assertFalse(result.isError)
    }

    @Test
    fun toolCallResult_error() {
        val result = errorResult("Something went wrong")
        assertTrue(result.isError)
        assertEquals("Something went wrong", (result.content[0] as ToolContent.Text).text)
    }

    @Test
    fun toolCallRequest_serializes() {
        val request = ToolCallRequest(
            name = "get_weather",
            arguments = kotlinx.serialization.json.buildJsonObject {
                put("location", kotlinx.serialization.json.JsonPrimitive("San Francisco"))
            },
        )
        val encoded = json.encodeToString(request)
        assertTrue(encoded.contains("\"name\":\"get_weather\""))
        assertTrue(encoded.contains("San Francisco"))
    }
}
