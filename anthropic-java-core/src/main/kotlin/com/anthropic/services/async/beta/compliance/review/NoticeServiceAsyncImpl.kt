package com.anthropic.services.async.beta.compliance.review

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.compliance.review.notices.NoticeListPageAsync
import com.anthropic.models.beta.compliance.review.notices.NoticeListPageResponse
import com.anthropic.models.beta.compliance.review.notices.NoticeListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class NoticeServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    NoticeServiceAsync {

    private val withRawResponse: NoticeServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): NoticeServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): NoticeServiceAsync =
        NoticeServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: NoticeListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<NoticeListPageAsync> =
        // get /v1/compliance/review/notices
        withRawResponse().list(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        NoticeServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): NoticeServiceAsync.WithRawResponse =
            NoticeServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<NoticeListPageResponse> =
            jsonHandler<NoticeListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: NoticeListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<NoticeListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "notices")
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
                                    NoticeListPageAsync.builder()
                                        .service(NoticeServiceAsyncImpl(clientOptions))
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
