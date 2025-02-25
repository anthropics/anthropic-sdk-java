// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.blocking

import com.anthropic.core.RequestOptions
import com.anthropic.models.ModelInfo
import com.anthropic.models.ModelListPage
import com.anthropic.models.ModelListParams
import com.anthropic.models.ModelRetrieveParams

interface ModelService {

    /**
     * Get a specific model.
     *
     * The Models API response can be used to determine information about a specific model or
     * resolve a model alias to a model ID.
     */
    @JvmOverloads
    fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ModelInfo

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    @JvmOverloads
    fun list(
        params: ModelListParams = ModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ModelListPage

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    fun list(requestOptions: RequestOptions): ModelListPage =
        list(ModelListParams.none(), requestOptions)
}
