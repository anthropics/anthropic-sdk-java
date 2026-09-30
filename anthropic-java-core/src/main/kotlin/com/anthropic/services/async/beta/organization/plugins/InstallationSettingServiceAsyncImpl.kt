package com.anthropic.services.async.beta.organization.plugins

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
import com.anthropic.models.beta.organization.plugins.installationsettings.BetaDeletedPluginInstallationSetting
import com.anthropic.models.beta.organization.plugins.installationsettings.BetaPluginInstallationSetting
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListPageAsync
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListPageResponse
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingListParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingRemoveParams
import com.anthropic.models.beta.organization.plugins.installationsettings.InstallationSettingSetParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class InstallationSettingServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) : InstallationSettingServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "ce-plugins-2026-09-01").build()
    }

    private val withRawResponse: InstallationSettingServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): InstallationSettingServiceAsync.WithRawResponse =
        withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): InstallationSettingServiceAsync =
        InstallationSettingServiceAsyncImpl(
            clientOptions.toBuilder().apply(modifier::accept).build()
        )

    override fun list(
        params: InstallationSettingListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<InstallationSettingListPageAsync> =
        // get /v1/organizations/plugins/{plugin_id}/installation_settings?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun remove(
        params: InstallationSettingRemoveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedPluginInstallationSetting> =
        // delete /v1/organizations/plugins/{plugin_id}/installation_settings/{target}?beta=true
        withRawResponse().remove(params, requestOptions).thenApply { it.parse() }

    override fun set(
        params: InstallationSettingSetParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaPluginInstallationSetting> =
        // post /v1/organizations/plugins/{plugin_id}/installation_settings/{target}?beta=true
        withRawResponse().set(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        InstallationSettingServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InstallationSettingServiceAsync.WithRawResponse =
            InstallationSettingServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<InstallationSettingListPageResponse> =
            jsonHandler<InstallationSettingListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: InstallationSettingListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<InstallationSettingListPageAsync>> {
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
                        "installation_settings",
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
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                InstallationSettingListPageAsync.builder()
                                    .service(InstallationSettingServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val removeHandler: Handler<BetaDeletedPluginInstallationSetting> =
            jsonHandler<BetaDeletedPluginInstallationSetting>(clientOptions.jsonMapper)

        override fun remove(
            params: InstallationSettingRemoveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedPluginInstallationSetting>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("target", params.target().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugins",
                        params._pathParam(0),
                        "installation_settings",
                        params._pathParam(1),
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
                            .use { removeHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val setHandler: Handler<BetaPluginInstallationSetting> =
            jsonHandler<BetaPluginInstallationSetting>(clientOptions.jsonMapper)

        override fun set(
            params: InstallationSettingSetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaPluginInstallationSetting>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("target", params.target().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "plugins",
                        params._pathParam(0),
                        "installation_settings",
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
}
