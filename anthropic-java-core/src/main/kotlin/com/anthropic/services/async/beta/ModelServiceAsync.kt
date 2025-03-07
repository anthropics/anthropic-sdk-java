// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.BetaModelInfo
import com.anthropic.models.BetaModelListPageAsync
import com.anthropic.models.BetaModelListParams
import com.anthropic.models.BetaModelRetrieveParams
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
    fun retrieve(params: BetaModelRetrieveParams): CompletableFuture<BetaModelInfo> =
        retrieve(params, RequestOptions.none())

    /** @see [retrieve] */
    fun retrieve(
        params: BetaModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaModelInfo>

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    fun list(): CompletableFuture<BetaModelListPageAsync> = list(BetaModelListParams.none())

    /** @see [list] */
    fun list(
        params: BetaModelListParams = BetaModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaModelListPageAsync>

    /** @see [list] */
    fun list(
        params: BetaModelListParams = BetaModelListParams.none()
    ): CompletableFuture<BetaModelListPageAsync> = list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): CompletableFuture<BetaModelListPageAsync> =
        list(BetaModelListParams.none(), requestOptions)

    /** A view of [ModelServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a raw HTTP response for `get /v1/models/{model_id}?beta=true`, but is otherwise
         * the same as [ModelServiceAsync.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            params: BetaModelRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>> =
            retrieve(params, RequestOptions.none())

        /** @see [retrieve] */
        @MustBeClosed
        fun retrieve(
            params: BetaModelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaModelInfo>>

        /**
         * Returns a raw HTTP response for `get /v1/models?beta=true`, but is otherwise the same as
         * [ModelServiceAsync.list].
         */
        @MustBeClosed
        fun list(): CompletableFuture<HttpResponseFor<BetaModelListPageAsync>> =
            list(BetaModelListParams.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BetaModelListParams = BetaModelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaModelListPageAsync>>

        /** @see [list] */
        @MustBeClosed
        fun list(
            params: BetaModelListParams = BetaModelListParams.none()
        ): CompletableFuture<HttpResponseFor<BetaModelListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see [list] */
        @MustBeClosed
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BetaModelListPageAsync>> =
            list(BetaModelListParams.none(), requestOptions)
    }
}
