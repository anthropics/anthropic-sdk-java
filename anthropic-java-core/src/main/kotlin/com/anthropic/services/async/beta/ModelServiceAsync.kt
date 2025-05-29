// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.models.BetaModelInfo
import com.anthropic.models.beta.models.ModelListPageAsync
import com.anthropic.models.beta.models.ModelListParams
import com.anthropic.models.beta.models.ModelRetrieveParams
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
    fun retrieve(modelId: String): CompletableFuture<BetaModelInfo> =
        retrieve(modelId, ModelRetrieveParams.none())

    /** @see [retrieve] */
    fun retrieve(
        modelId: String,
        params: ModelRetrieveParams = ModelRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaModelInfo> =
        retrieve(params.toBuilder().modelId(modelId).build(), requestOptions)

    /** @see [retrieve] */
    fun retrieve(
        modelId: String,
        params: ModelRetrieveParams = ModelRetrieveParams.none(),
    ): CompletableFuture<BetaModelInfo> = retrieve(modelId, params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaModelInfo>

    /** @see [retrieve] */
    fun retrieve(params: ModelRetrieveParams): CompletableFuture<BetaModelInfo> =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        modelId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaModelInfo> =
        retrieve(modelId, ModelRetrieveParams.none(), requestOptions)

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
         * Returns a raw HTTP response for `get /v1/models/{model_id}?beta=true`, but is otherwise
         * the same as [ModelServiceAsync.retrieve].
         */
        fun retrieve(modelId: String): CompletableFuture<HttpResponseFor<BetaModelInfo>> =
            retrieve(modelId, ModelRetrieveParams.none())

        /** @see [retrieve] */
        fun retrieve(
            modelId: String,
            params: ModelRetrieveParams = ModelRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>> =
            retrieve(params.toBuilder().modelId(modelId).build(), requestOptions)

        /** @see [retrieve] */
        fun retrieve(
            modelId: String,
            params: ModelRetrieveParams = ModelRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>> =
            retrieve(modelId, params, RequestOptions.none())

        /** @see [retrieve] */
        fun retrieve(
            params: ModelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>>

        /** @see [retrieve] */
        fun retrieve(
            params: ModelRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        fun retrieve(
            modelId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>> =
            retrieve(modelId, ModelRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/models?beta=true`, but is otherwise the same as
         * [ModelServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<ModelListPageAsync>> =
            list(ModelListParams.none())

        /** @see [list] */
        fun list(
            params: ModelListParams = ModelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ModelListPageAsync>>

        /** @see [list] */
        fun list(
            params: ModelListParams = ModelListParams.none()
        ): CompletableFuture<HttpResponseFor<ModelListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see [list] */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ModelListPageAsync>> =
            list(ModelListParams.none(), requestOptions)
    }
}
