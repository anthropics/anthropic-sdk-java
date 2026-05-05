// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookEventTest {

    @Test
    fun create() {
        val betaWebhookEvent =
            BetaWebhookEvent.builder()
                .id("wevt_011CZkZYZd9rLmz3ujAcsqEw")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .data(
                    BetaWebhookSessionStatusIdledEventData.builder()
                        .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                        .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                        .build()
                )
                .build()

        assertThat(betaWebhookEvent.id()).isEqualTo("wevt_011CZkZYZd9rLmz3ujAcsqEw")
        assertThat(betaWebhookEvent.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaWebhookEvent.data())
            .isEqualTo(
                BetaWebhookEventData.ofSessionStatusIdled(
                    BetaWebhookSessionStatusIdledEventData.builder()
                        .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                        .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEvent =
            BetaWebhookEvent.builder()
                .id("wevt_011CZkZYZd9rLmz3ujAcsqEw")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .data(
                    BetaWebhookSessionStatusIdledEventData.builder()
                        .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                        .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                        .build()
                )
                .build()

        val roundtrippedBetaWebhookEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEvent),
                jacksonTypeRef<BetaWebhookEvent>(),
            )

        assertThat(roundtrippedBetaWebhookEvent).isEqualTo(betaWebhookEvent)
    }
}
