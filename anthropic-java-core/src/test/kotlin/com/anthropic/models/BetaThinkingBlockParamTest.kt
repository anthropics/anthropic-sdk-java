// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaThinkingBlockParamTest {

    @Test
    fun createBetaThinkingBlockParam() {
        val betaThinkingBlockParam =
            BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()
        assertThat(betaThinkingBlockParam).isNotNull
        assertThat(betaThinkingBlockParam.signature()).isEqualTo("signature")
        assertThat(betaThinkingBlockParam.thinking()).isEqualTo("thinking")
    }
}
