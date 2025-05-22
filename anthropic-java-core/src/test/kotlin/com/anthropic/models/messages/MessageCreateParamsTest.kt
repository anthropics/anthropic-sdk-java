// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageCreateParamsTest {

    @Test
    fun create() {
        MessageCreateParams.builder()
            .maxTokens(1024L)
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_3_7_SONNET_LATEST)
            .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .serviceTier(MessageCreateParams.ServiceTier.AUTO)
            .addStopSequence("string")
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
            .toolChoice(ToolChoiceAuto.builder().disableParallelToolUse(true).build())
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
                    .type(Tool.Type.CUSTOM)
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
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                .addStopSequence("string")
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
                .toolChoice(ToolChoiceAuto.builder().disableParallelToolUse(true).build())
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
                        .type(Tool.Type.CUSTOM)
                        .build()
                )
                .topK(5L)
                .topP(0.7)
                .build()

        val body = params._body()

        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .containsExactly(
                MessageParam.builder().content("Hello, world").role(MessageParam.Role.USER).build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(body.metadata())
            .contains(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.serviceTier()).contains(MessageCreateParams.ServiceTier.AUTO)
        assertThat(body.stopSequences().getOrNull()).containsExactly("string")
        assertThat(body.system())
            .contains(
                MessageCreateParams.System.ofTextBlockParams(
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
            )
        assertThat(body.temperature()).contains(1.0)
        assertThat(body.thinking())
            .contains(
                ThinkingConfigParam.ofEnabled(
                    ThinkingConfigEnabled.builder().budgetTokens(1024L).build()
                )
            )
        assertThat(body.toolChoice())
            .contains(
                ToolChoice.ofAuto(ToolChoiceAuto.builder().disableParallelToolUse(true).build())
            )
        assertThat(body.tools().getOrNull())
            .containsExactly(
                ToolUnion.ofTool(
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
                        .type(Tool.Type.CUSTOM)
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
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .build()

        val body = params._body()

        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .containsExactly(
                MessageParam.builder().content("Hello, world").role(MessageParam.Role.USER).build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
    }
}
