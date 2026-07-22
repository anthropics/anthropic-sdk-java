// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookEnvironmentCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookEnvironmentCreatedEventData =
            BetaWebhookEnvironmentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookEnvironmentCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookEnvironmentCreatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookEnvironmentCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEnvironmentCreatedEventData =
            BetaWebhookEnvironmentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookEnvironmentCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEnvironmentCreatedEventData),
                jacksonTypeRef<BetaWebhookEnvironmentCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEnvironmentCreatedEventData)
            .isEqualTo(betaWebhookEnvironmentCreatedEventData)
    }
}
