package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.analytics.plugins.PluginListPage
import com.anthropic.models.beta.organization.analytics.plugins.PluginListPageResponse
import com.anthropic.models.beta.organization.analytics.plugins.PluginListParams
import java.util.function.Consumer

class PluginServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    PluginService {

    private val withRawResponse: PluginService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): PluginService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginService =
        PluginServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(params: PluginListParams, requestOptions: RequestOptions): PluginListPage =
        // get /v1/organizations/analytics/plugins?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        PluginService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): PluginService.WithRawResponse =
            PluginServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

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
                    .addPathSegments("v1", "organizations", "analytics", "plugins")
                    .putQueryParam("beta", "true")
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
    }
}
