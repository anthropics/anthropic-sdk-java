// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaThinkingConfigDisabledTest {

    @Test
    fun createBetaThinkingConfigDisabled() {
        val betaThinkingConfigDisabled = BetaThinkingConfigDisabled.builder().build()
        assertThat(betaThinkingConfigDisabled).isNotNull
    }
}
