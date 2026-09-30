package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListPage
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ConnectorService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConnectorService

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
    fun list(): ConnectorListPage = list(ConnectorListParams.none())

    /** @see list */
    fun list(
        params: ConnectorListParams = ConnectorListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ConnectorListPage

    /** @see list */
    fun list(params: ConnectorListParams = ConnectorListParams.none()): ConnectorListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ConnectorListPage =
        list(ConnectorListParams.none(), requestOptions)

    /** A view of [ConnectorService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConnectorService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/analytics/connectors?beta=true`,
         * but is otherwise the same as [ConnectorService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<ConnectorListPage> = list(ConnectorListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ConnectorListParams = ConnectorListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ConnectorListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: ConnectorListParams = ConnectorListParams.none()
        ): HttpResponseFor<ConnectorListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ConnectorListPage> =
            list(ConnectorListParams.none(), requestOptions)
    }
}
