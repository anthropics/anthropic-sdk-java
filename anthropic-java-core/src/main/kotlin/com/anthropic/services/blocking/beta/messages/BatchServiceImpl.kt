// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.messages

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.jsonlHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.json
import com.anthropic.core.http.map
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.errors.AnthropicError
import com.anthropic.models.BetaDeletedMessageBatch
import com.anthropic.models.BetaMessageBatch
import com.anthropic.models.BetaMessageBatchCancelParams
import com.anthropic.models.BetaMessageBatchCreateParams
import com.anthropic.models.BetaMessageBatchDeleteParams
import com.anthropic.models.BetaMessageBatchIndividualResponse
import com.anthropic.models.BetaMessageBatchListPage
import com.anthropic.models.BetaMessageBatchListParams
import com.anthropic.models.BetaMessageBatchResultsParams
import com.anthropic.models.BetaMessageBatchRetrieveParams

class BatchServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    BatchService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
    }

    private val withRawResponse: BatchService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): BatchService.WithRawResponse = withRawResponse

    override fun create(
        params: BetaMessageBatchCreateParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        // post /v1/messages/batches?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: BetaMessageBatchRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        // get /v1/messages/batches/{message_batch_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: BetaMessageBatchListParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatchListPage =
        // get /v1/messages/batches?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: BetaMessageBatchDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedMessageBatch =
        // delete /v1/messages/batches/{message_batch_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun cancel(
        params: BetaMessageBatchCancelParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        // post /v1/messages/batches/{message_batch_id}/cancel?beta=true
        withRawResponse().cancel(params, requestOptions).parse()

    override fun resultsStreaming(
        params: BetaMessageBatchResultsParams,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        // get /v1/messages/batches/{message_batch_id}/results?beta=true
        withRawResponse().resultsStreaming(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BatchService.WithRawResponse {

        private val errorHandler: Handler<AnthropicError> = errorHandler(clientOptions.jsonMapper)

        private val createHandler: Handler<BetaMessageBatch> =
            jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun create(
            params: BetaMessageBatchCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "batches")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val retrieveHandler: Handler<BetaMessageBatch> =
            jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun retrieve(
            params: BetaMessageBatchRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val listHandler: Handler<BetaMessageBatchListPage.Response> =
            jsonHandler<BetaMessageBatchListPage.Response>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun list(
            params: BetaMessageBatchListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatchListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                    .let {
                        BetaMessageBatchListPage.of(BatchServiceImpl(clientOptions), params, it)
                    }
            }
        }

        private val deleteHandler: Handler<BetaDeletedMessageBatch> =
            jsonHandler<BetaDeletedMessageBatch>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun delete(
            params: BetaMessageBatchDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedMessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val cancelHandler: Handler<BetaMessageBatch> =
            jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun cancel(
            params: BetaMessageBatchCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "cancel")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
            Handler<StreamResponse<BetaMessageBatchIndividualResponse>> =
            jsonlHandler<BetaMessageBatchIndividualResponse>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun resultsStreaming(
            params: BetaMessageBatchResultsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .addPathSegments("v1", "messages", "batches", params.getPathParam(0), "results")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
