// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaCitationCharLocationParam
import com.anthropic.models.beta.messages.BetaMetadata
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolChoiceAuto
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.messages.Model
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
                MessageCreateParams.builder()
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
                        BetaTool.builder()
                            .inputSchema(
                                BetaTool.InputSchema.builder()
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
                            .cacheControl(BetaCacheControlEphemeral.builder().build())
                            .description("Get the current weather in a given location")
                            .type(BetaTool.Type.CUSTOM)
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
                MessageCreateParams.builder()
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
                        BetaTool.builder()
                            .inputSchema(
                                BetaTool.InputSchema.builder()
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
                            .cacheControl(BetaCacheControlEphemeral.builder().build())
                            .description("Get the current weather in a given location")
                            .type(BetaTool.Type.CUSTOM)
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
                MessageCountTokensParams.builder()
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
                        BetaTool.builder()
                            .inputSchema(
                                BetaTool.InputSchema.builder()
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
                            .cacheControl(BetaCacheControlEphemeral.builder().build())
                            .description("Get the current weather in a given location")
                            .type(BetaTool.Type.CUSTOM)
                            .build()
                    )
                    .build()
            )

        val betaMessageTokensCount = betaMessageTokensCountFuture.get()
        betaMessageTokensCount.validate()
    }
}
