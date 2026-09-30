package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.compliancesettings.ComplianceSettingRetrieveParams
import com.anthropic.models.organization.compliancesettings.ComplianceSettingUpdateParams
import com.anthropic.models.organization.compliancesettings.OrganizationComplianceSettings
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ComplianceSettingService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceSettingService

    /**
     * Retrieve your organization's Compliance Settings.
     *
     * Compliance Settings is a singleton resource: there is exactly one per organization, addressed
     * without an identifier. The `state` field reflects whether the Compliance API is enabled. An
     * organization with a parent organization reads the state inherited from the parent's
     * configuration.
     */
    fun retrieve(): OrganizationComplianceSettings =
        retrieve(ComplianceSettingRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationComplianceSettings

    /** @see retrieve */
    fun retrieve(
        params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none()
    ): OrganizationComplianceSettings = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(requestOptions: RequestOptions): OrganizationComplianceSettings =
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
    fun update(params: ComplianceSettingUpdateParams): OrganizationComplianceSettings =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ComplianceSettingUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): OrganizationComplianceSettings

    /**
     * A view of [ComplianceSettingService] that provides access to raw HTTP responses for each
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
        ): ComplianceSettingService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/compliance_settings`, but is
         * otherwise the same as [ComplianceSettingService.retrieve].
         */
        @MustBeClosed
        fun retrieve(): HttpResponseFor<OrganizationComplianceSettings> =
            retrieve(ComplianceSettingRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationComplianceSettings>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ComplianceSettingRetrieveParams = ComplianceSettingRetrieveParams.none()
        ): HttpResponseFor<OrganizationComplianceSettings> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            requestOptions: RequestOptions
        ): HttpResponseFor<OrganizationComplianceSettings> =
            retrieve(ComplianceSettingRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/compliance_settings`, but is
         * otherwise the same as [ComplianceSettingService.update].
         */
        @MustBeClosed
        fun update(
            params: ComplianceSettingUpdateParams
        ): HttpResponseFor<OrganizationComplianceSettings> = update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ComplianceSettingUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<OrganizationComplianceSettings>
    }
}
