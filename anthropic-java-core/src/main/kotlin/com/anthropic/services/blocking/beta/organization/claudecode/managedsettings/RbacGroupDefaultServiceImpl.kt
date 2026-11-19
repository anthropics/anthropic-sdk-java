package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

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
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.BetaClaudeCodeRbacGroupDefaultManagedSettings
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultUpdateParams
import java.util.function.Consumer

class RbacGroupDefaultServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacGroupDefaultService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder()
                .put("anthropic-beta", "ce-claude-code-managed-settings-2026-10-06")
                .build()
    }

    private val withRawResponse: RbacGroupDefaultService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RbacGroupDefaultService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupDefaultService =
        RbacGroupDefaultServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: RbacGroupDefaultRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaClaudeCodeRbacGroupDefaultManagedSettings =
        // get /v1/organizations/claude_code/managed_settings/rbac_group_default
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: RbacGroupDefaultUpdateParams,
        requestOptions: RequestOptions,
    ): BetaClaudeCodeRbacGroupDefaultManagedSettings =
        // post /v1/organizations/claude_code/managed_settings/rbac_group_default
        withRawResponse().update(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacGroupDefaultService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupDefaultService.WithRawResponse =
            RbacGroupDefaultServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupDefaultManagedSettings>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacGroupDefaultRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings> {
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

        private val updateHandler: Handler<BetaClaudeCodeRbacGroupDefaultManagedSettings> =
            jsonHandler<BetaClaudeCodeRbacGroupDefaultManagedSettings>(clientOptions.jsonMapper)

        override fun update(
            params: RbacGroupDefaultUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeCodeRbacGroupDefaultManagedSettings> {
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
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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
