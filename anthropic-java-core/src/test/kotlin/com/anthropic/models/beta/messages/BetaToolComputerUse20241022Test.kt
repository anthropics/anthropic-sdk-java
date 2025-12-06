// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolComputerUse20241022Test {

    @Test
    fun create() {
        val betaToolComputerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaToolComputerUse20241022.displayHeightPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.displayWidthPx()).isEqualTo(1L)
        assertThat(betaToolComputerUse20241022.allowedCallers().getOrNull())
            .containsExactly(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
        assertThat(betaToolComputerUse20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolComputerUse20241022.deferLoading()).contains(true)
        assertThat(betaToolComputerUse20241022.displayNumber()).contains(0L)
        assertThat(betaToolComputerUse20241022.inputExamples().getOrNull())
            .containsExactly(
                BetaToolComputerUse20241022.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolComputerUse20241022.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolComputerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaToolComputerUse20241022 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolComputerUse20241022),
                jacksonTypeRef<BetaToolComputerUse20241022>(),
            )

        assertThat(roundtrippedBetaToolComputerUse20241022).isEqualTo(betaToolComputerUse20241022)
    }
}
