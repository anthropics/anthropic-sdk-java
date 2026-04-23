// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.memorystores

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsDeletedMemory
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemory
import com.anthropic.models.beta.memorystores.memories.MemoryCreateParams
import com.anthropic.models.beta.memorystores.memories.MemoryDeleteParams
import com.anthropic.models.beta.memorystores.memories.MemoryListPageAsync
import com.anthropic.models.beta.memorystores.memories.MemoryListParams
import com.anthropic.models.beta.memorystores.memories.MemoryRetrieveParams
import com.anthropic.models.beta.memorystores.memories.MemoryUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface MemoryServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryServiceAsync

    /** CreateMemory */
    fun create(
        memoryStoreId: String,
        params: MemoryCreateParams,
    ): CompletableFuture<BetaManagedAgentsMemory> =
        create(memoryStoreId, params, RequestOptions.none())

    /** @see create */
    fun create(
        memoryStoreId: String,
        params: MemoryCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemory> =
        create(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see create */
    fun create(params: MemoryCreateParams): CompletableFuture<BetaManagedAgentsMemory> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MemoryCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemory>

    /** GetMemory */
    fun retrieve(
        memoryId: String,
        params: MemoryRetrieveParams,
    ): CompletableFuture<BetaManagedAgentsMemory> =
        retrieve(memoryId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        memoryId: String,
        params: MemoryRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemory> =
        retrieve(params.toBuilder().memoryId(memoryId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: MemoryRetrieveParams): CompletableFuture<BetaManagedAgentsMemory> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemoryRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemory>

    /** UpdateMemory */
    fun update(
        memoryId: String,
        params: MemoryUpdateParams,
    ): CompletableFuture<BetaManagedAgentsMemory> = update(memoryId, params, RequestOptions.none())

    /** @see update */
    fun update(
        memoryId: String,
        params: MemoryUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemory> =
        update(params.toBuilder().memoryId(memoryId).build(), requestOptions)

    /** @see update */
    fun update(params: MemoryUpdateParams): CompletableFuture<BetaManagedAgentsMemory> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: MemoryUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemory>

    /** ListMemories */
    fun list(memoryStoreId: String): CompletableFuture<MemoryListPageAsync> =
        list(memoryStoreId, MemoryListParams.none())

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryListParams = MemoryListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemoryListPageAsync> =
        list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryListParams = MemoryListParams.none(),
    ): CompletableFuture<MemoryListPageAsync> = list(memoryStoreId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemoryListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemoryListPageAsync>

    /** @see list */
    fun list(params: MemoryListParams): CompletableFuture<MemoryListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemoryListPageAsync> =
        list(memoryStoreId, MemoryListParams.none(), requestOptions)

    /** DeleteMemory */
    fun delete(
        memoryId: String,
        params: MemoryDeleteParams,
    ): CompletableFuture<BetaManagedAgentsDeletedMemory> =
        delete(memoryId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        memoryId: String,
        params: MemoryDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedMemory> =
        delete(params.toBuilder().memoryId(memoryId).build(), requestOptions)

    /** @see delete */
    fun delete(params: MemoryDeleteParams): CompletableFuture<BetaManagedAgentsDeletedMemory> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: MemoryDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedMemory>

    /**
     * A view of [MemoryServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/memories?beta=true`, but is otherwise the same as
         * [MemoryServiceAsync.create].
         */
        fun create(
            memoryStoreId: String,
            params: MemoryCreateParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            create(memoryStoreId, params, RequestOptions.none())

        /** @see create */
        fun create(
            memoryStoreId: String,
            params: MemoryCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            create(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see create */
        fun create(
            params: MemoryCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: MemoryCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true`, but is otherwise the
         * same as [MemoryServiceAsync.retrieve].
         */
        fun retrieve(
            memoryId: String,
            params: MemoryRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            retrieve(memoryId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            memoryId: String,
            params: MemoryRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            retrieve(params.toBuilder().memoryId(memoryId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: MemoryRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: MemoryRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true`, but is otherwise the
         * same as [MemoryServiceAsync.update].
         */
        fun update(
            memoryId: String,
            params: MemoryUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            update(memoryId, params, RequestOptions.none())

        /** @see update */
        fun update(
            memoryId: String,
            params: MemoryUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            update(params.toBuilder().memoryId(memoryId).build(), requestOptions)

        /** @see update */
        fun update(
            params: MemoryUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: MemoryUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemory>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memories?beta=true`, but is otherwise the same as
         * [MemoryServiceAsync.list].
         */
        fun list(memoryStoreId: String): CompletableFuture<HttpResponseFor<MemoryListPageAsync>> =
            list(memoryStoreId, MemoryListParams.none())

        /** @see list */
        fun list(
            memoryStoreId: String,
            params: MemoryListParams = MemoryListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemoryListPageAsync>> =
            list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see list */
        fun list(
            memoryStoreId: String,
            params: MemoryListParams = MemoryListParams.none(),
        ): CompletableFuture<HttpResponseFor<MemoryListPageAsync>> =
            list(memoryStoreId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: MemoryListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemoryListPageAsync>>

        /** @see list */
        fun list(
            params: MemoryListParams
        ): CompletableFuture<HttpResponseFor<MemoryListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemoryListPageAsync>> =
            list(memoryStoreId, MemoryListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true`, but is otherwise the
         * same as [MemoryServiceAsync.delete].
         */
        fun delete(
            memoryId: String,
            params: MemoryDeleteParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemory>> =
            delete(memoryId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            memoryId: String,
            params: MemoryDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemory>> =
            delete(params.toBuilder().memoryId(memoryId).build(), requestOptions)

        /** @see delete */
        fun delete(
            params: MemoryDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemory>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: MemoryDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemory>>
    }
}
