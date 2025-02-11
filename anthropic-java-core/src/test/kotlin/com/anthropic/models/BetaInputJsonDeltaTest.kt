// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaInputJsonDeltaTest {

    @Test
    fun createBetaInputJsonDelta() {
        val betaInputJsonDelta = BetaInputJsonDelta.builder().partialJson("partial_json").build()
        assertThat(betaInputJsonDelta).isNotNull
        assertThat(betaInputJsonDelta.partialJson()).isEqualTo("partial_json")
    }
}
