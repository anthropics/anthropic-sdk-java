package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimit
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteResponse
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListPage
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListPageResponse
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitRetrieveParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitSetParams
import com.anthropic.services.blocking.beta.organization.spendlimits.EffectiveService
import com.anthropic.services.blocking.beta.organization.spendlimits.EffectiveServiceImpl
import com.anthropic.services.blocking.beta.organization.spendlimits.IncreaseRequestService
import com.anthropic.services.blocking.beta.organization.spendlimits.IncreaseRequestServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SpendLimitServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    SpendLimitService {

    private val withRawResponse: SpendLimitService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val effective: EffectiveService by lazy { EffectiveServiceImpl(clientOptions) }

    private val increaseRequests: IncreaseRequestService by lazy {
        IncreaseRequestServiceImpl(clientOptions)
    }

    override fun withRawResponse(): SpendLimitService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitService =
        SpendLimitServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun effective(): EffectiveService = effective

    override fun increaseRequests(): IncreaseRequestService = increaseRequests

    override fun retrieve(
        params: SpendLimitRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaSpendLimit =
        // get /v1/organizations/spend_limits/{spend_limit_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: SpendLimitListParams,
        requestOptions: RequestOptions,
    ): SpendLimitListPage =
        // get /v1/organizations/spend_limits?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: SpendLimitDeleteParams,
        requestOptions: RequestOptions,
    ): SpendLimitDeleteResponse =
        // delete /v1/organizations/spend_limits/{spend_limit_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun set(params: SpendLimitSetParams, requestOptions: RequestOptions): BetaSpendLimit =
        // post /v1/organizations/spend_limits?beta=true
        withRawResponse().set(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SpendLimitService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val effective: EffectiveService.WithRawResponse by lazy {
            EffectiveServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val increaseRequests: IncreaseRequestService.WithRawResponse by lazy {
            IncreaseRequestServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitService.WithRawResponse =
            SpendLimitServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun effective(): EffectiveService.WithRawResponse = effective

        override fun increaseRequests(): IncreaseRequestService.WithRawResponse = increaseRequests

        private val retrieveHandler: Handler<BetaSpendLimit> =
            jsonHandler<BetaSpendLimit>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SpendLimitRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimit> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("spendLimitId", params.spendLimitId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits")
                    .addPathParam("spendLimitId", params._pathParam(0))
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

        private val listHandler: Handler<SpendLimitListPageResponse> =
            jsonHandler<SpendLimitListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SpendLimitListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SpendLimitListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits")
                    .putQueryParam("beta", "true")
                    .replaceHeaders("anthropic-beta", "spend-limit-reads-2026-09-26")
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
                        SpendLimitListPage.builder()
                            .service(SpendLimitServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<SpendLimitDeleteResponse> =
            jsonHandler<SpendLimitDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: SpendLimitDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SpendLimitDeleteResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("spendLimitId", params.spendLimitId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits")
                    .addPathParam("spendLimitId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val setHandler: Handler<BetaSpendLimit> =
            jsonHandler<BetaSpendLimit>(clientOptions.jsonMapper)

        override fun set(
            params: SpendLimitSetParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimit> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits")
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
