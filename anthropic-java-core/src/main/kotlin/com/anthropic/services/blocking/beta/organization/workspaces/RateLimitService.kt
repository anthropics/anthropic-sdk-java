// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.workspaces

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListPage
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface RateLimitService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitService

    /**
     * List rate-limit overrides configured for a workspace.
     *
     * Returns only the groups and limiter types that have a workspace-level override. Groups
     * without overrides inherit the organization limits and are not listed; use `GET
     * /v1/organizations/rate_limits` to see those.
     *
     * When `limit` is omitted, every matching entry is returned in a single page; when `limit`
     * truncates the result, follow `next_page` to fetch the remaining entries.
     */
    fun list(workspaceId: String): RateLimitListPage = list(workspaceId, RateLimitListParams.none())

    /** @see list */
    fun list(
        workspaceId: String,
        params: RateLimitListParams = RateLimitListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RateLimitListPage = list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

    /** @see list */
    fun list(
        workspaceId: String,
        params: RateLimitListParams = RateLimitListParams.none(),
    ): RateLimitListPage = list(workspaceId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: RateLimitListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RateLimitListPage

    /** @see list */
    fun list(params: RateLimitListParams): RateLimitListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(workspaceId: String, requestOptions: RequestOptions): RateLimitListPage =
        list(workspaceId, RateLimitListParams.none(), requestOptions)

    /** A view of [RateLimitService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/workspaces/{workspace_id}/rate_limits?beta=true`, but is otherwise the
         * same as [RateLimitService.list].
         */
        @MustBeClosed
        fun list(workspaceId: String): HttpResponseFor<RateLimitListPage> =
            list(workspaceId, RateLimitListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            workspaceId: String,
            params: RateLimitListParams = RateLimitListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RateLimitListPage> =
            list(params.toBuilder().workspaceId(workspaceId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            workspaceId: String,
            params: RateLimitListParams = RateLimitListParams.none(),
        ): HttpResponseFor<RateLimitListPage> = list(workspaceId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: RateLimitListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RateLimitListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: RateLimitListParams): HttpResponseFor<RateLimitListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            workspaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RateLimitListPage> =
            list(workspaceId, RateLimitListParams.none(), requestOptions)
    }
}
