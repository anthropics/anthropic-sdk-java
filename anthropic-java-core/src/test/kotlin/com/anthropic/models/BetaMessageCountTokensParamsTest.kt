// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonNull
import com.anthropic.models.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageCountTokensParamsTest {

    @Test
    fun createBetaMessageCountTokensParams() {
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
            .system(BetaMessageCountTokensParams.System.ofString("string"))
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
                                    .properties(JsonNull.of())
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
    }

    @Test
    fun getBody() {
        val params =
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
                .system(BetaMessageCountTokensParams.System.ofString("string"))
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
                                        .properties(JsonNull.of())
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
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    BetaMessageParam.builder()
                        .content(BetaMessageParam.Content.ofString("string"))
                        .role(BetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(body.system()).isEqualTo(BetaMessageCountTokensParams.System.ofString("string"))
        assertThat(body.toolChoice())
            .isEqualTo(
                BetaToolChoice.ofBetaToolChoiceAuto(
                    BetaToolChoiceAuto.builder()
                        .type(BetaToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
            )
        assertThat(body.tools())
            .isEqualTo(
                listOf(
                    BetaMessageCountTokensParams.Tool.ofBetaTool(
                        BetaTool.builder()
                            .inputSchema(
                                BetaTool.InputSchema.builder()
                                    .type(BetaTool.InputSchema.Type.OBJECT)
                                    .properties(JsonNull.of())
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
    }

    @Test
    fun getBodyWithoutOptionalFields() {
        val params =
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
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    BetaMessageParam.builder()
                        .content(BetaMessageParam.Content.ofString("string"))
                        .role(BetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
    }
}
