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
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListPage
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListPageResponse
import com.anthropic.models.beta.organization.analytics.usercostreport.UserCostReportListParams
import java.util.function.Consumer

class UserCostReportServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    UserCostReportService {

    private val withRawResponse: UserCostReportService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): UserCostReportService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserCostReportService =
        UserCostReportServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: UserCostReportListParams,
        requestOptions: RequestOptions,
    ): UserCostReportListPage =
        // get /v1/organizations/analytics/user_cost_report?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UserCostReportService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserCostReportService.WithRawResponse =
            UserCostReportServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<UserCostReportListPageResponse> =
            jsonHandler<UserCostReportListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: UserCostReportListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserCostReportListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "user_cost_report")
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
                        UserCostReportListPage.builder()
                            .service(UserCostReportServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
