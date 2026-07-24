// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackCreditRedeemedTest {

    @Test
    fun create() {
        val betaFallbackCreditRedeemed = BetaFallbackCreditRedeemed.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackCreditRedeemed = BetaFallbackCreditRedeemed.builder().build()

        val roundtrippedBetaFallbackCreditRedeemed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackCreditRedeemed),
                jacksonTypeRef<BetaFallbackCreditRedeemed>(),
            )

        assertThat(roundtrippedBetaFallbackCreditRedeemed).isEqualTo(betaFallbackCreditRedeemed)
    }
}
