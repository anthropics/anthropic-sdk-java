// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.deploymentruns.BetaManagedAgentsDeploymentRun
import com.anthropic.models.beta.deploymentruns.DeploymentRunListPage
import com.anthropic.models.beta.deploymentruns.DeploymentRunListPageResponse
import com.anthropic.models.beta.deploymentruns.DeploymentRunListParams
import com.anthropic.models.beta.deploymentruns.DeploymentRunRetrieveParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class DeploymentRunServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    DeploymentRunService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: DeploymentRunService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): DeploymentRunService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): DeploymentRunService =
        DeploymentRunServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: DeploymentRunRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeploymentRun =
        // get /v1/deployment_runs/{deployment_run_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(
        params: DeploymentRunListParams,
        requestOptions: RequestOptions,
    ): DeploymentRunListPage =
        // get /v1/deployment_runs?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        DeploymentRunService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DeploymentRunService.WithRawResponse =
            DeploymentRunServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaManagedAgentsDeploymentRun> =
            jsonHandler<BetaManagedAgentsDeploymentRun>(clientOptions.jsonMapper)

        override fun retrieve(
            params: DeploymentRunRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentRunId", params.deploymentRunId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployment_runs", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val listHandler: Handler<DeploymentRunListPageResponse> =
            jsonHandler<DeploymentRunListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: DeploymentRunListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeploymentRunListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployment_runs")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                        DeploymentRunListPage.builder()
                            .service(DeploymentRunServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
