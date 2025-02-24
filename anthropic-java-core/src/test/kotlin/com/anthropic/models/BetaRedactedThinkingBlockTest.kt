// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRedactedThinkingBlockTest {

    @Test
    fun createBetaRedactedThinkingBlock() {
        val betaRedactedThinkingBlock = BetaRedactedThinkingBlock.builder().data("data").build()
        assertThat(betaRedactedThinkingBlock).isNotNull
        assertThat(betaRedactedThinkingBlock.data()).isEqualTo("data")
    }
}
