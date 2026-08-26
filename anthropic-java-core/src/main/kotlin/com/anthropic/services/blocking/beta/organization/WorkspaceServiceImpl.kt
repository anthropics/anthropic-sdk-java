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
import com.anthropic.models.beta.organization.workspaces.BetaWorkspace
import com.anthropic.models.beta.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceListPage
import com.anthropic.models.beta.organization.workspaces.WorkspaceListPageResponse
import com.anthropic.models.beta.organization.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceUpdateParams
import com.anthropic.services.blocking.beta.organization.workspaces.MemberService
import com.anthropic.services.blocking.beta.organization.workspaces.MemberServiceImpl
import com.anthropic.services.blocking.beta.organization.workspaces.RateLimitService
import com.anthropic.services.blocking.beta.organization.workspaces.RateLimitServiceImpl
import com.anthropic.services.blocking.beta.organization.workspaces.ServiceAccountService
import com.anthropic.services.blocking.beta.organization.workspaces.ServiceAccountServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkspaceServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkspaceService {

    private val withRawResponse: WorkspaceService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val rateLimits: RateLimitService by lazy { RateLimitServiceImpl(clientOptions) }

    private val members: MemberService by lazy { MemberServiceImpl(clientOptions) }

    private val serviceAccounts: ServiceAccountService by lazy {
        ServiceAccountServiceImpl(clientOptions)
    }

    override fun withRawResponse(): WorkspaceService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService =
        WorkspaceServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun rateLimits(): RateLimitService = rateLimits

    override fun members(): MemberService = members

    override fun serviceAccounts(): ServiceAccountService = serviceAccounts

    override fun create(
        params: WorkspaceCreateParams,
        requestOptions: RequestOptions,
    ): BetaWorkspace =
        // post /v1/organizations/workspaces?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: WorkspaceRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaWorkspace =
        // get /v1/organizations/workspaces/{workspace_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: WorkspaceUpdateParams,
        requestOptions: RequestOptions,
    ): BetaWorkspace =
        // post /v1/organizations/workspaces/{workspace_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions,
    ): WorkspaceListPage =
        // get /v1/organizations/workspaces?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: WorkspaceArchiveParams,
        requestOptions: RequestOptions,
    ): BetaWorkspace =
        // post /v1/organizations/workspaces/{workspace_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkspaceService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val rateLimits: RateLimitService.WithRawResponse by lazy {
            RateLimitServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val members: MemberService.WithRawResponse by lazy {
            MemberServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val serviceAccounts: ServiceAccountService.WithRawResponse by lazy {
            ServiceAccountServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceService.WithRawResponse =
            WorkspaceServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun rateLimits(): RateLimitService.WithRawResponse = rateLimits

        override fun members(): MemberService.WithRawResponse = members

        override fun serviceAccounts(): ServiceAccountService.WithRawResponse = serviceAccounts

        private val createHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun create(
            params: WorkspaceCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
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

        private val retrieveHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun retrieve(
            params: WorkspaceRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> {
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

        private val updateHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun update(
            params: WorkspaceUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> {
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

        private val listHandler: Handler<WorkspaceListPageResponse> =
            jsonHandler<WorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
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
                        WorkspaceListPage.builder()
                            .service(WorkspaceServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaWorkspace> =
            jsonHandler<BetaWorkspace>(clientOptions.jsonMapper)

        override fun archive(
            params: WorkspaceArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaWorkspace> {
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
