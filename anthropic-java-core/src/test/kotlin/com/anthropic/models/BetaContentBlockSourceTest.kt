// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaContentBlockSourceTest {

    @Test
    fun createBetaContentBlockSource() {
        val betaContentBlockSource = BetaContentBlockSource.builder().content("string").build()
        assertThat(betaContentBlockSource).isNotNull
        assertThat(betaContentBlockSource.content())
            .isEqualTo(BetaContentBlockSource.Content.ofString("string"))
    }
}
