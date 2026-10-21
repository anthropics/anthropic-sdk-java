package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsAgentArchivedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsAgentArchivedDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsAgentArchivedDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentArchivedDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsAgentArchivedDeploymentPausedReasonError)
    }
}
