package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.externalstorage.BetaDeletedExternalStorage
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorage
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageActivateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageCreateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageDeleteParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListPageAsync
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageListParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageRetrieveParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ExternalStorageServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalStorageServiceAsync

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
    fun create(params: ExternalStorageCreateParams): CompletableFuture<BetaExternalStorage> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ExternalStorageCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage>

    /**
     * Get one External Storage in the organization.
     *
     * @throws com.anthropic.models.beta.organization.externalstorage.BetaInvalidStorageArnException
     */
    fun retrieve(externalStorageId: String): CompletableFuture<BetaExternalStorage> =
        retrieve(externalStorageId, ExternalStorageRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        externalStorageId: String,
        params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage> =
        retrieve(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        externalStorageId: String,
        params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
    ): CompletableFuture<BetaExternalStorage> =
        retrieve(externalStorageId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ExternalStorageRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage>

    /** @see retrieve */
    fun retrieve(params: ExternalStorageRetrieveParams): CompletableFuture<BetaExternalStorage> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        externalStorageId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalStorage> =
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
    ): CompletableFuture<BetaExternalStorage> =
        update(externalStorageId, params, RequestOptions.none())

    /** @see update */
    fun update(
        externalStorageId: String,
        params: ExternalStorageUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage> =
        update(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see update */
    fun update(params: ExternalStorageUpdateParams): CompletableFuture<BetaExternalStorage> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ExternalStorageUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage>

    /** List the organization's External Storage, newest first. */
    fun list(): CompletableFuture<ExternalStorageListPageAsync> =
        list(ExternalStorageListParams.none())

    /** @see list */
    fun list(
        params: ExternalStorageListParams = ExternalStorageListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ExternalStorageListPageAsync>

    /** @see list */
    fun list(
        params: ExternalStorageListParams = ExternalStorageListParams.none()
    ): CompletableFuture<ExternalStorageListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ExternalStorageListPageAsync> =
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
    fun delete(externalStorageId: String): CompletableFuture<BetaDeletedExternalStorage> =
        delete(externalStorageId, ExternalStorageDeleteParams.none())

    /** @see delete */
    fun delete(
        externalStorageId: String,
        params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedExternalStorage> =
        delete(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see delete */
    fun delete(
        externalStorageId: String,
        params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
    ): CompletableFuture<BetaDeletedExternalStorage> =
        delete(externalStorageId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: ExternalStorageDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedExternalStorage>

    /** @see delete */
    fun delete(params: ExternalStorageDeleteParams): CompletableFuture<BetaDeletedExternalStorage> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        externalStorageId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedExternalStorage> =
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
    fun activate(externalStorageId: String): CompletableFuture<BetaExternalStorage> =
        activate(externalStorageId, ExternalStorageActivateParams.none())

    /** @see activate */
    fun activate(
        externalStorageId: String,
        params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage> =
        activate(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

    /** @see activate */
    fun activate(
        externalStorageId: String,
        params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
    ): CompletableFuture<BetaExternalStorage> =
        activate(externalStorageId, params, RequestOptions.none())

    /** @see activate */
    fun activate(
        params: ExternalStorageActivateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalStorage>

    /** @see activate */
    fun activate(params: ExternalStorageActivateParams): CompletableFuture<BetaExternalStorage> =
        activate(params, RequestOptions.none())

    /** @see activate */
    fun activate(
        externalStorageId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalStorage> =
        activate(externalStorageId, ExternalStorageActivateParams.none(), requestOptions)

    /**
     * A view of [ExternalStorageServiceAsync] that provides access to raw HTTP responses for each
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
        ): ExternalStorageServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/external_storage`, but is
         * otherwise the same as [ExternalStorageServiceAsync.create].
         */
        fun create(
            params: ExternalStorageCreateParams
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: ExternalStorageCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/external_storage/{external_storage_id}`, but is otherwise the same as
         * [ExternalStorageServiceAsync.retrieve].
         */
        fun retrieve(
            externalStorageId: String
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            retrieve(externalStorageId, ExternalStorageRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            externalStorageId: String,
            params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            retrieve(
                params.toBuilder().externalStorageId(externalStorageId).build(),
                requestOptions,
            )

        /** @see retrieve */
        fun retrieve(
            externalStorageId: String,
            params: ExternalStorageRetrieveParams = ExternalStorageRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            retrieve(externalStorageId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ExternalStorageRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>>

        /** @see retrieve */
        fun retrieve(
            params: ExternalStorageRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            externalStorageId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            retrieve(externalStorageId, ExternalStorageRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_storage/{external_storage_id}`, but is otherwise the same as
         * [ExternalStorageServiceAsync.update].
         */
        fun update(
            externalStorageId: String,
            params: ExternalStorageUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            update(externalStorageId, params, RequestOptions.none())

        /** @see update */
        fun update(
            externalStorageId: String,
            params: ExternalStorageUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            update(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

        /** @see update */
        fun update(
            params: ExternalStorageUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ExternalStorageUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/external_storage`, but is
         * otherwise the same as [ExternalStorageServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<ExternalStorageListPageAsync>> =
            list(ExternalStorageListParams.none())

        /** @see list */
        fun list(
            params: ExternalStorageListParams = ExternalStorageListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ExternalStorageListPageAsync>>

        /** @see list */
        fun list(
            params: ExternalStorageListParams = ExternalStorageListParams.none()
        ): CompletableFuture<HttpResponseFor<ExternalStorageListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ExternalStorageListPageAsync>> =
            list(ExternalStorageListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/external_storage/{external_storage_id}`, but is otherwise the same as
         * [ExternalStorageServiceAsync.delete].
         */
        fun delete(
            externalStorageId: String
        ): CompletableFuture<HttpResponseFor<BetaDeletedExternalStorage>> =
            delete(externalStorageId, ExternalStorageDeleteParams.none())

        /** @see delete */
        fun delete(
            externalStorageId: String,
            params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedExternalStorage>> =
            delete(params.toBuilder().externalStorageId(externalStorageId).build(), requestOptions)

        /** @see delete */
        fun delete(
            externalStorageId: String,
            params: ExternalStorageDeleteParams = ExternalStorageDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedExternalStorage>> =
            delete(externalStorageId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: ExternalStorageDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedExternalStorage>>

        /** @see delete */
        fun delete(
            params: ExternalStorageDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaDeletedExternalStorage>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            externalStorageId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedExternalStorage>> =
            delete(externalStorageId, ExternalStorageDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_storage/{external_storage_id}/activate`, but is otherwise the
         * same as [ExternalStorageServiceAsync.activate].
         */
        fun activate(
            externalStorageId: String
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            activate(externalStorageId, ExternalStorageActivateParams.none())

        /** @see activate */
        fun activate(
            externalStorageId: String,
            params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            activate(
                params.toBuilder().externalStorageId(externalStorageId).build(),
                requestOptions,
            )

        /** @see activate */
        fun activate(
            externalStorageId: String,
            params: ExternalStorageActivateParams = ExternalStorageActivateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            activate(externalStorageId, params, RequestOptions.none())

        /** @see activate */
        fun activate(
            params: ExternalStorageActivateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>>

        /** @see activate */
        fun activate(
            params: ExternalStorageActivateParams
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            activate(params, RequestOptions.none())

        /** @see activate */
        fun activate(
            externalStorageId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalStorage>> =
            activate(externalStorageId, ExternalStorageActivateParams.none(), requestOptions)
    }
}
