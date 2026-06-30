// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentUpdatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentUpdatedEventData =
            BetaWebhookDeploymentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentUpdatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentUpdatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentUpdatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentUpdatedEventData =
            BetaWebhookDeploymentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentUpdatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentUpdatedEventData),
                jacksonTypeRef<BetaWebhookDeploymentUpdatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentUpdatedEventData)
            .isEqualTo(betaWebhookDeploymentUpdatedEventData)
    }
}
