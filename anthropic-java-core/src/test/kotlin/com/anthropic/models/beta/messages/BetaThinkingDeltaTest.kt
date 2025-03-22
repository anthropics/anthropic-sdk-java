// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingDeltaTest {

    @Test
    fun create() {
        val betaThinkingDelta = BetaThinkingDelta.builder().thinking("thinking").build()

        assertThat(betaThinkingDelta.thinking()).isEqualTo("thinking")
    }
}
