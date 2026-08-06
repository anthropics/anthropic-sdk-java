// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionBudgetReachedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionBudgetReachedEventData =
            BetaWebhookSessionBudgetReachedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaWebhookSessionBudgetReachedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookSessionBudgetReachedEventData.organizationId())
            .isEqualTo("organization_id")
        assertThat(betaWebhookSessionBudgetReachedEventData.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionBudgetReachedEventData =
            BetaWebhookSessionBudgetReachedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaWebhookSessionBudgetReachedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionBudgetReachedEventData),
                jacksonTypeRef<BetaWebhookSessionBudgetReachedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionBudgetReachedEventData)
            .isEqualTo(betaWebhookSessionBudgetReachedEventData)
    }
}
