// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolComputerUse20241022Test {

    @Test
    fun createBetaToolComputerUse20241022() {
        val betaToolComputerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .displayNumber(0L)
                .build()
        assertThat(betaToolComputerUse20241022).isNotNull
        assertThat(betaToolComputerUse20241022.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaToolComputerUse20241022.displayNumber()).contains(0L)
    }
}
