// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionThreadIdledEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionThreadIdledEventData =
            BetaWebhookSessionThreadIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionThreadIdledEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionThreadIdledEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionThreadIdledEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionThreadIdledEventData =
            BetaWebhookSessionThreadIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionThreadIdledEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionThreadIdledEventData),
                jacksonTypeRef<BetaWebhookSessionThreadIdledEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionThreadIdledEventData)
            .isEqualTo(betaWebhookSessionThreadIdledEventData)
    }
}
