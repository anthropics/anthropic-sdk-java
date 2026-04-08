// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCredentialTest {

    @Test
    fun create() {
        val betaManagedAgentsCredential =
            BetaManagedAgentsCredential.builder()
                .id("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .archivedAt(null)
                .staticBearerAuth("https://example-server.modelcontextprotocol.io/sse")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .metadata(
                    BetaManagedAgentsCredential.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .type(BetaManagedAgentsCredential.Type.VAULT_CREDENTIAL)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .displayName("Example credential")
                .build()

        assertThat(betaManagedAgentsCredential.id()).isEqualTo("vcrd_011CZkZEMt8gZan2iYOQfSkw")
        assertThat(betaManagedAgentsCredential.archivedAt()).isEmpty
        assertThat(betaManagedAgentsCredential.auth())
            .isEqualTo(
                BetaManagedAgentsCredential.Auth.ofStaticBearer(
                    BetaManagedAgentsStaticBearerAuthResponse.builder()
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerAuthResponse.Type.STATIC_BEARER)
                        .build()
                )
            )
        assertThat(betaManagedAgentsCredential.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsCredential.metadata())
            .isEqualTo(
                BetaManagedAgentsCredential.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
        assertThat(betaManagedAgentsCredential.type())
            .isEqualTo(BetaManagedAgentsCredential.Type.VAULT_CREDENTIAL)
        assertThat(betaManagedAgentsCredential.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsCredential.vaultId()).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        assertThat(betaManagedAgentsCredential.displayName()).contains("Example credential")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCredential =
            BetaManagedAgentsCredential.builder()
                .id("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .archivedAt(null)
                .staticBearerAuth("https://example-server.modelcontextprotocol.io/sse")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .metadata(
                    BetaManagedAgentsCredential.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .type(BetaManagedAgentsCredential.Type.VAULT_CREDENTIAL)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .displayName("Example credential")
                .build()

        val roundtrippedBetaManagedAgentsCredential =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCredential),
                jacksonTypeRef<BetaManagedAgentsCredential>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCredential).isEqualTo(betaManagedAgentsCredential)
    }
}
