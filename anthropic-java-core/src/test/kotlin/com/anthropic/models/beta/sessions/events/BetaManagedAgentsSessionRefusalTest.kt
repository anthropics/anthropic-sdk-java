package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRefusalTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRefusal = BetaManagedAgentsSessionRefusal.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRefusal = BetaManagedAgentsSessionRefusal.builder().build()

        val roundtrippedBetaManagedAgentsSessionRefusal =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRefusal),
                jacksonTypeRef<BetaManagedAgentsSessionRefusal>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRefusal)
            .isEqualTo(betaManagedAgentsSessionRefusal)
    }
}
