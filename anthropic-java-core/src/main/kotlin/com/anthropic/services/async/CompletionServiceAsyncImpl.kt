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
import com.anthropic.models.Completion
import com.anthropic.models.CompletionCreateParams
import java.time.Duration
import java.util.concurrent.CompletableFuture

class CompletionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CompletionServiceAsync {

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val createHandler: Handler<Completion> =
        jsonHandler<Completion>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * [Legacy] Create a Text Completion.
     *
     * The Text Completions API is a legacy API. We recommend using the
     * [Messages API](https://docs.anthropic.com/en/api/messages) going forward.
     *
     * Future models and features will not be compatible with Text Completions. See our
     * [migration guide](https://docs.anthropic.com/en/api/migrating-from-text-completions-to-messages)
     * for guidance in migrating from Text Completions to Messages.
     */
    override fun create(
        params: CompletionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Completion> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "complete")
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync {
                clientOptions.httpClient.executeAsync(
                    it,
                    requestOptions.applyDefaults(
                        RequestOptions.builder().timeout(Duration.ofMillis(600000)).build()
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

    private val createStreamingHandler: Handler<StreamResponse<Completion>> =
        sseHandler(clientOptions.jsonMapper).mapJson<Completion>().withErrorHandler(errorHandler)

    /**
     * [Legacy] Create a Text Completion.
     *
     * The Text Completions API is a legacy API. We recommend using the
     * [Messages API](https://docs.anthropic.com/en/api/messages) going forward.
     *
     * Future models and features will not be compatible with Text Completions. See our
     * [migration guide](https://docs.anthropic.com/en/api/migrating-from-text-completions-to-messages)
     * for guidance in migrating from Text Completions to Messages.
     */
    override fun createStreaming(
        params: CompletionCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<Completion> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "complete")
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
                    requestOptions.applyDefaults(
                        RequestOptions.builder().timeout(Duration.ofMillis(600000)).build()
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
}
