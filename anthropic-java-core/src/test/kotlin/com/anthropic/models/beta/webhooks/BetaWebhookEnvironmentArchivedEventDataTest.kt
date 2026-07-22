// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookEnvironmentArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookEnvironmentArchivedEventData =
            BetaWebhookEnvironmentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookEnvironmentArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookEnvironmentArchivedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookEnvironmentArchivedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEnvironmentArchivedEventData =
            BetaWebhookEnvironmentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookEnvironmentArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEnvironmentArchivedEventData),
                jacksonTypeRef<BetaWebhookEnvironmentArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEnvironmentArchivedEventData)
            .isEqualTo(betaWebhookEnvironmentArchivedEventData)
    }
}
