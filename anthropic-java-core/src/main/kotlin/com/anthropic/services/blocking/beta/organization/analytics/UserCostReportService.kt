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
     * [Legacy] Get per-user cost in USD across a date range.
     *
     * Returns cost per user, ranked by spend; `group_by[]` or `bucket_width` splits a user across
     * several rows. Use this to see which users account for the most cost. Only cost attributed to
     * a user account is included; cost with no user attributed to it, such as Code Review usage and
     * Claude Tag usage in Slack channels, is left out. Available to organizations on a Claude
     * Enterprise plan. Requires an API key with the `read:analytics` scope.
     *
     * This endpoint is one of the legacy Claude Enterprise usage and cost reports. For new
     * integrations, use
     * [`GET /v1/organizations/analytics/cost`](/docs/en/api/beta/organization/analytics/cost/list)
     * in the Usage and Cost Analytics API instead. To move an existing integration, see
     * [Migrate from the Claude Enterprise usage and cost reports](/docs/en/manage-claude/usage-cost-analytics-api#migrate-from-enterprise-analytics-reports).
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
         * Returns a raw HTTP response for `get /v1/organizations/analytics/user_cost_report`, but
         * is otherwise the same as [UserCostReportService.list].
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
