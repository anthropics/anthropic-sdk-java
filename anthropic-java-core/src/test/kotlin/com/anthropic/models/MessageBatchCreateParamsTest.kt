// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchCreateParamsTest {

    @Test
    fun createMessageBatchCreateParams() {
        MessageBatchCreateParams.builder()
            .addRequest(
                MessageBatchCreateParams.Request.builder()
                    .customId("my-custom-id-1")
                    .params(
                        MessageBatchCreateParams.Request.Params.builder()
                            .maxTokens(1024L)
                            .addMessage(
                                MessageParam.builder()
                                    .content(MessageParam.Content.ofString("Hello, world"))
                                    .role(MessageParam.Role.USER)
                                    .build()
                            )
                            .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                            .metadata(
                                Metadata.builder()
                                    .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                    .build()
                            )
                            .addStopSequence("string")
                            .stream(true)
                            .system(
                                MessageBatchCreateParams.Request.Params.System.ofTextBlockParams(
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
                            .temperature(1.0)
                            .toolChoice(
                                ToolChoice.ofToolChoiceAuto(
                                    ToolChoiceAuto.builder()
                                        .type(ToolChoiceAuto.Type.AUTO)
                                        .disableParallelToolUse(true)
                                        .build()
                                )
                            )
                            .addTool(
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
                            .topK(5L)
                            .topP(0.7)
                            .build()
                    )
                    .build()
            )
            .build()
    }

    @Test
    fun getBody() {
        val params =
            MessageBatchCreateParams.builder()
                .addRequest(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addMessage(
                                    MessageParam.builder()
                                        .content(MessageParam.Content.ofString("Hello, world"))
                                        .role(MessageParam.Role.USER)
                                        .build()
                                )
                                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                .metadata(
                                    Metadata.builder()
                                        .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                        .build()
                                )
                                .addStopSequence("string")
                                .stream(true)
                                .system(
                                    MessageBatchCreateParams.Request.Params.System
                                        .ofTextBlockParams(
                                            listOf(
                                                TextBlockParam.builder()
                                                    .text("Today's date is 2024-06-01.")
                                                    .type(TextBlockParam.Type.TEXT)
                                                    .cacheControl(
                                                        CacheControlEphemeral.builder()
                                                            .type(
                                                                CacheControlEphemeral.Type.EPHEMERAL
                                                            )
                                                            .build()
                                                    )
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
                                .addTool(
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
                                .topK(5L)
                                .topP(0.7)
                                .build()
                        )
                        .build()
                )
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addMessage(
                                    MessageParam.builder()
                                        .content(MessageParam.Content.ofString("Hello, world"))
                                        .role(MessageParam.Role.USER)
                                        .build()
                                )
                                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                .metadata(
                                    Metadata.builder()
                                        .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                        .build()
                                )
                                .addStopSequence("string")
                                .stream(true)
                                .system(
                                    MessageBatchCreateParams.Request.Params.System
                                        .ofTextBlockParams(
                                            listOf(
                                                TextBlockParam.builder()
                                                    .text("Today's date is 2024-06-01.")
                                                    .type(TextBlockParam.Type.TEXT)
                                                    .cacheControl(
                                                        CacheControlEphemeral.builder()
                                                            .type(
                                                                CacheControlEphemeral.Type.EPHEMERAL
                                                            )
                                                            .build()
                                                    )
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
                                .addTool(
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
                                .topK(5L)
                                .topP(0.7)
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun getBodyWithoutOptionalFields() {
        val params =
            MessageBatchCreateParams.builder()
                .addRequest(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addMessage(
                                    MessageParam.builder()
                                        .content(MessageParam.Content.ofString("Hello, world"))
                                        .role(MessageParam.Role.USER)
                                        .build()
                                )
                                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                .build()
                        )
                        .build()
                )
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addMessage(
                                    MessageParam.builder()
                                        .content(MessageParam.Content.ofString("Hello, world"))
                                        .role(MessageParam.Role.USER)
                                        .build()
                                )
                                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                .build()
                        )
                        .build()
                )
            )
    }
}
