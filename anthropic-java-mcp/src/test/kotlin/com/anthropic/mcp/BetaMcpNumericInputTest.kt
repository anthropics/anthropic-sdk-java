package com.anthropic.mcp

import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.ObjectMapper
import io.modelcontextprotocol.client.McpSyncClient
import io.modelcontextprotocol.spec.McpSchema
import java.math.BigDecimal
import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class BetaMcpNumericInputTest {
    private val client: McpSyncClient = mock()
    private val mapper = ObjectMapper()

    private fun runnable() =
        BetaMcp.mcpTool(
            McpSchema.Tool(
                "capture",
                null,
                "Capture input",
                McpSchema.JsonSchema("object", null, null, null, null, null),
                null,
                null,
                null,
            ),
            client,
        )

    private fun capturedArguments(json: String): Map<String, Any?> {
        whenever(client.callTool(any<McpSchema.CallToolRequest>()))
            .thenReturn(
                McpSchema.CallToolResult(listOf(McpSchema.TextContent("ok")), false, null, null)
            )
        val result = runnable().runner.apply(json)
        assertThat(result.blocks()).isPresent
        val request = argumentCaptor<McpSchema.CallToolRequest>()
        verify(client).callTool(request.capture())
        assertThat(request.firstValue.name()).isEqualTo("capture")
        return request.firstValue.arguments()
    }

    @ParameterizedTest
    @ValueSource(
        strings =
            [
                "0.12345678901234567890123456789",
                "9007199254740993.0",
                "1e400",
                "1e-400",
                "-1.234567890123456789e50",
            ]
    )
    fun decimalInputRetainsExactValue(number: String) {
        val arguments = capturedArguments("""{"value":$number}""")
        val value = checkNotNull(arguments.getValue("value"))
        assertThat(value).isInstanceOf(BigDecimal::class.java)
        assertThat(value as BigDecimal).isEqualByComparingTo(BigDecimal(number))
        val serialized = mapper.writeValueAsString(arguments)
        // Read the numeric token as a decimal rather than converting it through Double.
        // The transport's JSON token may change notation, but not its mathematical value.
        mapper.factory.createParser(serialized).use { parser ->
            parser.nextToken()
            parser.nextToken()
            parser.nextToken()
            assertThat(parser.decimalValue).isEqualByComparingTo(BigDecimal(number))
        }
    }

    @Test
    fun nestedDecimalAndIntegerArgumentsStayDistinctFromStrings() {
        val arguments =
            capturedArguments(
                """{"nested":{"values":[0.1234567890123456789,1e-400]},"integer":18446744073709551615,"text":"1e400","flag":false,"nothing":null,"empty":{}}"""
            )
        val nested = checkNotNull(arguments["nested"]) as Map<*, *>
        val values = checkNotNull(nested["values"]) as List<*>
        assertThat(values[0]).isEqualTo(BigDecimal("0.1234567890123456789"))
        assertThat(values[1]).isEqualTo(BigDecimal("1e-400"))
        assertThat(arguments["integer"]).isEqualTo(BigInteger("18446744073709551615"))
        assertThat(arguments["text"]).isEqualTo("1e400")
        assertThat(arguments["flag"]).isEqualTo(false)
        assertThat(arguments).containsKey("nothing")
        assertThat(arguments["nothing"]).isNull()
        assertThat(arguments["empty"]).isEqualTo(emptyMap<String, Any>())
    }

    @ParameterizedTest
    @ValueSource(strings = ["{", "[1]", "\"text\""])
    fun invalidInputStillFailsBeforeCallingTheTool(json: String) {
        assertThatThrownBy { runnable().runner.apply(json) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageContaining("Failed to parse JSON tool input")
        verifyNoInteractions(client)
    }
}
