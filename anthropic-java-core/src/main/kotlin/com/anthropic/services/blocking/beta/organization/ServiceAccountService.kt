// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccount
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountArchiveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListPage
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountUpdateParams
import com.anthropic.services.blocking.beta.organization.serviceaccounts.WorkspaceService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ServiceAccountService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ServiceAccountService

    fun workspaces(): WorkspaceService

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
    fun create(params: ServiceAccountCreateParams): BetaServiceAccount =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ServiceAccountCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a service account by its ID (`svac_...`).
     */
    fun retrieve(serviceAccountId: String): BetaServiceAccount =
        retrieve(serviceAccountId, ServiceAccountRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        serviceAccountId: String,
        params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount =
        retrieve(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        serviceAccountId: String,
        params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
    ): BetaServiceAccount = retrieve(serviceAccountId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount

    /** @see retrieve */
    fun retrieve(params: ServiceAccountRetrieveParams): BetaServiceAccount =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(serviceAccountId: String, requestOptions: RequestOptions): BetaServiceAccount =
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
    fun update(serviceAccountId: String): BetaServiceAccount =
        update(serviceAccountId, ServiceAccountUpdateParams.none())

    /** @see update */
    fun update(
        serviceAccountId: String,
        params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount =
        update(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see update */
    fun update(
        serviceAccountId: String,
        params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
    ): BetaServiceAccount = update(serviceAccountId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount

    /** @see update */
    fun update(params: ServiceAccountUpdateParams): BetaServiceAccount =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(serviceAccountId: String, requestOptions: RequestOptions): BetaServiceAccount =
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
    fun list(): ServiceAccountListPage = list(ServiceAccountListParams.none())

    /** @see list */
    fun list(
        params: ServiceAccountListParams = ServiceAccountListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ServiceAccountListPage

    /** @see list */
    fun list(
        params: ServiceAccountListParams = ServiceAccountListParams.none()
    ): ServiceAccountListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ServiceAccountListPage =
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
    fun archive(serviceAccountId: String): BetaServiceAccount =
        archive(serviceAccountId, ServiceAccountArchiveParams.none())

    /** @see archive */
    fun archive(
        serviceAccountId: String,
        params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount =
        archive(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

    /** @see archive */
    fun archive(
        serviceAccountId: String,
        params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
    ): BetaServiceAccount = archive(serviceAccountId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: ServiceAccountArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaServiceAccount

    /** @see archive */
    fun archive(params: ServiceAccountArchiveParams): BetaServiceAccount =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(serviceAccountId: String, requestOptions: RequestOptions): BetaServiceAccount =
        archive(serviceAccountId, ServiceAccountArchiveParams.none(), requestOptions)

    /**
     * A view of [ServiceAccountService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ServiceAccountService.WithRawResponse

        fun workspaces(): WorkspaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/service_accounts?beta=true`, but
         * is otherwise the same as [ServiceAccountService.create].
         */
        @MustBeClosed
        fun create(params: ServiceAccountCreateParams): HttpResponseFor<BetaServiceAccount> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: ServiceAccountCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/service_accounts/{service_account_id}?beta=true`, but is otherwise the
         * same as [ServiceAccountService.retrieve].
         */
        @MustBeClosed
        fun retrieve(serviceAccountId: String): HttpResponseFor<BetaServiceAccount> =
            retrieve(serviceAccountId, ServiceAccountRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            serviceAccountId: String,
            params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount> =
            retrieve(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            serviceAccountId: String,
            params: ServiceAccountRetrieveParams = ServiceAccountRetrieveParams.none(),
        ): HttpResponseFor<BetaServiceAccount> =
            retrieve(serviceAccountId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ServiceAccountRetrieveParams): HttpResponseFor<BetaServiceAccount> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaServiceAccount> =
            retrieve(serviceAccountId, ServiceAccountRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/service_accounts/{service_account_id}?beta=true`, but is otherwise the
         * same as [ServiceAccountService.update].
         */
        @MustBeClosed
        fun update(serviceAccountId: String): HttpResponseFor<BetaServiceAccount> =
            update(serviceAccountId, ServiceAccountUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            serviceAccountId: String,
            params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount> =
            update(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            serviceAccountId: String,
            params: ServiceAccountUpdateParams = ServiceAccountUpdateParams.none(),
        ): HttpResponseFor<BetaServiceAccount> =
            update(serviceAccountId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount>

        /** @see update */
        @MustBeClosed
        fun update(params: ServiceAccountUpdateParams): HttpResponseFor<BetaServiceAccount> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaServiceAccount> =
            update(serviceAccountId, ServiceAccountUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/service_accounts?beta=true`, but
         * is otherwise the same as [ServiceAccountService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<ServiceAccountListPage> = list(ServiceAccountListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ServiceAccountListParams = ServiceAccountListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ServiceAccountListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: ServiceAccountListParams = ServiceAccountListParams.none()
        ): HttpResponseFor<ServiceAccountListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ServiceAccountListPage> =
            list(ServiceAccountListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/service_accounts/{service_account_id}/archive?beta=true`, but is
         * otherwise the same as [ServiceAccountService.archive].
         */
        @MustBeClosed
        fun archive(serviceAccountId: String): HttpResponseFor<BetaServiceAccount> =
            archive(serviceAccountId, ServiceAccountArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            serviceAccountId: String,
            params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount> =
            archive(params.toBuilder().serviceAccountId(serviceAccountId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            serviceAccountId: String,
            params: ServiceAccountArchiveParams = ServiceAccountArchiveParams.none(),
        ): HttpResponseFor<BetaServiceAccount> =
            archive(serviceAccountId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: ServiceAccountArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaServiceAccount>

        /** @see archive */
        @MustBeClosed
        fun archive(params: ServiceAccountArchiveParams): HttpResponseFor<BetaServiceAccount> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            serviceAccountId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaServiceAccount> =
            archive(serviceAccountId, ServiceAccountArchiveParams.none(), requestOptions)
    }
}
