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
import com.anthropic.models.beta.organization.claudetag.slackchannels.BetaClaudeTagDeletedSlackChannel
import com.anthropic.models.beta.organization.claudetag.slackchannels.BetaClaudeTagSlackChannel
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelCreateParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelDeleteParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelListPageAsync
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelListPageResponse
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelListParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackchannels.SlackChannelUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SlackChannelServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SlackChannelServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "experimental-claude-tag-2026-10-21").build()
    }

    private val withRawResponse: SlackChannelServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): SlackChannelServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SlackChannelServiceAsync =
        SlackChannelServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: SlackChannelCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackChannel> =
        // post /v1/organizations/claude_tag/slack_channels
        withRawResponse().create(params, requestOptions).thenParse()

    override fun retrieve(
        params: SlackChannelRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackChannel> =
        // get /v1/organizations/claude_tag/slack_channels/{id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: SlackChannelUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagSlackChannel> =
        // post /v1/organizations/claude_tag/slack_channels/{id}
        withRawResponse().update(params, requestOptions).thenParse()

    override fun list(
        params: SlackChannelListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SlackChannelListPageAsync> =
        // get /v1/organizations/claude_tag/slack_channels
        withRawResponse().list(params, requestOptions).thenParse()

    override fun delete(
        params: SlackChannelDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagDeletedSlackChannel> =
        // delete /v1/organizations/claude_tag/slack_channels/{id}
        withRawResponse().delete(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SlackChannelServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SlackChannelServiceAsync.WithRawResponse =
            SlackChannelServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaClaudeTagSlackChannel> =
            jsonHandler<BetaClaudeTagSlackChannel>(clientOptions.jsonMapper)

        override fun create(
            params: SlackChannelCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_channels")
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
                                .use { createHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .also {
                                    client.recordCreatedId(
                                        "anthropic.organization.claude_tag.slack_channel.id",
                                        it._id(),
                                    )
                                }
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaClaudeTagSlackChannel> =
            jsonHandler<BetaClaudeTagSlackChannel>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SlackChannelRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_channels")
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

        private val updateHandler: Handler<BetaClaudeTagSlackChannel> =
            jsonHandler<BetaClaudeTagSlackChannel>(clientOptions.jsonMapper)

        override fun update(
            params: SlackChannelUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagSlackChannel>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_channels")
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

        private val listHandler: Handler<SlackChannelListPageResponse> =
            jsonHandler<SlackChannelListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SlackChannelListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SlackChannelListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_channels")
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
                                    SlackChannelListPageAsync.builder()
                                        .service(SlackChannelServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val deleteHandler: Handler<BetaClaudeTagDeletedSlackChannel> =
            jsonHandler<BetaClaudeTagDeletedSlackChannel>(clientOptions.jsonMapper)

        override fun delete(
            params: SlackChannelDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagDeletedSlackChannel>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "claude_tag", "slack_channels")
                    .addPathParam("id", params._pathParam(0))
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
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
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
    }
}
