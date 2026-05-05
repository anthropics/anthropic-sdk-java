// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagent =
            BetaManagedAgentsMultiagent.builder()
                .addAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .type(BetaManagedAgentsMultiagent.Type.COORDINATOR)
                .build()

        assertThat(betaManagedAgentsMultiagent.agents())
            .containsExactly(
                BetaManagedAgentsAgentReference.builder()
                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .type(BetaManagedAgentsAgentReference.Type.AGENT)
                    .version(1)
                    .build()
            )
        assertThat(betaManagedAgentsMultiagent.type())
            .isEqualTo(BetaManagedAgentsMultiagent.Type.COORDINATOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagent =
            BetaManagedAgentsMultiagent.builder()
                .addAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .type(BetaManagedAgentsMultiagent.Type.COORDINATOR)
                .build()

        val roundtrippedBetaManagedAgentsMultiagent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagent),
                jacksonTypeRef<BetaManagedAgentsMultiagent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagent).isEqualTo(betaManagedAgentsMultiagent)
    }
}
