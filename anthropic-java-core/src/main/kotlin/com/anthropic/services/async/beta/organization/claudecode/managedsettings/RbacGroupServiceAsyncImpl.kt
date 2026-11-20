package com.anthropic.services.async.beta.organization.claudecode.managedsettings

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.BetaClaudeCodeRbacGroupManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.BetaDeletedClaudeCodeRbacGroupManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListPageAsync
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListPageResponse
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupListParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupSetParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RbacGroupServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacGroupServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder()
                .put("anthropic-beta", "ce-claude-code-managed-settings-2026-10-06")
                .build()
    }

    private val withRawResponse: RbacGroupServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RbacGroupServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupServiceAsync =
        RbacGroupServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeCodeRbacGroupManagedSettings> =
        // get /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun list(
        params: RbacGroupListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacGroupListPageAsync> =
        // get /v1/organizations/claude_code/managed_settings/rbac_groups
        withRawResponse().list(params, requestOptions).thenParse()

    override fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
        // delete /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}
        withRawResponse().delete(params, requestOptions).thenParse()

    override fun set(
        params: RbacGroupSetParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeCodeRbacGroupManagedSettings> =
        // post /v1/organizations/claude_code/managed_settings/rbac_groups/{rbac_group_id}
        withRawResponse().set(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacGroupServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupServiceAsync.WithRawResponse =
            RbacGroupServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaClaudeCodeRbacGroupManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupManagedSettings>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
        }

        private val listHandler: Handler<RbacGroupListPageResponse> =
            jsonHandler<RbacGroupListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RbacGroupListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacGroupListPageAsync>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { listHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .let {
                                    RbacGroupListPageAsync.builder()
                                        .service(RbacGroupServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val deleteHandler: Handler<BetaDeletedClaudeCodeRbacGroupManagedSettings> =
            jsonHandler<BetaDeletedClaudeCodeRbacGroupManagedSettings>(clientOptions.jsonMapper)

        override fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedClaudeCodeRbacGroupManagedSettings>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
        }

        private val setHandler: Handler<BetaClaudeCodeRbacGroupManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupManagedSettings>(clientOptions.jsonMapper)

        override fun set(
            params: RbacGroupSetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupManagedSettings>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
}
