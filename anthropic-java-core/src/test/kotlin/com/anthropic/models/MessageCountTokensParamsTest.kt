// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageCountTokensParamsTest {

    @Test
    fun createMessageCountTokensParams() {
        MessageCountTokensParams.builder()
            .messages(
                listOf(
                    MessageParam.builder()
                        .content(MessageParam.Content.ofString("string"))
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
            .model(Model.CLAUDE_3_5_HAIKU_LATEST)
            .system(
                MessageCountTokensParams.System.ofTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .type(CacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .build()
                    )
                )
            )
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
                        .name("name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .type(CacheControlEphemeral.Type.EPHEMERAL)
                                .build()
                        )
                        .description("Get the current weather in a given location")
                        .build()
                )
            )
            .build()
    }

    @Test
    fun getBody() {
        val params =
            MessageCountTokensParams.builder()
                .messages(
                    listOf(
                        MessageParam.builder()
                            .content(MessageParam.Content.ofString("string"))
                            .role(MessageParam.Role.USER)
                            .build()
                    )
                )
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .system(
                    MessageCountTokensParams.System.ofTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .type(TextBlockParam.Type.TEXT)
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .type(CacheControlEphemeral.Type.EPHEMERAL)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
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
                            .name("name")
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .type(CacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .description("Get the current weather in a given location")
                            .build()
                    )
                )
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
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
        assertThat(body.system())
            .contains(
                MessageCountTokensParams.System.ofTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .type(CacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .build()
                    )
                )
            )
        assertThat(body.toolChoice())
            .contains(
                ToolChoice.ofToolChoiceAuto(
                    ToolChoiceAuto.builder()
                        .type(ToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
            )
        assertThat(body.tools())
            .contains(
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
                        .name("name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .type(CacheControlEphemeral.Type.EPHEMERAL)
                                .build()
                        )
                        .description("Get the current weather in a given location")
                        .build()
                )
            )
    }

    @Test
    fun getBodyWithoutOptionalFields() {
        val params =
            MessageCountTokensParams.builder()
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
