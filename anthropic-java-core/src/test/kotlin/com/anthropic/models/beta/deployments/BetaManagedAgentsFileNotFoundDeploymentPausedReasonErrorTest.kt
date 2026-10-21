package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsFileNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsFileNotFoundDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsFileNotFoundDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsFileNotFoundDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileNotFoundDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsFileNotFoundDeploymentPausedReasonError)
    }
}
