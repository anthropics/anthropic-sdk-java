// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolChoiceAutoTest {

    @Test
    fun createBetaToolChoiceAuto() {
        val betaToolChoiceAuto =
            BetaToolChoiceAuto.builder()
                .type(BetaToolChoiceAuto.Type.AUTO)
                .disableParallelToolUse(true)
                .build()
        assertThat(betaToolChoiceAuto).isNotNull
        assertThat(betaToolChoiceAuto.type()).isEqualTo(BetaToolChoiceAuto.Type.AUTO)
        assertThat(betaToolChoiceAuto.disableParallelToolUse()).contains(true)
    }
}
