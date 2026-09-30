package com.anthropic.services.async.beta.organization.spendlimits

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
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListPageAsync
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListPageResponse
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class EffectiveServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    EffectiveServiceAsync {

    private val withRawResponse: EffectiveServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): EffectiveServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): EffectiveServiceAsync =
        EffectiveServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: EffectiveListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<EffectiveListPageAsync> =
        // get /v1/organizations/spend_limits/effective?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        EffectiveServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): EffectiveServiceAsync.WithRawResponse =
            EffectiveServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<EffectiveListPageResponse> =
            jsonHandler<EffectiveListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: EffectiveListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<EffectiveListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "spend_limits", "effective")
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
                                EffectiveListPageAsync.builder()
                                    .service(EffectiveServiceAsyncImpl(clientOptions))
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
