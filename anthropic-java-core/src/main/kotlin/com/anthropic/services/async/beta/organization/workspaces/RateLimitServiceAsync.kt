// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.workspaces

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListPageAsync
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface RateLimitServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitServiceAsync

    /**
     * List rate-limit overrides configured for a workspace.
     *
     * Returns only the groups and limiter types that have a workspace-level override. Groups
     * without overrides inherit the organization limits and are not listed; use `GET
     * /v1/organizations/rate_limits` to see those.
     *
     * This endpoint currently returns every matching entry in a single page regardless of `limit`;
     * follow `next_page` so that clients keep working when pagination is enabled.
     */
    fun list(workspaceId: String): CompletableFuture<RateLimitListPageAsync> =
        list(workspaceId, RateLimitListParams.none())

    /** @see list */
    fun list(
        workspaceId: String,
        params: RateLimitListParams = RateLimitListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RateLimitListPageAsync> =
        list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see list */
    fun list(
        workspaceId: String,
        params: RateLimitListParams = RateLimitListParams.none(),
    ): CompletableFuture<RateLimitListPageAsync> = list(workspaceId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: RateLimitListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RateLimitListPageAsync>

    /** @see list */
    fun list(params: RateLimitListParams): CompletableFuture<RateLimitListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        workspaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<RateLimitListPageAsync> =
        list(workspaceId, RateLimitListParams.none(), requestOptions)

    /**
     * A view of [RateLimitServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RateLimitServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/rate_limits?beta=true`, but is otherwise the
         * same as [RateLimitServiceAsync.list].
         */
        fun list(workspaceId: String): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(workspaceId, RateLimitListParams.none())

        /** @see list */
        fun list(
            workspaceId: String,
            params: RateLimitListParams = RateLimitListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see list */
        fun list(
            workspaceId: String,
            params: RateLimitListParams = RateLimitListParams.none(),
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(workspaceId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: RateLimitListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>>

        /** @see list */
        fun list(
            params: RateLimitListParams
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(workspaceId, RateLimitListParams.none(), requestOptions)
    }
}
