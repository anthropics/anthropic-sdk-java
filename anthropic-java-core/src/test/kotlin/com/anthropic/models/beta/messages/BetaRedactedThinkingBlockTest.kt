// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRedactedThinkingBlockTest {

    @Test
    fun create() {
        val betaRedactedThinkingBlock = BetaRedactedThinkingBlock.builder().data("data").build()

        assertThat(betaRedactedThinkingBlock.data()).isEqualTo("data")
    }
}
