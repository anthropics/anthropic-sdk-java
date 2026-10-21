package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRequiresActionTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRequiresAction =
            BetaManagedAgentsSessionRequiresAction.builder().addEventId("string").build()

        assertThat(betaManagedAgentsSessionRequiresAction.eventIds()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRequiresAction =
            BetaManagedAgentsSessionRequiresAction.builder().addEventId("string").build()

        val roundtrippedBetaManagedAgentsSessionRequiresAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRequiresAction),
                jacksonTypeRef<BetaManagedAgentsSessionRequiresAction>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRequiresAction)
            .isEqualTo(betaManagedAgentsSessionRequiresAction)
    }
}
