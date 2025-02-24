// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.mapJson
import com.anthropic.core.handlers.sseHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.map
import com.anthropic.core.json
import com.anthropic.core.prepare
import com.anthropic.errors.AnthropicError
import com.anthropic.models.Message
import com.anthropic.models.MessageCountTokensParams
import com.anthropic.models.MessageCreateParams
import com.anthropic.models.MessageTokensCount
import com.anthropic.models.RawMessageStreamEvent
import com.anthropic.services.blocking.messages.BatchService
import com.anthropic.services.blocking.messages.BatchServiceImpl

class MessageServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    MessageService {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val batches: BatchService by lazy { BatchServiceImpl(clientOptions) }

    override fun batches(): BatchService = batches

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
    override fun create(params: MessageCreateParams, requestOptions: RequestOptions): Message {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepare(clientOptions, params)
        val response =
            clientOptions.httpClient.execute(
                request,
                requestOptions.applyDefaultTimeoutFromMaxTokens(
                    params.maxTokens(),
                    isStreaming = false,
                ),
            )
        return response
            .use { createHandler.handle(it) }
            .also {
                if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                    it.validate()
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
    ): StreamResponse<RawMessageStreamEvent> {
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
                .prepare(clientOptions, params)
        val response =
            clientOptions.httpClient.execute(
                request,
                requestOptions.applyDefaultTimeoutFromMaxTokens(
                    params.maxTokens(),
                    isStreaming = true,
                ),
            )
        return response
            .let { createStreamingHandler.handle(it) }
            .let { streamResponse ->
                if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                    streamResponse.map { it.validate() }
                } else {
                    streamResponse
                }
            }
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
    ): MessageTokensCount {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages", "count_tokens")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepare(clientOptions, params)
        val response = clientOptions.httpClient.execute(request, requestOptions)
        return response
            .use { countTokensHandler.handle(it) }
            .also {
                if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                    it.validate()
                }
            }
    }
}
