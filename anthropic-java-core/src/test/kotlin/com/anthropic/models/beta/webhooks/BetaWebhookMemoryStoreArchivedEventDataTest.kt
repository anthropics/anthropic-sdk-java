// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookMemoryStoreArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookMemoryStoreArchivedEventData =
            BetaWebhookMemoryStoreArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookMemoryStoreArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookMemoryStoreArchivedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookMemoryStoreArchivedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookMemoryStoreArchivedEventData =
            BetaWebhookMemoryStoreArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookMemoryStoreArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookMemoryStoreArchivedEventData),
                jacksonTypeRef<BetaWebhookMemoryStoreArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookMemoryStoreArchivedEventData)
            .isEqualTo(betaWebhookMemoryStoreArchivedEventData)
    }
}
