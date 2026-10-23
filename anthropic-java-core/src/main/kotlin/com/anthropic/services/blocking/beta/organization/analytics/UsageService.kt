package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.usage.UsageListPage
import com.anthropic.models.beta.organization.analytics.usage.UsageListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface UsageService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UsageService

    /**
     * Get token usage across a date range, broken out by dimension.
     *
     * Returns one row per combination of the requested `group_by[]` dimensions (and time bucket,
     * when `time_bucket_width` is set) that has usage in the range, ranked by the `order_by` metric
     * or ordered by time bucket; every combination is returned across pages. With no `group_by[]`
     * and no `time_bucket_width`, a single row aggregates the whole range. Includes all usage,
     * whether or not it is attributable to a seat user. Requires an API key with the
     * `read:analytics` scope, or an Admin API key.
     */
    fun list(params: UsageListParams): UsageListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: UsageListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UsageListPage

    /** A view of [UsageService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): UsageService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/usage?beta=true`, but is
         * otherwise the same as [UsageService.list].
         */
        @MustBeClosed
        fun list(params: UsageListParams): HttpResponseFor<UsageListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: UsageListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UsageListPage>
    }
}
