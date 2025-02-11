// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolChoiceAnyTest {

    @Test
    fun createBetaToolChoiceAny() {
        val betaToolChoiceAny = BetaToolChoiceAny.builder().disableParallelToolUse(true).build()
        assertThat(betaToolChoiceAny).isNotNull
        assertThat(betaToolChoiceAny.disableParallelToolUse()).contains(true)
    }
}
