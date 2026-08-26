// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.apikeys.ApiKeyListPageAsync
import com.anthropic.models.beta.organization.apikeys.ApiKeyListParams
import com.anthropic.models.beta.organization.apikeys.ApiKeyRetrieveParams
import com.anthropic.models.beta.organization.apikeys.ApiKeyUpdateParams
import com.anthropic.models.beta.organization.apikeys.BetaApiKey
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
    fun retrieve(apiKeyId: String): CompletableFuture<BetaApiKey> =
        retrieve(apiKeyId, ApiKeyRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaApiKey> =
        retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        apiKeyId: String,
        params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
    ): CompletableFuture<BetaApiKey> = retrieve(apiKeyId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ApiKeyRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaApiKey>

    /** @see retrieve */
    fun retrieve(params: ApiKeyRetrieveParams): CompletableFuture<BetaApiKey> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(apiKeyId: String, requestOptions: RequestOptions): CompletableFuture<BetaApiKey> =
        retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

    /** Update API Key */
    fun update(apiKeyId: String): CompletableFuture<BetaApiKey> =
        update(apiKeyId, ApiKeyUpdateParams.none())

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaApiKey> =
        update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

    /** @see update */
    fun update(
        apiKeyId: String,
        params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
    ): CompletableFuture<BetaApiKey> = update(apiKeyId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: ApiKeyUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaApiKey>

    /** @see update */
    fun update(params: ApiKeyUpdateParams): CompletableFuture<BetaApiKey> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(apiKeyId: String, requestOptions: RequestOptions): CompletableFuture<BetaApiKey> =
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
         * Returns a raw HTTP response for `get /v1/organizations/api_keys/{api_key_id}?beta=true`,
         * but is otherwise the same as [ApiKeyServiceAsync.retrieve].
         */
        fun retrieve(apiKeyId: String): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            retrieve(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            apiKeyId: String,
            params: ApiKeyRetrieveParams = ApiKeyRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            retrieve(apiKeyId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ApiKeyRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaApiKey>>

        /** @see retrieve */
        fun retrieve(params: ApiKeyRetrieveParams): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            apiKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            retrieve(apiKeyId, ApiKeyRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/api_keys/{api_key_id}?beta=true`,
         * but is otherwise the same as [ApiKeyServiceAsync.update].
         */
        fun update(apiKeyId: String): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            update(apiKeyId, ApiKeyUpdateParams.none())

        /** @see update */
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            update(params.toBuilder().apiKeyId(apiKeyId).build(), requestOptions)

        /** @see update */
        fun update(
            apiKeyId: String,
            params: ApiKeyUpdateParams = ApiKeyUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            update(apiKeyId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: ApiKeyUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaApiKey>>

        /** @see update */
        fun update(params: ApiKeyUpdateParams): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            apiKeyId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaApiKey>> =
            update(apiKeyId, ApiKeyUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/api_keys?beta=true`, but is
         * otherwise the same as [ApiKeyServiceAsync.list].
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
