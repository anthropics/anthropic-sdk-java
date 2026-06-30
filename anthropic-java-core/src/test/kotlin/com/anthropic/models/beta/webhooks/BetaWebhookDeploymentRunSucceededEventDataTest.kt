// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentRunSucceededEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentRunSucceededEventData =
            BetaWebhookDeploymentRunSucceededEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentRunSucceededEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentRunSucceededEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentRunSucceededEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentRunSucceededEventData =
            BetaWebhookDeploymentRunSucceededEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentRunSucceededEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentRunSucceededEventData),
                jacksonTypeRef<BetaWebhookDeploymentRunSucceededEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentRunSucceededEventData)
            .isEqualTo(betaWebhookDeploymentRunSucceededEventData)
    }
}
