// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.models.ModelInfo
import com.anthropic.models.models.ModelListPage
import com.anthropic.models.models.ModelListPageResponse
import com.anthropic.models.models.ModelListParams
import com.anthropic.models.models.ModelRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ModelServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ModelService {

    private val withRawResponse: ModelService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ModelService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ModelService =
        ModelServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(params: ModelRetrieveParams, requestOptions: RequestOptions): ModelInfo =
        // get /v1/models/{model_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: ModelListParams, requestOptions: RequestOptions): ModelListPage =
        // get /v1/models
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ModelService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ModelService.WithRawResponse =
            ModelServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<ModelInfo> =
            jsonHandler<ModelInfo>(clientOptions.jsonMapper)

        override fun retrieve(
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        override fun list(
            params: ModelListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ModelListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "models")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let {
                        ModelListPage.builder()
                            .service(ModelServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
