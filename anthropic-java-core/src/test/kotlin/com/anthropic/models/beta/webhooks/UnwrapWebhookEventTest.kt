// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UnwrapWebhookEventTest {

    @Test
    fun create() {
        val unwrapWebhookEvent =
            UnwrapWebhookEvent.builder()
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

        assertThat(unwrapWebhookEvent.id()).isEqualTo("wevt_011CZkZYZd9rLmz3ujAcsqEw")
        assertThat(unwrapWebhookEvent.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(unwrapWebhookEvent.data())
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
        val unwrapWebhookEvent =
            UnwrapWebhookEvent.builder()
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

        val roundtrippedUnwrapWebhookEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(unwrapWebhookEvent),
                jacksonTypeRef<UnwrapWebhookEvent>(),
            )

        assertThat(roundtrippedUnwrapWebhookEvent).isEqualTo(unwrapWebhookEvent)
    }
}
