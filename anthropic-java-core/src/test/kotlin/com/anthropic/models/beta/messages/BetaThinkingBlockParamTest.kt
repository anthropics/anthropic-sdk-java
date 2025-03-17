// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingBlockParamTest {

    @Test
    fun createBetaThinkingBlockParam() {
        val betaThinkingBlockParam =
            BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()
        assertThat(betaThinkingBlockParam).isNotNull
        assertThat(betaThinkingBlockParam.signature()).isEqualTo("signature")
        assertThat(betaThinkingBlockParam.thinking()).isEqualTo("thinking")
    }
}
