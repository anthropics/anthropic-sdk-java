// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.serviceaccounts

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
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceListPageAsync
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceListPageResponse
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkspaceServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkspaceServiceAsync {

    private val withRawResponse: WorkspaceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WorkspaceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceServiceAsync =
        WorkspaceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceListPageAsync> =
        // get /v1/organizations/service_accounts/{service_account_id}/workspaces?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaServiceAccountWorkspaceMember> =
        // post /v1/organizations/service_accounts/{service_account_id}/workspaces?beta=true
        withRawResponse().add(params, requestOptions).thenApply { it.parse() }

    override fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceRemoveResponse> =
        // delete
        // /v1/organizations/service_accounts/{service_account_id}/workspaces/{workspace_id}?beta=true
        withRawResponse().remove(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkspaceServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceServiceAsync.WithRawResponse =
            WorkspaceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<WorkspaceListPageResponse> =
            jsonHandler<WorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> {
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
                        "workspaces",
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
                                WorkspaceListPageAsync.builder()
                                    .service(WorkspaceServiceAsyncImpl(clientOptions))
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
            params: WorkspaceAddParams,
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
                        "service_accounts",
                        params._pathParam(0),
                        "workspaces",
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

        private val removeHandler: Handler<WorkspaceRemoveResponse> =
            jsonHandler<WorkspaceRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceRemoveResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "service_accounts",
                        params._pathParam(0),
                        "workspaces",
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
