// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.BetaOrganization
import com.anthropic.models.beta.organization.OrganizationRetrieveParams
import com.anthropic.services.blocking.beta.organization.ApiKeyService
import com.anthropic.services.blocking.beta.organization.ApiKeyServiceImpl
import com.anthropic.services.blocking.beta.organization.ExternalKeyService
import com.anthropic.services.blocking.beta.organization.ExternalKeyServiceImpl
import com.anthropic.services.blocking.beta.organization.FederationService
import com.anthropic.services.blocking.beta.organization.FederationServiceImpl
import com.anthropic.services.blocking.beta.organization.InviteService
import com.anthropic.services.blocking.beta.organization.InviteServiceImpl
import com.anthropic.services.blocking.beta.organization.RateLimitService
import com.anthropic.services.blocking.beta.organization.RateLimitServiceImpl
import com.anthropic.services.blocking.beta.organization.ServiceAccountService
import com.anthropic.services.blocking.beta.organization.ServiceAccountServiceImpl
import com.anthropic.services.blocking.beta.organization.UserService
import com.anthropic.services.blocking.beta.organization.UserServiceImpl
import com.anthropic.services.blocking.beta.organization.WorkspaceService
import com.anthropic.services.blocking.beta.organization.WorkspaceServiceImpl
import java.util.function.Consumer

class OrganizationServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    OrganizationService {

    private val withRawResponse: OrganizationService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val apiKeys: ApiKeyService by lazy { ApiKeyServiceImpl(clientOptions) }

    private val externalKeys: ExternalKeyService by lazy { ExternalKeyServiceImpl(clientOptions) }

    private val federation: FederationService by lazy { FederationServiceImpl(clientOptions) }

    private val invites: InviteService by lazy { InviteServiceImpl(clientOptions) }

    private val serviceAccounts: ServiceAccountService by lazy {
        ServiceAccountServiceImpl(clientOptions)
    }

    private val users: UserService by lazy { UserServiceImpl(clientOptions) }

    private val workspaces: WorkspaceService by lazy { WorkspaceServiceImpl(clientOptions) }

    private val rateLimits: RateLimitService by lazy { RateLimitServiceImpl(clientOptions) }

    override fun withRawResponse(): OrganizationService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): OrganizationService =
        OrganizationServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun apiKeys(): ApiKeyService = apiKeys

    override fun externalKeys(): ExternalKeyService = externalKeys

    override fun federation(): FederationService = federation

    override fun invites(): InviteService = invites

    override fun serviceAccounts(): ServiceAccountService = serviceAccounts

    override fun users(): UserService = users

    override fun workspaces(): WorkspaceService = workspaces

    override fun rateLimits(): RateLimitService = rateLimits

    override fun retrieve(
        params: OrganizationRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaOrganization =
        // get /v1/organizations/me?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        OrganizationService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val apiKeys: ApiKeyService.WithRawResponse by lazy {
            ApiKeyServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val externalKeys: ExternalKeyService.WithRawResponse by lazy {
            ExternalKeyServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val federation: FederationService.WithRawResponse by lazy {
            FederationServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val invites: InviteService.WithRawResponse by lazy {
            InviteServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val serviceAccounts: ServiceAccountService.WithRawResponse by lazy {
            ServiceAccountServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val users: UserService.WithRawResponse by lazy {
            UserServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val workspaces: WorkspaceService.WithRawResponse by lazy {
            WorkspaceServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val rateLimits: RateLimitService.WithRawResponse by lazy {
            RateLimitServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): OrganizationService.WithRawResponse =
            OrganizationServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun apiKeys(): ApiKeyService.WithRawResponse = apiKeys

        override fun externalKeys(): ExternalKeyService.WithRawResponse = externalKeys

        override fun federation(): FederationService.WithRawResponse = federation

        override fun invites(): InviteService.WithRawResponse = invites

        override fun serviceAccounts(): ServiceAccountService.WithRawResponse = serviceAccounts

        override fun users(): UserService.WithRawResponse = users

        override fun workspaces(): WorkspaceService.WithRawResponse = workspaces

        override fun rateLimits(): RateLimitService.WithRawResponse = rateLimits

        private val retrieveHandler: Handler<BetaOrganization> =
            jsonHandler<BetaOrganization>(clientOptions.jsonMapper)

        override fun retrieve(
            params: OrganizationRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganization> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "me")
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
    }
}
