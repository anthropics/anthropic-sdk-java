// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.federation.rules

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRuleWorkspace
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListPage
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveResponse
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface WorkspaceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService

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
    fun list(federationRuleId: String): WorkspaceListPage =
        list(federationRuleId, WorkspaceListParams.none())

    /** @see list */
    fun list(
        federationRuleId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage =
        list(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see list */
    fun list(
        federationRuleId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
    ): WorkspaceListPage = list(federationRuleId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage

    /** @see list */
    fun list(params: WorkspaceListParams): WorkspaceListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(federationRuleId: String, requestOptions: RequestOptions): WorkspaceListPage =
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
    fun add(federationRuleId: String, params: WorkspaceAddParams): BetaFederationRuleWorkspace =
        add(federationRuleId, params, RequestOptions.none())

    /** @see add */
    fun add(
        federationRuleId: String,
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaFederationRuleWorkspace =
        add(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

    /** @see add */
    fun add(params: WorkspaceAddParams): BetaFederationRuleWorkspace =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaFederationRuleWorkspace

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
    fun remove(workspaceId: String, params: WorkspaceRemoveParams): WorkspaceRemoveResponse =
        remove(workspaceId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        workspaceId: String,
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceRemoveResponse =
        remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see remove */
    fun remove(params: WorkspaceRemoveParams): WorkspaceRemoveResponse =
        remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceRemoveResponse

    /** A view of [WorkspaceService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/federation_rules/{federation_rule_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.list].
         */
        @MustBeClosed
        fun list(federationRuleId: String): HttpResponseFor<WorkspaceListPage> =
            list(federationRuleId, WorkspaceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            federationRuleId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceListPage> =
            list(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            federationRuleId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
        ): HttpResponseFor<WorkspaceListPage> =
            list(federationRuleId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: WorkspaceListParams): HttpResponseFor<WorkspaceListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            federationRuleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceListPage> =
            list(federationRuleId, WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_rules/{federation_rule_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.add].
         */
        @MustBeClosed
        fun add(
            federationRuleId: String,
            params: WorkspaceAddParams,
        ): HttpResponseFor<BetaFederationRuleWorkspace> =
            add(federationRuleId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            federationRuleId: String,
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaFederationRuleWorkspace> =
            add(params.toBuilder().federationRuleId(federationRuleId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: WorkspaceAddParams): HttpResponseFor<BetaFederationRuleWorkspace> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaFederationRuleWorkspace>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/federation_rules/{federation_rule_id}/workspaces/{workspace_id}?beta=true`,
         * but is otherwise the same as [WorkspaceService.remove].
         */
        @MustBeClosed
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
        ): HttpResponseFor<WorkspaceRemoveResponse> =
            remove(workspaceId, params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            workspaceId: String,
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceRemoveResponse> =
            remove(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see remove */
        @MustBeClosed
        fun remove(params: WorkspaceRemoveParams): HttpResponseFor<WorkspaceRemoveResponse> =
            remove(params, RequestOptions.none())

        /** @see remove */
        @MustBeClosed
        fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceRemoveResponse>
    }
}
