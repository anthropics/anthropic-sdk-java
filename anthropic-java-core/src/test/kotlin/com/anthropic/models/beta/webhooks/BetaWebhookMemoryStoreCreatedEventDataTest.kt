// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookMemoryStoreCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookMemoryStoreCreatedEventData =
            BetaWebhookMemoryStoreCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookMemoryStoreCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookMemoryStoreCreatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookMemoryStoreCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookMemoryStoreCreatedEventData =
            BetaWebhookMemoryStoreCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookMemoryStoreCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookMemoryStoreCreatedEventData),
                jacksonTypeRef<BetaWebhookMemoryStoreCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookMemoryStoreCreatedEventData)
            .isEqualTo(betaWebhookMemoryStoreCreatedEventData)
    }
}
