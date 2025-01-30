// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageCountTokensParamsTest {

    @Test
    fun createBetaMessageCountTokensParams() {
        BetaMessageCountTokensParams.builder()
            .addUserMessage("string")
            .model(Model.CLAUDE_3_5_HAIKU_LATEST)
            .systemOfBetaTextBlockParams(
                listOf(
                    BetaTextBlockParam.builder()
                        .text("Today's date is 2024-06-01.")
                        .type(BetaTextBlockParam.Type.TEXT)
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                .build()
                        )
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .type(BetaCitationCharLocationParam.Type.CHAR_LOCATION)
                                .build()
                        )
                        .build()
                )
            )
            .toolChoice(
                BetaToolChoiceAuto.builder()
                    .type(BetaToolChoiceAuto.Type.AUTO)
                    .disableParallelToolUse(true)
                    .build()
            )
            .addTool(
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
                    .name("name")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                            .build()
                    )
                    .description("Get the current weather in a given location")
                    .type(BetaTool.Type.CUSTOM)
                    .build()
            )
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun body() {
        val params =
            BetaMessageCountTokensParams.builder()
                .addUserMessage("string")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .systemOfBetaTextBlockParams(
                    listOf(
                        BetaTextBlockParam.builder()
                            .text("Today's date is 2024-06-01.")
                            .type(BetaTextBlockParam.Type.TEXT)
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                    .build()
                            )
                            .addCitation(
                                BetaCitationCharLocationParam.builder()
                                    .citedText("cited_text")
                                    .documentIndex(0L)
                                    .documentTitle("x")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .type(BetaCitationCharLocationParam.Type.CHAR_LOCATION)
                                    .build()
                            )
                            .build()
                    )
                )
                .toolChoice(
                    BetaToolChoiceAuto.builder()
                        .type(BetaToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
                .addTool(
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
                        .name("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                                .build()
                        )
                        .description("Get the current weather in a given location")
                        .type(BetaTool.Type.CUSTOM)
                        .build()
                )
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()
        val body = params._body()
        assertThat(body).isNotNull
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    BetaMessageParam.builder()
                        .content("string")
                        .role(BetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(body.system())
            .contains(
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
                            .addCitation(
                                BetaCitationCharLocationParam.builder()
                                    .citedText("cited_text")
                                    .documentIndex(0L)
                                    .documentTitle("x")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .type(BetaCitationCharLocationParam.Type.CHAR_LOCATION)
                                    .build()
                            )
                            .build()
                    )
                )
            )
        assertThat(body.toolChoice())
            .contains(
                BetaToolChoice.ofAuto(
                    BetaToolChoiceAuto.builder()
                        .type(BetaToolChoiceAuto.Type.AUTO)
                        .disableParallelToolUse(true)
                        .build()
                )
            )
        assertThat(body.tools())
            .contains(
                listOf(
                    BetaMessageCountTokensParams.Tool.ofBeta(
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
                            .name("name")
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
    fun bodyWithoutOptionalFields() {
        val params =
            BetaMessageCountTokensParams.builder()
                .addUserMessage("string")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .build()
        val body = params._body()
        assertThat(body).isNotNull
        assertThat(body.messages())
            .isEqualTo(
                listOf(
                    BetaMessageParam.builder()
                        .content("string")
                        .role(BetaMessageParam.Role.USER)
                        .build()
                )
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
    }
}
