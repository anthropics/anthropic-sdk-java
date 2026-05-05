// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionOutcomeEvaluationEndedEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionOutcomeEvaluationEndedEventData =
            BetaWebhookSessionOutcomeEvaluationEndedEventData.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        assertThat(betaWebhookSessionOutcomeEvaluationEndedEventData.id())
            .isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(betaWebhookSessionOutcomeEvaluationEndedEventData.organizationId())
            .isEqualTo("org_011CZkZZAe0sMna4vkBdtrfx")
        assertThat(betaWebhookSessionOutcomeEvaluationEndedEventData.workspaceId())
            .isEqualTo("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionOutcomeEvaluationEndedEventData =
            BetaWebhookSessionOutcomeEvaluationEndedEventData.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val roundtrippedBetaWebhookSessionOutcomeEvaluationEndedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionOutcomeEvaluationEndedEventData),
                jacksonTypeRef<BetaWebhookSessionOutcomeEvaluationEndedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionOutcomeEvaluationEndedEventData)
            .isEqualTo(betaWebhookSessionOutcomeEvaluationEndedEventData)
    }
}
