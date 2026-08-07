// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackCreditNotAppliedTest {

    @Test
    fun create() {
        val betaFallbackCreditNotApplied =
            BetaFallbackCreditNotApplied.builder()
                .reason(BetaFallbackCreditNotApplied.Reason.BODY_MISMATCH)
                .addRemoveToRedeem("string")
                .build()

        assertThat(betaFallbackCreditNotApplied.reason())
            .isEqualTo(BetaFallbackCreditNotApplied.Reason.BODY_MISMATCH)
        assertThat(betaFallbackCreditNotApplied.removeToRedeem().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaFallbackCreditNotApplied =
            BetaFallbackCreditNotApplied.of(BetaFallbackCreditNotApplied.Reason.BODY_MISMATCH)

        val betaFallbackCreditNotApplied =
            baseBetaFallbackCreditNotApplied.toBuilder().addRemoveToRedeem("string").build()

        assertThat(betaFallbackCreditNotApplied.removeToRedeem().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackCreditNotApplied =
            BetaFallbackCreditNotApplied.builder()
                .reason(BetaFallbackCreditNotApplied.Reason.BODY_MISMATCH)
                .addRemoveToRedeem("string")
                .build()

        val roundtrippedBetaFallbackCreditNotApplied =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackCreditNotApplied),
                jacksonTypeRef<BetaFallbackCreditNotApplied>(),
            )

        assertThat(roundtrippedBetaFallbackCreditNotApplied).isEqualTo(betaFallbackCreditNotApplied)
    }
}
