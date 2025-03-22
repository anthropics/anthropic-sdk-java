// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceToolTest {

    @Test
    fun create() {
        val betaToolChoiceTool =
            BetaToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        assertThat(betaToolChoiceTool.name()).isEqualTo("name")
        assertThat(betaToolChoiceTool.disableParallelToolUse()).contains(true)
    }
}
