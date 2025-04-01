// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageDeltaUsageTest {

    @Test
    fun create() {
        val betaMessageDeltaUsage = BetaMessageDeltaUsage.builder().outputTokens(503L).build()

        assertThat(betaMessageDeltaUsage.outputTokens()).isEqualTo(503L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageDeltaUsage = BetaMessageDeltaUsage.builder().outputTokens(503L).build()

        val roundtrippedBetaMessageDeltaUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageDeltaUsage),
                jacksonTypeRef<BetaMessageDeltaUsage>(),
            )

        assertThat(roundtrippedBetaMessageDeltaUsage).isEqualTo(betaMessageDeltaUsage)
    }
}
