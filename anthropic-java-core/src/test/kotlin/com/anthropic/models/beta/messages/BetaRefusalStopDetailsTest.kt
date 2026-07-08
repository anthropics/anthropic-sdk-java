// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRefusalStopDetailsTest {

    @Test
    fun create() {
        val betaRefusalStopDetails =
            BetaRefusalStopDetails.builder()
                .category(BetaRefusalStopDetails.Category.CYBER)
                .explanation(
                    "This request was declined because it conflicts with Anthropic's Usage Policy."
                )
                .fallbackCreditToken("QW50aHJvcGljL0NsYXVkZQ==")
                .fallbackHasPrefillClaim(true)
                .recommendedModel("claude-sonnet-4-6")
                .build()

        assertThat(betaRefusalStopDetails.category())
            .contains(BetaRefusalStopDetails.Category.CYBER)
        assertThat(betaRefusalStopDetails.explanation())
            .contains(
                "This request was declined because it conflicts with Anthropic's Usage Policy."
            )
        assertThat(betaRefusalStopDetails.fallbackCreditToken())
            .contains("QW50aHJvcGljL0NsYXVkZQ==")
        assertThat(betaRefusalStopDetails.fallbackHasPrefillClaim()).contains(true)
        assertThat(betaRefusalStopDetails.recommendedModel()).contains("claude-sonnet-4-6")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRefusalStopDetails =
            BetaRefusalStopDetails.builder()
                .category(BetaRefusalStopDetails.Category.CYBER)
                .explanation(
                    "This request was declined because it conflicts with Anthropic's Usage Policy."
                )
                .fallbackCreditToken("QW50aHJvcGljL0NsYXVkZQ==")
                .fallbackHasPrefillClaim(true)
                .recommendedModel("claude-sonnet-4-6")
                .build()

        val roundtrippedBetaRefusalStopDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRefusalStopDetails),
                jacksonTypeRef<BetaRefusalStopDetails>(),
            )

        assertThat(roundtrippedBetaRefusalStopDetails).isEqualTo(betaRefusalStopDetails)
    }
}
