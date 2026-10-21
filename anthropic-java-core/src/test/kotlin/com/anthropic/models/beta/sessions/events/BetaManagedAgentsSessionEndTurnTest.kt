package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionEndTurnTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionEndTurn = BetaManagedAgentsSessionEndTurn.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEndTurn = BetaManagedAgentsSessionEndTurn.builder().build()

        val roundtrippedBetaManagedAgentsSessionEndTurn =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEndTurn),
                jacksonTypeRef<BetaManagedAgentsSessionEndTurn>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEndTurn)
            .isEqualTo(betaManagedAgentsSessionEndTurn)
    }
}
