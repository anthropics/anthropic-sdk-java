package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacroles.BetaRbacRole
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListPage
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListParams
import com.anthropic.models.beta.organization.rbacroles.RbacRoleRetrieveParams
import com.anthropic.services.blocking.beta.organization.rbacroles.PermissionService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface RbacRoleService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacRoleService

    fun permissions(): PermissionService

    /**
     * Retrieve an RBAC Role by ID.
     *
     * The RBAC Roles API is available to Claude Enterprise organizations only.
     */
    fun retrieve(rbacRoleId: String): BetaRbacRole =
        retrieve(rbacRoleId, RbacRoleRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        rbacRoleId: String,
        params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaRbacRole = retrieve(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        rbacRoleId: String,
        params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
    ): BetaRbacRole = retrieve(rbacRoleId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: RbacRoleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaRbacRole

    /** @see retrieve */
    fun retrieve(params: RbacRoleRetrieveParams): BetaRbacRole =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(rbacRoleId: String, requestOptions: RequestOptions): BetaRbacRole =
        retrieve(rbacRoleId, RbacRoleRetrieveParams.none(), requestOptions)

    /**
     * List RBAC Roles in the organization.
     *
     * The RBAC Roles API is available to Claude Enterprise organizations only.
     */
    fun list(): RbacRoleListPage = list(RbacRoleListParams.none())

    /** @see list */
    fun list(
        params: RbacRoleListParams = RbacRoleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RbacRoleListPage

    /** @see list */
    fun list(params: RbacRoleListParams = RbacRoleListParams.none()): RbacRoleListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): RbacRoleListPage =
        list(RbacRoleListParams.none(), requestOptions)

    /** A view of [RbacRoleService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacRoleService.WithRawResponse

        fun permissions(): PermissionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/rbac_roles/{rbac_role_id}?beta=true`, but is otherwise the same as
         * [RbacRoleService.retrieve].
         */
        @MustBeClosed
        fun retrieve(rbacRoleId: String): HttpResponseFor<BetaRbacRole> =
            retrieve(rbacRoleId, RbacRoleRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacRoleId: String,
            params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaRbacRole> =
            retrieve(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacRoleId: String,
            params: RbacRoleRetrieveParams = RbacRoleRetrieveParams.none(),
        ): HttpResponseFor<BetaRbacRole> = retrieve(rbacRoleId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: RbacRoleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaRbacRole>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: RbacRoleRetrieveParams): HttpResponseFor<BetaRbacRole> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            rbacRoleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaRbacRole> =
            retrieve(rbacRoleId, RbacRoleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/rbac_roles?beta=true`, but is
         * otherwise the same as [RbacRoleService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<RbacRoleListPage> = list(RbacRoleListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: RbacRoleListParams = RbacRoleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RbacRoleListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: RbacRoleListParams = RbacRoleListParams.none()
        ): HttpResponseFor<RbacRoleListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<RbacRoleListPage> =
            list(RbacRoleListParams.none(), requestOptions)
    }
}
