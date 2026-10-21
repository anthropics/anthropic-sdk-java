package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsOrganizationDisabledDeploymentPausedReasonError =
            BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsOrganizationDisabledDeploymentPausedReasonError =
            BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsOrganizationDisabledDeploymentPausedReasonError)
    }
}
