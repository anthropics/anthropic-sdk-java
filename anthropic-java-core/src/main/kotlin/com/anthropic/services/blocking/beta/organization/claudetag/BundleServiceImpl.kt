package com.anthropic.services.blocking.beta.organization.claudetag

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
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.claudetag.bundles.BetaClaudeTagBundle
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPage
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPageResponse
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import com.anthropic.services.blocking.beta.organization.claudetag.bundles.TargetService
import com.anthropic.services.blocking.beta.organization.claudetag.bundles.TargetServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class BundleServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    BundleService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "experimental-claude-tag-2026-10-21").build()
    }

    private val withRawResponse: BundleService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val targets: TargetService by lazy { TargetServiceImpl(clientOptions) }

    override fun withRawResponse(): BundleService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BundleService =
        BundleServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun targets(): TargetService = targets

    override fun retrieve(
        params: BundleRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaClaudeTagBundle =
        // get /v1/organizations/claude_tag/bundles/{bundle_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: BundleListParams, requestOptions: RequestOptions): BundleListPage =
        // get /v1/organizations/claude_tag/bundles
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BundleService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val targets: TargetService.WithRawResponse by lazy {
            TargetServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BundleService.WithRawResponse =
            BundleServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun targets(): TargetService.WithRawResponse = targets

        private val retrieveHandler: Handler<BetaClaudeTagBundle> =
            jsonHandler<BetaClaudeTagBundle>(clientOptions.jsonMapper)

        override fun retrieve(
            params: BundleRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagBundle> {
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

        private val listHandler: Handler<BundleListPageResponse> =
            jsonHandler<BundleListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: BundleListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BundleListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "bundles")
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
                            BundleListPage.builder()
                                .service(BundleServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }
    }
}
