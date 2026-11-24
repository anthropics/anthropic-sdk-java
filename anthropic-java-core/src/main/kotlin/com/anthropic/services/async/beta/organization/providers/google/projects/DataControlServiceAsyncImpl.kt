package com.anthropic.services.async.beta.organization.providers.google.projects

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
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectDataControls
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class DataControlServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    DataControlServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder()
                .put("anthropic-beta", "google-managed-data-controls-2026-09-01")
                .build()
    }

    private val withRawResponse: DataControlServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): DataControlServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): DataControlServiceAsync =
        DataControlServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: DataControlRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        // get /v1/organizations/providers/google/projects/{project_number}/data_controls
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: DataControlUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        // post /v1/organizations/providers/google/projects/{project_number}/data_controls
        withRawResponse().update(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        DataControlServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DataControlServiceAsync.WithRawResponse =
            DataControlServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaGoogleManagedProjectDataControls> =
            jsonHandler<BetaGoogleManagedProjectDataControls>(clientOptions.jsonMapper)

        override fun retrieve(
            params: DataControlRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("projectNumber", params.projectNumber().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "providers", "google", "projects")
                    .addPathParam("projectNumber", params._pathParam(0))
                    .addPathSegments("data_controls")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
        }

        private val updateHandler: Handler<BetaGoogleManagedProjectDataControls> =
            jsonHandler<BetaGoogleManagedProjectDataControls>(clientOptions.jsonMapper)

        override fun update(
            params: DataControlUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("projectNumber", params.projectNumber().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "providers", "google", "projects")
                    .addPathParam("projectNumber", params._pathParam(0))
                    .addPathSegments("data_controls")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
        }
    }
}
