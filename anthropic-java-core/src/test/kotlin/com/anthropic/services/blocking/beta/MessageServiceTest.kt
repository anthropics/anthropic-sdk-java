// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaCitationCharLocationParam
import com.anthropic.models.beta.messages.BetaMetadata
import com.anthropic.models.beta.messages.BetaRequestMcpServerToolConfiguration
import com.anthropic.models.beta.messages.BetaRequestMcpServerUrlDefinition
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolChoiceAuto
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MessageServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessage =
            messageService.create(
                MessageCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .container("container")
                    .addMcpServer(
                        BetaRequestMcpServerUrlDefinition.builder()
                            .name("name")
                            .url("url")
                            .authorizationToken("authorization_token")
                            .toolConfiguration(
                                BetaRequestMcpServerToolConfiguration.builder()
                                    .addAllowedTool("string")
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .metadata(
                        BetaMetadata.builder()
                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                            .build()
                    )
                    .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                    .addStopSequence("string")
                    .systemOfBetaTextBlockParams(
                        listOf(
                            BetaTextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
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
                                    .addRequired("location")
                                    .build()
                            )
                            .name("name")
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .description("Get the current weather in a given location")
                            .type(BetaTool.Type.CUSTOM)
                            .build()
                    )
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        betaMessage.validate()
    }

    @Test
    fun createStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessageStreamResponse =
            messageService.createStreaming(
                MessageCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .container("container")
                    .addMcpServer(
                        BetaRequestMcpServerUrlDefinition.builder()
                            .name("name")
                            .url("url")
                            .authorizationToken("authorization_token")
                            .toolConfiguration(
                                BetaRequestMcpServerToolConfiguration.builder()
                                    .addAllowedTool("string")
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .metadata(
                        BetaMetadata.builder()
                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                            .build()
                    )
                    .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                    .addStopSequence("string")
                    .systemOfBetaTextBlockParams(
                        listOf(
                            BetaTextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
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
                                    .addRequired("location")
                                    .build()
                            )
                            .name("name")
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .description("Get the current weather in a given location")
                            .type(BetaTool.Type.CUSTOM)
                            .build()
                    )
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        betaMessageStreamResponse.use {
            betaMessageStreamResponse.stream().forEach { betaMessage -> betaMessage.validate() }
        }
    }

    @Test
    fun countTokens() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessageTokensCount =
            messageService.countTokens(
                MessageCountTokensParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .addMcpServer(
                        BetaRequestMcpServerUrlDefinition.builder()
                            .name("name")
                            .url("url")
                            .authorizationToken("authorization_token")
                            .toolConfiguration(
                                BetaRequestMcpServerToolConfiguration.builder()
                                    .addAllowedTool("string")
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .systemOfBetaTextBlockParams(
                        listOf(
                            BetaTextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
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
                                    .addRequired("location")
                                    .build()
                            )
                            .name("name")
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .description("Get the current weather in a given location")
                            .type(BetaTool.Type.CUSTOM)
                            .build()
                    )
                    .build()
            )

        betaMessageTokensCount.validate()
    }
}
