package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder().build()

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
