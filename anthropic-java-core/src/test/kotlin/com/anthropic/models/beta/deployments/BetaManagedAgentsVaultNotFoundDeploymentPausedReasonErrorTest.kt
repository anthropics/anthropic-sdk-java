// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.Type
                        .VAULT_NOT_FOUND_ERROR
                )
                .build()

        assertThat(betaManagedAgentsVaultNotFoundDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.Type.VAULT_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.Type
                        .VAULT_NOT_FOUND_ERROR
                )
                .build()

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
