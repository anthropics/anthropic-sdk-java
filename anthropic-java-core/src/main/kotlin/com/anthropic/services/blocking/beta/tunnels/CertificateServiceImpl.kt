// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.tunnels

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
import com.anthropic.models.beta.tunnels.certificates.BetaTunnelCertificate
import com.anthropic.models.beta.tunnels.certificates.CertificateArchiveParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateListPage
import com.anthropic.models.beta.tunnels.certificates.CertificateListPageResponse
import com.anthropic.models.beta.tunnels.certificates.CertificateListParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CertificateServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    CertificateService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "mcp-tunnels-2026-06-22").build()
    }

    private val withRawResponse: CertificateService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CertificateService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CertificateService =
        CertificateServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: CertificateCreateParams,
        requestOptions: RequestOptions,
    ): BetaTunnelCertificate =
        // post /v1/tunnels/{tunnel_id}/certificates?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: CertificateRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaTunnelCertificate =
        // get /v1/tunnels/{tunnel_id}/certificates/{certificate_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: CertificateListParams,
        requestOptions: RequestOptions,
    ): CertificateListPage =
        // get /v1/tunnels/{tunnel_id}/certificates?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: CertificateArchiveParams,
        requestOptions: RequestOptions,
    ): BetaTunnelCertificate =
        // post /v1/tunnels/{tunnel_id}/certificates/{certificate_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CertificateService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CertificateService.WithRawResponse =
            CertificateServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaTunnelCertificate> =
            jsonHandler<BetaTunnelCertificate>(clientOptions.jsonMapper)

        override fun create(
            params: CertificateCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelCertificate> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels", params._pathParam(0), "certificates")
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

        private val retrieveHandler: Handler<BetaTunnelCertificate> =
            jsonHandler<BetaTunnelCertificate>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CertificateRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelCertificate> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("certificateId", params.certificateId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "tunnels",
                        params._pathParam(0),
                        "certificates",
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

        private val listHandler: Handler<CertificateListPageResponse> =
            jsonHandler<CertificateListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CertificateListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CertificateListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels", params._pathParam(0), "certificates")
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
                        CertificateListPage.builder()
                            .service(CertificateServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaTunnelCertificate> =
            jsonHandler<BetaTunnelCertificate>(clientOptions.jsonMapper)

        override fun archive(
            params: CertificateArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelCertificate> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("certificateId", params.certificateId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "tunnels",
                        params._pathParam(0),
                        "certificates",
                        params._pathParam(1),
                        "archive",
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
