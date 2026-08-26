// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.apikeys.ApiKeyListPage
import com.anthropic.models.beta.organization.apikeys.ApiKeyListParams
import com.anthropic.models.beta.organization.apikeys.ApiKeyRetrieveParams
import com.anthropic.models.beta.organization.apikeys.ApiKeyUpdateParams
import com.anthropic.models.beta.organization.apikeys.BetaApiKey
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface ApiKeyService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ApiKeyService

    /** Get API Key */
    fun retrieve(apiKeyId: String): BetaApiKey = retrieve(apiKeyId, ApiKeyRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaApiKey = retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
    ): BetaApiKey = retrieve(apiKeyId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ApiKeyRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaApiKey

    /** @see retrieve */
    fun retrieve(params: ApiKeyRetrieveParams): BetaApiKey = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(apiKeyId: String, requestOptions: RequestOptions): BetaApiKey =
        retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

    /** Update API Key */
    fun update(apiKeyId: String): BetaApiKey = update(apiKeyId, ApiKeyUpdateParams.none())

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaApiKey = update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
    ): BetaApiKey = update(apiKeyId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ApiKeyUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaApiKey

    /** @see update */
    fun update(params: ApiKeyUpdateParams): BetaApiKey = update(params, RequestOptions.none())

    /** @see update */
    fun update(apiKeyId: String, requestOptions: RequestOptions): BetaApiKey =
        update(apiKeyId, ApiKeyUpdateParams.none(), requestOptions)

    /** List API Keys */
    fun list(): ApiKeyListPage = list(ApiKeyListParams.none())

    /** @see list */
    fun list(
        params: ApiKeyListParams = ApiKeyListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKeyListPage

    /** @see list */
    fun list(params: ApiKeyListParams = ApiKeyListParams.none()): ApiKeyListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): ApiKeyListPage =
        list(ApiKeyListParams.none(), requestOptions)

    /** A view of [ApiKeyService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ApiKeyService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/api_keys/{api_key_id}?beta=true`,
         * but is otherwise the same as [ApiKeyService.retrieve].
         */
        @MustBeClosed
        fun retrieve(apiKeyId: String): HttpResponseFor<BetaApiKey> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaApiKey> =
            retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        ): HttpResponseFor<BetaApiKey> = retrieve(apiKeyId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ApiKeyRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaApiKey>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ApiKeyRetrieveParams): HttpResponseFor<BetaApiKey> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            apiKeyId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaApiKey> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/api_keys/{api_key_id}?beta=true`,
         * but is otherwise the same as [ApiKeyService.update].
         */
        @MustBeClosed
        fun update(apiKeyId: String): HttpResponseFor<BetaApiKey> =
            update(apiKeyId, ApiKeyUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaApiKey> =
            update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        ): HttpResponseFor<BetaApiKey> = update(apiKeyId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ApiKeyUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaApiKey>

        /** @see update */
        @MustBeClosed
        fun update(params: ApiKeyUpdateParams): HttpResponseFor<BetaApiKey> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(apiKeyId: String, requestOptions: RequestOptions): HttpResponseFor<BetaApiKey> =
            update(apiKeyId, ApiKeyUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/api_keys?beta=true`, but is
         * otherwise the same as [ApiKeyService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<ApiKeyListPage> = list(ApiKeyListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: ApiKeyListParams = ApiKeyListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKeyListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: ApiKeyListParams = ApiKeyListParams.none()
        ): HttpResponseFor<ApiKeyListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ApiKeyListPage> =
            list(ApiKeyListParams.none(), requestOptions)
    }
}
