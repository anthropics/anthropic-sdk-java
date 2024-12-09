// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolUseBlockTest {

    @Test
    fun createToolUseBlock() {
        val toolUseBlock =
            ToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .type(ToolUseBlock.Type.TOOL_USE)
                .build()
        assertThat(toolUseBlock).isNotNull
        assertThat(toolUseBlock.id()).isEqualTo("id")
        assertThat(toolUseBlock._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(toolUseBlock.name()).isEqualTo("x")
        assertThat(toolUseBlock.type()).isEqualTo(ToolUseBlock.Type.TOOL_USE)
    }
}
