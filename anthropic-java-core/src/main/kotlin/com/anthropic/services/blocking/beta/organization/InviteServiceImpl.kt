// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.models.beta.organization.invites.BetaOrganizationInvite
import com.anthropic.models.beta.organization.invites.InviteCreateParams
import com.anthropic.models.beta.organization.invites.InviteDeleteParams
import com.anthropic.models.beta.organization.invites.InviteDeleteResponse
import com.anthropic.models.beta.organization.invites.InviteListPage
import com.anthropic.models.beta.organization.invites.InviteListPageResponse
import com.anthropic.models.beta.organization.invites.InviteListParams
import com.anthropic.models.beta.organization.invites.InviteRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class InviteServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    InviteService {

    private val withRawResponse: InviteService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): InviteService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): InviteService =
        InviteServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: InviteCreateParams,
        requestOptions: RequestOptions,
    ): BetaOrganizationInvite =
        // post /v1/organizations/invites?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: InviteRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaOrganizationInvite =
        // get /v1/organizations/invites/{invite_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: InviteListParams, requestOptions: RequestOptions): InviteListPage =
        // get /v1/organizations/invites?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: InviteDeleteParams,
        requestOptions: RequestOptions,
    ): InviteDeleteResponse =
        // delete /v1/organizations/invites/{invite_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        InviteService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InviteService.WithRawResponse =
            InviteServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaOrganizationInvite> =
            jsonHandler<BetaOrganizationInvite>(clientOptions.jsonMapper)

        override fun create(
            params: InviteCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationInvite> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites")
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

        private val retrieveHandler: Handler<BetaOrganizationInvite> =
            jsonHandler<BetaOrganizationInvite>(clientOptions.jsonMapper)

        override fun retrieve(
            params: InviteRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationInvite> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("inviteId", params.inviteId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites", params._pathParam(0))
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

        private val listHandler: Handler<InviteListPageResponse> =
            jsonHandler<InviteListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: InviteListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<InviteListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites")
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
                        InviteListPage.builder()
                            .service(InviteServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<InviteDeleteResponse> =
            jsonHandler<InviteDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: InviteDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<InviteDeleteResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("inviteId", params.inviteId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
