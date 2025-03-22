// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingConfigParamTest {

    @Test
    fun ofEnabled() {
        val enabled = ThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        val thinkingConfigParam = ThinkingConfigParam.ofEnabled(enabled)

        assertThat(thinkingConfigParam.enabled()).contains(enabled)
        assertThat(thinkingConfigParam.disabled()).isEmpty
    }

    @Test
    fun ofDisabled() {
        val disabled = ThinkingConfigDisabled.builder().build()

        val thinkingConfigParam = ThinkingConfigParam.ofDisabled(disabled)

        assertThat(thinkingConfigParam.enabled()).isEmpty
        assertThat(thinkingConfigParam.disabled()).contains(disabled)
    }
}
