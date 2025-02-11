// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolChoiceToolTest {

    @Test
    fun createToolChoiceTool() {
        val toolChoiceTool =
            ToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()
        assertThat(toolChoiceTool).isNotNull
        assertThat(toolChoiceTool.name()).isEqualTo("name")
        assertThat(toolChoiceTool.disableParallelToolUse()).contains(true)
    }
}
