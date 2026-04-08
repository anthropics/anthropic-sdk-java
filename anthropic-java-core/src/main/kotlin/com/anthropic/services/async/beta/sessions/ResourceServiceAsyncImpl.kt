// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

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
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsDeleteSessionResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsFileResource
import com.anthropic.models.beta.sessions.resources.ResourceAddParams
import com.anthropic.models.beta.sessions.resources.ResourceDeleteParams
import com.anthropic.models.beta.sessions.resources.ResourceListPageAsync
import com.anthropic.models.beta.sessions.resources.ResourceListPageResponse
import com.anthropic.models.beta.sessions.resources.ResourceListParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveResponse
import com.anthropic.models.beta.sessions.resources.ResourceUpdateParams
import com.anthropic.models.beta.sessions.resources.ResourceUpdateResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ResourceServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ResourceServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: ResourceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ResourceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ResourceServiceAsync =
        ResourceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ResourceRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ResourceRetrieveResponse> =
        // get /v1/sessions/{session_id}/resources/{resource_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: ResourceUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ResourceUpdateResponse> =
        // post /v1/sessions/{session_id}/resources/{resource_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ResourceListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ResourceListPageAsync> =
        // get /v1/sessions/{session_id}/resources?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: ResourceDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeleteSessionResource> =
        // delete /v1/sessions/{session_id}/resources/{resource_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun add(
        params: ResourceAddParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsFileResource> =
        // post /v1/sessions/{session_id}/resources?beta=true
        withRawResponse().add(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ResourceServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ResourceServiceAsync.WithRawResponse =
            ResourceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<ResourceRetrieveResponse> =
            jsonHandler<ResourceRetrieveResponse>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ResourceRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ResourceRetrieveResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("resourceId", params.resourceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "resources",
                        params._pathParam(1),
                    )
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

        private val updateHandler: Handler<ResourceUpdateResponse> =
            jsonHandler<ResourceUpdateResponse>(clientOptions.jsonMapper)

        override fun update(
            params: ResourceUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ResourceUpdateResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("resourceId", params.resourceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "resources",
                        params._pathParam(1),
                    )
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

        private val listHandler: Handler<ResourceListPageResponse> =
            jsonHandler<ResourceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ResourceListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ResourceListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "resources")
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
                                ResourceListPageAsync.builder()
                                    .service(ResourceServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeleteSessionResource> =
            jsonHandler<BetaManagedAgentsDeleteSessionResource>(clientOptions.jsonMapper)

        override fun delete(
            params: ResourceDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeleteSessionResource>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("resourceId", params.resourceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "sessions",
                        params._pathParam(0),
                        "resources",
                        params._pathParam(1),
                    )
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

        private val addHandler: Handler<BetaManagedAgentsFileResource> =
            jsonHandler<BetaManagedAgentsFileResource>(clientOptions.jsonMapper)

        override fun add(
            params: ResourceAddParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsFileResource>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("sessionId", params.sessionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "sessions", params._pathParam(0), "resources")
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
                            .use { addHandler.handle(it) }
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
