package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.dreams.BetaDream
import com.anthropic.models.beta.dreams.DreamArchiveParams
import com.anthropic.models.beta.dreams.DreamCancelParams
import com.anthropic.models.beta.dreams.DreamCreateParams
import com.anthropic.models.beta.dreams.DreamListPageAsync
import com.anthropic.models.beta.dreams.DreamListParams
import com.anthropic.models.beta.dreams.DreamRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface DreamServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DreamServiceAsync

    /**
     * Start an asynchronous job that uses past sessions to produce a reorganized version of a
     * memory store and get back the dream to poll for the result.
     *
     * By default the dream writes its result to a new memory store and doesn't change the input
     * memory store. The response has `status` set to `pending` and an empty `outputs` array. Poll
     * the dream until `status` is `completed`, `failed`, or `canceled`.
     *
     * See the
     * [Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#create-a-dream) to
     * learn more about creating dreams.
     */
    fun create(params: DreamCreateParams): CompletableFuture<BetaDream> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: DreamCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream>

    /**
     * Get a dream by ID to check its status, output memory store, and token usage.
     *
     * Archived dreams are returned too.
     *
     * See the
     * [Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#track-progress) for
     * how to poll a dream and what each status means.
     */
    fun retrieve(dreamId: String): CompletableFuture<BetaDream> =
        retrieve(dreamId, DreamRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        dreamId: String,
        params: DreamRetrieveParams = DreamRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream> =
        retrieve(params.toBuilder().dreamId(dreamId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        dreamId: String,
        params: DreamRetrieveParams = DreamRetrieveParams.none(),
    ): CompletableFuture<BetaDream> = retrieve(dreamId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DreamRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream>

    /** @see retrieve */
    fun retrieve(params: DreamRetrieveParams): CompletableFuture<BetaDream> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(dreamId: String, requestOptions: RequestOptions): CompletableFuture<BetaDream> =
        retrieve(dreamId, DreamRetrieveParams.none(), requestOptions)

    /**
     * List the dreams in the workspace, newest first.
     *
     * Archived dreams are left out unless `include_archived` is `true`.
     *
     * See the [Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#list-dreams)
     * for how to page through dreams.
     */
    fun list(): CompletableFuture<DreamListPageAsync> = list(DreamListParams.none())

    /** @see list */
    fun list(
        params: DreamListParams = DreamListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DreamListPageAsync>

    /** @see list */
    fun list(
        params: DreamListParams = DreamListParams.none()
    ): CompletableFuture<DreamListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<DreamListPageAsync> =
        list(DreamListParams.none(), requestOptions)

    /**
     * Hide a `completed`, `failed`, or `canceled` dream from the default list of dreams.
     *
     * Archiving a `pending` or `running` dream returns a 400 error, so cancel it first. Archiving
     * an archived dream returns it unchanged. An archived dream can still be fetched by ID.
     * Archiving can't be undone.
     *
     * See the
     * [Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#archive-a-dream) to
     * learn more about archiving dreams.
     */
    fun archive(dreamId: String): CompletableFuture<BetaDream> =
        archive(dreamId, DreamArchiveParams.none())

    /** @see archive */
    fun archive(
        dreamId: String,
        params: DreamArchiveParams = DreamArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream> =
        archive(params.toBuilder().dreamId(dreamId).build(), requestOptions)

    /** @see archive */
    fun archive(
        dreamId: String,
        params: DreamArchiveParams = DreamArchiveParams.none(),
    ): CompletableFuture<BetaDream> = archive(dreamId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: DreamArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream>

    /** @see archive */
    fun archive(params: DreamArchiveParams): CompletableFuture<BetaDream> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(dreamId: String, requestOptions: RequestOptions): CompletableFuture<BetaDream> =
        archive(dreamId, DreamArchiveParams.none(), requestOptions)

    /**
     * Stop a `pending` or `running` dream.
     *
     * The response shows `status` as `canceled`, unless the dream reached `completed` or `failed`
     * first. `usage` can keep changing after the response. Canceling a `canceled` dream returns it
     * unchanged. Canceling a `completed` or `failed` dream returns a 400 error.
     *
     * See the
     * [Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#cancel-a-dream) to
     * learn more about canceling dreams.
     */
    fun cancel(dreamId: String): CompletableFuture<BetaDream> =
        cancel(dreamId, DreamCancelParams.none())

    /** @see cancel */
    fun cancel(
        dreamId: String,
        params: DreamCancelParams = DreamCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream> =
        cancel(params.toBuilder().dreamId(dreamId).build(), requestOptions)

    /** @see cancel */
    fun cancel(
        dreamId: String,
        params: DreamCancelParams = DreamCancelParams.none(),
    ): CompletableFuture<BetaDream> = cancel(dreamId, params, RequestOptions.none())

    /** @see cancel */
    fun cancel(
        params: DreamCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDream>

    /** @see cancel */
    fun cancel(params: DreamCancelParams): CompletableFuture<BetaDream> =
        cancel(params, RequestOptions.none())

    /** @see cancel */
    fun cancel(dreamId: String, requestOptions: RequestOptions): CompletableFuture<BetaDream> =
        cancel(dreamId, DreamCancelParams.none(), requestOptions)

    /** A view of [DreamServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DreamServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/dreams?beta=true`, but is otherwise the same as
         * [DreamServiceAsync.create].
         */
        fun create(params: DreamCreateParams): CompletableFuture<HttpResponseFor<BetaDream>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: DreamCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>>

        /**
         * Returns a raw HTTP response for `get /v1/dreams/{dream_id}?beta=true`, but is otherwise
         * the same as [DreamServiceAsync.retrieve].
         */
        fun retrieve(dreamId: String): CompletableFuture<HttpResponseFor<BetaDream>> =
            retrieve(dreamId, DreamRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            dreamId: String,
            params: DreamRetrieveParams = DreamRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            retrieve(params.toBuilder().dreamId(dreamId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            dreamId: String,
            params: DreamRetrieveParams = DreamRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            retrieve(dreamId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: DreamRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>>

        /** @see retrieve */
        fun retrieve(params: DreamRetrieveParams): CompletableFuture<HttpResponseFor<BetaDream>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            dreamId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            retrieve(dreamId, DreamRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/dreams?beta=true`, but is otherwise the same as
         * [DreamServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<DreamListPageAsync>> =
            list(DreamListParams.none())

        /** @see list */
        fun list(
            params: DreamListParams = DreamListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DreamListPageAsync>>

        /** @see list */
        fun list(
            params: DreamListParams = DreamListParams.none()
        ): CompletableFuture<HttpResponseFor<DreamListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<DreamListPageAsync>> =
            list(DreamListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/dreams/{dream_id}/archive?beta=true`, but is
         * otherwise the same as [DreamServiceAsync.archive].
         */
        fun archive(dreamId: String): CompletableFuture<HttpResponseFor<BetaDream>> =
            archive(dreamId, DreamArchiveParams.none())

        /** @see archive */
        fun archive(
            dreamId: String,
            params: DreamArchiveParams = DreamArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            archive(params.toBuilder().dreamId(dreamId).build(), requestOptions)

        /** @see archive */
        fun archive(
            dreamId: String,
            params: DreamArchiveParams = DreamArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            archive(dreamId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: DreamArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>>

        /** @see archive */
        fun archive(params: DreamArchiveParams): CompletableFuture<HttpResponseFor<BetaDream>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            dreamId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            archive(dreamId, DreamArchiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/dreams/{dream_id}/cancel?beta=true`, but is
         * otherwise the same as [DreamServiceAsync.cancel].
         */
        fun cancel(dreamId: String): CompletableFuture<HttpResponseFor<BetaDream>> =
            cancel(dreamId, DreamCancelParams.none())

        /** @see cancel */
        fun cancel(
            dreamId: String,
            params: DreamCancelParams = DreamCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            cancel(params.toBuilder().dreamId(dreamId).build(), requestOptions)

        /** @see cancel */
        fun cancel(
            dreamId: String,
            params: DreamCancelParams = DreamCancelParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            cancel(dreamId, params, RequestOptions.none())

        /** @see cancel */
        fun cancel(
            params: DreamCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDream>>

        /** @see cancel */
        fun cancel(params: DreamCancelParams): CompletableFuture<HttpResponseFor<BetaDream>> =
            cancel(params, RequestOptions.none())

        /** @see cancel */
        fun cancel(
            dreamId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDream>> =
            cancel(dreamId, DreamCancelParams.none(), requestOptions)
    }
}
