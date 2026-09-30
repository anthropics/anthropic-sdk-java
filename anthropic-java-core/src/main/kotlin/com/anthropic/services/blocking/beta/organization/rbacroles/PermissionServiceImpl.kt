package com.anthropic.services.blocking.beta.organization.rbacroles

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListPage
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListPageResponse
import com.anthropic.models.beta.organization.rbacroles.permissions.PermissionListParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class PermissionServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    PermissionService {

    private val withRawResponse: PermissionService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): PermissionService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): PermissionService =
        PermissionServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: PermissionListParams,
        requestOptions: RequestOptions,
    ): PermissionListPage =
        // get /v1/organizations/rbac_roles/{rbac_role_id}/permissions?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        PermissionService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): PermissionService.WithRawResponse =
            PermissionServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<PermissionListPageResponse> =
            jsonHandler<PermissionListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: PermissionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PermissionListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacRoleId", params.rbacRoleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "rbac_roles",
                        params._pathParam(0),
                        "permissions",
                    )
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
                        PermissionListPage.builder()
                            .service(PermissionServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
