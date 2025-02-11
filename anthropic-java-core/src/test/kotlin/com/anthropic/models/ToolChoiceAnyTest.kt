// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolChoiceAnyTest {

    @Test
    fun createToolChoiceAny() {
        val toolChoiceAny = ToolChoiceAny.builder().disableParallelToolUse(true).build()
        assertThat(toolChoiceAny).isNotNull
        assertThat(toolChoiceAny.disableParallelToolUse()).contains(true)
    }
}
