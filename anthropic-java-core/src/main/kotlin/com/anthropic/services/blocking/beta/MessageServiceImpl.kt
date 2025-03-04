// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

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
import com.anthropic.models.BetaMessage
import com.anthropic.models.BetaMessageCountTokensParams
import com.anthropic.models.BetaMessageCreateParams
import com.anthropic.models.BetaMessageTokensCount
import com.anthropic.models.BetaRawMessageStreamEvent
import com.anthropic.services.blocking.beta.messages.BatchService
import com.anthropic.services.blocking.beta.messages.BatchServiceImpl
import java.time.Duration

class MessageServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    MessageService {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val batches: BatchService by lazy { BatchServiceImpl(clientOptions) }

    override fun batches(): BatchService = batches

    private val createHandler: Handler<BetaMessage> =
        jsonHandler<BetaMessage>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     *
     * Learn more about the Messages API in our [user guide](/en/docs/initial-setup)
     */
    override fun create(
        params: BetaMessageCreateParams,
        requestOptions: RequestOptions,
    ): BetaMessage {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages")
                .putQueryParam("beta", "true")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepare(clientOptions, params)
        val requestOptions =
            requestOptions
                .applyDefaults(RequestOptions.from(clientOptions))
                .applyDefaults(RequestOptions.builder().timeout(Duration.ofMinutes(10)).build())
        val response = clientOptions.httpClient.execute(request, requestOptions)
        return response
            .use { createHandler.handle(it) }
            .also {
                if (requestOptions.responseValidation!!) {
                    it.validate()
                }
            }
    }

    private val createStreamingHandler: Handler<StreamResponse<BetaRawMessageStreamEvent>> =
        sseHandler(clientOptions.jsonMapper)
            .mapJson<BetaRawMessageStreamEvent>()
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
        params: BetaMessageCreateParams,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaRawMessageStreamEvent> {
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
                .prepare(clientOptions, params)
        val requestOptions =
            requestOptions
                .applyDefaults(RequestOptions.from(clientOptions))
                .applyDefaults(RequestOptions.builder().timeout(Duration.ofMinutes(10)).build())
        val response = clientOptions.httpClient.execute(request, requestOptions)
        return response
            .let { createStreamingHandler.handle(it) }
            .let { streamResponse ->
                if (requestOptions.responseValidation!!) {
                    streamResponse.map { it.validate() }
                } else {
                    streamResponse
                }
            }
    }

    private val countTokensHandler: Handler<BetaMessageTokensCount> =
        jsonHandler<BetaMessageTokensCount>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

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
        params: BetaMessageCountTokensParams,
        requestOptions: RequestOptions,
    ): BetaMessageTokensCount {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages", "count_tokens")
                .putQueryParam("beta", "true")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepare(clientOptions, params)
        val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
        val response = clientOptions.httpClient.execute(request, requestOptions)
        return response
            .use { countTokensHandler.handle(it) }
            .also {
                if (requestOptions.responseValidation!!) {
                    it.validate()
                }
            }
    }
}
