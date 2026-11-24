package com.anthropic.services.async.beta.organization.providers.google.projects

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectDataControls
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface DataControlServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DataControlServiceAsync

    /**
     * Get a Google Cloud project's encryption keys and storage buckets for Claude on Google Cloud's
     * Agent Platform, with each region's status.
     *
     * Your organization must have proved that it owns the project.
     */
    fun retrieve(projectNumber: String): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        retrieve(projectNumber, DataControlRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        projectNumber: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        retrieve(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        projectNumber: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        retrieve(projectNumber, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DataControlRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedProjectDataControls>

    /** @see retrieve */
    fun retrieve(
        params: DataControlRetrieveParams
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        projectNumber: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
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
    fun update(projectNumber: String): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        update(projectNumber, DataControlUpdateParams.none())

    /** @see update */
    fun update(
        projectNumber: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        update(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

    /** @see update */
    fun update(
        projectNumber: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        update(projectNumber, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: DataControlUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedProjectDataControls>

    /** @see update */
    fun update(
        params: DataControlUpdateParams
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        projectNumber: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaGoogleManagedProjectDataControls> =
        update(projectNumber, DataControlUpdateParams.none(), requestOptions)

    /**
     * A view of [DataControlServiceAsync] that provides access to raw HTTP responses for each
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
        ): DataControlServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/providers/google/projects/{project_number}/data_controls`, but is
         * otherwise the same as [DataControlServiceAsync.retrieve].
         */
        fun retrieve(
            projectNumber: String
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            retrieve(projectNumber, DataControlRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            projectNumber: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            retrieve(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            projectNumber: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            retrieve(projectNumber, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: DataControlRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>>

        /** @see retrieve */
        fun retrieve(
            params: DataControlRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            projectNumber: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            retrieve(projectNumber, DataControlRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/providers/google/projects/{project_number}/data_controls`, but is
         * otherwise the same as [DataControlServiceAsync.update].
         */
        fun update(
            projectNumber: String
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            update(projectNumber, DataControlUpdateParams.none())

        /** @see update */
        fun update(
            projectNumber: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            update(params.toBuilder().projectNumber(projectNumber).build(), requestOptions)

        /** @see update */
        fun update(
            projectNumber: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            update(projectNumber, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: DataControlUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>>

        /** @see update */
        fun update(
            params: DataControlUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            projectNumber: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedProjectDataControls>> =
            update(projectNumber, DataControlUpdateParams.none(), requestOptions)
    }
}
