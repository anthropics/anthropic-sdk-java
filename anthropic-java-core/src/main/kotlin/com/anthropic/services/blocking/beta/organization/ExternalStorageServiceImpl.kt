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
import com.anthropic.models.beta.organization.externalstorage.BetaDeletedExternalStorage
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorage
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageActivateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageCreateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageDeleteParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListPage
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListPageResponse
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageRetrieveParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ExternalStorageServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ExternalStorageService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "efs-2026-10-01").build()
    }

    private val withRawResponse: ExternalStorageService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ExternalStorageService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalStorageService =
        ExternalStorageServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: ExternalStorageCreateParams,
        requestOptions: RequestOptions,
    ): BetaExternalStorage =
        // post /v1/organizations/external_storage
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: ExternalStorageRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaExternalStorage =
        // get /v1/organizations/external_storage/{external_storage_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: ExternalStorageUpdateParams,
        requestOptions: RequestOptions,
    ): BetaExternalStorage =
        // post /v1/organizations/external_storage/{external_storage_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: ExternalStorageListParams,
        requestOptions: RequestOptions,
    ): ExternalStorageListPage =
        // get /v1/organizations/external_storage
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: ExternalStorageDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedExternalStorage =
        // delete /v1/organizations/external_storage/{external_storage_id}
        withRawResponse().delete(params, requestOptions).parse()

    override fun activate(
        params: ExternalStorageActivateParams,
        requestOptions: RequestOptions,
    ): BetaExternalStorage =
        // post /v1/organizations/external_storage/{external_storage_id}/activate
        withRawResponse().activate(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ExternalStorageService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExternalStorageService.WithRawResponse =
            ExternalStorageServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaExternalStorage> =
            jsonHandler<BetaExternalStorage>(clientOptions.jsonMapper)

        override fun create(
            params: ExternalStorageCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalStorage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_storage")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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
                                "anthropic.organization.external_storage.id",
                                it._id(),
                            )
                        }
                }
            }
        }

        private val retrieveHandler: Handler<BetaExternalStorage> =
            jsonHandler<BetaExternalStorage>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ExternalStorageRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalStorage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalStorageId", params.externalStorageId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_storage")
                    .addPathParam("externalStorageId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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

        private val updateHandler: Handler<BetaExternalStorage> =
            jsonHandler<BetaExternalStorage>(clientOptions.jsonMapper)

        override fun update(
            params: ExternalStorageUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalStorage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalStorageId", params.externalStorageId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_storage")
                    .addPathParam("externalStorageId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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

        private val listHandler: Handler<ExternalStorageListPageResponse> =
            jsonHandler<ExternalStorageListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ExternalStorageListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExternalStorageListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_storage")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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
                            ExternalStorageListPage.builder()
                                .service(ExternalStorageServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val deleteHandler: Handler<BetaDeletedExternalStorage> =
            jsonHandler<BetaDeletedExternalStorage>(clientOptions.jsonMapper)

        override fun delete(
            params: ExternalStorageDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedExternalStorage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalStorageId", params.externalStorageId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_storage")
                    .addPathParam("externalStorageId", params._pathParam(0))
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
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

        private val activateHandler: Handler<BetaExternalStorage> =
            jsonHandler<BetaExternalStorage>(clientOptions.jsonMapper)

        override fun activate(
            params: ExternalStorageActivateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalStorage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalStorageId", params.externalStorageId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_storage")
                    .addPathParam("externalStorageId", params._pathParam(0))
                    .addPathSegments("activate")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { activateHandler.handle(it) }
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
