// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ThinkingBlockTest {

    @Test
    fun createThinkingBlock() {
        val thinkingBlock =
            ThinkingBlock.builder().signature("signature").thinking("thinking").build()
        assertThat(thinkingBlock).isNotNull
        assertThat(thinkingBlock.signature()).isEqualTo("signature")
        assertThat(thinkingBlock.thinking()).isEqualTo("thinking")
    }
}
