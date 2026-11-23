package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookExternalStorageFailedEventDataTest {

    @Test
    fun create() {
        val betaWebhookExternalStorageFailedEventData =
            BetaWebhookExternalStorageFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookExternalStorageFailedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookExternalStorageFailedEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookExternalStorageFailedEventData =
            BetaWebhookExternalStorageFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookExternalStorageFailedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookExternalStorageFailedEventData),
                jacksonTypeRef<BetaWebhookExternalStorageFailedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookExternalStorageFailedEventData)
            .isEqualTo(betaWebhookExternalStorageFailedEventData)
    }
}
