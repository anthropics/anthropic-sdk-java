package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagReplyModeAutomatic
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsUpdate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SlackChannelUpdateParamsTest {

    @Test
    fun create() {
        SlackChannelUpdateParams.builder()
            .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .settings(
                BetaClaudeTagSettingsUpdate.builder()
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
    fun pathParams() {
        val params =
            SlackChannelUpdateParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .settings(BetaClaudeTagSettingsUpdate.builder().build())
                .build()

        assertThat(params._pathParam(0)).isEqualTo("slch_01SvpyRQK3nN6eH9uhRA7n1q")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            SlackChannelUpdateParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .settings(
                    BetaClaudeTagSettingsUpdate.builder()
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
            SlackChannelUpdateParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .settings(BetaClaudeTagSettingsUpdate.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SlackChannelUpdateParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .settings(
                    BetaClaudeTagSettingsUpdate.builder()
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

        assertThat(body.settings())
            .isEqualTo(
                BetaClaudeTagSettingsUpdate.builder()
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
            SlackChannelUpdateParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .settings(BetaClaudeTagSettingsUpdate.builder().build())
                .build()

        val body = params._body()

        assertThat(body.settings()).isEqualTo(BetaClaudeTagSettingsUpdate.builder().build())
    }
}
