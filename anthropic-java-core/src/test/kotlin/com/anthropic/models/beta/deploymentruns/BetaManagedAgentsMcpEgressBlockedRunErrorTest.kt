// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpEgressBlockedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpEgressBlockedRunError =
            BetaManagedAgentsMcpEgressBlockedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsMcpEgressBlockedRunError.Type.MCP_EGRESS_BLOCKED_ERROR)
                .build()

        assertThat(betaManagedAgentsMcpEgressBlockedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsMcpEgressBlockedRunError.type())
            .isEqualTo(BetaManagedAgentsMcpEgressBlockedRunError.Type.MCP_EGRESS_BLOCKED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpEgressBlockedRunError =
            BetaManagedAgentsMcpEgressBlockedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsMcpEgressBlockedRunError.Type.MCP_EGRESS_BLOCKED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsMcpEgressBlockedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpEgressBlockedRunError),
                jacksonTypeRef<BetaManagedAgentsMcpEgressBlockedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpEgressBlockedRunError)
            .isEqualTo(betaManagedAgentsMcpEgressBlockedRunError)
    }
}
