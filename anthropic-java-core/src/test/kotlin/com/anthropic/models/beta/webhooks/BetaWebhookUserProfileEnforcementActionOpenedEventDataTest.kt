package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookUserProfileEnforcementActionOpenedEventDataTest {

    @Test
    fun create() {
        val betaWebhookUserProfileEnforcementActionOpenedEventData =
            BetaWebhookUserProfileEnforcementActionOpenedEventData.builder()
                .id("id")
                .enforcementActionId("enforcement_action_id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookUserProfileEnforcementActionOpenedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookUserProfileEnforcementActionOpenedEventData.enforcementActionId())
            .isEqualTo("enforcement_action_id")
        assertThat(betaWebhookUserProfileEnforcementActionOpenedEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookUserProfileEnforcementActionOpenedEventData =
            BetaWebhookUserProfileEnforcementActionOpenedEventData.builder()
                .id("id")
                .enforcementActionId("enforcement_action_id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookUserProfileEnforcementActionOpenedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaWebhookUserProfileEnforcementActionOpenedEventData
                ),
                jacksonTypeRef<BetaWebhookUserProfileEnforcementActionOpenedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookUserProfileEnforcementActionOpenedEventData)
            .isEqualTo(betaWebhookUserProfileEnforcementActionOpenedEventData)
    }
}
