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
import com.anthropic.core.http.multipartFormData
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.pluginmarketplaces.BetaPluginMarketplace
import com.anthropic.models.beta.organization.pluginmarketplaces.BetaPluginMarketplaceValidationReport
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListPage
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListPageResponse
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceRetrieveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceUpdateParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateArchiveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateRepositoryParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class PluginMarketplaceServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    PluginMarketplaceService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "ce-plugins-2026-09-01").build()
    }

    private val withRawResponse: PluginMarketplaceService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): PluginMarketplaceService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginMarketplaceService =
        PluginMarketplaceServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: PluginMarketplaceRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaPluginMarketplace =
        // get /v1/organizations/plugin_marketplaces/{marketplace_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: PluginMarketplaceUpdateParams,
        requestOptions: RequestOptions,
    ): BetaPluginMarketplace =
        // post /v1/organizations/plugin_marketplaces/{marketplace_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: PluginMarketplaceListParams,
        requestOptions: RequestOptions,
    ): PluginMarketplaceListPage =
        // get /v1/organizations/plugin_marketplaces
        withRawResponse().list(params, requestOptions).parse()

    override fun validateArchive(
        params: PluginMarketplaceValidateArchiveParams,
        requestOptions: RequestOptions,
    ): BetaPluginMarketplaceValidationReport =
        // post /v1/organizations/plugin_marketplaces/validate_archive
        withRawResponse().validateArchive(params, requestOptions).parse()

    override fun validateRepository(
        params: PluginMarketplaceValidateRepositoryParams,
        requestOptions: RequestOptions,
    ): BetaPluginMarketplaceValidationReport =
        // post /v1/organizations/plugin_marketplaces/validate_repository
        withRawResponse().validateRepository(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        PluginMarketplaceService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): PluginMarketplaceService.WithRawResponse =
            PluginMarketplaceServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaPluginMarketplace> =
            jsonHandler<BetaPluginMarketplace>(clientOptions.jsonMapper)

        override fun retrieve(
            params: PluginMarketplaceRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginMarketplace> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("marketplaceId", params.marketplaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugin_marketplaces")
                    .addPathParam("marketplaceId", params._pathParam(0))
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

        private val updateHandler: Handler<BetaPluginMarketplace> =
            jsonHandler<BetaPluginMarketplace>(clientOptions.jsonMapper)

        override fun update(
            params: PluginMarketplaceUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginMarketplace> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("marketplaceId", params.marketplaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugin_marketplaces")
                    .addPathParam("marketplaceId", params._pathParam(0))
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

        private val listHandler: Handler<PluginMarketplaceListPageResponse> =
            jsonHandler<PluginMarketplaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: PluginMarketplaceListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PluginMarketplaceListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugin_marketplaces")
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
                            PluginMarketplaceListPage.builder()
                                .service(PluginMarketplaceServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val validateArchiveHandler: Handler<BetaPluginMarketplaceValidationReport> =
            jsonHandler<BetaPluginMarketplaceValidationReport>(clientOptions.jsonMapper)

        override fun validateArchive(
            params: PluginMarketplaceValidateArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginMarketplaceValidationReport> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugin_marketplaces",
                        "validate_archive",
                    )
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
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
                        .use { validateArchiveHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val validateRepositoryHandler: Handler<BetaPluginMarketplaceValidationReport> =
            jsonHandler<BetaPluginMarketplaceValidationReport>(clientOptions.jsonMapper)

        override fun validateRepository(
            params: PluginMarketplaceValidateRepositoryParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginMarketplaceValidationReport> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugin_marketplaces",
                        "validate_repository",
                    )
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
                        .use { validateRepositoryHandler.handle(it) }
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
