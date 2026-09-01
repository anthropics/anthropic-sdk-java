// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.BetaOrganization
import com.anthropic.models.beta.organization.OrganizationRetrieveParams
import com.anthropic.services.blocking.beta.organization.ApiKeyService
import com.anthropic.services.blocking.beta.organization.ComplianceSettingService
import com.anthropic.services.blocking.beta.organization.ExternalKeyService
import com.anthropic.services.blocking.beta.organization.FederationService
import com.anthropic.services.blocking.beta.organization.InviteService
import com.anthropic.services.blocking.beta.organization.RateLimitService
import com.anthropic.services.blocking.beta.organization.ServiceAccountService
import com.anthropic.services.blocking.beta.organization.UserService
import com.anthropic.services.blocking.beta.organization.WorkspaceService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface OrganizationService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): OrganizationService

    fun apiKeys(): ApiKeyService

    fun externalKeys(): ExternalKeyService

    fun federation(): FederationService

    fun invites(): InviteService

    fun serviceAccounts(): ServiceAccountService

    fun users(): UserService

    fun workspaces(): WorkspaceService

    fun rateLimits(): RateLimitService

    fun complianceSettings(): ComplianceSettingService

    /** Retrieve information about the organization associated with the authenticated API key. */
    fun retrieve(): BetaOrganization = retrieve(OrganizationRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: OrganizationRetrieveParams = OrganizationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganization

    /** @see retrieve */
    fun retrieve(
        params: OrganizationRetrieveParams = OrganizationRetrieveParams.none()
    ): BetaOrganization = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): BetaOrganization =
        retrieve(OrganizationRetrieveParams.none(), requestOptions)

    /**
     * A view of [OrganizationService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): OrganizationService.WithRawResponse

        fun apiKeys(): ApiKeyService.WithRawResponse

        fun externalKeys(): ExternalKeyService.WithRawResponse

        fun federation(): FederationService.WithRawResponse

        fun invites(): InviteService.WithRawResponse

        fun serviceAccounts(): ServiceAccountService.WithRawResponse

        fun users(): UserService.WithRawResponse

        fun workspaces(): WorkspaceService.WithRawResponse

        fun rateLimits(): RateLimitService.WithRawResponse

        fun complianceSettings(): ComplianceSettingService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/me?beta=true`, but is otherwise
         * the same as [OrganizationService.retrieve].
         */
        @MustBeClosed
        fun retrieve(): HttpResponseFor<BetaOrganization> =
            retrieve(OrganizationRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: OrganizationRetrieveParams = OrganizationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganization>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: OrganizationRetrieveParams = OrganizationRetrieveParams.none()
        ): HttpResponseFor<BetaOrganization> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(requestOptions: RequestOptions): HttpResponseFor<BetaOrganization> =
            retrieve(OrganizationRetrieveParams.none(), requestOptions)
    }
}
