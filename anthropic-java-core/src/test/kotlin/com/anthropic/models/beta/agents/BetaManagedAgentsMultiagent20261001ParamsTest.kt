package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagent20261001ParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagent20261001Params =
            BetaManagedAgentsMultiagent20261001Params.builder()
                .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                .subagents(
                    BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .build()
                )
                .workflows(
                    BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentParams.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentParams.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMultiagent20261001Params.advisor())
            .contains(
                BetaManagedAgentsMultiagentAdvisorParams.ofDisabled(
                    BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build()
                )
            )
        assertThat(betaManagedAgentsMultiagent20261001Params.subagents())
            .contains(
                BetaManagedAgentsMultiagentSubagentsParams.ofEnabled(
                    BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .build()
                )
            )
        assertThat(betaManagedAgentsMultiagent20261001Params.workflows())
            .contains(
                BetaManagedAgentsMultiagentWorkflowsParams.ofEnabled(
                    BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentParams.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentParams.Type.AGENT)
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
        val betaManagedAgentsMultiagent20261001Params =
            BetaManagedAgentsMultiagent20261001Params.builder()
                .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                .subagents(
                    BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .build()
                )
                .workflows(
                    BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentParams.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentParams.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMultiagent20261001Params =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagent20261001Params),
                jacksonTypeRef<BetaManagedAgentsMultiagent20261001Params>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagent20261001Params)
            .isEqualTo(betaManagedAgentsMultiagent20261001Params)
    }
}
