// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolChoiceToolTest {

    @Test
    fun createBetaToolChoiceTool() {
        val betaToolChoiceTool =
            BetaToolChoiceTool.builder()
                .name("name")
                .type(BetaToolChoiceTool.Type.TOOL)
                .disableParallelToolUse(true)
                .build()
        assertThat(betaToolChoiceTool).isNotNull
        assertThat(betaToolChoiceTool.name()).isEqualTo("name")
        assertThat(betaToolChoiceTool.type()).isEqualTo(BetaToolChoiceTool.Type.TOOL)
        assertThat(betaToolChoiceTool.disableParallelToolUse()).contains(true)
    }
}
