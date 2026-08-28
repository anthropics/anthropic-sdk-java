// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.compliancesettings.BetaComplianceSettings
import com.anthropic.models.beta.organization.compliancesettings.ComplianceSettingRetrieveParams
import com.anthropic.models.beta.organization.compliancesettings.ComplianceSettingUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ComplianceSettingServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceSettingServiceAsync

    /**
     * Retrieve your organization's Compliance Settings.
     *
     * Compliance Settings is a singleton resource: there is exactly one per organization, addressed
     * without an identifier. The `state` field reflects whether the Compliance API is enabled. An
     * organization with a parent organization reads the state inherited from the parent's
     * configuration.
     */
    fun retrieve(): CompletableFuture<BetaComplianceSettings> =
        retrieve(ComplianceSettingRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceSettings>

    /** @see retrieve */
    fun retrieve(
        params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none()
    ): CompletableFuture<BetaComplianceSettings> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): CompletableFuture<BetaComplianceSettings> =
        retrieve(ComplianceSettingRetrieveParams.none(), requestOptions)

    /**
     * Update your organization's Compliance Settings.
     *
     * Setting `state` to `enabled` turns on the Compliance API and begins capturing organization
     * activity events. Setting it to `disabled` turns both off. `state` reflects whether the
     * Compliance API is enabled.
     *
     * A request that sets `state` to its current value succeeds and leaves the resource unchanged.
     * A `disabled` request stays in effect until a later `enabled` request or the organization's
     * next provisioning action that enables Access Transparency: enabling Access Transparency also
     * enables the Compliance API, which serves its activity events, so such provisioning (including
     * re-runs) re-enables the Compliance API even after a `disabled` request. Automated
     * provisioning never disables compliance settings.
     */
    fun update(params: ComplianceSettingUpdateParams): CompletableFuture<BetaComplianceSettings> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ComplianceSettingUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceSettings>

    /**
     * A view of [ComplianceSettingServiceAsync] that provides access to raw HTTP responses for each
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
        ): ComplianceSettingServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/compliance_settings?beta=true`,
         * but is otherwise the same as [ComplianceSettingServiceAsync.retrieve].
         */
        fun retrieve(): CompletableFuture<HttpResponseFor<BetaComplianceSettings>> =
            retrieve(ComplianceSettingRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceSettings>>

        /** @see retrieve */
        fun retrieve(
            params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none()
        ): CompletableFuture<HttpResponseFor<BetaComplianceSettings>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BetaComplianceSettings>> =
            retrieve(ComplianceSettingRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/compliance_settings?beta=true`,
         * but is otherwise the same as [ComplianceSettingServiceAsync.update].
         */
        fun update(
            params: ComplianceSettingUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaComplianceSettings>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ComplianceSettingUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceSettings>>
    }
}
