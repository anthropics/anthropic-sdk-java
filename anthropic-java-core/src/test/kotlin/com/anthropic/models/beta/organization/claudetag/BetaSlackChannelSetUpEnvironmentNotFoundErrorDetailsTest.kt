package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSlackChannelSetUpEnvironmentNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val betaSlackChannelSetUpEnvironmentNotFoundErrorDetails =
            BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails.of("slack_channel_id")

        assertThat(betaSlackChannelSetUpEnvironmentNotFoundErrorDetails.slackChannelId())
            .isEqualTo("slack_channel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSlackChannelSetUpEnvironmentNotFoundErrorDetails =
            BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails.of("slack_channel_id")

        val roundtrippedBetaSlackChannelSetUpEnvironmentNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSlackChannelSetUpEnvironmentNotFoundErrorDetails),
                jacksonTypeRef<BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedBetaSlackChannelSetUpEnvironmentNotFoundErrorDetails)
            .isEqualTo(betaSlackChannelSetUpEnvironmentNotFoundErrorDetails)
    }
}
