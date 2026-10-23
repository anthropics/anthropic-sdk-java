package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.claudetag.BundleService
import com.anthropic.services.blocking.beta.organization.claudetag.BundleServiceImpl
import com.anthropic.services.blocking.beta.organization.claudetag.SlackChannelService
import com.anthropic.services.blocking.beta.organization.claudetag.SlackChannelServiceImpl
import com.anthropic.services.blocking.beta.organization.claudetag.SlackWorkspaceService
import com.anthropic.services.blocking.beta.organization.claudetag.SlackWorkspaceServiceImpl
import java.util.function.Consumer

class ClaudeTagServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ClaudeTagService {

    private val withRawResponse: ClaudeTagService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val slackChannels: SlackChannelService by lazy {
        SlackChannelServiceImpl(clientOptions)
    }

    private val slackWorkspaces: SlackWorkspaceService by lazy {
        SlackWorkspaceServiceImpl(clientOptions)
    }

    private val bundles: BundleService by lazy { BundleServiceImpl(clientOptions) }

    override fun withRawResponse(): ClaudeTagService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeTagService =
        ClaudeTagServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun slackChannels(): SlackChannelService = slackChannels

    override fun slackWorkspaces(): SlackWorkspaceService = slackWorkspaces

    override fun bundles(): BundleService = bundles

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ClaudeTagService.WithRawResponse {

        private val slackChannels: SlackChannelService.WithRawResponse by lazy {
            SlackChannelServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val slackWorkspaces: SlackWorkspaceService.WithRawResponse by lazy {
            SlackWorkspaceServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val bundles: BundleService.WithRawResponse by lazy {
            BundleServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ClaudeTagService.WithRawResponse =
            ClaudeTagServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun slackChannels(): SlackChannelService.WithRawResponse = slackChannels

        override fun slackWorkspaces(): SlackWorkspaceService.WithRawResponse = slackWorkspaces

        override fun bundles(): BundleService.WithRawResponse = bundles
    }
}
