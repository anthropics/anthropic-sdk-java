// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolTest {

    @Test
    fun createTool() {
        val tool =
            Tool.builder()
                .inputSchema(
                    Tool.InputSchema.builder()
                        .type(Tool.InputSchema.Type.OBJECT)
                        .properties(JsonNull.of())
                        .build()
                )
                .name("x")
                .description("Get the current weather in a given location")
                .build()
        assertThat(tool).isNotNull
        assertThat(tool.inputSchema())
            .isEqualTo(
                Tool.InputSchema.builder()
                    .type(Tool.InputSchema.Type.OBJECT)
                    .properties(JsonNull.of())
                    .build()
            )
        assertThat(tool.name()).isEqualTo("x")
        assertThat(tool.description()).contains("Get the current weather in a given location")
    }
}
