// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentRunFailedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentRunFailedEventData =
            BetaWebhookDeploymentRunFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentRunFailedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentRunFailedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentRunFailedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentRunFailedEventData =
            BetaWebhookDeploymentRunFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentRunFailedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentRunFailedEventData),
                jacksonTypeRef<BetaWebhookDeploymentRunFailedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentRunFailedEventData)
            .isEqualTo(betaWebhookDeploymentRunFailedEventData)
    }
}
