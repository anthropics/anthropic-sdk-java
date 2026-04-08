// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedVaultTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedVault =
            BetaManagedAgentsDeletedVault.builder()
                .id("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .type(BetaManagedAgentsDeletedVault.Type.VAULT_DELETED)
                .build()

        assertThat(betaManagedAgentsDeletedVault.id()).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        assertThat(betaManagedAgentsDeletedVault.type())
            .isEqualTo(BetaManagedAgentsDeletedVault.Type.VAULT_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedVault =
            BetaManagedAgentsDeletedVault.builder()
                .id("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .type(BetaManagedAgentsDeletedVault.Type.VAULT_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsDeletedVault =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedVault),
                jacksonTypeRef<BetaManagedAgentsDeletedVault>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedVault)
            .isEqualTo(betaManagedAgentsDeletedVault)
    }
}
