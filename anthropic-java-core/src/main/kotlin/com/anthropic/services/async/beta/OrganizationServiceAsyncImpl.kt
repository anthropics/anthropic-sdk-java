// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

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
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.BetaOrganization
import com.anthropic.models.beta.organization.OrganizationRetrieveParams
import com.anthropic.services.async.beta.organization.ApiKeyServiceAsync
import com.anthropic.services.async.beta.organization.ApiKeyServiceAsyncImpl
import com.anthropic.services.async.beta.organization.ExternalKeyServiceAsync
import com.anthropic.services.async.beta.organization.ExternalKeyServiceAsyncImpl
import com.anthropic.services.async.beta.organization.FederationServiceAsync
import com.anthropic.services.async.beta.organization.FederationServiceAsyncImpl
import com.anthropic.services.async.beta.organization.InviteServiceAsync
import com.anthropic.services.async.beta.organization.InviteServiceAsyncImpl
import com.anthropic.services.async.beta.organization.RateLimitServiceAsync
import com.anthropic.services.async.beta.organization.RateLimitServiceAsyncImpl
import com.anthropic.services.async.beta.organization.ServiceAccountServiceAsync
import com.anthropic.services.async.beta.organization.ServiceAccountServiceAsyncImpl
import com.anthropic.services.async.beta.organization.UserServiceAsync
import com.anthropic.services.async.beta.organization.UserServiceAsyncImpl
import com.anthropic.services.async.beta.organization.WorkspaceServiceAsync
import com.anthropic.services.async.beta.organization.WorkspaceServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class OrganizationServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    OrganizationServiceAsync {

    private val withRawResponse: OrganizationServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val apiKeys: ApiKeyServiceAsync by lazy { ApiKeyServiceAsyncImpl(clientOptions) }

    private val externalKeys: ExternalKeyServiceAsync by lazy {
        ExternalKeyServiceAsyncImpl(clientOptions)
    }

    private val federation: FederationServiceAsync by lazy {
        FederationServiceAsyncImpl(clientOptions)
    }

    private val invites: InviteServiceAsync by lazy { InviteServiceAsyncImpl(clientOptions) }

    private val serviceAccounts: ServiceAccountServiceAsync by lazy {
        ServiceAccountServiceAsyncImpl(clientOptions)
    }

    private val users: UserServiceAsync by lazy { UserServiceAsyncImpl(clientOptions) }

    private val workspaces: WorkspaceServiceAsync by lazy {
        WorkspaceServiceAsyncImpl(clientOptions)
    }

    private val rateLimits: RateLimitServiceAsync by lazy {
        RateLimitServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): OrganizationServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): OrganizationServiceAsync =
        OrganizationServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun apiKeys(): ApiKeyServiceAsync = apiKeys

    override fun externalKeys(): ExternalKeyServiceAsync = externalKeys

    override fun federation(): FederationServiceAsync = federation

    override fun invites(): InviteServiceAsync = invites

    override fun serviceAccounts(): ServiceAccountServiceAsync = serviceAccounts

    override fun users(): UserServiceAsync = users

    override fun workspaces(): WorkspaceServiceAsync = workspaces

    override fun rateLimits(): RateLimitServiceAsync = rateLimits

    override fun retrieve(
        params: OrganizationRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganization> =
        // get /v1/organizations/me?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        OrganizationServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val apiKeys: ApiKeyServiceAsync.WithRawResponse by lazy {
            ApiKeyServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val externalKeys: ExternalKeyServiceAsync.WithRawResponse by lazy {
            ExternalKeyServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val federation: FederationServiceAsync.WithRawResponse by lazy {
            FederationServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val invites: InviteServiceAsync.WithRawResponse by lazy {
            InviteServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val serviceAccounts: ServiceAccountServiceAsync.WithRawResponse by lazy {
            ServiceAccountServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val users: UserServiceAsync.WithRawResponse by lazy {
            UserServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val workspaces: WorkspaceServiceAsync.WithRawResponse by lazy {
            WorkspaceServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val rateLimits: RateLimitServiceAsync.WithRawResponse by lazy {
            RateLimitServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): OrganizationServiceAsync.WithRawResponse =
            OrganizationServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun apiKeys(): ApiKeyServiceAsync.WithRawResponse = apiKeys

        override fun externalKeys(): ExternalKeyServiceAsync.WithRawResponse = externalKeys

        override fun federation(): FederationServiceAsync.WithRawResponse = federation

        override fun invites(): InviteServiceAsync.WithRawResponse = invites

        override fun serviceAccounts(): ServiceAccountServiceAsync.WithRawResponse = serviceAccounts

        override fun users(): UserServiceAsync.WithRawResponse = users

        override fun workspaces(): WorkspaceServiceAsync.WithRawResponse = workspaces

        override fun rateLimits(): RateLimitServiceAsync.WithRawResponse = rateLimits

        private val retrieveHandler: Handler<BetaOrganization> =
            jsonHandler<BetaOrganization>(clientOptions.jsonMapper)

        override fun retrieve(
            params: OrganizationRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganization>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "me")
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
    }
}
