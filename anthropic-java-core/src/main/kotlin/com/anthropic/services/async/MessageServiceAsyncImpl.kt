// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

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
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.map
import com.anthropic.core.http.toAsync
import com.anthropic.core.json
import com.anthropic.core.prepareAsync
import com.anthropic.errors.AnthropicError
import com.anthropic.models.Message
import com.anthropic.models.MessageCountTokensParams
import com.anthropic.models.MessageCreateParams
import com.anthropic.models.MessageTokensCount
import com.anthropic.models.RawMessageStreamEvent
import com.anthropic.services.async.messages.BatchServiceAsync
import com.anthropic.services.async.messages.BatchServiceAsyncImpl
import java.util.concurrent.CompletableFuture

class MessageServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MessageServiceAsync {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val batches: BatchServiceAsync by lazy { BatchServiceAsyncImpl(clientOptions) }

    override fun batches(): BatchServiceAsync = batches

    private val createHandler: Handler<Message> =
        jsonHandler<Message>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    override fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Message> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync {
                clientOptions.httpClient.executeAsync(
                    it,
                    requestOptions.applyDefaultTimeoutFromMaxTokens(
                        params.maxTokens(),
                        isStreaming = false,
                    ),
                )
            }
            .thenApply { response ->
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
            }
    }

    private val createStreamingHandler: Handler<StreamResponse<RawMessageStreamEvent>> =
        sseHandler(clientOptions.jsonMapper)
            .mapJson<RawMessageStreamEvent>()
            .withErrorHandler(errorHandler)

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    override fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<RawMessageStreamEvent> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages")
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
        return request
            .thenComposeAsync {
                clientOptions.httpClient.executeAsync(
                    it,
                    requestOptions.applyDefaultTimeoutFromMaxTokens(
                        params.maxTokens(),
                        isStreaming = true,
                    ),
                )
            }
            .thenApply { response ->
                response
                    .let { createStreamingHandler.handle(it) }
                    .let { streamResponse ->
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            streamResponse.map { it.validate() }
                        } else {
                            streamResponse
                        }
                    }
            }
            .toAsync(clientOptions.streamHandlerExecutor)
    }

    private val countTokensHandler: Handler<MessageTokensCount> =
        jsonHandler<MessageTokensCount>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Count the number of tokens in a Message.
     *
     * The Token Count API can be used to count the number of tokens in a Message, including tools,
     * images, and documents, without creating it.
     *
     * Learn more about token counting in our
     * [user guide](/en/docs/build-with-claude/token-counting)
     */
    override fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageTokensCount> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages", "count_tokens")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { countTokensHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
            }
    }
}
