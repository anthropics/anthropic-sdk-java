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
import com.anthropic.models.beta.organization.workspaces.BetaWorkspace
import com.anthropic.models.beta.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceListPageAsync
import com.anthropic.models.beta.organization.workspaces.WorkspaceListPageResponse
import com.anthropic.models.beta.organization.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceUpdateParams
import com.anthropic.services.async.beta.organization.workspaces.MemberServiceAsync
import com.anthropic.services.async.beta.organization.workspaces.MemberServiceAsyncImpl
import com.anthropic.services.async.beta.organization.workspaces.RateLimitServiceAsync
import com.anthropic.services.async.beta.organization.workspaces.RateLimitServiceAsyncImpl
import com.anthropic.services.async.beta.organization.workspaces.ServiceAccountServiceAsync
import com.anthropic.services.async.beta.organization.workspaces.ServiceAccountServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkspaceServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkspaceServiceAsync {

    private val withRawResponse: WorkspaceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val rateLimits: RateLimitServiceAsync by lazy {
        RateLimitServiceAsyncImpl(clientOptions)
    }

    private val members: MemberServiceAsync by lazy { MemberServiceAsyncImpl(clientOptions) }

    private val serviceAccounts: ServiceAccountServiceAsync by lazy {
        ServiceAccountServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): WorkspaceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceServiceAsync =
        WorkspaceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun rateLimits(): RateLimitServiceAsync = rateLimits

    override fun members(): MemberServiceAsync = members

    override fun serviceAccounts(): ServiceAccountServiceAsync = serviceAccounts

    override fun create(
        params: WorkspaceCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
        // post /v1/organizations/workspaces?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: WorkspaceRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
        // get /v1/organizations/workspaces/{workspace_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: WorkspaceUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
        // post /v1/organizations/workspaces/{workspace_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceListPageAsync> =
        // get /v1/organizations/workspaces?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun archive(
        params: WorkspaceArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspace> =
        // post /v1/organizations/workspaces/{workspace_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkspaceServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val rateLimits: RateLimitServiceAsync.WithRawResponse by lazy {
            RateLimitServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val members: MemberServiceAsync.WithRawResponse by lazy {
            MemberServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val serviceAccounts: ServiceAccountServiceAsync.WithRawResponse by lazy {
            ServiceAccountServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceServiceAsync.WithRawResponse =
            WorkspaceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun rateLimits(): RateLimitServiceAsync.WithRawResponse = rateLimits

        override fun members(): MemberServiceAsync.WithRawResponse = members

        override fun serviceAccounts(): ServiceAccountServiceAsync.WithRawResponse = serviceAccounts

        private val createHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun create(
            params: WorkspaceCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
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

        private val retrieveHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun retrieve(
            params: WorkspaceRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces", params._pathParam(0))
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

        private val updateHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun update(
            params: WorkspaceUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces", params._pathParam(0))
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

        private val listHandler: Handler<WorkspaceListPageResponse> =
            jsonHandler<WorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
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

        private val archiveHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun archive(
            params: WorkspaceArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspace>> {
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
