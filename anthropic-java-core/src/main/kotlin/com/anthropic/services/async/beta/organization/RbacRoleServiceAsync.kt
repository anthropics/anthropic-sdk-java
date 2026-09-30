package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacroles.BetaRbacRole
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListPageAsync
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListParams
import com.anthropic.models.beta.organization.rbacroles.RbacRoleRetrieveParams
import com.anthropic.services.async.beta.organization.rbacroles.PermissionServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface RbacRoleServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacRoleServiceAsync

    fun permissions(): PermissionServiceAsync

    /**
     * Retrieve an RBAC Role by ID.
     *
     * The RBAC Roles API is available to Claude Enterprise organizations only.
     */
    fun retrieve(rbacRoleId: String): CompletableFuture<BetaRbacRole> =
        retrieve(rbacRoleId, RbacRoleRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        rbacRoleId: String,
        params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacRole> =
        retrieve(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        rbacRoleId: String,
        params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
    ): CompletableFuture<BetaRbacRole> = retrieve(rbacRoleId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacRoleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaRbacRole>

    /** @see retrieve */
    fun retrieve(params: RbacRoleRetrieveParams): CompletableFuture<BetaRbacRole> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        rbacRoleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaRbacRole> =
        retrieve(rbacRoleId, RbacRoleRetrieveParams.none(), requestOptions)

    /**
     * List RBAC Roles in the organization.
     *
     * The RBAC Roles API is available to Claude Enterprise organizations only.
     */
    fun list(): CompletableFuture<RbacRoleListPageAsync> = list(RbacRoleListParams.none())

    /** @see list */
    fun list(
        params: RbacRoleListParams = RbacRoleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RbacRoleListPageAsync>

    /** @see list */
    fun list(
        params: RbacRoleListParams = RbacRoleListParams.none()
    ): CompletableFuture<RbacRoleListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<RbacRoleListPageAsync> =
        list(RbacRoleListParams.none(), requestOptions)

    /**
     * A view of [RbacRoleServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacRoleServiceAsync.WithRawResponse

        fun permissions(): PermissionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/rbac_roles/{rbac_role_id}?beta=true`, but is otherwise the same as
         * [RbacRoleServiceAsync.retrieve].
         */
        fun retrieve(rbacRoleId: String): CompletableFuture<HttpResponseFor<BetaRbacRole>> =
            retrieve(rbacRoleId, RbacRoleRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            rbacRoleId: String,
            params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacRole>> =
            retrieve(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            rbacRoleId: String,
            params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacRole>> =
            retrieve(rbacRoleId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: RbacRoleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaRbacRole>>

        /** @see retrieve */
        fun retrieve(
            params: RbacRoleRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaRbacRole>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            rbacRoleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaRbacRole>> =
            retrieve(rbacRoleId, RbacRoleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/rbac_roles?beta=true`, but is
         * otherwise the same as [RbacRoleServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<RbacRoleListPageAsync>> =
            list(RbacRoleListParams.none())

        /** @see list */
        fun list(
            params: RbacRoleListParams = RbacRoleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RbacRoleListPageAsync>>

        /** @see list */
        fun list(
            params: RbacRoleListParams = RbacRoleListParams.none()
        ): CompletableFuture<HttpResponseFor<RbacRoleListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<RbacRoleListPageAsync>> =
            list(RbacRoleListParams.none(), requestOptions)
    }
}
