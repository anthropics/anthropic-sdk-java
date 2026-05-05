// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionArchivedEventData =
            BetaWebhookSessionArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionArchivedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionArchivedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionArchivedEventData =
            BetaWebhookSessionArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionArchivedEventData),
                jacksonTypeRef<BetaWebhookSessionArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionArchivedEventData)
            .isEqualTo(betaWebhookSessionArchivedEventData)
    }
}
