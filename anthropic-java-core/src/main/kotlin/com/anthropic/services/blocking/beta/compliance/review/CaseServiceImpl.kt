package com.anthropic.services.blocking.beta.compliance.review

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewCase
import com.anthropic.models.beta.compliance.review.cases.CaseListPage
import com.anthropic.models.beta.compliance.review.cases.CaseListPageResponse
import com.anthropic.models.beta.compliance.review.cases.CaseListParams
import com.anthropic.models.beta.compliance.review.cases.CaseRespondParams
import com.anthropic.models.beta.compliance.review.cases.CaseRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CaseServiceImpl internal constructor(private val clientOptions: ClientOptions) : CaseService {

    private val withRawResponse: CaseService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CaseService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaseService =
        CaseServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: CaseRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaComplianceReviewCase =
        // get /v1/compliance/review/cases/{case_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: CaseListParams, requestOptions: RequestOptions): CaseListPage =
        // get /v1/compliance/review/cases
        withRawResponse().list(params, requestOptions).parse()

    override fun respond(
        params: CaseRespondParams,
        requestOptions: RequestOptions,
    ): BetaComplianceReviewCase =
        // post /v1/compliance/review/cases/{case_id}/respond
        withRawResponse().respond(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CaseService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CaseService.WithRawResponse =
            CaseServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaComplianceReviewCase> =
            jsonHandler<BetaComplianceReviewCase>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CaseRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaComplianceReviewCase> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("caseId", params.caseId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "cases")
                    .addPathParam("caseId", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { retrieveHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val listHandler: Handler<CaseListPageResponse> =
            jsonHandler<CaseListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CaseListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CaseListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "cases")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { listHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                        .let {
                            CaseListPage.builder()
                                .service(CaseServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val respondHandler: Handler<BetaComplianceReviewCase> =
            jsonHandler<BetaComplianceReviewCase>(clientOptions.jsonMapper)

        override fun respond(
            params: CaseRespondParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaComplianceReviewCase> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("caseId", params.caseId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "cases")
                    .addPathParam("caseId", params._pathParam(0))
                    .addPathSegments("respond")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { respondHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }
    }
}
