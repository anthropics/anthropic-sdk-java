package com.anthropic.services.async.beta.organization.rbacroles

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListPageAsync
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface PermissionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PermissionServiceAsync

    /**
     * List the permissions an RBAC Role grants.
     *
     * The RBAC Roles API is available to Claude Enterprise organizations only.
     */
    fun list(rbacRoleId: String): CompletableFuture<PermissionListPageAsync> =
        list(rbacRoleId, PermissionListParams.none())

    /** @see list */
    fun list(
        rbacRoleId: String,
        params: PermissionListParams = PermissionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<PermissionListPageAsync> =
        list(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

    /** @see list */
    fun list(
        rbacRoleId: String,
        params: PermissionListParams = PermissionListParams.none(),
    ): CompletableFuture<PermissionListPageAsync> = list(rbacRoleId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: PermissionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<PermissionListPageAsync>

    /** @see list */
    fun list(params: PermissionListParams): CompletableFuture<PermissionListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        rbacRoleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<PermissionListPageAsync> =
        list(rbacRoleId, PermissionListParams.none(), requestOptions)

    /**
     * A view of [PermissionServiceAsync] that provides access to raw HTTP responses for each
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
        ): PermissionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/rbac_roles/{rbac_role_id}/permissions?beta=true`, but is otherwise the
         * same as [PermissionServiceAsync.list].
         */
        fun list(rbacRoleId: String): CompletableFuture<HttpResponseFor<PermissionListPageAsync>> =
            list(rbacRoleId, PermissionListParams.none())

        /** @see list */
        fun list(
            rbacRoleId: String,
            params: PermissionListParams = PermissionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<PermissionListPageAsync>> =
            list(params.toBuilder().rbacRoleId(rbacRoleId).build(), requestOptions)

        /** @see list */
        fun list(
            rbacRoleId: String,
            params: PermissionListParams = PermissionListParams.none(),
        ): CompletableFuture<HttpResponseFor<PermissionListPageAsync>> =
            list(rbacRoleId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: PermissionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<PermissionListPageAsync>>

        /** @see list */
        fun list(
            params: PermissionListParams
        ): CompletableFuture<HttpResponseFor<PermissionListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            rbacRoleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<PermissionListPageAsync>> =
            list(rbacRoleId, PermissionListParams.none(), requestOptions)
    }
}
