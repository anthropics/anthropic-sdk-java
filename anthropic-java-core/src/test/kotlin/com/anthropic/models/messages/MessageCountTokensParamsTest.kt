// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageCountTokensParamsTest {

    @Test
    fun create() {
        MessageCountTokensParams.builder()
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
                            .addRequired("location")
                            .build()
                    )
                    .name("name")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .description("Get the current weather in a given location")
                    .type(Tool.Type.CUSTOM)
                    .build()
            )
            .build()
    }

    @Test
    fun body() {
        val params =
            MessageCountTokensParams.builder()
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(CacheControlEphemeral.builder().build())
                        .description("Get the current weather in a given location")
                        .type(Tool.Type.CUSTOM)
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.messages())
            .containsExactly(
                MessageParam.builder().content("Hello, world").role(MessageParam.Role.USER).build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(body.system())
            .contains(
                MessageCountTokensParams.System.ofTextBlockParams(
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
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(CacheControlEphemeral.builder().build())
                        .description("Get the current weather in a given location")
                        .type(Tool.Type.CUSTOM)
                        .build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MessageCountTokensParams.builder()
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .build()

        val body = params._body()

        assertThat(body.messages())
            .containsExactly(
                MessageParam.builder().content("Hello, world").role(MessageParam.Role.USER).build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
    }
}
