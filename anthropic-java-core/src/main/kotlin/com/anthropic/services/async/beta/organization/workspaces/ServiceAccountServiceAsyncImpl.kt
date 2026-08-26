// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.workspaces

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
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccountWorkspaceMember
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountListPageAsync
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountListPageResponse
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRemoveResponse
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ServiceAccountServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : ServiceAccountServiceAsync {

    private val withRawResponse: ServiceAccountServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ServiceAccountServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): ServiceAccountServiceAsync =
        ServiceAccountServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        // get
        // /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        // post
        // /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ServiceAccountListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountListPageAsync> =
        // get /v1/organizations/workspaces/{workspace_id}/service_accounts?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun add(
        params: ServiceAccountAddParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        // post /v1/organizations/workspaces/{workspace_id}/service_accounts?beta=true
        withRawResponse().add(params, requestOptions).thenApply { it.parse() }

    override fun remove(
        params: ServiceAccountRemoveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountRemoveResponse> =
        // delete
        // /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}?beta=true
        withRawResponse().remove(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ServiceAccountServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ServiceAccountServiceAsync.WithRawResponse =
            ServiceAccountServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaServiceAccountWorkspaceMember> =
            jsonHandler<BetaServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("serviceAccountId", params.serviceAccountId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "workspaces",
                        params._pathParam(0),
                        "service_accounts",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
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

        private val updateHandler: Handler<BetaServiceAccountWorkspaceMember> =
            jsonHandler<BetaServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("serviceAccountId", params.serviceAccountId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "workspaces",
                        params._pathParam(0),
                        "service_accounts",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { updateHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<ServiceAccountListPageResponse> =
            jsonHandler<ServiceAccountListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ServiceAccountListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ServiceAccountListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "workspaces",
                        params._pathParam(0),
                        "service_accounts",
                    )
                    .putQueryParam("beta", "true")
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
                                ServiceAccountListPageAsync.builder()
                                    .service(ServiceAccountServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val addHandler: Handler<BetaServiceAccountWorkspaceMember> =
            jsonHandler<BetaServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun add(
            params: ServiceAccountAddParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccountWorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "workspaces",
                        params._pathParam(0),
                        "service_accounts",
                    )
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { addHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val removeHandler: Handler<ServiceAccountRemoveResponse> =
            jsonHandler<ServiceAccountRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: ServiceAccountRemoveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ServiceAccountRemoveResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("serviceAccountId", params.serviceAccountId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "workspaces",
                        params._pathParam(0),
                        "service_accounts",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { removeHandler.handle(it) }
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
