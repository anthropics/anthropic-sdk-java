// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnknownDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsUnknownDeploymentPausedReasonError =
            BetaManagedAgentsUnknownDeploymentPausedReasonError.builder()
                .type(BetaManagedAgentsUnknownDeploymentPausedReasonError.Type.UNKNOWN_ERROR)
                .build()

        assertThat(betaManagedAgentsUnknownDeploymentPausedReasonError.type())
            .isEqualTo(BetaManagedAgentsUnknownDeploymentPausedReasonError.Type.UNKNOWN_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownDeploymentPausedReasonError =
            BetaManagedAgentsUnknownDeploymentPausedReasonError.builder()
                .type(BetaManagedAgentsUnknownDeploymentPausedReasonError.Type.UNKNOWN_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsUnknownDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUnknownDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsUnknownDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnknownDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsUnknownDeploymentPausedReasonError)
    }
}
