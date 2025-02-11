// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaTextDeltaTest {

    @Test
    fun createBetaTextDelta() {
        val betaTextDelta = BetaTextDelta.builder().text("text").build()
        assertThat(betaTextDelta).isNotNull
        assertThat(betaTextDelta.text()).isEqualTo("text")
    }
}
