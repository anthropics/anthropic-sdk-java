// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolChoiceNoneTest {

    @Test
    fun createToolChoiceNone() {
        val toolChoiceNone = ToolChoiceNone.builder().build()
        assertThat(toolChoiceNone).isNotNull
    }
}
