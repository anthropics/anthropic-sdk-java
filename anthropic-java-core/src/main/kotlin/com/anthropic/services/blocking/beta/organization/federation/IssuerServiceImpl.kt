// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.federation

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.federation.issuers.BetaFederationIssuer
import com.anthropic.models.beta.organization.federation.issuers.IssuerArchiveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerListPage
import com.anthropic.models.beta.organization.federation.issuers.IssuerListPageResponse
import com.anthropic.models.beta.organization.federation.issuers.IssuerListParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerRetrieveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class IssuerServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    IssuerService {

    private val withRawResponse: IssuerService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): IssuerService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): IssuerService =
        IssuerServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: IssuerCreateParams,
        requestOptions: RequestOptions,
    ): BetaFederationIssuer =
        // post /v1/organizations/federation_issuers?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: IssuerRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaFederationIssuer =
        // get /v1/organizations/federation_issuers/{federation_issuer_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: IssuerUpdateParams,
        requestOptions: RequestOptions,
    ): BetaFederationIssuer =
        // post /v1/organizations/federation_issuers/{federation_issuer_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: IssuerListParams, requestOptions: RequestOptions): IssuerListPage =
        // get /v1/organizations/federation_issuers?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: IssuerArchiveParams,
        requestOptions: RequestOptions,
    ): BetaFederationIssuer =
        // post /v1/organizations/federation_issuers/{federation_issuer_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        IssuerService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): IssuerService.WithRawResponse =
            IssuerServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaFederationIssuer> =
            jsonHandler<BetaFederationIssuer>(clientOptions.jsonMapper)

        override fun create(
            params: IssuerCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationIssuer> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_issuers")
                    .putQueryParam("beta", "true")
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

        private val retrieveHandler: Handler<BetaFederationIssuer> =
            jsonHandler<BetaFederationIssuer>(clientOptions.jsonMapper)

        override fun retrieve(
            params: IssuerRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationIssuer> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationIssuerId", params.federationIssuerId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "federation_issuers",
                        params._pathParam(0),
                    )
                    .putQueryParam("beta", "true")
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

        private val updateHandler: Handler<BetaFederationIssuer> =
            jsonHandler<BetaFederationIssuer>(clientOptions.jsonMapper)

        override fun update(
            params: IssuerUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationIssuer> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationIssuerId", params.federationIssuerId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "federation_issuers",
                        params._pathParam(0),
                    )
                    .putQueryParam("beta", "true")
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

        private val listHandler: Handler<IssuerListPageResponse> =
            jsonHandler<IssuerListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: IssuerListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<IssuerListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_issuers")
                    .putQueryParam("beta", "true")
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
                        IssuerListPage.builder()
                            .service(IssuerServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaFederationIssuer> =
            jsonHandler<BetaFederationIssuer>(clientOptions.jsonMapper)

        override fun archive(
            params: IssuerArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationIssuer> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationIssuerId", params.federationIssuerId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "federation_issuers",
                        params._pathParam(0),
                        "archive",
                    )
                    .putQueryParam("beta", "true")
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
