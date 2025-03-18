// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextDeltaTest {

    @Test
    fun create() {
        val betaTextDelta = BetaTextDelta.builder().text("text").build()

        assertThat(betaTextDelta.text()).isEqualTo("text")
    }
}
