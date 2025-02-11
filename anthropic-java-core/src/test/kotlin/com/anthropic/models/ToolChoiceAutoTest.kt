// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolChoiceAutoTest {

    @Test
    fun createToolChoiceAuto() {
        val toolChoiceAuto = ToolChoiceAuto.builder().disableParallelToolUse(true).build()
        assertThat(toolChoiceAuto).isNotNull
        assertThat(toolChoiceAuto.disableParallelToolUse()).contains(true)
    }
}
