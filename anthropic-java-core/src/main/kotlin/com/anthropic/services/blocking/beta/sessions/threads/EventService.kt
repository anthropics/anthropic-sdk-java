// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.sessions.threads

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsStreamSessionThreadEvents
import com.anthropic.models.beta.sessions.threads.events.EventListPage
import com.anthropic.models.beta.sessions.threads.events.EventListParams
import com.anthropic.models.beta.sessions.threads.events.EventStreamParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface EventService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): EventService

    /** List Session Thread Events */
    fun list(threadId: String, params: EventListParams): EventListPage =
        list(threadId, params, RequestOptions.none())

    /** @see list */
    fun list(
        threadId: String,
        params: EventListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventListPage = list(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see list */
    fun list(params: EventListParams): EventListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: EventListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventListPage

    /** Stream Session Thread Events */
    @MustBeClosed
    fun streamStreaming(
        threadId: String,
        params: EventStreamParams,
    ): StreamResponse<BetaManagedAgentsStreamSessionThreadEvents> =
        streamStreaming(threadId, params, RequestOptions.none())

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        threadId: String,
        params: EventStreamParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaManagedAgentsStreamSessionThreadEvents> =
        streamStreaming(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        params: EventStreamParams
    ): StreamResponse<BetaManagedAgentsStreamSessionThreadEvents> =
        streamStreaming(params, RequestOptions.none())

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        params: EventStreamParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>

    /** A view of [EventService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): EventService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/threads/{thread_id}/events?beta=true`, but is otherwise the
         * same as [EventService.list].
         */
        @MustBeClosed
        fun list(threadId: String, params: EventListParams): HttpResponseFor<EventListPage> =
            list(threadId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            threadId: String,
            params: EventListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventListPage> =
            list(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(params: EventListParams): HttpResponseFor<EventListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: EventListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventListPage>

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/threads/{thread_id}/stream?beta=true`, but is otherwise the
         * same as [EventService.streamStreaming].
         */
        @MustBeClosed
        fun streamStreaming(
            threadId: String,
            params: EventStreamParams,
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>> =
            streamStreaming(threadId, params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            threadId: String,
            params: EventStreamParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>> =
            streamStreaming(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>> =
            streamStreaming(params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>>
    }
}
