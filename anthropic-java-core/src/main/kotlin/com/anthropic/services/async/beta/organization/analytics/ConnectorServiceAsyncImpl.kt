package com.anthropic.services.async.beta.organization.analytics

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
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListPageAsync
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListPageResponse
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class ConnectorServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ConnectorServiceAsync {

    private val withRawResponse: ConnectorServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ConnectorServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ConnectorServiceAsync =
        ConnectorServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: ConnectorListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ConnectorListPageAsync> =
        // get /v1/organizations/analytics/connectors?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ConnectorServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ConnectorServiceAsync.WithRawResponse =
            ConnectorServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<ConnectorListPageResponse> =
            jsonHandler<ConnectorListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ConnectorListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ConnectorListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "connectors")
                    .putQueryParam("beta", "true")
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
                                ConnectorListPageAsync.builder()
                                    .service(ConnectorServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }
    }
}
