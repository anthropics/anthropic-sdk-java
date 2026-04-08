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
import com.anthropic.models.beta.agents.AgentArchiveParams
import com.anthropic.models.beta.agents.AgentCreateParams
import com.anthropic.models.beta.agents.AgentListPage
import com.anthropic.models.beta.agents.AgentListPageResponse
import com.anthropic.models.beta.agents.AgentListParams
import com.anthropic.models.beta.agents.AgentRetrieveParams
import com.anthropic.models.beta.agents.AgentUpdateParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgent
import com.anthropic.services.blocking.beta.agents.VersionService
import com.anthropic.services.blocking.beta.agents.VersionServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class AgentServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    AgentService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: AgentService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val versions: VersionService by lazy { VersionServiceImpl(clientOptions) }

    override fun withRawResponse(): AgentService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): AgentService =
        AgentServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun versions(): VersionService = versions

    override fun create(
        params: AgentCreateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsAgent =
        // post /v1/agents?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: AgentRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsAgent =
        // get /v1/agents/{agent_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: AgentUpdateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsAgent =
        // post /v1/agents/{agent_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: AgentListParams, requestOptions: RequestOptions): AgentListPage =
        // get /v1/agents?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: AgentArchiveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsAgent =
        // post /v1/agents/{agent_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AgentService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val versions: VersionService.WithRawResponse by lazy {
            VersionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): AgentService.WithRawResponse =
            AgentServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun versions(): VersionService.WithRawResponse = versions

        private val createHandler: Handler<BetaManagedAgentsAgent> =
            jsonHandler<BetaManagedAgentsAgent>(clientOptions.jsonMapper)

        override fun create(
            params: AgentCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsAgent> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "agents")
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

        private val retrieveHandler: Handler<BetaManagedAgentsAgent> =
            jsonHandler<BetaManagedAgentsAgent>(clientOptions.jsonMapper)

        override fun retrieve(
            params: AgentRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsAgent> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("agentId", params.agentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "agents", params._pathParam(0))
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

        private val updateHandler: Handler<BetaManagedAgentsAgent> =
            jsonHandler<BetaManagedAgentsAgent>(clientOptions.jsonMapper)

        override fun update(
            params: AgentUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsAgent> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("agentId", params.agentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "agents", params._pathParam(0))
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

        private val listHandler: Handler<AgentListPageResponse> =
            jsonHandler<AgentListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: AgentListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AgentListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "agents")
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
                        AgentListPage.builder()
                            .service(AgentServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaManagedAgentsAgent> =
            jsonHandler<BetaManagedAgentsAgent>(clientOptions.jsonMapper)

        override fun archive(
            params: AgentArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsAgent> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("agentId", params.agentId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "agents", params._pathParam(0), "archive")
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
    }
}
