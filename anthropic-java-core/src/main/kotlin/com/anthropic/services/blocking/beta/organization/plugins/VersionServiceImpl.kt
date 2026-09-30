package com.anthropic.services.blocking.beta.organization.plugins

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
import com.anthropic.core.http.multipartFormData
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.plugins.versions.BetaPluginVersion
import com.anthropic.models.beta.organization.plugins.versions.VersionCreateParams
import com.anthropic.models.beta.organization.plugins.versions.VersionDownloadParams
import com.anthropic.models.beta.organization.plugins.versions.VersionListPage
import com.anthropic.models.beta.organization.plugins.versions.VersionListPageResponse
import com.anthropic.models.beta.organization.plugins.versions.VersionListParams
import com.anthropic.models.beta.organization.plugins.versions.VersionRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class VersionServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    VersionService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "ce-plugins-2026-09-01").build()
    }

    private val withRawResponse: VersionService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): VersionService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionService =
        VersionServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions,
    ): BetaPluginVersion =
        // post /v1/organizations/plugins/{plugin_id}/versions?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaPluginVersion =
        // get /v1/organizations/plugins/{plugin_id}/versions/{version}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: VersionListParams, requestOptions: RequestOptions): VersionListPage =
        // get /v1/organizations/plugins/{plugin_id}/versions?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun download(
        params: VersionDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse =
        // get /v1/organizations/plugins/{plugin_id}/versions/{version}/content?beta=true
        withRawResponse().download(params, requestOptions)

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        VersionService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VersionService.WithRawResponse =
            VersionServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaPluginVersion> =
            jsonHandler<BetaPluginVersion>(clientOptions.jsonMapper)

        override fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginVersion> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("pluginId", params.pluginId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugins",
                        params._pathParam(0),
                        "versions",
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
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

        private val retrieveHandler: Handler<BetaPluginVersion> =
            jsonHandler<BetaPluginVersion>(clientOptions.jsonMapper)

        override fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginVersion> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("version", params.version().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugins",
                        params._pathParam(0),
                        "versions",
                        params._pathParam(1),
                    )
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

        private val listHandler: Handler<VersionListPageResponse> =
            jsonHandler<VersionListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: VersionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("pluginId", params.pluginId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugins",
                        params._pathParam(0),
                        "versions",
                    )
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
                        VersionListPage.builder()
                            .service(VersionServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        override fun download(
            params: VersionDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("version", params.version().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugins",
                        params._pathParam(0),
                        "versions",
                        params._pathParam(1),
                        "content",
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .putHeader("Accept", "application/binary")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response)
        }
    }
}
