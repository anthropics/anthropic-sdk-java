// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.environments.BetaEnvironment
import com.anthropic.models.beta.environments.BetaEnvironmentDeleteResponse
import com.anthropic.models.beta.environments.EnvironmentArchiveParams
import com.anthropic.models.beta.environments.EnvironmentCreateParams
import com.anthropic.models.beta.environments.EnvironmentDeleteParams
import com.anthropic.models.beta.environments.EnvironmentListPageAsync
import com.anthropic.models.beta.environments.EnvironmentListPageResponse
import com.anthropic.models.beta.environments.EnvironmentListParams
import com.anthropic.models.beta.environments.EnvironmentRetrieveParams
import com.anthropic.models.beta.environments.EnvironmentUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class EnvironmentServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    EnvironmentServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: EnvironmentServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): EnvironmentServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): EnvironmentServiceAsync =
        EnvironmentServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: EnvironmentCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        // post /v1/environments?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: EnvironmentRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        // get /v1/environments/{environment_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: EnvironmentUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        // post /v1/environments/{environment_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: EnvironmentListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<EnvironmentListPageAsync> =
        // get /v1/environments?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: EnvironmentDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironmentDeleteResponse> =
        // delete /v1/environments/{environment_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun archive(
        params: EnvironmentArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaEnvironment> =
        // post /v1/environments/{environment_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        EnvironmentServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): EnvironmentServiceAsync.WithRawResponse =
            EnvironmentServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaEnvironment> =
            jsonHandler<BetaEnvironment>(clientOptions.jsonMapper)

        override fun create(
            params: EnvironmentCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<BetaEnvironment> =
            jsonHandler<BetaEnvironment>(clientOptions.jsonMapper)

        override fun retrieve(
            params: EnvironmentRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val updateHandler: Handler<BetaEnvironment> =
            jsonHandler<BetaEnvironment>(clientOptions.jsonMapper)

        override fun update(
            params: EnvironmentUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val listHandler: Handler<EnvironmentListPageResponse> =
            jsonHandler<EnvironmentListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: EnvironmentListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<EnvironmentListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                                EnvironmentListPageAsync.builder()
                                    .service(EnvironmentServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<BetaEnvironmentDeleteResponse> =
            jsonHandler<BetaEnvironmentDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: EnvironmentDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironmentDeleteResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { deleteHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val archiveHandler: Handler<BetaEnvironment> =
            jsonHandler<BetaEnvironment>(clientOptions.jsonMapper)

        override fun archive(
            params: EnvironmentArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaEnvironment>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0), "archive")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { archiveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }
    }
}
