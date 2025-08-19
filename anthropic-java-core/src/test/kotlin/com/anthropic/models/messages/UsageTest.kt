// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsageTest {

    @Test
    fun create() {
        val usage =
            Usage.builder()
                .cacheCreation(
                    CacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                .serviceTier(Usage.ServiceTier.STANDARD)
                .build()

        assertThat(usage.cacheCreation())
            .contains(
                CacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(usage.cacheCreationInputTokens()).contains(2051L)
        assertThat(usage.cacheReadInputTokens()).contains(2051L)
        assertThat(usage.inputTokens()).isEqualTo(2095L)
        assertThat(usage.outputTokens()).isEqualTo(503L)
        assertThat(usage.serverToolUse())
            .contains(ServerToolUsage.builder().webSearchRequests(0L).build())
        assertThat(usage.serviceTier()).contains(Usage.ServiceTier.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val usage =
            Usage.builder()
                .cacheCreation(
                    CacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(ServerToolUsage.builder().webSearchRequests(0L).build())
                .serviceTier(Usage.ServiceTier.STANDARD)
                .build()

        val roundtrippedUsage =
            jsonMapper.readValue(jsonMapper.writeValueAsString(usage), jacksonTypeRef<Usage>())

        assertThat(roundtrippedUsage).isEqualTo(usage)
    }
}
