// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

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
            .toAsync(clientOptions.streamExecutor())

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
