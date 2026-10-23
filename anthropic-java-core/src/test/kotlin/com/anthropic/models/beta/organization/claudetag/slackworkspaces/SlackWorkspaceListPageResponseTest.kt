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

internal class SlackWorkspaceListPageResponseTest {

    @Test
    fun create() {
        val slackWorkspaceListPageResponse =
            SlackWorkspaceListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(slackWorkspaceListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(slackWorkspaceListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val slackWorkspaceListPageResponse =
            SlackWorkspaceListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedSlackWorkspaceListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(slackWorkspaceListPageResponse),
                jacksonTypeRef<SlackWorkspaceListPageResponse>(),
            )

        assertThat(roundtrippedSlackWorkspaceListPageResponse)
            .isEqualTo(slackWorkspaceListPageResponse)
    }
}
