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
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.BetaClaudeCodeManagedSettingsConfiguration
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class ConfigurationServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ConfigurationServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder()
                .put("anthropic-beta", "ce-claude-code-managed-settings-2026-10-06")
                .build()
    }

    private val withRawResponse: ConfigurationServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ConfigurationServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConfigurationServiceAsync =
        ConfigurationServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ConfigurationRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeCodeManagedSettingsConfiguration> =
        // get /v1/organizations/claude_code/managed_settings/configuration
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: ConfigurationUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeCodeManagedSettingsConfiguration> =
        // post /v1/organizations/claude_code/managed_settings/configuration
        withRawResponse().update(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ConfigurationServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ConfigurationServiceAsync.WithRawResponse =
            ConfigurationServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaClaudeCodeManagedSettingsConfiguration> =
            jsonHandler<BetaClaudeCodeManagedSettingsConfiguration>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ConfigurationRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "configuration",
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

        private val updateHandler: Handler<BetaClaudeCodeManagedSettingsConfiguration> =
            jsonHandler<BetaClaudeCodeManagedSettingsConfiguration>(clientOptions.jsonMapper)

        override fun update(
            params: ConfigurationUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeCodeManagedSettingsConfiguration>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "claude_code",
                        "managed_settings",
                        "configuration",
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
