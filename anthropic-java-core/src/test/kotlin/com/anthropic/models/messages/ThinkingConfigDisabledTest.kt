// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingConfigDisabledTest {

    @Test
    fun createThinkingConfigDisabled() {
        val thinkingConfigDisabled = ThinkingConfigDisabled.builder().build()
        assertThat(thinkingConfigDisabled).isNotNull
    }
}
