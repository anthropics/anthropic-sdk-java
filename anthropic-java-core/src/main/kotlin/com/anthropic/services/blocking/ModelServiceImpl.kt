// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.prepare
import com.anthropic.errors.AnthropicError
import com.anthropic.models.ModelInfo
import com.anthropic.models.ModelListPage
import com.anthropic.models.ModelListParams
import com.anthropic.models.ModelRetrieveParams

class ModelServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ModelService {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val retrieveHandler: Handler<ModelInfo> =
        jsonHandler<ModelInfo>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Get a specific model.
     *
     * The Models API response can be used to determine information about a specific model or
     * resolve a model alias to a model ID.
     */
    override fun retrieve(params: ModelRetrieveParams, requestOptions: RequestOptions): ModelInfo {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "models", params.getPathParam(0))
                .build()
                .prepare(clientOptions, params)
        val response = clientOptions.httpClient.execute(request, requestOptions)
        return response
            .use { retrieveHandler.handle(it) }
            .also {
                if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                    it.validate()
                }
            }
    }

    private val listHandler: Handler<ModelListPage.Response> =
        jsonHandler<ModelListPage.Response>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    override fun list(params: ModelListParams, requestOptions: RequestOptions): ModelListPage {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "models")
                .build()
                .prepare(clientOptions, params)
        val response = clientOptions.httpClient.execute(request, requestOptions)
        return response
            .use { listHandler.handle(it) }
            .also {
                if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                    it.validate()
                }
            }
            .let { ModelListPage.of(this, params, it) }
    }
}
