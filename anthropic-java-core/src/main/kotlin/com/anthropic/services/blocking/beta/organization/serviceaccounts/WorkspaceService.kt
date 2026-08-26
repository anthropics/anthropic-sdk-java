// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.serviceaccounts

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccountWorkspaceMember
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceListPage
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveResponse
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
     * List the workspaces a service account is a member of.
     *
     * Each entry includes the service account's `workspace_role` in that workspace. Use `limit` and
     * the `next_page` cursor to paginate. When the service account has no explicit
     * default-workspace membership, the implicit (`implicit: true`) membership is returned as the
     * first entry on the first page; with `limit=1` the first page may return up to 2 entries (the
     * implicit entry plus one explicit membership) so a pagination cursor can be derived.
     * Memberships are returned only while the service account is active. Without a `page` cursor,
     * an archived service account returns an empty list. A `page` cursor that does not match an
     * active membership returns a 400 invalid-request error. A cursor stops matching when the
     * membership is removed, the workspace is deleted, or the service account is archived. Restart
     * pagination from the first page to recover.
     */
    fun list(serviceAccountId: String): WorkspaceListPage =
        list(serviceAccountId, WorkspaceListParams.none())

    /** @see list */
    fun list(
        serviceAccountId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage =
        list(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see list */
    fun list(
        serviceAccountId: String,
        params: WorkspaceListParams = WorkspaceListParams.none(),
    ): WorkspaceListPage = list(serviceAccountId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WorkspaceListPage

    /** @see list */
    fun list(params: WorkspaceListParams): WorkspaceListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(serviceAccountId: String, requestOptions: RequestOptions): WorkspaceListPage =
        list(serviceAccountId, WorkspaceListParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Add a service account to a workspace with the given `workspace_role`.
     *
     * Mirror of `POST /workspaces/{workspace_id}/service_accounts`, addressed from the
     * service-account side; both create the same membership. If the service account is already an
     * explicit member of the workspace, its `workspace_role` is replaced with the value supplied
     * here. Archived workspaces return 400. Archived service accounts cannot be added and are
     * rejected.
     */
    fun add(
        serviceAccountId: String,
        params: WorkspaceAddParams,
    ): BetaServiceAccountWorkspaceMember = add(serviceAccountId, params, RequestOptions.none())

    /** @see add */
    fun add(
        serviceAccountId: String,
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccountWorkspaceMember =
        add(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see add */
    fun add(params: WorkspaceAddParams): BetaServiceAccountWorkspaceMember =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccountWorkspaceMember

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Remove a service account from a workspace.
     *
     * Mirror of `DELETE /workspaces/{workspace_id}/service_accounts/{service_account_id}`,
     * addressed from the service-account side. Removal is idempotent (returns 200 even if the
     * membership was already removed). A DELETE against the implicit default-workspace membership
     * returns 200 but is a no-op and the membership persists; deleting an explicit
     * default-workspace row reverts to the implicit `workspace_user` membership. Archived
     * workspaces return 400.
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
         * /v1/organizations/service_accounts/{service_account_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.list].
         */
        @MustBeClosed
        fun list(serviceAccountId: String): HttpResponseFor<WorkspaceListPage> =
            list(serviceAccountId, WorkspaceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            serviceAccountId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WorkspaceListPage> =
            list(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            serviceAccountId: String,
            params: WorkspaceListParams = WorkspaceListParams.none(),
        ): HttpResponseFor<WorkspaceListPage> =
            list(serviceAccountId, params, RequestOptions.none())

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
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceListPage> =
            list(serviceAccountId, WorkspaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/service_accounts/{service_account_id}/workspaces?beta=true`, but is
         * otherwise the same as [WorkspaceService.add].
         */
        @MustBeClosed
        fun add(
            serviceAccountId: String,
            params: WorkspaceAddParams,
        ): HttpResponseFor<BetaServiceAccountWorkspaceMember> =
            add(serviceAccountId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            serviceAccountId: String,
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccountWorkspaceMember> =
            add(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: WorkspaceAddParams): HttpResponseFor<BetaServiceAccountWorkspaceMember> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccountWorkspaceMember>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/service_accounts/{service_account_id}/workspaces/{workspace_id}?beta=true`,
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
