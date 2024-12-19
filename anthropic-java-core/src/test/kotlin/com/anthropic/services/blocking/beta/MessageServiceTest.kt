// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.AnthropicBeta
import com.anthropic.models.BetaCacheControlEphemeral
import com.anthropic.models.BetaMessageCountTokensParams
import com.anthropic.models.BetaMessageCreateParams
import com.anthropic.models.BetaMessageParam
import com.anthropic.models.BetaMetadata
import com.anthropic.models.BetaTextBlockParam
import com.anthropic.models.BetaTool
import com.anthropic.models.BetaToolChoice
import com.anthropic.models.BetaToolChoiceAuto
import com.anthropic.models.BetaToolUnion
import com.anthropic.models.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class MessageServiceTest {

    @Test
    fun callCreate() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()
        val betaMessage =
            messageService.create(
                BetaMessageCreateParams.builder()
                    .maxTokens(1024L)
                    .messages(
                        listOf(
                            BetaMessageParam.builder()
                                .content(BetaMessageParam.Content.ofString("Hello, world"))
                                .role(BetaMessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .metadata(
                        BetaMetadata.builder()
                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                            .build()
                    )
                    .stopSequences(listOf("string"))
                    .system(
                        BetaMessageCreateParams.System.ofBetaTextBlockParams(
                            listOf(
                                BetaTextBlockParam.builder()
                                    .text("Today's date is 2024-06-01.")
                                    .type(BetaTextBlockParam.Type.TEXT)
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                            .build()
                                    )
                                    .build()
                            )
                        )
                    )
                    .temperature(1.0)
                    .toolChoice(
                        BetaToolChoice.ofBetaToolChoiceAuto(
                            BetaToolChoiceAuto.builder()
                                .type(BetaToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            BetaToolUnion.ofBetaTool(
                                BetaTool.builder()
                                    .inputSchema(
                                        BetaTool.InputSchema.builder()
                                            .type(BetaTool.InputSchema.Type.OBJECT)
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
                                    .name("x")
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                            .build()
                                    )
                                    .description("Get the current weather in a given location")
                                    .type(BetaTool.Type.CUSTOM)
                                    .build()
                            )
                        )
                    )
                    .topK(5L)
                    .topP(0.7)
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )
        println(betaMessage)
        betaMessage.validate()
    }

    @Test
    fun callCreateStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessageStream =
            messageService.createStreaming(
                BetaMessageCreateParams.builder()
                    .maxTokens(1024L)
                    .messages(
                        listOf(
                            BetaMessageParam.builder()
                                .content(BetaMessageParam.Content.ofString("Hello, world"))
                                .role(BetaMessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .metadata(
                        BetaMetadata.builder()
                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                            .build()
                    )
                    .stopSequences(listOf("string"))
                    .system(
                        BetaMessageCreateParams.System.ofBetaTextBlockParams(
                            listOf(
                                BetaTextBlockParam.builder()
                                    .text("Today's date is 2024-06-01.")
                                    .type(BetaTextBlockParam.Type.TEXT)
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                            .build()
                                    )
                                    .build()
                            )
                        )
                    )
                    .temperature(1.0)
                    .toolChoice(
                        BetaToolChoice.ofBetaToolChoiceAuto(
                            BetaToolChoiceAuto.builder()
                                .type(BetaToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            BetaToolUnion.ofBetaTool(
                                BetaTool.builder()
                                    .inputSchema(
                                        BetaTool.InputSchema.builder()
                                            .type(BetaTool.InputSchema.Type.OBJECT)
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
                                    .name("x")
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                            .build()
                                    )
                                    .description("Get the current weather in a given location")
                                    .type(BetaTool.Type.CUSTOM)
                                    .build()
                            )
                        )
                    )
                    .topK(5L)
                    .topP(0.7)
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )

        betaMessageStream.use {
            betaMessageStream.stream().forEach {
                println(it)
                it.validate()
            }
        }
    }

    @Test
    fun callCountTokens() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()
        val betaMessageTokensCount =
            messageService.countTokens(
                BetaMessageCountTokensParams.builder()
                    .messages(
                        listOf(
                            BetaMessageParam.builder()
                                .content(BetaMessageParam.Content.ofString("string"))
                                .role(BetaMessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .system(
                        BetaMessageCountTokensParams.System.ofBetaTextBlockParams(
                            listOf(
                                BetaTextBlockParam.builder()
                                    .text("Today's date is 2024-06-01.")
                                    .type(BetaTextBlockParam.Type.TEXT)
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                            .build()
                                    )
                                    .build()
                            )
                        )
                    )
                    .toolChoice(
                        BetaToolChoice.ofBetaToolChoiceAuto(
                            BetaToolChoiceAuto.builder()
                                .type(BetaToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            BetaMessageCountTokensParams.Tool.ofBetaTool(
                                BetaTool.builder()
                                    .inputSchema(
                                        BetaTool.InputSchema.builder()
                                            .type(BetaTool.InputSchema.Type.OBJECT)
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
                                    .name("x")
                                    .cacheControl(
                                        BetaCacheControlEphemeral.builder()
                                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                            .build()
                                    )
                                    .description("Get the current weather in a given location")
                                    .type(BetaTool.Type.CUSTOM)
                                    .build()
                            )
                        )
                    )
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )
        println(betaMessageTokensCount)
        betaMessageTokensCount.validate()
    }
}
