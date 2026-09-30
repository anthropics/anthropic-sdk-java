package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.OrganizationInfo
import com.anthropic.models.organization.OrganizationRetrieveParams
import com.anthropic.services.blocking.organization.ApiKeyService
import com.anthropic.services.blocking.organization.ComplianceSettingService
import com.anthropic.services.blocking.organization.ExternalKeyService
import com.anthropic.services.blocking.organization.FederationService
import com.anthropic.services.blocking.organization.InviteService
import com.anthropic.services.blocking.organization.RateLimitService
import com.anthropic.services.blocking.organization.ServiceAccountService
import com.anthropic.services.blocking.organization.UserService
import com.anthropic.services.blocking.organization.WorkspaceService
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
    fun retrieve(): OrganizationInfo = retrieve(OrganizationRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: OrganizationRetrieveParams = OrganizationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationInfo

    /** @see retrieve */
    fun retrieve(
        params: OrganizationRetrieveParams = OrganizationRetrieveParams.none()
    ): OrganizationInfo = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): OrganizationInfo =
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
         * Returns a raw HTTP response for `get /v1/organizations/me`, but is otherwise the same as
         * [OrganizationService.retrieve].
         */
        @MustBeClosed
        fun retrieve(): HttpResponseFor<OrganizationInfo> =
            retrieve(OrganizationRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: OrganizationRetrieveParams = OrganizationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationInfo>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: OrganizationRetrieveParams = OrganizationRetrieveParams.none()
        ): HttpResponseFor<OrganizationInfo> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(requestOptions: RequestOptions): HttpResponseFor<OrganizationInfo> =
            retrieve(OrganizationRetrieveParams.none(), requestOptions)
    }
}
