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
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListPage
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListPageResponse
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListParams
import java.util.function.Consumer

class ConnectorServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ConnectorService {

    private val withRawResponse: ConnectorService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ConnectorService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConnectorService =
        ConnectorServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: ConnectorListParams,
        requestOptions: RequestOptions,
    ): ConnectorListPage =
        // get /v1/organizations/analytics/connectors?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ConnectorService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ConnectorService.WithRawResponse =
            ConnectorServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<ConnectorListPageResponse> =
            jsonHandler<ConnectorListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ConnectorListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ConnectorListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "connectors")
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
                        ConnectorListPage.builder()
                            .service(ConnectorServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
