package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentWorkflowsEnabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentWorkflowsEnabledParams =
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

        assertThat(betaManagedAgentsMultiagentWorkflowsEnabledParams.inlineAgents())
            .contains(
                BetaManagedAgentsMultiagentInlineAgentsParams.ofDisabled(
                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                )
            )
        assertThat(betaManagedAgentsMultiagentWorkflowsEnabledParams.predefinedAgents().getOrNull())
            .containsExactly(
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                    BetaManagedAgentsAgentParams.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentParams.Type.AGENT)
                        .version(1)
                        .build()
                )
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsMultiagentWorkflowsEnabledParams =
            BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder().build()

        val betaManagedAgentsMultiagentWorkflowsEnabledParams =
            baseBetaManagedAgentsMultiagentWorkflowsEnabledParams
                .toBuilder()
                .addPredefinedAgent(
                    BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                        BetaManagedAgentsAgentParams.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .type(BetaManagedAgentsAgentParams.Type.AGENT)
                            .version(1)
                            .build()
                    )
                )
                .build()

        assertThat(betaManagedAgentsMultiagentWorkflowsEnabledParams.predefinedAgents().getOrNull())
            .containsExactly(
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                    BetaManagedAgentsAgentParams.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentParams.Type.AGENT)
                        .version(1)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflowsEnabledParams =
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

        val roundtrippedBetaManagedAgentsMultiagentWorkflowsEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflowsEnabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsEnabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflowsEnabledParams)
            .isEqualTo(betaManagedAgentsMultiagentWorkflowsEnabledParams)
    }
}
