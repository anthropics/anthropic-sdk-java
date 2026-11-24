package com.anthropic.services.async.beta.userprofiles.providers.google

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.BetaGoogleManagedDataControls
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface DataControlServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DataControlServiceAsync

    /**
     * Get the profile's encryption keys and storage buckets for Claude on Google Cloud's Agent
     * Platform, with each region's status.
     */
    fun retrieve(userProfileId: String): CompletableFuture<BetaGoogleManagedDataControls> =
        retrieve(userProfileId, DataControlRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedDataControls> =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
    ): CompletableFuture<BetaGoogleManagedDataControls> =
        retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DataControlRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedDataControls>

    /** @see retrieve */
    fun retrieve(
        params: DataControlRetrieveParams
    ): CompletableFuture<BetaGoogleManagedDataControls> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaGoogleManagedDataControls> =
        retrieve(userProfileId, DataControlRetrieveParams.none(), requestOptions)

    /**
     * Turn a control on or off, or change the key or bucket set for a region. Controls and regions
     * that you leave out stay as they are.
     */
    fun update(userProfileId: String): CompletableFuture<BetaGoogleManagedDataControls> =
        update(userProfileId, DataControlUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedDataControls> =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
    ): CompletableFuture<BetaGoogleManagedDataControls> =
        update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: DataControlUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaGoogleManagedDataControls>

    /** @see update */
    fun update(params: DataControlUpdateParams): CompletableFuture<BetaGoogleManagedDataControls> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaGoogleManagedDataControls> =
        update(userProfileId, DataControlUpdateParams.none(), requestOptions)

    /**
     * A view of [DataControlServiceAsync] that provides access to raw HTTP responses for each
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
        ): DataControlServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/user_profiles/{user_profile_id}/providers/google/data_controls`, but is otherwise the
         * same as [DataControlServiceAsync.retrieve].
         */
        fun retrieve(
            userProfileId: String
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            retrieve(userProfileId, DataControlRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: DataControlRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>>

        /** @see retrieve */
        fun retrieve(
            params: DataControlRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            retrieve(userProfileId, DataControlRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/user_profiles/{user_profile_id}/providers/google/data_controls`, but is otherwise the
         * same as [DataControlServiceAsync.update].
         */
        fun update(
            userProfileId: String
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            update(userProfileId, DataControlUpdateParams.none())

        /** @see update */
        fun update(
            userProfileId: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        fun update(
            userProfileId: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            update(userProfileId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: DataControlUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>>

        /** @see update */
        fun update(
            params: DataControlUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaGoogleManagedDataControls>> =
            update(userProfileId, DataControlUpdateParams.none(), requestOptions)
    }
}
