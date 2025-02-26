// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.AnthropicBeta
import com.anthropic.models.BetaCacheControlEphemeral
import com.anthropic.models.BetaCitationCharLocationParam
import com.anthropic.models.BetaMessageCountTokensParams
import com.anthropic.models.BetaMessageCreateParams
import com.anthropic.models.BetaMetadata
import com.anthropic.models.BetaTextBlockParam
import com.anthropic.models.BetaToolChoiceAuto
import com.anthropic.models.BetaToolComputerUse20241022
import com.anthropic.models.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class MessageServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageServiceAsync = client.beta().messages()

        val betaMessageFuture =
            messageServiceAsync.create(
                BetaMessageCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .metadata(
                        BetaMetadata.builder()
                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                            .build()
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
            )

        val betaMessage = betaMessageFuture.get()
        betaMessage.validate()
    }

    @Test
    fun createStreaming() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageServiceAsync = client.beta().messages()

        val betaMessageStreamResponse =
            messageServiceAsync.createStreaming(
                BetaMessageCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .metadata(
                        BetaMetadata.builder()
                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                            .build()
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
            )

        val onCompleteFuture =
            betaMessageStreamResponse
                .subscribe { betaMessage -> betaMessage.validate() }
                .onCompleteFuture()
        onCompleteFuture.get()
    }

    @Test
    fun countTokens() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageServiceAsync = client.beta().messages()

        val betaMessageTokensCountFuture =
            messageServiceAsync.countTokens(
                BetaMessageCountTokensParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
                    .build()
            )

        val betaMessageTokensCount = betaMessageTokensCountFuture.get()
        betaMessageTokensCount.validate()
    }
}
