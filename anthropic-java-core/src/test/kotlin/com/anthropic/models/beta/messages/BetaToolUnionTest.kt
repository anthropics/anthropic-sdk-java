// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolUnionTest {

    @Test
    fun ofBetaTool() {
        val betaTool =
            BetaTool.builder()
                .inputSchema(BetaTool.InputSchema.builder().build())
                .name("name")
                .build()

        val betaToolUnion = BetaToolUnion.ofBetaTool(betaTool)

        assertThat(betaToolUnion.betaTool()).contains(betaTool)
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofComputerUse20241022() {
        val computerUse20241022 =
            BetaToolComputerUse20241022.builder().displayHeightPx(1L).displayWidthPx(1L).build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20241022(computerUse20241022)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).contains(computerUse20241022)
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofBash20241022() {
        val bash20241022 = BetaToolBash20241022.builder().build()

        val betaToolUnion = BetaToolUnion.ofBash20241022(bash20241022)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.bash20241022()).contains(bash20241022)
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofTextEditor20241022() {
        val textEditor20241022 = BetaToolTextEditor20241022.builder().build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20241022(textEditor20241022)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).contains(textEditor20241022)
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofComputerUse20250124() {
        val computerUse20250124 =
            BetaToolComputerUse20250124.builder().displayHeightPx(1L).displayWidthPx(1L).build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20250124(computerUse20250124)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).contains(computerUse20250124)
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofBash20250124() {
        val bash20250124 = BetaToolBash20250124.builder().build()

        val betaToolUnion = BetaToolUnion.ofBash20250124(bash20250124)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.bash20250124()).contains(bash20250124)
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofTextEditor20250124() {
        val textEditor20250124 = BetaToolTextEditor20250124.builder().build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250124(textEditor20250124)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).contains(textEditor20250124)
    }
}
