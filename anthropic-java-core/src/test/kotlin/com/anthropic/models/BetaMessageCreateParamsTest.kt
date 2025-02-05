// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageCreateParamsTest {

    @Test
    fun create() {
        BetaMessageCreateParams.builder()
            .maxTokens(1024L)
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_3_5_HAIKU_LATEST)
            .metadata(BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .addStopSequence("string")
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
            .temperature(1.0)
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
            .topK(5L)
            .topP(0.7)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun body() {
        val params =
            BetaMessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .metadata(
                    BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                )
                .addStopSequence("string")
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
                .temperature(1.0)
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
                .topK(5L)
                .topP(0.7)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(body.metadata())
            .contains(BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences()).contains(listOf("string"))
        assertThat(body.system())
            .contains(
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
        assertThat(body.temperature()).contains(1.0)
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
        assertThat(body.topK()).contains(5L)
        assertThat(body.topP()).contains(0.7)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            BetaMessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
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
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
    }
}
