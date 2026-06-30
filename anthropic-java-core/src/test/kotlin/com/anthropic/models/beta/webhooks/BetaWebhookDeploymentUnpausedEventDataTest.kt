// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentUnpausedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentUnpausedEventData =
            BetaWebhookDeploymentUnpausedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentUnpausedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentUnpausedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentUnpausedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentUnpausedEventData =
            BetaWebhookDeploymentUnpausedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentUnpausedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentUnpausedEventData),
                jacksonTypeRef<BetaWebhookDeploymentUnpausedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentUnpausedEventData)
            .isEqualTo(betaWebhookDeploymentUnpausedEventData)
    }
}
