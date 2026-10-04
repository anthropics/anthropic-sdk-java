package com.anthropic.mcp

import com.fasterxml.jackson.databind.ObjectMapper
import io.modelcontextprotocol.client.McpSyncClient
import io.modelcontextprotocol.spec.McpSchema
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.mock

internal class BetaMcpAdditionalPropertiesTest {
    private val client: McpSyncClient = mock()
    private val mapper = ObjectMapper()

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun preservesExplicitAdditionalProperties(allowed: Boolean) {
        val schema =
            McpSchema.JsonSchema(
                "object",
                mapOf("name" to mapOf("type" to "string")),
                listOf("name"),
                allowed,
                null,
                null,
            )
        val before = mapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(schema)
        val tool = McpSchema.Tool("lookup", null, "Look up a name", schema, null, null, null)
        val definition = BetaMcp.mcpTool(tool, client).definition
        val wire = mapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(definition)
        assertThat(wire.path("input_schema").has("additionalProperties")).isTrue()
        assertThat(wire.path("input_schema").path("additionalProperties").booleanValue())
            .isEqualTo(allowed)
        assertThat(wire.path("input_schema").path("required").get(0).textValue()).isEqualTo("name")
        assertThat(
                wire.path("input_schema").path("properties").path("name").path("type").textValue()
            )
            .isEqualTo("string")
        assertThat(mapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(schema))
            .isEqualTo(before)
    }

    @Test
    fun omittedAdditionalPropertiesRemainsAbsent() {
        val schema = McpSchema.JsonSchema("object", emptyMap(), emptyList(), null, null, null)
        val tool = McpSchema.Tool("lookup", null, null, schema, null, null, null)
        val wire =
            mapper.valueToTree<com.fasterxml.jackson.databind.JsonNode>(
                BetaMcp.mcpTool(tool, client).definition
            )
        assertThat(wire.path("input_schema").has("additionalProperties")).isFalse()
        assertThat(wire.path("input_schema").path("type").textValue()).isEqualTo("object")
    }

    @Test
    fun closedSchemaAndDefinitionsSurviveListConversion() {
        val defs = mapOf<String, Any>("Name" to mapOf("type" to "string"))
        val schema =
            McpSchema.JsonSchema(
                "object",
                mapOf("name" to mapOf("$" + "ref" to "#/$" + "defs/Name")),
                listOf("name"),
                false,
                defs,
                defs,
            )
        val tool = McpSchema.Tool("lookup", null, null, schema, null, null, null)
        val converted = BetaMcp.mcpTools(listOf(tool), client)
        assertThat(converted).hasSize(1)
        val wire =
            mapper
                .valueToTree<com.fasterxml.jackson.databind.JsonNode>(converted[0].definition)
                .path("input_schema")
        assertThat(wire.has("additionalProperties")).isTrue()
        assertThat(wire.path("additionalProperties").booleanValue()).isFalse()
        assertThat(wire.path("$" + "defs").path("Name").path("type").textValue())
            .isEqualTo("string")
        assertThat(wire.path("definitions").path("Name").path("type").textValue())
            .isEqualTo("string")
    }
}
