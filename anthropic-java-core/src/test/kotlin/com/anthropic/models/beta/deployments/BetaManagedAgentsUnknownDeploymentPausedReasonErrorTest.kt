package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnknownDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsUnknownDeploymentPausedReasonError =
            BetaManagedAgentsUnknownDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownDeploymentPausedReasonError =
            BetaManagedAgentsUnknownDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsUnknownDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUnknownDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsUnknownDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnknownDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsUnknownDeploymentPausedReasonError)
    }
}
