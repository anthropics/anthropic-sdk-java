// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.prepareAsync
import com.anthropic.errors.AnthropicError
import com.anthropic.models.ModelInfo
import com.anthropic.models.ModelListPageAsync
import com.anthropic.models.ModelListParams
import com.anthropic.models.ModelRetrieveParams
import java.util.concurrent.CompletableFuture

class ModelServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ModelServiceAsync {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val retrieveHandler: Handler<ModelInfo> =
        jsonHandler<ModelInfo>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Get a specific model.
     *
     * The Models API response can be used to determine information about a specific model or
     * resolve a model alias to a model ID.
     */
    override fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ModelInfo> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "models", params.getPathParam(0))
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
            }
    }

    private val listHandler: Handler<ModelListPageAsync.Response> =
        jsonHandler<ModelListPageAsync.Response>(clientOptions.jsonMapper)
            .withErrorHandler(errorHandler)

    /**
     * List available models.
     *
     * The Models API response can be used to determine which models are available for use in the
     * API. More recently released models are listed first.
     */
    override fun list(
        params: ModelListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ModelListPageAsync> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "models")
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
                    .let { ModelListPageAsync.of(this, params, it) }
            }
    }
}
