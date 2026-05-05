// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSelfParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentParams =
            BetaManagedAgentsMultiagentParams.builder()
                .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addAgent(
                    BetaManagedAgentsMultiagentSelfParams.builder()
                        .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                        .build()
                )
                .type(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
                .build()

        assertThat(betaManagedAgentsMultiagentParams.agents())
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
        assertThat(betaManagedAgentsMultiagentParams.type())
            .isEqualTo(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentParams =
            BetaManagedAgentsMultiagentParams.builder()
                .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addAgent(
                    BetaManagedAgentsMultiagentSelfParams.builder()
                        .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                        .build()
                )
                .type(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
                .build()

        val roundtrippedBetaManagedAgentsMultiagentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentParams)
            .isEqualTo(betaManagedAgentsMultiagentParams)
    }
}
