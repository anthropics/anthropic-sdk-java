// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.federation.rules

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRuleWorkspace
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListPage
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListPageResponse
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceListParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WorkspaceServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    WorkspaceService {

    private val withRawResponse: WorkspaceService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WorkspaceService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WorkspaceService =
        WorkspaceServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: WorkspaceListParams,
        requestOptions: RequestOptions,
    ): WorkspaceListPage =
        // get /v1/organizations/federation_rules/{federation_rule_id}/workspaces?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun add(
        params: WorkspaceAddParams,
        requestOptions: RequestOptions,
    ): BetaFederationRuleWorkspace =
        // post /v1/organizations/federation_rules/{federation_rule_id}/workspaces?beta=true
        withRawResponse().add(params, requestOptions).parse()

    override fun remove(
        params: WorkspaceRemoveParams,
        requestOptions: RequestOptions,
    ): WorkspaceRemoveResponse =
        // delete
        // /v1/organizations/federation_rules/{federation_rule_id}/workspaces/{workspace_id}?beta=true
        withRawResponse().remove(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WorkspaceService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WorkspaceService.WithRawResponse =
            WorkspaceServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<WorkspaceListPageResponse> =
            jsonHandler<WorkspaceListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WorkspaceListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationRuleId", params.federationRuleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "federation_rules",
                        params._pathParam(0),
                        "workspaces",
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
                        WorkspaceListPage.builder()
                            .service(WorkspaceServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val addHandler: Handler<BetaFederationRuleWorkspace> =
            jsonHandler<BetaFederationRuleWorkspace>(clientOptions.jsonMapper)

        override fun add(
            params: WorkspaceAddParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationRuleWorkspace> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("federationRuleId", params.federationRuleId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "federation_rules",
                        params._pathParam(0),
                        "workspaces",
                    )
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { addHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val removeHandler: Handler<WorkspaceRemoveResponse> =
            jsonHandler<WorkspaceRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: WorkspaceRemoveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WorkspaceRemoveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "federation_rules",
                        params._pathParam(0),
                        "workspaces",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { removeHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
