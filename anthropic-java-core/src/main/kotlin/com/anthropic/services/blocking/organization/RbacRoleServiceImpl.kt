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
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.organization.rbacroles.RbacRole
import com.anthropic.models.organization.rbacroles.RbacRoleListPage
import com.anthropic.models.organization.rbacroles.RbacRoleListPageResponse
import com.anthropic.models.organization.rbacroles.RbacRoleListParams
import com.anthropic.models.organization.rbacroles.RbacRoleRetrieveParams
import com.anthropic.services.blocking.organization.rbacroles.PermissionService
import com.anthropic.services.blocking.organization.rbacroles.PermissionServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RbacRoleServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacRoleService {

    private val withRawResponse: RbacRoleService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val permissions: PermissionService by lazy { PermissionServiceImpl(clientOptions) }

    override fun withRawResponse(): RbacRoleService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacRoleService =
        RbacRoleServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun permissions(): PermissionService = permissions

    override fun retrieve(
        params: RbacRoleRetrieveParams,
        requestOptions: RequestOptions,
    ): RbacRole =
        // get /v1/organizations/rbac_roles/{rbac_role_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: RbacRoleListParams,
        requestOptions: RequestOptions,
    ): RbacRoleListPage =
        // get /v1/organizations/rbac_roles
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacRoleService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val permissions: PermissionService.WithRawResponse by lazy {
            PermissionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacRoleService.WithRawResponse =
            RbacRoleServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun permissions(): PermissionService.WithRawResponse = permissions

        private val retrieveHandler: Handler<RbacRole> =
            jsonHandler<RbacRole>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacRoleRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacRole> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacRoleId", params.rbacRoleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_roles")
                    .addPathParam("rbacRoleId", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { retrieveHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val listHandler: Handler<RbacRoleListPageResponse> =
            jsonHandler<RbacRoleListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RbacRoleListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacRoleListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_roles")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { listHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                        .let {
                            RbacRoleListPage.builder()
                                .service(RbacRoleServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }
    }
}
