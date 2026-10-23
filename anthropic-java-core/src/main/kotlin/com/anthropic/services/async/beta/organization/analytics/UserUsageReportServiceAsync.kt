package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.userusagereport.UserUsageReportListPageAsync
import com.anthropic.models.beta.organization.analytics.userusagereport.UserUsageReportListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface UserUsageReportServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserUsageReportServiceAsync

    /**
     * [Legacy] Get per-user token usage across a date range.
     *
     * Returns usage per user, ranked by the chosen token metric; `group_by[]` or `bucket_width`
     * splits a user across several rows. Use this to see which users consume the most tokens. Only
     * usage attributed to a user account is included; usage with no user attributed to it, such as
     * Code Review usage and Claude Tag usage in Slack channels, is left out. Available to
     * organizations on a Claude Enterprise plan. Requires an API key with the `read:analytics`
     * scope.
     *
     * This endpoint is one of the legacy Claude Enterprise usage and cost reports. For new
     * integrations, use
     * [`GET /v1/organizations/analytics/usage`](/docs/en/api/beta/organization/analytics/usage/list)
     * in the Usage and Cost Analytics API instead. To move an existing integration, see
     * [Migrate from the Claude Enterprise usage and cost reports](/docs/en/manage-claude/usage-cost-analytics-api#migrate-from-enterprise-analytics-reports).
     */
    fun list(params: UserUsageReportListParams): CompletableFuture<UserUsageReportListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: UserUsageReportListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserUsageReportListPageAsync>

    /**
     * A view of [UserUsageReportServiceAsync] that provides access to raw HTTP responses for each
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
        ): UserUsageReportServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/user_usage_report`, but
         * is otherwise the same as [UserUsageReportServiceAsync.list].
         */
        fun list(
            params: UserUsageReportListParams
        ): CompletableFuture<HttpResponseFor<UserUsageReportListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: UserUsageReportListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserUsageReportListPageAsync>>
    }
}
