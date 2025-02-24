// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaThinkingDeltaTest {

    @Test
    fun createBetaThinkingDelta() {
        val betaThinkingDelta = BetaThinkingDelta.builder().thinking("thinking").build()
        assertThat(betaThinkingDelta).isNotNull
        assertThat(betaThinkingDelta.thinking()).isEqualTo("thinking")
    }
}
