// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.environments

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.environments.work.BetaSelfHostedWork
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkHeartbeatResponse
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkListResponse
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkQueueStats
import com.anthropic.models.beta.environments.work.WorkAckParams
import com.anthropic.models.beta.environments.work.WorkHeartbeatParams
import com.anthropic.models.beta.environments.work.WorkListPageAsync
import com.anthropic.models.beta.environments.work.WorkListParams
import com.anthropic.models.beta.environments.work.WorkPollParams
import com.anthropic.models.beta.environments.work.WorkRetrieveParams
import com.anthropic.models.beta.environments.work.WorkStatsParams
import com.anthropic.models.beta.environments.work.WorkStopParams
import com.anthropic.models.beta.environments.work.WorkUpdateParams
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: WorkServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WorkServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkServiceAsync =
        WorkServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: WorkRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWork> =
        // get /v1/environments/{environment_id}/work/{work_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: WorkUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWork> =
        // post /v1/environments/{environment_id}/work/{work_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: WorkListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkListPageAsync> =
        // get /v1/environments/{environment_id}/work?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun ack(
        params: WorkAckParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWork> =
        // post /v1/environments/{environment_id}/work/{work_id}/ack?beta=true
        withRawResponse().ack(params, requestOptions).thenApply { it.parse() }

    override fun heartbeat(
        params: WorkHeartbeatParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWorkHeartbeatResponse> =
        // post /v1/environments/{environment_id}/work/{work_id}/heartbeat?beta=true
        withRawResponse().heartbeat(params, requestOptions).thenApply { it.parse() }

    override fun poll(
        params: WorkPollParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Optional<BetaSelfHostedWork>> =
        // get /v1/environments/{environment_id}/work/poll?beta=true
        withRawResponse().poll(params, requestOptions).thenApply { it.parse() }

    override fun stats(
        params: WorkStatsParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWorkQueueStats> =
        // get /v1/environments/{environment_id}/work/stats?beta=true
        withRawResponse().stats(params, requestOptions).thenApply { it.parse() }

    override fun stop(
        params: WorkStopParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSelfHostedWork> =
        // post /v1/environments/{environment_id}/work/{work_id}/stop?beta=true
        withRawResponse().stop(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkServiceAsync.WithRawResponse =
            WorkServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaSelfHostedWork> =
            jsonHandler<BetaSelfHostedWork>(clientOptions.jsonMapper)

        override fun retrieve(
            params: WorkRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workId", params.workId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "environments",
                        params._pathParam(0),
                        "work",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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

        private val updateHandler: Handler<BetaSelfHostedWork> =
            jsonHandler<BetaSelfHostedWork>(clientOptions.jsonMapper)

        override fun update(
            params: WorkUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workId", params.workId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "environments",
                        params._pathParam(0),
                        "work",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { updateHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<BetaSelfHostedWorkListResponse> =
            jsonHandler<BetaSelfHostedWorkListResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0), "work")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                WorkListPageAsync.builder()
                                    .service(WorkServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val ackHandler: Handler<BetaSelfHostedWork> =
            jsonHandler<BetaSelfHostedWork>(clientOptions.jsonMapper)

        override fun ack(
            params: WorkAckParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workId", params.workId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "environments",
                        params._pathParam(0),
                        "work",
                        params._pathParam(1),
                        "ack",
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { ackHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val heartbeatHandler: Handler<BetaSelfHostedWorkHeartbeatResponse> =
            jsonHandler<BetaSelfHostedWorkHeartbeatResponse>(clientOptions.jsonMapper)

        override fun heartbeat(
            params: WorkHeartbeatParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkHeartbeatResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workId", params.workId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "environments",
                        params._pathParam(0),
                        "work",
                        params._pathParam(1),
                        "heartbeat",
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { heartbeatHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val pollHandler: Handler<Optional<BetaSelfHostedWork>> =
            jsonHandler<Optional<BetaSelfHostedWork>>(clientOptions.jsonMapper)

        override fun poll(
            params: WorkPollParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Optional<BetaSelfHostedWork>>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0), "work", "poll")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { pollHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.ifPresent { it.validate() }
                                }
                            }
                    }
                }
        }

        private val statsHandler: Handler<BetaSelfHostedWorkQueueStats> =
            jsonHandler<BetaSelfHostedWorkQueueStats>(clientOptions.jsonMapper)

        override fun stats(
            params: WorkStatsParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWorkQueueStats>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("environmentId", params.environmentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "environments", params._pathParam(0), "work", "stats")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { statsHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val stopHandler: Handler<BetaSelfHostedWork> =
            jsonHandler<BetaSelfHostedWork>(clientOptions.jsonMapper)

        override fun stop(
            params: WorkStopParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSelfHostedWork>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workId", params.workId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "environments",
                        params._pathParam(0),
                        "work",
                        params._pathParam(1),
                        "stop",
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { stopHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }
    }
}
