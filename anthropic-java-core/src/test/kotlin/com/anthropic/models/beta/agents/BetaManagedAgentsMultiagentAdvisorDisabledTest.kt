package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentAdvisorDisabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentAdvisorDisabled =
            BetaManagedAgentsMultiagentAdvisorDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorDisabled =
            BetaManagedAgentsMultiagentAdvisorDisabled.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentAdvisorDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisorDisabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorDisabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisorDisabled)
            .isEqualTo(betaManagedAgentsMultiagentAdvisorDisabled)
    }
}
