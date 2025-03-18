// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RedactedThinkingBlockTest {

    @Test
    fun create() {
        val redactedThinkingBlock = RedactedThinkingBlock.builder().data("data").build()

        assertThat(redactedThinkingBlock.data()).isEqualTo("data")
    }
}
