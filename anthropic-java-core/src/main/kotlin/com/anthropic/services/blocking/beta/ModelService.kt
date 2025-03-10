// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.BetaModelInfo
import com.anthropic.models.BetaModelListPage
import com.anthropic.models.BetaModelListParams
import com.anthropic.models.BetaModelRetrieveParams
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
    fun retrieve(params: BetaModelRetrieveParams): BetaModelInfo =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: BetaModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaModelInfo

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    fun list(): BetaModelListPage = list(BetaModelListParams.none())

    /** @see [list] */
    fun list(
        params: BetaModelListParams = BetaModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaModelListPage

    /** @see [list] */
    fun list(params: BetaModelListParams = BetaModelListParams.none()): BetaModelListPage =
        list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): BetaModelListPage =
        list(BetaModelListParams.none(), requestOptions)

    /** A view of [ModelService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `get /v1/models/{model_id}?beta=true`, but is otherwise
         * the same as [ModelService.retrieve].
         */
        @MustBeClosed
        fun retrieve(params: BetaModelRetrieveParams): HttpResponseFor<BetaModelInfo> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: BetaModelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaModelInfo>

        /**
         * Returns a raw HTTP response for `get /v1/models?beta=true`, but is otherwise the same as
         * [ModelService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<BetaModelListPage> = list(BetaModelListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BetaModelListParams = BetaModelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaModelListPage>

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BetaModelListParams = BetaModelListParams.none()
        ): HttpResponseFor<BetaModelListPage> = list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<BetaModelListPage> =
            list(BetaModelListParams.none(), requestOptions)
    }
}
