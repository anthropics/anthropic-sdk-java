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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.plugins.BetaDeletedPlugin
import com.anthropic.models.beta.organization.plugins.BetaPlugin
import com.anthropic.models.beta.organization.plugins.PluginCreateParams
import com.anthropic.models.beta.organization.plugins.PluginDeleteParams
import com.anthropic.models.beta.organization.plugins.PluginListPage
import com.anthropic.models.beta.organization.plugins.PluginListPageResponse
import com.anthropic.models.beta.organization.plugins.PluginListParams
import com.anthropic.models.beta.organization.plugins.PluginRetrieveParams
import com.anthropic.models.beta.organization.plugins.PluginUpdateParams
import com.anthropic.services.blocking.beta.organization.plugins.InstallationSettingService
import com.anthropic.services.blocking.beta.organization.plugins.InstallationSettingServiceImpl
import com.anthropic.services.blocking.beta.organization.plugins.ShareService
import com.anthropic.services.blocking.beta.organization.plugins.ShareServiceImpl
import com.anthropic.services.blocking.beta.organization.plugins.VersionService
import com.anthropic.services.blocking.beta.organization.plugins.VersionServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class PluginServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    PluginService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "ce-plugins-2026-09-01").build()
    }

    private val withRawResponse: PluginService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val versions: VersionService by lazy { VersionServiceImpl(clientOptions) }

    private val installationSettings: InstallationSettingService by lazy {
        InstallationSettingServiceImpl(clientOptions)
    }

    private val shares: ShareService by lazy { ShareServiceImpl(clientOptions) }

    override fun withRawResponse(): PluginService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginService =
        PluginServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun versions(): VersionService = versions

    override fun installationSettings(): InstallationSettingService = installationSettings

    override fun shares(): ShareService = shares

    override fun create(params: PluginCreateParams, requestOptions: RequestOptions): BetaPlugin =
        // post /v1/organizations/plugins?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: PluginRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaPlugin =
        // get /v1/organizations/plugins/{plugin_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(params: PluginUpdateParams, requestOptions: RequestOptions): BetaPlugin =
        // post /v1/organizations/plugins/{plugin_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: PluginListParams, requestOptions: RequestOptions): PluginListPage =
        // get /v1/organizations/plugins?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: PluginDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedPlugin =
        // delete /v1/organizations/plugins/{plugin_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        PluginService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val versions: VersionService.WithRawResponse by lazy {
            VersionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val installationSettings: InstallationSettingService.WithRawResponse by lazy {
            InstallationSettingServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val shares: ShareService.WithRawResponse by lazy {
            ShareServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): PluginService.WithRawResponse =
            PluginServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun versions(): VersionService.WithRawResponse = versions

        override fun installationSettings(): InstallationSettingService.WithRawResponse =
            installationSettings

        override fun shares(): ShareService.WithRawResponse = shares

        private val createHandler: Handler<BetaPlugin> =
            jsonHandler<BetaPlugin>(clientOptions.jsonMapper)

        override fun create(
            params: PluginCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPlugin> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
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

        private val retrieveHandler: Handler<BetaPlugin> =
            jsonHandler<BetaPlugin>(clientOptions.jsonMapper)

        override fun retrieve(
            params: PluginRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPlugin> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("pluginId", params.pluginId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
                    .addPathParam("pluginId", params._pathParam(0))
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

        private val updateHandler: Handler<BetaPlugin> =
            jsonHandler<BetaPlugin>(clientOptions.jsonMapper)

        override fun update(
            params: PluginUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPlugin> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("pluginId", params.pluginId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
                    .addPathParam("pluginId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<PluginListPageResponse> =
            jsonHandler<PluginListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: PluginListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PluginListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
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
                        PluginListPage.builder()
                            .service(PluginServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<BetaDeletedPlugin> =
            jsonHandler<BetaDeletedPlugin>(clientOptions.jsonMapper)

        override fun delete(
            params: PluginDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedPlugin> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("pluginId", params.pluginId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
                    .addPathParam("pluginId", params._pathParam(0))
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
    }
}
