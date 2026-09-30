package com.anthropic.services.async.organization

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
import com.anthropic.core.prepareAsync
import com.anthropic.models.organization.invites.InviteCreateParams
import com.anthropic.models.organization.invites.InviteDeleteParams
import com.anthropic.models.organization.invites.InviteDeleteResponse
import com.anthropic.models.organization.invites.InviteListPageAsync
import com.anthropic.models.organization.invites.InviteListPageResponse
import com.anthropic.models.organization.invites.InviteListParams
import com.anthropic.models.organization.invites.InviteRetrieveParams
import com.anthropic.models.organization.invites.OrganizationInvite
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class InviteServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    InviteServiceAsync {

    private val withRawResponse: InviteServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): InviteServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): InviteServiceAsync =
        InviteServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: InviteCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<OrganizationInvite> =
        // post /v1/organizations/invites
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: InviteRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<OrganizationInvite> =
        // get /v1/organizations/invites/{invite_id}
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: InviteListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<InviteListPageAsync> =
        // get /v1/organizations/invites
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: InviteDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<InviteDeleteResponse> =
        // delete /v1/organizations/invites/{invite_id}
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        InviteServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InviteServiceAsync.WithRawResponse =
            InviteServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<OrganizationInvite> =
            jsonHandler<OrganizationInvite>(clientOptions.jsonMapper)

        override fun create(
            params: InviteCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<OrganizationInvite> =
            jsonHandler<OrganizationInvite>(clientOptions.jsonMapper)

        override fun retrieve(
            params: InviteRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<OrganizationInvite>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("inviteId", params.inviteId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<InviteListPageResponse> =
            jsonHandler<InviteListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: InviteListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<InviteListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                InviteListPageAsync.builder()
                                    .service(InviteServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<InviteDeleteResponse> =
            jsonHandler<InviteDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: InviteDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<InviteDeleteResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("inviteId", params.inviteId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "invites", params._pathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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
}
