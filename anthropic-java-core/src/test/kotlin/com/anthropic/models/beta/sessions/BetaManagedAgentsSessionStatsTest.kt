// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionStatsTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionStats =
            BetaManagedAgentsSessionStats.builder().activeSeconds(0.0).durationSeconds(0.0).build()

        assertThat(betaManagedAgentsSessionStats.activeSeconds()).contains(0.0)
        assertThat(betaManagedAgentsSessionStats.durationSeconds()).contains(0.0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionStats =
            BetaManagedAgentsSessionStats.builder().activeSeconds(0.0).durationSeconds(0.0).build()

        val roundtrippedBetaManagedAgentsSessionStats =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionStats),
                jacksonTypeRef<BetaManagedAgentsSessionStats>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionStats)
            .isEqualTo(betaManagedAgentsSessionStats)
    }
}
