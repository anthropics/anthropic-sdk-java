// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaThinkingBlockTest {

    @Test
    fun createBetaThinkingBlock() {
        val betaThinkingBlock =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()
        assertThat(betaThinkingBlock).isNotNull
        assertThat(betaThinkingBlock.signature()).isEqualTo("signature")
        assertThat(betaThinkingBlock.thinking()).isEqualTo("thinking")
    }
}
