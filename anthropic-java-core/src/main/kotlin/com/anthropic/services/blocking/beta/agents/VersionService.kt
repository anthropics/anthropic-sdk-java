// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.agents

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.agents.versions.VersionListPage
import com.anthropic.models.beta.agents.versions.VersionListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface VersionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionService

    /** List Agent Versions */
    fun list(agentId: String): VersionListPage = list(agentId, VersionListParams.none())

    /** @see list */
    fun list(
        agentId: String,
        params: VersionListParams = VersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionListPage = list(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see list */
    fun list(
        agentId: String,
        params: VersionListParams = VersionListParams.none(),
    ): VersionListPage = list(agentId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: VersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionListPage

    /** @see list */
    fun list(params: VersionListParams): VersionListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(agentId: String, requestOptions: RequestOptions): VersionListPage =
        list(agentId, VersionListParams.none(), requestOptions)

    /** A view of [VersionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/agents/{agent_id}/versions?beta=true`, but is
         * otherwise the same as [VersionService.list].
         */
        @MustBeClosed
        fun list(agentId: String): HttpResponseFor<VersionListPage> =
            list(agentId, VersionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            agentId: String,
            params: VersionListParams = VersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionListPage> =
            list(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            agentId: String,
            params: VersionListParams = VersionListParams.none(),
        ): HttpResponseFor<VersionListPage> = list(agentId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: VersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: VersionListParams): HttpResponseFor<VersionListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            agentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionListPage> =
            list(agentId, VersionListParams.none(), requestOptions)
    }
}
