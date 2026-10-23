package com.anthropic.services.blocking.beta.organization.claudetag

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsCreate
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsUpdate
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSlackChannelIdentifiers
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelCreateParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelDeleteParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelListParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SlackChannelServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val slackChannelService =
            client.beta().organization().claudeTag().slackChannels().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagSlackChannel =
            slackChannelService.create(
                SlackChannelCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
                            .build()
                    )
                    .build()
            )

        betaClaudeTagSlackChannel.validate()
    }

    @Test
    fun retrieve() {
        val slackChannelService =
            client.beta().organization().claudeTag().slackChannels().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagSlackChannel =
            slackChannelService.retrieve(
                SlackChannelRetrieveParams.builder()
                    .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                    .includeInherited(true)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaClaudeTagSlackChannel.validate()
    }

    @Test
    fun update() {
        val slackChannelService =
            client.beta().organization().claudeTag().slackChannels().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagSlackChannel =
            slackChannelService.update(
                SlackChannelUpdateParams.builder()
                    .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
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
            )

        betaClaudeTagSlackChannel.validate()
    }

    @Test
    fun list() {
        val slackChannelService =
            client.beta().organization().claudeTag().slackChannels().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page =
            slackChannelService.list(
                SlackChannelListParams.builder().slackWorkspaceId("slack_workspace_id").build()
            )

        page.response().validate()
    }

    @Test
    fun delete() {
        val slackChannelService =
            client.beta().organization().claudeTag().slackChannels().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagDeletedSlackChannel =
            slackChannelService.delete(
                SlackChannelDeleteParams.builder()
                    .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaClaudeTagDeletedSlackChannel.validate()
    }
}
