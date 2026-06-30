// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookDeploymentDeletedEventDataTest {

    @Test
    fun create() {
        val betaWebhookDeploymentDeletedEventData =
            BetaWebhookDeploymentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookDeploymentDeletedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookDeploymentDeletedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookDeploymentDeletedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookDeploymentDeletedEventData =
            BetaWebhookDeploymentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookDeploymentDeletedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookDeploymentDeletedEventData),
                jacksonTypeRef<BetaWebhookDeploymentDeletedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookDeploymentDeletedEventData)
            .isEqualTo(betaWebhookDeploymentDeletedEventData)
    }
}
