// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError =
            BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.Type
                        .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                )
                .build()

        assertThat(
                betaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.type()
            )
            .isEqualTo(
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.Type
                    .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError =
            BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.Type
                        .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
                ),
                jacksonTypeRef<
                    BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
                >(),
            )

        assertThat(
                roundtrippedBetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
            )
            .isEqualTo(betaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError)
    }
}
