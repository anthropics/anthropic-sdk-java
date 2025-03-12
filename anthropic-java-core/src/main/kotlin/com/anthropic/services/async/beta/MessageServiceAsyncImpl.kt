// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.mapJson
import com.anthropic.core.handlers.sseHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.json
import com.anthropic.core.http.map
import com.anthropic.core.http.parseable
import com.anthropic.core.http.toAsync
import com.anthropic.core.prepareAsync
import com.anthropic.errors.AnthropicError
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageTokensCount
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.services.async.beta.messages.BatchServiceAsync
import com.anthropic.services.async.beta.messages.BatchServiceAsyncImpl
import java.util.concurrent.CompletableFuture

class MessageServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MessageServiceAsync {

    private val withRawResponse: MessageServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val batches: BatchServiceAsync by lazy { BatchServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): MessageServiceAsync.WithRawResponse = withRawResponse

    override fun batches(): BatchServiceAsync = batches

    override fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessage> =
        // post /v1/messages?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        // post /v1/messages?beta=true
        withRawResponse()
            .createStreaming(params, requestOptions)
            .thenApply { it.parse() }
            .toAsync(clientOptions.streamHandlerExecutor)

    override fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessageTokensCount> =
        // post /v1/messages/count_tokens?beta=true
        withRawResponse().countTokens(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MessageServiceAsync.WithRawResponse {

        private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

        private val batches: BatchServiceAsync.WithRawResponse by lazy {
            BatchServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun batches(): BatchServiceAsync.WithRawResponse = batches

        private val createHandler: Handler<BetaMessage> =
            jsonHandler<BetaMessage>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaMessage>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages")
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .applyDefaultTimeoutFromMaxTokens(params.maxTokens(), isStreaming = false)
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val createStreamingHandler: Handler<StreamResponse<BetaRawMessageStreamEvent>> =
            sseHandler(clientOptions.jsonMapper)
                .mapJson<BetaRawMessageStreamEvent>()
                .withErrorHandler(errorHandler)

        override fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages")
                    .putQueryParam("beta", "true")
                    .body(
                        json(
                            clientOptions.jsonMapper,
                            params
                                ._body()
                                .toBuilder()
                                .putAdditionalProperty("stream", JsonValue.from(true))
                                .build(),
                        )
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .applyDefaultTimeoutFromMaxTokens(params.maxTokens(), isStreaming = true)
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .let { createStreamingHandler.handle(it) }
                            .let { streamResponse ->
                                if (requestOptions.responseValidation!!) {
                                    streamResponse.map { it.validate() }
                                } else {
                                    streamResponse
                                }
                            }
                    }
                }
        }

        private val countTokensHandler: Handler<BetaMessageTokensCount> =
            jsonHandler<BetaMessageTokensCount>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaMessageTokensCount>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "count_tokens")
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .use { countTokensHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }
    }
}
