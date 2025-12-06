// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolComputerUse20250124Test {

    @Test
    fun create() {
        val betaToolComputerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaToolComputerUse20250124.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20250124.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20250124.allowedCallers().getOrNull())
            .containsExactly(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
        assertThat(betaToolComputerUse20250124.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolComputerUse20250124.deferLoading()).contains(true)
        assertThat(betaToolComputerUse20250124.displayNumber()).contains(0L)
        assertThat(betaToolComputerUse20250124.inputExamples().getOrNull())
            .containsExactly(
                BetaToolComputerUse20250124.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolComputerUse20250124.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolComputerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaToolComputerUse20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolComputerUse20250124),
                jacksonTypeRef<BetaToolComputerUse20250124>(),
            )

        assertThat(roundtrippedBetaToolComputerUse20250124).isEqualTo(betaToolComputerUse20250124)
    }
}
