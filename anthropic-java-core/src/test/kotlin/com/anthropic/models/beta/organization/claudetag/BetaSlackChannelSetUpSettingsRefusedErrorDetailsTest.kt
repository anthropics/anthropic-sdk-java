package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSlackChannelSetUpSettingsRefusedErrorDetailsTest {

    @Test
    fun create() {
        val betaSlackChannelSetUpSettingsRefusedErrorDetails =
            BetaSlackChannelSetUpSettingsRefusedErrorDetails.of("slack_channel_id")

        assertThat(betaSlackChannelSetUpSettingsRefusedErrorDetails.slackChannelId())
            .isEqualTo("slack_channel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSlackChannelSetUpSettingsRefusedErrorDetails =
            BetaSlackChannelSetUpSettingsRefusedErrorDetails.of("slack_channel_id")

        val roundtrippedBetaSlackChannelSetUpSettingsRefusedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSlackChannelSetUpSettingsRefusedErrorDetails),
                jacksonTypeRef<BetaSlackChannelSetUpSettingsRefusedErrorDetails>(),
            )

        assertThat(roundtrippedBetaSlackChannelSetUpSettingsRefusedErrorDetails)
            .isEqualTo(betaSlackChannelSetUpSettingsRefusedErrorDetails)
    }
}
