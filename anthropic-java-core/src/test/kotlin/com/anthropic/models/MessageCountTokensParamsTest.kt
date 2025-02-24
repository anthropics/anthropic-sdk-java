// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import kotlin.test.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageCountTokensParamsTest {

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
                ToolBash20250124.builder()
                    .cacheControl(CacheControlEphemeral.builder().build())
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
                    ToolBash20250124.builder()
                        .cacheControl(CacheControlEphemeral.builder().build())
                        .build()
                )
                .build()

        val body = params._body()

        assertNotNull(body)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    MessageParam.builder()
                        .content("Hello, world")
                        .role(MessageParam.Role.USER)
                        .build()
                )
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
        assertThat(body.tools())
            .contains(
                listOf(
                    MessageCountTokensTool.ofToolBash20250124(
                        ToolBash20250124.builder()
                            .cacheControl(CacheControlEphemeral.builder().build())
                            .build()
                    )
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

        assertNotNull(body)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    MessageParam.builder()
                        .content("Hello, world")
                        .role(MessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
    }
}
