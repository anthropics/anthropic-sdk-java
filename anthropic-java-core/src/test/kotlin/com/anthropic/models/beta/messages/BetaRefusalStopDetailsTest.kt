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
                .explanation("explanation")
                .build()

        assertThat(betaRefusalStopDetails.category())
            .contains(BetaRefusalStopDetails.Category.CYBER)
        assertThat(betaRefusalStopDetails.explanation()).contains("explanation")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRefusalStopDetails =
            BetaRefusalStopDetails.builder()
                .category(BetaRefusalStopDetails.Category.CYBER)
                .explanation("explanation")
                .build()

        val roundtrippedBetaRefusalStopDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRefusalStopDetails),
                jacksonTypeRef<BetaRefusalStopDetails>(),
            )

        assertThat(roundtrippedBetaRefusalStopDetails).isEqualTo(betaRefusalStopDetails)
    }
}
