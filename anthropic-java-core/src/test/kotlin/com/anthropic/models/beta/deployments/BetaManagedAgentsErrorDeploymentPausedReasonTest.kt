package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsErrorDeploymentPausedReasonTest {

    @Test
    fun create() {
        val betaManagedAgentsErrorDeploymentPausedReason =
            BetaManagedAgentsErrorDeploymentPausedReason.builder()
                .error(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsErrorDeploymentPausedReason.error())
            .isEqualTo(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsErrorDeploymentPausedReason =
            BetaManagedAgentsErrorDeploymentPausedReason.builder()
                .error(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsErrorDeploymentPausedReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsErrorDeploymentPausedReason),
                jacksonTypeRef<BetaManagedAgentsErrorDeploymentPausedReason>(),
            )

        assertThat(roundtrippedBetaManagedAgentsErrorDeploymentPausedReason)
            .isEqualTo(betaManagedAgentsErrorDeploymentPausedReason)
    }
}
