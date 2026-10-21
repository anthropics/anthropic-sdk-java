package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError =
            BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError =
            BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder().build()

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
