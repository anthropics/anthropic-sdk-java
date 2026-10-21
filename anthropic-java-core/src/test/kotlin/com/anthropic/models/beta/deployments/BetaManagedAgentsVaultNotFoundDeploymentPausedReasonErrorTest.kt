package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsVaultNotFoundDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsVaultNotFoundDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVaultNotFoundDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsVaultNotFoundDeploymentPausedReasonError)
    }
}
