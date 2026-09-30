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
import com.anthropic.models.beta.organization.analytics.usagereport.UsageReportListPage
import com.anthropic.models.beta.organization.analytics.usagereport.UsageReportListPageResponse
import com.anthropic.models.beta.organization.analytics.usagereport.UsageReportListParams
import java.util.function.Consumer

class UsageReportServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    UsageReportService {

    private val withRawResponse: UsageReportService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): UsageReportService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): UsageReportService =
        UsageReportServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: UsageReportListParams,
        requestOptions: RequestOptions,
    ): UsageReportListPage =
        // get /v1/organizations/analytics/usage_report?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UsageReportService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UsageReportService.WithRawResponse =
            UsageReportServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<UsageReportListPageResponse> =
            jsonHandler<UsageReportListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: UsageReportListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UsageReportListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "usage_report")
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
                        UsageReportListPage.builder()
                            .service(UsageReportServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
