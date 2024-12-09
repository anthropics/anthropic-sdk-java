// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.map
import com.anthropic.core.handlers.mapJson
import com.anthropic.core.handlers.sseHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.json
import com.anthropic.errors.AnthropicError
import com.anthropic.models.Message
import com.anthropic.models.MessageCreateParams
import com.anthropic.models.RawMessageStreamEvent
import java.time.Duration

class MessageServiceImpl
constructor(
    private val clientOptions: ClientOptions,
) : MessageService {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val createHandler: Handler<Message> =
        jsonHandler<Message>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Send a structured list of input messages with text and/or image content, and the model will
     * generate the next message in the conversation.
     *
     * The Messages API can be used for either single queries or stateless multi-turn conversations.
     */
    override fun create(params: MessageCreateParams, requestOptions: RequestOptions): Message {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages")
                .putAllQueryParams(clientOptions.queryParams)
                .replaceAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .replaceAllHeaders(params.getHeaders())
                .body(json(clientOptions.jsonMapper, params.getBody()))
                .build()
        return clientOptions.httpClient
            .execute(
                request,
                requestOptions.applyDefaults(
                    RequestOptions.builder().timeout(Duration.ofMillis(600000)).build()
                )
            )
            .let { response ->
                response
                    .use { createHandler.handle(it) }
                    .apply {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            validate()
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
     */
    override fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions
    ): StreamResponse<RawMessageStreamEvent> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages")
                .putAllQueryParams(clientOptions.queryParams)
                .replaceAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .replaceAllHeaders(params.getHeaders())
                .body(
                    json(
                        clientOptions.jsonMapper,
                        params
                            .getBody()
                            .toBuilder()
                            .putAdditionalProperty("stream", JsonValue.from(true))
                            .build()
                    )
                )
                .build()
        return clientOptions.httpClient
            .execute(
                request,
                requestOptions.applyDefaults(
                    RequestOptions.builder().timeout(Duration.ofMillis(600000)).build()
                )
            )
            .let { response ->
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
    }
}
