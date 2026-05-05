// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultArchivedEventData =
            BetaWebhookVaultArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultArchivedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookVaultArchivedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultArchivedEventData =
            BetaWebhookVaultArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultArchivedEventData),
                jacksonTypeRef<BetaWebhookVaultArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultArchivedEventData)
            .isEqualTo(betaWebhookVaultArchivedEventData)
    }
}
