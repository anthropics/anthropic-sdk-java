// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionStatusRunStartedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionStatusRunStartedEventData =
            BetaWebhookSessionStatusRunStartedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionStatusRunStartedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionStatusRunStartedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionStatusRunStartedEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionStatusRunStartedEventData =
            BetaWebhookSessionStatusRunStartedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionStatusRunStartedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionStatusRunStartedEventData),
                jacksonTypeRef<BetaWebhookSessionStatusRunStartedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionStatusRunStartedEventData)
            .isEqualTo(betaWebhookSessionStatusRunStartedEventData)
    }
}
