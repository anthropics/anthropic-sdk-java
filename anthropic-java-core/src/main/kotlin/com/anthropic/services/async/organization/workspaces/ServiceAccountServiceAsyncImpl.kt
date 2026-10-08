package com.anthropic.services.async.organization.workspaces

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
import com.anthropic.models.organization.serviceaccounts.ServiceAccountWorkspaceMember
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountListPageAsync
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountListPageResponse
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRemoveResponse
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
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
    ): CompletableFuture<ServiceAccountWorkspaceMember> =
        // get /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountWorkspaceMember> =
        // post /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ServiceAccountListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountListPageAsync> =
        // get /v1/organizations/workspaces/{workspace_id}/service_accounts
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun add(
        params: ServiceAccountAddParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountWorkspaceMember> =
        // post /v1/organizations/workspaces/{workspace_id}/service_accounts
        withRawResponse().add(params, requestOptions).thenApply { it.parse() }

    override fun remove(
        params: ServiceAccountRemoveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ServiceAccountRemoveResponse> =
        // delete /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}
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

        private val retrieveHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ServiceAccountWorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("serviceAccountId", params.serviceAccountId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("service_accounts")
                    .addPathParam("serviceAccountId", params._pathParam(1))
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

        private val updateHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ServiceAccountWorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("serviceAccountId", params.serviceAccountId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("service_accounts")
                    .addPathParam("serviceAccountId", params._pathParam(1))
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
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("service_accounts")
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

        private val addHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun add(
            params: ServiceAccountAddParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ServiceAccountWorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("service_accounts")
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
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("service_accounts")
                    .addPathParam("serviceAccountId", params._pathParam(1))
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
