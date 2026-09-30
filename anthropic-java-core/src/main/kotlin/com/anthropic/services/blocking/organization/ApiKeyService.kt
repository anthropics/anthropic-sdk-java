package com.anthropic.services.blocking.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.apikeys.ApiKey
import com.anthropic.models.organization.apikeys.ApiKeyListPage
import com.anthropic.models.organization.apikeys.ApiKeyListParams
import com.anthropic.models.organization.apikeys.ApiKeyRetrieveParams
import com.anthropic.models.organization.apikeys.ApiKeyUpdateParams
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
    fun retrieve(apiKeyId: String): ApiKey = retrieve(apiKeyId, ApiKeyRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKey = retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
    ): ApiKey = retrieve(apiKeyId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ApiKeyRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKey

    /** @see retrieve */
    fun retrieve(params: ApiKeyRetrieveParams): ApiKey = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(apiKeyId: String, requestOptions: RequestOptions): ApiKey =
        retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

    /** Update API Key */
    fun update(apiKeyId: String): ApiKey = update(apiKeyId, ApiKeyUpdateParams.none())

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKey = update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see update */
    fun update(apiKeyId: String, params: ApiKeyUpdateParams = ApiKeyUpdateParams.none()): ApiKey =
        update(apiKeyId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ApiKeyUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKey

    /** @see update */
    fun update(params: ApiKeyUpdateParams): ApiKey = update(params, RequestOptions.none())

    /** @see update */
    fun update(apiKeyId: String, requestOptions: RequestOptions): ApiKey =
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
         * Returns a raw HTTP response for `get /v1/organizations/api_keys/{api_key_id}`, but is
         * otherwise the same as [ApiKeyService.retrieve].
         */
        @MustBeClosed
        fun retrieve(apiKeyId: String): HttpResponseFor<ApiKey> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKey> =
            retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        ): HttpResponseFor<ApiKey> = retrieve(apiKeyId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ApiKeyRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKey>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: ApiKeyRetrieveParams): HttpResponseFor<ApiKey> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(apiKeyId: String, requestOptions: RequestOptions): HttpResponseFor<ApiKey> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/api_keys/{api_key_id}`, but is
         * otherwise the same as [ApiKeyService.update].
         */
        @MustBeClosed
        fun update(apiKeyId: String): HttpResponseFor<ApiKey> =
            update(apiKeyId, ApiKeyUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKey> =
            update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        ): HttpResponseFor<ApiKey> = update(apiKeyId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: ApiKeyUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKey>

        /** @see update */
        @MustBeClosed
        fun update(params: ApiKeyUpdateParams): HttpResponseFor<ApiKey> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(apiKeyId: String, requestOptions: RequestOptions): HttpResponseFor<ApiKey> =
            update(apiKeyId, ApiKeyUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/api_keys`, but is otherwise the
         * same as [ApiKeyService.list].
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
