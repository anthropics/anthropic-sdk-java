// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError =
            BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.Type
                        .MCP_EGRESS_BLOCKED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.Type
                    .MCP_EGRESS_BLOCKED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError =
            BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.Type
                        .MCP_EGRESS_BLOCKED_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError)
    }
}
