package com.anthropic.services.async.beta.organization.telemetrydestinations

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.BetaDeletedTelemetryDestinationWorkspace
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.BetaTelemetryDestinationWorkspace
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListPageAsync
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListPageResponse
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceRemoveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkspaceServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkspaceServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "telemetry-destinations-2026-08-11").build()
    }

    private val withRawResponse: WorkspaceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WorkspaceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceServiceAsync =
        WorkspaceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceListPageAsync> =
        // get /v1/organizations/telemetry_destinations/{destination_id}/workspaces?beta=true
        withRawResponse().list(params, requestOptions).thenParse()

    override fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTelemetryDestinationWorkspace> =
        // post
        // /v1/organizations/telemetry_destinations/{destination_id}/workspaces/{workspace_id}?beta=true
        withRawResponse().add(params, requestOptions).thenParse()

    override fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedTelemetryDestinationWorkspace> =
        // delete
        // /v1/organizations/telemetry_destinations/{destination_id}/workspaces/{workspace_id}?beta=true
        withRawResponse().remove(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkspaceServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceServiceAsync.WithRawResponse =
            WorkspaceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<WorkspaceListPageResponse> =
            jsonHandler<WorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("destinationId", params.destinationId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .addPathSegments("workspaces")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { listHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .let {
                                    WorkspaceListPageAsync.builder()
                                        .service(WorkspaceServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val addHandler: Handler<BetaTelemetryDestinationWorkspace> =
            jsonHandler<BetaTelemetryDestinationWorkspace>(clientOptions.jsonMapper)

        override fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTelemetryDestinationWorkspace>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .addPathSegments("workspaces")
                    .addPathParam("workspaceId", params._pathParam(1))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { addHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }

        private val removeHandler: Handler<BetaDeletedTelemetryDestinationWorkspace> =
            jsonHandler<BetaDeletedTelemetryDestinationWorkspace>(clientOptions.jsonMapper)

        override fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedTelemetryDestinationWorkspace>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .addPathSegments("workspaces")
                    .addPathParam("workspaceId", params._pathParam(1))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { removeHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }
    }
}
