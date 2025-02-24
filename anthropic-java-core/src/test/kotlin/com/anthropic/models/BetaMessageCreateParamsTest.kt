// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageCreateParamsTest {

    @Test
    fun create() {
        BetaMessageCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .maxTokens(1024L)
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_3_7_SONNET_LATEST)
            .metadata(BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .addStopSequence("string")
            .systemOfBetaTextBlockParams(
                listOf(
                    BetaTextBlockParam.builder()
                        .text("Today's date is 2024-06-01.")
                        .cacheControl(BetaCacheControlEphemeral.builder().build())
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
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
            .toolChoice(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())
            .addTool(
                BetaToolComputerUse20241022.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .displayNumber(0L)
                    .build()
            )
            .topK(5L)
            .topP(0.7)
            .build()
    }

    @Test
    fun body() {
        val params =
            BetaMessageCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .metadata(
                    BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                )
                .addStopSequence("string")
                .systemOfBetaTextBlockParams(
                    listOf(
                        BetaTextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .cacheControl(BetaCacheControlEphemeral.builder().build())
                            .addCitation(
                                BetaCitationCharLocationParam.builder()
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
                .toolChoice(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())
                .addTool(
                    BetaToolComputerUse20241022.builder()
                        .displayHeightPx(1L)
                        .displayWidthPx(1L)
                        .cacheControl(BetaCacheControlEphemeral.builder().build())
                        .displayNumber(0L)
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
                    BetaMessageParam.builder()
                        .content("Hello, world")
                        .role(BetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(body.metadata())
            .contains(BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences()).contains(listOf("string"))
        assertThat(body.system())
            .contains(
                BetaMessageCreateParams.System.ofBetaTextBlockParams(
                    listOf(
                        BetaTextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .cacheControl(BetaCacheControlEphemeral.builder().build())
                            .addCitation(
                                BetaCitationCharLocationParam.builder()
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
                BetaThinkingConfigParam.ofEnabled(
                    BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()
                )
            )
        assertThat(body.toolChoice())
            .contains(
                BetaToolChoice.ofAuto(
                    BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()
                )
            )
        assertThat(body.tools())
            .contains(
                listOf(
                    BetaToolUnion.ofComputerUse20241022(
                        BetaToolComputerUse20241022.builder()
                            .displayHeightPx(1L)
                            .displayWidthPx(1L)
                            .cacheControl(BetaCacheControlEphemeral.builder().build())
                            .displayNumber(0L)
                            .build()
                    )
                )
            )
        assertThat(body.topK()).contains(5L)
        assertThat(body.topP()).contains(0.7)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            BetaMessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .build()

        val body = params._body()

        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    BetaMessageParam.builder()
                        .content("Hello, world")
                        .role(BetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
    }
}
