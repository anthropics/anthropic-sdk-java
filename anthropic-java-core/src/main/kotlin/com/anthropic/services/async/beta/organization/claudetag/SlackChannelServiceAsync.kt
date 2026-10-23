package com.anthropic.services.async.beta.organization.claudetag

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.slackchannels.BetaClaudeTagDeletedSlackChannel
import com.anthropic.models.beta.organization.claudetag.slackchannels.BetaClaudeTagSlackChannel
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelCreateParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelDeleteParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelListPageAsync
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelListParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface SlackChannelServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SlackChannelServiceAsync

    /**
     * Set a Slack channel up for Claude Tag and return it with its identifier. If the channel is
     * already set up, whether in claude.ai, by Claude's first use in it or by an earlier request,
     * the existing channel is returned, so the request can be repeated. `settings`, when sent, is
     * applied as an update applies it. Before a channel is set up, Claude's Slack app confirms with
     * Slack that the channel is in the workspace, is not archived and is visible to the app; a
     * channel that fails the confirmation returns 404. Setting the channel up and applying
     * `settings` are two steps: when the second is refused, the response is the refusal, the
     * channel stays set up with every setting unset, and the error's `details.slack_channel_id`
     * holds the channel's identifier.
     */
    fun create(params: SlackChannelCreateParams): CompletableFuture<BetaClaudeTagSlackChannel> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SlackChannelCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackChannel>

    /**
     * Get one Slack channel and its settings. By default `settings` holds what the channel itself
     * stores: a setting that is null there is not set on the channel and is inherited. With
     * `include_inherited=true`, `settings` holds what applies in the channel once the settings
     * levels above it are taken into account, and the request fails with 503 rather than resolve
     * against a settings level that could not be read. Either way `sources` names the settings
     * level each value comes from, so a caller tells the channel's own value from an inherited one
     * by its source, not by whether it is null.
     */
    fun retrieve(id: String): CompletableFuture<BetaClaudeTagSlackChannel> =
        retrieve(id, SlackChannelRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SlackChannelRetrieveParams = SlackChannelRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackChannel> =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SlackChannelRetrieveParams = SlackChannelRetrieveParams.none(),
    ): CompletableFuture<BetaClaudeTagSlackChannel> = retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SlackChannelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackChannel>

    /** @see retrieve */
    fun retrieve(params: SlackChannelRetrieveParams): CompletableFuture<BetaClaudeTagSlackChannel> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackChannel> =
        retrieve(id, SlackChannelRetrieveParams.none(), requestOptions)

    /**
     * Change the settings a Slack channel stores. The update is partial: a setting left out is
     * unchanged, and a setting sent as null is cleared, so the channel inherits it. If any value is
     * refused, no setting in the request is stored. A group direct message takes no settings and
     * returns 400.
     */
    fun update(
        id: String,
        params: SlackChannelUpdateParams,
    ): CompletableFuture<BetaClaudeTagSlackChannel> = update(id, params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: SlackChannelUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackChannel> =
        update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(params: SlackChannelUpdateParams): CompletableFuture<BetaClaudeTagSlackChannel> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: SlackChannelUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackChannel>

    /**
     * List the set-up Slack channels of one Slack workspace, newest first by `created_at`. A
     * channel that is not set up is not listed: Slack holds the full list of a workspace's
     * channels. Pass `slack_channel_id` as well to look one channel up by its Slack identifiers:
     * `data` then holds that channel, or is empty when it is not set up, and `limit` and `page` are
     * ignored. A workspace that the organization owns only through a Slack Enterprise Grid returns
     * 404 until it is set up. With `include_inherited=true` each channel's `settings` holds what
     * applies in it, and the request fails with 503 rather than resolve against a settings level
     * that could not be read. A `page` cursor resumes only a request with the same
     * `include_inherited`.
     */
    fun list(params: SlackChannelListParams): CompletableFuture<SlackChannelListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: SlackChannelListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SlackChannelListPageAsync>

    /**
     * Remove a Slack channel from Claude Tag. This deletes the channel's stored settings and its
     * Bundle Targets at once, and then, in the background, the channel's sessions and their
     * transcripts, its memory, its routines and the artifacts published from it. None of it can be
     * restored. The channel in Slack and its messages are unchanged. The request is refused with
     * 400 and `error.details.error_code` `slack_channel_enabled` while `enabled` resolves to `true`
     * for the channel, as a read with `include_inherited=true` returns it: set `enabled` to `false`
     * on the channel first. This guards against removing a channel in use by mistake; it is not a
     * way to keep Claude out, because the removal deletes the channel's own `enabled: false` with
     * its other settings, after which the channel inherits again and Claude's next use of it can
     * set it up anew with a new identifier. The identifier returns 404 as soon as the request
     * returns, so a repeated removal returns 404. A group direct message returns 400, and so does a
     * channel that holds configuration this API does not manage.
     */
    fun delete(id: String): CompletableFuture<BetaClaudeTagDeletedSlackChannel> =
        delete(id, SlackChannelDeleteParams.none())

    /** @see delete */
    fun delete(
        id: String,
        params: SlackChannelDeleteParams = SlackChannelDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagDeletedSlackChannel> =
        delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(
        id: String,
        params: SlackChannelDeleteParams = SlackChannelDeleteParams.none(),
    ): CompletableFuture<BetaClaudeTagDeletedSlackChannel> =
        delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SlackChannelDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagDeletedSlackChannel>

    /** @see delete */
    fun delete(
        params: SlackChannelDeleteParams
    ): CompletableFuture<BetaClaudeTagDeletedSlackChannel> = delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagDeletedSlackChannel> =
        delete(id, SlackChannelDeleteParams.none(), requestOptions)

    /**
     * A view of [SlackChannelServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SlackChannelServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/claude_tag/slack_channels`, but
         * is otherwise the same as [SlackChannelServiceAsync.create].
         */
        fun create(
            params: SlackChannelCreateParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: SlackChannelCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/slack_channels/{id}`,
         * but is otherwise the same as [SlackChannelServiceAsync.retrieve].
         */
        fun retrieve(id: String): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            retrieve(id, SlackChannelRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SlackChannelRetrieveParams = SlackChannelRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SlackChannelRetrieveParams = SlackChannelRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SlackChannelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>>

        /** @see retrieve */
        fun retrieve(
            params: SlackChannelRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            retrieve(id, SlackChannelRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/claude_tag/slack_channels/{id}`,
         * but is otherwise the same as [SlackChannelServiceAsync.update].
         */
        fun update(
            id: String,
            params: SlackChannelUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            update(id, params, RequestOptions.none())

        /** @see update */
        fun update(
            id: String,
            params: SlackChannelUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        fun update(
            params: SlackChannelUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: SlackChannelUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/slack_channels`, but is
         * otherwise the same as [SlackChannelServiceAsync.list].
         */
        fun list(
            params: SlackChannelListParams
        ): CompletableFuture<HttpResponseFor<SlackChannelListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: SlackChannelListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SlackChannelListPageAsync>>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/claude_tag/slack_channels/{id}`, but is otherwise the same as
         * [SlackChannelServiceAsync.delete].
         */
        fun delete(
            id: String
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>> =
            delete(id, SlackChannelDeleteParams.none())

        /** @see delete */
        fun delete(
            id: String,
            params: SlackChannelDeleteParams = SlackChannelDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        fun delete(
            id: String,
            params: SlackChannelDeleteParams = SlackChannelDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>> =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: SlackChannelDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>>

        /** @see delete */
        fun delete(
            params: SlackChannelDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>> =
            delete(id, SlackChannelDeleteParams.none(), requestOptions)
    }
}
