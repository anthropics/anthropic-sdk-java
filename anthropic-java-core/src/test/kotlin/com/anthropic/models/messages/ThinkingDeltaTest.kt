// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingDeltaTest {

    @Test
    fun createThinkingDelta() {
        val thinkingDelta = ThinkingDelta.builder().thinking("thinking").build()
        assertThat(thinkingDelta).isNotNull
        assertThat(thinkingDelta.thinking()).isEqualTo("thinking")
    }
}
