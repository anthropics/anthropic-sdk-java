// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionStatusTerminatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionStatusTerminatedEventData =
            BetaWebhookSessionStatusTerminatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionStatusTerminatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionStatusTerminatedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionStatusTerminatedEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionStatusTerminatedEventData =
            BetaWebhookSessionStatusTerminatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionStatusTerminatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionStatusTerminatedEventData),
                jacksonTypeRef<BetaWebhookSessionStatusTerminatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionStatusTerminatedEventData)
            .isEqualTo(betaWebhookSessionStatusTerminatedEventData)
    }
}
