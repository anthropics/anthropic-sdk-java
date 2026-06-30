// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookEnvironmentUpdatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookEnvironmentUpdatedEventData =
            BetaWebhookEnvironmentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookEnvironmentUpdatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookEnvironmentUpdatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookEnvironmentUpdatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEnvironmentUpdatedEventData =
            BetaWebhookEnvironmentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookEnvironmentUpdatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEnvironmentUpdatedEventData),
                jacksonTypeRef<BetaWebhookEnvironmentUpdatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEnvironmentUpdatedEventData)
            .isEqualTo(betaWebhookEnvironmentUpdatedEventData)
    }
}
