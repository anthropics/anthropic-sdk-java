package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingSources
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettings
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSlackChannelIdentifiers
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackChannelTest {

    @Test
    fun create() {
        val betaClaudeTagSlackChannel =
            BetaClaudeTagSlackChannel.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .settings(
                    BetaClaudeTagSettings.builder()
                        .enabled(true)
                        .environmentId("environment_id")
                        .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                        .instructions("instructions")
                        .model("model")
                        .build()
                )
                .slack(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
                .sources(
                    BetaClaudeTagSettingSources.builder()
                        .slackChannelEnabled("slack_channel_id")
                        .slackChannelEnvironmentId("slack_channel_id")
                        .slackChannelGuestPolicy("slack_channel_id")
                        .addSlackChannelInstruction("slack_channel_id")
                        .slackChannelModel("slack_channel_id")
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaClaudeTagSlackChannel.id()).isEqualTo("id")
        assertThat(betaClaudeTagSlackChannel.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeTagSlackChannel.settings())
            .isEqualTo(
                BetaClaudeTagSettings.builder()
                    .enabled(true)
                    .environmentId("environment_id")
                    .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                    .instructions("instructions")
                    .model("model")
                    .build()
            )
        assertThat(betaClaudeTagSlackChannel.slack())
            .isEqualTo(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("xx")
                    .workspaceId("xx")
                    .build()
            )
        assertThat(betaClaudeTagSlackChannel.sources())
            .isEqualTo(
                BetaClaudeTagSettingSources.builder()
                    .slackChannelEnabled("slack_channel_id")
                    .slackChannelEnvironmentId("slack_channel_id")
                    .slackChannelGuestPolicy("slack_channel_id")
                    .addSlackChannelInstruction("slack_channel_id")
                    .slackChannelModel("slack_channel_id")
                    .build()
            )
        assertThat(betaClaudeTagSlackChannel.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackChannel =
            BetaClaudeTagSlackChannel.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .settings(
                    BetaClaudeTagSettings.builder()
                        .enabled(true)
                        .environmentId("environment_id")
                        .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                        .instructions("instructions")
                        .model("model")
                        .build()
                )
                .slack(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
                .sources(
                    BetaClaudeTagSettingSources.builder()
                        .slackChannelEnabled("slack_channel_id")
                        .slackChannelEnvironmentId("slack_channel_id")
                        .slackChannelGuestPolicy("slack_channel_id")
                        .addSlackChannelInstruction("slack_channel_id")
                        .slackChannelModel("slack_channel_id")
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaClaudeTagSlackChannel =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackChannel),
                jacksonTypeRef<BetaClaudeTagSlackChannel>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackChannel).isEqualTo(betaClaudeTagSlackChannel)
    }
}
