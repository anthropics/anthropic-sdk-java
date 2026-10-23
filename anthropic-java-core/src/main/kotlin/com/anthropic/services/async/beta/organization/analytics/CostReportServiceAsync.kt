package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListPageAsync
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CostReportServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CostReportServiceAsync

    /**
     * [Legacy] Get cost in USD over time across a date range.
     *
     * Returns cost bucketed by minute, hour, or day, optionally broken down by one or more
     * `group_by[]` dimensions, such as model or cost type. Available to organizations on a Claude
     * Enterprise plan. Requires an API key with the `read:analytics` scope.
     *
     * This endpoint is one of the legacy Claude Enterprise usage and cost reports. For new
     * integrations, use
     * [`GET /v1/organizations/analytics/cost`](/docs/en/api/beta/organization/analytics/cost/list)
     * in the Usage and Cost Analytics API instead. To move an existing integration, see
     * [Migrate from the Claude Enterprise usage and cost reports](/docs/en/manage-claude/usage-cost-analytics-api#migrate-from-enterprise-analytics-reports).
     */
    fun list(params: CostReportListParams): CompletableFuture<CostReportListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CostReportListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CostReportListPageAsync>

    /**
     * A view of [CostReportServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CostReportServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/cost_report`, but is
         * otherwise the same as [CostReportServiceAsync.list].
         */
        fun list(
            params: CostReportListParams
        ): CompletableFuture<HttpResponseFor<CostReportListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: CostReportListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CostReportListPageAsync>>
    }
}
