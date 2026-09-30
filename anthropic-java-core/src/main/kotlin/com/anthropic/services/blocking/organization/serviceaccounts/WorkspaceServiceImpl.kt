package com.anthropic.services.blocking.organization.serviceaccounts

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
import com.anthropic.models.organization.serviceaccounts.ServiceAccountWorkspaceMember
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceListPage
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceListPageResponse
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceListParams
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceRemoveResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkspaceServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkspaceService {

    private val withRawResponse: WorkspaceService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WorkspaceService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService =
        WorkspaceServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions,
    ): WorkspaceListPage =
        // get /v1/organizations/service_accounts/{service_account_id}/workspaces
        withRawResponse().list(params, requestOptions).parse()

    override fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions,
    ): ServiceAccountWorkspaceMember =
        // post /v1/organizations/service_accounts/{service_account_id}/workspaces
        withRawResponse().add(params, requestOptions).parse()

    override fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions,
    ): WorkspaceRemoveResponse =
        // delete /v1/organizations/service_accounts/{service_account_id}/workspaces/{workspace_id}
        withRawResponse().remove(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkspaceService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceService.WithRawResponse =
            WorkspaceServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<WorkspaceListPageResponse> =
            jsonHandler<WorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceListPage> {
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

        private val addHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccountWorkspaceMember> {
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
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { addHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val removeHandler: Handler<WorkspaceRemoveResponse> =
            jsonHandler<WorkspaceRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceRemoveResponse> {
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
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
