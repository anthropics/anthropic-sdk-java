// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentCreatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentCreatedEventData =
            BetaWebhookDeploymentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentCreatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentCreatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentCreatedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentCreatedEventData =
            BetaWebhookDeploymentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentCreatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentCreatedEventData),
                jacksonTypeRef<BetaWebhookDeploymentCreatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentCreatedEventData)
            .isEqualTo(betaWebhookDeploymentCreatedEventData)
    }
}
