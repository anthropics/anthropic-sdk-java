// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccount
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountArchiveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListPageAsync
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountUpdateParams
import com.anthropic.services.async.beta.organization.serviceaccounts.WorkspaceServiceAsync
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

    fun workspaces(): WorkspaceServiceAsync

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Create a service account.
     *
     * A service account is a named workload identity that federation rules target.
     * `organization_role` is `developer` (default) or `admin`; a rule may only be created or
     * retargeted to grant `org:admin` scope when the target's `organization_role` is `admin`.
     * Creating an `admin`-role service account requires an interactive credential (a user OAuth
     * token or a Console session) — a workload may only create `developer`-role service accounts.
     */
    fun create(params: ServiceAccountCreateParams): CompletableFuture<BetaServiceAccount> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ServiceAccountCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a service account by its ID (`svac_...`).
     */
    fun retrieve(serviceAccountId: String): CompletableFuture<BetaServiceAccount> =
        retrieve(serviceAccountId, ServiceAccountRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        serviceAccountId: String,
        params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount> =
        retrieve(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        serviceAccountId: String,
        params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
    ): CompletableFuture<BetaServiceAccount> =
        retrieve(serviceAccountId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount>

    /** @see retrieve */
    fun retrieve(params: ServiceAccountRetrieveParams): CompletableFuture<BetaServiceAccount> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        serviceAccountId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        retrieve(serviceAccountId, ServiceAccountRetrieveParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Update a service account.
     *
     * Only `description` and `organization_role` are mutable; `name` cannot be changed. Archived
     * service accounts cannot be updated; this returns 400. Setting `organization_role` to `admin`
     * (even when unchanged) requires an interactive credential (a user OAuth token or a Console
     * session).
     */
    fun update(serviceAccountId: String): CompletableFuture<BetaServiceAccount> =
        update(serviceAccountId, ServiceAccountUpdateParams.none())

    /** @see update */
    fun update(
        serviceAccountId: String,
        params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount> =
        update(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see update */
    fun update(
        serviceAccountId: String,
        params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
    ): CompletableFuture<BetaServiceAccount> =
        update(serviceAccountId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount>

    /** @see update */
    fun update(params: ServiceAccountUpdateParams): CompletableFuture<BetaServiceAccount> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        serviceAccountId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        update(serviceAccountId, ServiceAccountUpdateParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * List service accounts in the caller's organization.
     *
     * Results are ordered by creation time, newest first. Use `limit` and the `next_page` cursor to
     * paginate; set `include_archived=true` to include archived service accounts.
     */
    fun list(): CompletableFuture<ServiceAccountListPageAsync> =
        list(ServiceAccountListParams.none())

    /** @see list */
    fun list(
        params: ServiceAccountListParams = ServiceAccountListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ServiceAccountListPageAsync>

    /** @see list */
    fun list(
        params: ServiceAccountListParams = ServiceAccountListParams.none()
    ): CompletableFuture<ServiceAccountListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ServiceAccountListPageAsync> =
        list(ServiceAccountListParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Archive a service account.
     *
     * Idempotent; re-archiving returns the service account with its original `archived_at`.
     * Rejected with 400 if any live (non-archived) federation rule still targets this service
     * account, same as issuer archival; archive those rules first or change their target to another
     * service account.
     */
    fun archive(serviceAccountId: String): CompletableFuture<BetaServiceAccount> =
        archive(serviceAccountId, ServiceAccountArchiveParams.none())

    /** @see archive */
    fun archive(
        serviceAccountId: String,
        params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount> =
        archive(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see archive */
    fun archive(
        serviceAccountId: String,
        params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
    ): CompletableFuture<BetaServiceAccount> =
        archive(serviceAccountId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: ServiceAccountArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaServiceAccount>

    /** @see archive */
    fun archive(params: ServiceAccountArchiveParams): CompletableFuture<BetaServiceAccount> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        serviceAccountId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        archive(serviceAccountId, ServiceAccountArchiveParams.none(), requestOptions)

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

        fun workspaces(): WorkspaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/service_accounts?beta=true`, but
         * is otherwise the same as [ServiceAccountServiceAsync.create].
         */
        fun create(
            params: ServiceAccountCreateParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: ServiceAccountCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/service_accounts/{service_account_id}?beta=true`, but is otherwise the
         * same as [ServiceAccountServiceAsync.retrieve].
         */
        fun retrieve(
            serviceAccountId: String
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            retrieve(serviceAccountId, ServiceAccountRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            serviceAccountId: String,
            params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            retrieve(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            serviceAccountId: String,
            params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            retrieve(serviceAccountId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>>

        /** @see retrieve */
        fun retrieve(
            params: ServiceAccountRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            retrieve(serviceAccountId, ServiceAccountRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/service_accounts/{service_account_id}?beta=true`, but is otherwise the
         * same as [ServiceAccountServiceAsync.update].
         */
        fun update(
            serviceAccountId: String
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            update(serviceAccountId, ServiceAccountUpdateParams.none())

        /** @see update */
        fun update(
            serviceAccountId: String,
            params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            update(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see update */
        fun update(
            serviceAccountId: String,
            params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            update(serviceAccountId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>>

        /** @see update */
        fun update(
            params: ServiceAccountUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            update(serviceAccountId, ServiceAccountUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/service_accounts?beta=true`, but
         * is otherwise the same as [ServiceAccountServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(ServiceAccountListParams.none())

        /** @see list */
        fun list(
            params: ServiceAccountListParams = ServiceAccountListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>>

        /** @see list */
        fun list(
            params: ServiceAccountListParams = ServiceAccountListParams.none()
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> =
            list(ServiceAccountListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/service_accounts/{service_account_id}/archive?beta=true`, but is
         * otherwise the same as [ServiceAccountServiceAsync.archive].
         */
        fun archive(
            serviceAccountId: String
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            archive(serviceAccountId, ServiceAccountArchiveParams.none())

        /** @see archive */
        fun archive(
            serviceAccountId: String,
            params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            archive(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see archive */
        fun archive(
            serviceAccountId: String,
            params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            archive(serviceAccountId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: ServiceAccountArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>>

        /** @see archive */
        fun archive(
            params: ServiceAccountArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> =
            archive(serviceAccountId, ServiceAccountArchiveParams.none(), requestOptions)
    }
}
