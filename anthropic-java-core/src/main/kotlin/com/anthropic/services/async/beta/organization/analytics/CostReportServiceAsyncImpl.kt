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
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListPageAsync
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListPageResponse
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class CostReportServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CostReportServiceAsync {

    private val withRawResponse: CostReportServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CostReportServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CostReportServiceAsync =
        CostReportServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: CostReportListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CostReportListPageAsync> =
        // get /v1/organizations/analytics/cost_report?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CostReportServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CostReportServiceAsync.WithRawResponse =
            CostReportServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<CostReportListPageResponse> =
            jsonHandler<CostReportListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CostReportListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CostReportListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "cost_report")
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
                                CostReportListPageAsync.builder()
                                    .service(CostReportServiceAsyncImpl(clientOptions))
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
