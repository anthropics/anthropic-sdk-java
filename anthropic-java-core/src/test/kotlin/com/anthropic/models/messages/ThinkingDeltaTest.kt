// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingDeltaTest {

    @Test
    fun create() {
        val thinkingDelta = ThinkingDelta.builder().thinking("thinking").build()

        assertThat(thinkingDelta.thinking()).isEqualTo("thinking")
    }
}
