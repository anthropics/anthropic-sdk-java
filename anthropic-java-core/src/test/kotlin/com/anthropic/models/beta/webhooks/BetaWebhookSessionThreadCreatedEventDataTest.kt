// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionThreadCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionThreadCreatedEventData =
            BetaWebhookSessionThreadCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionThreadCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionThreadCreatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionThreadCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionThreadCreatedEventData =
            BetaWebhookSessionThreadCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionThreadCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionThreadCreatedEventData),
                jacksonTypeRef<BetaWebhookSessionThreadCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionThreadCreatedEventData)
            .isEqualTo(betaWebhookSessionThreadCreatedEventData)
    }
}
