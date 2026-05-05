// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.sessions

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsSessionThread
import com.anthropic.models.beta.sessions.threads.ThreadArchiveParams
import com.anthropic.models.beta.sessions.threads.ThreadListPage
import com.anthropic.models.beta.sessions.threads.ThreadListParams
import com.anthropic.models.beta.sessions.threads.ThreadRetrieveParams
import com.anthropic.services.blocking.beta.sessions.threads.EventService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ThreadService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ThreadService

    fun events(): EventService

    /** Get Session Thread */
    fun retrieve(threadId: String, params: ThreadRetrieveParams): BetaManagedAgentsSessionThread =
        retrieve(threadId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        threadId: String,
        params: ThreadRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSessionThread =
        retrieve(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: ThreadRetrieveParams): BetaManagedAgentsSessionThread =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ThreadRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSessionThread

    /** List Session Threads */
    fun list(sessionId: String): ThreadListPage = list(sessionId, ThreadListParams.none())

    /** @see list */
    fun list(
        sessionId: String,
        params: ThreadListParams = ThreadListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ThreadListPage = list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see list */
    fun list(
        sessionId: String,
        params: ThreadListParams = ThreadListParams.none(),
    ): ThreadListPage = list(sessionId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: ThreadListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ThreadListPage

    /** @see list */
    fun list(params: ThreadListParams): ThreadListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(sessionId: String, requestOptions: RequestOptions): ThreadListPage =
        list(sessionId, ThreadListParams.none(), requestOptions)

    /** Archive Session Thread */
    fun archive(threadId: String, params: ThreadArchiveParams): BetaManagedAgentsSessionThread =
        archive(threadId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        threadId: String,
        params: ThreadArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSessionThread =
        archive(params.toBuilder().threadId(threadId).build(), requestOptions)

    /** @see archive */
    fun archive(params: ThreadArchiveParams): BetaManagedAgentsSessionThread =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: ThreadArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSessionThread

    /** A view of [ThreadService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ThreadService.WithRawResponse

        fun events(): EventService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/sessions/{session_id}/threads/{thread_id}?beta=true`, but is otherwise the same as
         * [ThreadService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            threadId: String,
            params: ThreadRetrieveParams,
        ): HttpResponseFor<BetaManagedAgentsSessionThread> =
            retrieve(threadId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            threadId: String,
            params: ThreadRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSessionThread> =
            retrieve(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ThreadRetrieveParams
        ): HttpResponseFor<BetaManagedAgentsSessionThread> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ThreadRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSessionThread>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}/threads?beta=true`, but is
         * otherwise the same as [ThreadService.list].
         */
        @MustBeClosed
        fun list(sessionId: String): HttpResponseFor<ThreadListPage> =
            list(sessionId, ThreadListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: ThreadListParams = ThreadListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ThreadListPage> =
            list(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            params: ThreadListParams = ThreadListParams.none(),
        ): HttpResponseFor<ThreadListPage> = list(sessionId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ThreadListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ThreadListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: ThreadListParams): HttpResponseFor<ThreadListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ThreadListPage> =
            list(sessionId, ThreadListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/sessions/{session_id}/threads/{thread_id}/archive?beta=true`, but is otherwise the
         * same as [ThreadService.archive].
         */
        @MustBeClosed
        fun archive(
            threadId: String,
            params: ThreadArchiveParams,
        ): HttpResponseFor<BetaManagedAgentsSessionThread> =
            archive(threadId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            threadId: String,
            params: ThreadArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSessionThread> =
            archive(params.toBuilder().threadId(threadId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(params: ThreadArchiveParams): HttpResponseFor<BetaManagedAgentsSessionThread> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: ThreadArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSessionThread>
    }
}
