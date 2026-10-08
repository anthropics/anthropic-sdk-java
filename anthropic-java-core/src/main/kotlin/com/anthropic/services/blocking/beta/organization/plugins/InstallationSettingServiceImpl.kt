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
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.plugins.installationsettings.BetaDeletedPluginInstallationSetting
import com.anthropic.models.beta.organization.plugins.installationsettings.BetaPluginInstallationSetting
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListPage
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListPageResponse
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingRemoveParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingSetParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class InstallationSettingServiceImpl
internal constructor(private val clientOptions: ClientOptions) : InstallationSettingService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "ce-plugins-2026-09-01").build()
    }

    private val withRawResponse: InstallationSettingService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): InstallationSettingService.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): InstallationSettingService =
        InstallationSettingServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: InstallationSettingListParams,
        requestOptions: RequestOptions,
    ): InstallationSettingListPage =
        // get /v1/organizations/plugins/{plugin_id}/installation_settings?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun remove(
        params: InstallationSettingRemoveParams,
        requestOptions: RequestOptions,
    ): BetaDeletedPluginInstallationSetting =
        // delete /v1/organizations/plugins/{plugin_id}/installation_settings/{target}?beta=true
        withRawResponse().remove(params, requestOptions).parse()

    override fun set(
        params: InstallationSettingSetParams,
        requestOptions: RequestOptions,
    ): BetaPluginInstallationSetting =
        // post /v1/organizations/plugins/{plugin_id}/installation_settings/{target}?beta=true
        withRawResponse().set(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        InstallationSettingService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InstallationSettingService.WithRawResponse =
            InstallationSettingServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<InstallationSettingListPageResponse> =
            jsonHandler<InstallationSettingListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: InstallationSettingListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<InstallationSettingListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("pluginId", params.pluginId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
                    .addPathParam("pluginId", params._pathParam(0))
                    .addPathSegments("installation_settings")
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
                        InstallationSettingListPage.builder()
                            .service(InstallationSettingServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val removeHandler: Handler<BetaDeletedPluginInstallationSetting> =
            jsonHandler<BetaDeletedPluginInstallationSetting>(clientOptions.jsonMapper)

        override fun remove(
            params: InstallationSettingRemoveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedPluginInstallationSetting> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("target", params.target().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
                    .addPathParam("pluginId", params._pathParam(0))
                    .addPathSegments("installation_settings")
                    .addPathParam("target", params._pathParam(1))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { removeHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val setHandler: Handler<BetaPluginInstallationSetting> =
            jsonHandler<BetaPluginInstallationSetting>(clientOptions.jsonMapper)

        override fun set(
            params: InstallationSettingSetParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginInstallationSetting> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("target", params.target().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "plugins")
                    .addPathParam("pluginId", params._pathParam(0))
                    .addPathSegments("installation_settings")
                    .addPathParam("target", params._pathParam(1))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { setHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
