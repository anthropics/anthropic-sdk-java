// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookAgentArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookAgentArchivedEventData =
            BetaWebhookAgentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookAgentArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookAgentArchivedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookAgentArchivedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookAgentArchivedEventData =
            BetaWebhookAgentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookAgentArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookAgentArchivedEventData),
                jacksonTypeRef<BetaWebhookAgentArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookAgentArchivedEventData)
            .isEqualTo(betaWebhookAgentArchivedEventData)
    }
}
