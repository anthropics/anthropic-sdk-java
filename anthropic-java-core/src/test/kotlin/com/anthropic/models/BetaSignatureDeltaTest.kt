// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaSignatureDeltaTest {

    @Test
    fun createBetaSignatureDelta() {
        val betaSignatureDelta = BetaSignatureDelta.builder().signature("signature").build()
        assertThat(betaSignatureDelta).isNotNull
        assertThat(betaSignatureDelta.signature()).isEqualTo("signature")
    }
}
