package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsManualDeploymentPausedReasonTest {

    @Test
    fun create() {
        val betaManagedAgentsManualDeploymentPausedReason =
            BetaManagedAgentsManualDeploymentPausedReason.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsManualDeploymentPausedReason =
            BetaManagedAgentsManualDeploymentPausedReason.builder().build()

        val roundtrippedBetaManagedAgentsManualDeploymentPausedReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsManualDeploymentPausedReason),
                jacksonTypeRef<BetaManagedAgentsManualDeploymentPausedReason>(),
            )

        assertThat(roundtrippedBetaManagedAgentsManualDeploymentPausedReason)
            .isEqualTo(betaManagedAgentsManualDeploymentPausedReason)
    }
}
