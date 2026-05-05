// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionStatusScheduledEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionStatusScheduledEventData =
            BetaWebhookSessionStatusScheduledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionStatusScheduledEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionStatusScheduledEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionStatusScheduledEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionStatusScheduledEventData =
            BetaWebhookSessionStatusScheduledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionStatusScheduledEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionStatusScheduledEventData),
                jacksonTypeRef<BetaWebhookSessionStatusScheduledEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionStatusScheduledEventData)
            .isEqualTo(betaWebhookSessionStatusScheduledEventData)
    }
}
