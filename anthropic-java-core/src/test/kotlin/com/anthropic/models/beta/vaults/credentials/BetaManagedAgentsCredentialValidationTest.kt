// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCredentialValidationTest {

    @Test
    fun create() {
        val betaManagedAgentsCredentialValidation =
            BetaManagedAgentsCredentialValidation.builder()
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .hasRefreshToken(true)
                .mcpProbe(
                    BetaManagedAgentsMcpProbe.builder()
                        .httpResponse(
                            BetaManagedAgentsRefreshHttpResponse.builder()
                                .body("body")
                                .bodyTruncated(true)
                                .contentType("content_type")
                                .statusCode(0)
                                .build()
                        )
                        .method("method")
                        .build()
                )
                .refresh(
                    BetaManagedAgentsRefreshObject.builder()
                        .httpResponse(
                            BetaManagedAgentsRefreshHttpResponse.builder()
                                .body("body")
                                .bodyTruncated(true)
                                .contentType("content_type")
                                .statusCode(0)
                                .build()
                        )
                        .status(BetaManagedAgentsRefreshObject.Status.SUCCEEDED)
                        .build()
                )
                .status(BetaManagedAgentsCredentialValidationStatus.VALID)
                .type(BetaManagedAgentsCredentialValidation.Type.VAULT_CREDENTIAL_VALIDATION)
                .validatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .build()

        assertThat(betaManagedAgentsCredentialValidation.credentialId())
            .isEqualTo("vcrd_011CZkZEMt8gZan2iYOQfSkw")
        assertThat(betaManagedAgentsCredentialValidation.hasRefreshToken()).isEqualTo(true)
        assertThat(betaManagedAgentsCredentialValidation.mcpProbe())
            .contains(
                BetaManagedAgentsMcpProbe.builder()
                    .httpResponse(
                        BetaManagedAgentsRefreshHttpResponse.builder()
                            .body("body")
                            .bodyTruncated(true)
                            .contentType("content_type")
                            .statusCode(0)
                            .build()
                    )
                    .method("method")
                    .build()
            )
        assertThat(betaManagedAgentsCredentialValidation.refresh())
            .contains(
                BetaManagedAgentsRefreshObject.builder()
                    .httpResponse(
                        BetaManagedAgentsRefreshHttpResponse.builder()
                            .body("body")
                            .bodyTruncated(true)
                            .contentType("content_type")
                            .statusCode(0)
                            .build()
                    )
                    .status(BetaManagedAgentsRefreshObject.Status.SUCCEEDED)
                    .build()
            )
        assertThat(betaManagedAgentsCredentialValidation.status())
            .isEqualTo(BetaManagedAgentsCredentialValidationStatus.VALID)
        assertThat(betaManagedAgentsCredentialValidation.type())
            .isEqualTo(BetaManagedAgentsCredentialValidation.Type.VAULT_CREDENTIAL_VALIDATION)
        assertThat(betaManagedAgentsCredentialValidation.validatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsCredentialValidation.vaultId())
            .isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCredentialValidation =
            BetaManagedAgentsCredentialValidation.builder()
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .hasRefreshToken(true)
                .mcpProbe(
                    BetaManagedAgentsMcpProbe.builder()
                        .httpResponse(
                            BetaManagedAgentsRefreshHttpResponse.builder()
                                .body("body")
                                .bodyTruncated(true)
                                .contentType("content_type")
                                .statusCode(0)
                                .build()
                        )
                        .method("method")
                        .build()
                )
                .refresh(
                    BetaManagedAgentsRefreshObject.builder()
                        .httpResponse(
                            BetaManagedAgentsRefreshHttpResponse.builder()
                                .body("body")
                                .bodyTruncated(true)
                                .contentType("content_type")
                                .statusCode(0)
                                .build()
                        )
                        .status(BetaManagedAgentsRefreshObject.Status.SUCCEEDED)
                        .build()
                )
                .status(BetaManagedAgentsCredentialValidationStatus.VALID)
                .type(BetaManagedAgentsCredentialValidation.Type.VAULT_CREDENTIAL_VALIDATION)
                .validatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .build()

        val roundtrippedBetaManagedAgentsCredentialValidation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCredentialValidation),
                jacksonTypeRef<BetaManagedAgentsCredentialValidation>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCredentialValidation)
            .isEqualTo(betaManagedAgentsCredentialValidation)
    }
}
