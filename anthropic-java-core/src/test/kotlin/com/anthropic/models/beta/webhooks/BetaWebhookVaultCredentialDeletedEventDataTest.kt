// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultCredentialDeletedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultCredentialDeletedEventData =
            BetaWebhookVaultCredentialDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultCredentialDeletedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultCredentialDeletedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookVaultCredentialDeletedEventData.vaultId()).isEqualTo("vault_id")
        assertThat(betaWebhookVaultCredentialDeletedEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultCredentialDeletedEventData =
            BetaWebhookVaultCredentialDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultCredentialDeletedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultCredentialDeletedEventData),
                jacksonTypeRef<BetaWebhookVaultCredentialDeletedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultCredentialDeletedEventData)
            .isEqualTo(betaWebhookVaultCredentialDeletedEventData)
    }
}
