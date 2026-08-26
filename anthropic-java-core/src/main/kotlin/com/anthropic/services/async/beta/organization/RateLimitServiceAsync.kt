// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.ratelimits.RateLimitListPageAsync
import com.anthropic.models.beta.organization.ratelimits.RateLimitListParams
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
     * List Messages API rate limits for your organization.
     *
     * Each entry corresponds to one rate-limit group (either a model family or an API-surface
     * category such as the Files API or Message Batches) and contains the set of limiter values
     * that apply to it.
     *
     * This endpoint currently returns every matching entry in a single page regardless of `limit`;
     * follow `next_page` so that clients keep working when pagination is enabled.
     */
    fun list(): CompletableFuture<RateLimitListPageAsync> = list(RateLimitListParams.none())

    /** @see list */
    fun list(
        params: RateLimitListParams = RateLimitListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<RateLimitListPageAsync>

    /** @see list */
    fun list(
        params: RateLimitListParams = RateLimitListParams.none()
    ): CompletableFuture<RateLimitListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<RateLimitListPageAsync> =
        list(RateLimitListParams.none(), requestOptions)

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
         * Returns a raw HTTP response for `get /v1/organizations/rate_limits?beta=true`, but is
         * otherwise the same as [RateLimitServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(RateLimitListParams.none())

        /** @see list */
        fun list(
            params: RateLimitListParams = RateLimitListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>>

        /** @see list */
        fun list(
            params: RateLimitListParams = RateLimitListParams.none()
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> =
            list(RateLimitListParams.none(), requestOptions)
    }
}
