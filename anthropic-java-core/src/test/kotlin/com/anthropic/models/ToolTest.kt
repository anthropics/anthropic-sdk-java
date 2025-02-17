// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolTest {

    @Test
    fun createTool() {
        val tool =
            Tool.builder()
                .inputSchema(
                    Tool.InputSchema.builder()
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
                .cacheControl(CacheControlEphemeral.builder().build())
                .description("Get the current weather in a given location")
                .build()
        assertThat(tool).isNotNull
        assertThat(tool.inputSchema())
            .isEqualTo(
                Tool.InputSchema.builder()
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
        assertThat(tool.name()).isEqualTo("name")
        assertThat(tool.cacheControl()).contains(CacheControlEphemeral.builder().build())
        assertThat(tool.description()).contains("Get the current weather in a given location")
    }
}
