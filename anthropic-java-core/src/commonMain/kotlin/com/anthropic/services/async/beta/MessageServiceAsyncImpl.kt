// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import kotlinx.kmp.util.core.streamExecutor
import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.handlers.errorBodyHandler
import kotlinx.kmp.util.core.handlers.errorHandler
import kotlinx.kmp.util.core.handlers.jsonHandler
import kotlinx.kmp.util.core.handlers.mapJson
import kotlinx.kmp.util.core.handlers.sseHandler
import kotlinx.kmp.util.core.http.AsyncStreamResponse
import kotlinx.kmp.util.core.http.HttpMethod
import kotlinx.kmp.util.core.http.HttpRequest
import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.http.HttpResponse.Handler
import kotlinx.kmp.util.core.http.HttpResponseFor
import kotlinx.kmp.util.core.http.StreamResponse
import kotlinx.kmp.util.core.http.json
import kotlinx.kmp.util.core.http.map
import kotlinx.kmp.util.core.http.parseable
import kotlinx.kmp.util.core.http.toAsync
import kotlinx.kmp.util.core.prepareSuspend
import com.anthropic.helpers.warnIfThinkingEnabled
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageTokensCount
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.services.async.beta.messages.BatchServiceAsync
import com.anthropic.services.async.beta.messages.BatchServiceAsyncImpl
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
    ): BetaMessage =
        // post /v1/messages?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun createStreaming(
        params: MessageCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        // post /v1/messages?beta=true
        withRawResponse().createStreaming(params, requestOptions).parse()
            .toAsync(clientOptions.streamExecutor())

    override suspend fun countTokens(
        params: MessageCountTokensParams,
        requestOptions: RequestOptions,
    ): BetaMessageTokensCount =
        // post /v1/messages/count_tokens?beta=true
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

        private val createHandler: Handler<BetaMessage> =
            jsonHandler<BetaMessage>(clientOptions.jsonMapper)

        override suspend fun create(
            params: MessageCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessage> {
            warnIfThinkingEnabled(params.model().toString(), params.thinking().orElse(null))

            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages")
                    .putQueryParam("beta", "true")
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

        private val createStreamingHandler: Handler<StreamResponse<BetaRawMessageStreamEvent>> =
            sseHandler(clientOptions.jsonMapper).mapJson<BetaRawMessageStreamEvent>()

        override suspend fun createStreaming(
            params: MessageCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<BetaRawMessageStreamEvent>> {
            warnIfThinkingEnabled(params.model().toString(), params.thinking().orElse(null))

            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages")
                    .putQueryParam("beta", "true")
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

        private val countTokensHandler: Handler<BetaMessageTokensCount> =
            jsonHandler<BetaMessageTokensCount>(clientOptions.jsonMapper)

        override suspend fun countTokens(
            params: MessageCountTokensParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageTokensCount> {
            warnIfThinkingEnabled(params.model().toString(), params.thinking().orElse(null))

            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "count_tokens")
                    .putQueryParam("beta", "true")
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
