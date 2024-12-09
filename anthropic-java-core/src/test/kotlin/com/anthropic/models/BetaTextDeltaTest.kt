// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaTextDeltaTest {

    @Test
    fun createBetaTextDelta() {
        val betaTextDelta =
            BetaTextDelta.builder().text("text").type(BetaTextDelta.Type.TEXT_DELTA).build()
        assertThat(betaTextDelta).isNotNull
        assertThat(betaTextDelta.text()).isEqualTo("text")
        assertThat(betaTextDelta.type()).isEqualTo(BetaTextDelta.Type.TEXT_DELTA)
    }
}
