package com.anthropic.services.blocking.organization.federation

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
import com.anthropic.models.organization.federation.issuers.FederationIssuer
import com.anthropic.models.organization.federation.issuers.IssuerArchiveParams
import com.anthropic.models.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.organization.federation.issuers.IssuerListPage
import com.anthropic.models.organization.federation.issuers.IssuerListPageResponse
import com.anthropic.models.organization.federation.issuers.IssuerListParams
import com.anthropic.models.organization.federation.issuers.IssuerRetrieveParams
import com.anthropic.models.organization.federation.issuers.IssuerUpdateParams
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
    ): FederationIssuer =
        // post /v1/organizations/federation_issuers
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: IssuerRetrieveParams,
        requestOptions: RequestOptions,
    ): FederationIssuer =
        // get /v1/organizations/federation_issuers/{federation_issuer_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: IssuerUpdateParams,
        requestOptions: RequestOptions,
    ): FederationIssuer =
        // post /v1/organizations/federation_issuers/{federation_issuer_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: IssuerListParams, requestOptions: RequestOptions): IssuerListPage =
        // get /v1/organizations/federation_issuers
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: IssuerArchiveParams,
        requestOptions: RequestOptions,
    ): FederationIssuer =
        // post /v1/organizations/federation_issuers/{federation_issuer_id}/archive
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

        private val createHandler: Handler<FederationIssuer> =
            jsonHandler<FederationIssuer>(clientOptions.jsonMapper)

        override fun create(
            params: IssuerCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_issuers")
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

        private val retrieveHandler: Handler<FederationIssuer> =
            jsonHandler<FederationIssuer>(clientOptions.jsonMapper)

        override fun retrieve(
            params: IssuerRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationIssuerId", params.federationIssuerId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_issuers")
                    .addPathParam("federationIssuerId", params._pathParam(0))
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

        private val updateHandler: Handler<FederationIssuer> =
            jsonHandler<FederationIssuer>(clientOptions.jsonMapper)

        override fun update(
            params: IssuerUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationIssuerId", params.federationIssuerId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_issuers")
                    .addPathParam("federationIssuerId", params._pathParam(0))
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

        private val archiveHandler: Handler<FederationIssuer> =
            jsonHandler<FederationIssuer>(clientOptions.jsonMapper)

        override fun archive(
            params: IssuerArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationIssuerId", params.federationIssuerId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_issuers")
                    .addPathParam("federationIssuerId", params._pathParam(0))
                    .addPathSegments("archive")
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
