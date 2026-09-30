package com.anthropic.services.blocking.organization.federation

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
import com.anthropic.models.organization.federation.rules.FederationRule
import com.anthropic.models.organization.federation.rules.RuleArchiveParams
import com.anthropic.models.organization.federation.rules.RuleCreateParams
import com.anthropic.models.organization.federation.rules.RuleListPage
import com.anthropic.models.organization.federation.rules.RuleListPageResponse
import com.anthropic.models.organization.federation.rules.RuleListParams
import com.anthropic.models.organization.federation.rules.RuleRetrieveParams
import com.anthropic.models.organization.federation.rules.RuleUpdateParams
import com.anthropic.services.blocking.organization.federation.rules.WorkspaceService
import com.anthropic.services.blocking.organization.federation.rules.WorkspaceServiceImpl
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

    override fun create(params: RuleCreateParams, requestOptions: RequestOptions): FederationRule =
        // post /v1/organizations/federation_rules
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: RuleRetrieveParams,
        requestOptions: RequestOptions,
    ): FederationRule =
        // get /v1/organizations/federation_rules/{federation_rule_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(params: RuleUpdateParams, requestOptions: RequestOptions): FederationRule =
        // post /v1/organizations/federation_rules/{federation_rule_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: RuleListParams, requestOptions: RequestOptions): RuleListPage =
        // get /v1/organizations/federation_rules
        withRawResponse().list(params, requestOptions).parse()

    override fun archive(
        params: RuleArchiveParams,
        requestOptions: RequestOptions,
    ): FederationRule =
        // post /v1/organizations/federation_rules/{federation_rule_id}/archive
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

        private val createHandler: Handler<FederationRule> =
            jsonHandler<FederationRule>(clientOptions.jsonMapper)

        override fun create(
            params: RuleCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_rules")
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

        private val retrieveHandler: Handler<FederationRule> =
            jsonHandler<FederationRule>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RuleRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> {
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

        private val updateHandler: Handler<FederationRule> =
            jsonHandler<FederationRule>(clientOptions.jsonMapper)

        override fun update(
            params: RuleUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> {
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

        private val archiveHandler: Handler<FederationRule> =
            jsonHandler<FederationRule>(clientOptions.jsonMapper)

        override fun archive(
            params: RuleArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationRule> {
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
