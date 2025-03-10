// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.models.BetaModelInfo
import com.anthropic.models.beta.models.ModelListPage
import com.anthropic.models.beta.models.ModelListParams
import com.anthropic.models.beta.models.ModelRetrieveParams
import com.google.errorprone.annotations.MustBeClosed

interface ModelService {

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
    fun retrieve(params: ModelRetrieveParams): BetaModelInfo =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaModelInfo

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    fun list(): ModelListPage = list(ModelListParams.none())

    /** @see [list] */
    fun list(
        params: ModelListParams = ModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ModelListPage

    /** @see [list] */
    fun list(params: ModelListParams = ModelListParams.none()): ModelListPage =
        list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): ModelListPage =
        list(ModelListParams.none(), requestOptions)

    /** A view of [ModelService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `get /v1/models/{model_id}?beta=true`, but is otherwise
         * the same as [ModelService.retrieve].
         */
        @MustBeClosed
        fun retrieve(params: ModelRetrieveParams): HttpResponseFor<BetaModelInfo> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: ModelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaModelInfo>

        /**
         * Returns a raw HTTP response for `get /v1/models?beta=true`, but is otherwise the same as
         * [ModelService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<ModelListPage> = list(ModelListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: ModelListParams = ModelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ModelListPage>

        /** @see [list] */
        @MustBeClosed
        fun list(params: ModelListParams = ModelListParams.none()): HttpResponseFor<ModelListPage> =
            list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ModelListPage> =
            list(ModelListParams.none(), requestOptions)
    }
}
