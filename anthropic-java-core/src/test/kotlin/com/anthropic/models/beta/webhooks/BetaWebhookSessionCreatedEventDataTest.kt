// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionCreatedEventData =
            BetaWebhookSessionCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionCreatedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookSessionCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionCreatedEventData =
            BetaWebhookSessionCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionCreatedEventData),
                jacksonTypeRef<BetaWebhookSessionCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionCreatedEventData)
            .isEqualTo(betaWebhookSessionCreatedEventData)
    }
}
