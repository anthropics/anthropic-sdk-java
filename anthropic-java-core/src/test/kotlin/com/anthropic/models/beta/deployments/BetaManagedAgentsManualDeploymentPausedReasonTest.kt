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
            BetaManagedAgentsManualDeploymentPausedReason.builder()
                .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                .build()

        assertThat(betaManagedAgentsManualDeploymentPausedReason.type())
            .isEqualTo(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsManualDeploymentPausedReason =
            BetaManagedAgentsManualDeploymentPausedReason.builder()
                .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                .build()

        val roundtrippedBetaManagedAgentsManualDeploymentPausedReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsManualDeploymentPausedReason),
                jacksonTypeRef<BetaManagedAgentsManualDeploymentPausedReason>(),
            )

        assertThat(roundtrippedBetaManagedAgentsManualDeploymentPausedReason)
            .isEqualTo(betaManagedAgentsManualDeploymentPausedReason)
    }
}
