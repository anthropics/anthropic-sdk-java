package com.anthropic.services.async.beta.organization.claudetag.bundles

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.claudetag.bundles.targets.BetaClaudeTagBundleTarget
import com.anthropic.models.beta.organization.claudetag.bundles.targets.BetaClaudeTagDeletedBundleTarget
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetCreateParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetDeleteParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListPageAsync
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListPageResponse
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class TargetServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    TargetServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "experimental-claude-tag-2026-10-21").build()
    }

    private val withRawResponse: TargetServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): TargetServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): TargetServiceAsync =
        TargetServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: TargetCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagBundleTarget> =
        // post /v1/organizations/claude_tag/bundles/{bundle_id}/targets
        withRawResponse().create(params, requestOptions).thenParse()

    override fun retrieve(
        params: TargetRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagBundleTarget> =
        // get /v1/organizations/claude_tag/bundles/{bundle_id}/targets/{target_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun list(
        params: TargetListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<TargetListPageAsync> =
        // get /v1/organizations/claude_tag/bundles/{bundle_id}/targets
        withRawResponse().list(params, requestOptions).thenParse()

    override fun delete(
        params: TargetDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagDeletedBundleTarget> =
        // delete /v1/organizations/claude_tag/bundles/{bundle_id}/targets/{target_id}
        withRawResponse().delete(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        TargetServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TargetServiceAsync.WithRawResponse =
            TargetServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaClaudeTagBundleTarget> =
            jsonHandler<BetaClaudeTagBundleTarget>(clientOptions.jsonMapper)

        override fun create(
            params: TargetCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundleTarget>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("bundleId", params.bundleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
                    .addPathParam("bundleId", params._pathParam(0))
                    .addPathSegments("targets")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
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
                                .use { createHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaClaudeTagBundleTarget> =
            jsonHandler<BetaClaudeTagBundleTarget>(clientOptions.jsonMapper)

        override fun retrieve(
            params: TargetRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundleTarget>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("targetId", params.targetId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
                    .addPathParam("bundleId", params._pathParam(0))
                    .addPathSegments("targets")
                    .addPathParam("targetId", params._pathParam(1))
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

        private val listHandler: Handler<TargetListPageResponse> =
            jsonHandler<TargetListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: TargetListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<TargetListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("bundleId", params.bundleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
                    .addPathParam("bundleId", params._pathParam(0))
                    .addPathSegments("targets")
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
                                    TargetListPageAsync.builder()
                                        .service(TargetServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val deleteHandler: Handler<BetaClaudeTagDeletedBundleTarget> =
            jsonHandler<BetaClaudeTagDeletedBundleTarget>(clientOptions.jsonMapper)

        override fun delete(
            params: TargetDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedBundleTarget>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("targetId", params.targetId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
                    .addPathParam("bundleId", params._pathParam(0))
                    .addPathSegments("targets")
                    .addPathParam("targetId", params._pathParam(1))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
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
                                .use { deleteHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }
    }
}
