// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import kotlinx.kmp.util.core.streamExecutor
import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.handlers.errorBodyHandler
import kotlinx.kmp.util.core.handlers.errorHandler
import kotlinx.kmp.util.core.handlers.jsonHandler
import kotlinx.kmp.util.core.http.HttpMethod
import kotlinx.kmp.util.core.http.HttpRequest
import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.http.HttpResponse.Handler
import kotlinx.kmp.util.core.http.HttpResponseFor
import kotlinx.kmp.util.core.http.parseable
import kotlinx.kmp.util.core.prepareSuspend
import com.anthropic.models.models.ModelInfo
import com.anthropic.models.models.ModelListPageAsync
import com.anthropic.models.models.ModelListPageResponse
import com.anthropic.models.models.ModelListParams
import com.anthropic.models.models.ModelRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ModelServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ModelServiceAsync {

    private val withRawResponse: ModelServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ModelServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ModelServiceAsync =
        ModelServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun retrieve(
        params: ModelRetrieveParams,
        requestOptions: RequestOptions,
    ): ModelInfo =
        // get /v1/models/{model_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun list(
        params: ModelListParams,
        requestOptions: RequestOptions,
    ): ModelListPageAsync =
        // get /v1/models
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ModelServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ModelServiceAsync.WithRawResponse =
            ModelServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<ModelInfo> =
            jsonHandler<ModelInfo>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: ModelRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ModelInfo> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("modelId", params.modelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "models", params._pathParam(0))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val listHandler: Handler<ModelListPageResponse> =
            jsonHandler<ModelListPageResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: ModelListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ModelListPageAsync> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "models")
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                ModelListPageAsync.builder()
                                    .service(ModelServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamExecutor())
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
        }
    }
}
