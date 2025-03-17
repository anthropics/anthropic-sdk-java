// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceAutoTest {

    @Test
    fun createBetaToolChoiceAuto() {
        val betaToolChoiceAuto = BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()
        assertThat(betaToolChoiceAuto).isNotNull
        assertThat(betaToolChoiceAuto.disableParallelToolUse()).contains(true)
    }
}
