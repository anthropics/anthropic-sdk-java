// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.vaults.BetaManagedAgentsDeletedVault
import com.anthropic.models.beta.vaults.BetaManagedAgentsVault
import com.anthropic.models.beta.vaults.VaultArchiveParams
import com.anthropic.models.beta.vaults.VaultCreateParams
import com.anthropic.models.beta.vaults.VaultDeleteParams
import com.anthropic.models.beta.vaults.VaultListPage
import com.anthropic.models.beta.vaults.VaultListParams
import com.anthropic.models.beta.vaults.VaultRetrieveParams
import com.anthropic.models.beta.vaults.VaultUpdateParams
import com.anthropic.services.blocking.beta.vaults.CredentialService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface VaultService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultService

    fun credentials(): CredentialService

    /** Create Vault */
    fun create(params: VaultCreateParams): BetaManagedAgentsVault =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VaultCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault

    /** Get Vault */
    fun retrieve(vaultId: String): BetaManagedAgentsVault =
        retrieve(vaultId, VaultRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        vaultId: String,
        params: VaultRetrieveParams = VaultRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault =
        retrieve(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        vaultId: String,
        params: VaultRetrieveParams = VaultRetrieveParams.none(),
    ): BetaManagedAgentsVault = retrieve(vaultId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VaultRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault

    /** @see retrieve */
    fun retrieve(params: VaultRetrieveParams): BetaManagedAgentsVault =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(vaultId: String, requestOptions: RequestOptions): BetaManagedAgentsVault =
        retrieve(vaultId, VaultRetrieveParams.none(), requestOptions)

    /** Update Vault */
    fun update(vaultId: String): BetaManagedAgentsVault = update(vaultId, VaultUpdateParams.none())

    /** @see update */
    fun update(
        vaultId: String,
        params: VaultUpdateParams = VaultUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault = update(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see update */
    fun update(
        vaultId: String,
        params: VaultUpdateParams = VaultUpdateParams.none(),
    ): BetaManagedAgentsVault = update(vaultId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: VaultUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault

    /** @see update */
    fun update(params: VaultUpdateParams): BetaManagedAgentsVault =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(vaultId: String, requestOptions: RequestOptions): BetaManagedAgentsVault =
        update(vaultId, VaultUpdateParams.none(), requestOptions)

    /** List Vaults */
    fun list(): VaultListPage = list(VaultListParams.none())

    /** @see list */
    fun list(
        params: VaultListParams = VaultListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VaultListPage

    /** @see list */
    fun list(params: VaultListParams = VaultListParams.none()): VaultListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): VaultListPage =
        list(VaultListParams.none(), requestOptions)

    /** Delete Vault */
    fun delete(vaultId: String): BetaManagedAgentsDeletedVault =
        delete(vaultId, VaultDeleteParams.none())

    /** @see delete */
    fun delete(
        vaultId: String,
        params: VaultDeleteParams = VaultDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedVault =
        delete(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see delete */
    fun delete(
        vaultId: String,
        params: VaultDeleteParams = VaultDeleteParams.none(),
    ): BetaManagedAgentsDeletedVault = delete(vaultId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: VaultDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedVault

    /** @see delete */
    fun delete(params: VaultDeleteParams): BetaManagedAgentsDeletedVault =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(vaultId: String, requestOptions: RequestOptions): BetaManagedAgentsDeletedVault =
        delete(vaultId, VaultDeleteParams.none(), requestOptions)

    /** Archive Vault */
    fun archive(vaultId: String): BetaManagedAgentsVault =
        archive(vaultId, VaultArchiveParams.none())

    /** @see archive */
    fun archive(
        vaultId: String,
        params: VaultArchiveParams = VaultArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault = archive(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see archive */
    fun archive(
        vaultId: String,
        params: VaultArchiveParams = VaultArchiveParams.none(),
    ): BetaManagedAgentsVault = archive(vaultId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: VaultArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsVault

    /** @see archive */
    fun archive(params: VaultArchiveParams): BetaManagedAgentsVault =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(vaultId: String, requestOptions: RequestOptions): BetaManagedAgentsVault =
        archive(vaultId, VaultArchiveParams.none(), requestOptions)

    /** A view of [VaultService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultService.WithRawResponse

        fun credentials(): CredentialService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/vaults?beta=true`, but is otherwise the same as
         * [VaultService.create].
         */
        @MustBeClosed
        fun create(params: VaultCreateParams): HttpResponseFor<BetaManagedAgentsVault> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: VaultCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault>

        /**
         * Returns a raw HTTP response for `get /v1/vaults/{vault_id}?beta=true`, but is otherwise
         * the same as [VaultService.retrieve].
         */
        @MustBeClosed
        fun retrieve(vaultId: String): HttpResponseFor<BetaManagedAgentsVault> =
            retrieve(vaultId, VaultRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            vaultId: String,
            params: VaultRetrieveParams = VaultRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault> =
            retrieve(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            vaultId: String,
            params: VaultRetrieveParams = VaultRetrieveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsVault> =
            retrieve(vaultId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: VaultRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: VaultRetrieveParams): HttpResponseFor<BetaManagedAgentsVault> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            vaultId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> =
            retrieve(vaultId, VaultRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/vaults/{vault_id}?beta=true`, but is otherwise
         * the same as [VaultService.update].
         */
        @MustBeClosed
        fun update(vaultId: String): HttpResponseFor<BetaManagedAgentsVault> =
            update(vaultId, VaultUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            vaultId: String,
            params: VaultUpdateParams = VaultUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault> =
            update(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            vaultId: String,
            params: VaultUpdateParams = VaultUpdateParams.none(),
        ): HttpResponseFor<BetaManagedAgentsVault> = update(vaultId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: VaultUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault>

        /** @see update */
        @MustBeClosed
        fun update(params: VaultUpdateParams): HttpResponseFor<BetaManagedAgentsVault> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            vaultId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> =
            update(vaultId, VaultUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/vaults?beta=true`, but is otherwise the same as
         * [VaultService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<VaultListPage> = list(VaultListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: VaultListParams = VaultListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VaultListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: VaultListParams = VaultListParams.none()): HttpResponseFor<VaultListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<VaultListPage> =
            list(VaultListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/vaults/{vault_id}?beta=true`, but is
         * otherwise the same as [VaultService.delete].
         */
        @MustBeClosed
        fun delete(vaultId: String): HttpResponseFor<BetaManagedAgentsDeletedVault> =
            delete(vaultId, VaultDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            vaultId: String,
            params: VaultDeleteParams = VaultDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedVault> =
            delete(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            vaultId: String,
            params: VaultDeleteParams = VaultDeleteParams.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedVault> =
            delete(vaultId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: VaultDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedVault>

        /** @see delete */
        @MustBeClosed
        fun delete(params: VaultDeleteParams): HttpResponseFor<BetaManagedAgentsDeletedVault> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            vaultId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedVault> =
            delete(vaultId, VaultDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/vaults/{vault_id}/archive?beta=true`, but is
         * otherwise the same as [VaultService.archive].
         */
        @MustBeClosed
        fun archive(vaultId: String): HttpResponseFor<BetaManagedAgentsVault> =
            archive(vaultId, VaultArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            vaultId: String,
            params: VaultArchiveParams = VaultArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault> =
            archive(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            vaultId: String,
            params: VaultArchiveParams = VaultArchiveParams.none(),
        ): HttpResponseFor<BetaManagedAgentsVault> = archive(vaultId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: VaultArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsVault>

        /** @see archive */
        @MustBeClosed
        fun archive(params: VaultArchiveParams): HttpResponseFor<BetaManagedAgentsVault> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            vaultId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> =
            archive(vaultId, VaultArchiveParams.none(), requestOptions)
    }
}
