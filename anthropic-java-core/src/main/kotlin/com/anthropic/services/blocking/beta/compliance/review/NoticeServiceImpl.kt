package com.anthropic.services.blocking.beta.compliance.review

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
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.compliance.review.notices.NoticeListPage
import com.anthropic.models.beta.compliance.review.notices.NoticeListPageResponse
import com.anthropic.models.beta.compliance.review.notices.NoticeListParams
import java.util.function.Consumer

class NoticeServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    NoticeService {

    private val withRawResponse: NoticeService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): NoticeService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): NoticeService =
        NoticeServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(params: NoticeListParams, requestOptions: RequestOptions): NoticeListPage =
        // get /v1/compliance/review/notices
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        NoticeService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): NoticeService.WithRawResponse =
            NoticeServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<NoticeListPageResponse> =
            jsonHandler<NoticeListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: NoticeListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<NoticeListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "notices")
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
                            NoticeListPage.builder()
                                .service(NoticeServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }
    }
}
