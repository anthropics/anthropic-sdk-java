package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.plugins.PluginListPageAsync
import com.anthropic.models.beta.organization.analytics.plugins.PluginListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface PluginServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginServiceAsync

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
    fun list(): CompletableFuture<PluginListPageAsync> = list(PluginListParams.none())

    /** @see list */
    fun list(
        params: PluginListParams = PluginListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<PluginListPageAsync>

    /** @see list */
    fun list(
        params: PluginListParams = PluginListParams.none()
    ): CompletableFuture<PluginListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<PluginListPageAsync> =
        list(PluginListParams.none(), requestOptions)

    /**
     * A view of [PluginServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): PluginServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/plugins?beta=true`, but
         * is otherwise the same as [PluginServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<PluginListPageAsync>> =
            list(PluginListParams.none())

        /** @see list */
        fun list(
            params: PluginListParams = PluginListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<PluginListPageAsync>>

        /** @see list */
        fun list(
            params: PluginListParams = PluginListParams.none()
        ): CompletableFuture<HttpResponseFor<PluginListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<PluginListPageAsync>> =
            list(PluginListParams.none(), requestOptions)
    }
}
