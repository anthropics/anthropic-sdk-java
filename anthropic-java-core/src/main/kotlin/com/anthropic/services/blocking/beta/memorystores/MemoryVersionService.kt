// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.memorystores

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.memorystores.memoryversions.BetaManagedAgentsMemoryVersion
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListPage
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRedactParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface MemoryVersionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryVersionService

    /** GetMemoryVersion */
    fun retrieve(
        memoryVersionId: String,
        params: MemoryVersionRetrieveParams,
    ): BetaManagedAgentsMemoryVersion = retrieve(memoryVersionId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        memoryVersionId: String,
        params: MemoryVersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryVersion =
        retrieve(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: MemoryVersionRetrieveParams): BetaManagedAgentsMemoryVersion =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: MemoryVersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryVersion

    /** ListMemoryVersions */
    fun list(memoryStoreId: String): MemoryVersionListPage =
        list(memoryStoreId, MemoryVersionListParams.none())

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryVersionListParams = MemoryVersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemoryVersionListPage =
        list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

    /** @see list */
    fun list(
        memoryStoreId: String,
        params: MemoryVersionListParams = MemoryVersionListParams.none(),
    ): MemoryVersionListPage = list(memoryStoreId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: MemoryVersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MemoryVersionListPage

    /** @see list */
    fun list(params: MemoryVersionListParams): MemoryVersionListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(memoryStoreId: String, requestOptions: RequestOptions): MemoryVersionListPage =
        list(memoryStoreId, MemoryVersionListParams.none(), requestOptions)

    /** RedactMemoryVersion */
    fun redact(
        memoryVersionId: String,
        params: MemoryVersionRedactParams,
    ): BetaManagedAgentsMemoryVersion = redact(memoryVersionId, params, RequestOptions.none())

    /** @see redact */
    fun redact(
        memoryVersionId: String,
        params: MemoryVersionRedactParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryVersion =
        redact(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

    /** @see redact */
    fun redact(params: MemoryVersionRedactParams): BetaManagedAgentsMemoryVersion =
        redact(params, RequestOptions.none())

    /** @see redact */
    fun redact(
        params: MemoryVersionRedactParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsMemoryVersion

    /**
     * A view of [MemoryVersionService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryVersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memory_versions/{memory_version_id}?beta=true`, but
         * is otherwise the same as [MemoryVersionService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            memoryVersionId: String,
            params: MemoryVersionRetrieveParams,
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion> =
            retrieve(memoryVersionId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            memoryVersionId: String,
            params: MemoryVersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion> =
            retrieve(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: MemoryVersionRetrieveParams
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: MemoryVersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion>

        /**
         * Returns a raw HTTP response for `get
         * /v1/memory_stores/{memory_store_id}/memory_versions?beta=true`, but is otherwise the same
         * as [MemoryVersionService.list].
         */
        @MustBeClosed
        fun list(memoryStoreId: String): HttpResponseFor<MemoryVersionListPage> =
            list(memoryStoreId, MemoryVersionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            memoryStoreId: String,
            params: MemoryVersionListParams = MemoryVersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemoryVersionListPage> =
            list(params.toBuilder().memoryStoreId(memoryStoreId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            memoryStoreId: String,
            params: MemoryVersionListParams = MemoryVersionListParams.none(),
        ): HttpResponseFor<MemoryVersionListPage> =
            list(memoryStoreId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: MemoryVersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MemoryVersionListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: MemoryVersionListParams): HttpResponseFor<MemoryVersionListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            memoryStoreId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemoryVersionListPage> =
            list(memoryStoreId, MemoryVersionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/memory_stores/{memory_store_id}/memory_versions/{memory_version_id}/redact?beta=true`,
         * but is otherwise the same as [MemoryVersionService.redact].
         */
        @MustBeClosed
        fun redact(
            memoryVersionId: String,
            params: MemoryVersionRedactParams,
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion> =
            redact(memoryVersionId, params, RequestOptions.none())

        /** @see redact */
        @MustBeClosed
        fun redact(
            memoryVersionId: String,
            params: MemoryVersionRedactParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion> =
            redact(params.toBuilder().memoryVersionId(memoryVersionId).build(), requestOptions)

        /** @see redact */
        @MustBeClosed
        fun redact(
            params: MemoryVersionRedactParams
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion> = redact(params, RequestOptions.none())

        /** @see redact */
        @MustBeClosed
        fun redact(
            params: MemoryVersionRedactParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsMemoryVersion>
    }
}
