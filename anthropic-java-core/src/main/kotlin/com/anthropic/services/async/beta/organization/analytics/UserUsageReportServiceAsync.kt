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
     * Get per-user token usage across a date range.
     *
     * Returns one row per user, ranked by the chosen token metric. Use this to see which users
     * consume the most tokens. Only usage attributable to a seat user is included; for
     * organization-wide totals including direct API-key and automation traffic, use the bucketed
     * `/v1/organizations/analytics/usage_report` endpoint. Available to organizations on a Claude
     * Enterprise plan. Requires an API key with the `read:analytics` scope.
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
         * Returns a raw HTTP response for `get
         * /v1/organizations/analytics/user_usage_report?beta=true`, but is otherwise the same as
         * [UserUsageReportServiceAsync.list].
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
