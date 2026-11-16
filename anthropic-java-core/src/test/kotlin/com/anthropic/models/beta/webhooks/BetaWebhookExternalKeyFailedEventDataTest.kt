package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookExternalKeyFailedEventDataTest {

    @Test
    fun create() {
        val betaWebhookExternalKeyFailedEventData =
            BetaWebhookExternalKeyFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookExternalKeyFailedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookExternalKeyFailedEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookExternalKeyFailedEventData =
            BetaWebhookExternalKeyFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookExternalKeyFailedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookExternalKeyFailedEventData),
                jacksonTypeRef<BetaWebhookExternalKeyFailedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookExternalKeyFailedEventData)
            .isEqualTo(betaWebhookExternalKeyFailedEventData)
    }
}
