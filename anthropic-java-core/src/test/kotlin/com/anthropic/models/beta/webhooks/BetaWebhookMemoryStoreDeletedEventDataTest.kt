// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookMemoryStoreDeletedEventDataTest {

    @Test
    fun create() {
        val betaWebhookMemoryStoreDeletedEventData =
            BetaWebhookMemoryStoreDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookMemoryStoreDeletedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookMemoryStoreDeletedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookMemoryStoreDeletedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookMemoryStoreDeletedEventData =
            BetaWebhookMemoryStoreDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookMemoryStoreDeletedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookMemoryStoreDeletedEventData),
                jacksonTypeRef<BetaWebhookMemoryStoreDeletedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookMemoryStoreDeletedEventData)
            .isEqualTo(betaWebhookMemoryStoreDeletedEventData)
    }
}
