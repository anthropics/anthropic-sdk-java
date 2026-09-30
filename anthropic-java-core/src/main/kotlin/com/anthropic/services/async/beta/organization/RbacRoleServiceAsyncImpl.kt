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
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.rbacroles.BetaRbacRole
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListPageAsync
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListPageResponse
import com.anthropic.models.beta.organization.rbacroles.RbacRoleListParams
import com.anthropic.models.beta.organization.rbacroles.RbacRoleRetrieveParams
import com.anthropic.services.async.beta.organization.rbacroles.PermissionServiceAsync
import com.anthropic.services.async.beta.organization.rbacroles.PermissionServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RbacRoleServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacRoleServiceAsync {

    private val withRawResponse: RbacRoleServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val permissions: PermissionServiceAsync by lazy {
        PermissionServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): RbacRoleServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacRoleServiceAsync =
        RbacRoleServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun permissions(): PermissionServiceAsync = permissions

    override fun retrieve(
        params: RbacRoleRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaRbacRole> =
        // get /v1/organizations/rbac_roles/{rbac_role_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: RbacRoleListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacRoleListPageAsync> =
        // get /v1/organizations/rbac_roles?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacRoleServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val permissions: PermissionServiceAsync.WithRawResponse by lazy {
            PermissionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacRoleServiceAsync.WithRawResponse =
            RbacRoleServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun permissions(): PermissionServiceAsync.WithRawResponse = permissions

        private val retrieveHandler: Handler<BetaRbacRole> =
            jsonHandler<BetaRbacRole>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacRoleRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaRbacRole>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacRoleId", params.rbacRoleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_roles", params._pathParam(0))
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

        private val listHandler: Handler<RbacRoleListPageResponse> =
            jsonHandler<RbacRoleListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RbacRoleListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacRoleListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_roles")
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
                                RbacRoleListPageAsync.builder()
                                    .service(RbacRoleServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }
    }
}
