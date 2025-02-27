// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchCreateParamsTest {

    @Test
    fun create() {
        MessageBatchCreateParams.builder()
            .addRequest(
                MessageBatchCreateParams.Request.builder()
                    .customId("my-custom-id-1")
                    .params(
                        MessageBatchCreateParams.Request.Params.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_3_7_SONNET_LATEST)
                            .metadata(
                                Metadata.builder()
                                    .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                    .build()
                            )
                            .addStopSequence("string")
                            .stream(true)
                            .systemOfTextBlockParams(
                                listOf(
                                    TextBlockParam.builder()
                                        .text("Today's date is 2024-06-01.")
                                        .cacheControl(CacheControlEphemeral.builder().build())
                                        .addCitation(
                                            CitationCharLocationParam.builder()
                                                .citedText("cited_text")
                                                .documentIndex(0L)
                                                .documentTitle("x")
                                                .endCharIndex(0L)
                                                .startCharIndex(0L)
                                                .build()
                                        )
                                        .build()
                                )
                            )
                            .temperature(1.0)
                            .enabledThinking(1024L)
                            .toolChoice(
                                ToolChoiceAuto.builder().disableParallelToolUse(true).build()
                            )
                            .addTool(
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
                            .topK(5L)
                            .topP(0.7)
                            .build()
                    )
                    .build()
            )
            .build()
    }

    @Test
    fun body() {
        val params =
            MessageBatchCreateParams.builder()
                .addRequest(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .metadata(
                                    Metadata.builder()
                                        .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                        .build()
                                )
                                .addStopSequence("string")
                                .stream(true)
                                .systemOfTextBlockParams(
                                    listOf(
                                        TextBlockParam.builder()
                                            .text("Today's date is 2024-06-01.")
                                            .cacheControl(CacheControlEphemeral.builder().build())
                                            .addCitation(
                                                CitationCharLocationParam.builder()
                                                    .citedText("cited_text")
                                                    .documentIndex(0L)
                                                    .documentTitle("x")
                                                    .endCharIndex(0L)
                                                    .startCharIndex(0L)
                                                    .build()
                                            )
                                            .build()
                                    )
                                )
                                .temperature(1.0)
                                .enabledThinking(1024L)
                                .toolChoice(
                                    ToolChoiceAuto.builder().disableParallelToolUse(true).build()
                                )
                                .addTool(
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
                                .topK(5L)
                                .topP(0.7)
                                .build()
                        )
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body).isNotNull
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .metadata(
                                    Metadata.builder()
                                        .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                        .build()
                                )
                                .addStopSequence("string")
                                .stream(true)
                                .systemOfTextBlockParams(
                                    listOf(
                                        TextBlockParam.builder()
                                            .text("Today's date is 2024-06-01.")
                                            .cacheControl(CacheControlEphemeral.builder().build())
                                            .addCitation(
                                                CitationCharLocationParam.builder()
                                                    .citedText("cited_text")
                                                    .documentIndex(0L)
                                                    .documentTitle("x")
                                                    .endCharIndex(0L)
                                                    .startCharIndex(0L)
                                                    .build()
                                            )
                                            .build()
                                    )
                                )
                                .temperature(1.0)
                                .enabledThinking(1024L)
                                .toolChoice(
                                    ToolChoiceAuto.builder().disableParallelToolUse(true).build()
                                )
                                .addTool(
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
                                .topK(5L)
                                .topP(0.7)
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MessageBatchCreateParams.builder()
                .addRequest(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .build()
                        )
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body).isNotNull
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    MessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            MessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .build()
                        )
                        .build()
                )
            )
    }
}
