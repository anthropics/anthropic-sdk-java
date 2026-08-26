// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.workspaces

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccountWorkspaceMember
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountListPageAsync
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRemoveResponse
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ServiceAccountServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ServiceAccountServiceAsync

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a service account's membership in a workspace.
     *
     * Returns the membership record, including the service account's `workspace_role` in this
     * workspace. Archived workspaces return 400. For the default workspace, returns the implicit
     * (`implicit: true`) membership when no explicit membership exists; an explicitly added
     * membership is returned with its assigned role. An archived service account returns 404.
     */
    fun retrieve(
        serviceAccountId: String,
        params: ServiceAccountRetrieveParams,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        retrieve(serviceAccountId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        serviceAccountId: String,
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        retrieve(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: ServiceAccountRetrieveParams
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccountWorkspaceMember>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Change a service account's role in a workspace.
     *
     * The new `workspace_role` replaces the current one. Only explicit memberships can be updated;
     * to set a role on the implicit default-workspace membership, add the service account
     * explicitly with `POST /workspaces/{workspace_id}/service_accounts`. Archived workspaces
     * return 400. Archived service accounts cannot be updated and are rejected.
     */
    fun update(
        serviceAccountId: String,
        params: ServiceAccountUpdateParams,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        update(serviceAccountId, params, RequestOptions.none())

    /** @see update */
    fun update(
        serviceAccountId: String,
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        update(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see update */
    fun update(
        params: ServiceAccountUpdateParams
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> = update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccountWorkspaceMember>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * List the service accounts that are members of a workspace.
     *
     * Each entry includes the service account's `workspace_role`. Use `limit` and the `next_page`
     * cursor to paginate. Archived workspaces return 400; use `GET
     * /service_accounts/{id}/workspaces` to audit memberships of an archived workspace. The
     * implicit default-workspace membership is not included in this list. Memberships of archived
     * service accounts are omitted from the results.
     */
    fun list(workspaceId: String): CompletableFuture<ServiceAccountListPageAsync> =
        list(workspaceId, ServiceAccountListParams.none())

    /** @see list */
    fun list(
        workspaceId: String,
        params: ServiceAccountListParams = ServiceAccountListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ServiceAccountListPageAsync> =
        list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see list */
    fun list(
        workspaceId: String,
        params: ServiceAccountListParams = ServiceAccountListParams.none(),
    ): CompletableFuture<ServiceAccountListPageAsync> =
        list(workspaceId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ServiceAccountListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ServiceAccountListPageAsync>

    /** @see list */
    fun list(params: ServiceAccountListParams): CompletableFuture<ServiceAccountListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountListPageAsync> =
        list(workspaceId, ServiceAccountListParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Add a service account to a workspace with the given `workspace_role`.
     *
     * The role determines what the service account can do in the workspace and which
     * workspace-scoped permissions it can be granted when authenticating through federation. Every
     * service account is already an implicit `workspace_user` member of the default workspace;
     * adding it explicitly assigns a chosen role. If the service account is already an explicit
     * member of the workspace, its `workspace_role` is replaced with the value supplied here.
     * Archived workspaces return 400. Archived service accounts cannot be added and are rejected.
     */
    fun add(
        workspaceId: String,
        params: ServiceAccountAddParams,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        add(workspaceId, params, RequestOptions.none())

    /** @see add */
    fun add(
        workspaceId: String,
        params: ServiceAccountAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see add */
    fun add(params: ServiceAccountAddParams): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: ServiceAccountAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccountWorkspaceMember>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Remove a service account from a workspace.
     *
     * Removal is idempotent (returns 200 even if the membership was already removed). A DELETE
     * against the implicit default-workspace membership returns 200 but is a no-op and the
     * membership persists; deleting an explicit default-workspace row reverts to the implicit
     * `workspace_user` membership. Archived workspaces return 400.
     */
    fun remove(
        serviceAccountId: String,
        params: ServiceAccountRemoveParams,
    ): CompletableFuture<ServiceAccountRemoveResponse> =
        remove(serviceAccountId, params, RequestOptions.none())

    /** @see remove */
    fun remove(
        serviceAccountId: String,
        params: ServiceAccountRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ServiceAccountRemoveResponse> =
        remove(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see remove */
    fun remove(
        params: ServiceAccountRemoveParams
    ): CompletableFuture<ServiceAccountRemoveResponse> = remove(params, RequestOptions.none())

    /** @see remove */
    fun remove(
        params: ServiceAccountRemoveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ServiceAccountRemoveResponse>

    /**
     * A view of [ServiceAccountServiceAsync] that provides access to raw HTTP responses for each
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
        ): ServiceAccountServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}?beta=true`,
         * but is otherwise the same as [ServiceAccountServiceAsync.retrieve].
         */
        fun retrieve(
            serviceAccountId: String,
            params: ServiceAccountRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            retrieve(serviceAccountId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            serviceAccountId: String,
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            retrieve(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: ServiceAccountRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}?beta=true`,
         * but is otherwise the same as [ServiceAccountServiceAsync.update].
         */
        fun update(
            serviceAccountId: String,
            params: ServiceAccountUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            update(serviceAccountId, params, RequestOptions.none())

        /** @see update */
        fun update(
            serviceAccountId: String,
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            update(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see update */
        fun update(
            params: ServiceAccountUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/service_accounts?beta=true`, but is otherwise
         * the same as [ServiceAccountServiceAsync.list].
         */
        fun list(
            workspaceId: String
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(workspaceId, ServiceAccountListParams.none())

        /** @see list */
        fun list(
            workspaceId: String,
            params: ServiceAccountListParams = ServiceAccountListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see list */
        fun list(
            workspaceId: String,
            params: ServiceAccountListParams = ServiceAccountListParams.none(),
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(workspaceId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: ServiceAccountListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>>

        /** @see list */
        fun list(
            params: ServiceAccountListParams
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(workspaceId, ServiceAccountListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/workspaces/{workspace_id}/service_accounts?beta=true`, but is otherwise
         * the same as [ServiceAccountServiceAsync.add].
         */
        fun add(
            workspaceId: String,
            params: ServiceAccountAddParams,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            add(workspaceId, params, RequestOptions.none())

        /** @see add */
        fun add(
            workspaceId: String,
            params: ServiceAccountAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            add(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see add */
        fun add(
            params: ServiceAccountAddParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> =
            add(params, RequestOptions.none())

        /** @see add */
        fun add(
            params: ServiceAccountAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>>

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}?beta=true`,
         * but is otherwise the same as [ServiceAccountServiceAsync.remove].
         */
        fun remove(
            serviceAccountId: String,
            params: ServiceAccountRemoveParams,
        ): CompletableFuture<HttpResponseFor<ServiceAccountRemoveResponse>> =
            remove(serviceAccountId, params, RequestOptions.none())

        /** @see remove */
        fun remove(
            serviceAccountId: String,
            params: ServiceAccountRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ServiceAccountRemoveResponse>> =
            remove(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see remove */
        fun remove(
            params: ServiceAccountRemoveParams
        ): CompletableFuture<HttpResponseFor<ServiceAccountRemoveResponse>> =
            remove(params, RequestOptions.none())

        /** @see remove */
        fun remove(
            params: ServiceAccountRemoveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ServiceAccountRemoveResponse>>
    }
}
