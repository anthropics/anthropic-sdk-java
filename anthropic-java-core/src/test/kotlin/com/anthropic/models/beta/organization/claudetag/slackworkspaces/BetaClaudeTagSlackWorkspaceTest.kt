package com.anthropic.models.beta.organization.claudetag.slackworkspaces

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingSources
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettings
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSlackWorkspaceIdentifiers
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackWorkspaceTest {

    @Test
    fun create() {
        val betaClaudeTagSlackWorkspace =
            BetaClaudeTagSlackWorkspace.builder()
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
                .slack(BetaClaudeTagSlackWorkspaceIdentifiers.of("workspace_id"))
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

        assertThat(betaClaudeTagSlackWorkspace.id()).isEqualTo("id")
        assertThat(betaClaudeTagSlackWorkspace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeTagSlackWorkspace.settings())
            .isEqualTo(
                BetaClaudeTagSettings.builder()
                    .enabled(true)
                    .environmentId("environment_id")
                    .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                    .instructions("instructions")
                    .model("model")
                    .build()
            )
        assertThat(betaClaudeTagSlackWorkspace.slack())
            .isEqualTo(BetaClaudeTagSlackWorkspaceIdentifiers.of("workspace_id"))
        assertThat(betaClaudeTagSlackWorkspace.sources())
            .isEqualTo(
                BetaClaudeTagSettingSources.builder()
                    .slackChannelEnabled("slack_channel_id")
                    .slackChannelEnvironmentId("slack_channel_id")
                    .slackChannelGuestPolicy("slack_channel_id")
                    .addSlackChannelInstruction("slack_channel_id")
                    .slackChannelModel("slack_channel_id")
                    .build()
            )
        assertThat(betaClaudeTagSlackWorkspace.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackWorkspace =
            BetaClaudeTagSlackWorkspace.builder()
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
                .slack(BetaClaudeTagSlackWorkspaceIdentifiers.of("workspace_id"))
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

        val roundtrippedBetaClaudeTagSlackWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackWorkspace),
                jacksonTypeRef<BetaClaudeTagSlackWorkspace>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackWorkspace).isEqualTo(betaClaudeTagSlackWorkspace)
    }
}
