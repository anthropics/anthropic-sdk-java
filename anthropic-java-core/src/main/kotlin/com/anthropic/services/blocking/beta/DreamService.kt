// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.dreams.BetaDream
import com.anthropic.models.beta.dreams.DreamArchiveParams
import com.anthropic.models.beta.dreams.DreamCancelParams
import com.anthropic.models.beta.dreams.DreamCreateParams
import com.anthropic.models.beta.dreams.DreamListPage
import com.anthropic.models.beta.dreams.DreamListParams
import com.anthropic.models.beta.dreams.DreamRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface DreamService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DreamService

    /** Create a Dream */
    fun create(params: DreamCreateParams): BetaDream = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: DreamCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream

    /** Get a Dream */
    fun retrieve(dreamId: String): BetaDream = retrieve(dreamId, DreamRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        dreamId: String,
        params: DreamRetrieveParams = DreamRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream = retrieve(params.toBuilder().dreamId(dreamId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        dreamId: String,
        params: DreamRetrieveParams = DreamRetrieveParams.none(),
    ): BetaDream = retrieve(dreamId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DreamRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream

    /** @see retrieve */
    fun retrieve(params: DreamRetrieveParams): BetaDream = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(dreamId: String, requestOptions: RequestOptions): BetaDream =
        retrieve(dreamId, DreamRetrieveParams.none(), requestOptions)

    /** List Dreams */
    fun list(): DreamListPage = list(DreamListParams.none())

    /** @see list */
    fun list(
        params: DreamListParams = DreamListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DreamListPage

    /** @see list */
    fun list(params: DreamListParams = DreamListParams.none()): DreamListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): DreamListPage =
        list(DreamListParams.none(), requestOptions)

    /** Archive a Dream */
    fun archive(dreamId: String): BetaDream = archive(dreamId, DreamArchiveParams.none())

    /** @see archive */
    fun archive(
        dreamId: String,
        params: DreamArchiveParams = DreamArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream = archive(params.toBuilder().dreamId(dreamId).build(), requestOptions)

    /** @see archive */
    fun archive(
        dreamId: String,
        params: DreamArchiveParams = DreamArchiveParams.none(),
    ): BetaDream = archive(dreamId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: DreamArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream

    /** @see archive */
    fun archive(params: DreamArchiveParams): BetaDream = archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(dreamId: String, requestOptions: RequestOptions): BetaDream =
        archive(dreamId, DreamArchiveParams.none(), requestOptions)

    /** Cancel a Dream */
    fun cancel(dreamId: String): BetaDream = cancel(dreamId, DreamCancelParams.none())

    /** @see cancel */
    fun cancel(
        dreamId: String,
        params: DreamCancelParams = DreamCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream = cancel(params.toBuilder().dreamId(dreamId).build(), requestOptions)

    /** @see cancel */
    fun cancel(dreamId: String, params: DreamCancelParams = DreamCancelParams.none()): BetaDream =
        cancel(dreamId, params, RequestOptions.none())

    /** @see cancel */
    fun cancel(
        params: DreamCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDream

    /** @see cancel */
    fun cancel(params: DreamCancelParams): BetaDream = cancel(params, RequestOptions.none())

    /** @see cancel */
    fun cancel(dreamId: String, requestOptions: RequestOptions): BetaDream =
        cancel(dreamId, DreamCancelParams.none(), requestOptions)

    /** A view of [DreamService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): DreamService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/dreams?beta=true`, but is otherwise the same as
         * [DreamService.create].
         */
        @MustBeClosed
        fun create(params: DreamCreateParams): HttpResponseFor<BetaDream> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: DreamCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream>

        /**
         * Returns a raw HTTP response for `get /v1/dreams/{dream_id}?beta=true`, but is otherwise
         * the same as [DreamService.retrieve].
         */
        @MustBeClosed
        fun retrieve(dreamId: String): HttpResponseFor<BetaDream> =
            retrieve(dreamId, DreamRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            dreamId: String,
            params: DreamRetrieveParams = DreamRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream> =
            retrieve(params.toBuilder().dreamId(dreamId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            dreamId: String,
            params: DreamRetrieveParams = DreamRetrieveParams.none(),
        ): HttpResponseFor<BetaDream> = retrieve(dreamId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DreamRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: DreamRetrieveParams): HttpResponseFor<BetaDream> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(dreamId: String, requestOptions: RequestOptions): HttpResponseFor<BetaDream> =
            retrieve(dreamId, DreamRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/dreams?beta=true`, but is otherwise the same as
         * [DreamService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<DreamListPage> = list(DreamListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: DreamListParams = DreamListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DreamListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: DreamListParams = DreamListParams.none()): HttpResponseFor<DreamListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<DreamListPage> =
            list(DreamListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/dreams/{dream_id}/archive?beta=true`, but is
         * otherwise the same as [DreamService.archive].
         */
        @MustBeClosed
        fun archive(dreamId: String): HttpResponseFor<BetaDream> =
            archive(dreamId, DreamArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            dreamId: String,
            params: DreamArchiveParams = DreamArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream> =
            archive(params.toBuilder().dreamId(dreamId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            dreamId: String,
            params: DreamArchiveParams = DreamArchiveParams.none(),
        ): HttpResponseFor<BetaDream> = archive(dreamId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: DreamArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream>

        /** @see archive */
        @MustBeClosed
        fun archive(params: DreamArchiveParams): HttpResponseFor<BetaDream> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(dreamId: String, requestOptions: RequestOptions): HttpResponseFor<BetaDream> =
            archive(dreamId, DreamArchiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/dreams/{dream_id}/cancel?beta=true`, but is
         * otherwise the same as [DreamService.cancel].
         */
        @MustBeClosed
        fun cancel(dreamId: String): HttpResponseFor<BetaDream> =
            cancel(dreamId, DreamCancelParams.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            dreamId: String,
            params: DreamCancelParams = DreamCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream> =
            cancel(params.toBuilder().dreamId(dreamId).build(), requestOptions)

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            dreamId: String,
            params: DreamCancelParams = DreamCancelParams.none(),
        ): HttpResponseFor<BetaDream> = cancel(dreamId, params, RequestOptions.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            params: DreamCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDream>

        /** @see cancel */
        @MustBeClosed
        fun cancel(params: DreamCancelParams): HttpResponseFor<BetaDream> =
            cancel(params, RequestOptions.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(dreamId: String, requestOptions: RequestOptions): HttpResponseFor<BetaDream> =
            cancel(dreamId, DreamCancelParams.none(), requestOptions)
    }
}
