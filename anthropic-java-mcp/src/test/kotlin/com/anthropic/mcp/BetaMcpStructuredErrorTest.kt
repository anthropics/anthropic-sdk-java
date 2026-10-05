package com.anthropic.mcp

import com.anthropic.errors.AnthropicException
import com.fasterxml.jackson.databind.ObjectMapper
import io.modelcontextprotocol.client.McpSyncClient
import io.modelcontextprotocol.spec.McpSchema
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class BetaMcpStructuredErrorTest {
    private val client: McpSyncClient = mock()
    private val mapper = ObjectMapper()

    private fun execute(result: McpSchema.CallToolResult): String {
        whenever(client.callTool(any<McpSchema.CallToolRequest>())).thenReturn(result)
        val tool =
            McpSchema.Tool(
                "check",
                null,
                null,
                McpSchema.JsonSchema("object", null, null, null, null, null),
                null,
                null,
                null,
            )
        return BetaMcp.mcpTool(tool, client).runner.apply("{}").string().orElseThrow()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun structuredOnlyResultPreservesPayload(isError: Boolean) {
        val payloads =
            listOf(
                emptyMap<String, Any?>(),
                mapOf(
                    "message" to "try again",
                    "retry" to false,
                    "attempt" to 0,
                    "nested" to mapOf("values" to listOf("café", null, 3)),
                ),
            )
        for (payload in payloads) {
            val result = McpSchema.CallToolResult(emptyList(), isError, payload, null)
            val text =
                if (isError) {
                    val error =
                        org.junit.jupiter.api.assertThrows<AnthropicException> { execute(result) }
                    requireNotNull(error.message)
                } else execute(result)
            assertThat(mapper.readTree(text)).isEqualTo(mapper.valueToTree(payload))
        }
    }

    @Test
    fun existingTextErrorTakesPrecedenceOverStructuredContent() {
        val result =
            McpSchema.CallToolResult(
                listOf(McpSchema.TextContent("first"), McpSchema.TextContent("second")),
                true,
                mapOf("ignored" to "detail"),
                null,
            )
        val error = org.junit.jupiter.api.assertThrows<AnthropicException> { execute(result) }
        assertThat(error.message).isEqualTo("first\nsecond")
    }

    @Test
    fun errorWithoutEitherContentKeepsExistingEmptyMessage() {
        val result = McpSchema.CallToolResult(emptyList(), true, null, null)
        val error = org.junit.jupiter.api.assertThrows<AnthropicException> { execute(result) }
        assertThat(error.message).isEmpty()
    }

    @Test
    fun structuredErrorSerializationFailureRetainsItsCause() {
        val recursive = mutableMapOf<String, Any?>()
        recursive["self"] = recursive
        val result = McpSchema.CallToolResult(emptyList(), true, recursive, null)
        val error =
            org.junit.jupiter.api.assertThrows<com.anthropic.errors.AnthropicInvalidDataException> {
                execute(result)
            }
        assertThat(error)
            .hasMessage("Failed to serialize structuredContent")
            .hasCauseInstanceOf(com.fasterxml.jackson.core.JsonProcessingException::class.java)
    }
}
