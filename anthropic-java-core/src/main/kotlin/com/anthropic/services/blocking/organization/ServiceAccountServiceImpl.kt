package com.anthropic.services.blocking.organization

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
import com.anthropic.models.organization.serviceaccounts.ServiceAccount
import com.anthropic.models.organization.serviceaccounts.ServiceAccountArchiveParams
import com.anthropic.models.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.organization.serviceaccounts.ServiceAccountListPage
import com.anthropic.models.organization.serviceaccounts.ServiceAccountListPageResponse
import com.anthropic.models.organization.serviceaccounts.ServiceAccountListParams
import com.anthropic.models.organization.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.organization.serviceaccounts.ServiceAccountUpdateParams
import com.anthropic.services.blocking.organization.serviceaccounts.WorkspaceService
import com.anthropic.services.blocking.organization.serviceaccounts.WorkspaceServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ServiceAccountServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ServiceAccountService {

    private val withRawResponse: ServiceAccountService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val workspaces: WorkspaceService by lazy { WorkspaceServiceImpl(clientOptions) }

    override fun withRawResponse(): ServiceAccountService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ServiceAccountService =
        ServiceAccountServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun workspaces(): WorkspaceService = workspaces

    override fun create(
        params: ServiceAccountCreateParams,
        requestOptions: RequestOptions,
    ): ServiceAccount =
        // post /v1/organizations/service_accounts
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: ServiceAccountRetrieveParams,
        requestOptions: RequestOptions,
    ): ServiceAccount =
        // get /v1/organizations/service_accounts/{service_account_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: ServiceAccountUpdateParams,
        requestOptions: RequestOptions,
    ): ServiceAccount =
        // post /v1/organizations/service_accounts/{service_account_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: ServiceAccountListParams,
        requestOptions: RequestOptions,
    ): ServiceAccountListPage =
        // get /v1/organizations/service_accounts
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: ServiceAccountArchiveParams,
        requestOptions: RequestOptions,
    ): ServiceAccount =
        // post /v1/organizations/service_accounts/{service_account_id}/archive
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ServiceAccountService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val workspaces: WorkspaceService.WithRawResponse by lazy {
            WorkspaceServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ServiceAccountService.WithRawResponse =
            ServiceAccountServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun workspaces(): WorkspaceService.WithRawResponse = workspaces

        private val createHandler: Handler<ServiceAccount> =
            jsonHandler<ServiceAccount>(clientOptions.jsonMapper)

        override fun create(
            params: ServiceAccountCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccount> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "service_accounts")
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

        private val retrieveHandler: Handler<ServiceAccount> =
            jsonHandler<ServiceAccount>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ServiceAccountRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccount> {
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

        private val updateHandler: Handler<ServiceAccount> =
            jsonHandler<ServiceAccount>(clientOptions.jsonMapper)

        override fun update(
            params: ServiceAccountUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccount> {
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
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "service_accounts")
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

        private val archiveHandler: Handler<ServiceAccount> =
            jsonHandler<ServiceAccount>(clientOptions.jsonMapper)

        override fun archive(
            params: ServiceAccountArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ServiceAccount> {
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
