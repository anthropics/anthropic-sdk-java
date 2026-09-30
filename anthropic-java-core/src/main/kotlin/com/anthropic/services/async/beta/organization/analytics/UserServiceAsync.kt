package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.users.UserListPageAsync
import com.anthropic.models.beta.organization.analytics.users.UserListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface UserServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserServiceAsync

    /**
     * Get per-user activity for a given day, with cursor-based pagination.
     *
     * Returns activity metrics for each user in the organization, sorted by email address. Use
     * `group_by[]` for per-RBAC-group aggregates, or `filter[]` to scope results to specific
     * members, groups, or a chat project. Available to organizations on a Claude Enterprise plan.
     * Requires an API key with the `read:analytics` scope.
     */
    fun list(): CompletableFuture<UserListPageAsync> = list(UserListParams.none())

    /** @see list */
    fun list(
        params: UserListParams = UserListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserListPageAsync>

    /** @see list */
    fun list(params: UserListParams = UserListParams.none()): CompletableFuture<UserListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<UserListPageAsync> =
        list(UserListParams.none(), requestOptions)

    /** A view of [UserServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/users?beta=true`, but is
         * otherwise the same as [UserServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<UserListPageAsync>> =
            list(UserListParams.none())

        /** @see list */
        fun list(
            params: UserListParams = UserListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>>

        /** @see list */
        fun list(
            params: UserListParams = UserListParams.none()
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>> =
            list(UserListParams.none(), requestOptions)
    }
}
