// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultCredentialCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultCredentialCreatedEventData =
            BetaWebhookVaultCredentialCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultCredentialCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultCredentialCreatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookVaultCredentialCreatedEventData.vaultId()).isEqualTo("vault_id")
        assertThat(betaWebhookVaultCredentialCreatedEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultCredentialCreatedEventData =
            BetaWebhookVaultCredentialCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultCredentialCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultCredentialCreatedEventData),
                jacksonTypeRef<BetaWebhookVaultCredentialCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultCredentialCreatedEventData)
            .isEqualTo(betaWebhookVaultCredentialCreatedEventData)
    }
}
