// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.vaults

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsCredential
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsCredentialValidation
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsDeletedCredential
import com.anthropic.models.beta.vaults.credentials.CredentialArchiveParams
import com.anthropic.models.beta.vaults.credentials.CredentialCreateParams
import com.anthropic.models.beta.vaults.credentials.CredentialDeleteParams
import com.anthropic.models.beta.vaults.credentials.CredentialListPageAsync
import com.anthropic.models.beta.vaults.credentials.CredentialListParams
import com.anthropic.models.beta.vaults.credentials.CredentialMcpOAuthValidateParams
import com.anthropic.models.beta.vaults.credentials.CredentialRetrieveParams
import com.anthropic.models.beta.vaults.credentials.CredentialUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CredentialServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialServiceAsync

    /** Create Credential */
    fun create(
        vaultId: String,
        params: CredentialCreateParams,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        create(vaultId, params, RequestOptions.none())

    /** @see create */
    fun create(
        vaultId: String,
        params: CredentialCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential> =
        create(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see create */
    fun create(params: CredentialCreateParams): CompletableFuture<BetaManagedAgentsCredential> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential>

    /** Get Credential */
    fun retrieve(
        credentialId: String,
        params: CredentialRetrieveParams,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        retrieve(credentialId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        credentialId: String,
        params: CredentialRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential> =
        retrieve(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: CredentialRetrieveParams): CompletableFuture<BetaManagedAgentsCredential> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: CredentialRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential>

    /** Update Credential */
    fun update(
        credentialId: String,
        params: CredentialUpdateParams,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        update(credentialId, params, RequestOptions.none())

    /** @see update */
    fun update(
        credentialId: String,
        params: CredentialUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential> =
        update(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see update */
    fun update(params: CredentialUpdateParams): CompletableFuture<BetaManagedAgentsCredential> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential>

    /** List Credentials */
    fun list(vaultId: String): CompletableFuture<CredentialListPageAsync> =
        list(vaultId, CredentialListParams.none())

    /** @see list */
    fun list(
        vaultId: String,
        params: CredentialListParams = CredentialListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CredentialListPageAsync> =
        list(params.toBuilder().vaultId(vaultId).build(), requestOptions)

    /** @see list */
    fun list(
        vaultId: String,
        params: CredentialListParams = CredentialListParams.none(),
    ): CompletableFuture<CredentialListPageAsync> = list(vaultId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CredentialListPageAsync>

    /** @see list */
    fun list(params: CredentialListParams): CompletableFuture<CredentialListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        vaultId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<CredentialListPageAsync> =
        list(vaultId, CredentialListParams.none(), requestOptions)

    /** Delete Credential */
    fun delete(
        credentialId: String,
        params: CredentialDeleteParams,
    ): CompletableFuture<BetaManagedAgentsDeletedCredential> =
        delete(credentialId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        credentialId: String,
        params: CredentialDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedCredential> =
        delete(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see delete */
    fun delete(
        params: CredentialDeleteParams
    ): CompletableFuture<BetaManagedAgentsDeletedCredential> = delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsDeletedCredential>

    /** Archive Credential */
    fun archive(
        credentialId: String,
        params: CredentialArchiveParams,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        archive(credentialId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        credentialId: String,
        params: CredentialArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential> =
        archive(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see archive */
    fun archive(params: CredentialArchiveParams): CompletableFuture<BetaManagedAgentsCredential> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: CredentialArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredential>

    /** Validate Credential */
    fun mcpOAuthValidate(
        credentialId: String,
        params: CredentialMcpOAuthValidateParams,
    ): CompletableFuture<BetaManagedAgentsCredentialValidation> =
        mcpOAuthValidate(credentialId, params, RequestOptions.none())

    /** @see mcpOAuthValidate */
    fun mcpOAuthValidate(
        credentialId: String,
        params: CredentialMcpOAuthValidateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredentialValidation> =
        mcpOAuthValidate(params.toBuilder().credentialId(credentialId).build(), requestOptions)

    /** @see mcpOAuthValidate */
    fun mcpOAuthValidate(
        params: CredentialMcpOAuthValidateParams
    ): CompletableFuture<BetaManagedAgentsCredentialValidation> =
        mcpOAuthValidate(params, RequestOptions.none())

    /** @see mcpOAuthValidate */
    fun mcpOAuthValidate(
        params: CredentialMcpOAuthValidateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaManagedAgentsCredentialValidation>

    /**
     * A view of [CredentialServiceAsync] that provides access to raw HTTP responses for each
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
        ): CredentialServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/vaults/{vault_id}/credentials?beta=true`, but
         * is otherwise the same as [CredentialServiceAsync.create].
         */
        fun create(
            vaultId: String,
            params: CredentialCreateParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            create(vaultId, params, RequestOptions.none())

        /** @see create */
        fun create(
            vaultId: String,
            params: CredentialCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            create(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see create */
        fun create(
            params: CredentialCreateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true`, but is otherwise the same
         * as [CredentialServiceAsync.retrieve].
         */
        fun retrieve(
            credentialId: String,
            params: CredentialRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            retrieve(credentialId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            credentialId: String,
            params: CredentialRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            retrieve(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: CredentialRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: CredentialRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true`, but is otherwise the same
         * as [CredentialServiceAsync.update].
         */
        fun update(
            credentialId: String,
            params: CredentialUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            update(credentialId, params, RequestOptions.none())

        /** @see update */
        fun update(
            credentialId: String,
            params: CredentialUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            update(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see update */
        fun update(
            params: CredentialUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>>

        /**
         * Returns a raw HTTP response for `get /v1/vaults/{vault_id}/credentials?beta=true`, but is
         * otherwise the same as [CredentialServiceAsync.list].
         */
        fun list(vaultId: String): CompletableFuture<HttpResponseFor<CredentialListPageAsync>> =
            list(vaultId, CredentialListParams.none())

        /** @see list */
        fun list(
            vaultId: String,
            params: CredentialListParams = CredentialListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CredentialListPageAsync>> =
            list(params.toBuilder().vaultId(vaultId).build(), requestOptions)

        /** @see list */
        fun list(
            vaultId: String,
            params: CredentialListParams = CredentialListParams.none(),
        ): CompletableFuture<HttpResponseFor<CredentialListPageAsync>> =
            list(vaultId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CredentialListPageAsync>>

        /** @see list */
        fun list(
            params: CredentialListParams
        ): CompletableFuture<HttpResponseFor<CredentialListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            vaultId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CredentialListPageAsync>> =
            list(vaultId, CredentialListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true`, but is otherwise the same
         * as [CredentialServiceAsync.delete].
         */
        fun delete(
            credentialId: String,
            params: CredentialDeleteParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedCredential>> =
            delete(credentialId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            credentialId: String,
            params: CredentialDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedCredential>> =
            delete(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see delete */
        fun delete(
            params: CredentialDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedCredential>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedCredential>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/vaults/{vault_id}/credentials/{credential_id}/archive?beta=true`, but is otherwise
         * the same as [CredentialServiceAsync.archive].
         */
        fun archive(
            credentialId: String,
            params: CredentialArchiveParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            archive(credentialId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            credentialId: String,
            params: CredentialArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            archive(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see archive */
        fun archive(
            params: CredentialArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: CredentialArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/vaults/{vault_id}/credentials/{credential_id}/mcp_oauth_validate?beta=true`, but is
         * otherwise the same as [CredentialServiceAsync.mcpOAuthValidate].
         */
        fun mcpOAuthValidate(
            credentialId: String,
            params: CredentialMcpOAuthValidateParams,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredentialValidation>> =
            mcpOAuthValidate(credentialId, params, RequestOptions.none())

        /** @see mcpOAuthValidate */
        fun mcpOAuthValidate(
            credentialId: String,
            params: CredentialMcpOAuthValidateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredentialValidation>> =
            mcpOAuthValidate(params.toBuilder().credentialId(credentialId).build(), requestOptions)

        /** @see mcpOAuthValidate */
        fun mcpOAuthValidate(
            params: CredentialMcpOAuthValidateParams
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredentialValidation>> =
            mcpOAuthValidate(params, RequestOptions.none())

        /** @see mcpOAuthValidate */
        fun mcpOAuthValidate(
            params: CredentialMcpOAuthValidateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredentialValidation>>
    }
}
