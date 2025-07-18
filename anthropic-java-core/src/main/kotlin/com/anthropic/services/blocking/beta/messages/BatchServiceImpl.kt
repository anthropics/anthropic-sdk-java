// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.messages

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.jsonlHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.json
import com.anthropic.core.http.map
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.messages.batches.BatchCancelParams
import com.anthropic.models.beta.messages.batches.BatchCreateParams
import com.anthropic.models.beta.messages.batches.BatchDeleteParams
import com.anthropic.models.beta.messages.batches.BatchListPage
import com.anthropic.models.beta.messages.batches.BatchListPageResponse
import com.anthropic.models.beta.messages.batches.BatchListParams
import com.anthropic.models.beta.messages.batches.BatchResultsParams
import com.anthropic.models.beta.messages.batches.BatchRetrieveParams
import com.anthropic.models.beta.messages.batches.BetaDeletedMessageBatch
import com.anthropic.models.beta.messages.batches.BetaMessageBatch
import com.anthropic.models.beta.messages.batches.BetaMessageBatchIndividualResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

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

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): BatchService =
        BatchServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: BatchCreateParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        // post /v1/messages/batches?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: BatchRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        // get /v1/messages/batches/{message_batch_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: BatchListParams, requestOptions: RequestOptions): BatchListPage =
        // get /v1/messages/batches?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: BatchDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedMessageBatch =
        // delete /v1/messages/batches/{message_batch_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun cancel(
        params: BatchCancelParams,
        requestOptions: RequestOptions,
    ): BetaMessageBatch =
        // post /v1/messages/batches/{message_batch_id}/cancel?beta=true
        withRawResponse().cancel(params, requestOptions).parse()

    override fun resultsStreaming(
        params: BatchResultsParams,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaMessageBatchIndividualResponse> =
        // get /v1/messages/batches/{message_batch_id}/results?beta=true
        withRawResponse().resultsStreaming(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BatchService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BatchService.WithRawResponse =
            BatchServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaMessageBatch> =
            jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper)

        override fun create(
            params: BatchCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        private val retrieveHandler: Handler<BetaMessageBatch> =
            jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper)

        override fun retrieve(
            params: BatchRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        override fun list(
            params: BatchListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BatchListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let {
                        BatchListPage.builder()
                            .service(BatchServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<BetaDeletedMessageBatch> =
            jsonHandler<BetaDeletedMessageBatch>(clientOptions.jsonMapper)

        override fun delete(
            params: BatchDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedMessageBatch> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        private val cancelHandler: Handler<BetaMessageBatch> =
            jsonHandler<BetaMessageBatch>(clientOptions.jsonMapper)

        override fun cancel(
            params: BatchCancelParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaMessageBatch> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0), "cancel")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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
            Handler<StreamResponse<BetaMessageBatchIndividualResponse>> =
            jsonlHandler<BetaMessageBatchIndividualResponse>(clientOptions.jsonMapper)

        override fun resultsStreaming(
            params: BatchResultsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<BetaMessageBatchIndividualResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("messageBatchId", params.messageBatchId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "messages", "batches", params._pathParam(0), "results")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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
