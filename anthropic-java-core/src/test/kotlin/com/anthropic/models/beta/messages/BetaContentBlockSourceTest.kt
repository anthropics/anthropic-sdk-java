// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContentBlockSourceTest {

    @Test
    fun create() {
        val betaContentBlockSource = BetaContentBlockSource.builder().content("string").build()

        assertThat(betaContentBlockSource.content())
            .isEqualTo(BetaContentBlockSource.Content.ofString("string"))
    }
}
