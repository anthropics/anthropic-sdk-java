package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSettingSourcesTest {

    @Test
    fun create() {
        val betaClaudeTagSettingSources =
            BetaClaudeTagSettingSources.builder()
                .slackChannelEnabled("slack_channel_id")
                .slackChannelEnvironmentId("slack_channel_id")
                .slackChannelGuestPolicy("slack_channel_id")
                .addSlackChannelInstruction("slack_channel_id")
                .slackChannelModel("slack_channel_id")
                .build()

        assertThat(betaClaudeTagSettingSources.enabled())
            .contains(BetaClaudeTagSettingSource.ofSlackChannel("slack_channel_id"))
        assertThat(betaClaudeTagSettingSources.environmentId())
            .contains(BetaClaudeTagSettingSource.ofSlackChannel("slack_channel_id"))
        assertThat(betaClaudeTagSettingSources.guestPolicy())
            .contains(BetaClaudeTagSettingSource.ofSlackChannel("slack_channel_id"))
        assertThat(betaClaudeTagSettingSources.instructions())
            .containsExactly(BetaClaudeTagSettingSource.ofSlackChannel("slack_channel_id"))
        assertThat(betaClaudeTagSettingSources.model())
            .contains(BetaClaudeTagSettingSource.ofSlackChannel("slack_channel_id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSources =
            BetaClaudeTagSettingSources.builder()
                .slackChannelEnabled("slack_channel_id")
                .slackChannelEnvironmentId("slack_channel_id")
                .slackChannelGuestPolicy("slack_channel_id")
                .addSlackChannelInstruction("slack_channel_id")
                .slackChannelModel("slack_channel_id")
                .build()

        val roundtrippedBetaClaudeTagSettingSources =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSources),
                jacksonTypeRef<BetaClaudeTagSettingSources>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSources).isEqualTo(betaClaudeTagSettingSources)
    }
}
