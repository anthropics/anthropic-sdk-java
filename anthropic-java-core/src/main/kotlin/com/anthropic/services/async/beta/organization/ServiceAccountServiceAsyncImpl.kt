// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

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
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccount
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountArchiveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListPageAsync
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListPageResponse
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountUpdateParams
import com.anthropic.services.async.beta.organization.serviceaccounts.WorkspaceServiceAsync
import com.anthropic.services.async.beta.organization.serviceaccounts.WorkspaceServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ServiceAccountServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : ServiceAccountServiceAsync {

    private val withRawResponse: ServiceAccountServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val workspaces: WorkspaceServiceAsync by lazy {
        WorkspaceServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): ServiceAccountServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): ServiceAccountServiceAsync =
        ServiceAccountServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun workspaces(): WorkspaceServiceAsync = workspaces

    override fun create(
        params: ServiceAccountCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        // post /v1/organizations/service_accounts?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        // get /v1/organizations/service_accounts/{service_account_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        // post /v1/organizations/service_accounts/{service_account_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ServiceAccountListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountListPageAsync> =
        // get /v1/organizations/service_accounts?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun archive(
        params: ServiceAccountArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccount> =
        // post /v1/organizations/service_accounts/{service_account_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ServiceAccountServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val workspaces: WorkspaceServiceAsync.WithRawResponse by lazy {
            WorkspaceServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ServiceAccountServiceAsync.WithRawResponse =
            ServiceAccountServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun workspaces(): WorkspaceServiceAsync.WithRawResponse = workspaces

        private val createHandler: Handler<BetaServiceAccount> =
            jsonHandler<BetaServiceAccount>(clientOptions.jsonMapper)

        override fun create(
            params: ServiceAccountCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "service_accounts")
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
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<BetaServiceAccount> =
            jsonHandler<BetaServiceAccount>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> {
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
                        "service_accounts",
                        params._pathParam(0),
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

        private val updateHandler: Handler<BetaServiceAccount> =
            jsonHandler<BetaServiceAccount>(clientOptions.jsonMapper)

        override fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> {
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
                        "service_accounts",
                        params._pathParam(0),
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
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "service_accounts")
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

        private val archiveHandler: Handler<BetaServiceAccount> =
            jsonHandler<BetaServiceAccount>(clientOptions.jsonMapper)

        override fun archive(
            params: ServiceAccountArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaServiceAccount>> {
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
                        "service_accounts",
                        params._pathParam(0),
                        "archive",
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
}
