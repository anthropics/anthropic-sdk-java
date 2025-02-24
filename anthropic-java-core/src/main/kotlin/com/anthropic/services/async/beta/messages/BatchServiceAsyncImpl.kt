// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.messages

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.jsonlHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.map
import com.anthropic.core.http.toAsync
import com.anthropic.core.json
import com.anthropic.core.prepareAsync
import com.anthropic.errors.AnthropicError
import com.anthropic.models.BetaDeletedMessageBatch
import com.anthropic.models.BetaMessageBatch
import com.anthropic.models.BetaMessageBatchCancelParams
import com.anthropic.models.BetaMessageBatchCreateParams
import com.anthropic.models.BetaMessageBatchDeleteParams
import com.anthropic.models.BetaMessageBatchIndividualResponse
import com.anthropic.models.BetaMessageBatchListPageAsync
import com.anthropic.models.BetaMessageBatchListParams
import com.anthropic.models.BetaMessageBatchResultsParams
import com.anthropic.models.BetaMessageBatchRetrieveParams
import java.util.concurrent.CompletableFuture

class BatchServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BatchServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
    }

    private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

    private val createHandler: Handler<BetaMessageBatch> =
        jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Send a batch of Message creation requests.
     *
     * The Message Batches API can be used to process multiple Messages API requests at once. Once a
     * Message Batch is created, it begins processing immediately. Batches can take up to 24 hours
     * to complete.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    override fun create(
        params: BetaMessageBatchCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessageBatch> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages", "batches")
                .putQueryParam("beta", "true")
                .putAllHeaders(DEFAULT_HEADERS)
                .body(json(clientOptions.jsonMapper, params._body()))
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
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

    private val retrieveHandler: Handler<BetaMessageBatch> =
        jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * This endpoint is idempotent and can be used to poll for Message Batch completion. To access
     * the results of a Message Batch, make a request to the `results_url` field in the response.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    override fun retrieve(
        params: BetaMessageBatchRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessageBatch> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                .putQueryParam("beta", "true")
                .putAllHeaders(DEFAULT_HEADERS)
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
            }
    }

    private val listHandler: Handler<BetaMessageBatchListPageAsync.Response> =
        jsonHandler<BetaMessageBatchListPageAsync.Response>(clientOptions.jsonMapper)
            .withErrorHandler(errorHandler)

    /**
     * List all Message Batches within a Workspace. Most recently created batches are returned
     * first.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    override fun list(
        params: BetaMessageBatchListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessageBatchListPageAsync> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "messages", "batches")
                .putQueryParam("beta", "true")
                .putAllHeaders(DEFAULT_HEADERS)
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
                    .let { BetaMessageBatchListPageAsync.of(this, params, it) }
            }
    }

    private val deleteHandler: Handler<BetaDeletedMessageBatch> =
        jsonHandler<BetaDeletedMessageBatch>(clientOptions.jsonMapper)
            .withErrorHandler(errorHandler)

    /**
     * Delete a Message Batch.
     *
     * Message Batches can only be deleted once they've finished processing. If you'd like to delete
     * an in-progress batch, you must first cancel it.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    override fun delete(
        params: BetaMessageBatchDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedMessageBatch> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                .putQueryParam("beta", "true")
                .putAllHeaders(DEFAULT_HEADERS)
                .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
            }
    }

    private val cancelHandler: Handler<BetaMessageBatch> =
        jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Batches may be canceled any time before processing ends. Once cancellation is initiated, the
     * batch enters a `canceling` state, at which time the system may complete any in-progress,
     * non-interruptible requests before finalizing cancellation.
     *
     * The number of canceled requests is specified in `request_counts`. To determine which requests
     * were canceled, check the individual results within the batch. Note that cancellation may not
     * result in any canceled requests if they were non-interruptible.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    override fun cancel(
        params: BetaMessageBatchCancelParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessageBatch> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "cancel")
                .putQueryParam("beta", "true")
                .putAllHeaders(DEFAULT_HEADERS)
                .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .use { cancelHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                            it.validate()
                        }
                    }
            }
    }

    private val resultsStreamingHandler:
        Handler<StreamResponse<BetaMessageBatchIndividualResponse>> =
        jsonlHandler<BetaMessageBatchIndividualResponse>(clientOptions.jsonMapper)
            .withErrorHandler(errorHandler)

    /**
     * Streams the results of a Message Batch as a `.jsonl` file.
     *
     * Each line in the file is a JSON object containing the result of a single request in the
     * Message Batch. Results are not guaranteed to be in the same order as requests. Use the
     * `custom_id` field to match results to requests.
     *
     * Learn more about the Message Batches API in our
     * [user guide](/en/docs/build-with-claude/batch-processing)
     */
    override fun resultsStreaming(
        params: BetaMessageBatchResultsParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaMessageBatchIndividualResponse> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "results")
                .putQueryParam("beta", "true")
                .putAllHeaders(DEFAULT_HEADERS)
                .build()
                .prepareAsync(clientOptions, params)
        return request
            .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
            .thenApply { response ->
                response
                    .let { resultsStreamingHandler.handle(it) }
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
