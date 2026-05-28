// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOutputTokensDetailsTest {

    @Test
    fun create() {
        val betaOutputTokensDetails = BetaOutputTokensDetails.builder().thinkingTokens(0L).build()

        assertThat(betaOutputTokensDetails.thinkingTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputTokensDetails = BetaOutputTokensDetails.builder().thinkingTokens(0L).build()

        val roundtrippedBetaOutputTokensDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputTokensDetails),
                jacksonTypeRef<BetaOutputTokensDetails>(),
            )

        assertThat(roundtrippedBetaOutputTokensDetails).isEqualTo(betaOutputTokensDetails)
    }
}
