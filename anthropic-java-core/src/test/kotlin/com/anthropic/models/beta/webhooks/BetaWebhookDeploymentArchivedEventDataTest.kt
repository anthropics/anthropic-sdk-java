// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentArchivedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentArchivedEventData =
            BetaWebhookDeploymentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentArchivedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentArchivedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentArchivedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentArchivedEventData =
            BetaWebhookDeploymentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentArchivedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentArchivedEventData),
                jacksonTypeRef<BetaWebhookDeploymentArchivedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentArchivedEventData)
            .isEqualTo(betaWebhookDeploymentArchivedEventData)
    }
}
