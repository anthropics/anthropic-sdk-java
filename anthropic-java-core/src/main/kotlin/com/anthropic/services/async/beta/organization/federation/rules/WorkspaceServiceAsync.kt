// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.federation.rules

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRuleWorkspace
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListPageAsync
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface WorkspaceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceServiceAsync

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * List workspaces where this federation rule is enabled.
     *
     * Returns all workspace enablements in a single response; the `limit` and `page` parameters are
     * accepted but have no effect, and `next_page` is always `null`. Returns explicit per-workspace
     * enablements only; for rules with `applies_to_all_workspaces` or a legacy single
     * `workspace_id`, check those fields on the rule itself.
     */
    fun list(federationRuleId: String): CompletableFuture<WorkspaceListPageAsync> =
        list(federationRuleId, WorkspaceListParams.none())

    /** @see list */
    fun list(
        federationRuleId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceListPageAsync> =
        list(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see list */
    fun list(
        federationRuleId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
    ): CompletableFuture<WorkspaceListPageAsync> =
        list(federationRuleId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceListPageAsync>

    /** @see list */
    fun list(params: WorkspaceListParams): CompletableFuture<WorkspaceListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        federationRuleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceListPageAsync> =
        list(federationRuleId, WorkspaceListParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Enable a federation rule for a workspace.
     *
     * Idempotent; re-enabling returns the existing enablement. The rule and workspace must both
     * belong to your organization. Membership of the rule's target service account in this
     * workspace is not checked at enablement: token exchange into this workspace is rejected unless
     * the target is a member (it is implicitly a member of the default workspace). Archived rules
     * are rejected with 400. OAuth callers may only manage rules whose `oauth_scope` is
     * `workspace:developer` or `workspace:inference`; other scopes require a Console session.
     */
    fun add(
        federationRuleId: String,
        params: WorkspaceAddParams,
    ): CompletableFuture<BetaFederationRuleWorkspace> =
        add(federationRuleId, params, RequestOptions.none())

    /** @see add */
    fun add(
        federationRuleId: String,
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRuleWorkspace> =
        add(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see add */
    fun add(params: WorkspaceAddParams): CompletableFuture<BetaFederationRuleWorkspace> =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationRuleWorkspace>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Disable a federation rule for a workspace.
     *
     * Idempotent; succeeds even if the enablement was already removed. OAuth callers may only
     * manage rules whose `oauth_scope` is `workspace:developer` or `workspace:inference`; other
     * scopes require a Console session.
     */
    fun remove(
        workspaceId: String,
        params: WorkspaceRemoveParams,
    ): CompletableFuture<WorkspaceRemoveResponse> =
        remove(workspaceId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        workspaceId: String,
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceRemoveResponse> =
        remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see remove */
    fun remove(params: WorkspaceRemoveParams): CompletableFuture<WorkspaceRemoveResponse> =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WorkspaceRemoveResponse>

    /**
     * A view of [WorkspaceServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/federation_rules/{federation_rule_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceServiceAsync.list].
         */
        fun list(
            federationRuleId: String
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(federationRuleId, WorkspaceListParams.none())

        /** @see list */
        fun list(
            federationRuleId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see list */
        fun list(
            federationRuleId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(federationRuleId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>>

        /** @see list */
        fun list(
            params: WorkspaceListParams
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> =
            list(federationRuleId, WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_rules/{federation_rule_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceServiceAsync.add].
         */
        fun add(
            federationRuleId: String,
            params: WorkspaceAddParams,
        ): CompletableFuture<HttpResponseFor<BetaFederationRuleWorkspace>> =
            add(federationRuleId, params, RequestOptions.none())

        /** @see add */
        fun add(
            federationRuleId: String,
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRuleWorkspace>> =
            add(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see add */
        fun add(
            params: WorkspaceAddParams
        ): CompletableFuture<HttpResponseFor<BetaFederationRuleWorkspace>> =
            add(params, RequestOptions.none())

        /** @see add */
        fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationRuleWorkspace>>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/federation_rules/{federation_rule_id}/workspaces/{workspace_id}?beta=true`,
         * but is otherwise the same as [WorkspaceServiceAsync.remove].
         */
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
        ): CompletableFuture<HttpResponseFor<WorkspaceRemoveResponse>> =
            remove(workspaceId, params, RequestOptions.none())

        /** @see remove */
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceRemoveResponse>> =
            remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see remove */
        fun remove(
            params: WorkspaceRemoveParams
        ): CompletableFuture<HttpResponseFor<WorkspaceRemoveResponse>> =
            remove(params, RequestOptions.none())

        /** @see remove */
        fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WorkspaceRemoveResponse>>
    }
}
