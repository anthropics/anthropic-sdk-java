// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsManualDeploymentPausedReasonTest {

    @Test
    fun create() {
        val betaManagedAgentsManualDeploymentPausedReason =
            BetaManagedAgentsManualDeploymentPausedReason.of(
                BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL
            )

        assertThat(betaManagedAgentsManualDeploymentPausedReason.type())
            .isEqualTo(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsManualDeploymentPausedReason =
            BetaManagedAgentsManualDeploymentPausedReason.of(
                BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL
            )

        val roundtrippedBetaManagedAgentsManualDeploymentPausedReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsManualDeploymentPausedReason),
                jacksonTypeRef<BetaManagedAgentsManualDeploymentPausedReason>(),
            )

        assertThat(roundtrippedBetaManagedAgentsManualDeploymentPausedReason)
            .isEqualTo(betaManagedAgentsManualDeploymentPausedReason)
    }
}
