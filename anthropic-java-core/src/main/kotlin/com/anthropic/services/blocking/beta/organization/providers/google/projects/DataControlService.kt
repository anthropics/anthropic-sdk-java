package com.anthropic.services.blocking.beta.organization.providers.google.projects

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectDataControls
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface DataControlService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DataControlService

    /**
     * Get a Google Cloud project's encryption keys and storage buckets for Claude on Google Cloud's
     * Agent Platform, with each region's status.
     *
     * Your organization must have proved that it owns the project.
     */
    fun retrieve(projectNumber: String): BetaGoogleManagedProjectDataControls =
        retrieve(projectNumber, DataControlRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        projectNumber: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedProjectDataControls =
        retrieve(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        projectNumber: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
    ): BetaGoogleManagedProjectDataControls = retrieve(projectNumber, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DataControlRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedProjectDataControls

    /** @see retrieve */
    fun retrieve(params: DataControlRetrieveParams): BetaGoogleManagedProjectDataControls =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        projectNumber: String,
        requestOptions: RequestOptions,
    ): BetaGoogleManagedProjectDataControls =
        retrieve(projectNumber, DataControlRetrieveParams.none(), requestOptions)

    /**
     * Turn a control on or off, or change the key or bucket set for a region. Controls and regions
     * that you leave out stay as they are.
     *
     * Your organization must have proved that it owns the project. Only one organization at a time
     * can have controls turned on for a project.
     *
     * **Requires a credential that identifies a user or a service account**: an OAuth access token
     * with the `org:admin` scope, or an organization-scoped API key that belongs to a user or
     * service account whose organization role can update these controls. The `admin`, `owner` and
     * `primary_owner` roles can. An Admin API key gets a `403` error.
     *
     * @throws com.anthropic.models.beta.BetaCmekNotApprovedException
     * @throws com.anthropic.models.beta.BetaExternalKeyNotFoundException
     * @throws com.anthropic.models.beta.BetaExternalKeyProviderMismatchException
     * @throws com.anthropic.models.beta.BetaGcpProjectNotVerifiedException
     * @throws com.anthropic.models.organization.workspaces.KeyProfileMismatchException
     * @throws com.anthropic.models.beta.organization.BetaOrganizationNotEligibleException
     * @throws com.anthropic.models.beta.BetaRegionNotSupportedException
     * @throws com.anthropic.models.beta.BetaStorageNotApprovedException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     * @throws com.anthropic.models.beta.BetaGcpProjectControlledByAnotherOrganizationException
     * @throws com.anthropic.models.beta.BetaStorageInUseException
     * @throws com.anthropic.models.beta.organization.BetaAuthorizationCheckUnavailableException
     */
    fun update(projectNumber: String): BetaGoogleManagedProjectDataControls =
        update(projectNumber, DataControlUpdateParams.none())

    /** @see update */
    fun update(
        projectNumber: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedProjectDataControls =
        update(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

    /** @see update */
    fun update(
        projectNumber: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
    ): BetaGoogleManagedProjectDataControls = update(projectNumber, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: DataControlUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedProjectDataControls

    /** @see update */
    fun update(params: DataControlUpdateParams): BetaGoogleManagedProjectDataControls =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        projectNumber: String,
        requestOptions: RequestOptions,
    ): BetaGoogleManagedProjectDataControls =
        update(projectNumber, DataControlUpdateParams.none(), requestOptions)

    /**
     * A view of [DataControlService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DataControlService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/providers/google/projects/{project_number}/data_controls`, but is
         * otherwise the same as [DataControlService.retrieve].
         */
        @MustBeClosed
        fun retrieve(projectNumber: String): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            retrieve(projectNumber, DataControlRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            projectNumber: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            retrieve(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            projectNumber: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            retrieve(projectNumber, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DataControlRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DataControlRetrieveParams
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            projectNumber: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            retrieve(projectNumber, DataControlRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/providers/google/projects/{project_number}/data_controls`, but is
         * otherwise the same as [DataControlService.update].
         */
        @MustBeClosed
        fun update(projectNumber: String): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            update(projectNumber, DataControlUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            projectNumber: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            update(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            projectNumber: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            update(projectNumber, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: DataControlUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls>

        /** @see update */
        @MustBeClosed
        fun update(
            params: DataControlUpdateParams
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            projectNumber: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaGoogleManagedProjectDataControls> =
            update(projectNumber, DataControlUpdateParams.none(), requestOptions)
    }
}
