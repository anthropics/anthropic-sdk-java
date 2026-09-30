package com.anthropic.services.blocking.beta.organization.spendlimits

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.spendlimits.increaserequests.BetaSpendLimitIncreaseRequest
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestApproveParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestApproveResponse
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestDenyParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestListPage
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestListPageResponse
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestListParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class IncreaseRequestServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    IncreaseRequestService {

    private val withRawResponse: IncreaseRequestService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): IncreaseRequestService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): IncreaseRequestService =
        IncreaseRequestServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: IncreaseRequestRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaSpendLimitIncreaseRequest =
        // get
        // /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: IncreaseRequestListParams,
        requestOptions: RequestOptions,
    ): IncreaseRequestListPage =
        // get /v1/organizations/spend_limit_increase_requests?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun approve(
        params: IncreaseRequestApproveParams,
        requestOptions: RequestOptions,
    ): IncreaseRequestApproveResponse =
        // post
        // /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}/approve?beta=true
        withRawResponse().approve(params, requestOptions).parse()

    override fun deny(
        params: IncreaseRequestDenyParams,
        requestOptions: RequestOptions,
    ): BetaSpendLimitIncreaseRequest =
        // post
        // /v1/organizations/spend_limit_increase_requests/{spend_limit_increase_request_id}/deny?beta=true
        withRawResponse().deny(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        IncreaseRequestService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): IncreaseRequestService.WithRawResponse =
            IncreaseRequestServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaSpendLimitIncreaseRequest> =
            jsonHandler<BetaSpendLimitIncreaseRequest>(clientOptions.jsonMapper)

        override fun retrieve(
            params: IncreaseRequestRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired(
                "spendLimitIncreaseRequestId",
                params.spendLimitIncreaseRequestId().getOrNull(),
            )
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "spend_limit_increase_requests",
                        params._pathParam(0),
                    )
                    .putQueryParam("beta", "true")
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<IncreaseRequestListPageResponse> =
            jsonHandler<IncreaseRequestListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: IncreaseRequestListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<IncreaseRequestListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limit_increase_requests")
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
                        IncreaseRequestListPage.builder()
                            .service(IncreaseRequestServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val approveHandler: Handler<IncreaseRequestApproveResponse> =
            jsonHandler<IncreaseRequestApproveResponse>(clientOptions.jsonMapper)

        override fun approve(
            params: IncreaseRequestApproveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<IncreaseRequestApproveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired(
                "spendLimitIncreaseRequestId",
                params.spendLimitIncreaseRequestId().getOrNull(),
            )
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "spend_limit_increase_requests",
                        params._pathParam(0),
                        "approve",
                    )
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { approveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val denyHandler: Handler<BetaSpendLimitIncreaseRequest> =
            jsonHandler<BetaSpendLimitIncreaseRequest>(clientOptions.jsonMapper)

        override fun deny(
            params: IncreaseRequestDenyParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimitIncreaseRequest> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired(
                "spendLimitIncreaseRequestId",
                params.spendLimitIncreaseRequestId().getOrNull(),
            )
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "spend_limit_increase_requests",
                        params._pathParam(0),
                        "deny",
                    )
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { denyHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
