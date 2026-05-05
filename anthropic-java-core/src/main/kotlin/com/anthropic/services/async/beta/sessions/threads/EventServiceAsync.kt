// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions.threads

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsStreamSessionThreadEvents
import com.anthropic.models.beta.sessions.threads.events.EventListPageAsync
import com.anthropic.models.beta.sessions.threads.events.EventListParams
import com.anthropic.models.beta.sessions.threads.events.EventStreamParams
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

    /** List Session Thread Events */
    fun list(threadId: String, params: EventListParams): CompletableFuture<EventListPageAsync> =
        list(threadId, params, RequestOptions.none())

    /** @see list */
    fun list(
        threadId: String,
        params: EventListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<EventListPageAsync> =
        list(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see list */
    fun list(params: EventListParams): CompletableFuture<EventListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        params: EventListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<EventListPageAsync>

    /** Stream Session Thread Events */
    fun streamStreaming(
        threadId: String,
        params: EventStreamParams,
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionThreadEvents> =
        streamStreaming(threadId, params, RequestOptions.none())

    /** @see streamStreaming */
    fun streamStreaming(
        threadId: String,
        params: EventStreamParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionThreadEvents> =
        streamStreaming(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see streamStreaming */
    fun streamStreaming(
        params: EventStreamParams
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionThreadEvents> =
        streamStreaming(params, RequestOptions.none())

    /** @see streamStreaming */
    fun streamStreaming(
        params: EventStreamParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<BetaManagedAgentsStreamSessionThreadEvents>

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
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/threads/{thread_id}/events?beta=true`, but is otherwise the
         * same as [EventServiceAsync.list].
         */
        fun list(
            threadId: String,
            params: EventListParams,
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(threadId, params, RequestOptions.none())

        /** @see list */
        fun list(
            threadId: String,
            params: EventListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see list */
        fun list(params: EventListParams): CompletableFuture<HttpResponseFor<EventListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            params: EventListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<EventListPageAsync>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/threads/{thread_id}/stream?beta=true`, but is otherwise the
         * same as [EventServiceAsync.streamStreaming].
         */
        @MustBeClosed
        fun streamStreaming(
            threadId: String,
            params: EventStreamParams,
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>>
        > = streamStreaming(threadId, params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            threadId: String,
            params: EventStreamParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>>
        > = streamStreaming(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>>
        > = streamStreaming(params, RequestOptions.none())

        /** @see streamStreaming */
        @MustBeClosed
        fun streamStreaming(
            params: EventStreamParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<
            HttpResponseFor<StreamResponse<BetaManagedAgentsStreamSessionThreadEvents>>
        >
    }
}
