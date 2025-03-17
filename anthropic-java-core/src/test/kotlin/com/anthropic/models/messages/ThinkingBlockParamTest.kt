// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingBlockParamTest {

    @Test
    fun createThinkingBlockParam() {
        val thinkingBlockParam =
            ThinkingBlockParam.builder().signature("signature").thinking("thinking").build()
        assertThat(thinkingBlockParam).isNotNull
        assertThat(thinkingBlockParam.signature()).isEqualTo("signature")
        assertThat(thinkingBlockParam.thinking()).isEqualTo("thinking")
    }
}
