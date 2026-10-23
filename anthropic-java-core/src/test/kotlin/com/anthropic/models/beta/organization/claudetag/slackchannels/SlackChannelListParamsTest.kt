package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SlackChannelListParamsTest {

    @Test
    fun create() {
        SlackChannelListParams.builder()
            .slackWorkspaceId("slack_workspace_id")
            .includeInherited(true)
            .limit(0)
            .page("page")
            .slackChannelId("slack_channel_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun headers() {
        val params =
            SlackChannelListParams.builder()
                .slackWorkspaceId("slack_workspace_id")
                .includeInherited(true)
                .limit(0)
                .page("page")
                .slackChannelId("slack_channel_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
        val params = SlackChannelListParams.builder().slackWorkspaceId("slack_workspace_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            SlackChannelListParams.builder()
                .slackWorkspaceId("slack_workspace_id")
                .includeInherited(true)
                .limit(0)
                .page("page")
                .slackChannelId("slack_channel_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("slack_workspace_id", "slack_workspace_id")
                    .put("include_inherited", "true")
                    .put("limit", "0")
                    .put("page", "page")
                    .put("slack_channel_id", "slack_channel_id")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = SlackChannelListParams.builder().slackWorkspaceId("slack_workspace_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder().put("slack_workspace_id", "slack_workspace_id").build()
            )
    }
}
