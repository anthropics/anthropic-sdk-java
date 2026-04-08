// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeletedSession
import com.anthropic.models.beta.sessions.BetaManagedAgentsSession
import com.anthropic.models.beta.sessions.SessionArchiveParams
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.beta.sessions.SessionDeleteParams
import com.anthropic.models.beta.sessions.SessionListPage
import com.anthropic.models.beta.sessions.SessionListParams
import com.anthropic.models.beta.sessions.SessionRetrieveParams
import com.anthropic.models.beta.sessions.SessionUpdateParams
import com.anthropic.services.blocking.beta.sessions.EventService
import com.anthropic.services.blocking.beta.sessions.ResourceService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface SessionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionService

    fun events(): EventService

    fun resources(): ResourceService

    /** Create Session */
    fun create(params: SessionCreateParams): BetaManagedAgentsSession =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SessionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession

    /** Get Session */
    fun retrieve(sessionId: String): BetaManagedAgentsSession =
        retrieve(sessionId, SessionRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        sessionId: String,
        params: SessionRetrieveParams = SessionRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession =
        retrieve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        sessionId: String,
        params: SessionRetrieveParams = SessionRetrieveParams.none(),
    ): BetaManagedAgentsSession = retrieve(sessionId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SessionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession

    /** @see retrieve */
    fun retrieve(params: SessionRetrieveParams): BetaManagedAgentsSession =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(sessionId: String, requestOptions: RequestOptions): BetaManagedAgentsSession =
        retrieve(sessionId, SessionRetrieveParams.none(), requestOptions)

    /** Update Session */
    fun update(sessionId: String): BetaManagedAgentsSession =
        update(sessionId, SessionUpdateParams.none())

    /** @see update */
    fun update(
        sessionId: String,
        params: SessionUpdateParams = SessionUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession =
        update(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see update */
    fun update(
        sessionId: String,
        params: SessionUpdateParams = SessionUpdateParams.none(),
    ): BetaManagedAgentsSession = update(sessionId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: SessionUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession

    /** @see update */
    fun update(params: SessionUpdateParams): BetaManagedAgentsSession =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(sessionId: String, requestOptions: RequestOptions): BetaManagedAgentsSession =
        update(sessionId, SessionUpdateParams.none(), requestOptions)

    /** List Sessions */
    fun list(): SessionListPage = list(SessionListParams.none())

    /** @see list */
    fun list(
        params: SessionListParams = SessionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SessionListPage

    /** @see list */
    fun list(params: SessionListParams = SessionListParams.none()): SessionListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): SessionListPage =
        list(SessionListParams.none(), requestOptions)

    /** Delete Session */
    fun delete(sessionId: String): BetaManagedAgentsDeletedSession =
        delete(sessionId, SessionDeleteParams.none())

    /** @see delete */
    fun delete(
        sessionId: String,
        params: SessionDeleteParams = SessionDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedSession =
        delete(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see delete */
    fun delete(
        sessionId: String,
        params: SessionDeleteParams = SessionDeleteParams.none(),
    ): BetaManagedAgentsDeletedSession = delete(sessionId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SessionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedSession

    /** @see delete */
    fun delete(params: SessionDeleteParams): BetaManagedAgentsDeletedSession =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(sessionId: String, requestOptions: RequestOptions): BetaManagedAgentsDeletedSession =
        delete(sessionId, SessionDeleteParams.none(), requestOptions)

    /** Archive Session */
    fun archive(sessionId: String): BetaManagedAgentsSession =
        archive(sessionId, SessionArchiveParams.none())

    /** @see archive */
    fun archive(
        sessionId: String,
        params: SessionArchiveParams = SessionArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession =
        archive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

    /** @see archive */
    fun archive(
        sessionId: String,
        params: SessionArchiveParams = SessionArchiveParams.none(),
    ): BetaManagedAgentsSession = archive(sessionId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: SessionArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsSession

    /** @see archive */
    fun archive(params: SessionArchiveParams): BetaManagedAgentsSession =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(sessionId: String, requestOptions: RequestOptions): BetaManagedAgentsSession =
        archive(sessionId, SessionArchiveParams.none(), requestOptions)

    /** A view of [SessionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): SessionService.WithRawResponse

        fun events(): EventService.WithRawResponse

        fun resources(): ResourceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/sessions?beta=true`, but is otherwise the same
         * as [SessionService.create].
         */
        @MustBeClosed
        fun create(params: SessionCreateParams): HttpResponseFor<BetaManagedAgentsSession> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: SessionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession>

        /**
         * Returns a raw HTTP response for `get /v1/sessions/{session_id}?beta=true`, but is
         * otherwise the same as [SessionService.retrieve].
         */
        @MustBeClosed
        fun retrieve(sessionId: String): HttpResponseFor<BetaManagedAgentsSession> =
            retrieve(sessionId, SessionRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            sessionId: String,
            params: SessionRetrieveParams = SessionRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession> =
            retrieve(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            sessionId: String,
            params: SessionRetrieveParams = SessionRetrieveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsSession> =
            retrieve(sessionId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SessionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: SessionRetrieveParams): HttpResponseFor<BetaManagedAgentsSession> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsSession> =
            retrieve(sessionId, SessionRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}?beta=true`, but is
         * otherwise the same as [SessionService.update].
         */
        @MustBeClosed
        fun update(sessionId: String): HttpResponseFor<BetaManagedAgentsSession> =
            update(sessionId, SessionUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            sessionId: String,
            params: SessionUpdateParams = SessionUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession> =
            update(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            sessionId: String,
            params: SessionUpdateParams = SessionUpdateParams.none(),
        ): HttpResponseFor<BetaManagedAgentsSession> =
            update(sessionId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: SessionUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession>

        /** @see update */
        @MustBeClosed
        fun update(params: SessionUpdateParams): HttpResponseFor<BetaManagedAgentsSession> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsSession> =
            update(sessionId, SessionUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/sessions?beta=true`, but is otherwise the same
         * as [SessionService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<SessionListPage> = list(SessionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: SessionListParams = SessionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SessionListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: SessionListParams = SessionListParams.none()
        ): HttpResponseFor<SessionListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<SessionListPage> =
            list(SessionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/sessions/{session_id}?beta=true`, but is
         * otherwise the same as [SessionService.delete].
         */
        @MustBeClosed
        fun delete(sessionId: String): HttpResponseFor<BetaManagedAgentsDeletedSession> =
            delete(sessionId, SessionDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            sessionId: String,
            params: SessionDeleteParams = SessionDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedSession> =
            delete(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            sessionId: String,
            params: SessionDeleteParams = SessionDeleteParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedSession> =
            delete(sessionId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: SessionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedSession>

        /** @see delete */
        @MustBeClosed
        fun delete(params: SessionDeleteParams): HttpResponseFor<BetaManagedAgentsDeletedSession> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedSession> =
            delete(sessionId, SessionDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/sessions/{session_id}/archive?beta=true`, but
         * is otherwise the same as [SessionService.archive].
         */
        @MustBeClosed
        fun archive(sessionId: String): HttpResponseFor<BetaManagedAgentsSession> =
            archive(sessionId, SessionArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            sessionId: String,
            params: SessionArchiveParams = SessionArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession> =
            archive(params.toBuilder().sessionId(sessionId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            sessionId: String,
            params: SessionArchiveParams = SessionArchiveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsSession> =
            archive(sessionId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: SessionArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsSession>

        /** @see archive */
        @MustBeClosed
        fun archive(params: SessionArchiveParams): HttpResponseFor<BetaManagedAgentsSession> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            sessionId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsSession> =
            archive(sessionId, SessionArchiveParams.none(), requestOptions)
    }
}
