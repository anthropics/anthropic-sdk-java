package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagReplyModeAutomatic
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsCreate
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSlackChannelIdentifiers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SlackChannelCreateParamsTest {

    @Test
    fun create() {
        SlackChannelCreateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .slack(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("xx")
                    .workspaceId("xx")
                    .build()
            )
            .settings(
                BetaClaudeTagSettingsCreate.builder()
                    .enabled(true)
                    .environmentId("x")
                    .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                    .instructions("instructions")
                    .model("x")
                    .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                    .build()
            )
            .build()
    }

    @Test
    fun headers() {
        val params =
            SlackChannelCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .slack(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
                .settings(
                    BetaClaudeTagSettingsCreate.builder()
                        .enabled(true)
                        .environmentId("x")
                        .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                        .instructions("instructions")
                        .model("x")
                        .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "experimental-claude-tag-2026-10-21")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SlackChannelCreateParams.builder()
                .slack(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SlackChannelCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .slack(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
                .settings(
                    BetaClaudeTagSettingsCreate.builder()
                        .enabled(true)
                        .environmentId("x")
                        .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                        .instructions("instructions")
                        .model("x")
                        .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.slack())
            .isEqualTo(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("xx")
                    .workspaceId("xx")
                    .build()
            )
        assertThat(body.settings())
            .contains(
                BetaClaudeTagSettingsCreate.builder()
                    .enabled(true)
                    .environmentId("x")
                    .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                    .instructions("instructions")
                    .model("x")
                    .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SlackChannelCreateParams.builder()
                .slack(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.slack())
            .isEqualTo(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("xx")
                    .workspaceId("xx")
                    .build()
            )
    }
}
