// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageDeltaUsageTest {

    @Test
    fun create() {
        val betaMessageDeltaUsage =
            BetaMessageDeltaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(
                    BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .build()

        assertThat(betaMessageDeltaUsage.cacheCreationInputTokens()).contains(2051L)
        assertThat(betaMessageDeltaUsage.cacheReadInputTokens()).contains(2051L)
        assertThat(betaMessageDeltaUsage.inputTokens()).contains(2095L)
        assertThat(betaMessageDeltaUsage.outputTokens()).isEqualTo(503L)
        assertThat(betaMessageDeltaUsage.serverToolUse())
            .contains(
                BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageDeltaUsage =
            BetaMessageDeltaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(
                    BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .build()

        val roundtrippedBetaMessageDeltaUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageDeltaUsage),
                jacksonTypeRef<BetaMessageDeltaUsage>(),
            )

        assertThat(roundtrippedBetaMessageDeltaUsage).isEqualTo(betaMessageDeltaUsage)
    }
}
