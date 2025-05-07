// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUsageTest {

    @Test
    fun create() {
        val betaUsage =
            BetaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(BetaServerToolUsage.builder().webSearchRequests(0L).build())
                .build()

        assertThat(betaUsage.cacheCreationInputTokens()).contains(2051L)
        assertThat(betaUsage.cacheReadInputTokens()).contains(2051L)
        assertThat(betaUsage.inputTokens()).isEqualTo(2095L)
        assertThat(betaUsage.outputTokens()).isEqualTo(503L)
        assertThat(betaUsage.serverToolUse())
            .contains(BetaServerToolUsage.builder().webSearchRequests(0L).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUsage =
            BetaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(BetaServerToolUsage.builder().webSearchRequests(0L).build())
                .build()

        val roundtrippedBetaUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUsage),
                jacksonTypeRef<BetaUsage>(),
            )

        assertThat(roundtrippedBetaUsage).isEqualTo(betaUsage)
    }
}
