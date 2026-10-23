package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.cost.CostListPageAsync
import com.anthropic.models.beta.organization.analytics.cost.CostListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CostServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CostServiceAsync

    /**
     * Get cost in USD across a date range, broken out by dimension.
     *
     * Returns one row per combination of the requested `group_by[]` dimensions (and time bucket,
     * when `time_bucket_width` is set) that has cost in the range, ranked by spend or ordered by
     * time bucket; every combination is returned across pages. With no `group_by[]` and no
     * `time_bucket_width`, a single row aggregates the whole range. Includes all cost, whether or
     * not it is attributable to a seat user. Requires an API key with the `read:analytics` scope,
     * or an Admin API key.
     */
    fun list(params: CostListParams): CompletableFuture<CostListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CostListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CostListPageAsync>

    /** A view of [CostServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): CostServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/cost?beta=true`, but is
         * otherwise the same as [CostServiceAsync.list].
         */
        fun list(params: CostListParams): CompletableFuture<HttpResponseFor<CostListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: CostListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CostListPageAsync>>
    }
}
