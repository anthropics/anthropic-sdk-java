// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionDeletedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionDeletedEventData =
            BetaWebhookSessionDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionDeletedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionDeletedEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookSessionDeletedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionDeletedEventData =
            BetaWebhookSessionDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionDeletedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionDeletedEventData),
                jacksonTypeRef<BetaWebhookSessionDeletedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionDeletedEventData)
            .isEqualTo(betaWebhookSessionDeletedEventData)
    }
}
