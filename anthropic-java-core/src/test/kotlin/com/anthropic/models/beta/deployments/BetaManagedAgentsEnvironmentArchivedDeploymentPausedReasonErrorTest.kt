// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                        .ENVIRONMENT_ARCHIVED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                    .ENVIRONMENT_ARCHIVED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                        .ENVIRONMENT_ARCHIVED_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError)
    }
}
