// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedWorkQueueStatsTest {

    @Test
    fun create() {
        val betaSelfHostedWorkQueueStats =
            BetaSelfHostedWorkQueueStats.builder()
                .depth(0L)
                .oldestQueuedAt("oldest_queued_at")
                .pending(0L)
                .workersPolling(0L)
                .build()

        assertThat(betaSelfHostedWorkQueueStats.depth()).isEqualTo(0L)
        assertThat(betaSelfHostedWorkQueueStats.oldestQueuedAt()).contains("oldest_queued_at")
        assertThat(betaSelfHostedWorkQueueStats.pending()).isEqualTo(0L)
        assertThat(betaSelfHostedWorkQueueStats.workersPolling()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedWorkQueueStats =
            BetaSelfHostedWorkQueueStats.builder()
                .depth(0L)
                .oldestQueuedAt("oldest_queued_at")
                .pending(0L)
                .workersPolling(0L)
                .build()

        val roundtrippedBetaSelfHostedWorkQueueStats =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedWorkQueueStats),
                jacksonTypeRef<BetaSelfHostedWorkQueueStats>(),
            )

        assertThat(roundtrippedBetaSelfHostedWorkQueueStats).isEqualTo(betaSelfHostedWorkQueueStats)
    }
}
