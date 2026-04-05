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
import com.anthropic.models.completions.Completion
import com.anthropic.models.completions.CompletionCreateParams
import java.util.function.Consumer

class CompletionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CompletionServiceAsync {

    private val withRawResponse: CompletionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CompletionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CompletionServiceAsync =
        CompletionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun create(
        params: CompletionCreateParams,
        requestOptions: RequestOptions,
    ): Completion =
        // post /v1/complete
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun createStreaming(
        params: CompletionCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<Completion> =
        // post /v1/complete
        withRawResponse().createStreaming(params, requestOptions).parse()
            .toAsync(clientOptions.streamHandlerExecutor)

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CompletionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CompletionServiceAsync.WithRawResponse =
            CompletionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<Completion> =
            jsonHandler<Completion>(clientOptions.jsonMapper)

        override suspend fun create(
            params: CompletionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Completion> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "complete")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .applyDefaultTimeoutFromMaxTokens(
                        params.maxTokensToSample(),
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

        private val createStreamingHandler: Handler<StreamResponse<Completion>> =
            sseHandler(clientOptions.jsonMapper).mapJson<Completion>()

        override suspend fun createStreaming(
            params: CompletionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<Completion>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "complete")
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
                        params.maxTokensToSample(),
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
    }
}
