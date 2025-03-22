// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingBlockTest {

    @Test
    fun create() {
        val betaThinkingBlock =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()

        assertThat(betaThinkingBlock.signature()).isEqualTo("signature")
        assertThat(betaThinkingBlock.thinking()).isEqualTo("thinking")
    }
}
