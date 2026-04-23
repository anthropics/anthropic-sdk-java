// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.memorystores

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
import com.anthropic.models.beta.memorystores.memoryversions.BetaManagedAgentsMemoryVersion
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListPageAsync
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListPageResponse
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionListParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRedactParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class MemoryVersionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MemoryVersionServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: MemoryVersionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): MemoryVersionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemoryVersionServiceAsync =
        MemoryVersionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: MemoryVersionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> =
        // get /v1/memory_stores/{memory_store_id}/memory_versions/{memory_version_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: MemoryVersionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemoryVersionListPageAsync> =
        // get /v1/memory_stores/{memory_store_id}/memory_versions?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun redact(
        params: MemoryVersionRedactParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsMemoryVersion> =
        // post
        // /v1/memory_stores/{memory_store_id}/memory_versions/{memory_version_id}/redact?beta=true
        withRawResponse().redact(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MemoryVersionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemoryVersionServiceAsync.WithRawResponse =
            MemoryVersionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaManagedAgentsMemoryVersion> =
            jsonHandler<BetaManagedAgentsMemoryVersion>(clientOptions.jsonMapper)

        override fun retrieve(
            params: MemoryVersionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryVersionId", params.memoryVersionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "memory_stores",
                        params._pathParam(0),
                        "memory_versions",
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

        private val listHandler: Handler<MemoryVersionListPageResponse> =
            jsonHandler<MemoryVersionListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: MemoryVersionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemoryVersionListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryStoreId", params.memoryStoreId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "memory_stores", params._pathParam(0), "memory_versions")
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
                                MemoryVersionListPageAsync.builder()
                                    .service(MemoryVersionServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val redactHandler: Handler<BetaManagedAgentsMemoryVersion> =
            jsonHandler<BetaManagedAgentsMemoryVersion>(clientOptions.jsonMapper)

        override fun redact(
            params: MemoryVersionRedactParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsMemoryVersion>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("memoryVersionId", params.memoryVersionId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "memory_stores",
                        params._pathParam(0),
                        "memory_versions",
                        params._pathParam(1),
                        "redact",
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
                            .use { redactHandler.handle(it) }
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
