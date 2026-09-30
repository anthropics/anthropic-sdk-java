package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListPageAsync
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ConnectorServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConnectorServiceAsync

    /**
     * Get per-connector usage for a given day, with cursor-based pagination.
     *
     * Returns connector usage metrics for the organization, sorted by connector name. Connector
     * names are normalized from their various sources — for example, "Atlassian MCP server" and
     * "mcp-atlassian" both appear as "atlassian". Use `group_by[]` to break usage out per member,
     * per RBAC group, or per product surface, and `filter[]` to scope results; the parameter
     * descriptions list the supported dimensions. Available to organizations on a Claude Enterprise
     * plan. Requires an API key with the `read:analytics` scope.
     */
    fun list(): CompletableFuture<ConnectorListPageAsync> = list(ConnectorListParams.none())

    /** @see list */
    fun list(
        params: ConnectorListParams = ConnectorListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ConnectorListPageAsync>

    /** @see list */
    fun list(
        params: ConnectorListParams = ConnectorListParams.none()
    ): CompletableFuture<ConnectorListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ConnectorListPageAsync> =
        list(ConnectorListParams.none(), requestOptions)

    /**
     * A view of [ConnectorServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ConnectorServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/connectors?beta=true`,
         * but is otherwise the same as [ConnectorServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<ConnectorListPageAsync>> =
            list(ConnectorListParams.none())

        /** @see list */
        fun list(
            params: ConnectorListParams = ConnectorListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ConnectorListPageAsync>>

        /** @see list */
        fun list(
            params: ConnectorListParams = ConnectorListParams.none()
        ): CompletableFuture<HttpResponseFor<ConnectorListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ConnectorListPageAsync>> =
            list(ConnectorListParams.none(), requestOptions)
    }
}
