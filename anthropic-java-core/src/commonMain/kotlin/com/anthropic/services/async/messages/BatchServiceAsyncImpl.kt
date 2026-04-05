// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.messages

import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.handlers.errorBodyHandler
import kotlinx.kmp.util.core.handlers.errorHandler
import kotlinx.kmp.util.core.handlers.jsonHandler
import kotlinx.kmp.util.core.handlers.jsonlHandler
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
import com.anthropic.models.messages.batches.BatchCancelParams
import com.anthropic.models.messages.batches.BatchCreateParams
import com.anthropic.models.messages.batches.BatchDeleteParams
import com.anthropic.models.messages.batches.BatchListPageAsync
import com.anthropic.models.messages.batches.BatchListPageResponse
import com.anthropic.models.messages.batches.BatchListParams
import com.anthropic.models.messages.batches.BatchResultsParams
import com.anthropic.models.messages.batches.BatchRetrieveParams
import com.anthropic.models.messages.batches.DeletedMessageBatch
import com.anthropic.models.messages.batches.MessageBatch
import com.anthropic.models.messages.batches.MessageBatchIndividualResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class BatchServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BatchServiceAsync {

    private val withRawResponse: BatchServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): BatchServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BatchServiceAsync =
        BatchServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun create(
        params: BatchCreateParams,
        requestOptions: RequestOptions,
    ): MessageBatch =
        // post /v1/messages/batches
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: BatchRetrieveParams,
        requestOptions: RequestOptions,
    ): MessageBatch =
        // get /v1/messages/batches/{message_batch_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun list(
        params: BatchListParams,
        requestOptions: RequestOptions,
    ): BatchListPageAsync =
        // get /v1/messages/batches
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(
        params: BatchDeleteParams,
        requestOptions: RequestOptions,
    ): DeletedMessageBatch =
        // delete /v1/messages/batches/{message_batch_id}
        withRawResponse().delete(params, requestOptions).parse()

    override suspend fun cancel(
        params: BatchCancelParams,
        requestOptions: RequestOptions,
    ): MessageBatch =
        // post /v1/messages/batches/{message_batch_id}/cancel
        withRawResponse().cancel(params, requestOptions).parse()

    override suspend fun resultsStreaming(
        params: BatchResultsParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<MessageBatchIndividualResponse> =
        // get /v1/messages/batches/{message_batch_id}/results
        withRawResponse().resultsStreaming(params, requestOptions).parse()
            .toAsync(clientOptions.streamHandlerExecutor)

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BatchServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BatchServiceAsync.WithRawResponse =
            BatchServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper)

        override suspend fun create(
            params: BatchCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
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

        private val retrieveHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: BatchRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatch> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val listHandler: Handler<BatchListPageResponse> =
            jsonHandler<BatchListPageResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: BatchListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BatchListPageAsync> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches")
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                BatchListPageAsync.builder()
                                    .service(BatchServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
        }

        private val deleteHandler: Handler<DeletedMessageBatch> =
            jsonHandler<DeletedMessageBatch>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: BatchDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedMessageBatch> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { deleteHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val cancelHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper)

        override suspend fun cancel(
            params: BatchCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatch> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0), "cancel")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { cancelHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val resultsStreamingHandler:
            Handler<StreamResponse<MessageBatchIndividualResponse>> =
            jsonlHandler<MessageBatchIndividualResponse>(clientOptions.jsonMapper)

        override suspend fun resultsStreaming(
            params: BatchResultsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0), "results")
                    .putHeader("Accept", "application/x-jsonl")
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
