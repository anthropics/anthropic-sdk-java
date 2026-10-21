package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentCoordinatorTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentCoordinator =
            BetaManagedAgentsMultiagentCoordinator.builder()
                .addAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .version(1)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMultiagentCoordinator.agents())
            .containsExactly(
                BetaManagedAgentsMultiagentCoordinator.Agent.ofAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .version(1)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentCoordinator =
            BetaManagedAgentsMultiagentCoordinator.builder()
                .addAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .version(1)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMultiagentCoordinator =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentCoordinator),
                jacksonTypeRef<BetaManagedAgentsMultiagentCoordinator>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentCoordinator)
            .isEqualTo(betaManagedAgentsMultiagentCoordinator)
    }
}
