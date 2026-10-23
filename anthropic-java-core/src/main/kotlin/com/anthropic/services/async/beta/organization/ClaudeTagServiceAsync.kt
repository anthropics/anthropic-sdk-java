package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.claudetag.BundleServiceAsync
import com.anthropic.services.async.beta.organization.claudetag.SlackChannelServiceAsync
import com.anthropic.services.async.beta.organization.claudetag.SlackWorkspaceServiceAsync
import java.util.function.Consumer

interface ClaudeTagServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeTagServiceAsync

    fun slackChannels(): SlackChannelServiceAsync

    fun slackWorkspaces(): SlackWorkspaceServiceAsync

    fun bundles(): BundleServiceAsync

    /**
     * A view of [ClaudeTagServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ClaudeTagServiceAsync.WithRawResponse

        fun slackChannels(): SlackChannelServiceAsync.WithRawResponse

        fun slackWorkspaces(): SlackWorkspaceServiceAsync.WithRawResponse

        fun bundles(): BundleServiceAsync.WithRawResponse
    }
}
