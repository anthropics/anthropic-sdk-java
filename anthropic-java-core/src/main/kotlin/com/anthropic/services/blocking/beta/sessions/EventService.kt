// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.sessions

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSendSessionEvents
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents
import com.anthropic.models.beta.sessions.events.EventListPage
import com.anthropic.models.beta.sessions.events.EventListParams
import com.anthropic.models.beta.sessions.events.EventSendParams
import com.anthropic.models.beta.sessions.events.EventStreamParams
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

    /** List Events */
    fun list(sessionId: String): EventListPage = list(sessionId, EventListParams.none())

    /** @see list */
    fun list(
        sessionId: String,
        params: EventListParams = EventListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventListPage = list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(sessionId: String, params: EventListParams = EventListParams.none()): EventListPage =
        list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: EventListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventListPage

    /** @see list */
    fun list(params: EventListParams): EventListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(sessionId: String, requestOptions: RequestOptions): EventListPage =
        list(sessionId, EventListParams.none(), requestOptions)

    /** Send Events */
    fun send(sessionId: String, params: EventSendParams): BetaManagedAgentsSendSessionEvents =
        send(sessionId, params, RequestOptions.none())

    /** @see send */
    fun send(
        sessionId: String,
        params: EventSendParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSendSessionEvents =
        send(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see send */
    fun send(params: EventSendParams): BetaManagedAgentsSendSessionEvents =
        send(params, RequestOptions.none())

    /** @see send */
    fun send(
        params: EventSendParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSendSessionEvents

    /** Stream Events */
    @MustBeClosed
    fun streamStreaming(sessionId: String): StreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(sessionId, EventStreamParams.none())

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        sessionId: String,
        params: EventStreamParams = EventStreamParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        sessionId: String,
        params: EventStreamParams = EventStreamParams.none(),
    ): StreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(sessionId, params, RequestOptions.none())

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        params: EventStreamParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<BetaManagedAgentsStreamSessionEvents>

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        params: EventStreamParams
    ): StreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(params, RequestOptions.none())

    /** @see streamStreaming */
    @MustBeClosed
    fun streamStreaming(
        sessionId: String,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(sessionId, EventStreamParams.none(), requestOptions)

    /** A view of [EventService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): EventService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/events?beta=true`, but is
         * otherwise the same as [EventService.list].
         */
        @MustBeClosed
        fun list(sessionId: String): HttpResponseFor<EventListPage> =
            list(sessionId, EventListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: EventListParams = EventListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventListPage> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: EventListParams = EventListParams.none(),
        ): HttpResponseFor<EventListPage> = list(sessionId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: EventListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: EventListParams): HttpResponseFor<EventListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<EventListPage> = list(sessionId, EventListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}/events?beta=true`, but is
         * otherwise the same as [EventService.send].
         */
        @MustBeClosed
        fun send(
            sessionId: String,
            params: EventSendParams,
        ): HttpResponseFor<BetaManagedAgentsSendSessionEvents> =
            send(sessionId, params, RequestOptions.none())

        /** @see send */
        @MustBeClosed
        fun send(
            sessionId: String,
            params: EventSendParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSendSessionEvents> =
            send(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see send */
        @MustBeClosed
        fun send(params: EventSendParams): HttpResponseFor<BetaManagedAgentsSendSessionEvents> =
            send(params, RequestOptions.none())

        /** @see send */
        @MustBeClosed
        fun send(
            params: EventSendParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSendSessionEvents>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/events/stream?beta=true`,
         * but is otherwise the same as [EventService.streamStreaming].
         */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>> =
            streamStreaming(sessionId, EventStreamParams.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String,
            params: EventStreamParams = EventStreamParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>> =
            streamStreaming(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String,
            params: EventStreamParams = EventStreamParams.none(),
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>> =
            streamStreaming(sessionId, params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>> =
            streamStreaming(params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>> =
            streamStreaming(sessionId, EventStreamParams.none(), requestOptions)
    }
}
