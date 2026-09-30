package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListPage
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface CostReportService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CostReportService

    /**
     * Get cost in USD over time across a date range.
     *
     * Returns cost bucketed by minute, hour, or day, optionally broken down by product, model,
     * context window, inference region, speed, cost type, or token type. Available to organizations
     * on a Claude Enterprise plan. Requires an API key with the `read:analytics` scope.
     */
    fun list(params: CostReportListParams): CostReportListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CostReportListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CostReportListPage

    /** A view of [CostReportService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CostReportService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/cost_report?beta=true`,
         * but is otherwise the same as [CostReportService.list].
         */
        @MustBeClosed
        fun list(params: CostReportListParams): HttpResponseFor<CostReportListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: CostReportListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CostReportListPage>
    }
}
