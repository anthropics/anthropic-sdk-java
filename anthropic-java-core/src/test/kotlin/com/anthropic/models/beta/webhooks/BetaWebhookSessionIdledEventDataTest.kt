// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionIdledEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionIdledEventData =
            BetaWebhookSessionIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionIdledEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionIdledEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookSessionIdledEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionIdledEventData =
            BetaWebhookSessionIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionIdledEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionIdledEventData),
                jacksonTypeRef<BetaWebhookSessionIdledEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionIdledEventData)
            .isEqualTo(betaWebhookSessionIdledEventData)
    }
}
