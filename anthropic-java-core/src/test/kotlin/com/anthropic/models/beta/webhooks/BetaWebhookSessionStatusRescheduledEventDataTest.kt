// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionStatusRescheduledEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionStatusRescheduledEventData =
            BetaWebhookSessionStatusRescheduledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionStatusRescheduledEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionStatusRescheduledEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionStatusRescheduledEventData.workspaceId())
            .isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionStatusRescheduledEventData =
            BetaWebhookSessionStatusRescheduledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionStatusRescheduledEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionStatusRescheduledEventData),
                jacksonTypeRef<BetaWebhookSessionStatusRescheduledEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionStatusRescheduledEventData)
            .isEqualTo(betaWebhookSessionStatusRescheduledEventData)
    }
}
