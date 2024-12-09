// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaTextBlockTest {

    @Test
    fun createBetaTextBlock() {
        val betaTextBlock =
            BetaTextBlock.builder().text("text").type(BetaTextBlock.Type.TEXT).build()
        assertThat(betaTextBlock).isNotNull
        assertThat(betaTextBlock.text()).isEqualTo("text")
        assertThat(betaTextBlock.type()).isEqualTo(BetaTextBlock.Type.TEXT)
    }
}
