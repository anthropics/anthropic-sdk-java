package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListPage
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface UserCostReportService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserCostReportService

    /**
     * Get per-user cost in USD across a date range.
     *
     * Returns one row per user, ranked by spend. Use this to see which users account for the most
     * cost. Only cost attributable to a seat user is included; for organization-wide totals
     * including direct API-key and automation traffic, use the bucketed
     * `/v1/organizations/analytics/cost_report` endpoint. Available to organizations on a Claude
     * Enterprise plan. Requires an API key with the `read:analytics` scope.
     */
    fun list(params: UserCostReportListParams): UserCostReportListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: UserCostReportListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserCostReportListPage

    /**
     * A view of [UserCostReportService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserCostReportService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/analytics/user_cost_report?beta=true`, but is otherwise the same as
         * [UserCostReportService.list].
         */
        @MustBeClosed
        fun list(params: UserCostReportListParams): HttpResponseFor<UserCostReportListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: UserCostReportListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserCostReportListPage>
    }
}
