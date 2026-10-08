package com.anthropic.services.blocking.organization.workspaces

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
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountListPage
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountListPageResponse
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRemoveResponse
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ServiceAccountServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ServiceAccountService {

    private val withRawResponse: ServiceAccountService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ServiceAccountService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ServiceAccountService =
        ServiceAccountServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions,
    ): ServiceAccountWorkspaceMember =
        // get /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions,
    ): ServiceAccountWorkspaceMember =
        // post /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: ServiceAccountListParams,
        requestOptions: RequestOptions,
    ): ServiceAccountListPage =
        // get /v1/organizations/workspaces/{workspace_id}/service_accounts
        withRawResponse().list(params, requestOptions).parse()

    override fun add(
        params: ServiceAccountAddParams,
        requestOptions: RequestOptions,
    ): ServiceAccountWorkspaceMember =
        // post /v1/organizations/workspaces/{workspace_id}/service_accounts
        withRawResponse().add(params, requestOptions).parse()

    override fun remove(
        params: ServiceAccountRemoveParams,
        requestOptions: RequestOptions,
    ): ServiceAccountRemoveResponse =
        // delete /v1/organizations/workspaces/{workspace_id}/service_accounts/{service_account_id}
        withRawResponse().remove(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ServiceAccountService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ServiceAccountService.WithRawResponse =
            ServiceAccountServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccountWorkspaceMember> {
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

        private val updateHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccountWorkspaceMember> {
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

        private val listHandler: Handler<ServiceAccountListPageResponse> =
            jsonHandler<ServiceAccountListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ServiceAccountListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccountListPage> {
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
                        ServiceAccountListPage.builder()
                            .service(ServiceAccountServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val addHandler: Handler<ServiceAccountWorkspaceMember> =
            jsonHandler<ServiceAccountWorkspaceMember>(clientOptions.jsonMapper)

        override fun add(
            params: ServiceAccountAddParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccountWorkspaceMember> {
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

        private val removeHandler: Handler<ServiceAccountRemoveResponse> =
            jsonHandler<ServiceAccountRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: ServiceAccountRemoveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccountRemoveResponse> {
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
