// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

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
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.apikeys.ApiKeyListPageAsync
import com.anthropic.models.beta.organization.apikeys.ApiKeyListPageResponse
import com.anthropic.models.beta.organization.apikeys.ApiKeyListParams
import com.anthropic.models.beta.organization.apikeys.ApiKeyRetrieveParams
import com.anthropic.models.beta.organization.apikeys.ApiKeyUpdateParams
import com.anthropic.models.beta.organization.apikeys.BetaApiKey
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ApiKeyServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ApiKeyServiceAsync {

    private val withRawResponse: ApiKeyServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ApiKeyServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ApiKeyServiceAsync =
        ApiKeyServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ApiKeyRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaApiKey> =
        // get /v1/organizations/api_keys/{api_key_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: ApiKeyUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaApiKey> =
        // post /v1/organizations/api_keys/{api_key_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ApiKeyListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ApiKeyListPageAsync> =
        // get /v1/organizations/api_keys?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ApiKeyServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ApiKeyServiceAsync.WithRawResponse =
            ApiKeyServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaApiKey> =
            jsonHandler<BetaApiKey>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ApiKeyRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("apiKeyId", params.apiKeyId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "api_keys", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val updateHandler: Handler<BetaApiKey> =
            jsonHandler<BetaApiKey>(clientOptions.jsonMapper)

        override fun update(
            params: ApiKeyUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("apiKeyId", params.apiKeyId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "api_keys", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { updateHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<ApiKeyListPageResponse> =
            jsonHandler<ApiKeyListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ApiKeyListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ApiKeyListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "api_keys")
                    .putQueryParam("beta", "true")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                ApiKeyListPageAsync.builder()
                                    .service(ApiKeyServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }
    }
}
