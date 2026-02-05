// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTest {

    @Test
    fun create() {
        val betaTool =
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

        assertThat(betaTool.inputSchema())
            .isEqualTo(
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
        assertThat(betaTool.name()).isEqualTo("name")
        assertThat(betaTool.allowedCallers().getOrNull())
            .containsExactly(BetaTool.AllowedCaller.DIRECT)
        assertThat(betaTool.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaTool.deferLoading()).contains(true)
        assertThat(betaTool.description()).contains("Get the current weather in a given location")
        assertThat(betaTool.eagerInputStreaming()).contains(true)
        assertThat(betaTool.inputExamples().getOrNull())
            .containsExactly(
                BetaTool.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaTool.strict()).contains(true)
        assertThat(betaTool.type()).contains(BetaTool.Type.CUSTOM)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTool =
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

        val roundtrippedBetaTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTool),
                jacksonTypeRef<BetaTool>(),
            )

        assertThat(roundtrippedBetaTool).isEqualTo(betaTool)
    }
}
