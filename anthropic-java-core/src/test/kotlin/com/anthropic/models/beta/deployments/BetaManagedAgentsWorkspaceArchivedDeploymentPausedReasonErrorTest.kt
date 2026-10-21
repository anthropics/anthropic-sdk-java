package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError =
            BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError =
            BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError)
    }
}
