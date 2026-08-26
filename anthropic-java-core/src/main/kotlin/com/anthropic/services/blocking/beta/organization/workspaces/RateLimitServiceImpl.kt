// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.workspaces

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListPage
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListPageResponse
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitListParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RateLimitServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    RateLimitService {

    private val withRawResponse: RateLimitService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RateLimitService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RateLimitService =
        RateLimitServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: RateLimitListParams,
        requestOptions: RequestOptions,
    ): RateLimitListPage =
        // get /v1/organizations/workspaces/{workspace_id}/rate_limits?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RateLimitService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RateLimitService.WithRawResponse =
            RateLimitServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<RateLimitListPageResponse> =
            jsonHandler<RateLimitListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RateLimitListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RateLimitListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "workspaces",
                        params._pathParam(0),
                        "rate_limits",
                    )
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
                        RateLimitListPage.builder()
                            .service(RateLimitServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
