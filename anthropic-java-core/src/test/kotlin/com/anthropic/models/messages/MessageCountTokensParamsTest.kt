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
            .model(Model.CLAUDE_SONNET_4_5_20250929)
            .systemOfTextBlockParams(
                listOf(
                    TextBlockParam.builder()
                        .text("Today's date is 2024-06-01.")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
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
                                Tool.InputSchema.Properties.builder()
                                    .putAdditionalProperty("location", JsonValue.from("bar"))
                                    .putAdditionalProperty("unit", JsonValue.from("bar"))
                                    .build()
                            )
                            .addRequired("location")
                            .build()
                    )
                    .name("name")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
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
                .model(Model.CLAUDE_SONNET_4_5_20250929)
                .systemOfTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
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
                                    Tool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
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
        assertThat(body.model()).isEqualTo(Model.CLAUDE_SONNET_4_5_20250929)
        assertThat(body.system())
            .contains(
                MessageCountTokensParams.System.ofTextBlockParams(
                    listOf(
                        TextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
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
                                    Tool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
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
                .model(Model.CLAUDE_SONNET_4_5_20250929)
                .build()

        val body = params._body()

        assertThat(body.messages())
            .containsExactly(
                MessageParam.builder().content("Hello, world").role(MessageParam.Role.USER).build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_SONNET_4_5_20250929)
    }
}
