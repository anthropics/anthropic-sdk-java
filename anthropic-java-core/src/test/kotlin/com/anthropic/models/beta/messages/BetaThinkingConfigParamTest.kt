// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingConfigParamTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofEnabled(enabled)

        assertThat(betaThinkingConfigParam.enabled()).contains(enabled)
        assertThat(betaThinkingConfigParam.disabled()).isEmpty
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaThinkingConfigDisabled.builder().build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofDisabled(disabled)

        assertThat(betaThinkingConfigParam.enabled()).isEmpty
        assertThat(betaThinkingConfigParam.disabled()).contains(disabled)
    }
}
