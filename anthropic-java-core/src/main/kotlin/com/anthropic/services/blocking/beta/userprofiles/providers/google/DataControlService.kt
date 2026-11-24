package com.anthropic.services.blocking.beta.userprofiles.providers.google

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.BetaGoogleManagedDataControls
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface DataControlService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): DataControlService

    /**
     * Get the profile's encryption keys and storage buckets for Claude on Google Cloud's Agent
     * Platform, with each region's status.
     */
    fun retrieve(userProfileId: String): BetaGoogleManagedDataControls =
        retrieve(userProfileId, DataControlRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedDataControls =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
    ): BetaGoogleManagedDataControls = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: DataControlRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedDataControls

    /** @see retrieve */
    fun retrieve(params: DataControlRetrieveParams): BetaGoogleManagedDataControls =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): BetaGoogleManagedDataControls =
        retrieve(userProfileId, DataControlRetrieveParams.none(), requestOptions)

    /**
     * Turn a control on or off, or change the key or bucket set for a region. Controls and regions
     * that you leave out stay as they are.
     */
    fun update(userProfileId: String): BetaGoogleManagedDataControls =
        update(userProfileId, DataControlUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedDataControls =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: DataControlUpdateParams = DataControlUpdateParams.none(),
    ): BetaGoogleManagedDataControls = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: DataControlUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaGoogleManagedDataControls

    /** @see update */
    fun update(params: DataControlUpdateParams): BetaGoogleManagedDataControls =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): BetaGoogleManagedDataControls =
        update(userProfileId, DataControlUpdateParams.none(), requestOptions)

    /**
     * A view of [DataControlService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): DataControlService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/user_profiles/{user_profile_id}/providers/google/data_controls`, but is otherwise the
         * same as [DataControlService.retrieve].
         */
        @MustBeClosed
        fun retrieve(userProfileId: String): HttpResponseFor<BetaGoogleManagedDataControls> =
            retrieve(userProfileId, DataControlRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedDataControls> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: DataControlRetrieveParams = DataControlRetrieveParams.none(),
        ): HttpResponseFor<BetaGoogleManagedDataControls> =
            retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DataControlRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedDataControls>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DataControlRetrieveParams
        ): HttpResponseFor<BetaGoogleManagedDataControls> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaGoogleManagedDataControls> =
            retrieve(userProfileId, DataControlRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/user_profiles/{user_profile_id}/providers/google/data_controls`, but is otherwise the
         * same as [DataControlService.update].
         */
        @MustBeClosed
        fun update(userProfileId: String): HttpResponseFor<BetaGoogleManagedDataControls> =
            update(userProfileId, DataControlUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedDataControls> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: DataControlUpdateParams = DataControlUpdateParams.none(),
        ): HttpResponseFor<BetaGoogleManagedDataControls> =
            update(userProfileId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: DataControlUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaGoogleManagedDataControls>

        /** @see update */
        @MustBeClosed
        fun update(
            params: DataControlUpdateParams
        ): HttpResponseFor<BetaGoogleManagedDataControls> = update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaGoogleManagedDataControls> =
            update(userProfileId, DataControlUpdateParams.none(), requestOptions)
    }
}
