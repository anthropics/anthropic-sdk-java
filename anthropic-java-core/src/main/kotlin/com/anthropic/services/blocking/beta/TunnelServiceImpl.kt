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
import com.anthropic.models.beta.tunnels.BetaTunnel
import com.anthropic.models.beta.tunnels.BetaTunnelToken
import com.anthropic.models.beta.tunnels.TunnelArchiveParams
import com.anthropic.models.beta.tunnels.TunnelCreateParams
import com.anthropic.models.beta.tunnels.TunnelListPage
import com.anthropic.models.beta.tunnels.TunnelListPageResponse
import com.anthropic.models.beta.tunnels.TunnelListParams
import com.anthropic.models.beta.tunnels.TunnelRetrieveParams
import com.anthropic.models.beta.tunnels.TunnelRevealTokenParams
import com.anthropic.models.beta.tunnels.TunnelRotateTokenParams
import com.anthropic.services.blocking.beta.tunnels.CertificateService
import com.anthropic.services.blocking.beta.tunnels.CertificateServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class TunnelServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    TunnelService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "mcp-tunnels-2026-06-22").build()
    }

    private val withRawResponse: TunnelService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val certificates: CertificateService by lazy { CertificateServiceImpl(clientOptions) }

    override fun withRawResponse(): TunnelService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): TunnelService =
        TunnelServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun certificates(): CertificateService = certificates

    override fun create(params: TunnelCreateParams, requestOptions: RequestOptions): BetaTunnel =
        // post /v1/tunnels?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: TunnelRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaTunnel =
        // get /v1/tunnels/{tunnel_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: TunnelListParams, requestOptions: RequestOptions): TunnelListPage =
        // get /v1/tunnels?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(params: TunnelArchiveParams, requestOptions: RequestOptions): BetaTunnel =
        // post /v1/tunnels/{tunnel_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    override fun revealToken(
        params: TunnelRevealTokenParams,
        requestOptions: RequestOptions,
    ): BetaTunnelToken =
        // post /v1/tunnels/{tunnel_id}/reveal_token?beta=true
        withRawResponse().revealToken(params, requestOptions).parse()

    override fun rotateToken(
        params: TunnelRotateTokenParams,
        requestOptions: RequestOptions,
    ): BetaTunnelToken =
        // post /v1/tunnels/{tunnel_id}/rotate_token?beta=true
        withRawResponse().rotateToken(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        TunnelService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val certificates: CertificateService.WithRawResponse by lazy {
            CertificateServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TunnelService.WithRawResponse =
            TunnelServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun certificates(): CertificateService.WithRawResponse = certificates

        private val createHandler: Handler<BetaTunnel> =
            jsonHandler<BetaTunnel>(clientOptions.jsonMapper)

        override fun create(
            params: TunnelCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnel> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels")
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

        private val retrieveHandler: Handler<BetaTunnel> =
            jsonHandler<BetaTunnel>(clientOptions.jsonMapper)

        override fun retrieve(
            params: TunnelRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnel> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels", params._pathParam(0))
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

        private val listHandler: Handler<TunnelListPageResponse> =
            jsonHandler<TunnelListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: TunnelListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TunnelListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels")
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
                        TunnelListPage.builder()
                            .service(TunnelServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaTunnel> =
            jsonHandler<BetaTunnel>(clientOptions.jsonMapper)

        override fun archive(
            params: TunnelArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnel> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels", params._pathParam(0), "archive")
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

        private val revealTokenHandler: Handler<BetaTunnelToken> =
            jsonHandler<BetaTunnelToken>(clientOptions.jsonMapper)

        override fun revealToken(
            params: TunnelRevealTokenParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelToken> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels", params._pathParam(0), "reveal_token")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { revealTokenHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val rotateTokenHandler: Handler<BetaTunnelToken> =
            jsonHandler<BetaTunnelToken>(clientOptions.jsonMapper)

        override fun rotateToken(
            params: TunnelRotateTokenParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelToken> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels", params._pathParam(0), "rotate_token")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { rotateTokenHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
