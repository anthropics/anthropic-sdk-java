// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultArchivedDeploymentPausedReasonError =
            BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.Type
                        .VAULT_ARCHIVED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsVaultArchivedDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.Type.VAULT_ARCHIVED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultArchivedDeploymentPausedReasonError =
            BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.Type
                        .VAULT_ARCHIVED_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsVaultArchivedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsVaultArchivedDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsVaultArchivedDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVaultArchivedDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsVaultArchivedDeploymentPausedReasonError)
    }
}
