// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VaultListPageResponseTest {

    @Test
    fun create() {
        val vaultListPageResponse =
            VaultListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(vaultListPageResponse.data().getOrNull())
            .containsExactly(
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
            )
        assertThat(vaultListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val vaultListPageResponse =
            VaultListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedVaultListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(vaultListPageResponse),
                jacksonTypeRef<VaultListPageResponse>(),
            )

        assertThat(roundtrippedVaultListPageResponse).isEqualTo(vaultListPageResponse)
    }
}
