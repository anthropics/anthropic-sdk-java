// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.federation

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
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRule
import com.anthropic.models.beta.organization.federation.rules.RuleArchiveParams
import com.anthropic.models.beta.organization.federation.rules.RuleCreateParams
import com.anthropic.models.beta.organization.federation.rules.RuleListPage
import com.anthropic.models.beta.organization.federation.rules.RuleListPageResponse
import com.anthropic.models.beta.organization.federation.rules.RuleListParams
import com.anthropic.models.beta.organization.federation.rules.RuleRetrieveParams
import com.anthropic.models.beta.organization.federation.rules.RuleUpdateParams
import com.anthropic.services.blocking.beta.organization.federation.rules.WorkspaceService
import com.anthropic.services.blocking.beta.organization.federation.rules.WorkspaceServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RuleServiceImpl internal constructor(private val clientOptions: ClientOptions) : RuleService {

    private val withRawResponse: RuleService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val workspaces: WorkspaceService by lazy { WorkspaceServiceImpl(clientOptions) }

    override fun withRawResponse(): RuleService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RuleService =
        RuleServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun workspaces(): WorkspaceService = workspaces

    override fun create(
        params: RuleCreateParams,
        requestOptions: RequestOptions,
    ): BetaFederationRule =
        // post /v1/organizations/federation_rules?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: RuleRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaFederationRule =
        // get /v1/organizations/federation_rules/{federation_rule_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: RuleUpdateParams,
        requestOptions: RequestOptions,
    ): BetaFederationRule =
        // post /v1/organizations/federation_rules/{federation_rule_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: RuleListParams, requestOptions: RequestOptions): RuleListPage =
        // get /v1/organizations/federation_rules?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: RuleArchiveParams,
        requestOptions: RequestOptions,
    ): BetaFederationRule =
        // post /v1/organizations/federation_rules/{federation_rule_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RuleService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val workspaces: WorkspaceService.WithRawResponse by lazy {
            WorkspaceServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RuleService.WithRawResponse =
            RuleServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun workspaces(): WorkspaceService.WithRawResponse = workspaces

        private val createHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun create(
            params: RuleCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationRule> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_rules")
                    .putQueryParam("beta", "true")
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

        private val retrieveHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RuleRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationRule> {
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
                    )
                    .putQueryParam("beta", "true")
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

        private val updateHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun update(
            params: RuleUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationRule> {
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
                    )
                    .putQueryParam("beta", "true")
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

        private val listHandler: Handler<RuleListPageResponse> =
            jsonHandler<RuleListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RuleListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RuleListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_rules")
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
                        RuleListPage.builder()
                            .service(RuleServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val archiveHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun archive(
            params: RuleArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFederationRule> {
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
                        "archive",
                    )
                    .putQueryParam("beta", "true")
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
