package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.externalstorage.BetaDeletedExternalStorage
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorage
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageActivateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageCreateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageDeleteParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListPage
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageRetrieveParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ExternalStorageService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalStorageService

    /**
     * Register a bucket in the organization's own cloud account. It is saved as
     * `pending_configuration` and verified when activated.
     *
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaGrantRequiredException
     * @throws com.anthropic.models.beta.organization.BetaOrganizationNotEligibleException
     * @throws
     *   com.anthropic.models.beta.organization.externalstorage.BetaProviderNotAllowedException
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaUnsupportedRegionException
     * @throws com.anthropic.models.userprofiles.UserProfileNotFoundException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     * @throws
     *   com.anthropic.models.beta.organization.externalstorage.BetaStorageAlreadyRegisteredException
     * @throws com.anthropic.models.beta.organization.BetaAuthorizationCheckUnavailableException
     */
    fun create(params: ExternalStorageCreateParams): BetaExternalStorage =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ExternalStorageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage

    /**
     * Get one External Storage in the organization.
     *
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaInvalidStorageArnException
     */
    fun retrieve(externalStorageId: String): BetaExternalStorage =
        retrieve(externalStorageId, ExternalStorageRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        externalStorageId: String,
        params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage =
        retrieve(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        externalStorageId: String,
        params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
    ): BetaExternalStorage = retrieve(externalStorageId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ExternalStorageRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage

    /** @see retrieve */
    fun retrieve(params: ExternalStorageRetrieveParams): BetaExternalStorage =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(externalStorageId: String, requestOptions: RequestOptions): BetaExternalStorage =
        retrieve(externalStorageId, ExternalStorageRetrieveParams.none(), requestOptions)

    /**
     * Rename External Storage or clear its name, in any status.
     *
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaInvalidStorageArnException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     */
    fun update(
        externalStorageId: String,
        params: ExternalStorageUpdateParams,
    ): BetaExternalStorage = update(externalStorageId, params, RequestOptions.none())

    /** @see update */
    fun update(
        externalStorageId: String,
        params: ExternalStorageUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage =
        update(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see update */
    fun update(params: ExternalStorageUpdateParams): BetaExternalStorage =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ExternalStorageUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage

    /** List the organization's External Storage, newest first. */
    fun list(): ExternalStorageListPage = list(ExternalStorageListParams.none())

    /** @see list */
    fun list(
        params: ExternalStorageListParams = ExternalStorageListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExternalStorageListPage

    /** @see list */
    fun list(
        params: ExternalStorageListParams = ExternalStorageListParams.none()
    ): ExternalStorageListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ExternalStorageListPage =
        list(ExternalStorageListParams.none(), requestOptions)

    /**
     * Delete External Storage. Storage that is pending configuration, available, or active but
     * never attached is deleted at once, unless an Enterprise Frontier Safeguards workspace policy
     * lists it. Other storage gets a 400 that says what to do next. On `draining` storage that 400
     * can mean this delete started a removal that can't be undone. To check, get the storage: if
     * `status.removal_started` is true, delete it again later to finish.
     *
     * @throws
     *   com.anthropic.models.beta.organization.externalstorage.BetaExternalStorageInUseException
     * @throws
     *   com.anthropic.models.beta.organization.externalstorage.BetaExternalStorageInWorkspacePolicyException
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaInvalidStorageArnException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     */
    fun delete(externalStorageId: String): BetaDeletedExternalStorage =
        delete(externalStorageId, ExternalStorageDeleteParams.none())

    /** @see delete */
    fun delete(
        externalStorageId: String,
        params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedExternalStorage =
        delete(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see delete */
    fun delete(
        externalStorageId: String,
        params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
    ): BetaDeletedExternalStorage = delete(externalStorageId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: ExternalStorageDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedExternalStorage

    /** @see delete */
    fun delete(params: ExternalStorageDeleteParams): BetaDeletedExternalStorage =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        externalStorageId: String,
        requestOptions: RequestOptions,
    ): BetaDeletedExternalStorage =
        delete(externalStorageId, ExternalStorageDeleteParams.none(), requestOptions)

    /**
     * Verify pending External Storage and make it available. Storage that is already available,
     * active or draining comes back unchanged, without any check.
     *
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaGrantRequiredException
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaInvalidStorageArnException
     * @throws com.anthropic.models.beta.organization.BetaOrganizationNotEligibleException
     * @throws
     *   com.anthropic.models.beta.organization.externalstorage.BetaProviderNotAllowedException
     * @throws
     *   com.anthropic.models.beta.organization.externalstorage.BetaStorageVerificationFailedException
     * @throws com.anthropic.models.userprofiles.UserProfileNotFoundException
     * @throws com.anthropic.models.SandboxEgressRestrictedException
     * @throws com.anthropic.models.beta.organization.BetaAuthorizationCheckUnavailableException
     */
    fun activate(externalStorageId: String): BetaExternalStorage =
        activate(externalStorageId, ExternalStorageActivateParams.none())

    /** @see activate */
    fun activate(
        externalStorageId: String,
        params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage =
        activate(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see activate */
    fun activate(
        externalStorageId: String,
        params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
    ): BetaExternalStorage = activate(externalStorageId, params, RequestOptions.none())

    /** @see activate */
    fun activate(
        params: ExternalStorageActivateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalStorage

    /** @see activate */
    fun activate(params: ExternalStorageActivateParams): BetaExternalStorage =
        activate(params, RequestOptions.none())

    /** @see activate */
    fun activate(externalStorageId: String, requestOptions: RequestOptions): BetaExternalStorage =
        activate(externalStorageId, ExternalStorageActivateParams.none(), requestOptions)

    /**
     * A view of [ExternalStorageService] that provides access to raw HTTP responses for each
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
        ): ExternalStorageService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/external_storage`, but is
         * otherwise the same as [ExternalStorageService.create].
         */
        @MustBeClosed
        fun create(params: ExternalStorageCreateParams): HttpResponseFor<BetaExternalStorage> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: ExternalStorageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/external_storage/{external_storage_id}`, but is otherwise the same as
         * [ExternalStorageService.retrieve].
         */
        @MustBeClosed
        fun retrieve(externalStorageId: String): HttpResponseFor<BetaExternalStorage> =
            retrieve(externalStorageId, ExternalStorageRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            externalStorageId: String,
            params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage> =
            retrieve(
                params.toBuilder().externalStorageId(externalStorageId).build(),
                requestOptions,
            )

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            externalStorageId: String,
            params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
        ): HttpResponseFor<BetaExternalStorage> =
            retrieve(externalStorageId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ExternalStorageRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ExternalStorageRetrieveParams): HttpResponseFor<BetaExternalStorage> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            externalStorageId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalStorage> =
            retrieve(externalStorageId, ExternalStorageRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_storage/{external_storage_id}`, but is otherwise the same as
         * [ExternalStorageService.update].
         */
        @MustBeClosed
        fun update(
            externalStorageId: String,
            params: ExternalStorageUpdateParams,
        ): HttpResponseFor<BetaExternalStorage> =
            update(externalStorageId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            externalStorageId: String,
            params: ExternalStorageUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage> =
            update(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: ExternalStorageUpdateParams): HttpResponseFor<BetaExternalStorage> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ExternalStorageUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/external_storage`, but is
         * otherwise the same as [ExternalStorageService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<ExternalStorageListPage> =
            list(ExternalStorageListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ExternalStorageListParams = ExternalStorageListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExternalStorageListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: ExternalStorageListParams = ExternalStorageListParams.none()
        ): HttpResponseFor<ExternalStorageListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ExternalStorageListPage> =
            list(ExternalStorageListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/external_storage/{external_storage_id}`, but is otherwise the same as
         * [ExternalStorageService.delete].
         */
        @MustBeClosed
        fun delete(externalStorageId: String): HttpResponseFor<BetaDeletedExternalStorage> =
            delete(externalStorageId, ExternalStorageDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            externalStorageId: String,
            params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedExternalStorage> =
            delete(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            externalStorageId: String,
            params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedExternalStorage> =
            delete(externalStorageId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: ExternalStorageDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedExternalStorage>

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: ExternalStorageDeleteParams
        ): HttpResponseFor<BetaDeletedExternalStorage> = delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            externalStorageId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedExternalStorage> =
            delete(externalStorageId, ExternalStorageDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_storage/{external_storage_id}/activate`, but is otherwise the
         * same as [ExternalStorageService.activate].
         */
        @MustBeClosed
        fun activate(externalStorageId: String): HttpResponseFor<BetaExternalStorage> =
            activate(externalStorageId, ExternalStorageActivateParams.none())

        /** @see activate */
        @MustBeClosed
        fun activate(
            externalStorageId: String,
            params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage> =
            activate(
                params.toBuilder().externalStorageId(externalStorageId).build(),
                requestOptions,
            )

        /** @see activate */
        @MustBeClosed
        fun activate(
            externalStorageId: String,
            params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
        ): HttpResponseFor<BetaExternalStorage> =
            activate(externalStorageId, params, RequestOptions.none())

        /** @see activate */
        @MustBeClosed
        fun activate(
            params: ExternalStorageActivateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalStorage>

        /** @see activate */
        @MustBeClosed
        fun activate(params: ExternalStorageActivateParams): HttpResponseFor<BetaExternalStorage> =
            activate(params, RequestOptions.none())

        /** @see activate */
        @MustBeClosed
        fun activate(
            externalStorageId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalStorage> =
            activate(externalStorageId, ExternalStorageActivateParams.none(), requestOptions)
    }
}
