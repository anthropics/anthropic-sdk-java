package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentAdvisorEnabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentAdvisorEnabledParams =
            BetaManagedAgentsMultiagentAdvisorEnabledParams.of("claude-fable-5")

        assertThat(betaManagedAgentsMultiagentAdvisorEnabledParams.model())
            .isEqualTo("claude-fable-5")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorEnabledParams =
            BetaManagedAgentsMultiagentAdvisorEnabledParams.of("claude-fable-5")

        val roundtrippedBetaManagedAgentsMultiagentAdvisorEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisorEnabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorEnabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisorEnabledParams)
            .isEqualTo(betaManagedAgentsMultiagentAdvisorEnabledParams)
    }
}
