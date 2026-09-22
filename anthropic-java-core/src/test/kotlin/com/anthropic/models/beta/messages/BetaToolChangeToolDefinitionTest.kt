package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChangeToolDefinitionTest {

    @Test
    fun create() {
        val betaToolChangeToolDefinition =
            BetaToolChangeToolDefinition.builder()
                .definition(
                    BetaResponseTool.builder()
                        .inputSchema(
                            BetaResponseToolInputSchema.builder()
                                .properties(
                                    BetaResponseToolInputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                        .deferLoading(true)
                        .description("Get the current weather in a given location")
                        .eagerInputStreaming(true)
                        .addInputExample(
                            BetaResponseTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaResponseTool.Type.CUSTOM)
                        .build()
                )
                .build()

        assertThat(betaToolChangeToolDefinition.definition())
            .isEqualTo(
                BetaResponseToolUnion.ofBetaResponseTool(
                    BetaResponseTool.builder()
                        .inputSchema(
                            BetaResponseToolInputSchema.builder()
                                .properties(
                                    BetaResponseToolInputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                        .deferLoading(true)
                        .description("Get the current weather in a given location")
                        .eagerInputStreaming(true)
                        .addInputExample(
                            BetaResponseTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaResponseTool.Type.CUSTOM)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChangeToolDefinition =
            BetaToolChangeToolDefinition.builder()
                .definition(
                    BetaResponseTool.builder()
                        .inputSchema(
                            BetaResponseToolInputSchema.builder()
                                .properties(
                                    BetaResponseToolInputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                        .deferLoading(true)
                        .description("Get the current weather in a given location")
                        .eagerInputStreaming(true)
                        .addInputExample(
                            BetaResponseTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaResponseTool.Type.CUSTOM)
                        .build()
                )
                .build()

        val roundtrippedBetaToolChangeToolDefinition =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChangeToolDefinition),
                jacksonTypeRef<BetaToolChangeToolDefinition>(),
            )

        assertThat(roundtrippedBetaToolChangeToolDefinition).isEqualTo(betaToolChangeToolDefinition)
    }
}
