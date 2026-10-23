package com.anthropic.services.async.beta.tunnels

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
import com.anthropic.models.beta.tunnels.certificates.BetaTunnelCertificate
import com.anthropic.models.beta.tunnels.certificates.CertificateArchiveParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateListPageAsync
import com.anthropic.models.beta.tunnels.certificates.CertificateListPageResponse
import com.anthropic.models.beta.tunnels.certificates.CertificateListParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CertificateServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CertificateServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "mcp-tunnels-2026-06-22").build()
    }

    private val withRawResponse: CertificateServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CertificateServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CertificateServiceAsync =
        CertificateServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: CertificateCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTunnelCertificate> =
        // post /v1/tunnels/{tunnel_id}/certificates
        withRawResponse().create(params, requestOptions).thenParse()

    override fun retrieve(
        params: CertificateRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTunnelCertificate> =
        // get /v1/tunnels/{tunnel_id}/certificates/{certificate_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun list(
        params: CertificateListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CertificateListPageAsync> =
        // get /v1/tunnels/{tunnel_id}/certificates
        withRawResponse().list(params, requestOptions).thenParse()

    override fun archive(
        params: CertificateArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTunnelCertificate> =
        // post /v1/tunnels/{tunnel_id}/certificates/{certificate_id}/archive
        withRawResponse().archive(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CertificateServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CertificateServiceAsync.WithRawResponse =
            CertificateServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaTunnelCertificate> =
            jsonHandler<BetaTunnelCertificate>(clientOptions.jsonMapper)

        override fun create(
            params: CertificateCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels")
                    .addPathParam("tunnelId", params._pathParam(0))
                    .addPathSegments("certificates")
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
                                .also {
                                    client.recordCreatedId(
                                        "anthropic.tunnel.certificate.id",
                                        it._id(),
                                    )
                                }
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaTunnelCertificate> =
            jsonHandler<BetaTunnelCertificate>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CertificateRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("certificateId", params.certificateId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels")
                    .addPathParam("tunnelId", params._pathParam(0))
                    .addPathSegments("certificates")
                    .addPathParam("certificateId", params._pathParam(1))
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

        private val listHandler: Handler<CertificateListPageResponse> =
            jsonHandler<CertificateListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CertificateListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CertificateListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("tunnelId", params.tunnelId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels")
                    .addPathParam("tunnelId", params._pathParam(0))
                    .addPathSegments("certificates")
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
                                    CertificateListPageAsync.builder()
                                        .service(CertificateServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val archiveHandler: Handler<BetaTunnelCertificate> =
            jsonHandler<BetaTunnelCertificate>(clientOptions.jsonMapper)

        override fun archive(
            params: CertificateArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("certificateId", params.certificateId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "tunnels")
                    .addPathParam("tunnelId", params._pathParam(0))
                    .addPathSegments("certificates")
                    .addPathParam("certificateId", params._pathParam(1))
                    .addPathSegments("archive")
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
                                .use { archiveHandler.handle(it) }
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
