package com.anthropic.services.async.beta.organization.claudetag

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
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.BetaClaudeTagSlackWorkspace
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListPageAsync
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListPageResponse
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceListParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SlackWorkspaceServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : SlackWorkspaceServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "experimental-claude-tag-2026-10-21").build()
    }

    private val withRawResponse: SlackWorkspaceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): SlackWorkspaceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): SlackWorkspaceServiceAsync =
        SlackWorkspaceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: SlackWorkspaceRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        // get /v1/organizations/claude_tag/slack_workspaces/{id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: SlackWorkspaceUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackWorkspace> =
        // post /v1/organizations/claude_tag/slack_workspaces/{id}
        withRawResponse().update(params, requestOptions).thenParse()

    override fun list(
        params: SlackWorkspaceListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SlackWorkspaceListPageAsync> =
        // get /v1/organizations/claude_tag/slack_workspaces
        withRawResponse().list(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SlackWorkspaceServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SlackWorkspaceServiceAsync.WithRawResponse =
            SlackWorkspaceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaClaudeTagSlackWorkspace> =
            jsonHandler<BetaClaudeTagSlackWorkspace>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SlackWorkspaceRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_workspaces")
                    .addPathParam("id", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
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

        private val updateHandler: Handler<BetaClaudeTagSlackWorkspace> =
            jsonHandler<BetaClaudeTagSlackWorkspace>(clientOptions.jsonMapper)

        override fun update(
            params: SlackWorkspaceUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackWorkspace>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_workspaces")
                    .addPathParam("id", params._pathParam(0))
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
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
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

        private val listHandler: Handler<SlackWorkspaceListPageResponse> =
            jsonHandler<SlackWorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SlackWorkspaceListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SlackWorkspaceListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_workspaces")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
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
                                    SlackWorkspaceListPageAsync.builder()
                                        .service(SlackWorkspaceServiceAsyncImpl(clientOptions))
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
}
