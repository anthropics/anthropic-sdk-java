// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.Type
                        .ENVIRONMENT_NOT_FOUND_ERROR
                )
                .build()

        assertThat(betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.Type
                    .ENVIRONMENT_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.Type
                        .ENVIRONMENT_NOT_FOUND_ERROR
                )
                .build()

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
