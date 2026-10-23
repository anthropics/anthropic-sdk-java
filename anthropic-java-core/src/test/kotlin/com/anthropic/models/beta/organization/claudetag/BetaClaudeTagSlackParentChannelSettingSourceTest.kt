package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackParentChannelSettingSourceTest {

    @Test
    fun create() {
        val betaClaudeTagSlackParentChannelSettingSource =
            BetaClaudeTagSlackParentChannelSettingSource.of("slack_channel_id")

        assertThat(betaClaudeTagSlackParentChannelSettingSource.slackChannelId())
            .isEqualTo("slack_channel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackParentChannelSettingSource =
            BetaClaudeTagSlackParentChannelSettingSource.of("slack_channel_id")

        val roundtrippedBetaClaudeTagSlackParentChannelSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackParentChannelSettingSource),
                jacksonTypeRef<BetaClaudeTagSlackParentChannelSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackParentChannelSettingSource)
            .isEqualTo(betaClaudeTagSlackParentChannelSettingSource)
    }
}
