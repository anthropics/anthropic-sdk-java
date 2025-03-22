// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingConfigEnabledTest {

    @Test
    fun create() {
        val betaThinkingConfigEnabled =
            BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        assertThat(betaThinkingConfigEnabled.budgetTokens()).isEqualTo(1024L)
    }
}
