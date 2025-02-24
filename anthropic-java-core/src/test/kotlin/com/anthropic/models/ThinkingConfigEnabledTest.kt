// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ThinkingConfigEnabledTest {

    @Test
    fun createThinkingConfigEnabled() {
        val thinkingConfigEnabled = ThinkingConfigEnabled.builder().budgetTokens(1024L).build()
        assertThat(thinkingConfigEnabled).isNotNull
        assertThat(thinkingConfigEnabled.budgetTokens()).isEqualTo(1024L)
    }
}
