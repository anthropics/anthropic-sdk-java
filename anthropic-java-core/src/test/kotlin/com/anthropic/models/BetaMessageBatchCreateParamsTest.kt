// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchCreateParamsTest {

    @Test
    fun createBetaMessageBatchCreateParams() {
        BetaMessageBatchCreateParams.builder()
            .requests(
                listOf(
                    BetaMessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BetaMessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .messages(
                                    listOf(
                                        BetaMessageParam.builder()
                                            .content(
                                                BetaMessageParam.Content.ofString("Hello, world")
                                            )
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
                                .stream(true)
                                .system(
                                    BetaMessageBatchCreateParams.Request.Params.System
                                        .ofBetaTextBlockParams(
                                            listOf(
                                                BetaTextBlockParam.builder()
                                                    .text("Today's date is 2024-06-01.")
                                                    .type(BetaTextBlockParam.Type.TEXT)
                                                    .cacheControl(
                                                        BetaCacheControlEphemeral.builder()
                                                            .type(
                                                                BetaCacheControlEphemeral.Type
                                                                    .EPHEMERAL
                                                            )
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
                                                        .type(
                                                            BetaCacheControlEphemeral.Type.EPHEMERAL
                                                        )
                                                        .build()
                                                )
                                                .description(
                                                    "Get the current weather in a given location"
                                                )
                                                .type(BetaTool.Type.CUSTOM)
                                                .build()
                                        )
                                    )
                                )
                                .topK(5L)
                                .topP(0.7)
                                .build()
                        )
                        .build()
                )
            )
            .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
            .build()
    }

    @Test
    fun getBody() {
        val params =
            BetaMessageBatchCreateParams.builder()
                .requests(
                    listOf(
                        BetaMessageBatchCreateParams.Request.builder()
                            .customId("my-custom-id-1")
                            .params(
                                BetaMessageBatchCreateParams.Request.Params.builder()
                                    .maxTokens(1024L)
                                    .messages(
                                        listOf(
                                            BetaMessageParam.builder()
                                                .content(
                                                    BetaMessageParam.Content.ofString(
                                                        "Hello, world"
                                                    )
                                                )
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
                                    .stream(true)
                                    .system(
                                        BetaMessageBatchCreateParams.Request.Params.System
                                            .ofBetaTextBlockParams(
                                                listOf(
                                                    BetaTextBlockParam.builder()
                                                        .text("Today's date is 2024-06-01.")
                                                        .type(BetaTextBlockParam.Type.TEXT)
                                                        .cacheControl(
                                                            BetaCacheControlEphemeral.builder()
                                                                .type(
                                                                    BetaCacheControlEphemeral.Type
                                                                        .EPHEMERAL
                                                                )
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
                                                            .type(
                                                                BetaCacheControlEphemeral.Type
                                                                    .EPHEMERAL
                                                            )
                                                            .build()
                                                    )
                                                    .description(
                                                        "Get the current weather in a given location"
                                                    )
                                                    .type(BetaTool.Type.CUSTOM)
                                                    .build()
                                            )
                                        )
                                    )
                                    .topK(5L)
                                    .topP(0.7)
                                    .build()
                            )
                            .build()
                    )
                )
                .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    BetaMessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BetaMessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .messages(
                                    listOf(
                                        BetaMessageParam.builder()
                                            .content(
                                                BetaMessageParam.Content.ofString("Hello, world")
                                            )
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
                                .stream(true)
                                .system(
                                    BetaMessageBatchCreateParams.Request.Params.System
                                        .ofBetaTextBlockParams(
                                            listOf(
                                                BetaTextBlockParam.builder()
                                                    .text("Today's date is 2024-06-01.")
                                                    .type(BetaTextBlockParam.Type.TEXT)
                                                    .cacheControl(
                                                        BetaCacheControlEphemeral.builder()
                                                            .type(
                                                                BetaCacheControlEphemeral.Type
                                                                    .EPHEMERAL
                                                            )
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
                                                        .type(
                                                            BetaCacheControlEphemeral.Type.EPHEMERAL
                                                        )
                                                        .build()
                                                )
                                                .description(
                                                    "Get the current weather in a given location"
                                                )
                                                .type(BetaTool.Type.CUSTOM)
                                                .build()
                                        )
                                    )
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
    fun getBodyWithoutOptionalFields() {
        val params =
            BetaMessageBatchCreateParams.builder()
                .requests(
                    listOf(
                        BetaMessageBatchCreateParams.Request.builder()
                            .customId("my-custom-id-1")
                            .params(
                                BetaMessageBatchCreateParams.Request.Params.builder()
                                    .maxTokens(1024L)
                                    .messages(
                                        listOf(
                                            BetaMessageParam.builder()
                                                .content(
                                                    BetaMessageParam.Content.ofString(
                                                        "Hello, world"
                                                    )
                                                )
                                                .role(BetaMessageParam.Role.USER)
                                                .build()
                                        )
                                    )
                                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                    .build()
                            )
                            .build()
                    )
                )
                .build()
        val body = params.getBody()
        assertThat(body).isNotNull
        assertThat(body.requests())
            .isEqualTo(
                listOf(
                    BetaMessageBatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BetaMessageBatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .messages(
                                    listOf(
                                        BetaMessageParam.builder()
                                            .content(
                                                BetaMessageParam.Content.ofString("Hello, world")
                                            )
                                            .role(BetaMessageParam.Role.USER)
                                            .build()
                                    )
                                )
                                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                .build()
                        )
                        .build()
                )
            )
    }
}
