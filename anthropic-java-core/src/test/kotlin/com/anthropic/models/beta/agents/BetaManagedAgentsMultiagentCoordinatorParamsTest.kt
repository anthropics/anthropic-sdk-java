// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsMultiagentRosterEntryParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentCoordinatorParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentCoordinatorParams =
            BetaManagedAgentsMultiagentCoordinatorParams.builder()
                .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addAgent(
                    BetaManagedAgentsMultiagentSelfParams.builder()
                        .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                        .build()
                )
                .type(BetaManagedAgentsMultiagentCoordinatorParams.Type.COORDINATOR)
                .build()

        assertThat(betaManagedAgentsMultiagentCoordinatorParams.agents())
            .containsExactly(
                BetaManagedAgentsMultiagentRosterEntryParams.ofString(
                    "agent_011CZkYqphY8vELVzwCUpqiQ"
                ),
                BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(
                    BetaManagedAgentsMultiagentSelfParams.builder()
                        .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                        .build()
                ),
            )
        assertThat(betaManagedAgentsMultiagentCoordinatorParams.type())
            .isEqualTo(BetaManagedAgentsMultiagentCoordinatorParams.Type.COORDINATOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentCoordinatorParams =
            BetaManagedAgentsMultiagentCoordinatorParams.builder()
                .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addAgent(
                    BetaManagedAgentsMultiagentSelfParams.builder()
                        .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                        .build()
                )
                .type(BetaManagedAgentsMultiagentCoordinatorParams.Type.COORDINATOR)
                .build()

        val roundtrippedBetaManagedAgentsMultiagentCoordinatorParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentCoordinatorParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentCoordinatorParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentCoordinatorParams)
            .isEqualTo(betaManagedAgentsMultiagentCoordinatorParams)
    }
}
