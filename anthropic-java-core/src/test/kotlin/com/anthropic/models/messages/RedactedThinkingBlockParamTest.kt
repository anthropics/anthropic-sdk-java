// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RedactedThinkingBlockParamTest {

    @Test
    fun create() {
        val redactedThinkingBlockParam = RedactedThinkingBlockParam.builder().data("data").build()

        assertThat(redactedThinkingBlockParam.data()).isEqualTo("data")
    }
}
