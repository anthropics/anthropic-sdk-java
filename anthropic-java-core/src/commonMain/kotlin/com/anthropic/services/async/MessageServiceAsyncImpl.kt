// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import kotlinx.kmp.util.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.mapJson
import com.anthropic.core.handlers.sseHandler
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.json
import com.anthropic.core.http.map
import com.anthropic.core.http.parseable
import com.anthropic.core.http.toAsync
import com.anthropic.core.prepareSuspend
import com.anthropic.helpers.warnIfThinkingEnabled
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.MessageTokensCount
import com.anthropic.models.messages.RawMessageStreamEvent
import com.anthropic.services.async.messages.BatchServiceAsync
import com.anthropic.services.async.messages.BatchServiceAsyncImpl
import java.util.function.Consumer

class MessageServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MessageServiceAsync {

    private val withRawResponse: MessageServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val batches: BatchServiceAsync by lazy { BatchServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): MessageServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MessageServiceAsync =
        MessageServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun batches(): BatchServiceAsync = batches

    override suspend fun create(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): Message =
        // post /v1/messages
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<RawMessageStreamEvent> =
        // post /v1/messages
        withRawResponse().createStreaming(params, requestOptions).parse()
            .toAsync(clientOptions.streamHandlerExecutor)

    override suspend fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions,
    ): MessageTokensCount =
        // post /v1/messages/count_tokens
        withRawResponse().countTokens(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MessageServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val batches: BatchServiceAsync.WithRawResponse by lazy {
            BatchServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MessageServiceAsync.WithRawResponse =
            MessageServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun batches(): BatchServiceAsync.WithRawResponse = batches

        private val createHandler: Handler<Message> = jsonHandler<Message>(clientOptions.jsonMapper)

        override suspend fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Message> {
            warnIfThinkingEnabled(params.model().toString(), params.thinking().orElse(null))

            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .applyDefaultTimeoutFromMaxTokens(
                        params.maxTokens(),
                        isStreaming = false,
                        model = params.model().toString(),
                    )
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val createStreamingHandler: Handler<StreamResponse<RawMessageStreamEvent>> =
            sseHandler(clientOptions.jsonMapper).mapJson<RawMessageStreamEvent>()

        override suspend fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<RawMessageStreamEvent>> {
            warnIfThinkingEnabled(params.model().toString(), params.thinking().orElse(null))

            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages")
                    .putHeader("Accept", "text/event-stream")
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
                    .prepareSuspend(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .applyDefaultTimeoutFromMaxTokens(
                        params.maxTokens(),
                        isStreaming = true,
                        model = params.model().toString(),
                    )
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
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

        private val countTokensHandler: Handler<MessageTokensCount> =
            jsonHandler<MessageTokensCount>(clientOptions.jsonMapper)

        override suspend fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageTokensCount> {
            warnIfThinkingEnabled(params.model().toString(), params.thinking().orElse(null))

            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "count_tokens")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
