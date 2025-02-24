// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaThinkingConfigEnabledTest {

    @Test
    fun createBetaThinkingConfigEnabled() {
        val betaThinkingConfigEnabled =
            BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()
        assertThat(betaThinkingConfigEnabled).isNotNull
        assertThat(betaThinkingConfigEnabled.budgetTokens()).isEqualTo(1024L)
    }
}
