// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolChoiceAutoTest {

    @Test
    fun create() {
        val toolChoiceAuto = ToolChoiceAuto.builder().disableParallelToolUse(true).build()

        assertThat(toolChoiceAuto.disableParallelToolUse()).contains(true)
    }
}
