// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.memorystores

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsDeletedMemory
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemory
import com.anthropic.models.beta.memorystores.memories.MemoryCreateParams
import com.anthropic.models.beta.memorystores.memories.MemoryDeleteParams
import com.anthropic.models.beta.memorystores.memories.MemoryListPage
import com.anthropic.models.beta.memorystores.memories.MemoryListParams
import com.anthropic.models.beta.memorystores.memories.MemoryRetrieveParams
import com.anthropic.models.beta.memorystores.memories.MemoryUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface MemoryService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryService

    /** Create a memory */
    fun create(memoryStoreId: String, params: MemoryCreateParams): BetaManagedAgentsMemory =
        create(memoryStoreId, params, RequestOptions.none())

    /** @see create */
    fun create(
        memoryStoreId: String,
        params: MemoryCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemory =
        create(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see create */
    fun create(params: MemoryCreateParams): BetaManagedAgentsMemory =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MemoryCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemory

    /** Retrieve a memory */
    fun retrieve(memoryId: String, params: MemoryRetrieveParams): BetaManagedAgentsMemory =
        retrieve(memoryId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        memoryId: String,
        params: MemoryRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemory =
        retrieve(params.toBuilder().memoryId(memoryId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: MemoryRetrieveParams): BetaManagedAgentsMemory =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemoryRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemory

    /** Update a memory */
    fun update(memoryId: String, params: MemoryUpdateParams): BetaManagedAgentsMemory =
        update(memoryId, params, RequestOptions.none())

    /** @see update */
    fun update(
        memoryId: String,
        params: MemoryUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemory =
        update(params.toBuilder().memoryId(memoryId).build(), requestOptions)

    /** @see update */
    fun update(params: MemoryUpdateParams): BetaManagedAgentsMemory =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: MemoryUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemory

    /** List memories */
    fun list(memoryStoreId: String): MemoryListPage = list(memoryStoreId, MemoryListParams.none())

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryListParams = MemoryListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemoryListPage =
        list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryListParams = MemoryListParams.none(),
    ): MemoryListPage = list(memoryStoreId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemoryListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemoryListPage

    /** @see list */
    fun list(params: MemoryListParams): MemoryListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(memoryStoreId: String, requestOptions: RequestOptions): MemoryListPage =
        list(memoryStoreId, MemoryListParams.none(), requestOptions)

    /** Delete a memory */
    fun delete(memoryId: String, params: MemoryDeleteParams): BetaManagedAgentsDeletedMemory =
        delete(memoryId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        memoryId: String,
        params: MemoryDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedMemory =
        delete(params.toBuilder().memoryId(memoryId).build(), requestOptions)

    /** @see delete */
    fun delete(params: MemoryDeleteParams): BetaManagedAgentsDeletedMemory =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: MemoryDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedMemory

    /** A view of [MemoryService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/memories?beta=true`, but is otherwise the same as
         * [MemoryService.create].
         */
        @MustBeClosed
        fun create(
            memoryStoreId: String,
            params: MemoryCreateParams,
        ): HttpResponseFor<BetaManagedAgentsMemory> =
            create(memoryStoreId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            memoryStoreId: String,
            params: MemoryCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemory> =
            create(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: MemoryCreateParams): HttpResponseFor<BetaManagedAgentsMemory> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: MemoryCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemory>

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true`, but is otherwise the
         * same as [MemoryService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            memoryId: String,
            params: MemoryRetrieveParams,
        ): HttpResponseFor<BetaManagedAgentsMemory> =
            retrieve(memoryId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            memoryId: String,
            params: MemoryRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemory> =
            retrieve(params.toBuilder().memoryId(memoryId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: MemoryRetrieveParams): HttpResponseFor<BetaManagedAgentsMemory> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: MemoryRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemory>

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true`, but is otherwise the
         * same as [MemoryService.update].
         */
        @MustBeClosed
        fun update(
            memoryId: String,
            params: MemoryUpdateParams,
        ): HttpResponseFor<BetaManagedAgentsMemory> =
            update(memoryId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            memoryId: String,
            params: MemoryUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemory> =
            update(params.toBuilder().memoryId(memoryId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: MemoryUpdateParams): HttpResponseFor<BetaManagedAgentsMemory> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: MemoryUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemory>

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memories?beta=true`, but is otherwise the same as
         * [MemoryService.list].
         */
        @MustBeClosed
        fun list(memoryStoreId: String): HttpResponseFor<MemoryListPage> =
            list(memoryStoreId, MemoryListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            memoryStoreId: String,
            params: MemoryListParams = MemoryListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemoryListPage> =
            list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            memoryStoreId: String,
            params: MemoryListParams = MemoryListParams.none(),
        ): HttpResponseFor<MemoryListPage> = list(memoryStoreId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: MemoryListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemoryListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: MemoryListParams): HttpResponseFor<MemoryListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemoryListPage> =
            list(memoryStoreId, MemoryListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true`, but is otherwise the
         * same as [MemoryService.delete].
         */
        @MustBeClosed
        fun delete(
            memoryId: String,
            params: MemoryDeleteParams,
        ): HttpResponseFor<BetaManagedAgentsDeletedMemory> =
            delete(memoryId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            memoryId: String,
            params: MemoryDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedMemory> =
            delete(params.toBuilder().memoryId(memoryId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(params: MemoryDeleteParams): HttpResponseFor<BetaManagedAgentsDeletedMemory> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: MemoryDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedMemory>
    }
}
