// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.models.ModelInfo
import com.anthropic.models.models.ModelListPageAsync
import com.anthropic.models.models.ModelListParams
import com.anthropic.models.models.ModelRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.concurrent.CompletableFuture

interface ModelServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Get a specific model.
     *
     * The Models API response can be used to determine information about a specific model or
     * resolve a model alias to a model ID.
     */
    fun retrieve(params: ModelRetrieveParams): CompletableFuture<ModelInfo> =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ModelInfo>

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    fun list(): CompletableFuture<ModelListPageAsync> = list(ModelListParams.none())

    /** @see [list] */
    fun list(
        params: ModelListParams = ModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ModelListPageAsync>

    /** @see [list] */
    fun list(
        params: ModelListParams = ModelListParams.none()
    ): CompletableFuture<ModelListPageAsync> = list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): CompletableFuture<ModelListPageAsync> =
        list(ModelListParams.none(), requestOptions)

    /** A view of [ModelServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `get /v1/models/{model_id}`, but is otherwise the same as
         * [ModelServiceAsync.retrieve].
         */
        @MustBeClosed
        fun retrieve(params: ModelRetrieveParams): CompletableFuture<HttpResponseFor<ModelInfo>> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: ModelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ModelInfo>>

        /**
         * Returns a raw HTTP response for `get /v1/models`, but is otherwise the same as
         * [ModelServiceAsync.list].
         */
        @MustBeClosed
        fun list(): CompletableFuture<HttpResponseFor<ModelListPageAsync>> =
            list(ModelListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: ModelListParams = ModelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ModelListPageAsync>>

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: ModelListParams = ModelListParams.none()
        ): CompletableFuture<HttpResponseFor<ModelListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ModelListPageAsync>> =
            list(ModelListParams.none(), requestOptions)
    }
}
