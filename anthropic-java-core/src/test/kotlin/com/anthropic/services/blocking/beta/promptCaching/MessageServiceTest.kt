// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.promptCaching

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonNull
import com.anthropic.models.*
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
        val messageService = client.beta().promptCaching().messages()
        val promptCachingBetaMessage =
            messageService.create(
                BetaPromptCachingMessageCreateParams.builder()
                    .maxTokens(1024L)
                    .messages(
                        listOf(
                            PromptCachingBetaMessageParam.builder()
                                .content(PromptCachingBetaMessageParam.Content.ofString("string"))
                                .role(PromptCachingBetaMessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .stopSequences(listOf("string"))
                    .system(BetaPromptCachingMessageCreateParams.System.ofString("string"))
                    .temperature(1.0)
                    .toolChoice(
                        ToolChoice.ofToolChoiceAuto(
                            ToolChoiceAuto.builder()
                                .type(ToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            PromptCachingBetaTool.builder()
                                .inputSchema(
                                    PromptCachingBetaTool.InputSchema.builder()
                                        .type(PromptCachingBetaTool.InputSchema.Type.OBJECT)
                                        .properties(JsonNull.of())
                                        .build()
                                )
                                .name("x")
                                .cacheControl(
                                    PromptCachingBetaCacheControlEphemeral.builder()
                                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                                        .build()
                                )
                                .description("Get the current weather in a given location")
                                .build()
                        )
                    )
                    .topK(5L)
                    .topP(0.7)
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )
        println(promptCachingBetaMessage)
        promptCachingBetaMessage.validate()
    }

    @Test
    fun callCreateStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().promptCaching().messages()

        val promptCachingBetaMessageStream =
            messageService.createStreaming(
                BetaPromptCachingMessageCreateParams.builder()
                    .maxTokens(1024L)
                    .messages(
                        listOf(
                            PromptCachingBetaMessageParam.builder()
                                .content(PromptCachingBetaMessageParam.Content.ofString("string"))
                                .role(PromptCachingBetaMessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .stopSequences(listOf("string"))
                    .system(BetaPromptCachingMessageCreateParams.System.ofString("string"))
                    .temperature(1.0)
                    .toolChoice(
                        ToolChoice.ofToolChoiceAuto(
                            ToolChoiceAuto.builder()
                                .type(ToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            PromptCachingBetaTool.builder()
                                .inputSchema(
                                    PromptCachingBetaTool.InputSchema.builder()
                                        .type(PromptCachingBetaTool.InputSchema.Type.OBJECT)
                                        .properties(JsonNull.of())
                                        .build()
                                )
                                .name("x")
                                .cacheControl(
                                    PromptCachingBetaCacheControlEphemeral.builder()
                                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                                        .build()
                                )
                                .description("Get the current weather in a given location")
                                .build()
                        )
                    )
                    .topK(5L)
                    .topP(0.7)
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )

        promptCachingBetaMessageStream.use {
            promptCachingBetaMessageStream.stream().forEach {
                println(it)
                it.validate()
            }
        }
    }
}
