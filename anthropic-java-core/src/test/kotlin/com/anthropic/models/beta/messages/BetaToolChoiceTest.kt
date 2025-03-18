// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceTest {

    @Test
    fun ofAuto() {
        val auto = BetaToolChoiceAuto.builder().build()

        val betaToolChoice = BetaToolChoice.ofAuto(auto)

        assertThat(betaToolChoice.auto()).contains(auto)
        assertThat(betaToolChoice.any()).isEmpty
        assertThat(betaToolChoice.tool()).isEmpty
        assertThat(betaToolChoice.none()).isEmpty
    }

    @Test
    fun ofAny() {
        val any = BetaToolChoiceAny.builder().build()

        val betaToolChoice = BetaToolChoice.ofAny(any)

        assertThat(betaToolChoice.auto()).isEmpty
        assertThat(betaToolChoice.any()).contains(any)
        assertThat(betaToolChoice.tool()).isEmpty
        assertThat(betaToolChoice.none()).isEmpty
    }

    @Test
    fun ofTool() {
        val tool = BetaToolChoiceTool.builder().name("name").build()

        val betaToolChoice = BetaToolChoice.ofTool(tool)

        assertThat(betaToolChoice.auto()).isEmpty
        assertThat(betaToolChoice.any()).isEmpty
        assertThat(betaToolChoice.tool()).contains(tool)
        assertThat(betaToolChoice.none()).isEmpty
    }

    @Test
    fun ofNone() {
        val none = BetaToolChoiceNone.builder().build()

        val betaToolChoice = BetaToolChoice.ofNone(none)

        assertThat(betaToolChoice.auto()).isEmpty
        assertThat(betaToolChoice.any()).isEmpty
        assertThat(betaToolChoice.tool()).isEmpty
        assertThat(betaToolChoice.none()).contains(none)
    }
}
