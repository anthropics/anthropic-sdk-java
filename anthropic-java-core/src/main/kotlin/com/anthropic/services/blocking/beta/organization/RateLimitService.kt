// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.ratelimits.RateLimitListPage
import com.anthropic.models.beta.organization.ratelimits.RateLimitListParams
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
     * List Messages API rate limits for your organization.
     *
     * Each entry corresponds to one rate-limit group (either a model family or an API-surface
     * category such as the Files API or Message Batches) and contains the set of limiter values
     * that apply to it.
     *
     * This endpoint currently returns every matching entry in a single page regardless of `limit`;
     * follow `next_page` so that clients keep working when pagination is enabled.
     */
    fun list(): RateLimitListPage = list(RateLimitListParams.none())

    /** @see list */
    fun list(
        params: RateLimitListParams = RateLimitListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RateLimitListPage

    /** @see list */
    fun list(params: RateLimitListParams = RateLimitListParams.none()): RateLimitListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): RateLimitListPage =
        list(RateLimitListParams.none(), requestOptions)

    /** A view of [RateLimitService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/rate_limits?beta=true`, but is
         * otherwise the same as [RateLimitService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<RateLimitListPage> = list(RateLimitListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: RateLimitListParams = RateLimitListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RateLimitListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: RateLimitListParams = RateLimitListParams.none()
        ): HttpResponseFor<RateLimitListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<RateLimitListPage> =
            list(RateLimitListParams.none(), requestOptions)
    }
}
