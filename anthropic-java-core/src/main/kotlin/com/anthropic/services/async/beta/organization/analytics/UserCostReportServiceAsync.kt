package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListPageAsync
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface UserCostReportServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserCostReportServiceAsync

    /**
     * Get per-user cost in USD across a date range.
     *
     * Returns one row per user, ranked by spend. Use this to see which users account for the most
     * cost. Only cost attributable to a seat user is included; for organization-wide totals
     * including direct API-key and automation traffic, use the bucketed
     * `/v1/organizations/analytics/cost_report` endpoint. Available to organizations on a Claude
     * Enterprise plan. Requires an API key with the `read:analytics` scope.
     */
    fun list(params: UserCostReportListParams): CompletableFuture<UserCostReportListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: UserCostReportListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserCostReportListPageAsync>

    /**
     * A view of [UserCostReportServiceAsync] that provides access to raw HTTP responses for each
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
        ): UserCostReportServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/analytics/user_cost_report?beta=true`, but is otherwise the same as
         * [UserCostReportServiceAsync.list].
         */
        fun list(
            params: UserCostReportListParams
        ): CompletableFuture<HttpResponseFor<UserCostReportListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: UserCostReportListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserCostReportListPageAsync>>
    }
}
