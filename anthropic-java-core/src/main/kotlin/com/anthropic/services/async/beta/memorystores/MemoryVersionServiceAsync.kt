// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.memorystores

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.memorystores.memoryversions.BetaManagedAgentsMemoryVersion
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListPageAsync
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRedactParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface MemoryVersionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryVersionServiceAsync

    /** GetMemoryVersion */
    fun retrieve(
        memoryVersionId: String,
        params: MemoryVersionRetrieveParams,
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> =
        retrieve(memoryVersionId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        memoryVersionId: String,
        params: MemoryVersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> =
        retrieve(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: MemoryVersionRetrieveParams
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemoryVersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryVersion>

    /** ListMemoryVersions */
    fun list(memoryStoreId: String): CompletableFuture<MemoryVersionListPageAsync> =
        list(memoryStoreId, MemoryVersionListParams.none())

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryVersionListParams = MemoryVersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemoryVersionListPageAsync> =
        list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryVersionListParams = MemoryVersionListParams.none(),
    ): CompletableFuture<MemoryVersionListPageAsync> =
        list(memoryStoreId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemoryVersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<MemoryVersionListPageAsync>

    /** @see list */
    fun list(params: MemoryVersionListParams): CompletableFuture<MemoryVersionListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        memoryStoreId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemoryVersionListPageAsync> =
        list(memoryStoreId, MemoryVersionListParams.none(), requestOptions)

    /** RedactMemoryVersion */
    fun redact(
        memoryVersionId: String,
        params: MemoryVersionRedactParams,
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> =
        redact(memoryVersionId, params, RequestOptions.none())

    /** @see redact */
    fun redact(
        memoryVersionId: String,
        params: MemoryVersionRedactParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> =
        redact(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

    /** @see redact */
    fun redact(
        params: MemoryVersionRedactParams
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> = redact(params, RequestOptions.none())

    /** @see redact */
    fun redact(
        params: MemoryVersionRedactParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsMemoryVersion>

    /**
     * A view of [MemoryVersionServiceAsync] that provides access to raw HTTP responses for each
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
        ): MemoryVersionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memory_versions/{memory_version_id}?beta=true`, but
         * is otherwise the same as [MemoryVersionServiceAsync.retrieve].
         */
        fun retrieve(
            memoryVersionId: String,
            params: MemoryVersionRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> =
            retrieve(memoryVersionId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            memoryVersionId: String,
            params: MemoryVersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> =
            retrieve(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: MemoryVersionRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: MemoryVersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memory_versions?beta=true`, but is otherwise the same
         * as [MemoryVersionServiceAsync.list].
         */
        fun list(
            memoryStoreId: String
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>> =
            list(memoryStoreId, MemoryVersionListParams.none())

        /** @see list */
        fun list(
            memoryStoreId: String,
            params: MemoryVersionListParams = MemoryVersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>> =
            list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see list */
        fun list(
            memoryStoreId: String,
            params: MemoryVersionListParams = MemoryVersionListParams.none(),
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>> =
            list(memoryStoreId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: MemoryVersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>>

        /** @see list */
        fun list(
            params: MemoryVersionListParams
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>> =
            list(memoryStoreId, MemoryVersionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/memory_versions/{memory_version_id}/redact?beta=true`,
         * but is otherwise the same as [MemoryVersionServiceAsync.redact].
         */
        fun redact(
            memoryVersionId: String,
            params: MemoryVersionRedactParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> =
            redact(memoryVersionId, params, RequestOptions.none())

        /** @see redact */
        fun redact(
            memoryVersionId: String,
            params: MemoryVersionRedactParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> =
            redact(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

        /** @see redact */
        fun redact(
            params: MemoryVersionRedactParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> =
            redact(params, RequestOptions.none())

        /** @see redact */
        fun redact(
            params: MemoryVersionRedactParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>>
    }
}
