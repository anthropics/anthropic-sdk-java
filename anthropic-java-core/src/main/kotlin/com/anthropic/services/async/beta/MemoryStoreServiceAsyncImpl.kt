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
import com.anthropic.models.beta.memorystores.BetaManagedAgentsDeletedMemoryStore
import com.anthropic.models.beta.memorystores.BetaManagedAgentsMemoryStore
import com.anthropic.models.beta.memorystores.MemoryStoreArchiveParams
import com.anthropic.models.beta.memorystores.MemoryStoreCreateParams
import com.anthropic.models.beta.memorystores.MemoryStoreDeleteParams
import com.anthropic.models.beta.memorystores.MemoryStoreListPageAsync
import com.anthropic.models.beta.memorystores.MemoryStoreListPageResponse
import com.anthropic.models.beta.memorystores.MemoryStoreListParams
import com.anthropic.models.beta.memorystores.MemoryStoreRetrieveParams
import com.anthropic.models.beta.memorystores.MemoryStoreUpdateParams
import com.anthropic.services.async.beta.memorystores.MemoryServiceAsync
import com.anthropic.services.async.beta.memorystores.MemoryServiceAsyncImpl
import com.anthropic.services.async.beta.memorystores.MemoryVersionServiceAsync
import com.anthropic.services.async.beta.memorystores.MemoryVersionServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class MemoryStoreServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MemoryStoreServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: MemoryStoreServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val memories: MemoryServiceAsync by lazy { MemoryServiceAsyncImpl(clientOptions) }

    private val memoryVersions: MemoryVersionServiceAsync by lazy {
        MemoryVersionServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): MemoryStoreServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryStoreServiceAsync =
        MemoryStoreServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun memories(): MemoryServiceAsync = memories

    override fun memoryVersions(): MemoryVersionServiceAsync = memoryVersions

    override fun create(
        params: MemoryStoreCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        // post /v1/memory_stores?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: MemoryStoreRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        // get /v1/memory_stores/{memory_store_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: MemoryStoreUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        // post /v1/memory_stores/{memory_store_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: MemoryStoreListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemoryStoreListPageAsync> =
        // get /v1/memory_stores?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: MemoryStoreDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeletedMemoryStore> =
        // delete /v1/memory_stores/{memory_store_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun archive(
        params: MemoryStoreArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryStore> =
        // post /v1/memory_stores/{memory_store_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MemoryStoreServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val memories: MemoryServiceAsync.WithRawResponse by lazy {
            MemoryServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val memoryVersions: MemoryVersionServiceAsync.WithRawResponse by lazy {
            MemoryVersionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryStoreServiceAsync.WithRawResponse =
            MemoryStoreServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun memories(): MemoryServiceAsync.WithRawResponse = memories

        override fun memoryVersions(): MemoryVersionServiceAsync.WithRawResponse = memoryVersions

        private val createHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun create(
            params: MemoryStoreCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores")
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

        private val retrieveHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun retrieve(
            params: MemoryStoreRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0))
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

        private val updateHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun update(
            params: MemoryStoreUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0))
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

        private val listHandler: Handler<MemoryStoreListPageResponse> =
            jsonHandler<MemoryStoreListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: MemoryStoreListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemoryStoreListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores")
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
                                MemoryStoreListPageAsync.builder()
                                    .service(MemoryStoreServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeletedMemoryStore> =
            jsonHandler<BetaManagedAgentsDeletedMemoryStore>(clientOptions.jsonMapper)

        override fun delete(
            params: MemoryStoreDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedMemoryStore>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0))
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

        private val archiveHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun archive(
            params: MemoryStoreArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryStore>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0), "archive")
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
