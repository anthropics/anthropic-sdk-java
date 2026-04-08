// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.agents.AgentArchiveParams
import com.anthropic.models.beta.agents.AgentCreateParams
import com.anthropic.models.beta.agents.AgentListPage
import com.anthropic.models.beta.agents.AgentListParams
import com.anthropic.models.beta.agents.AgentRetrieveParams
import com.anthropic.models.beta.agents.AgentUpdateParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgent
import com.anthropic.services.blocking.beta.agents.VersionService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface AgentService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): AgentService

    fun versions(): VersionService

    /** Create Agent */
    fun create(params: AgentCreateParams): BetaManagedAgentsAgent =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: AgentCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent

    /** Get Agent */
    fun retrieve(agentId: String): BetaManagedAgentsAgent =
        retrieve(agentId, AgentRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        agentId: String,
        params: AgentRetrieveParams = AgentRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent =
        retrieve(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        agentId: String,
        params: AgentRetrieveParams = AgentRetrieveParams.none(),
    ): BetaManagedAgentsAgent = retrieve(agentId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: AgentRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent

    /** @see retrieve */
    fun retrieve(params: AgentRetrieveParams): BetaManagedAgentsAgent =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(agentId: String, requestOptions: RequestOptions): BetaManagedAgentsAgent =
        retrieve(agentId, AgentRetrieveParams.none(), requestOptions)

    /** Update Agent */
    fun update(agentId: String, params: AgentUpdateParams): BetaManagedAgentsAgent =
        update(agentId, params, RequestOptions.none())

    /** @see update */
    fun update(
        agentId: String,
        params: AgentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent = update(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see update */
    fun update(params: AgentUpdateParams): BetaManagedAgentsAgent =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: AgentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent

    /** List Agents */
    fun list(): AgentListPage = list(AgentListParams.none())

    /** @see list */
    fun list(
        params: AgentListParams = AgentListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AgentListPage

    /** @see list */
    fun list(params: AgentListParams = AgentListParams.none()): AgentListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): AgentListPage =
        list(AgentListParams.none(), requestOptions)

    /** Archive Agent */
    fun archive(agentId: String): BetaManagedAgentsAgent =
        archive(agentId, AgentArchiveParams.none())

    /** @see archive */
    fun archive(
        agentId: String,
        params: AgentArchiveParams = AgentArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent = archive(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see archive */
    fun archive(
        agentId: String,
        params: AgentArchiveParams = AgentArchiveParams.none(),
    ): BetaManagedAgentsAgent = archive(agentId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: AgentArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsAgent

    /** @see archive */
    fun archive(params: AgentArchiveParams): BetaManagedAgentsAgent =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(agentId: String, requestOptions: RequestOptions): BetaManagedAgentsAgent =
        archive(agentId, AgentArchiveParams.none(), requestOptions)

    /** A view of [AgentService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): AgentService.WithRawResponse

        fun versions(): VersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/agents?beta=true`, but is otherwise the same as
         * [AgentService.create].
         */
        @MustBeClosed
        fun create(params: AgentCreateParams): HttpResponseFor<BetaManagedAgentsAgent> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: AgentCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent>

        /**
         * Returns a raw HTTP response for `get /v1/agents/{agent_id}?beta=true`, but is otherwise
         * the same as [AgentService.retrieve].
         */
        @MustBeClosed
        fun retrieve(agentId: String): HttpResponseFor<BetaManagedAgentsAgent> =
            retrieve(agentId, AgentRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            agentId: String,
            params: AgentRetrieveParams = AgentRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent> =
            retrieve(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            agentId: String,
            params: AgentRetrieveParams = AgentRetrieveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent> =
            retrieve(agentId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: AgentRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: AgentRetrieveParams): HttpResponseFor<BetaManagedAgentsAgent> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            agentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsAgent> =
            retrieve(agentId, AgentRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/agents/{agent_id}?beta=true`, but is otherwise
         * the same as [AgentService.update].
         */
        @MustBeClosed
        fun update(
            agentId: String,
            params: AgentUpdateParams,
        ): HttpResponseFor<BetaManagedAgentsAgent> = update(agentId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            agentId: String,
            params: AgentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent> =
            update(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: AgentUpdateParams): HttpResponseFor<BetaManagedAgentsAgent> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: AgentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent>

        /**
         * Returns a raw HTTP response for `get /v1/agents?beta=true`, but is otherwise the same as
         * [AgentService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<AgentListPage> = list(AgentListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: AgentListParams = AgentListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AgentListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: AgentListParams = AgentListParams.none()): HttpResponseFor<AgentListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<AgentListPage> =
            list(AgentListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/agents/{agent_id}/archive?beta=true`, but is
         * otherwise the same as [AgentService.archive].
         */
        @MustBeClosed
        fun archive(agentId: String): HttpResponseFor<BetaManagedAgentsAgent> =
            archive(agentId, AgentArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            agentId: String,
            params: AgentArchiveParams = AgentArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent> =
            archive(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            agentId: String,
            params: AgentArchiveParams = AgentArchiveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent> = archive(agentId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: AgentArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsAgent>

        /** @see archive */
        @MustBeClosed
        fun archive(params: AgentArchiveParams): HttpResponseFor<BetaManagedAgentsAgent> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            agentId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsAgent> =
            archive(agentId, AgentArchiveParams.none(), requestOptions)
    }
}
