package com.anthropic.services.async.beta.organization.claudecode.managedsettings

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
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
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.BetaClaudeCodeRbacGroupDefaultManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class RbacGroupDefaultServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : RbacGroupDefaultServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder()
                .put("anthropic-beta", "ce-claude-code-managed-settings-2026-10-06")
                .build()
    }

    private val withRawResponse: RbacGroupDefaultServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RbacGroupDefaultServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): RbacGroupDefaultServiceAsync =
        RbacGroupDefaultServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: RbacGroupDefaultRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
        // get /v1/organizations/claude_code/managed_settings/rbac_group_default
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: RbacGroupDefaultUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
        // post /v1/organizations/claude_code/managed_settings/rbac_group_default
        withRawResponse().update(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacGroupDefaultServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupDefaultServiceAsync.WithRawResponse =
            RbacGroupDefaultServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupDefaultManagedSettings>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacGroupDefaultRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "rbac_group_default",
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

        private val updateHandler: Handler<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupDefaultManagedSettings>(clientOptions.jsonMapper)

        override fun update(
            params: RbacGroupDefaultUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "rbac_group_default",
                    )
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
                                .use { updateHandler.handle(it) }
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
