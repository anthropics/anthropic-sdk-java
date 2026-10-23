package com.anthropic.models.beta.organization.claudetag.slackworkspaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsUpdate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SlackWorkspaceUpdateParamsTest {

    @Test
    fun create() {
        SlackWorkspaceUpdateParams.builder()
            .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .settings(
                BetaClaudeTagSettingsUpdate.builder()
                    .enabled(true)
                    .environmentId("x")
                    .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                    .instructions("instructions")
                    .model("x")
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            SlackWorkspaceUpdateParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                .settings(BetaClaudeTagSettingsUpdate.builder().build())
                .build()

        assertThat(params._pathParam(0)).isEqualTo("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            SlackWorkspaceUpdateParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .settings(
                    BetaClaudeTagSettingsUpdate.builder()
                        .enabled(true)
                        .environmentId("x")
                        .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                        .instructions("instructions")
                        .model("x")
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "experimental-claude-tag-2026-10-21")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SlackWorkspaceUpdateParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                .settings(BetaClaudeTagSettingsUpdate.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SlackWorkspaceUpdateParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .settings(
                    BetaClaudeTagSettingsUpdate.builder()
                        .enabled(true)
                        .environmentId("x")
                        .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                        .instructions("instructions")
                        .model("x")
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
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SlackWorkspaceUpdateParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                .settings(BetaClaudeTagSettingsUpdate.builder().build())
                .build()

        val body = params._body()

        assertThat(body.settings()).isEqualTo(BetaClaudeTagSettingsUpdate.builder().build())
    }
}
