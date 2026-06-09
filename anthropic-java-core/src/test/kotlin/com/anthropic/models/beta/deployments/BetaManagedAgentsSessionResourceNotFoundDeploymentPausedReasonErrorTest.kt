// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.Type
                        .SESSION_RESOURCE_NOT_FOUND_ERROR
                )
                .build()

        assertThat(betaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.Type
                    .SESSION_RESOURCE_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.Type
                        .SESSION_RESOURCE_NOT_FOUND_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
                ),
                jacksonTypeRef<
                    BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
                >(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError)
    }
}
