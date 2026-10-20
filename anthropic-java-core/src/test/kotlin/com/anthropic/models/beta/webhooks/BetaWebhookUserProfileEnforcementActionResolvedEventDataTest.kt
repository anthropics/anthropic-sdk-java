package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookUserProfileEnforcementActionResolvedEventDataTest {

    @Test
    fun create() {
        val betaWebhookUserProfileEnforcementActionResolvedEventData =
            BetaWebhookUserProfileEnforcementActionResolvedEventData.builder()
                .id("id")
                .enforcementActionId("enforcement_action_id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookUserProfileEnforcementActionResolvedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookUserProfileEnforcementActionResolvedEventData.enforcementActionId())
            .isEqualTo("enforcement_action_id")
        assertThat(betaWebhookUserProfileEnforcementActionResolvedEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookUserProfileEnforcementActionResolvedEventData =
            BetaWebhookUserProfileEnforcementActionResolvedEventData.builder()
                .id("id")
                .enforcementActionId("enforcement_action_id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookUserProfileEnforcementActionResolvedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaWebhookUserProfileEnforcementActionResolvedEventData
                ),
                jacksonTypeRef<BetaWebhookUserProfileEnforcementActionResolvedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookUserProfileEnforcementActionResolvedEventData)
            .isEqualTo(betaWebhookUserProfileEnforcementActionResolvedEventData)
    }
}
