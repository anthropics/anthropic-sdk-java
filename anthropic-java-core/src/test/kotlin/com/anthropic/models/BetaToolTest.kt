// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolTest {

    @Test
    fun createBetaTool() {
        val betaTool =
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
        assertThat(betaTool).isNotNull
        assertThat(betaTool.inputSchema())
            .isEqualTo(
                BetaTool.InputSchema.builder()
                    .type(BetaTool.InputSchema.Type.OBJECT)
                    .properties(JsonNull.of())
                    .build()
            )
        assertThat(betaTool.name()).isEqualTo("x")
        assertThat(betaTool.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
        assertThat(betaTool.description()).contains("Get the current weather in a given location")
        assertThat(betaTool.type()).contains(BetaTool.Type.CUSTOM)
    }
}
