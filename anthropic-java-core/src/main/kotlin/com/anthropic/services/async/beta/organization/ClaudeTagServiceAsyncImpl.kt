package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.claudetag.BundleServiceAsync
import com.anthropic.services.async.beta.organization.claudetag.BundleServiceAsyncImpl
import com.anthropic.services.async.beta.organization.claudetag.SlackChannelServiceAsync
import com.anthropic.services.async.beta.organization.claudetag.SlackChannelServiceAsyncImpl
import com.anthropic.services.async.beta.organization.claudetag.SlackWorkspaceServiceAsync
import com.anthropic.services.async.beta.organization.claudetag.SlackWorkspaceServiceAsyncImpl
import java.util.function.Consumer

class ClaudeTagServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ClaudeTagServiceAsync {

    private val withRawResponse: ClaudeTagServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val slackChannels: SlackChannelServiceAsync by lazy {
        SlackChannelServiceAsyncImpl(clientOptions)
    }

    private val slackWorkspaces: SlackWorkspaceServiceAsync by lazy {
        SlackWorkspaceServiceAsyncImpl(clientOptions)
    }

    private val bundles: BundleServiceAsync by lazy { BundleServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): ClaudeTagServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeTagServiceAsync =
        ClaudeTagServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun slackChannels(): SlackChannelServiceAsync = slackChannels

    override fun slackWorkspaces(): SlackWorkspaceServiceAsync = slackWorkspaces

    override fun bundles(): BundleServiceAsync = bundles

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ClaudeTagServiceAsync.WithRawResponse {

        private val slackChannels: SlackChannelServiceAsync.WithRawResponse by lazy {
            SlackChannelServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val slackWorkspaces: SlackWorkspaceServiceAsync.WithRawResponse by lazy {
            SlackWorkspaceServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val bundles: BundleServiceAsync.WithRawResponse by lazy {
            BundleServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ClaudeTagServiceAsync.WithRawResponse =
            ClaudeTagServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun slackChannels(): SlackChannelServiceAsync.WithRawResponse = slackChannels

        override fun slackWorkspaces(): SlackWorkspaceServiceAsync.WithRawResponse = slackWorkspaces

        override fun bundles(): BundleServiceAsync.WithRawResponse = bundles
    }
}
