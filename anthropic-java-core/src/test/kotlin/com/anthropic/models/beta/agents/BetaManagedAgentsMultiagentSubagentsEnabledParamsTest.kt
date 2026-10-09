package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentSubagentsEnabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentSubagentsEnabledParams =
            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                .inlineAgents(
                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                )
                .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addPredefinedAgent(
                    BetaManagedAgentsMultiagentSelfParams.of(
                        BetaManagedAgentsMultiagentSelfParams.Type.SELF
                    )
                )
                .build()

        assertThat(betaManagedAgentsMultiagentSubagentsEnabledParams.inlineAgents())
            .contains(
                BetaManagedAgentsMultiagentInlineAgentsParams.ofDisabled(
                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                )
            )
        assertThat(betaManagedAgentsMultiagentSubagentsEnabledParams.predefinedAgents().getOrNull())
            .containsExactly(
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofString(
                    "agent_011CZkYqphY8vELVzwCUpqiQ"
                ),
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(
                    BetaManagedAgentsMultiagentSelfParams.Type.SELF
                ),
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsMultiagentSubagentsEnabledParams =
            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder().build()

        val betaManagedAgentsMultiagentSubagentsEnabledParams =
            baseBetaManagedAgentsMultiagentSubagentsEnabledParams
                .toBuilder()
                .addPredefinedAgent(
                    BetaManagedAgentsMultiagentPredefinedAgentParams.ofString(
                        "agent_011CZkYqphY8vELVzwCUpqiQ"
                    )
                )
                .addPredefinedAgent(
                    BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(
                        BetaManagedAgentsMultiagentSelfParams.Type.SELF
                    )
                )
                .build()

        assertThat(betaManagedAgentsMultiagentSubagentsEnabledParams.predefinedAgents().getOrNull())
            .containsExactly(
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofString(
                    "agent_011CZkYqphY8vELVzwCUpqiQ"
                ),
                BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(
                    BetaManagedAgentsMultiagentSelfParams.Type.SELF
                ),
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagentsEnabledParams =
            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                .inlineAgents(
                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                )
                .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addPredefinedAgent(
                    BetaManagedAgentsMultiagentSelfParams.of(
                        BetaManagedAgentsMultiagentSelfParams.Type.SELF
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsMultiagentSubagentsEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagentsEnabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsEnabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagentsEnabledParams)
            .isEqualTo(betaManagedAgentsMultiagentSubagentsEnabledParams)
    }
}
