// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookVaultCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookVaultCreatedEventData =
            BetaWebhookVaultCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookVaultCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookVaultCreatedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookVaultCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookVaultCreatedEventData =
            BetaWebhookVaultCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookVaultCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookVaultCreatedEventData),
                jacksonTypeRef<BetaWebhookVaultCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookVaultCreatedEventData)
            .isEqualTo(betaWebhookVaultCreatedEventData)
    }
}
