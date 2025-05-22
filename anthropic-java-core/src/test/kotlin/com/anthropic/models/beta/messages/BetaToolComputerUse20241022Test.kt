// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolComputerUse20241022Test {

    @Test
    fun create() {
        val betaToolComputerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .displayNumber(0L)
                .build()

        assertThat(betaToolComputerUse20241022.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolComputerUse20241022.displayNumber()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolComputerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .displayNumber(0L)
                .build()

        val roundtrippedBetaToolComputerUse20241022 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolComputerUse20241022),
                jacksonTypeRef<BetaToolComputerUse20241022>(),
            )

        assertThat(roundtrippedBetaToolComputerUse20241022).isEqualTo(betaToolComputerUse20241022)
    }
}
