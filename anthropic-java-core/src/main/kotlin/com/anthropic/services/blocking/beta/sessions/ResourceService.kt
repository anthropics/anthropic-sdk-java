// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.sessions

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsDeleteSessionResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsFileResource
import com.anthropic.models.beta.sessions.resources.ResourceAddParams
import com.anthropic.models.beta.sessions.resources.ResourceDeleteParams
import com.anthropic.models.beta.sessions.resources.ResourceListPage
import com.anthropic.models.beta.sessions.resources.ResourceListParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveResponse
import com.anthropic.models.beta.sessions.resources.ResourceUpdateParams
import com.anthropic.models.beta.sessions.resources.ResourceUpdateResponse
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ResourceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ResourceService

    /** Get Session Resource */
    fun retrieve(resourceId: String, params: ResourceRetrieveParams): ResourceRetrieveResponse =
        retrieve(resourceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        resourceId: String,
        params: ResourceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ResourceRetrieveResponse =
        retrieve(params.toBuilder().resourceId(resourceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: ResourceRetrieveParams): ResourceRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ResourceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ResourceRetrieveResponse

    /** Update Session Resource */
    fun update(resourceId: String, params: ResourceUpdateParams): ResourceUpdateResponse =
        update(resourceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        resourceId: String,
        params: ResourceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ResourceUpdateResponse =
        update(params.toBuilder().resourceId(resourceId).build(), requestOptions)

    /** @see update */
    fun update(params: ResourceUpdateParams): ResourceUpdateResponse =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ResourceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ResourceUpdateResponse

    /** List Session Resources */
    fun list(sessionId: String): ResourceListPage = list(sessionId, ResourceListParams.none())

    /** @see list */
    fun list(
        sessionId: String,
        params: ResourceListParams = ResourceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ResourceListPage = list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(
        sessionId: String,
        params: ResourceListParams = ResourceListParams.none(),
    ): ResourceListPage = list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ResourceListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ResourceListPage

    /** @see list */
    fun list(params: ResourceListParams): ResourceListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(sessionId: String, requestOptions: RequestOptions): ResourceListPage =
        list(sessionId, ResourceListParams.none(), requestOptions)

    /** Delete Session Resource */
    fun delete(
        resourceId: String,
        params: ResourceDeleteParams,
    ): BetaManagedAgentsDeleteSessionResource = delete(resourceId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        resourceId: String,
        params: ResourceDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeleteSessionResource =
        delete(params.toBuilder().resourceId(resourceId).build(), requestOptions)

    /** @see delete */
    fun delete(params: ResourceDeleteParams): BetaManagedAgentsDeleteSessionResource =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: ResourceDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeleteSessionResource

    /** Add Session Resource */
    fun add(sessionId: String, params: ResourceAddParams): BetaManagedAgentsFileResource =
        add(sessionId, params, RequestOptions.none())

    /** @see add */
    fun add(
        sessionId: String,
        params: ResourceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsFileResource =
        add(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see add */
    fun add(params: ResourceAddParams): BetaManagedAgentsFileResource =
        add(params, RequestOptions.none())

    /** @see add */
    fun add(
        params: ResourceAddParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsFileResource

    /** A view of [ResourceService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ResourceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/resources/{resource_id}?beta=true`, but is otherwise the same
         * as [ResourceService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            resourceId: String,
            params: ResourceRetrieveParams,
        ): HttpResponseFor<ResourceRetrieveResponse> =
            retrieve(resourceId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            resourceId: String,
            params: ResourceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ResourceRetrieveResponse> =
            retrieve(params.toBuilder().resourceId(resourceId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ResourceRetrieveParams): HttpResponseFor<ResourceRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ResourceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ResourceRetrieveResponse>

        /**
         * Returns a raw HTTP response for `post
         * /v1/sessions/{session_id}/resources/{resource_id}?beta=true`, but is otherwise the same
         * as [ResourceService.update].
         */
        @MustBeClosed
        fun update(
            resourceId: String,
            params: ResourceUpdateParams,
        ): HttpResponseFor<ResourceUpdateResponse> =
            update(resourceId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            resourceId: String,
            params: ResourceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ResourceUpdateResponse> =
            update(params.toBuilder().resourceId(resourceId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: ResourceUpdateParams): HttpResponseFor<ResourceUpdateResponse> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ResourceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ResourceUpdateResponse>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/resources?beta=true`, but
         * is otherwise the same as [ResourceService.list].
         */
        @MustBeClosed
        fun list(sessionId: String): HttpResponseFor<ResourceListPage> =
            list(sessionId, ResourceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: ResourceListParams = ResourceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ResourceListPage> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: ResourceListParams = ResourceListParams.none(),
        ): HttpResponseFor<ResourceListPage> = list(sessionId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ResourceListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ResourceListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: ResourceListParams): HttpResponseFor<ResourceListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ResourceListPage> =
            list(sessionId, ResourceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/sessions/{session_id}/resources/{resource_id}?beta=true`, but is otherwise the same
         * as [ResourceService.delete].
         */
        @MustBeClosed
        fun delete(
            resourceId: String,
            params: ResourceDeleteParams,
        ): HttpResponseFor<BetaManagedAgentsDeleteSessionResource> =
            delete(resourceId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            resourceId: String,
            params: ResourceDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeleteSessionResource> =
            delete(params.toBuilder().resourceId(resourceId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: ResourceDeleteParams
        ): HttpResponseFor<BetaManagedAgentsDeleteSessionResource> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: ResourceDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeleteSessionResource>

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}/resources?beta=true`, but
         * is otherwise the same as [ResourceService.add].
         */
        @MustBeClosed
        fun add(
            sessionId: String,
            params: ResourceAddParams,
        ): HttpResponseFor<BetaManagedAgentsFileResource> =
            add(sessionId, params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            sessionId: String,
            params: ResourceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsFileResource> =
            add(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see add */
        @MustBeClosed
        fun add(params: ResourceAddParams): HttpResponseFor<BetaManagedAgentsFileResource> =
            add(params, RequestOptions.none())

        /** @see add */
        @MustBeClosed
        fun add(
            params: ResourceAddParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsFileResource>
    }
}
