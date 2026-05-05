// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultCredentialRefreshFailedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultCredentialRefreshFailedEventData =
            BetaWebhookVaultCredentialRefreshFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultCredentialRefreshFailedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultCredentialRefreshFailedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookVaultCredentialRefreshFailedEventData.vaultId()).isEqualTo("vault_id")
        assertThat(betaWebhookVaultCredentialRefreshFailedEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultCredentialRefreshFailedEventData =
            BetaWebhookVaultCredentialRefreshFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultCredentialRefreshFailedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultCredentialRefreshFailedEventData),
                jacksonTypeRef<BetaWebhookVaultCredentialRefreshFailedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultCredentialRefreshFailedEventData)
            .isEqualTo(betaWebhookVaultCredentialRefreshFailedEventData)
    }
}
