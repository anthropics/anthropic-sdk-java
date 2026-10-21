package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError)
    }
}
