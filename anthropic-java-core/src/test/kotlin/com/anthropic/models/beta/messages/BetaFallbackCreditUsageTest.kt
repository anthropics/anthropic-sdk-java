// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackCreditUsageTest {

    @Test
    fun create() {
        val betaFallbackCreditUsage =
            BetaFallbackCreditUsage.builder()
                .status(BetaFallbackCreditRedeemed.builder().build())
                .build()

        assertThat(betaFallbackCreditUsage.status())
            .isEqualTo(
                BetaFallbackCreditUsage.Status.ofRedeemed(
                    BetaFallbackCreditRedeemed.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackCreditUsage =
            BetaFallbackCreditUsage.builder()
                .status(BetaFallbackCreditRedeemed.builder().build())
                .build()

        val roundtrippedBetaFallbackCreditUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackCreditUsage),
                jacksonTypeRef<BetaFallbackCreditUsage>(),
            )

        assertThat(roundtrippedBetaFallbackCreditUsage).isEqualTo(betaFallbackCreditUsage)
    }
}
