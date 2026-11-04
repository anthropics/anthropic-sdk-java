package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSlackChannelSetUpSettingsConflictErrorDetailsTest {

    @Test
    fun create() {
        val betaSlackChannelSetUpSettingsConflictErrorDetails =
            BetaSlackChannelSetUpSettingsConflictErrorDetails.of("slack_channel_id")

        assertThat(betaSlackChannelSetUpSettingsConflictErrorDetails.slackChannelId())
            .isEqualTo("slack_channel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSlackChannelSetUpSettingsConflictErrorDetails =
            BetaSlackChannelSetUpSettingsConflictErrorDetails.of("slack_channel_id")

        val roundtrippedBetaSlackChannelSetUpSettingsConflictErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSlackChannelSetUpSettingsConflictErrorDetails),
                jacksonTypeRef<BetaSlackChannelSetUpSettingsConflictErrorDetails>(),
            )

        assertThat(roundtrippedBetaSlackChannelSetUpSettingsConflictErrorDetails)
            .isEqualTo(betaSlackChannelSetUpSettingsConflictErrorDetails)
    }
}
