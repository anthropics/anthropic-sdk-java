// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeletedSession
import com.anthropic.models.beta.sessions.BetaManagedAgentsSession
import com.anthropic.models.beta.sessions.SessionArchiveParams
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.beta.sessions.SessionDeleteParams
import com.anthropic.models.beta.sessions.SessionListPageAsync
import com.anthropic.models.beta.sessions.SessionListParams
import com.anthropic.models.beta.sessions.SessionRetrieveParams
import com.anthropic.models.beta.sessions.SessionUpdateParams
import com.anthropic.services.async.beta.sessions.EventServiceAsync
import com.anthropic.services.async.beta.sessions.ResourceServiceAsync
import com.anthropic.services.async.beta.sessions.ThreadServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface SessionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionServiceAsync

    fun events(): EventServiceAsync

    fun resources(): ResourceServiceAsync

    fun threads(): ThreadServiceAsync

    /** Create Session */
    fun create(params: SessionCreateParams): CompletableFuture<BetaManagedAgentsSession> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SessionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession>

    /** Get Session */
    fun retrieve(sessionId: String): CompletableFuture<BetaManagedAgentsSession> =
        retrieve(sessionId, SessionRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        sessionId: String,
        params: SessionRetrieveParams = SessionRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession> =
        retrieve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        sessionId: String,
        params: SessionRetrieveParams = SessionRetrieveParams.none(),
    ): CompletableFuture<BetaManagedAgentsSession> =
        retrieve(sessionId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SessionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession>

    /** @see retrieve */
    fun retrieve(params: SessionRetrieveParams): CompletableFuture<BetaManagedAgentsSession> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsSession> =
        retrieve(sessionId, SessionRetrieveParams.none(), requestOptions)

    /** Update Session */
    fun update(sessionId: String): CompletableFuture<BetaManagedAgentsSession> =
        update(sessionId, SessionUpdateParams.none())

    /** @see update */
    fun update(
        sessionId: String,
        params: SessionUpdateParams = SessionUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession> =
        update(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see update */
    fun update(
        sessionId: String,
        params: SessionUpdateParams = SessionUpdateParams.none(),
    ): CompletableFuture<BetaManagedAgentsSession> =
        update(sessionId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: SessionUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession>

    /** @see update */
    fun update(params: SessionUpdateParams): CompletableFuture<BetaManagedAgentsSession> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsSession> =
        update(sessionId, SessionUpdateParams.none(), requestOptions)

    /** List Sessions */
    fun list(): CompletableFuture<SessionListPageAsync> = list(SessionListParams.none())

    /** @see list */
    fun list(
        params: SessionListParams = SessionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SessionListPageAsync>

    /** @see list */
    fun list(
        params: SessionListParams = SessionListParams.none()
    ): CompletableFuture<SessionListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<SessionListPageAsync> =
        list(SessionListParams.none(), requestOptions)

    /** Delete Session */
    fun delete(sessionId: String): CompletableFuture<BetaManagedAgentsDeletedSession> =
        delete(sessionId, SessionDeleteParams.none())

    /** @see delete */
    fun delete(
        sessionId: String,
        params: SessionDeleteParams = SessionDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedSession> =
        delete(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see delete */
    fun delete(
        sessionId: String,
        params: SessionDeleteParams = SessionDeleteParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedSession> =
        delete(sessionId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SessionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedSession>

    /** @see delete */
    fun delete(params: SessionDeleteParams): CompletableFuture<BetaManagedAgentsDeletedSession> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeletedSession> =
        delete(sessionId, SessionDeleteParams.none(), requestOptions)

    /** Archive Session */
    fun archive(sessionId: String): CompletableFuture<BetaManagedAgentsSession> =
        archive(sessionId, SessionArchiveParams.none())

    /** @see archive */
    fun archive(
        sessionId: String,
        params: SessionArchiveParams = SessionArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession> =
        archive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see archive */
    fun archive(
        sessionId: String,
        params: SessionArchiveParams = SessionArchiveParams.none(),
    ): CompletableFuture<BetaManagedAgentsSession> =
        archive(sessionId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: SessionArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsSession>

    /** @see archive */
    fun archive(params: SessionArchiveParams): CompletableFuture<BetaManagedAgentsSession> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        sessionId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsSession> =
        archive(sessionId, SessionArchiveParams.none(), requestOptions)

    /**
     * A view of [SessionServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SessionServiceAsync.WithRawResponse

        fun events(): EventServiceAsync.WithRawResponse

        fun resources(): ResourceServiceAsync.WithRawResponse

        fun threads(): ThreadServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/sessions?beta=true`, but is otherwise the same
         * as [SessionServiceAsync.create].
         */
        fun create(
            params: SessionCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: SessionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}?beta=true`, but is
         * otherwise the same as [SessionServiceAsync.retrieve].
         */
        fun retrieve(
            sessionId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            retrieve(sessionId, SessionRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            sessionId: String,
            params: SessionRetrieveParams = SessionRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            retrieve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            sessionId: String,
            params: SessionRetrieveParams = SessionRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            retrieve(sessionId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SessionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>>

        /** @see retrieve */
        fun retrieve(
            params: SessionRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            retrieve(sessionId, SessionRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}?beta=true`, but is
         * otherwise the same as [SessionServiceAsync.update].
         */
        fun update(
            sessionId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            update(sessionId, SessionUpdateParams.none())

        /** @see update */
        fun update(
            sessionId: String,
            params: SessionUpdateParams = SessionUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            update(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see update */
        fun update(
            sessionId: String,
            params: SessionUpdateParams = SessionUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            update(sessionId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: SessionUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>>

        /** @see update */
        fun update(
            params: SessionUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            update(sessionId, SessionUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/sessions?beta=true`, but is otherwise the same
         * as [SessionServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<SessionListPageAsync>> =
            list(SessionListParams.none())

        /** @see list */
        fun list(
            params: SessionListParams = SessionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>>

        /** @see list */
        fun list(
            params: SessionListParams = SessionListParams.none()
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SessionListPageAsync>> =
            list(SessionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/sessions/{session_id}?beta=true`, but is
         * otherwise the same as [SessionServiceAsync.delete].
         */
        fun delete(
            sessionId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedSession>> =
            delete(sessionId, SessionDeleteParams.none())

        /** @see delete */
        fun delete(
            sessionId: String,
            params: SessionDeleteParams = SessionDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedSession>> =
            delete(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see delete */
        fun delete(
            sessionId: String,
            params: SessionDeleteParams = SessionDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedSession>> =
            delete(sessionId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: SessionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedSession>>

        /** @see delete */
        fun delete(
            params: SessionDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedSession>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedSession>> =
            delete(sessionId, SessionDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}/archive?beta=true`, but
         * is otherwise the same as [SessionServiceAsync.archive].
         */
        fun archive(
            sessionId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            archive(sessionId, SessionArchiveParams.none())

        /** @see archive */
        fun archive(
            sessionId: String,
            params: SessionArchiveParams = SessionArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            archive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see archive */
        fun archive(
            sessionId: String,
            params: SessionArchiveParams = SessionArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            archive(sessionId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: SessionArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>>

        /** @see archive */
        fun archive(
            params: SessionArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            sessionId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsSession>> =
            archive(sessionId, SessionArchiveParams.none(), requestOptions)
    }
}
