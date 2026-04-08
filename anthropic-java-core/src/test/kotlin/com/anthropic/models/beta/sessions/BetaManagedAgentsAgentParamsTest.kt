// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentParams =
            BetaManagedAgentsAgentParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentParams.Type.AGENT)
                .version(0)
                .build()

        assertThat(betaManagedAgentsAgentParams.id()).isEqualTo("x")
        assertThat(betaManagedAgentsAgentParams.type())
            .isEqualTo(BetaManagedAgentsAgentParams.Type.AGENT)
        assertThat(betaManagedAgentsAgentParams.version()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentParams =
            BetaManagedAgentsAgentParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentParams.Type.AGENT)
                .version(0)
                .build()

        val roundtrippedBetaManagedAgentsAgentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentParams),
                jacksonTypeRef<BetaManagedAgentsAgentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentParams).isEqualTo(betaManagedAgentsAgentParams)
    }
}
