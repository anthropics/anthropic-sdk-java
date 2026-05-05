// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultCredentialArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultCredentialArchivedEventData =
            BetaWebhookVaultCredentialArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultCredentialArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultCredentialArchivedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookVaultCredentialArchivedEventData.vaultId()).isEqualTo("vault_id")
        assertThat(betaWebhookVaultCredentialArchivedEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultCredentialArchivedEventData =
            BetaWebhookVaultCredentialArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultCredentialArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultCredentialArchivedEventData),
                jacksonTypeRef<BetaWebhookVaultCredentialArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultCredentialArchivedEventData)
            .isEqualTo(betaWebhookVaultCredentialArchivedEventData)
    }
}
