package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookExternalKeyRecoveredEventDataTest {

    @Test
    fun create() {
        val betaWebhookExternalKeyRecoveredEventData =
            BetaWebhookExternalKeyRecoveredEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookExternalKeyRecoveredEventData.id()).isEqualTo("id")
        assertThat(betaWebhookExternalKeyRecoveredEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookExternalKeyRecoveredEventData =
            BetaWebhookExternalKeyRecoveredEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookExternalKeyRecoveredEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookExternalKeyRecoveredEventData),
                jacksonTypeRef<BetaWebhookExternalKeyRecoveredEventData>(),
            )

        assertThat(roundtrippedBetaWebhookExternalKeyRecoveredEventData)
            .isEqualTo(betaWebhookExternalKeyRecoveredEventData)
    }
}
