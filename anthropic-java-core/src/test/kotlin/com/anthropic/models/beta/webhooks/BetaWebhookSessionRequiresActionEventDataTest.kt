// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionRequiresActionEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionRequiresActionEventData =
            BetaWebhookSessionRequiresActionEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionRequiresActionEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionRequiresActionEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionRequiresActionEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionRequiresActionEventData =
            BetaWebhookSessionRequiresActionEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionRequiresActionEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionRequiresActionEventData),
                jacksonTypeRef<BetaWebhookSessionRequiresActionEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionRequiresActionEventData)
            .isEqualTo(betaWebhookSessionRequiresActionEventData)
    }
}
