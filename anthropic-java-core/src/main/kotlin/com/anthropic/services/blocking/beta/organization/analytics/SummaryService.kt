package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.summaries.SummaryListPage
import com.anthropic.models.beta.organization.analytics.summaries.SummaryListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface SummaryService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SummaryService

    /**
     * Get organization-wide activity summaries for a date range.
     *
     * Returns one entry per day from `starting_date` (inclusive) to `ending_date` (exclusive) in
     * `data`, the same `data` / `next_page` envelope as the other analytics list endpoints; the
     * series is currently returned in full, so `next_page` is always null. Data is typically
     * available with a 1-day lag and may be revised by a few percent over the following days: when
     * `ending_date` is omitted it defaults to the most recent available day + 1, so the last entry
     * covers the most recent available day. The series can be scoped to an RBAC group via
     * `filter[]=rbac_group_id:{id}`. Available to organizations on a Claude Enterprise plan.
     * Requires an API key with the `read:analytics` scope.
     */
    fun list(params: SummaryListParams): SummaryListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: SummaryListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SummaryListPage

    /** A view of [SummaryService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): SummaryService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/summaries?beta=true`,
         * but is otherwise the same as [SummaryService.list].
         */
        @MustBeClosed
        fun list(params: SummaryListParams): HttpResponseFor<SummaryListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: SummaryListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SummaryListPage>
    }
}
