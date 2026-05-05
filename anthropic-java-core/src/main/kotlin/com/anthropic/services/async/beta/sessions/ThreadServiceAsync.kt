// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsSessionThread
import com.anthropic.models.beta.sessions.threads.ThreadArchiveParams
import com.anthropic.models.beta.sessions.threads.ThreadListPageAsync
import com.anthropic.models.beta.sessions.threads.ThreadListParams
import com.anthropic.models.beta.sessions.threads.ThreadRetrieveParams
import com.anthropic.services.async.beta.sessions.threads.EventServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ThreadServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ThreadServiceAsync

    fun events(): EventServiceAsync

    /** Get Session Thread */
    fun retrieve(
        threadId: String,
        params: ThreadRetrieveParams,
    ): CompletableFuture<BetaManagedAgentsSessionThread> =
        retrieve(threadId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        threadId: String,
        params: ThreadRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSessionThread> =
        retrieve(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: ThreadRetrieveParams): CompletableFuture<BetaManagedAgentsSessionThread> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ThreadRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSessionThread>

    /** List Session Threads */
    fun list(sessionId: String): CompletableFuture<ThreadListPageAsync> =
        list(sessionId, ThreadListParams.none())

    /** @see list */
    fun list(
        sessionId: String,
        params: ThreadListParams = ThreadListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ThreadListPageAsync> =
        list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(
        sessionId: String,
        params: ThreadListParams = ThreadListParams.none(),
    ): CompletableFuture<ThreadListPageAsync> = list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ThreadListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ThreadListPageAsync>

    /** @see list */
    fun list(params: ThreadListParams): CompletableFuture<ThreadListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ThreadListPageAsync> =
        list(sessionId, ThreadListParams.none(), requestOptions)

    /** Archive Session Thread */
    fun archive(
        threadId: String,
        params: ThreadArchiveParams,
    ): CompletableFuture<BetaManagedAgentsSessionThread> =
        archive(threadId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        threadId: String,
        params: ThreadArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSessionThread> =
        archive(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see archive */
    fun archive(params: ThreadArchiveParams): CompletableFuture<BetaManagedAgentsSessionThread> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: ThreadArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSessionThread>

    /**
     * A view of [ThreadServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ThreadServiceAsync.WithRawResponse

        fun events(): EventServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/threads/{thread_id}?beta=true`, but is otherwise the same as
         * [ThreadServiceAsync.retrieve].
         */
        fun retrieve(
            threadId: String,
            params: ThreadRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>> =
            retrieve(threadId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            threadId: String,
            params: ThreadRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>> =
            retrieve(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: ThreadRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ThreadRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/threads?beta=true`, but is
         * otherwise the same as [ThreadServiceAsync.list].
         */
        fun list(sessionId: String): CompletableFuture<HttpResponseFor<ThreadListPageAsync>> =
            list(sessionId, ThreadListParams.none())

        /** @see list */
        fun list(
            sessionId: String,
            params: ThreadListParams = ThreadListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ThreadListPageAsync>> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        fun list(
            sessionId: String,
            params: ThreadListParams = ThreadListParams.none(),
        ): CompletableFuture<HttpResponseFor<ThreadListPageAsync>> =
            list(sessionId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: ThreadListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ThreadListPageAsync>>

        /** @see list */
        fun list(
            params: ThreadListParams
        ): CompletableFuture<HttpResponseFor<ThreadListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ThreadListPageAsync>> =
            list(sessionId, ThreadListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/sessions/{session_id}/threads/{thread_id}/archive?beta=true`, but is otherwise the
         * same as [ThreadServiceAsync.archive].
         */
        fun archive(
            threadId: String,
            params: ThreadArchiveParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>> =
            archive(threadId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            threadId: String,
            params: ThreadArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>> =
            archive(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see archive */
        fun archive(
            params: ThreadArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: ThreadArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSessionThread>>
    }
}
