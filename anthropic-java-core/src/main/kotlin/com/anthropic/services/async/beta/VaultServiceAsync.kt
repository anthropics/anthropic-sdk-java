// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.vaults.BetaManagedAgentsDeletedVault
import com.anthropic.models.beta.vaults.BetaManagedAgentsVault
import com.anthropic.models.beta.vaults.VaultArchiveParams
import com.anthropic.models.beta.vaults.VaultCreateParams
import com.anthropic.models.beta.vaults.VaultDeleteParams
import com.anthropic.models.beta.vaults.VaultListPageAsync
import com.anthropic.models.beta.vaults.VaultListParams
import com.anthropic.models.beta.vaults.VaultRetrieveParams
import com.anthropic.models.beta.vaults.VaultUpdateParams
import com.anthropic.services.async.beta.vaults.CredentialServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface VaultServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultServiceAsync

    fun credentials(): CredentialServiceAsync

    /** Create Vault */
    fun create(params: VaultCreateParams): CompletableFuture<BetaManagedAgentsVault> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VaultCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault>

    /** Get Vault */
    fun retrieve(vaultId: String): CompletableFuture<BetaManagedAgentsVault> =
        retrieve(vaultId, VaultRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        vaultId: String,
        params: VaultRetrieveParams = VaultRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault> =
        retrieve(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        vaultId: String,
        params: VaultRetrieveParams = VaultRetrieveParams.none(),
    ): CompletableFuture<BetaManagedAgentsVault> = retrieve(vaultId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VaultRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault>

    /** @see retrieve */
    fun retrieve(params: VaultRetrieveParams): CompletableFuture<BetaManagedAgentsVault> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        vaultId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsVault> =
        retrieve(vaultId, VaultRetrieveParams.none(), requestOptions)

    /** Update Vault */
    fun update(vaultId: String): CompletableFuture<BetaManagedAgentsVault> =
        update(vaultId, VaultUpdateParams.none())

    /** @see update */
    fun update(
        vaultId: String,
        params: VaultUpdateParams = VaultUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault> =
        update(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see update */
    fun update(
        vaultId: String,
        params: VaultUpdateParams = VaultUpdateParams.none(),
    ): CompletableFuture<BetaManagedAgentsVault> = update(vaultId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: VaultUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault>

    /** @see update */
    fun update(params: VaultUpdateParams): CompletableFuture<BetaManagedAgentsVault> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        vaultId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsVault> =
        update(vaultId, VaultUpdateParams.none(), requestOptions)

    /** List Vaults */
    fun list(): CompletableFuture<VaultListPageAsync> = list(VaultListParams.none())

    /** @see list */
    fun list(
        params: VaultListParams = VaultListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<VaultListPageAsync>

    /** @see list */
    fun list(
        params: VaultListParams = VaultListParams.none()
    ): CompletableFuture<VaultListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<VaultListPageAsync> =
        list(VaultListParams.none(), requestOptions)

    /** Delete Vault */
    fun delete(vaultId: String): CompletableFuture<BetaManagedAgentsDeletedVault> =
        delete(vaultId, VaultDeleteParams.none())

    /** @see delete */
    fun delete(
        vaultId: String,
        params: VaultDeleteParams = VaultDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedVault> =
        delete(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see delete */
    fun delete(
        vaultId: String,
        params: VaultDeleteParams = VaultDeleteParams.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedVault> =
        delete(vaultId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: VaultDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedVault>

    /** @see delete */
    fun delete(params: VaultDeleteParams): CompletableFuture<BetaManagedAgentsDeletedVault> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        vaultId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeletedVault> =
        delete(vaultId, VaultDeleteParams.none(), requestOptions)

    /** Archive Vault */
    fun archive(vaultId: String): CompletableFuture<BetaManagedAgentsVault> =
        archive(vaultId, VaultArchiveParams.none())

    /** @see archive */
    fun archive(
        vaultId: String,
        params: VaultArchiveParams = VaultArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault> =
        archive(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see archive */
    fun archive(
        vaultId: String,
        params: VaultArchiveParams = VaultArchiveParams.none(),
    ): CompletableFuture<BetaManagedAgentsVault> = archive(vaultId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: VaultArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsVault>

    /** @see archive */
    fun archive(params: VaultArchiveParams): CompletableFuture<BetaManagedAgentsVault> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        vaultId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsVault> =
        archive(vaultId, VaultArchiveParams.none(), requestOptions)

    /** A view of [VaultServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VaultServiceAsync.WithRawResponse

        fun credentials(): CredentialServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/vaults?beta=true`, but is otherwise the same as
         * [VaultServiceAsync.create].
         */
        fun create(
            params: VaultCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: VaultCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>>

        /**
         * Returns a raw HTTP response for `get /v1/vaults/{vault_id}?beta=true`, but is otherwise
         * the same as [VaultServiceAsync.retrieve].
         */
        fun retrieve(vaultId: String): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            retrieve(vaultId, VaultRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            vaultId: String,
            params: VaultRetrieveParams = VaultRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            retrieve(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            vaultId: String,
            params: VaultRetrieveParams = VaultRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            retrieve(vaultId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: VaultRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>>

        /** @see retrieve */
        fun retrieve(
            params: VaultRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            vaultId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            retrieve(vaultId, VaultRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/vaults/{vault_id}?beta=true`, but is otherwise
         * the same as [VaultServiceAsync.update].
         */
        fun update(vaultId: String): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            update(vaultId, VaultUpdateParams.none())

        /** @see update */
        fun update(
            vaultId: String,
            params: VaultUpdateParams = VaultUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            update(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see update */
        fun update(
            vaultId: String,
            params: VaultUpdateParams = VaultUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            update(vaultId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: VaultUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>>

        /** @see update */
        fun update(
            params: VaultUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            vaultId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            update(vaultId, VaultUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/vaults?beta=true`, but is otherwise the same as
         * [VaultServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<VaultListPageAsync>> =
            list(VaultListParams.none())

        /** @see list */
        fun list(
            params: VaultListParams = VaultListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<VaultListPageAsync>>

        /** @see list */
        fun list(
            params: VaultListParams = VaultListParams.none()
        ): CompletableFuture<HttpResponseFor<VaultListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<VaultListPageAsync>> =
            list(VaultListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/vaults/{vault_id}?beta=true`, but is
         * otherwise the same as [VaultServiceAsync.delete].
         */
        fun delete(
            vaultId: String
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedVault>> =
            delete(vaultId, VaultDeleteParams.none())

        /** @see delete */
        fun delete(
            vaultId: String,
            params: VaultDeleteParams = VaultDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedVault>> =
            delete(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see delete */
        fun delete(
            vaultId: String,
            params: VaultDeleteParams = VaultDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedVault>> =
            delete(vaultId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: VaultDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedVault>>

        /** @see delete */
        fun delete(
            params: VaultDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedVault>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            vaultId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedVault>> =
            delete(vaultId, VaultDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/vaults/{vault_id}/archive?beta=true`, but is
         * otherwise the same as [VaultServiceAsync.archive].
         */
        fun archive(vaultId: String): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            archive(vaultId, VaultArchiveParams.none())

        /** @see archive */
        fun archive(
            vaultId: String,
            params: VaultArchiveParams = VaultArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            archive(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see archive */
        fun archive(
            vaultId: String,
            params: VaultArchiveParams = VaultArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            archive(vaultId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: VaultArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>>

        /** @see archive */
        fun archive(
            params: VaultArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            vaultId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsVault>> =
            archive(vaultId, VaultArchiveParams.none(), requestOptions)
    }
}
