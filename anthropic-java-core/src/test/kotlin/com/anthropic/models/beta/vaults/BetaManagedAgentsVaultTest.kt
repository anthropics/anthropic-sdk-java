// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultTest {

    @Test
    fun create() {
        val betaManagedAgentsVault =
            BetaManagedAgentsVault.builder()
                .id("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .archivedAt(null)
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .displayName("Example vault")
                .metadata(
                    BetaManagedAgentsVault.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .type(BetaManagedAgentsVault.Type.VAULT)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        assertThat(betaManagedAgentsVault.id()).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        assertThat(betaManagedAgentsVault.archivedAt()).isEmpty
        assertThat(betaManagedAgentsVault.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsVault.displayName()).isEqualTo("Example vault")
        assertThat(betaManagedAgentsVault.metadata())
            .isEqualTo(
                BetaManagedAgentsVault.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
        assertThat(betaManagedAgentsVault.type()).isEqualTo(BetaManagedAgentsVault.Type.VAULT)
        assertThat(betaManagedAgentsVault.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVault =
            BetaManagedAgentsVault.builder()
                .id("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .archivedAt(null)
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .displayName("Example vault")
                .metadata(
                    BetaManagedAgentsVault.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .type(BetaManagedAgentsVault.Type.VAULT)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val roundtrippedBetaManagedAgentsVault =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsVault),
                jacksonTypeRef<BetaManagedAgentsVault>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVault).isEqualTo(betaManagedAgentsVault)
    }
}
