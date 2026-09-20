package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test

internal class BetaResponseToolToParamTest {

    @Test
    fun toParamKeepsEveryInputSchemaKeyword() {
        val tool = jsonMapper.readValue(TOOL_JSON, jacksonTypeRef<BetaResponseTool>())

        val param = tool.toParam()

        assertThat(wireTree(param)).isEqualTo(jsonMapper.readTree(TOOL_JSON))
        assertThat(jsonMapper.writeValueAsString(param._inputSchema())).isEqualTo(INPUT_SCHEMA_JSON)
        assertThat(param.inputSchema().required()).contains(listOf("a"))
        assertThat(param.inputSchema()._additionalProperties())
            .containsExactly(
                entry("additionalProperties", JsonValue.from(false)),
                entry("\$defs", JsonValue.from(mapOf("A" to mapOf("type" to "string")))),
            )
    }

    @Test
    fun toParamKeepsMissingOrNullInputSchema() {
        val missing = """{"type":"custom","name":"t"}"""
        val explicitNull = """{"type":"custom","name":"t","input_schema":null}"""

        for (json in listOf(missing, explicitNull)) {
            val tool = jsonMapper.readValue(json, jacksonTypeRef<BetaResponseTool>())

            assertThat(wireTree(tool.toParam())).isEqualTo(jsonMapper.readTree(json))
        }
    }

    @Test
    fun compactionBlockToParamKeepsToolChangesAsReturned() {
        val json =
            """{"type":"compaction","content":"summary","encrypted_content":"ZW5j","signature":"c2ln","tool_changes":[""" +
                """{"type":"tool_addition","tool":{"type":"tool_definition","definition":$TOOL_JSON}},""" +
                """{"type":"tool_removal","tool":{"type":"tool_reference","name":"old"}}]}"""
        val block = jsonMapper.readValue(json, jacksonTypeRef<BetaCompactionBlock>())
        val definition =
            block.toolChanges().get()[0].asAddition().tool().asDefinition().definition()
        assertThat(definition.isBetaResponseTool()).isTrue()

        assertThat(wireTree(block.toParam())).isEqualTo(jsonMapper.readTree(json))
    }

    private fun wireTree(value: Any): JsonNode =
        jsonMapper.readTree(jsonMapper.writeValueAsString(value))

    private companion object {

        val jsonMapper = jsonMapper()

        const val INPUT_SCHEMA_JSON =
            """{"type":"object","properties":{"a":{"${'$'}ref":"#/${'$'}defs/A"}},"required":["a"],"additionalProperties":false,"${'$'}defs":{"A":{"type":"string"}}}"""

        const val TOOL_JSON = """{"type":"custom","name":"t","input_schema":$INPUT_SCHEMA_JSON}"""
    }
}
