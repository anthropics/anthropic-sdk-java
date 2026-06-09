// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.Type
                        .AGENT_ARCHIVED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsAgentArchivedDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.Type.AGENT_ARCHIVED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.Type
                        .AGENT_ARCHIVED_ERROR
                )
                .build()

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
