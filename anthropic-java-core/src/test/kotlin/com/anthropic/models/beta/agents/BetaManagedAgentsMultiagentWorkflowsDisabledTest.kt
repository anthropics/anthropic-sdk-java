package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentWorkflowsDisabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentWorkflowsDisabled =
            BetaManagedAgentsMultiagentWorkflowsDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflowsDisabled =
            BetaManagedAgentsMultiagentWorkflowsDisabled.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentWorkflowsDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflowsDisabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsDisabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflowsDisabled)
            .isEqualTo(betaManagedAgentsMultiagentWorkflowsDisabled)
    }
}
