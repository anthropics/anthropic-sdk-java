// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.memorystores.BetaManagedAgentsDeletedMemoryStore
import com.anthropic.models.beta.memorystores.BetaManagedAgentsMemoryStore
import com.anthropic.models.beta.memorystores.MemoryStoreArchiveParams
import com.anthropic.models.beta.memorystores.MemoryStoreCreateParams
import com.anthropic.models.beta.memorystores.MemoryStoreDeleteParams
import com.anthropic.models.beta.memorystores.MemoryStoreListPageAsync
import com.anthropic.models.beta.memorystores.MemoryStoreListParams
import com.anthropic.models.beta.memorystores.MemoryStoreRetrieveParams
import com.anthropic.models.beta.memorystores.MemoryStoreUpdateParams
import com.anthropic.services.async.beta.memorystores.MemoryServiceAsync
import com.anthropic.services.async.beta.memorystores.MemoryVersionServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface MemoryStoreServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryStoreServiceAsync

    fun memories(): MemoryServiceAsync

    fun memoryVersions(): MemoryVersionServiceAsync

    /** Create a memory store */
    fun create(params: MemoryStoreCreateParams): CompletableFuture<BetaManagedAgentsMemoryStore> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MemoryStoreCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore>

    /** Retrieve a memory store */
    fun retrieve(memoryStoreId: String): CompletableFuture<BetaManagedAgentsMemoryStore> =
        retrieve(memoryStoreId, MemoryStoreRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        memoryStoreId: String,
        params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        retrieve(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        memoryStoreId: String,
        params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        retrieve(memoryStoreId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemoryStoreRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore>

    /** @see retrieve */
    fun retrieve(
        params: MemoryStoreRetrieveParams
    ): CompletableFuture<BetaManagedAgentsMemoryStore> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        retrieve(memoryStoreId, MemoryStoreRetrieveParams.none(), requestOptions)

    /** Update a memory store */
    fun update(memoryStoreId: String): CompletableFuture<BetaManagedAgentsMemoryStore> =
        update(memoryStoreId, MemoryStoreUpdateParams.none())

    /** @see update */
    fun update(
        memoryStoreId: String,
        params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        update(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see update */
    fun update(
        memoryStoreId: String,
        params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        update(memoryStoreId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: MemoryStoreUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore>

    /** @see update */
    fun update(params: MemoryStoreUpdateParams): CompletableFuture<BetaManagedAgentsMemoryStore> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        update(memoryStoreId, MemoryStoreUpdateParams.none(), requestOptions)

    /** List memory stores */
    fun list(): CompletableFuture<MemoryStoreListPageAsync> = list(MemoryStoreListParams.none())

    /** @see list */
    fun list(
        params: MemoryStoreListParams = MemoryStoreListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemoryStoreListPageAsync>

    /** @see list */
    fun list(
        params: MemoryStoreListParams = MemoryStoreListParams.none()
    ): CompletableFuture<MemoryStoreListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<MemoryStoreListPageAsync> =
        list(MemoryStoreListParams.none(), requestOptions)

    /** Delete a memory store */
    fun delete(memoryStoreId: String): CompletableFuture<BetaManagedAgentsDeletedMemoryStore> =
        delete(memoryStoreId, MemoryStoreDeleteParams.none())

    /** @see delete */
    fun delete(
        memoryStoreId: String,
        params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedMemoryStore> =
        delete(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see delete */
    fun delete(
        memoryStoreId: String,
        params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedMemoryStore> =
        delete(memoryStoreId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: MemoryStoreDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedMemoryStore>

    /** @see delete */
    fun delete(
        params: MemoryStoreDeleteParams
    ): CompletableFuture<BetaManagedAgentsDeletedMemoryStore> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeletedMemoryStore> =
        delete(memoryStoreId, MemoryStoreDeleteParams.none(), requestOptions)

    /** Archive a memory store */
    fun archive(memoryStoreId: String): CompletableFuture<BetaManagedAgentsMemoryStore> =
        archive(memoryStoreId, MemoryStoreArchiveParams.none())

    /** @see archive */
    fun archive(
        memoryStoreId: String,
        params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        archive(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see archive */
    fun archive(
        memoryStoreId: String,
        params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        archive(memoryStoreId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: MemoryStoreArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryStore>

    /** @see archive */
    fun archive(params: MemoryStoreArchiveParams): CompletableFuture<BetaManagedAgentsMemoryStore> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        archive(memoryStoreId, MemoryStoreArchiveParams.none(), requestOptions)

    /**
     * A view of [MemoryStoreServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryStoreServiceAsync.WithRawResponse

        fun memories(): MemoryServiceAsync.WithRawResponse

        fun memoryVersions(): MemoryVersionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/memory_stores?beta=true`, but is otherwise the
         * same as [MemoryStoreServiceAsync.create].
         */
        fun create(
            params: MemoryStoreCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: MemoryStoreCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>>

        /**
         * Returns a raw HTTP response for `get /v1/memory_stores/{memory_store_id}?beta=true`, but
         * is otherwise the same as [MemoryStoreServiceAsync.retrieve].
         */
        fun retrieve(
            memoryStoreId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            retrieve(memoryStoreId, MemoryStoreRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            memoryStoreId: String,
            params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            retrieve(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            memoryStoreId: String,
            params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            retrieve(memoryStoreId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: MemoryStoreRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>>

        /** @see retrieve */
        fun retrieve(
            params: MemoryStoreRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            retrieve(memoryStoreId, MemoryStoreRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/memory_stores/{memory_store_id}?beta=true`, but
         * is otherwise the same as [MemoryStoreServiceAsync.update].
         */
        fun update(
            memoryStoreId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            update(memoryStoreId, MemoryStoreUpdateParams.none())

        /** @see update */
        fun update(
            memoryStoreId: String,
            params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            update(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see update */
        fun update(
            memoryStoreId: String,
            params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            update(memoryStoreId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: MemoryStoreUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>>

        /** @see update */
        fun update(
            params: MemoryStoreUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            update(memoryStoreId, MemoryStoreUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/memory_stores?beta=true`, but is otherwise the
         * same as [MemoryStoreServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<MemoryStoreListPageAsync>> =
            list(MemoryStoreListParams.none())

        /** @see list */
        fun list(
            params: MemoryStoreListParams = MemoryStoreListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemoryStoreListPageAsync>>

        /** @see list */
        fun list(
            params: MemoryStoreListParams = MemoryStoreListParams.none()
        ): CompletableFuture<HttpResponseFor<MemoryStoreListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<MemoryStoreListPageAsync>> =
            list(MemoryStoreListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/memory_stores/{memory_store_id}?beta=true`,
         * but is otherwise the same as [MemoryStoreServiceAsync.delete].
         */
        fun delete(
            memoryStoreId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>> =
            delete(memoryStoreId, MemoryStoreDeleteParams.none())

        /** @see delete */
        fun delete(
            memoryStoreId: String,
            params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>> =
            delete(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see delete */
        fun delete(
            memoryStoreId: String,
            params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>> =
            delete(memoryStoreId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: MemoryStoreDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>>

        /** @see delete */
        fun delete(
            params: MemoryStoreDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>> =
            delete(memoryStoreId, MemoryStoreDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/archive?beta=true`, but is otherwise the same as
         * [MemoryStoreServiceAsync.archive].
         */
        fun archive(
            memoryStoreId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            archive(memoryStoreId, MemoryStoreArchiveParams.none())

        /** @see archive */
        fun archive(
            memoryStoreId: String,
            params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            archive(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see archive */
        fun archive(
            memoryStoreId: String,
            params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            archive(memoryStoreId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: MemoryStoreArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>>

        /** @see archive */
        fun archive(
            params: MemoryStoreArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> =
            archive(memoryStoreId, MemoryStoreArchiveParams.none(), requestOptions)
    }
}
