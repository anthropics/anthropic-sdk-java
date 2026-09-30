package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.usagereport.UsageReportListPage
import com.anthropic.models.beta.organization.analytics.usagereport.UsageReportListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface UsageReportService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UsageReportService

    /**
     * Get token usage over time across a date range.
     *
     * Returns token usage bucketed by minute, hour, or day, optionally broken down by product,
     * model, context window, inference region, or speed. Available to organizations on a Claude
     * Enterprise plan. Requires an API key with the `read:analytics` scope.
     */
    fun list(params: UsageReportListParams): UsageReportListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: UsageReportListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UsageReportListPage

    /**
     * A view of [UsageReportService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UsageReportService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/usage_report?beta=true`,
         * but is otherwise the same as [UsageReportService.list].
         */
        @MustBeClosed
        fun list(params: UsageReportListParams): HttpResponseFor<UsageReportListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: UsageReportListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UsageReportListPage>
    }
}
