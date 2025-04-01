// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolComputerUse20250124Test {

    @Test
    fun create() {
        val betaToolComputerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .displayNumber(0L)
                .build()

        assertThat(betaToolComputerUse20250124.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20250124.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaToolComputerUse20250124.displayNumber()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolComputerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .displayNumber(0L)
                .build()

        val roundtrippedBetaToolComputerUse20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolComputerUse20250124),
                jacksonTypeRef<BetaToolComputerUse20250124>(),
            )

        assertThat(roundtrippedBetaToolComputerUse20250124).isEqualTo(betaToolComputerUse20250124)
    }
}
