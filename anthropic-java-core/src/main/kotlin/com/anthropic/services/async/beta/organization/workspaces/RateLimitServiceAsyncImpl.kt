package com.anthropic.services.async.beta.organization.workspaces

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
import com.anthropic.models.beta.organization.workspaces.ratelimits.BetaWorkspaceRateLimit
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListPageAsync
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListPageResponse
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListParams
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RateLimitServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RateLimitServiceAsync {

    private val withRawResponse: RateLimitServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RateLimitServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitServiceAsync =
        RateLimitServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun update(
        params: RateLimitUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaWorkspaceRateLimit> =
        // post /v1/organizations/workspaces/{workspace_id}/rate_limits/{rate_limit_id}
        withRawResponse().update(params, requestOptions).thenParse()

    override fun list(
        params: RateLimitListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RateLimitListPageAsync> =
        // get /v1/organizations/workspaces/{workspace_id}/rate_limits
        withRawResponse().list(params, requestOptions).thenParse()

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

        private val updateHandler: Handler<BetaWorkspaceRateLimit> =
            jsonHandler<BetaWorkspaceRateLimit>(clientOptions.jsonMapper)

        override fun update(
            params: RateLimitUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaWorkspaceRateLimit>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rateLimitId", params.rateLimitId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("rate_limits")
                    .addPathParam("rateLimitId", params._pathParam(1))
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { updateHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }

        private val listHandler: Handler<RateLimitListPageResponse> =
            jsonHandler<RateLimitListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RateLimitListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RateLimitListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("rate_limits")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions =
                requestOptions
                    .applyDefaults(RequestOptions.from(clientOptions))
                    .withQualifiers(mapOf("beta" to "true"))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
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
}
