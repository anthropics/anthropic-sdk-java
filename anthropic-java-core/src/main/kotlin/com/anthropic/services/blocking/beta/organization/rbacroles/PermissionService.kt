package com.anthropic.services.blocking.beta.organization.rbacroles

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListPage
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface PermissionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PermissionService

    /**
     * List the permissions an RBAC Role grants.
     *
     * The RBAC Roles API is available to Claude Enterprise organizations only.
     */
    fun list(rbacRoleId: String): PermissionListPage = list(rbacRoleId, PermissionListParams.none())

    /** @see list */
    fun list(
        rbacRoleId: String,
        params: PermissionListParams = PermissionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PermissionListPage = list(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

    /** @see list */
    fun list(
        rbacRoleId: String,
        params: PermissionListParams = PermissionListParams.none(),
    ): PermissionListPage = list(rbacRoleId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: PermissionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PermissionListPage

    /** @see list */
    fun list(params: PermissionListParams): PermissionListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(rbacRoleId: String, requestOptions: RequestOptions): PermissionListPage =
        list(rbacRoleId, PermissionListParams.none(), requestOptions)

    /** A view of [PermissionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): PermissionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/rbac_roles/{rbac_role_id}/permissions?beta=true`, but is otherwise the
         * same as [PermissionService.list].
         */
        @MustBeClosed
        fun list(rbacRoleId: String): HttpResponseFor<PermissionListPage> =
            list(rbacRoleId, PermissionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            rbacRoleId: String,
            params: PermissionListParams = PermissionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PermissionListPage> =
            list(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            rbacRoleId: String,
            params: PermissionListParams = PermissionListParams.none(),
        ): HttpResponseFor<PermissionListPage> = list(rbacRoleId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: PermissionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PermissionListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: PermissionListParams): HttpResponseFor<PermissionListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            rbacRoleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PermissionListPage> =
            list(rbacRoleId, PermissionListParams.none(), requestOptions)
    }
}
