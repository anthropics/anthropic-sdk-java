package com.anthropic.models.beta.messages

import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.jsonMapper
import com.anthropic.core.toolFromClass
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.databind.JsonNode
import io.swagger.v3.oas.annotations.media.Schema
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class BetaToolDefinitionFromClassTest {

    @Suppress("unused")
    @JsonClassDescription("Gets the weather in a location")
    class GetWeather(val location: String, val unit: String)

    // `"pattern"` is not a supported keyword, so local validation rejects the derived schema.
    @Suppress("unused") class Unsupported(@get:Schema(pattern = "unsupported") val s: String)

    @Test
    fun additionBlockDefinitionToolFromClass() {
        val block =
            BetaRequestToolAdditionBlock.builder().definitionTool(GetWeather::class.java).build()

        val tool = block.tool().asDefinition().definition().asBetaTool()
        assertThat(tool).isEqualTo(toolFromClass(GetWeather::class.java))
        assertStrictGetWeather(wireTree(block).get("tool").get("definition"))
        assertThat(wireTree(block).get("type").textValue()).isEqualTo("tool_addition")
        assertThat(wireTree(block).get("tool").get("type").textValue()).isEqualTo("tool_definition")
    }

    @Test
    fun additionBlockDefinitionToolFromClassLocalValidation() {
        assertThatThrownBy {
                BetaRequestToolAdditionBlock.builder().definitionTool(Unsupported::class.java)
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {
                BetaRequestToolAdditionBlock.builder()
                    .definitionTool(Unsupported::class.java, JsonSchemaLocalValidation.YES)
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)

        val block =
            BetaRequestToolAdditionBlock.builder()
                .definitionTool(Unsupported::class.java, JsonSchemaLocalValidation.NO)
                .build()
        assertThat(block.tool().asDefinition().definition().asBetaTool())
            .isEqualTo(toolFromClass(Unsupported::class.java, JsonSchemaLocalValidation.NO))
    }

    @Test
    fun toolDefinitionParamDefinitionFromClass() {
        val definition =
            BetaToolChangeToolDefinitionParam.builder().definition(GetWeather::class.java).build()

        assertThat(definition.definition().asBetaTool())
            .isEqualTo(toolFromClass(GetWeather::class.java))
        assertStrictGetWeather(wireTree(definition).get("definition"))
    }

    @Test
    fun toolDefinitionParamDefinitionFromClassLocalValidation() {
        assertThatThrownBy {
                BetaToolChangeToolDefinitionParam.builder().definition(Unsupported::class.java)
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {
                BetaToolChangeToolDefinitionParam.builder()
                    .definition(Unsupported::class.java, JsonSchemaLocalValidation.YES)
            }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)

        val definition =
            BetaToolChangeToolDefinitionParam.builder()
                .definition(Unsupported::class.java, JsonSchemaLocalValidation.NO)
                .build()
        assertThat(definition.definition().asBetaTool())
            .isEqualTo(toolFromClass(Unsupported::class.java, JsonSchemaLocalValidation.NO))
    }

    private fun assertStrictGetWeather(tool: JsonNode) {
        assertThat(tool.get("name").textValue()).isEqualTo("get_weather")
        assertThat(tool.get("description").textValue()).isEqualTo("Gets the weather in a location")
        assertThat(tool.get("strict").booleanValue()).isTrue()
        val schema = tool.get("input_schema")
        assertThat(schema.get("type").textValue()).isEqualTo("object")
        assertThat(schema.get("additionalProperties").booleanValue()).isFalse()
        assertThat(schema.get("properties").fieldNames().asSequence().toList())
            .containsExactlyInAnyOrder("location", "unit")
        assertThat(schema.get("required").map { it.textValue() })
            .containsExactlyInAnyOrder("location", "unit")
    }

    private fun wireTree(value: Any): JsonNode =
        jsonMapper.readTree(jsonMapper.writeValueAsString(value))

    private companion object {

        val jsonMapper = jsonMapper()
    }
}
