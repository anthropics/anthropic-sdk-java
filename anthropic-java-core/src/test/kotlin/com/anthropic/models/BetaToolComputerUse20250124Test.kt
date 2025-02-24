// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolComputerUse20250124Test {

    @Test
    fun createBetaToolComputerUse20250124() {
        val betaToolComputerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .displayNumber(0L)
                .build()
        assertThat(betaToolComputerUse20250124).isNotNull
        assertThat(betaToolComputerUse20250124.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20250124.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaToolComputerUse20250124.displayNumber()).contains(0L)
    }
}
