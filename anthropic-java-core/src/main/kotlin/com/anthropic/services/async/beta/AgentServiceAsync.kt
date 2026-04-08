// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.agents.AgentArchiveParams
import com.anthropic.models.beta.agents.AgentCreateParams
import com.anthropic.models.beta.agents.AgentListPageAsync
import com.anthropic.models.beta.agents.AgentListParams
import com.anthropic.models.beta.agents.AgentRetrieveParams
import com.anthropic.models.beta.agents.AgentUpdateParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgent
import com.anthropic.services.async.beta.agents.VersionServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface AgentServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): AgentServiceAsync

    fun versions(): VersionServiceAsync

    /** Create Agent */
    fun create(params: AgentCreateParams): CompletableFuture<BetaManagedAgentsAgent> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: AgentCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent>

    /** Get Agent */
    fun retrieve(agentId: String): CompletableFuture<BetaManagedAgentsAgent> =
        retrieve(agentId, AgentRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        agentId: String,
        params: AgentRetrieveParams = AgentRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent> =
        retrieve(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        agentId: String,
        params: AgentRetrieveParams = AgentRetrieveParams.none(),
    ): CompletableFuture<BetaManagedAgentsAgent> = retrieve(agentId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: AgentRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent>

    /** @see retrieve */
    fun retrieve(params: AgentRetrieveParams): CompletableFuture<BetaManagedAgentsAgent> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        agentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsAgent> =
        retrieve(agentId, AgentRetrieveParams.none(), requestOptions)

    /** Update Agent */
    fun update(
        agentId: String,
        params: AgentUpdateParams,
    ): CompletableFuture<BetaManagedAgentsAgent> = update(agentId, params, RequestOptions.none())

    /** @see update */
    fun update(
        agentId: String,
        params: AgentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent> =
        update(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see update */
    fun update(params: AgentUpdateParams): CompletableFuture<BetaManagedAgentsAgent> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: AgentUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent>

    /** List Agents */
    fun list(): CompletableFuture<AgentListPageAsync> = list(AgentListParams.none())

    /** @see list */
    fun list(
        params: AgentListParams = AgentListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<AgentListPageAsync>

    /** @see list */
    fun list(
        params: AgentListParams = AgentListParams.none()
    ): CompletableFuture<AgentListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<AgentListPageAsync> =
        list(AgentListParams.none(), requestOptions)

    /** Archive Agent */
    fun archive(agentId: String): CompletableFuture<BetaManagedAgentsAgent> =
        archive(agentId, AgentArchiveParams.none())

    /** @see archive */
    fun archive(
        agentId: String,
        params: AgentArchiveParams = AgentArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent> =
        archive(params.toBuilder().agentId(agentId).build(), requestOptions)

    /** @see archive */
    fun archive(
        agentId: String,
        params: AgentArchiveParams = AgentArchiveParams.none(),
    ): CompletableFuture<BetaManagedAgentsAgent> = archive(agentId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: AgentArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsAgent>

    /** @see archive */
    fun archive(params: AgentArchiveParams): CompletableFuture<BetaManagedAgentsAgent> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        agentId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsAgent> =
        archive(agentId, AgentArchiveParams.none(), requestOptions)

    /** A view of [AgentServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AgentServiceAsync.WithRawResponse

        fun versions(): VersionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/agents?beta=true`, but is otherwise the same as
         * [AgentServiceAsync.create].
         */
        fun create(
            params: AgentCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: AgentCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>>

        /**
         * Returns a raw HTTP response for `get /v1/agents/{agent_id}?beta=true`, but is otherwise
         * the same as [AgentServiceAsync.retrieve].
         */
        fun retrieve(agentId: String): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            retrieve(agentId, AgentRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            agentId: String,
            params: AgentRetrieveParams = AgentRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            retrieve(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            agentId: String,
            params: AgentRetrieveParams = AgentRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            retrieve(agentId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: AgentRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>>

        /** @see retrieve */
        fun retrieve(
            params: AgentRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            agentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            retrieve(agentId, AgentRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/agents/{agent_id}?beta=true`, but is otherwise
         * the same as [AgentServiceAsync.update].
         */
        fun update(
            agentId: String,
            params: AgentUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            update(agentId, params, RequestOptions.none())

        /** @see update */
        fun update(
            agentId: String,
            params: AgentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            update(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see update */
        fun update(
            params: AgentUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: AgentUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>>

        /**
         * Returns a raw HTTP response for `get /v1/agents?beta=true`, but is otherwise the same as
         * [AgentServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<AgentListPageAsync>> =
            list(AgentListParams.none())

        /** @see list */
        fun list(
            params: AgentListParams = AgentListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<AgentListPageAsync>>

        /** @see list */
        fun list(
            params: AgentListParams = AgentListParams.none()
        ): CompletableFuture<HttpResponseFor<AgentListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<AgentListPageAsync>> =
            list(AgentListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/agents/{agent_id}/archive?beta=true`, but is
         * otherwise the same as [AgentServiceAsync.archive].
         */
        fun archive(agentId: String): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            archive(agentId, AgentArchiveParams.none())

        /** @see archive */
        fun archive(
            agentId: String,
            params: AgentArchiveParams = AgentArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            archive(params.toBuilder().agentId(agentId).build(), requestOptions)

        /** @see archive */
        fun archive(
            agentId: String,
            params: AgentArchiveParams = AgentArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            archive(agentId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: AgentArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>>

        /** @see archive */
        fun archive(
            params: AgentArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            agentId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsAgent>> =
            archive(agentId, AgentArchiveParams.none(), requestOptions)
    }
}
