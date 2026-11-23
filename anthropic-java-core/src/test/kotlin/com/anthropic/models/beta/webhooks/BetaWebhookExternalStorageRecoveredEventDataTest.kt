package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookExternalStorageRecoveredEventDataTest {

    @Test
    fun create() {
        val betaWebhookExternalStorageRecoveredEventData =
            BetaWebhookExternalStorageRecoveredEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookExternalStorageRecoveredEventData.id()).isEqualTo("id")
        assertThat(betaWebhookExternalStorageRecoveredEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookExternalStorageRecoveredEventData =
            BetaWebhookExternalStorageRecoveredEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookExternalStorageRecoveredEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookExternalStorageRecoveredEventData),
                jacksonTypeRef<BetaWebhookExternalStorageRecoveredEventData>(),
            )

        assertThat(roundtrippedBetaWebhookExternalStorageRecoveredEventData)
            .isEqualTo(betaWebhookExternalStorageRecoveredEventData)
    }
}
