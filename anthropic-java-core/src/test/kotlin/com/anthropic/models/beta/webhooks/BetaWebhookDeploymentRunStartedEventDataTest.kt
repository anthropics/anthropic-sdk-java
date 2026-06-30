// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentRunStartedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentRunStartedEventData =
            BetaWebhookDeploymentRunStartedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentRunStartedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentRunStartedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentRunStartedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentRunStartedEventData =
            BetaWebhookDeploymentRunStartedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentRunStartedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentRunStartedEventData),
                jacksonTypeRef<BetaWebhookDeploymentRunStartedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentRunStartedEventData)
            .isEqualTo(betaWebhookDeploymentRunStartedEventData)
    }
}
