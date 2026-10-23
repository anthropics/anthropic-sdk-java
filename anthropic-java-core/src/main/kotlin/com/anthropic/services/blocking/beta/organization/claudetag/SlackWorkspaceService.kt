package com.anthropic.services.blocking.beta.organization.claudetag

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.BetaClaudeTagSlackWorkspace
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListPage
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface SlackWorkspaceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SlackWorkspaceService

    /**
     * Get one Slack workspace and its settings. By default `settings` holds what the workspace
     * itself stores: a setting that is null there is not set on the workspace and is inherited.
     * With `include_inherited=true`, `settings` holds what applies in the workspace once the
     * organization's Slack defaults are taken into account, and the request fails with 503 rather
     * than resolve against a settings level that could not be read. Either way `sources` names the
     * settings level each value comes from.
     */
    fun retrieve(id: String): BetaClaudeTagSlackWorkspace =
        retrieve(id, SlackWorkspaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagSlackWorkspace = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        id: String,
        params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
    ): BetaClaudeTagSlackWorkspace = retrieve(id, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SlackWorkspaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagSlackWorkspace

    /** @see retrieve */
    fun retrieve(params: SlackWorkspaceRetrieveParams): BetaClaudeTagSlackWorkspace =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): BetaClaudeTagSlackWorkspace =
        retrieve(id, SlackWorkspaceRetrieveParams.none(), requestOptions)

    /**
     * Change the settings a Slack workspace stores. The update is partial: a setting left out is
     * unchanged, and a setting sent as null is cleared, so the workspace inherits it. If any value
     * is refused, no setting in the request is stored.
     */
    fun update(id: String, params: SlackWorkspaceUpdateParams): BetaClaudeTagSlackWorkspace =
        update(id, params, RequestOptions.none())

    /** @see update */
    fun update(
        id: String,
        params: SlackWorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagSlackWorkspace = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    fun update(params: SlackWorkspaceUpdateParams): BetaClaudeTagSlackWorkspace =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: SlackWorkspaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagSlackWorkspace

    /**
     * List the set-up Slack workspaces the organization owns, newest first by `created_at`. Pass
     * `slack_workspace_id` to look one workspace up by its Slack identifier: `data` then holds that
     * workspace, or is empty when it is not set up. A workspace of a Slack Enterprise Grid the
     * organization has connected returns 404 until it is set up. With `include_inherited=true` each
     * workspace's `settings` holds what applies in it, and the request fails with 503 rather than
     * resolve against a settings level that could not be read. A `page` cursor resumes only a
     * request with the same `include_inherited`.
     */
    fun list(): SlackWorkspaceListPage = list(SlackWorkspaceListParams.none())

    /** @see list */
    fun list(
        params: SlackWorkspaceListParams = SlackWorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SlackWorkspaceListPage

    /** @see list */
    fun list(
        params: SlackWorkspaceListParams = SlackWorkspaceListParams.none()
    ): SlackWorkspaceListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): SlackWorkspaceListPage =
        list(SlackWorkspaceListParams.none(), requestOptions)

    /**
     * A view of [SlackWorkspaceService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SlackWorkspaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/slack_workspaces/{id}`,
         * but is otherwise the same as [SlackWorkspaceService.retrieve].
         */
        @MustBeClosed
        fun retrieve(id: String): HttpResponseFor<BetaClaudeTagSlackWorkspace> =
            retrieve(id, SlackWorkspaceRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: SlackWorkspaceRetrieveParams = SlackWorkspaceRetrieveParams.none(),
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> =
            retrieve(id, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SlackWorkspaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SlackWorkspaceRetrieveParams
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> =
            retrieve(id, SlackWorkspaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_tag/slack_workspaces/{id}`, but is otherwise the same as
         * [SlackWorkspaceService.update].
         */
        @MustBeClosed
        fun update(
            id: String,
            params: SlackWorkspaceUpdateParams,
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> = update(id, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            id: String,
            params: SlackWorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            params: SlackWorkspaceUpdateParams
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace> = update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: SlackWorkspaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagSlackWorkspace>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/slack_workspaces`, but
         * is otherwise the same as [SlackWorkspaceService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<SlackWorkspaceListPage> = list(SlackWorkspaceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: SlackWorkspaceListParams = SlackWorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SlackWorkspaceListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: SlackWorkspaceListParams = SlackWorkspaceListParams.none()
        ): HttpResponseFor<SlackWorkspaceListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<SlackWorkspaceListPage> =
            list(SlackWorkspaceListParams.none(), requestOptions)
    }
}
