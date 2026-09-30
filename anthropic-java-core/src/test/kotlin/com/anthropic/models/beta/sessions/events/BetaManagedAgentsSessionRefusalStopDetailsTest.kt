package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRefusalStopDetailsTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRefusalStopDetails =
            BetaManagedAgentsSessionRefusalStopDetails.builder()
                .category(BetaManagedAgentsSessionRefusalStopDetails.Category.CYBER)
                .explanation("explanation")
                .build()

        assertThat(betaManagedAgentsSessionRefusalStopDetails.category())
            .contains(BetaManagedAgentsSessionRefusalStopDetails.Category.CYBER)
        assertThat(betaManagedAgentsSessionRefusalStopDetails.explanation()).contains("explanation")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRefusalStopDetails =
            BetaManagedAgentsSessionRefusalStopDetails.builder()
                .category(BetaManagedAgentsSessionRefusalStopDetails.Category.CYBER)
                .explanation("explanation")
                .build()

        val roundtrippedBetaManagedAgentsSessionRefusalStopDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRefusalStopDetails),
                jacksonTypeRef<BetaManagedAgentsSessionRefusalStopDetails>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRefusalStopDetails)
            .isEqualTo(betaManagedAgentsSessionRefusalStopDetails)
    }
}
