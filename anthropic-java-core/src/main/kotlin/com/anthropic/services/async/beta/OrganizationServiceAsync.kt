// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.BetaOrganization
import com.anthropic.models.beta.organization.OrganizationRetrieveParams
import com.anthropic.services.async.beta.organization.ApiKeyServiceAsync
import com.anthropic.services.async.beta.organization.ComplianceSettingServiceAsync
import com.anthropic.services.async.beta.organization.ExternalKeyServiceAsync
import com.anthropic.services.async.beta.organization.FederationServiceAsync
import com.anthropic.services.async.beta.organization.InviteServiceAsync
import com.anthropic.services.async.beta.organization.RateLimitServiceAsync
import com.anthropic.services.async.beta.organization.ServiceAccountServiceAsync
import com.anthropic.services.async.beta.organization.UserServiceAsync
import com.anthropic.services.async.beta.organization.WorkspaceServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface OrganizationServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): OrganizationServiceAsync

    fun apiKeys(): ApiKeyServiceAsync

    fun externalKeys(): ExternalKeyServiceAsync

    fun federation(): FederationServiceAsync

    fun invites(): InviteServiceAsync

    fun serviceAccounts(): ServiceAccountServiceAsync

    fun users(): UserServiceAsync

    fun workspaces(): WorkspaceServiceAsync

    fun rateLimits(): RateLimitServiceAsync

    fun complianceSettings(): ComplianceSettingServiceAsync

    /** Retrieve information about the organization associated with the authenticated API key. */
    fun retrieve(): CompletableFuture<BetaOrganization> =
        retrieve(OrganizationRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: OrganizationRetrieveParams = OrganizationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganization>

    /** @see retrieve */
    fun retrieve(
        params: OrganizationRetrieveParams = OrganizationRetrieveParams.none()
    ): CompletableFuture<BetaOrganization> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): CompletableFuture<BetaOrganization> =
        retrieve(OrganizationRetrieveParams.none(), requestOptions)

    /**
     * A view of [OrganizationServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): OrganizationServiceAsync.WithRawResponse

        fun apiKeys(): ApiKeyServiceAsync.WithRawResponse

        fun externalKeys(): ExternalKeyServiceAsync.WithRawResponse

        fun federation(): FederationServiceAsync.WithRawResponse

        fun invites(): InviteServiceAsync.WithRawResponse

        fun serviceAccounts(): ServiceAccountServiceAsync.WithRawResponse

        fun users(): UserServiceAsync.WithRawResponse

        fun workspaces(): WorkspaceServiceAsync.WithRawResponse

        fun rateLimits(): RateLimitServiceAsync.WithRawResponse

        fun complianceSettings(): ComplianceSettingServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/me?beta=true`, but is otherwise
         * the same as [OrganizationServiceAsync.retrieve].
         */
        fun retrieve(): CompletableFuture<HttpResponseFor<BetaOrganization>> =
            retrieve(OrganizationRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            params: OrganizationRetrieveParams = OrganizationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganization>>

        /** @see retrieve */
        fun retrieve(
            params: OrganizationRetrieveParams = OrganizationRetrieveParams.none()
        ): CompletableFuture<HttpResponseFor<BetaOrganization>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BetaOrganization>> =
            retrieve(OrganizationRetrieveParams.none(), requestOptions)
    }
}
