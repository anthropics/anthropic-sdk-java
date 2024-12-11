// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaToolTest {

    @Test
    fun createPromptCachingBetaTool() {
        val promptCachingBetaTool =
            PromptCachingBetaTool.builder()
                .inputSchema(
                    PromptCachingBetaTool.InputSchema.builder()
                        .type(PromptCachingBetaTool.InputSchema.Type.OBJECT)
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
                    PromptCachingBetaCacheControlEphemeral.builder()
                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .description("Get the current weather in a given location")
                .build()
        assertThat(promptCachingBetaTool).isNotNull
        assertThat(promptCachingBetaTool.inputSchema())
            .isEqualTo(
                PromptCachingBetaTool.InputSchema.builder()
                    .type(PromptCachingBetaTool.InputSchema.Type.OBJECT)
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
        assertThat(promptCachingBetaTool.name()).isEqualTo("x")
        assertThat(promptCachingBetaTool.cacheControl())
            .contains(
                PromptCachingBetaCacheControlEphemeral.builder()
                    .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
        assertThat(promptCachingBetaTool.description())
            .contains("Get the current weather in a given location")
    }
}
