package com.anthropic.services.async.beta.organization.claudetag

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.BetaClaudeTagSlackWorkspace
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListPageAsync
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface SlackWorkspaceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SlackWorkspaceServiceAsync

    /**
     * Get one Slack workspace and its settings. By default `settings` holds what the workspace
     * itself stores: a setting that is null there is not set on the workspace and is inherited.
     * With `include_inherited=true`, `settings` holds what applies in the workspace once the
     * organization's Slack defaults are taken into account, and the request fails with 503 rather
     * than resolve against a settings level that could not be read. Either way `sources` names the
     * settings level each value comes from.
     */
    fun retrieve(id: String): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        retrieve(id, SlackWorkspaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> = retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SlackWorkspaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackWorkspace>

    /** @see retrieve */
    fun retrieve(
        params: SlackWorkspaceRetrieveParams
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        retrieve(id, SlackWorkspaceRetrieveParams.none(), requestOptions)

    /**
     * Change the settings a Slack workspace stores. The update is partial: a setting left out is
     * unchanged, and a setting sent as null is cleared, so the workspace inherits it. If any value
     * is refused, no setting in the request is stored.
     */
    fun update(
        id: String,
        params: SlackWorkspaceUpdateParams,
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> = update(id, params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: SlackWorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(params: SlackWorkspaceUpdateParams): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: SlackWorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagSlackWorkspace>

    /**
     * List the set-up Slack workspaces the organization owns, newest first by `created_at`. Pass
     * `slack_workspace_id` to look one workspace up by its Slack identifier: `data` then holds that
     * workspace, or is empty when it is not set up. A workspace of a Slack Enterprise Grid the
     * organization has connected returns 404 until it is set up. With `include_inherited=true` each
     * workspace's `settings` holds what applies in it, and the request fails with 503 rather than
     * resolve against a settings level that could not be read. A `page` cursor resumes only a
     * request with the same `include_inherited`.
     */
    fun list(): CompletableFuture<SlackWorkspaceListPageAsync> =
        list(SlackWorkspaceListParams.none())

    /** @see list */
    fun list(
        params: SlackWorkspaceListParams = SlackWorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SlackWorkspaceListPageAsync>

    /** @see list */
    fun list(
        params: SlackWorkspaceListParams = SlackWorkspaceListParams.none()
    ): CompletableFuture<SlackWorkspaceListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<SlackWorkspaceListPageAsync> =
        list(SlackWorkspaceListParams.none(), requestOptions)

    /**
     * A view of [SlackWorkspaceServiceAsync] that provides access to raw HTTP responses for each
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
        ): SlackWorkspaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/slack_workspaces/{id}`,
         * but is otherwise the same as [SlackWorkspaceServiceAsync.retrieve].
         */
        fun retrieve(id: String): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            retrieve(id, SlackWorkspaceRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            id: String,
            params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SlackWorkspaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>>

        /** @see retrieve */
        fun retrieve(
            params: SlackWorkspaceRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            retrieve(id, SlackWorkspaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_tag/slack_workspaces/{id}`, but is otherwise the same as
         * [SlackWorkspaceServiceAsync.update].
         */
        fun update(
            id: String,
            params: SlackWorkspaceUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            update(id, params, RequestOptions.none())

        /** @see update */
        fun update(
            id: String,
            params: SlackWorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        fun update(
            params: SlackWorkspaceUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: SlackWorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/slack_workspaces`, but
         * is otherwise the same as [SlackWorkspaceServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<SlackWorkspaceListPageAsync>> =
            list(SlackWorkspaceListParams.none())

        /** @see list */
        fun list(
            params: SlackWorkspaceListParams = SlackWorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SlackWorkspaceListPageAsync>>

        /** @see list */
        fun list(
            params: SlackWorkspaceListParams = SlackWorkspaceListParams.none()
        ): CompletableFuture<HttpResponseFor<SlackWorkspaceListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SlackWorkspaceListPageAsync>> =
            list(SlackWorkspaceListParams.none(), requestOptions)
    }
}
