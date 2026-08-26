// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.externalkeys.BetaExternalKey
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteResponse
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListPageAsync
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyRetrieveParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyUpdateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ExternalKeyServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalKeyServiceAsync

    /** Create an external key config owned by the caller's organization. */
    fun create(params: ExternalKeyCreateParams): CompletableFuture<BetaExternalKey> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ExternalKeyCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalKey>

    /** Retrieve a single external key config in the caller's organization by ID. */
    fun retrieve(externalKeyId: String): CompletableFuture<BetaExternalKey> =
        retrieve(externalKeyId, ExternalKeyRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        externalKeyId: String,
        params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalKey> =
        retrieve(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        externalKeyId: String,
        params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
    ): CompletableFuture<BetaExternalKey> = retrieve(externalKeyId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ExternalKeyRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalKey>

    /** @see retrieve */
    fun retrieve(params: ExternalKeyRetrieveParams): CompletableFuture<BetaExternalKey> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        externalKeyId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalKey> =
        retrieve(externalKeyId, ExternalKeyRetrieveParams.none(), requestOptions)

    /**
     * Partially update an external key config. Omitted fields are left unchanged.
     *
     * `display_name` is always editable. `geo` and `provider_config` cannot be changed once any
     * workspace references this config, because previously encrypted data requires the original key
     * identity to decrypt.
     */
    fun update(externalKeyId: String): CompletableFuture<BetaExternalKey> =
        update(externalKeyId, ExternalKeyUpdateParams.none())

    /** @see update */
    fun update(
        externalKeyId: String,
        params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalKey> =
        update(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see update */
    fun update(
        externalKeyId: String,
        params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
    ): CompletableFuture<BetaExternalKey> = update(externalKeyId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ExternalKeyUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaExternalKey>

    /** @see update */
    fun update(params: ExternalKeyUpdateParams): CompletableFuture<BetaExternalKey> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        externalKeyId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalKey> =
        update(externalKeyId, ExternalKeyUpdateParams.none(), requestOptions)

    /**
     * List external key configs in the caller's organization.
     *
     * Results are ordered by creation time (newest first). Use the `next_page` cursor from the
     * response to fetch subsequent pages.
     */
    fun list(): CompletableFuture<ExternalKeyListPageAsync> = list(ExternalKeyListParams.none())

    /** @see list */
    fun list(
        params: ExternalKeyListParams = ExternalKeyListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ExternalKeyListPageAsync>

    /** @see list */
    fun list(
        params: ExternalKeyListParams = ExternalKeyListParams.none()
    ): CompletableFuture<ExternalKeyListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ExternalKeyListPageAsync> =
        list(ExternalKeyListParams.none(), requestOptions)

    /**
     * Delete an external key config.
     *
     * The request is rejected if any workspace still references this config.
     */
    fun delete(externalKeyId: String): CompletableFuture<ExternalKeyDeleteResponse> =
        delete(externalKeyId, ExternalKeyDeleteParams.none())

    /** @see delete */
    fun delete(
        externalKeyId: String,
        params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ExternalKeyDeleteResponse> =
        delete(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see delete */
    fun delete(
        externalKeyId: String,
        params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
    ): CompletableFuture<ExternalKeyDeleteResponse> =
        delete(externalKeyId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: ExternalKeyDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ExternalKeyDeleteResponse>

    /** @see delete */
    fun delete(params: ExternalKeyDeleteParams): CompletableFuture<ExternalKeyDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        externalKeyId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExternalKeyDeleteResponse> =
        delete(externalKeyId, ExternalKeyDeleteParams.none(), requestOptions)

    /**
     * Validate an external key config against the customer's KMS.
     *
     * Anthropic performs an encrypt/decrypt roundtrip against the configured KMS key and waits up
     * to 30 seconds for the result. The response status is `success` if the roundtrip succeeded, or
     * `failure` with an error message if it failed or timed out.
     */
    fun validate(externalKeyId: String): CompletableFuture<ExternalKeyValidateResponse> =
        validate(externalKeyId, ExternalKeyValidateParams.none())

    /** @see validate */
    fun validate(
        externalKeyId: String,
        params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ExternalKeyValidateResponse> =
        validate(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see validate */
    fun validate(
        externalKeyId: String,
        params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
    ): CompletableFuture<ExternalKeyValidateResponse> =
        validate(externalKeyId, params, RequestOptions.none())

    /** @see validate */
    fun validate(
        params: ExternalKeyValidateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ExternalKeyValidateResponse>

    /** @see validate */
    fun validate(
        params: ExternalKeyValidateParams
    ): CompletableFuture<ExternalKeyValidateResponse> = validate(params, RequestOptions.none())

    /** @see validate */
    fun validate(
        externalKeyId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExternalKeyValidateResponse> =
        validate(externalKeyId, ExternalKeyValidateParams.none(), requestOptions)

    /**
     * A view of [ExternalKeyServiceAsync] that provides access to raw HTTP responses for each
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
        ): ExternalKeyServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/external_keys?beta=true`, but is
         * otherwise the same as [ExternalKeyServiceAsync.create].
         */
        fun create(
            params: ExternalKeyCreateParams
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: ExternalKeyCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/external_keys/{external_key_id}?beta=true`, but is otherwise the same
         * as [ExternalKeyServiceAsync.retrieve].
         */
        fun retrieve(externalKeyId: String): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            retrieve(externalKeyId, ExternalKeyRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            externalKeyId: String,
            params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            retrieve(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            externalKeyId: String,
            params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            retrieve(externalKeyId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ExternalKeyRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>>

        /** @see retrieve */
        fun retrieve(
            params: ExternalKeyRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            retrieve(externalKeyId, ExternalKeyRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_keys/{external_key_id}?beta=true`, but is otherwise the same
         * as [ExternalKeyServiceAsync.update].
         */
        fun update(externalKeyId: String): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            update(externalKeyId, ExternalKeyUpdateParams.none())

        /** @see update */
        fun update(
            externalKeyId: String,
            params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            update(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see update */
        fun update(
            externalKeyId: String,
            params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            update(externalKeyId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ExternalKeyUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>>

        /** @see update */
        fun update(
            params: ExternalKeyUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> =
            update(externalKeyId, ExternalKeyUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/external_keys?beta=true`, but is
         * otherwise the same as [ExternalKeyServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<ExternalKeyListPageAsync>> =
            list(ExternalKeyListParams.none())

        /** @see list */
        fun list(
            params: ExternalKeyListParams = ExternalKeyListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyListPageAsync>>

        /** @see list */
        fun list(
            params: ExternalKeyListParams = ExternalKeyListParams.none()
        ): CompletableFuture<HttpResponseFor<ExternalKeyListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ExternalKeyListPageAsync>> =
            list(ExternalKeyListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/external_keys/{external_key_id}?beta=true`, but is otherwise the same
         * as [ExternalKeyServiceAsync.delete].
         */
        fun delete(
            externalKeyId: String
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>> =
            delete(externalKeyId, ExternalKeyDeleteParams.none())

        /** @see delete */
        fun delete(
            externalKeyId: String,
            params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>> =
            delete(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see delete */
        fun delete(
            externalKeyId: String,
            params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>> =
            delete(externalKeyId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: ExternalKeyDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>>

        /** @see delete */
        fun delete(
            params: ExternalKeyDeleteParams
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>> =
            delete(externalKeyId, ExternalKeyDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_keys/{external_key_id}/validate?beta=true`, but is otherwise
         * the same as [ExternalKeyServiceAsync.validate].
         */
        fun validate(
            externalKeyId: String
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>> =
            validate(externalKeyId, ExternalKeyValidateParams.none())

        /** @see validate */
        fun validate(
            externalKeyId: String,
            params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>> =
            validate(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see validate */
        fun validate(
            externalKeyId: String,
            params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>> =
            validate(externalKeyId, params, RequestOptions.none())

        /** @see validate */
        fun validate(
            params: ExternalKeyValidateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>>

        /** @see validate */
        fun validate(
            params: ExternalKeyValidateParams
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>> =
            validate(params, RequestOptions.none())

        /** @see validate */
        fun validate(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>> =
            validate(externalKeyId, ExternalKeyValidateParams.none(), requestOptions)
    }
}
