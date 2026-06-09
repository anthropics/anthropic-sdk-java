// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError =
            BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.Type
                        .WORKSPACE_ARCHIVED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.Type
                    .WORKSPACE_ARCHIVED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError =
            BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.Type
                        .WORKSPACE_ARCHIVED_ERROR
                )
                .build()

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
