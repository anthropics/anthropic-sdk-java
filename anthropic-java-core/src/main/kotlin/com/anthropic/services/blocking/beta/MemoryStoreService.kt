// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.memorystores.BetaManagedAgentsDeletedMemoryStore
import com.anthropic.models.beta.memorystores.BetaManagedAgentsMemoryStore
import com.anthropic.models.beta.memorystores.MemoryStoreArchiveParams
import com.anthropic.models.beta.memorystores.MemoryStoreCreateParams
import com.anthropic.models.beta.memorystores.MemoryStoreDeleteParams
import com.anthropic.models.beta.memorystores.MemoryStoreListPage
import com.anthropic.models.beta.memorystores.MemoryStoreListParams
import com.anthropic.models.beta.memorystores.MemoryStoreRetrieveParams
import com.anthropic.models.beta.memorystores.MemoryStoreUpdateParams
import com.anthropic.services.blocking.beta.memorystores.MemoryService
import com.anthropic.services.blocking.beta.memorystores.MemoryVersionService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface MemoryStoreService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryStoreService

    fun memories(): MemoryService

    fun memoryVersions(): MemoryVersionService

    /** Create a memory store */
    fun create(params: MemoryStoreCreateParams): BetaManagedAgentsMemoryStore =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: MemoryStoreCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore

    /** Retrieve a memory store */
    fun retrieve(memoryStoreId: String): BetaManagedAgentsMemoryStore =
        retrieve(memoryStoreId, MemoryStoreRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        memoryStoreId: String,
        params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore =
        retrieve(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        memoryStoreId: String,
        params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
    ): BetaManagedAgentsMemoryStore = retrieve(memoryStoreId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemoryStoreRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore

    /** @see retrieve */
    fun retrieve(params: MemoryStoreRetrieveParams): BetaManagedAgentsMemoryStore =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        retrieve(memoryStoreId, MemoryStoreRetrieveParams.none(), requestOptions)

    /** Update a memory store */
    fun update(memoryStoreId: String): BetaManagedAgentsMemoryStore =
        update(memoryStoreId, MemoryStoreUpdateParams.none())

    /** @see update */
    fun update(
        memoryStoreId: String,
        params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore =
        update(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see update */
    fun update(
        memoryStoreId: String,
        params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
    ): BetaManagedAgentsMemoryStore = update(memoryStoreId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: MemoryStoreUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore

    /** @see update */
    fun update(params: MemoryStoreUpdateParams): BetaManagedAgentsMemoryStore =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        update(memoryStoreId, MemoryStoreUpdateParams.none(), requestOptions)

    /** List memory stores */
    fun list(): MemoryStoreListPage = list(MemoryStoreListParams.none())

    /** @see list */
    fun list(
        params: MemoryStoreListParams = MemoryStoreListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemoryStoreListPage

    /** @see list */
    fun list(params: MemoryStoreListParams = MemoryStoreListParams.none()): MemoryStoreListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): MemoryStoreListPage =
        list(MemoryStoreListParams.none(), requestOptions)

    /** Delete a memory store */
    fun delete(memoryStoreId: String): BetaManagedAgentsDeletedMemoryStore =
        delete(memoryStoreId, MemoryStoreDeleteParams.none())

    /** @see delete */
    fun delete(
        memoryStoreId: String,
        params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedMemoryStore =
        delete(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see delete */
    fun delete(
        memoryStoreId: String,
        params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
    ): BetaManagedAgentsDeletedMemoryStore = delete(memoryStoreId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: MemoryStoreDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedMemoryStore

    /** @see delete */
    fun delete(params: MemoryStoreDeleteParams): BetaManagedAgentsDeletedMemoryStore =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeletedMemoryStore =
        delete(memoryStoreId, MemoryStoreDeleteParams.none(), requestOptions)

    /** Archive a memory store */
    fun archive(memoryStoreId: String): BetaManagedAgentsMemoryStore =
        archive(memoryStoreId, MemoryStoreArchiveParams.none())

    /** @see archive */
    fun archive(
        memoryStoreId: String,
        params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore =
        archive(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see archive */
    fun archive(
        memoryStoreId: String,
        params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
    ): BetaManagedAgentsMemoryStore = archive(memoryStoreId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: MemoryStoreArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryStore

    /** @see archive */
    fun archive(params: MemoryStoreArchiveParams): BetaManagedAgentsMemoryStore =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        archive(memoryStoreId, MemoryStoreArchiveParams.none(), requestOptions)

    /**
     * A view of [MemoryStoreService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryStoreService.WithRawResponse

        fun memories(): MemoryService.WithRawResponse

        fun memoryVersions(): MemoryVersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/memory_stores?beta=true`, but is otherwise the
         * same as [MemoryStoreService.create].
         */
        @MustBeClosed
        fun create(params: MemoryStoreCreateParams): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: MemoryStoreCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore>

        /**
         * Returns a raw HTTP response for `get /v1/memory_stores/{memory_store_id}?beta=true`, but
         * is otherwise the same as [MemoryStoreService.retrieve].
         */
        @MustBeClosed
        fun retrieve(memoryStoreId: String): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            retrieve(memoryStoreId, MemoryStoreRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            memoryStoreId: String,
            params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            retrieve(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            memoryStoreId: String,
            params: MemoryStoreRetrieveParams = MemoryStoreRetrieveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            retrieve(memoryStoreId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: MemoryStoreRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: MemoryStoreRetrieveParams
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            retrieve(memoryStoreId, MemoryStoreRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/memory_stores/{memory_store_id}?beta=true`, but
         * is otherwise the same as [MemoryStoreService.update].
         */
        @MustBeClosed
        fun update(memoryStoreId: String): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            update(memoryStoreId, MemoryStoreUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            memoryStoreId: String,
            params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            update(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            memoryStoreId: String,
            params: MemoryStoreUpdateParams = MemoryStoreUpdateParams.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            update(memoryStoreId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: MemoryStoreUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore>

        /** @see update */
        @MustBeClosed
        fun update(params: MemoryStoreUpdateParams): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            update(memoryStoreId, MemoryStoreUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/memory_stores?beta=true`, but is otherwise the
         * same as [MemoryStoreService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<MemoryStoreListPage> = list(MemoryStoreListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: MemoryStoreListParams = MemoryStoreListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemoryStoreListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: MemoryStoreListParams = MemoryStoreListParams.none()
        ): HttpResponseFor<MemoryStoreListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<MemoryStoreListPage> =
            list(MemoryStoreListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/memory_stores/{memory_store_id}?beta=true`,
         * but is otherwise the same as [MemoryStoreService.delete].
         */
        @MustBeClosed
        fun delete(memoryStoreId: String): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore> =
            delete(memoryStoreId, MemoryStoreDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            memoryStoreId: String,
            params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore> =
            delete(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            memoryStoreId: String,
            params: MemoryStoreDeleteParams = MemoryStoreDeleteParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore> =
            delete(memoryStoreId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: MemoryStoreDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: MemoryStoreDeleteParams
        ): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore> =
            delete(memoryStoreId, MemoryStoreDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/archive?beta=true`, but is otherwise the same as
         * [MemoryStoreService.archive].
         */
        @MustBeClosed
        fun archive(memoryStoreId: String): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            archive(memoryStoreId, MemoryStoreArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            memoryStoreId: String,
            params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            archive(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            memoryStoreId: String,
            params: MemoryStoreArchiveParams = MemoryStoreArchiveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            archive(memoryStoreId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: MemoryStoreArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryStore>

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: MemoryStoreArchiveParams
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> = archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> =
            archive(memoryStoreId, MemoryStoreArchiveParams.none(), requestOptions)
    }
}
