// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackCreditTokenParamTest {

    @Test
    fun create() {
        val betaFallbackCreditTokenParam =
            BetaFallbackCreditTokenParam.builder()
                .token("x")
                .mode(BetaFallbackCreditTokenParam.Mode.STRICT)
                .build()

        assertThat(betaFallbackCreditTokenParam.token()).isEqualTo("x")
        assertThat(betaFallbackCreditTokenParam.mode())
            .contains(BetaFallbackCreditTokenParam.Mode.STRICT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackCreditTokenParam =
            BetaFallbackCreditTokenParam.builder()
                .token("x")
                .mode(BetaFallbackCreditTokenParam.Mode.STRICT)
                .build()

        val roundtrippedBetaFallbackCreditTokenParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackCreditTokenParam),
                jacksonTypeRef<BetaFallbackCreditTokenParam>(),
            )

        assertThat(roundtrippedBetaFallbackCreditTokenParam).isEqualTo(betaFallbackCreditTokenParam)
    }
}
