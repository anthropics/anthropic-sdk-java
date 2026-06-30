// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookEnvironmentDeletedEventDataTest {

    @Test
    fun create() {
        val betaWebhookEnvironmentDeletedEventData =
            BetaWebhookEnvironmentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .type(BetaWebhookEnvironmentDeletedEventType.ENVIRONMENT_DELETED)
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookEnvironmentDeletedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookEnvironmentDeletedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookEnvironmentDeletedEventData.type())
            .isEqualTo(BetaWebhookEnvironmentDeletedEventType.ENVIRONMENT_DELETED)
        assertThat(betaWebhookEnvironmentDeletedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEnvironmentDeletedEventData =
            BetaWebhookEnvironmentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .type(BetaWebhookEnvironmentDeletedEventType.ENVIRONMENT_DELETED)
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookEnvironmentDeletedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEnvironmentDeletedEventData),
                jacksonTypeRef<BetaWebhookEnvironmentDeletedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEnvironmentDeletedEventData)
            .isEqualTo(betaWebhookEnvironmentDeletedEventData)
    }
}
