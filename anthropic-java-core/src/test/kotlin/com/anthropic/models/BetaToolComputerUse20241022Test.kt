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
                .name(BetaToolComputerUse20241022.Name.COMPUTER)
                .type(BetaToolComputerUse20241022.Type.COMPUTER_20241022)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .displayNumber(0L)
                .build()
        assertThat(betaToolComputerUse20241022).isNotNull
        assertThat(betaToolComputerUse20241022.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.name())
            .isEqualTo(BetaToolComputerUse20241022.Name.COMPUTER)
        assertThat(betaToolComputerUse20241022.type())
            .isEqualTo(BetaToolComputerUse20241022.Type.COMPUTER_20241022)
        assertThat(betaToolComputerUse20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
        assertThat(betaToolComputerUse20241022.displayNumber()).contains(0L)
    }
}
