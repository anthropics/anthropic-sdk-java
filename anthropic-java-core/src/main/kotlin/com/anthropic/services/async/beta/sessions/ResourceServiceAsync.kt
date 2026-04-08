// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsDeleteSessionResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsFileResource
import com.anthropic.models.beta.sessions.resources.ResourceAddParams
import com.anthropic.models.beta.sessions.resources.ResourceDeleteParams
import com.anthropic.models.beta.sessions.resources.ResourceListPageAsync
import com.anthropic.models.beta.sessions.resources.ResourceListParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveResponse
import com.anthropic.models.beta.sessions.resources.ResourceUpdateParams
import com.anthropic.models.beta.sessions.resources.ResourceUpdateResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ResourceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ResourceServiceAsync

    /** Get Session Resource */
    fun retrieve(
        resourceId: String,
        params: ResourceRetrieveParams,
    ): CompletableFuture<ResourceRetrieveResponse> =
        retrieve(resourceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        resourceId: String,
        params: ResourceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ResourceRetrieveResponse> =
        retrieve(params.toBuilder().resourceId(resourceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: ResourceRetrieveParams): CompletableFuture<ResourceRetrieveResponse> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ResourceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ResourceRetrieveResponse>

    /** Update Session Resource */
    fun update(
        resourceId: String,
        params: ResourceUpdateParams,
    ): CompletableFuture<ResourceUpdateResponse> = update(resourceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        resourceId: String,
        params: ResourceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ResourceUpdateResponse> =
        update(params.toBuilder().resourceId(resourceId).build(), requestOptions)

    /** @see update */
    fun update(params: ResourceUpdateParams): CompletableFuture<ResourceUpdateResponse> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ResourceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ResourceUpdateResponse>

    /** List Session Resources */
    fun list(sessionId: String): CompletableFuture<ResourceListPageAsync> =
        list(sessionId, ResourceListParams.none())

    /** @see list */
    fun list(
        sessionId: String,
        params: ResourceListParams = ResourceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ResourceListPageAsync> =
        list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(
        sessionId: String,
        params: ResourceListParams = ResourceListParams.none(),
    ): CompletableFuture<ResourceListPageAsync> = list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ResourceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ResourceListPageAsync>

    /** @see list */
    fun list(params: ResourceListParams): CompletableFuture<ResourceListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ResourceListPageAsync> =
        list(sessionId, ResourceListParams.none(), requestOptions)

    /** Delete Session Resource */
    fun delete(
        resourceId: String,
        params: ResourceDeleteParams,
    ): CompletableFuture<BetaManagedAgentsDeleteSessionResource> =
        delete(resourceId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        resourceId: String,
        params: ResourceDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeleteSessionResource> =
        delete(params.toBuilder().resourceId(resourceId).build(), requestOptions)

    /** @see delete */
    fun delete(
        params: ResourceDeleteParams
    ): CompletableFuture<BetaManagedAgentsDeleteSessionResource> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: ResourceDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeleteSessionResource>

    /** Add Session Resource */
    fun add(
        sessionId: String,
        params: ResourceAddParams,
    ): CompletableFuture<BetaManagedAgentsFileResource> =
        add(sessionId, params, RequestOptions.none())

    /** @see add */
    fun add(
        sessionId: String,
        params: ResourceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsFileResource> =
        add(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see add */
    fun add(params: ResourceAddParams): CompletableFuture<BetaManagedAgentsFileResource> =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: ResourceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsFileResource>

    /**
     * A view of [ResourceServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ResourceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/resources/{resource_id}?beta=true`, but is otherwise the same
         * as [ResourceServiceAsync.retrieve].
         */
        fun retrieve(
            resourceId: String,
            params: ResourceRetrieveParams,
        ): CompletableFuture<HttpResponseFor<ResourceRetrieveResponse>> =
            retrieve(resourceId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            resourceId: String,
            params: ResourceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ResourceRetrieveResponse>> =
            retrieve(params.toBuilder().resourceId(resourceId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: ResourceRetrieveParams
        ): CompletableFuture<HttpResponseFor<ResourceRetrieveResponse>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ResourceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ResourceRetrieveResponse>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/sessions/{session_id}/resources/{resource_id}?beta=true`, but is otherwise the same
         * as [ResourceServiceAsync.update].
         */
        fun update(
            resourceId: String,
            params: ResourceUpdateParams,
        ): CompletableFuture<HttpResponseFor<ResourceUpdateResponse>> =
            update(resourceId, params, RequestOptions.none())

        /** @see update */
        fun update(
            resourceId: String,
            params: ResourceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ResourceUpdateResponse>> =
            update(params.toBuilder().resourceId(resourceId).build(), requestOptions)

        /** @see update */
        fun update(
            params: ResourceUpdateParams
        ): CompletableFuture<HttpResponseFor<ResourceUpdateResponse>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ResourceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ResourceUpdateResponse>>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/resources?beta=true`, but
         * is otherwise the same as [ResourceServiceAsync.list].
         */
        fun list(sessionId: String): CompletableFuture<HttpResponseFor<ResourceListPageAsync>> =
            list(sessionId, ResourceListParams.none())

        /** @see list */
        fun list(
            sessionId: String,
            params: ResourceListParams = ResourceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ResourceListPageAsync>> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        fun list(
            sessionId: String,
            params: ResourceListParams = ResourceListParams.none(),
        ): CompletableFuture<HttpResponseFor<ResourceListPageAsync>> =
            list(sessionId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: ResourceListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ResourceListPageAsync>>

        /** @see list */
        fun list(
            params: ResourceListParams
        ): CompletableFuture<HttpResponseFor<ResourceListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ResourceListPageAsync>> =
            list(sessionId, ResourceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/sessions/{session_id}/resources/{resource_id}?beta=true`, but is otherwise the same
         * as [ResourceServiceAsync.delete].
         */
        fun delete(
            resourceId: String,
            params: ResourceDeleteParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeleteSessionResource>> =
            delete(resourceId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            resourceId: String,
            params: ResourceDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeleteSessionResource>> =
            delete(params.toBuilder().resourceId(resourceId).build(), requestOptions)

        /** @see delete */
        fun delete(
            params: ResourceDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeleteSessionResource>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: ResourceDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeleteSessionResource>>

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}/resources?beta=true`, but
         * is otherwise the same as [ResourceServiceAsync.add].
         */
        fun add(
            sessionId: String,
            params: ResourceAddParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsFileResource>> =
            add(sessionId, params, RequestOptions.none())

        /** @see add */
        fun add(
            sessionId: String,
            params: ResourceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsFileResource>> =
            add(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see add */
        fun add(
            params: ResourceAddParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsFileResource>> =
            add(params, RequestOptions.none())

        /** @see add */
        fun add(
            params: ResourceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsFileResource>>
    }
}
