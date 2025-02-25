// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.anthropic.services.blocking.beta

import com.anthropic.core.RequestOptions
import com.anthropic.models.BetaModelInfo
import com.anthropic.models.BetaModelListPage
import com.anthropic.models.BetaModelListParams
import com.anthropic.models.BetaModelRetrieveParams

interface ModelService {

    /**
     * Get a specific model.
     *
     * The Models API response can be used to determine information about a specific model or
     * resolve a model alias to a model ID.
     */
    @JvmOverloads
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
    @JvmOverloads
    fun list(
        params: BetaModelListParams = BetaModelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaModelListPage

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    fun list(requestOptions: RequestOptions): BetaModelListPage =
        list(BetaModelListParams.none(), requestOptions)
}
