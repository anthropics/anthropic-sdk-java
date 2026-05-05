// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionThreadStatsTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionThreadStats =
            BetaManagedAgentsSessionThreadStats.builder()
                .activeSeconds(0.0)
                .durationSeconds(0.0)
                .startupSeconds(0.0)
                .build()

        assertThat(betaManagedAgentsSessionThreadStats.activeSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionThreadStats.durationSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionThreadStats.startupSeconds()).contains(0.0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionThreadStats =
            BetaManagedAgentsSessionThreadStats.builder()
                .activeSeconds(0.0)
                .durationSeconds(0.0)
                .startupSeconds(0.0)
                .build()

        val roundtrippedBetaManagedAgentsSessionThreadStats =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionThreadStats),
                jacksonTypeRef<BetaManagedAgentsSessionThreadStats>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionThreadStats)
            .isEqualTo(betaManagedAgentsSessionThreadStats)
    }
}
