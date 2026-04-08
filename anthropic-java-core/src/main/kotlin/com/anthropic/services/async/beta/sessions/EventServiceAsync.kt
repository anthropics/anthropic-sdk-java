// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSendSessionEvents
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents
import com.anthropic.models.beta.sessions.events.EventListPageAsync
import com.anthropic.models.beta.sessions.events.EventListParams
import com.anthropic.models.beta.sessions.events.EventSendParams
import com.anthropic.models.beta.sessions.events.EventStreamParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface EventServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): EventServiceAsync

    /** List Events */
    fun list(sessionId: String): CompletableFuture<EventListPageAsync> =
        list(sessionId, EventListParams.none())

    /** @see list */
    fun list(
        sessionId: String,
        params: EventListParams = EventListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<EventListPageAsync> =
        list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(
        sessionId: String,
        params: EventListParams = EventListParams.none(),
    ): CompletableFuture<EventListPageAsync> = list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: EventListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<EventListPageAsync>

    /** @see list */
    fun list(params: EventListParams): CompletableFuture<EventListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<EventListPageAsync> =
        list(sessionId, EventListParams.none(), requestOptions)

    /** Send Events */
    fun send(
        sessionId: String,
        params: EventSendParams,
    ): CompletableFuture<BetaManagedAgentsSendSessionEvents> =
        send(sessionId, params, RequestOptions.none())

    /** @see send */
    fun send(
        sessionId: String,
        params: EventSendParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSendSessionEvents> =
        send(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see send */
    fun send(params: EventSendParams): CompletableFuture<BetaManagedAgentsSendSessionEvents> =
        send(params, RequestOptions.none())

    /** @see send */
    fun send(
        params: EventSendParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSendSessionEvents>

    /** Stream Events */
    fun streamStreaming(
        sessionId: String
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(sessionId, EventStreamParams.none())

    /** @see streamStreaming */
    fun streamStreaming(
        sessionId: String,
        params: EventStreamParams = EventStreamParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see streamStreaming */
    fun streamStreaming(
        sessionId: String,
        params: EventStreamParams = EventStreamParams.none(),
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(sessionId, params, RequestOptions.none())

    /** @see streamStreaming */
    fun streamStreaming(
        params: EventStreamParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionEvents>

    /** @see streamStreaming */
    fun streamStreaming(
        params: EventStreamParams
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(params, RequestOptions.none())

    /** @see streamStreaming */
    fun streamStreaming(
        sessionId: String,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionEvents> =
        streamStreaming(sessionId, EventStreamParams.none(), requestOptions)

    /** A view of [EventServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): EventServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/events?beta=true`, but is
         * otherwise the same as [EventServiceAsync.list].
         */
        fun list(sessionId: String): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(sessionId, EventListParams.none())

        /** @see list */
        fun list(
            sessionId: String,
            params: EventListParams = EventListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        fun list(
            sessionId: String,
            params: EventListParams = EventListParams.none(),
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(sessionId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: EventListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>>

        /** @see list */
        fun list(params: EventListParams): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(sessionId, EventListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}/events?beta=true`, but is
         * otherwise the same as [EventServiceAsync.send].
         */
        fun send(
            sessionId: String,
            params: EventSendParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSendSessionEvents>> =
            send(sessionId, params, RequestOptions.none())

        /** @see send */
        fun send(
            sessionId: String,
            params: EventSendParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSendSessionEvents>> =
            send(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see send */
        fun send(
            params: EventSendParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSendSessionEvents>> =
            send(params, RequestOptions.none())

        /** @see send */
        fun send(
            params: EventSendParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSendSessionEvents>>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/events/stream?beta=true`,
         * but is otherwise the same as [EventServiceAsync.streamStreaming].
         */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>
        > = streamStreaming(sessionId, EventStreamParams.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String,
            params: EventStreamParams = EventStreamParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>
        > = streamStreaming(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String,
            params: EventStreamParams = EventStreamParams.none(),
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>
        > = streamStreaming(sessionId, params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>>

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>
        > = streamStreaming(params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionEvents>>
        > = streamStreaming(sessionId, EventStreamParams.none(), requestOptions)
    }
}
