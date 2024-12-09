// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonNull
import com.anthropic.models.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaPromptCachingMessageCreateParamsTest {

    @Test
    fun createBetaPromptCachingMessageCreateParams() {
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
            .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
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
    }

    @Test
    fun getBody() {
        val params =
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
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
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
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    PromptCachingBetaMessageParam.builder()
                        .content(PromptCachingBetaMessageParam.Content.ofString("string"))
                        .role(PromptCachingBetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(body.metadata())
            .isEqualTo(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences()).isEqualTo(listOf("string"))
        assertThat(body.system())
            .isEqualTo(BetaPromptCachingMessageCreateParams.System.ofString("string"))
        assertThat(body.temperature()).isEqualTo(1.0)
        assertThat(body.toolChoice())
            .isEqualTo(
                ToolChoice.ofToolChoiceAuto(
                    ToolChoiceAuto.builder()
                        .type(ToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
            )
        assertThat(body.tools())
            .isEqualTo(
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
        assertThat(body.topK()).isEqualTo(5L)
        assertThat(body.topP()).isEqualTo(0.7)
    }

    @Test
    fun getBodyWithoutOptionalFields() {
        val params =
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
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    PromptCachingBetaMessageParam.builder()
                        .content(PromptCachingBetaMessageParam.Content.ofString("string"))
                        .role(PromptCachingBetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
    }
}
