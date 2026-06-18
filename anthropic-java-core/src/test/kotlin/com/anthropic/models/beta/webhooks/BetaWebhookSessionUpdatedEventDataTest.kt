// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionUpdatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionUpdatedEventData =
            BetaWebhookSessionUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionUpdatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionUpdatedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookSessionUpdatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionUpdatedEventData =
            BetaWebhookSessionUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionUpdatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionUpdatedEventData),
                jacksonTypeRef<BetaWebhookSessionUpdatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionUpdatedEventData)
            .isEqualTo(betaWebhookSessionUpdatedEventData)
    }
}
