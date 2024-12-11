// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import com.anthropic.models.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageCreateParamsTest {

    @Test
    fun createMessageCreateParams() {
        MessageCreateParams.builder()
            .maxTokens(1024L)
            .messages(
                listOf(
                    MessageParam.builder()
                        .content(MessageParam.Content.ofString("string"))
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
            .model(Model.CLAUDE_3_5_HAIKU_LATEST)
            .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .stopSequences(listOf("string"))
            .system(
                MessageCreateParams.System.ofTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .build()
                    )
                )
            )
            .temperature(1.0)
            .toolChoice(
                ToolChoice.ofToolChoiceAuto(
                    ToolChoiceAuto.builder()
                        .type(ToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
            )
            .tools(
                listOf(
                    Tool.builder()
                        .inputSchema(
                            Tool.InputSchema.builder()
                                .type(Tool.InputSchema.Type.OBJECT)
                                .properties(
                                    JsonValue.from(
                                        mapOf(
                                            "location" to
                                                mapOf(
                                                    "description" to
                                                        "The city and state, e.g. San Francisco, CA",
                                                    "type" to "string"
                                                ),
                                            "unit" to
                                                mapOf(
                                                    "description" to
                                                        "Unit for the output - one of (celsius, fahrenheit)",
                                                    "type" to "string"
                                                )
                                        )
                                    )
                                )
                                .build()
                        )
                        .name("x")
                        .description("Get the current weather in a given location")
                        .build()
                )
            )
            .topK(5L)
            .topP(0.7)
            .build()
    }

    @Test
    fun getBody() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .messages(
                    listOf(
                        MessageParam.builder()
                            .content(MessageParam.Content.ofString("string"))
                            .role(MessageParam.Role.USER)
                            .build()
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .stopSequences(listOf("string"))
                .system(
                    MessageCreateParams.System.ofTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .type(TextBlockParam.Type.TEXT)
                                .build()
                        )
                    )
                )
                .temperature(1.0)
                .toolChoice(
                    ToolChoice.ofToolChoiceAuto(
                        ToolChoiceAuto.builder()
                            .type(ToolChoiceAuto.Type.AUTO)
                            .disableParallelToolUse(true)
                            .build()
                    )
                )
                .tools(
                    listOf(
                        Tool.builder()
                            .inputSchema(
                                Tool.InputSchema.builder()
                                    .type(Tool.InputSchema.Type.OBJECT)
                                    .properties(
                                        JsonValue.from(
                                            mapOf(
                                                "location" to
                                                    mapOf(
                                                        "description" to
                                                            "The city and state, e.g. San Francisco, CA",
                                                        "type" to "string"
                                                    ),
                                                "unit" to
                                                    mapOf(
                                                        "description" to
                                                            "Unit for the output - one of (celsius, fahrenheit)",
                                                        "type" to "string"
                                                    )
                                            )
                                        )
                                    )
                                    .build()
                            )
                            .name("x")
                            .description("Get the current weather in a given location")
                            .build()
                    )
                )
                .topK(5L)
                .topP(0.7)
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    MessageParam.builder()
                        .content(MessageParam.Content.ofString("string"))
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(body.metadata())
            .isEqualTo(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences()).isEqualTo(listOf("string"))
        assertThat(body.system())
            .isEqualTo(
                MessageCreateParams.System.ofTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .build()
                    )
                )
            )
        assertThat(body.temperature()).isEqualTo(1.0)
        assertThat(body.toolChoice())
            .isEqualTo(
                ToolChoice.ofToolChoiceAuto(
                    ToolChoiceAuto.builder()
                        .type(ToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
            )
        assertThat(body.tools())
            .isEqualTo(
                listOf(
                    Tool.builder()
                        .inputSchema(
                            Tool.InputSchema.builder()
                                .type(Tool.InputSchema.Type.OBJECT)
                                .properties(
                                    JsonValue.from(
                                        mapOf(
                                            "location" to
                                                mapOf(
                                                    "description" to
                                                        "The city and state, e.g. San Francisco, CA",
                                                    "type" to "string"
                                                ),
                                            "unit" to
                                                mapOf(
                                                    "description" to
                                                        "Unit for the output - one of (celsius, fahrenheit)",
                                                    "type" to "string"
                                                )
                                        )
                                    )
                                )
                                .build()
                        )
                        .name("x")
                        .description("Get the current weather in a given location")
                        .build()
                )
            )
        assertThat(body.topK()).isEqualTo(5L)
        assertThat(body.topP()).isEqualTo(0.7)
    }

    @Test
    fun getBodyWithoutOptionalFields() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .messages(
                    listOf(
                        MessageParam.builder()
                            .content(MessageParam.Content.ofString("string"))
                            .role(MessageParam.Role.USER)
                            .build()
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    MessageParam.builder()
                        .content(MessageParam.Content.ofString("string"))
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
    }
}
