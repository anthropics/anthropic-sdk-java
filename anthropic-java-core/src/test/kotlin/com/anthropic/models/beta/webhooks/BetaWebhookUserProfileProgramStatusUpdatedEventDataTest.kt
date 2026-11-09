package com.anthropic.models.beta.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebhookUserProfileProgramStatusUpdatedEventDataTest {

    @Test
    fun create() {
        val betaWebhookUserProfileProgramStatusUpdatedEventData =
            BetaWebhookUserProfileProgramStatusUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        assertThat(betaWebhookUserProfileProgramStatusUpdatedEventData.id()).isEqualTo("id")
        assertThat(betaWebhookUserProfileProgramStatusUpdatedEventData.organizationId())
            .isEqualTo("organization_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookUserProfileProgramStatusUpdatedEventData =
            BetaWebhookUserProfileProgramStatusUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val roundtrippedBetaWebhookUserProfileProgramStatusUpdatedEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookUserProfileProgramStatusUpdatedEventData),
                jacksonTypeRef<BetaWebhookUserProfileProgramStatusUpdatedEventData>(),
            )

        assertThat(roundtrippedBetaWebhookUserProfileProgramStatusUpdatedEventData)
            .isEqualTo(betaWebhookUserProfileProgramStatusUpdatedEventData)
    }
}
