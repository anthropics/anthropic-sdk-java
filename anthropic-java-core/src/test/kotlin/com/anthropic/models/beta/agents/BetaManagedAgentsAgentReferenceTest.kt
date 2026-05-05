// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentReferenceTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentReference =
            BetaManagedAgentsAgentReference.builder()
                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                .version(1)
                .build()

        assertThat(betaManagedAgentsAgentReference.id()).isEqualTo("agent_011CZkYqphY8vELVzwCUpqiQ")
        assertThat(betaManagedAgentsAgentReference.type())
            .isEqualTo(BetaManagedAgentsAgentReference.Type.AGENT)
        assertThat(betaManagedAgentsAgentReference.version()).isEqualTo(1)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentReference =
            BetaManagedAgentsAgentReference.builder()
                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                .version(1)
                .build()

        val roundtrippedBetaManagedAgentsAgentReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentReference),
                jacksonTypeRef<BetaManagedAgentsAgentReference>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentReference)
            .isEqualTo(betaManagedAgentsAgentReference)
    }
}
