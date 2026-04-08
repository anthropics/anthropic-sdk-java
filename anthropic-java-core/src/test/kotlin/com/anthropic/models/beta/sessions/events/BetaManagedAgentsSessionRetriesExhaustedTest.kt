// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRetriesExhaustedTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRetriesExhausted =
            BetaManagedAgentsSessionRetriesExhausted.builder()
                .type(BetaManagedAgentsSessionRetriesExhausted.Type.RETRIES_EXHAUSTED)
                .build()

        assertThat(betaManagedAgentsSessionRetriesExhausted.type())
            .isEqualTo(BetaManagedAgentsSessionRetriesExhausted.Type.RETRIES_EXHAUSTED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRetriesExhausted =
            BetaManagedAgentsSessionRetriesExhausted.builder()
                .type(BetaManagedAgentsSessionRetriesExhausted.Type.RETRIES_EXHAUSTED)
                .build()

        val roundtrippedBetaManagedAgentsSessionRetriesExhausted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRetriesExhausted),
                jacksonTypeRef<BetaManagedAgentsSessionRetriesExhausted>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRetriesExhausted)
            .isEqualTo(betaManagedAgentsSessionRetriesExhausted)
    }
}
