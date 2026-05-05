// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultDeletedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultDeletedEventData =
            BetaWebhookVaultDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultDeletedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultDeletedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookVaultDeletedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultDeletedEventData =
            BetaWebhookVaultDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultDeletedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultDeletedEventData),
                jacksonTypeRef<BetaWebhookVaultDeletedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultDeletedEventData)
            .isEqualTo(betaWebhookVaultDeletedEventData)
    }
}
