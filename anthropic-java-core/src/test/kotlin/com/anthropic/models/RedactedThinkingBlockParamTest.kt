// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RedactedThinkingBlockParamTest {

    @Test
    fun createRedactedThinkingBlockParam() {
        val redactedThinkingBlockParam = RedactedThinkingBlockParam.builder().data("data").build()
        assertThat(redactedThinkingBlockParam).isNotNull
        assertThat(redactedThinkingBlockParam.data()).isEqualTo("data")
    }
}
