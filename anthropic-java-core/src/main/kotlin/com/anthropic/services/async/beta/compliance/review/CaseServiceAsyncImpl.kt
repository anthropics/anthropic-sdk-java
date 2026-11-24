package com.anthropic.services.async.beta.compliance.review

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewCase
import com.anthropic.models.beta.compliance.review.cases.CaseListPageAsync
import com.anthropic.models.beta.compliance.review.cases.CaseListPageResponse
import com.anthropic.models.beta.compliance.review.cases.CaseListParams
import com.anthropic.models.beta.compliance.review.cases.CaseRespondParams
import com.anthropic.models.beta.compliance.review.cases.CaseRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CaseServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CaseServiceAsync {

    private val withRawResponse: CaseServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CaseServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CaseServiceAsync =
        CaseServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: CaseRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaComplianceReviewCase> =
        // get /v1/compliance/review/cases/{case_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun list(
        params: CaseListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CaseListPageAsync> =
        // get /v1/compliance/review/cases
        withRawResponse().list(params, requestOptions).thenParse()

    override fun respond(
        params: CaseRespondParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaComplianceReviewCase> =
        // post /v1/compliance/review/cases/{case_id}/respond
        withRawResponse().respond(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CaseServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CaseServiceAsync.WithRawResponse =
            CaseServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaComplianceReviewCase> =
            jsonHandler<BetaComplianceReviewCase>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CaseRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
        }

        private val listHandler: Handler<CaseListPageResponse> =
            jsonHandler<CaseListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CaseListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CaseListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "cases")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { listHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .let {
                                    CaseListPageAsync.builder()
                                        .service(CaseServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val respondHandler: Handler<BetaComplianceReviewCase> =
            jsonHandler<BetaComplianceReviewCase>(clientOptions.jsonMapper)

        override fun respond(
            params: CaseRespondParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewCase>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync(
                        { client.executeAsync(it, requestOptions) },
                        clientOptions.completionExecutor,
                    )
                    .thenApply { response ->
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
}
