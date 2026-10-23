package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.claudetag.BundleService
import com.anthropic.services.blocking.beta.organization.claudetag.SlackChannelService
import com.anthropic.services.blocking.beta.organization.claudetag.SlackWorkspaceService
import java.util.function.Consumer

interface ClaudeTagService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeTagService

    fun slackChannels(): SlackChannelService

    fun slackWorkspaces(): SlackWorkspaceService

    fun bundles(): BundleService

    /** A view of [ClaudeTagService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ClaudeTagService.WithRawResponse

        fun slackChannels(): SlackChannelService.WithRawResponse

        fun slackWorkspaces(): SlackWorkspaceService.WithRawResponse

        fun bundles(): BundleService.WithRawResponse
    }
}
