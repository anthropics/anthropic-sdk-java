// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.messages

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.jsonlHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.map
import com.anthropic.core.http.parseable
import com.anthropic.core.json
import com.anthropic.core.prepare
import com.anthropic.errors.AnthropicError
import com.anthropic.models.DeletedMessageBatch
import com.anthropic.models.MessageBatch
import com.anthropic.models.MessageBatchCancelParams
import com.anthropic.models.MessageBatchCreateParams
import com.anthropic.models.MessageBatchDeleteParams
import com.anthropic.models.MessageBatchIndividualResponse
import com.anthropic.models.MessageBatchListPage
import com.anthropic.models.MessageBatchListParams
import com.anthropic.models.MessageBatchResultsParams
import com.anthropic.models.MessageBatchRetrieveParams

class BatchServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    BatchService {

    private val withRawResponse: BatchService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): BatchService.WithRawResponse = withRawResponse

    override fun create(
        params: MessageBatchCreateParams,
        requestOptions: RequestOptions,
    ): MessageBatch =
        // post /v1/messages/batches
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: MessageBatchRetrieveParams,
        requestOptions: RequestOptions,
    ): MessageBatch =
        // get /v1/messages/batches/{message_batch_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: MessageBatchListParams,
        requestOptions: RequestOptions,
    ): MessageBatchListPage =
        // get /v1/messages/batches
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: MessageBatchDeleteParams,
        requestOptions: RequestOptions,
    ): DeletedMessageBatch =
        // delete /v1/messages/batches/{message_batch_id}
        withRawResponse().delete(params, requestOptions).parse()

    override fun cancel(
        params: MessageBatchCancelParams,
        requestOptions: RequestOptions,
    ): MessageBatch =
        // post /v1/messages/batches/{message_batch_id}/cancel
        withRawResponse().cancel(params, requestOptions).parse()

    override fun resultsStreaming(
        params: MessageBatchResultsParams,
        requestOptions: RequestOptions,
    ): StreamResponse<MessageBatchIndividualResponse> =
        // get /v1/messages/batches/{message_batch_id}/results
        withRawResponse().resultsStreaming(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BatchService.WithRawResponse {

        private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

        private val createHandler: Handler<MessageBatch> =
            jsonHandler<MessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun create(
            params: MessageBatchCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "batches")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
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
            jsonHandler<MessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun retrieve(
            params: MessageBatchRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<MessageBatchListPage.Response> =
            jsonHandler<MessageBatchListPage.Response>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun list(
            params: MessageBatchListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatchListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let { MessageBatchListPage.of(BatchServiceImpl(clientOptions), params, it) }
            }
        }

        private val deleteHandler: Handler<DeletedMessageBatch> =
            jsonHandler<DeletedMessageBatch>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun delete(
            params: MessageBatchDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedMessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
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
            jsonHandler<MessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun cancel(
            params: MessageBatchCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "cancel")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
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
                .withErrorHandler(errorHandler)

        override fun resultsStreaming(
            params: MessageBatchResultsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<MessageBatchIndividualResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "results")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
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
