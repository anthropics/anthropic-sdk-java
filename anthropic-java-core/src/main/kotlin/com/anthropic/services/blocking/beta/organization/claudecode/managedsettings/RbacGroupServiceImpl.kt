package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.BetaClaudeCodeRbacGroupManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.BetaDeletedClaudeCodeRbacGroupManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListPage
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListPageResponse
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupSetParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RbacGroupServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacGroupService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder()
                .put("anthropic-beta", "ce-claude-code-managed-settings-2026-10-06")
                .build()
    }

    private val withRawResponse: RbacGroupService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RbacGroupService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupService =
        RbacGroupServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaClaudeCodeRbacGroupManagedSettings =
        // get /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: RbacGroupListParams,
        requestOptions: RequestOptions,
    ): RbacGroupListPage =
        // get /v1/organizations/claude_code/managed_settings/rbac_groups
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedClaudeCodeRbacGroupManagedSettings =
        // delete /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}
        withRawResponse().delete(params, requestOptions).parse()

    override fun set(
        params: RbacGroupSetParams,
        requestOptions: RequestOptions,
    ): BetaClaudeCodeRbacGroupManagedSettings =
        // post /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}
        withRawResponse().set(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacGroupService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupService.WithRawResponse =
            RbacGroupServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaClaudeCodeRbacGroupManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupManagedSettings>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "rbac_groups",
                    )
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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

        private val listHandler: Handler<RbacGroupListPageResponse> =
            jsonHandler<RbacGroupListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RbacGroupListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacGroupListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "rbac_groups",
                    )
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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
                            RbacGroupListPage.builder()
                                .service(RbacGroupServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val deleteHandler: Handler<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            jsonHandler<BetaDeletedClaudeCodeRbacGroupManagedSettings>(clientOptions.jsonMapper)

        override fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "rbac_groups",
                    )
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { deleteHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val setHandler: Handler<BetaClaudeCodeRbacGroupManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupManagedSettings>(clientOptions.jsonMapper)

        override fun set(
            params: RbacGroupSetParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "rbac_groups",
                    )
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { setHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }
    }
}
