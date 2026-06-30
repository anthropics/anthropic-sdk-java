// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookAgentCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookAgentCreatedEventData =
            BetaWebhookAgentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookAgentCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookAgentCreatedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookAgentCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookAgentCreatedEventData =
            BetaWebhookAgentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookAgentCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookAgentCreatedEventData),
                jacksonTypeRef<BetaWebhookAgentCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookAgentCreatedEventData)
            .isEqualTo(betaWebhookAgentCreatedEventData)
    }
}
