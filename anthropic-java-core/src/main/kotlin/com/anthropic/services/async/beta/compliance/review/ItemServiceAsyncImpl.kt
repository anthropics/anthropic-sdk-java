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
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.compliance.review.items.BetaComplianceReviewItem
import com.anthropic.models.beta.compliance.review.items.ItemRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ItemServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ItemServiceAsync {

    private val withRawResponse: ItemServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ItemServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ItemServiceAsync =
        ItemServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ItemRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaComplianceReviewItem> =
        // get /v1/compliance/review/items/{item_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ItemServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ItemServiceAsync.WithRawResponse =
            ItemServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaComplianceReviewItem> =
            jsonHandler<BetaComplianceReviewItem>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ItemRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("itemId", params.itemId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "compliance", "review", "items")
                    .addPathParam("itemId", params._pathParam(0))
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
    }
}
