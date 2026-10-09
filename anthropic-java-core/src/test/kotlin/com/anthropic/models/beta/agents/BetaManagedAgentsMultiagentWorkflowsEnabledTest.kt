package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentWorkflowsEnabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentWorkflowsEnabled =
            BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMultiagentWorkflowsEnabled.inlineAgents())
            .isEqualTo(
                BetaManagedAgentsMultiagentInlineAgents.ofDisabled(
                    BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build()
                )
            )
        assertThat(betaManagedAgentsMultiagentWorkflowsEnabled.predefinedAgents())
            .containsExactly(
                BetaManagedAgentsAgentReference.builder()
                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .type(BetaManagedAgentsAgentReference.Type.AGENT)
                    .version(1)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflowsEnabled =
            BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMultiagentWorkflowsEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflowsEnabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsEnabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflowsEnabled)
            .isEqualTo(betaManagedAgentsMultiagentWorkflowsEnabled)
    }
}
