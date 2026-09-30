package com.anthropic.services.async.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.apikeys.ApiKey
import com.anthropic.models.organization.apikeys.ApiKeyListPageAsync
import com.anthropic.models.organization.apikeys.ApiKeyListParams
import com.anthropic.models.organization.apikeys.ApiKeyRetrieveParams
import com.anthropic.models.organization.apikeys.ApiKeyUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ApiKeyServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ApiKeyServiceAsync

    /** Get API Key */
    fun retrieve(apiKeyId: String): CompletableFuture<ApiKey> =
        retrieve(apiKeyId, ApiKeyRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ApiKey> =
        retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
    ): CompletableFuture<ApiKey> = retrieve(apiKeyId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ApiKeyRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ApiKey>

    /** @see retrieve */
    fun retrieve(params: ApiKeyRetrieveParams): CompletableFuture<ApiKey> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(apiKeyId: String, requestOptions: RequestOptions): CompletableFuture<ApiKey> =
        retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

    /** Update API Key */
    fun update(apiKeyId: String): CompletableFuture<ApiKey> =
        update(apiKeyId, ApiKeyUpdateParams.none())

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ApiKey> =
        update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
    ): CompletableFuture<ApiKey> = update(apiKeyId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ApiKeyUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ApiKey>

    /** @see update */
    fun update(params: ApiKeyUpdateParams): CompletableFuture<ApiKey> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(apiKeyId: String, requestOptions: RequestOptions): CompletableFuture<ApiKey> =
        update(apiKeyId, ApiKeyUpdateParams.none(), requestOptions)

    /** List API Keys */
    fun list(): CompletableFuture<ApiKeyListPageAsync> = list(ApiKeyListParams.none())

    /** @see list */
    fun list(
        params: ApiKeyListParams = ApiKeyListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<ApiKeyListPageAsync>

    /** @see list */
    fun list(
        params: ApiKeyListParams = ApiKeyListParams.none()
    ): CompletableFuture<ApiKeyListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<ApiKeyListPageAsync> =
        list(ApiKeyListParams.none(), requestOptions)

    /**
     * A view of [ApiKeyServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ApiKeyServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/api_keys/{api_key_id}`, but is
         * otherwise the same as [ApiKeyServiceAsync.retrieve].
         */
        fun retrieve(apiKeyId: String): CompletableFuture<HttpResponseFor<ApiKey>> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ApiKey>> =
            retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<ApiKey>> =
            retrieve(apiKeyId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ApiKeyRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ApiKey>>

        /** @see retrieve */
        fun retrieve(params: ApiKeyRetrieveParams): CompletableFuture<HttpResponseFor<ApiKey>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            apiKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ApiKey>> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/api_keys/{api_key_id}`, but is
         * otherwise the same as [ApiKeyServiceAsync.update].
         */
        fun update(apiKeyId: String): CompletableFuture<HttpResponseFor<ApiKey>> =
            update(apiKeyId, ApiKeyUpdateParams.none())

        /** @see update */
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ApiKey>> =
            update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see update */
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<ApiKey>> =
            update(apiKeyId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ApiKeyUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ApiKey>>

        /** @see update */
        fun update(params: ApiKeyUpdateParams): CompletableFuture<HttpResponseFor<ApiKey>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            apiKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ApiKey>> =
            update(apiKeyId, ApiKeyUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/api_keys`, but is otherwise the
         * same as [ApiKeyServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<ApiKeyListPageAsync>> =
            list(ApiKeyListParams.none())

        /** @see list */
        fun list(
            params: ApiKeyListParams = ApiKeyListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<ApiKeyListPageAsync>>

        /** @see list */
        fun list(
            params: ApiKeyListParams = ApiKeyListParams.none()
        ): CompletableFuture<HttpResponseFor<ApiKeyListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<ApiKeyListPageAsync>> =
            list(ApiKeyListParams.none(), requestOptions)
    }
}
