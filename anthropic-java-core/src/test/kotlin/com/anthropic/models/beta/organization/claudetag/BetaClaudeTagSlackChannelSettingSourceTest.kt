package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackChannelSettingSourceTest {

    @Test
    fun create() {
        val betaClaudeTagSlackChannelSettingSource =
            BetaClaudeTagSlackChannelSettingSource.of("slack_channel_id")

        assertThat(betaClaudeTagSlackChannelSettingSource.slackChannelId())
            .isEqualTo("slack_channel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackChannelSettingSource =
            BetaClaudeTagSlackChannelSettingSource.of("slack_channel_id")

        val roundtrippedBetaClaudeTagSlackChannelSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackChannelSettingSource),
                jacksonTypeRef<BetaClaudeTagSlackChannelSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackChannelSettingSource)
            .isEqualTo(betaClaudeTagSlackChannelSettingSource)
    }
}
