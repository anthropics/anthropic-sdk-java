package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.plugins.PluginListPage
import com.anthropic.models.beta.organization.analytics.plugins.PluginListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface PluginService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginService

    /**
     * Get per-plugin install + invocation usage for a given day, with pagination.
     *
     * Returns plugin usage metrics for the organization across Cowork and Claude Code, sorted by
     * plugin name. The `plugin_name` value `third-party` is an aggregate bucket, not a plugin: it
     * collects plugin activity, from either surface, for which the reporting client did not provide
     * a plugin name — so an organization's own plugins can contribute both to their own named rows
     * and to this bucket. Use `group_by[]` to break usage out per member, per RBAC group, or per
     * product surface (Cowork / Claude Code), and `filter[]` to scope results; the parameter
     * descriptions list the supported dimensions. Requires an API key with the `read:analytics`
     * scope. `starting_date` / `ending_date` select range-rollup mode like `/skills`.
     */
    fun list(): PluginListPage = list(PluginListParams.none())

    /** @see list */
    fun list(
        params: PluginListParams = PluginListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PluginListPage

    /** @see list */
    fun list(params: PluginListParams = PluginListParams.none()): PluginListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): PluginListPage =
        list(PluginListParams.none(), requestOptions)

    /** A view of [PluginService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/plugins?beta=true`, but
         * is otherwise the same as [PluginService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<PluginListPage> = list(PluginListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: PluginListParams = PluginListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PluginListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: PluginListParams = PluginListParams.none()
        ): HttpResponseFor<PluginListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<PluginListPage> =
            list(PluginListParams.none(), requestOptions)
    }
}
