// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookAgentUpdatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookAgentUpdatedEventData =
            BetaWebhookAgentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookAgentUpdatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookAgentUpdatedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookAgentUpdatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookAgentUpdatedEventData =
            BetaWebhookAgentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookAgentUpdatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookAgentUpdatedEventData),
                jacksonTypeRef<BetaWebhookAgentUpdatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookAgentUpdatedEventData)
            .isEqualTo(betaWebhookAgentUpdatedEventData)
    }
}
