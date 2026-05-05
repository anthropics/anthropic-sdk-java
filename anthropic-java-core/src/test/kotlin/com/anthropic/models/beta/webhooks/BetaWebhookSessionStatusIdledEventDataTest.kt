// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookSessionStatusIdledEventDataTest {

    @Test
    fun create() {
        val betaWebhookSessionStatusIdledEventData =
            BetaWebhookSessionStatusIdledEventData.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        assertThat(betaWebhookSessionStatusIdledEventData.id())
            .isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(betaWebhookSessionStatusIdledEventData.organizationId())
            .isEqualTo("org_011CZkZZAe0sMna4vkBdtrfx")
        assertThat(betaWebhookSessionStatusIdledEventData.workspaceId())
            .isEqualTo("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookSessionStatusIdledEventData =
            BetaWebhookSessionStatusIdledEventData.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val roundtrippedBetaWebhookSessionStatusIdledEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookSessionStatusIdledEventData),
                jacksonTypeRef<BetaWebhookSessionStatusIdledEventData>(),
            )

        assertThat(roundtrippedBetaWebhookSessionStatusIdledEventData)
            .isEqualTo(betaWebhookSessionStatusIdledEventData)
    }
}
