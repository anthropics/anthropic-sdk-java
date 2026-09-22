package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChangeToolDefinitionParamTest {

    @Test
    fun create() {
        val betaToolChangeToolDefinitionParam =
            BetaToolChangeToolDefinitionParam.builder()
                .definition(
                    BetaTool.builder()
                        .inputSchema(
                            BetaTool.InputSchema.builder()
                                .properties(
                                    BetaTool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .addAllowedCaller(BetaTool.AllowedCaller.DIRECT)
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .deferLoading(true)
                        .description("Get the current weather in a given location")
                        .eagerInputStreaming(true)
                        .addInputExample(
                            BetaTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaTool.Type.CUSTOM)
                        .build()
                )
                .build()

        assertThat(betaToolChangeToolDefinitionParam.definition())
            .isEqualTo(
                BetaToolUnion.ofBetaTool(
                    BetaTool.builder()
                        .inputSchema(
                            BetaTool.InputSchema.builder()
                                .properties(
                                    BetaTool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .addAllowedCaller(BetaTool.AllowedCaller.DIRECT)
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .deferLoading(true)
                        .description("Get the current weather in a given location")
                        .eagerInputStreaming(true)
                        .addInputExample(
                            BetaTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaTool.Type.CUSTOM)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChangeToolDefinitionParam =
            BetaToolChangeToolDefinitionParam.builder()
                .definition(
                    BetaTool.builder()
                        .inputSchema(
                            BetaTool.InputSchema.builder()
                                .properties(
                                    BetaTool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                                        .build()
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .addAllowedCaller(BetaTool.AllowedCaller.DIRECT)
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .deferLoading(true)
                        .description("Get the current weather in a given location")
                        .eagerInputStreaming(true)
                        .addInputExample(
                            BetaTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaTool.Type.CUSTOM)
                        .build()
                )
                .build()

        val roundtrippedBetaToolChangeToolDefinitionParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChangeToolDefinitionParam),
                jacksonTypeRef<BetaToolChangeToolDefinitionParam>(),
            )

        assertThat(roundtrippedBetaToolChangeToolDefinitionParam)
            .isEqualTo(betaToolChangeToolDefinitionParam)
    }
}
