// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolTest {

    @Test
    fun createBetaTool() {
        val betaTool =
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
        assertThat(betaTool).isNotNull
        assertThat(betaTool.inputSchema())
            .isEqualTo(
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
        assertThat(betaTool.name()).isEqualTo("name")
        assertThat(betaTool.cacheControl()).contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaTool.description()).contains("Get the current weather in a given location")
        assertThat(betaTool.type()).contains(BetaTool.Type.CUSTOM)
    }
}
