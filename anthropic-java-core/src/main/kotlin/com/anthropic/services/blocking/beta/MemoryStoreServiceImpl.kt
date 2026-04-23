// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.memorystores.BetaManagedAgentsDeletedMemoryStore
import com.anthropic.models.beta.memorystores.BetaManagedAgentsMemoryStore
import com.anthropic.models.beta.memorystores.MemoryStoreArchiveParams
import com.anthropic.models.beta.memorystores.MemoryStoreCreateParams
import com.anthropic.models.beta.memorystores.MemoryStoreDeleteParams
import com.anthropic.models.beta.memorystores.MemoryStoreListPage
import com.anthropic.models.beta.memorystores.MemoryStoreListPageResponse
import com.anthropic.models.beta.memorystores.MemoryStoreListParams
import com.anthropic.models.beta.memorystores.MemoryStoreRetrieveParams
import com.anthropic.models.beta.memorystores.MemoryStoreUpdateParams
import com.anthropic.services.blocking.beta.memorystores.MemoryService
import com.anthropic.services.blocking.beta.memorystores.MemoryServiceImpl
import com.anthropic.services.blocking.beta.memorystores.MemoryVersionService
import com.anthropic.services.blocking.beta.memorystores.MemoryVersionServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class MemoryStoreServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    MemoryStoreService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: MemoryStoreService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val memories: MemoryService by lazy { MemoryServiceImpl(clientOptions) }

    private val memoryVersions: MemoryVersionService by lazy {
        MemoryVersionServiceImpl(clientOptions)
    }

    override fun withRawResponse(): MemoryStoreService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryStoreService =
        MemoryStoreServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun memories(): MemoryService = memories

    override fun memoryVersions(): MemoryVersionService = memoryVersions

    override fun create(
        params: MemoryStoreCreateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        // post /v1/memory_stores?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: MemoryStoreRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        // get /v1/memory_stores/{memory_store_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: MemoryStoreUpdateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        // post /v1/memory_stores/{memory_store_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: MemoryStoreListParams,
        requestOptions: RequestOptions,
    ): MemoryStoreListPage =
        // get /v1/memory_stores?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: MemoryStoreDeleteParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeletedMemoryStore =
        // delete /v1/memory_stores/{memory_store_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun archive(
        params: MemoryStoreArchiveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemoryStore =
        // post /v1/memory_stores/{memory_store_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MemoryStoreService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val memories: MemoryService.WithRawResponse by lazy {
            MemoryServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val memoryVersions: MemoryVersionService.WithRawResponse by lazy {
            MemoryVersionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryStoreService.WithRawResponse =
            MemoryStoreServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun memories(): MemoryService.WithRawResponse = memories

        override fun memoryVersions(): MemoryVersionService.WithRawResponse = memoryVersions

        private val createHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun create(
            params: MemoryStoreCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun retrieve(
            params: MemoryStoreRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> {
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

        private val updateHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun update(
            params: MemoryStoreUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> {
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<MemoryStoreListPageResponse> =
            jsonHandler<MemoryStoreListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: MemoryStoreListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemoryStoreListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                        MemoryStoreListPage.builder()
                            .service(MemoryStoreServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeletedMemoryStore> =
            jsonHandler<BetaManagedAgentsDeletedMemoryStore>(clientOptions.jsonMapper)

        override fun delete(
            params: MemoryStoreDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedMemoryStore> {
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val archiveHandler: Handler<BetaManagedAgentsMemoryStore> =
            jsonHandler<BetaManagedAgentsMemoryStore>(clientOptions.jsonMapper)

        override fun archive(
            params: MemoryStoreArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemoryStore> {
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
