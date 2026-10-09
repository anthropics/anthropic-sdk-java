package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentSubagentsEnabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentSubagentsEnabled =
            BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMultiagentSubagentsEnabled.inlineAgents())
            .isEqualTo(
                BetaManagedAgentsMultiagentInlineAgents.ofDisabled(
                    BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build()
                )
            )
        assertThat(betaManagedAgentsMultiagentSubagentsEnabled.predefinedAgents())
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
        val betaManagedAgentsMultiagentSubagentsEnabled =
            BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMultiagentSubagentsEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagentsEnabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsEnabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagentsEnabled)
            .isEqualTo(betaManagedAgentsMultiagentSubagentsEnabled)
    }
}
