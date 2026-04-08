// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.vaults

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsCredential
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsDeletedCredential
import com.anthropic.models.beta.vaults.credentials.CredentialArchiveParams
import com.anthropic.models.beta.vaults.credentials.CredentialCreateParams
import com.anthropic.models.beta.vaults.credentials.CredentialDeleteParams
import com.anthropic.models.beta.vaults.credentials.CredentialListPage
import com.anthropic.models.beta.vaults.credentials.CredentialListParams
import com.anthropic.models.beta.vaults.credentials.CredentialRetrieveParams
import com.anthropic.models.beta.vaults.credentials.CredentialUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface CredentialService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialService

    /** Create Credential */
    fun create(vaultId: String, params: CredentialCreateParams): BetaManagedAgentsCredential =
        create(vaultId, params, RequestOptions.none())

    /** @see create */
    fun create(
        vaultId: String,
        params: CredentialCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential =
        create(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see create */
    fun create(params: CredentialCreateParams): BetaManagedAgentsCredential =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential

    /** Get Credential */
    fun retrieve(
        credentialId: String,
        params: CredentialRetrieveParams,
    ): BetaManagedAgentsCredential = retrieve(credentialId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        credentialId: String,
        params: CredentialRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential =
        retrieve(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: CredentialRetrieveParams): BetaManagedAgentsCredential =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: CredentialRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential

    /** Update Credential */
    fun update(credentialId: String, params: CredentialUpdateParams): BetaManagedAgentsCredential =
        update(credentialId, params, RequestOptions.none())

    /** @see update */
    fun update(
        credentialId: String,
        params: CredentialUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential =
        update(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see update */
    fun update(params: CredentialUpdateParams): BetaManagedAgentsCredential =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential

    /** List Credentials */
    fun list(vaultId: String): CredentialListPage = list(vaultId, CredentialListParams.none())

    /** @see list */
    fun list(
        vaultId: String,
        params: CredentialListParams = CredentialListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CredentialListPage = list(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see list */
    fun list(
        vaultId: String,
        params: CredentialListParams = CredentialListParams.none(),
    ): CredentialListPage = list(vaultId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CredentialListPage

    /** @see list */
    fun list(params: CredentialListParams): CredentialListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(vaultId: String, requestOptions: RequestOptions): CredentialListPage =
        list(vaultId, CredentialListParams.none(), requestOptions)

    /** Delete Credential */
    fun delete(
        credentialId: String,
        params: CredentialDeleteParams,
    ): BetaManagedAgentsDeletedCredential = delete(credentialId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        credentialId: String,
        params: CredentialDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedCredential =
        delete(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see delete */
    fun delete(params: CredentialDeleteParams): BetaManagedAgentsDeletedCredential =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsDeletedCredential

    /** Archive Credential */
    fun archive(
        credentialId: String,
        params: CredentialArchiveParams,
    ): BetaManagedAgentsCredential = archive(credentialId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        credentialId: String,
        params: CredentialArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential =
        archive(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see archive */
    fun archive(params: CredentialArchiveParams): BetaManagedAgentsCredential =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: CredentialArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaManagedAgentsCredential

    /** A view of [CredentialService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/vaults/{vault_id}/credentials?beta=true`, but
         * is otherwise the same as [CredentialService.create].
         */
        @MustBeClosed
        fun create(
            vaultId: String,
            params: CredentialCreateParams,
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            create(vaultId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            vaultId: String,
            params: CredentialCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            create(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: CredentialCreateParams): HttpResponseFor<BetaManagedAgentsCredential> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential>

        /**
         * Returns a raw HTTP response for `get
         * /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true`, but is otherwise the same
         * as [CredentialService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            credentialId: String,
            params: CredentialRetrieveParams,
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            retrieve(credentialId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            credentialId: String,
            params: CredentialRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            retrieve(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: CredentialRetrieveParams
        ): HttpResponseFor<BetaManagedAgentsCredential> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: CredentialRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential>

        /**
         * Returns a raw HTTP response for `post
         * /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true`, but is otherwise the same
         * as [CredentialService.update].
         */
        @MustBeClosed
        fun update(
            credentialId: String,
            params: CredentialUpdateParams,
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            update(credentialId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            credentialId: String,
            params: CredentialUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            update(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: CredentialUpdateParams): HttpResponseFor<BetaManagedAgentsCredential> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential>

        /**
         * Returns a raw HTTP response for `get /v1/vaults/{vault_id}/credentials?beta=true`, but is
         * otherwise the same as [CredentialService.list].
         */
        @MustBeClosed
        fun list(vaultId: String): HttpResponseFor<CredentialListPage> =
            list(vaultId, CredentialListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            vaultId: String,
            params: CredentialListParams = CredentialListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CredentialListPage> =
            list(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            vaultId: String,
            params: CredentialListParams = CredentialListParams.none(),
        ): HttpResponseFor<CredentialListPage> = list(vaultId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CredentialListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: CredentialListParams): HttpResponseFor<CredentialListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            vaultId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialListPage> =
            list(vaultId, CredentialListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true`, but is otherwise the same
         * as [CredentialService.delete].
         */
        @MustBeClosed
        fun delete(
            credentialId: String,
            params: CredentialDeleteParams,
        ): HttpResponseFor<BetaManagedAgentsDeletedCredential> =
            delete(credentialId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            credentialId: String,
            params: CredentialDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedCredential> =
            delete(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: CredentialDeleteParams
        ): HttpResponseFor<BetaManagedAgentsDeletedCredential> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsDeletedCredential>

        /**
         * Returns a raw HTTP response for `post
         * /v1/vaults/{vault_id}/credentials/{credential_id}/archive?beta=true`, but is otherwise
         * the same as [CredentialService.archive].
         */
        @MustBeClosed
        fun archive(
            credentialId: String,
            params: CredentialArchiveParams,
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            archive(credentialId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            credentialId: String,
            params: CredentialArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential> =
            archive(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(params: CredentialArchiveParams): HttpResponseFor<BetaManagedAgentsCredential> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: CredentialArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaManagedAgentsCredential>
    }
}
