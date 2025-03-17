// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInputJsonDeltaTest {

    @Test
    fun createBetaInputJsonDelta() {
        val betaInputJsonDelta = BetaInputJsonDelta.builder().partialJson("partial_json").build()
        assertThat(betaInputJsonDelta).isNotNull
        assertThat(betaInputJsonDelta.partialJson()).isEqualTo("partial_json")
    }
}
