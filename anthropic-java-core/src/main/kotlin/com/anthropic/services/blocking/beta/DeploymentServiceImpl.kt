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
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.deploymentruns.BetaManagedAgentsDeploymentRun
import com.anthropic.models.beta.deployments.BetaManagedAgentsDeployment
import com.anthropic.models.beta.deployments.DeploymentArchiveParams
import com.anthropic.models.beta.deployments.DeploymentCreateParams
import com.anthropic.models.beta.deployments.DeploymentListPage
import com.anthropic.models.beta.deployments.DeploymentListPageResponse
import com.anthropic.models.beta.deployments.DeploymentListParams
import com.anthropic.models.beta.deployments.DeploymentPauseParams
import com.anthropic.models.beta.deployments.DeploymentRetrieveParams
import com.anthropic.models.beta.deployments.DeploymentRunParams
import com.anthropic.models.beta.deployments.DeploymentUnpauseParams
import com.anthropic.models.beta.deployments.DeploymentUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class DeploymentServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    DeploymentService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: DeploymentService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): DeploymentService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): DeploymentService =
        DeploymentServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: DeploymentCreateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        // post /v1/deployments?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: DeploymentRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        // get /v1/deployments/{deployment_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: DeploymentUpdateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        // post /v1/deployments/{deployment_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: DeploymentListParams,
        requestOptions: RequestOptions,
    ): DeploymentListPage =
        // get /v1/deployments?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: DeploymentArchiveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        // post /v1/deployments/{deployment_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    override fun pause(
        params: DeploymentPauseParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        // post /v1/deployments/{deployment_id}/pause?beta=true
        withRawResponse().pause(params, requestOptions).parse()

    override fun run(
        params: DeploymentRunParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeploymentRun =
        // post /v1/deployments/{deployment_id}/run?beta=true
        withRawResponse().run(params, requestOptions).parse()

    override fun unpause(
        params: DeploymentUnpauseParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeployment =
        // post /v1/deployments/{deployment_id}/unpause?beta=true
        withRawResponse().unpause(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        DeploymentService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DeploymentService.WithRawResponse =
            DeploymentServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaManagedAgentsDeployment> =
            jsonHandler<BetaManagedAgentsDeployment>(clientOptions.jsonMapper)

        override fun create(
            params: DeploymentCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaManagedAgentsDeployment> =
            jsonHandler<BetaManagedAgentsDeployment>(clientOptions.jsonMapper)

        override fun retrieve(
            params: DeploymentRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentId", params.deploymentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments", params._pathParam(0))
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

        private val updateHandler: Handler<BetaManagedAgentsDeployment> =
            jsonHandler<BetaManagedAgentsDeployment>(clientOptions.jsonMapper)

        override fun update(
            params: DeploymentUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentId", params.deploymentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<DeploymentListPageResponse> =
            jsonHandler<DeploymentListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: DeploymentListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeploymentListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments")
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
                        DeploymentListPage.builder()
                            .service(DeploymentServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaManagedAgentsDeployment> =
            jsonHandler<BetaManagedAgentsDeployment>(clientOptions.jsonMapper)

        override fun archive(
            params: DeploymentArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentId", params.deploymentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments", params._pathParam(0), "archive")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { archiveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val pauseHandler: Handler<BetaManagedAgentsDeployment> =
            jsonHandler<BetaManagedAgentsDeployment>(clientOptions.jsonMapper)

        override fun pause(
            params: DeploymentPauseParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentId", params.deploymentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments", params._pathParam(0), "pause")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { pauseHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val runHandler: Handler<BetaManagedAgentsDeploymentRun> =
            jsonHandler<BetaManagedAgentsDeploymentRun>(clientOptions.jsonMapper)

        override fun run(
            params: DeploymentRunParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeploymentRun> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentId", params.deploymentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments", params._pathParam(0), "run")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { runHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val unpauseHandler: Handler<BetaManagedAgentsDeployment> =
            jsonHandler<BetaManagedAgentsDeployment>(clientOptions.jsonMapper)

        override fun unpause(
            params: DeploymentUnpauseParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeployment> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("deploymentId", params.deploymentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "deployments", params._pathParam(0), "unpause")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { unpauseHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
