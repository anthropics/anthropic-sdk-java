// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
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
        assertThat(body.system())
            .isEqualTo(
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
