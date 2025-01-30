// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageCreateParamsTest {

    @Test
    fun createMessageCreateParams() {
        MessageCreateParams.builder()
            .maxTokens(1024L)
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_3_5_HAIKU_LATEST)
            .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .addStopSequence("string")
            .systemOfTextBlockParams(
                listOf(
                    TextBlockParam.builder()
                        .text("Today's date is 2024-06-01.")
                        .type(TextBlockParam.Type.TEXT)
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .type(CacheControlEphemeral.Type.EPHEMERAL)
                                .build()
                        )
                        .addCitation(
                            CitationCharLocationParam.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .type(CitationCharLocationParam.Type.CHAR_LOCATION)
                                .build()
                        )
                        .build()
                )
            )
            .temperature(1.0)
            .toolChoice(
                ToolChoiceAuto.builder()
                    .type(ToolChoiceAuto.Type.AUTO)
                    .disableParallelToolUse(true)
                    .build()
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
    }

    @Test
    fun body() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .addStopSequence("string")
                .systemOfTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .type(CacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .addCitation(
                                CitationCharLocationParam.builder()
                                    .citedText("cited_text")
                                    .documentIndex(0L)
                                    .documentTitle("x")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .type(CitationCharLocationParam.Type.CHAR_LOCATION)
                                    .build()
                            )
                            .build()
                    )
                )
                .temperature(1.0)
                .toolChoice(
                    ToolChoiceAuto.builder()
                        .type(ToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
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
        val body = params._body()
        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    MessageParam.builder()
                        .content("Hello, world")
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(body.metadata())
            .contains(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences()).contains(listOf("string"))
        assertThat(body.system())
            .contains(
                MessageCreateParams.System.ofTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(TextBlockParam.Type.TEXT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .type(CacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .addCitation(
                                CitationCharLocationParam.builder()
                                    .citedText("cited_text")
                                    .documentIndex(0L)
                                    .documentTitle("x")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .type(CitationCharLocationParam.Type.CHAR_LOCATION)
                                    .build()
                            )
                            .build()
                    )
                )
            )
        assertThat(body.temperature()).contains(1.0)
        assertThat(body.toolChoice())
            .contains(
                ToolChoice.ofAuto(
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
        assertThat(body.topK()).contains(5L)
        assertThat(body.topP()).contains(0.7)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .build()
        val body = params._body()
        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    MessageParam.builder()
                        .content("Hello, world")
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
    }
}
