// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceAnyTest {

    @Test
    fun create() {
        val betaToolChoiceAny = BetaToolChoiceAny.builder().disableParallelToolUse(true).build()

        assertThat(betaToolChoiceAny.disableParallelToolUse()).contains(true)
    }
}
