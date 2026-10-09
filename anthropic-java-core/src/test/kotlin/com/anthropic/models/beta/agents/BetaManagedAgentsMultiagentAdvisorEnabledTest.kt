package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentAdvisorEnabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentAdvisorEnabled =
            BetaManagedAgentsMultiagentAdvisorEnabled.of("claude-fable-5")

        assertThat(betaManagedAgentsMultiagentAdvisorEnabled.model()).isEqualTo("claude-fable-5")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorEnabled =
            BetaManagedAgentsMultiagentAdvisorEnabled.of("claude-fable-5")

        val roundtrippedBetaManagedAgentsMultiagentAdvisorEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisorEnabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorEnabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisorEnabled)
            .isEqualTo(betaManagedAgentsMultiagentAdvisorEnabled)
    }
}
