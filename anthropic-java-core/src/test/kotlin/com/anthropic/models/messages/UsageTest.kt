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
                .inferenceGeo("inference_geo")
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(
                    ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .serviceTier(Usage.ServiceTier.STANDARD)
                .speed(Usage.Speed.STANDARD)
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
        assertThat(usage.inferenceGeo()).contains("inference_geo")
        assertThat(usage.inputTokens()).isEqualTo(2095L)
        assertThat(usage.outputTokens()).isEqualTo(503L)
        assertThat(usage.serverToolUse())
            .contains(ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build())
        assertThat(usage.serviceTier()).contains(Usage.ServiceTier.STANDARD)
        assertThat(usage.speed()).contains(Usage.Speed.STANDARD)
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
                .inferenceGeo("inference_geo")
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(
                    ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .serviceTier(Usage.ServiceTier.STANDARD)
                .speed(Usage.Speed.STANDARD)
                .build()

        val roundtrippedUsage =
            jsonMapper.readValue(jsonMapper.writeValueAsString(usage), jacksonTypeRef<Usage>())

        assertThat(roundtrippedUsage).isEqualTo(usage)
    }
}
