// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.federation

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
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRule
import com.anthropic.models.beta.organization.federation.rules.RuleArchiveParams
import com.anthropic.models.beta.organization.federation.rules.RuleCreateParams
import com.anthropic.models.beta.organization.federation.rules.RuleListPageAsync
import com.anthropic.models.beta.organization.federation.rules.RuleListPageResponse
import com.anthropic.models.beta.organization.federation.rules.RuleListParams
import com.anthropic.models.beta.organization.federation.rules.RuleRetrieveParams
import com.anthropic.models.beta.organization.federation.rules.RuleUpdateParams
import com.anthropic.services.async.beta.organization.federation.rules.WorkspaceServiceAsync
import com.anthropic.services.async.beta.organization.federation.rules.WorkspaceServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RuleServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RuleServiceAsync {

    private val withRawResponse: RuleServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val workspaces: WorkspaceServiceAsync by lazy {
        WorkspaceServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): RuleServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RuleServiceAsync =
        RuleServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun workspaces(): WorkspaceServiceAsync = workspaces

    override fun create(
        params: RuleCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        // post /v1/organizations/federation_rules?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: RuleRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        // get /v1/organizations/federation_rules/{federation_rule_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: RuleUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        // post /v1/organizations/federation_rules/{federation_rule_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: RuleListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RuleListPageAsync> =
        // get /v1/organizations/federation_rules?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun archive(
        params: RuleArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationRule> =
        // post /v1/organizations/federation_rules/{federation_rule_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RuleServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val workspaces: WorkspaceServiceAsync.WithRawResponse by lazy {
            WorkspaceServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RuleServiceAsync.WithRawResponse =
            RuleServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun workspaces(): WorkspaceServiceAsync.WithRawResponse = workspaces

        private val createHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun create(
            params: RuleCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_rules")
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RuleRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val updateHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun update(
            params: RuleUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { updateHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<RuleListPageResponse> =
            jsonHandler<RuleListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RuleListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RuleListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "federation_rules")
                    .putQueryParam("beta", "true")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                RuleListPageAsync.builder()
                                    .service(RuleServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val archiveHandler: Handler<BetaFederationRule> =
            jsonHandler<BetaFederationRule>(clientOptions.jsonMapper)

        override fun archive(
            params: RuleArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationRule>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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
}
