// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolChoiceAnyTest {

    @Test
    fun createBetaToolChoiceAny() {
        val betaToolChoiceAny =
            BetaToolChoiceAny.builder()
                .type(BetaToolChoiceAny.Type.ANY)
                .disableParallelToolUse(true)
                .build()
        assertThat(betaToolChoiceAny).isNotNull
        assertThat(betaToolChoiceAny.type()).isEqualTo(BetaToolChoiceAny.Type.ANY)
        assertThat(betaToolChoiceAny.disableParallelToolUse()).contains(true)
    }
}
