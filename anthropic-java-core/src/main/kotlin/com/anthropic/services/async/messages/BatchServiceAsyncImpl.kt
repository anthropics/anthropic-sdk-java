// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.messages

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.jsonlHandler
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
import com.anthropic.models.DeletedMessageBatch
import com.anthropic.models.MessageBatch
import com.anthropic.models.MessageBatchCancelParams
import com.anthropic.models.MessageBatchCreateParams
import com.anthropic.models.MessageBatchDeleteParams
import com.anthropic.models.MessageBatchIndividualResponse
import com.anthropic.models.MessageBatchListPageAsync
import com.anthropic.models.MessageBatchListParams
import com.anthropic.models.MessageBatchResultsParams
import com.anthropic.models.MessageBatchRetrieveParams
import java.util.concurrent.CompletableFuture

class BatchServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BatchServiceAsync {

    private val withRawResponse: BatchServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): BatchServiceAsync.WithRawResponse = withRawResponse

    override fun create(
        params: MessageBatchCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageBatch> =
        // post /v1/messages/batches
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: MessageBatchRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageBatch> =
        // get /v1/messages/batches/{message_batch_id}
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: MessageBatchListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageBatchListPageAsync> =
        // get /v1/messages/batches
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: MessageBatchDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedMessageBatch> =
        // delete /v1/messages/batches/{message_batch_id}
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun cancel(
        params: MessageBatchCancelParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MessageBatch> =
        // post /v1/messages/batches/{message_batch_id}/cancel
        withRawResponse().cancel(params, requestOptions).thenApply { it.parse() }

    override fun resultsStreaming(
        params: MessageBatchResultsParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        // get /v1/messages/batches/{message_batch_id}/results
        withRawResponse()
            .resultsStreaming(params, requestOptions)
            .thenApply { it.parse() }
            .toAsync(clientOptions.streamHandlerExecutor)

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BatchServiceAsync.WithRawResponse {

        private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

        private val createHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun create(
            params: MessageBatchCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MessageBatch>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "batches")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
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

        private val retrieveHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun retrieve(
            params: MessageBatchRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MessageBatch>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<MessageBatchListPageAsync.Response> =
            jsonHandler<MessageBatchListPageAsync.Response>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun list(
            params: MessageBatchListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MessageBatchListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                MessageBatchListPageAsync.of(
                                    BatchServiceAsyncImpl(clientOptions),
                                    params,
                                    it,
                                )
                            }
                    }
                }
        }

        private val deleteHandler: Handler<DeletedMessageBatch> =
            jsonHandler<DeletedMessageBatch>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun delete(
            params: MessageBatchDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedMessageBatch>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .use { deleteHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val cancelHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun cancel(
            params: MessageBatchCancelParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MessageBatch>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "cancel")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .use { cancelHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val resultsStreamingHandler:
            Handler<StreamResponse<MessageBatchIndividualResponse>> =
            jsonlHandler<MessageBatchIndividualResponse>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun resultsStreaming(
            params: MessageBatchResultsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "results")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    response.parseable {
                        response
                            .let { resultsStreamingHandler.handle(it) }
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
}
