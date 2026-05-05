// File generated from our OpenAPI spec by Stainless.

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
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .type(BetaManagedAgentsMultiagentCoordinator.Type.COORDINATOR)
                .build()

        assertThat(betaManagedAgentsMultiagentCoordinator.agents())
            .containsExactly(
                BetaManagedAgentsAgentReference.builder()
                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .type(BetaManagedAgentsAgentReference.Type.AGENT)
                    .version(1)
                    .build()
            )
        assertThat(betaManagedAgentsMultiagentCoordinator.type())
            .isEqualTo(BetaManagedAgentsMultiagentCoordinator.Type.COORDINATOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentCoordinator =
            BetaManagedAgentsMultiagentCoordinator.builder()
                .addAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .type(BetaManagedAgentsMultiagentCoordinator.Type.COORDINATOR)
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
