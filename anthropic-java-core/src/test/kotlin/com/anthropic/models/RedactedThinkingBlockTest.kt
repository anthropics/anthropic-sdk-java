// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RedactedThinkingBlockTest {

    @Test
    fun createRedactedThinkingBlock() {
        val redactedThinkingBlock = RedactedThinkingBlock.builder().data("data").build()
        assertThat(redactedThinkingBlock).isNotNull
        assertThat(redactedThinkingBlock.data()).isEqualTo("data")
    }
}
