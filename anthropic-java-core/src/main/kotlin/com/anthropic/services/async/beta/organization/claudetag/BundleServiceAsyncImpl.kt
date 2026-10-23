package com.anthropic.services.async.beta.organization.claudetag

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
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.claudetag.bundles.BetaClaudeTagBundle
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPageAsync
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPageResponse
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import com.anthropic.services.async.beta.organization.claudetag.bundles.TargetServiceAsync
import com.anthropic.services.async.beta.organization.claudetag.bundles.TargetServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class BundleServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BundleServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "experimental-claude-tag-2026-10-21").build()
    }

    private val withRawResponse: BundleServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val targets: TargetServiceAsync by lazy { TargetServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): BundleServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BundleServiceAsync =
        BundleServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun targets(): TargetServiceAsync = targets

    override fun retrieve(
        params: BundleRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagBundle> =
        // get /v1/organizations/claude_tag/bundles/{bundle_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun list(
        params: BundleListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BundleListPageAsync> =
        // get /v1/organizations/claude_tag/bundles
        withRawResponse().list(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BundleServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val targets: TargetServiceAsync.WithRawResponse by lazy {
            TargetServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BundleServiceAsync.WithRawResponse =
            BundleServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun targets(): TargetServiceAsync.WithRawResponse = targets

        private val retrieveHandler: Handler<BetaClaudeTagBundle> =
            jsonHandler<BetaClaudeTagBundle>(clientOptions.jsonMapper)

        override fun retrieve(
            params: BundleRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("bundleId", params.bundleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
                    .addPathParam("bundleId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { retrieveHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }

        private val listHandler: Handler<BundleListPageResponse> =
            jsonHandler<BundleListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: BundleListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BundleListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { listHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .let {
                                    BundleListPageAsync.builder()
                                        .service(BundleServiceAsyncImpl(clientOptions))
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
}
