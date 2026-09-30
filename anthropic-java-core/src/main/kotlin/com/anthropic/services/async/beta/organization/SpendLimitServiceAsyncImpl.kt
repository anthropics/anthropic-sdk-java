package com.anthropic.services.async.beta.organization

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
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimit
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteResponse
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListPageAsync
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListPageResponse
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitRetrieveParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitSetParams
import com.anthropic.services.async.beta.organization.spendlimits.EffectiveServiceAsync
import com.anthropic.services.async.beta.organization.spendlimits.EffectiveServiceAsyncImpl
import com.anthropic.services.async.beta.organization.spendlimits.IncreaseRequestServiceAsync
import com.anthropic.services.async.beta.organization.spendlimits.IncreaseRequestServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SpendLimitServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SpendLimitServiceAsync {

    private val withRawResponse: SpendLimitServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val effective: EffectiveServiceAsync by lazy {
        EffectiveServiceAsyncImpl(clientOptions)
    }

    private val increaseRequests: IncreaseRequestServiceAsync by lazy {
        IncreaseRequestServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): SpendLimitServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitServiceAsync =
        SpendLimitServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun effective(): EffectiveServiceAsync = effective

    override fun increaseRequests(): IncreaseRequestServiceAsync = increaseRequests

    override fun retrieve(
        params: SpendLimitRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSpendLimit> =
        // get /v1/organizations/spend_limits/{spend_limit_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: SpendLimitListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SpendLimitListPageAsync> =
        // get /v1/organizations/spend_limits?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: SpendLimitDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SpendLimitDeleteResponse> =
        // delete /v1/organizations/spend_limits/{spend_limit_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun set(
        params: SpendLimitSetParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSpendLimit> =
        // post /v1/organizations/spend_limits?beta=true
        withRawResponse().set(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SpendLimitServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val effective: EffectiveServiceAsync.WithRawResponse by lazy {
            EffectiveServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val increaseRequests: IncreaseRequestServiceAsync.WithRawResponse by lazy {
            IncreaseRequestServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitServiceAsync.WithRawResponse =
            SpendLimitServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun effective(): EffectiveServiceAsync.WithRawResponse = effective

        override fun increaseRequests(): IncreaseRequestServiceAsync.WithRawResponse =
            increaseRequests

        private val retrieveHandler: Handler<BetaSpendLimit> =
            jsonHandler<BetaSpendLimit>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SpendLimitRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("spendLimitId", params.spendLimitId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<SpendLimitListPageResponse> =
            jsonHandler<SpendLimitListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SpendLimitListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SpendLimitListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits")
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
                                SpendLimitListPageAsync.builder()
                                    .service(SpendLimitServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<SpendLimitDeleteResponse> =
            jsonHandler<SpendLimitDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: SpendLimitDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("spendLimitId", params.spendLimitId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { deleteHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val setHandler: Handler<BetaSpendLimit> =
            jsonHandler<BetaSpendLimit>(clientOptions.jsonMapper)

        override fun set(
            params: SpendLimitSetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits")
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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
}
