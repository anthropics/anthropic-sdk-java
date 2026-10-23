package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackChannelBundleTargetTest {

    @Test
    fun create() {
        val betaClaudeTagSlackChannelBundleTarget =
            BetaClaudeTagSlackChannelBundleTarget.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .slackChannelId("slack_channel_id")
                .build()

        assertThat(betaClaudeTagSlackChannelBundleTarget.id()).isEqualTo("id")
        assertThat(betaClaudeTagSlackChannelBundleTarget.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeTagSlackChannelBundleTarget.slackChannelId())
            .isEqualTo("slack_channel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackChannelBundleTarget =
            BetaClaudeTagSlackChannelBundleTarget.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .slackChannelId("slack_channel_id")
                .build()

        val roundtrippedBetaClaudeTagSlackChannelBundleTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackChannelBundleTarget),
                jacksonTypeRef<BetaClaudeTagSlackChannelBundleTarget>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackChannelBundleTarget)
            .isEqualTo(betaClaudeTagSlackChannelBundleTarget)
    }
}
