// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsFileNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.Type
                        .FILE_NOT_FOUND_ERROR
                )
                .build()

        assertThat(betaManagedAgentsFileNotFoundDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.Type.FILE_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.Type
                        .FILE_NOT_FOUND_ERROR
                )
                .build()

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
