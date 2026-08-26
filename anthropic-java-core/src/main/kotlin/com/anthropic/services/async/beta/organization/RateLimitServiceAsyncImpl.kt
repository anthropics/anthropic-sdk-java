// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

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
import com.anthropic.models.beta.organization.ratelimits.RateLimitListPageAsync
import com.anthropic.models.beta.organization.ratelimits.RateLimitListPageResponse
import com.anthropic.models.beta.organization.ratelimits.RateLimitListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class RateLimitServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RateLimitServiceAsync {

    private val withRawResponse: RateLimitServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RateLimitServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitServiceAsync =
        RateLimitServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: RateLimitListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RateLimitListPageAsync> =
        // get /v1/organizations/rate_limits?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RateLimitServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RateLimitServiceAsync.WithRawResponse =
            RateLimitServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<RateLimitListPageResponse> =
            jsonHandler<RateLimitListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RateLimitListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rate_limits")
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
                                RateLimitListPageAsync.builder()
                                    .service(RateLimitServiceAsyncImpl(clientOptions))
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
