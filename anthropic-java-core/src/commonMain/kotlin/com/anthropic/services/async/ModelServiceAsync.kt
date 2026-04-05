// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.http.HttpResponseFor
import com.anthropic.models.models.ModelInfo
import com.anthropic.models.models.ModelListPageAsync
import com.anthropic.models.models.ModelListParams
import com.anthropic.models.models.ModelRetrieveParams
import java.util.function.Consumer

interface ModelServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ModelServiceAsync

    /**
     * Get a specific model.
     *
     * The Models API response can be used to determine information about a specific model or
     * resolve a model alias to a model ID.
     */
    suspend fun retrieve(modelId: String): ModelInfo =
        retrieve(modelId, ModelRetrieveParams.none())

    /** @see retrieve */
    suspend fun retrieve(
        modelId: String,
        params: ModelRetrieveParams = ModelRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ModelInfo =
        retrieve(params.toBuilder().modelId(modelId).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        modelId: String,
        params: ModelRetrieveParams = ModelRetrieveParams.none(),
    ): ModelInfo = retrieve(modelId, params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ModelInfo

    /** @see retrieve */
    suspend fun retrieve(params: ModelRetrieveParams): ModelInfo =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(modelId: String, requestOptions: RequestOptions): ModelInfo =
        retrieve(modelId, ModelRetrieveParams.none(), requestOptions)

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    suspend fun list(): ModelListPageAsync = list(ModelListParams.none())

    /** @see list */
    suspend fun list(
        params: ModelListParams = ModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ModelListPageAsync

    /** @see list */
    suspend fun list(
        params: ModelListParams = ModelListParams.none()
    ): ModelListPageAsync = list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): ModelListPageAsync =
        list(ModelListParams.none(), requestOptions)

    /** A view of [ModelServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ModelServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/models/{model_id}`, but is otherwise the same as
         * [ModelServiceAsync.retrieve].
         */
        suspend fun retrieve(modelId: String): HttpResponseFor<ModelInfo> =
            retrieve(modelId, ModelRetrieveParams.none())

        /** @see retrieve */
        suspend fun retrieve(
            modelId: String,
            params: ModelRetrieveParams = ModelRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ModelInfo> =
            retrieve(params.toBuilder().modelId(modelId).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(
            modelId: String,
            params: ModelRetrieveParams = ModelRetrieveParams.none(),
        ): HttpResponseFor<ModelInfo> =
            retrieve(modelId, params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            params: ModelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ModelInfo>

        /** @see retrieve */
        suspend fun retrieve(params: ModelRetrieveParams): HttpResponseFor<ModelInfo> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            modelId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ModelInfo> =
            retrieve(modelId, ModelRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/models`, but is otherwise the same as
         * [ModelServiceAsync.list].
         */
        suspend fun list(): HttpResponseFor<ModelListPageAsync> =
            list(ModelListParams.none())

        /** @see list */
        suspend fun list(
            params: ModelListParams = ModelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ModelListPageAsync>

        /** @see list */
        suspend fun list(
            params: ModelListParams = ModelListParams.none()
        ): HttpResponseFor<ModelListPageAsync> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(
            requestOptions: RequestOptions
        ): HttpResponseFor<ModelListPageAsync> =
            list(ModelListParams.none(), requestOptions)
    }
}
