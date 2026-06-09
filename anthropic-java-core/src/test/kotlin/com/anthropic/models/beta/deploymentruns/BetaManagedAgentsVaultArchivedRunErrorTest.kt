// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultArchivedRunError =
            BetaManagedAgentsVaultArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsVaultArchivedRunError.Type.VAULT_ARCHIVED_ERROR)
                .build()

        assertThat(betaManagedAgentsVaultArchivedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsVaultArchivedRunError.type())
            .isEqualTo(BetaManagedAgentsVaultArchivedRunError.Type.VAULT_ARCHIVED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultArchivedRunError =
            BetaManagedAgentsVaultArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsVaultArchivedRunError.Type.VAULT_ARCHIVED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsVaultArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsVaultArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsVaultArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVaultArchivedRunError)
            .isEqualTo(betaManagedAgentsVaultArchivedRunError)
    }
}
