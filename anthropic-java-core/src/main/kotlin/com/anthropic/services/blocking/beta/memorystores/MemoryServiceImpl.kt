// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.memorystores

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
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsDeletedMemory
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemory
import com.anthropic.models.beta.memorystores.memories.MemoryCreateParams
import com.anthropic.models.beta.memorystores.memories.MemoryDeleteParams
import com.anthropic.models.beta.memorystores.memories.MemoryListPage
import com.anthropic.models.beta.memorystores.memories.MemoryListPageResponse
import com.anthropic.models.beta.memorystores.memories.MemoryListParams
import com.anthropic.models.beta.memorystores.memories.MemoryRetrieveParams
import com.anthropic.models.beta.memorystores.memories.MemoryUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class MemoryServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    MemoryService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: MemoryService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): MemoryService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryService =
        MemoryServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: MemoryCreateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemory =
        // post /v1/memory_stores/{memory_store_id}/memories?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: MemoryRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemory =
        // get /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: MemoryUpdateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsMemory =
        // post /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: MemoryListParams, requestOptions: RequestOptions): MemoryListPage =
        // get /v1/memory_stores/{memory_store_id}/memories?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: MemoryDeleteParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeletedMemory =
        // delete /v1/memory_stores/{memory_store_id}/memories/{memory_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MemoryService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryService.WithRawResponse =
            MemoryServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaManagedAgentsMemory> =
            jsonHandler<BetaManagedAgentsMemory>(clientOptions.jsonMapper)

        override fun create(
            params: MemoryCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemory> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0), "memories")
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

        private val retrieveHandler: Handler<BetaManagedAgentsMemory> =
            jsonHandler<BetaManagedAgentsMemory>(clientOptions.jsonMapper)

        override fun retrieve(
            params: MemoryRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemory> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryId", params.memoryId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "memory_stores",
                        params._pathParam(0),
                        "memories",
                        params._pathParam(1),
                    )
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

        private val updateHandler: Handler<BetaManagedAgentsMemory> =
            jsonHandler<BetaManagedAgentsMemory>(clientOptions.jsonMapper)

        override fun update(
            params: MemoryUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsMemory> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryId", params.memoryId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "memory_stores",
                        params._pathParam(0),
                        "memories",
                        params._pathParam(1),
                    )
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

        private val listHandler: Handler<MemoryListPageResponse> =
            jsonHandler<MemoryListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: MemoryListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemoryListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0), "memories")
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
                        MemoryListPage.builder()
                            .service(MemoryServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeletedMemory> =
            jsonHandler<BetaManagedAgentsDeletedMemory>(clientOptions.jsonMapper)

        override fun delete(
            params: MemoryDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedMemory> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryId", params.memoryId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "memory_stores",
                        params._pathParam(0),
                        "memories",
                        params._pathParam(1),
                    )
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
    }
}
