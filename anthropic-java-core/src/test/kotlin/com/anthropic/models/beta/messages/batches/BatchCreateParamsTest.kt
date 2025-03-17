// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaCitationCharLocationParam
import com.anthropic.models.beta.messages.BetaMetadata
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolChoiceAuto
import com.anthropic.models.messages.Model
import kotlin.test.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BatchCreateParamsTest {

    @Test
    fun create() {
        BatchCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .addRequest(
                BatchCreateParams.Request.builder()
                    .customId("my-custom-id-1")
                    .params(
                        BatchCreateParams.Request.Params.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_3_7_SONNET_LATEST)
                            .metadata(
                                BetaMetadata.builder()
                                    .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                    .build()
                            )
                            .addStopSequence("string")
                            .stream(true)
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
                            .toolChoice(
                                BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()
                            )
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
                    .build()
            )
            .build()
    }

    @Test
    fun body() {
        val params =
            BatchCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .metadata(
                                    BetaMetadata.builder()
                                        .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                        .build()
                                )
                                .addStopSequence("string")
                                .stream(true)
                                .systemOfBetaTextBlockParams(
                                    listOf(
                                        BetaTextBlockParam.builder()
                                            .text("Today's date is 2024-06-01.")
                                            .cacheControl(
                                                BetaCacheControlEphemeral.builder().build()
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
                                .toolChoice(
                                    BetaToolChoiceAuto.builder()
                                        .disableParallelToolUse(true)
                                        .build()
                                )
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
                        .build()
                )
                .build()

        val body = params._body()

        assertNotNull(body)
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .metadata(
                                    BetaMetadata.builder()
                                        .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                        .build()
                                )
                                .addStopSequence("string")
                                .stream(true)
                                .systemOfBetaTextBlockParams(
                                    listOf(
                                        BetaTextBlockParam.builder()
                                            .text("Today's date is 2024-06-01.")
                                            .cacheControl(
                                                BetaCacheControlEphemeral.builder().build()
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
                                .toolChoice(
                                    BetaToolChoiceAuto.builder()
                                        .disableParallelToolUse(true)
                                        .build()
                                )
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
                        .build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            BatchCreateParams.builder()
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .build()
                        )
                        .build()
                )
                .build()

        val body = params._body()

        assertNotNull(body)
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                .build()
                        )
                        .build()
                )
            )
    }
}
