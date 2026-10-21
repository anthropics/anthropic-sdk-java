package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRetriesExhaustedTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRetriesExhausted =
            BetaManagedAgentsSessionRetriesExhausted.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRetriesExhausted =
            BetaManagedAgentsSessionRetriesExhausted.builder().build()

        val roundtrippedBetaManagedAgentsSessionRetriesExhausted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRetriesExhausted),
                jacksonTypeRef<BetaManagedAgentsSessionRetriesExhausted>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRetriesExhausted)
            .isEqualTo(betaManagedAgentsSessionRetriesExhausted)
    }
}
