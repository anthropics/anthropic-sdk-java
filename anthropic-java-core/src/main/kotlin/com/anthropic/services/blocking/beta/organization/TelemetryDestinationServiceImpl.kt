package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.telemetrydestinations.BetaDeletedTelemetryDestination
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestination
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestinationTestResponse
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationCreateParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationDeleteParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListPage
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListPageResponse
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationListParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationRetrieveParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationTestParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationUpdateParams
import com.anthropic.services.blocking.beta.organization.telemetrydestinations.WorkspaceService
import com.anthropic.services.blocking.beta.organization.telemetrydestinations.WorkspaceServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class TelemetryDestinationServiceImpl
internal constructor(private val clientOptions: ClientOptions) : TelemetryDestinationService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "telemetry-destinations-2026-08-11").build()
    }

    private val withRawResponse: TelemetryDestinationService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val workspaces: WorkspaceService by lazy { WorkspaceServiceImpl(clientOptions) }

    override fun withRawResponse(): TelemetryDestinationService.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): TelemetryDestinationService =
        TelemetryDestinationServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun workspaces(): WorkspaceService = workspaces

    override fun create(
        params: TelemetryDestinationCreateParams,
        requestOptions: RequestOptions,
    ): BetaTelemetryDestination =
        // post /v1/organizations/telemetry_destinations?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: TelemetryDestinationRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaTelemetryDestination =
        // get /v1/organizations/telemetry_destinations/{destination_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: TelemetryDestinationUpdateParams,
        requestOptions: RequestOptions,
    ): BetaTelemetryDestination =
        // post /v1/organizations/telemetry_destinations/{destination_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: TelemetryDestinationListParams,
        requestOptions: RequestOptions,
    ): TelemetryDestinationListPage =
        // get /v1/organizations/telemetry_destinations?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: TelemetryDestinationDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedTelemetryDestination =
        // delete /v1/organizations/telemetry_destinations/{destination_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun test(
        params: TelemetryDestinationTestParams,
        requestOptions: RequestOptions,
    ): BetaTelemetryDestinationTestResponse =
        // post /v1/organizations/telemetry_destinations/{destination_id}/test?beta=true
        withRawResponse().test(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        TelemetryDestinationService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val workspaces: WorkspaceService.WithRawResponse by lazy {
            WorkspaceServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TelemetryDestinationService.WithRawResponse =
            TelemetryDestinationServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun workspaces(): WorkspaceService.WithRawResponse = workspaces

        private val createHandler: Handler<BetaTelemetryDestination> =
            jsonHandler<BetaTelemetryDestination>(clientOptions.jsonMapper)

        override fun create(
            params: TelemetryDestinationCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestination> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { createHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                        .also {
                            client.recordCreatedId(
                                "anthropic.organization.telemetry_destination.id",
                                it._id(),
                            )
                        }
                }
            }
        }

        private val retrieveHandler: Handler<BetaTelemetryDestination> =
            jsonHandler<BetaTelemetryDestination>(clientOptions.jsonMapper)

        override fun retrieve(
            params: TelemetryDestinationRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestination> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("destinationId", params.destinationId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { retrieveHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val updateHandler: Handler<BetaTelemetryDestination> =
            jsonHandler<BetaTelemetryDestination>(clientOptions.jsonMapper)

        override fun update(
            params: TelemetryDestinationUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestination> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("destinationId", params.destinationId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { updateHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val listHandler: Handler<TelemetryDestinationListPageResponse> =
            jsonHandler<TelemetryDestinationListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: TelemetryDestinationListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TelemetryDestinationListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { listHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                        .let {
                            TelemetryDestinationListPage.builder()
                                .service(TelemetryDestinationServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val deleteHandler: Handler<BetaDeletedTelemetryDestination> =
            jsonHandler<BetaDeletedTelemetryDestination>(clientOptions.jsonMapper)

        override fun delete(
            params: TelemetryDestinationDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedTelemetryDestination> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("destinationId", params.destinationId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { deleteHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val testHandler: Handler<BetaTelemetryDestinationTestResponse> =
            jsonHandler<BetaTelemetryDestinationTestResponse>(clientOptions.jsonMapper)

        override fun test(
            params: TelemetryDestinationTestParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTelemetryDestinationTestResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("destinationId", params.destinationId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "telemetry_destinations")
                    .addPathParam("destinationId", params._pathParam(0))
                    .addPathSegments("test")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { testHandler.handle(it) }
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
