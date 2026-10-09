package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagent20261001Test {

    @Test
    fun create() {
        val betaManagedAgentsMultiagent20261001 =
            BetaManagedAgentsMultiagent20261001.builder()
                .advisor(BetaManagedAgentsMultiagentAdvisorDisabled.builder().build())
                .subagents(
                    BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .workflows(
                    BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMultiagent20261001.advisor())
            .isEqualTo(
                BetaManagedAgentsMultiagentAdvisor.ofDisabled(
                    BetaManagedAgentsMultiagentAdvisorDisabled.builder().build()
                )
            )
        assertThat(betaManagedAgentsMultiagent20261001.subagents())
            .isEqualTo(
                BetaManagedAgentsMultiagentSubagents.ofEnabled(
                    BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsMultiagent20261001.workflows())
            .isEqualTo(
                BetaManagedAgentsMultiagentWorkflows.ofEnabled(
                    BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagent20261001 =
            BetaManagedAgentsMultiagent20261001.builder()
                .advisor(BetaManagedAgentsMultiagentAdvisorDisabled.builder().build())
                .subagents(
                    BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .workflows(
                    BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMultiagent20261001 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagent20261001),
                jacksonTypeRef<BetaManagedAgentsMultiagent20261001>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagent20261001)
            .isEqualTo(betaManagedAgentsMultiagent20261001)
    }
}
