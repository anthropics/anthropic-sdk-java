// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.externalkeys.BetaExternalKey
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteResponse
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListPage
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyRetrieveParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyUpdateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateResponse
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ExternalKeyService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalKeyService

    /** Create an external key config owned by the caller's organization. */
    fun create(params: ExternalKeyCreateParams): BetaExternalKey =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: ExternalKeyCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalKey

    /** Retrieve a single external key config in the caller's organization by ID. */
    fun retrieve(externalKeyId: String): BetaExternalKey =
        retrieve(externalKeyId, ExternalKeyRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        externalKeyId: String,
        params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalKey =
        retrieve(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        externalKeyId: String,
        params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
    ): BetaExternalKey = retrieve(externalKeyId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ExternalKeyRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalKey

    /** @see retrieve */
    fun retrieve(params: ExternalKeyRetrieveParams): BetaExternalKey =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(externalKeyId: String, requestOptions: RequestOptions): BetaExternalKey =
        retrieve(externalKeyId, ExternalKeyRetrieveParams.none(), requestOptions)

    /**
     * Partially update an external key config. Omitted fields are left unchanged.
     *
     * `display_name` is always editable. `geo` and `provider_config` cannot be changed once any
     * workspace references this config, because previously encrypted data requires the original key
     * identity to decrypt.
     */
    fun update(externalKeyId: String): BetaExternalKey =
        update(externalKeyId, ExternalKeyUpdateParams.none())

    /** @see update */
    fun update(
        externalKeyId: String,
        params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalKey =
        update(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see update */
    fun update(
        externalKeyId: String,
        params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
    ): BetaExternalKey = update(externalKeyId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ExternalKeyUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaExternalKey

    /** @see update */
    fun update(params: ExternalKeyUpdateParams): BetaExternalKey =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(externalKeyId: String, requestOptions: RequestOptions): BetaExternalKey =
        update(externalKeyId, ExternalKeyUpdateParams.none(), requestOptions)

    /**
     * List external key configs in the caller's organization.
     *
     * Results are ordered by creation time (newest first). Use the `next_page` cursor from the
     * response to fetch subsequent pages.
     */
    fun list(): ExternalKeyListPage = list(ExternalKeyListParams.none())

    /** @see list */
    fun list(
        params: ExternalKeyListParams = ExternalKeyListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExternalKeyListPage

    /** @see list */
    fun list(params: ExternalKeyListParams = ExternalKeyListParams.none()): ExternalKeyListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ExternalKeyListPage =
        list(ExternalKeyListParams.none(), requestOptions)

    /**
     * Delete an external key config.
     *
     * The request is rejected if any workspace still references this config.
     */
    fun delete(externalKeyId: String): ExternalKeyDeleteResponse =
        delete(externalKeyId, ExternalKeyDeleteParams.none())

    /** @see delete */
    fun delete(
        externalKeyId: String,
        params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExternalKeyDeleteResponse =
        delete(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see delete */
    fun delete(
        externalKeyId: String,
        params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
    ): ExternalKeyDeleteResponse = delete(externalKeyId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: ExternalKeyDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExternalKeyDeleteResponse

    /** @see delete */
    fun delete(params: ExternalKeyDeleteParams): ExternalKeyDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(externalKeyId: String, requestOptions: RequestOptions): ExternalKeyDeleteResponse =
        delete(externalKeyId, ExternalKeyDeleteParams.none(), requestOptions)

    /**
     * Validate an external key config against the customer's KMS.
     *
     * Anthropic performs an encrypt/decrypt roundtrip against the configured KMS key and waits up
     * to 30 seconds for the result. The response status is `success` if the roundtrip succeeded, or
     * `failure` with an error message if it failed or timed out.
     */
    fun validate(externalKeyId: String): ExternalKeyValidateResponse =
        validate(externalKeyId, ExternalKeyValidateParams.none())

    /** @see validate */
    fun validate(
        externalKeyId: String,
        params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExternalKeyValidateResponse =
        validate(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

    /** @see validate */
    fun validate(
        externalKeyId: String,
        params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
    ): ExternalKeyValidateResponse = validate(externalKeyId, params, RequestOptions.none())

    /** @see validate */
    fun validate(
        params: ExternalKeyValidateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExternalKeyValidateResponse

    /** @see validate */
    fun validate(params: ExternalKeyValidateParams): ExternalKeyValidateResponse =
        validate(params, RequestOptions.none())

    /** @see validate */
    fun validate(
        externalKeyId: String,
        requestOptions: RequestOptions,
    ): ExternalKeyValidateResponse =
        validate(externalKeyId, ExternalKeyValidateParams.none(), requestOptions)

    /**
     * A view of [ExternalKeyService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExternalKeyService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/external_keys?beta=true`, but is
         * otherwise the same as [ExternalKeyService.create].
         */
        @MustBeClosed
        fun create(params: ExternalKeyCreateParams): HttpResponseFor<BetaExternalKey> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: ExternalKeyCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalKey>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/external_keys/{external_key_id}?beta=true`, but is otherwise the same
         * as [ExternalKeyService.retrieve].
         */
        @MustBeClosed
        fun retrieve(externalKeyId: String): HttpResponseFor<BetaExternalKey> =
            retrieve(externalKeyId, ExternalKeyRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            externalKeyId: String,
            params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalKey> =
            retrieve(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            externalKeyId: String,
            params: ExternalKeyRetrieveParams = ExternalKeyRetrieveParams.none(),
        ): HttpResponseFor<BetaExternalKey> = retrieve(externalKeyId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ExternalKeyRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalKey>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ExternalKeyRetrieveParams): HttpResponseFor<BetaExternalKey> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalKey> =
            retrieve(externalKeyId, ExternalKeyRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_keys/{external_key_id}?beta=true`, but is otherwise the same
         * as [ExternalKeyService.update].
         */
        @MustBeClosed
        fun update(externalKeyId: String): HttpResponseFor<BetaExternalKey> =
            update(externalKeyId, ExternalKeyUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            externalKeyId: String,
            params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalKey> =
            update(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            externalKeyId: String,
            params: ExternalKeyUpdateParams = ExternalKeyUpdateParams.none(),
        ): HttpResponseFor<BetaExternalKey> = update(externalKeyId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ExternalKeyUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaExternalKey>

        /** @see update */
        @MustBeClosed
        fun update(params: ExternalKeyUpdateParams): HttpResponseFor<BetaExternalKey> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalKey> =
            update(externalKeyId, ExternalKeyUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/external_keys?beta=true`, but is
         * otherwise the same as [ExternalKeyService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<ExternalKeyListPage> = list(ExternalKeyListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ExternalKeyListParams = ExternalKeyListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExternalKeyListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: ExternalKeyListParams = ExternalKeyListParams.none()
        ): HttpResponseFor<ExternalKeyListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ExternalKeyListPage> =
            list(ExternalKeyListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/external_keys/{external_key_id}?beta=true`, but is otherwise the same
         * as [ExternalKeyService.delete].
         */
        @MustBeClosed
        fun delete(externalKeyId: String): HttpResponseFor<ExternalKeyDeleteResponse> =
            delete(externalKeyId, ExternalKeyDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            externalKeyId: String,
            params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExternalKeyDeleteResponse> =
            delete(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            externalKeyId: String,
            params: ExternalKeyDeleteParams = ExternalKeyDeleteParams.none(),
        ): HttpResponseFor<ExternalKeyDeleteResponse> =
            delete(externalKeyId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: ExternalKeyDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExternalKeyDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(params: ExternalKeyDeleteParams): HttpResponseFor<ExternalKeyDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExternalKeyDeleteResponse> =
            delete(externalKeyId, ExternalKeyDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/external_keys/{external_key_id}/validate?beta=true`, but is otherwise
         * the same as [ExternalKeyService.validate].
         */
        @MustBeClosed
        fun validate(externalKeyId: String): HttpResponseFor<ExternalKeyValidateResponse> =
            validate(externalKeyId, ExternalKeyValidateParams.none())

        /** @see validate */
        @MustBeClosed
        fun validate(
            externalKeyId: String,
            params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExternalKeyValidateResponse> =
            validate(params.toBuilder().externalKeyId(externalKeyId).build(), requestOptions)

        /** @see validate */
        @MustBeClosed
        fun validate(
            externalKeyId: String,
            params: ExternalKeyValidateParams = ExternalKeyValidateParams.none(),
        ): HttpResponseFor<ExternalKeyValidateResponse> =
            validate(externalKeyId, params, RequestOptions.none())

        /** @see validate */
        @MustBeClosed
        fun validate(
            params: ExternalKeyValidateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExternalKeyValidateResponse>

        /** @see validate */
        @MustBeClosed
        fun validate(
            params: ExternalKeyValidateParams
        ): HttpResponseFor<ExternalKeyValidateResponse> = validate(params, RequestOptions.none())

        /** @see validate */
        @MustBeClosed
        fun validate(
            externalKeyId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExternalKeyValidateResponse> =
            validate(externalKeyId, ExternalKeyValidateParams.none(), requestOptions)
    }
}
