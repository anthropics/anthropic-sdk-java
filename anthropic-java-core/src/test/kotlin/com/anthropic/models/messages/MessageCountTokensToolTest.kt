// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MessageCountTokensToolTest {

    @Test
    fun ofTool() {
        val tool =
            Tool.builder()
                .inputSchema(
                    Tool.InputSchema.builder()
                        .properties(
                            JsonValue.from(
                                mapOf(
                                    "location" to
                                        mapOf(
                                            "description" to
                                                "The city and state, e.g. San Francisco, CA",
                                            "type" to "string",
                                        ),
                                    "unit" to
                                        mapOf(
                                            "description" to
                                                "Unit for the output - one of (celsius, fahrenheit)",
                                            "type" to "string",
                                        ),
                                )
                            )
                        )
                        .build()
                )
                .name("name")
                .cacheControl(CacheControlEphemeral.builder().build())
                .description("Get the current weather in a given location")
                .build()

        val messageCountTokensTool = MessageCountTokensTool.ofTool(tool)

        assertThat(messageCountTokensTool.tool()).contains(tool)
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
    }

    @Test
    fun ofToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofTool(
                Tool.builder()
                    .inputSchema(
                        Tool.InputSchema.builder()
                            .properties(
                                JsonValue.from(
                                    mapOf(
                                        "location" to
                                            mapOf(
                                                "description" to
                                                    "The city and state, e.g. San Francisco, CA",
                                                "type" to "string",
                                            ),
                                        "unit" to
                                            mapOf(
                                                "description" to
                                                    "Unit for the output - one of (celsius, fahrenheit)",
                                                "type" to "string",
                                            ),
                                    )
                                )
                            )
                            .build()
                    )
                    .name("name")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .description("Get the current weather in a given location")
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolBash20250124() {
        val toolBash20250124 =
            ToolBash20250124.builder().cacheControl(CacheControlEphemeral.builder().build()).build()

        val messageCountTokensTool = MessageCountTokensTool.ofToolBash20250124(toolBash20250124)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).contains(toolBash20250124)
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
    }

    @Test
    fun ofToolBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolBash20250124(
                ToolBash20250124.builder()
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolTextEditor20250124() {
        val toolTextEditor20250124 =
            ToolTextEditor20250124.builder()
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250124(toolTextEditor20250124)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).contains(toolTextEditor20250124)
    }

    @Test
    fun ofToolTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250124(
                ToolTextEditor20250124.builder()
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val messageCountTokensTool =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<MessageCountTokensTool>())

        val e = assertThrows<AnthropicInvalidDataException> { messageCountTokensTool.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
