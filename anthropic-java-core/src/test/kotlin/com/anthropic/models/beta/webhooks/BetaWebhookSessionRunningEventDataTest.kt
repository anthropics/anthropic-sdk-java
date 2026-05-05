// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionRunningEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionRunningEventData =
            BetaWebhookSessionRunningEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionRunningEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionRunningEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookSessionRunningEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionRunningEventData =
            BetaWebhookSessionRunningEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionRunningEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionRunningEventData),
                jacksonTypeRef<BetaWebhookSessionRunningEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionRunningEventData)
            .isEqualTo(betaWebhookSessionRunningEventData)
    }
}
