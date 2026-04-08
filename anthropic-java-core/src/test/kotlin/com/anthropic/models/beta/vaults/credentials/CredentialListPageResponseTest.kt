// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialListPageResponseTest {

    @Test
    fun create() {
        val credentialListPageResponse =
            CredentialListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(credentialListPageResponse.data().getOrNull())
            .containsExactly(
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
            )
        assertThat(credentialListPageResponse.nextPage())
            .contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val credentialListPageResponse =
            CredentialListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedCredentialListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(credentialListPageResponse),
                jacksonTypeRef<CredentialListPageResponse>(),
            )

        assertThat(roundtrippedCredentialListPageResponse).isEqualTo(credentialListPageResponse)
    }
}
