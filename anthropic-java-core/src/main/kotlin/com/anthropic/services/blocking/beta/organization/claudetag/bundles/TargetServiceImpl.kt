package com.anthropic.services.blocking.beta.organization.claudetag.bundles

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
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.claudetag.bundles.targets.BetaClaudeTagBundleTarget
import com.anthropic.models.beta.organization.claudetag.bundles.targets.BetaClaudeTagDeletedBundleTarget
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetCreateParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetDeleteParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListPage
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListPageResponse
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class TargetServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    TargetService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "experimental-claude-tag-2026-10-21").build()
    }

    private val withRawResponse: TargetService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): TargetService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): TargetService =
        TargetServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: TargetCreateParams,
        requestOptions: RequestOptions,
    ): BetaClaudeTagBundleTarget =
        // post /v1/organizations/claude_tag/bundles/{bundle_id}/targets
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: TargetRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaClaudeTagBundleTarget =
        // get /v1/organizations/claude_tag/bundles/{bundle_id}/targets/{target_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: TargetListParams, requestOptions: RequestOptions): TargetListPage =
        // get /v1/organizations/claude_tag/bundles/{bundle_id}/targets
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: TargetDeleteParams,
        requestOptions: RequestOptions,
    ): BetaClaudeTagDeletedBundleTarget =
        // delete /v1/organizations/claude_tag/bundles/{bundle_id}/targets/{target_id}
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        TargetService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TargetService.WithRawResponse =
            TargetServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaClaudeTagBundleTarget> =
            jsonHandler<BetaClaudeTagBundleTarget>(clientOptions.jsonMapper)

        override fun create(
            params: TargetCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagBundleTarget> {
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
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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

        private val retrieveHandler: Handler<BetaClaudeTagBundleTarget> =
            jsonHandler<BetaClaudeTagBundleTarget>(clientOptions.jsonMapper)

        override fun retrieve(
            params: TargetRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagBundleTarget> {
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
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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

        private val listHandler: Handler<TargetListPageResponse> =
            jsonHandler<TargetListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: TargetListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TargetListPage> {
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
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { listHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                        .let {
                            TargetListPage.builder()
                                .service(TargetServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val deleteHandler: Handler<BetaClaudeTagDeletedBundleTarget> =
            jsonHandler<BetaClaudeTagDeletedBundleTarget>(clientOptions.jsonMapper)

        override fun delete(
            params: TargetDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagDeletedBundleTarget> {
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
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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
