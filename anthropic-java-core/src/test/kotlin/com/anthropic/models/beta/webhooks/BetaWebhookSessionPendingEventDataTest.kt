// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionPendingEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionPendingEventData =
            BetaWebhookSessionPendingEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionPendingEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionPendingEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookSessionPendingEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionPendingEventData =
            BetaWebhookSessionPendingEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionPendingEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionPendingEventData),
                jacksonTypeRef<BetaWebhookSessionPendingEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionPendingEventData)
            .isEqualTo(betaWebhookSessionPendingEventData)
    }
}
